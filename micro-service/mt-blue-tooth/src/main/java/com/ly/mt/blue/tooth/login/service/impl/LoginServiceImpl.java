package com.ly.mt.blue.tooth.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.login.service.LoginService;
import com.ly.mt.blue.tooth.login.vo.LoginResultVo;
import com.ly.mt.blue.tooth.base.token.service.impl.TokenServiceImpl;
import com.ly.mt.blue.tooth.user.vo.BlueToothPubUserVo;
import com.ly.mt.blue.tooth.user.vo.BlueToothUserVo;
import com.ly.mt.core.base.dict.UserStatus;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.blue.tooth.base.dict.PasswordStatusEnum.PASSWORDNOSET;
import static com.ly.mt.blue.tooth.base.dict.PasswordStatusEnum.PASSWORDSET;
import static com.ly.mt.blue.tooth.base.dict.TokenEnum.TOKEN_EXPIRE;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_BLUETOOTH;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_CODE_BLUETOOTH_LOGIN;


@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private TokenServiceImpl tokenService;
    /**
     * 用户名密码登录
     *
     * @param loginName
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson nameLogin(String loginName, String password) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_name",loginName);
        password = MD5Util.md5(password);
        //jsonObject.put("password",MD5Util.md5(password));
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonObject);
        List<BlueToothPubUserVo> list = JSONObject.parseObject(result,new TypeReference<List<BlueToothPubUserVo>>() {});
        LoginResultVo loginResultVo = new LoginResultVo();
        if (list.isEmpty()) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户名或密码错误!");
        }else{
            BlueToothPubUserVo blueToothPubUserVo = list.get(0);
            //密码为空设置密码状态
            if(StringUtil.isEmpty(blueToothPubUserVo.getPassword())){
                //loginResultVo = saveToken(blueToothPubUserVo.getId(),password);
                loginResultVo.setStatus(PASSWORDNOSET.getId());
            }else if(!blueToothPubUserVo.getPassword().equals(password)){//用户名与密码不一致
                loginResultVo.setStatus(PASSWORDSET.getId());
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户名或密码错误!");
            }else{
                loginResultVo = saveToken(blueToothPubUserVo.getId(),password);
                loginResultVo.setStatus(PASSWORDSET.getId());
            }
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginResultVo);
    }

    /**
     * 手机号/验证码登录
     *
     * @param mobile/dynamicCode
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson dynamicCodeLogin(String mobile,String dynamicCode)  throws Exception {
        // 校验登录验证码
        String redisCode = (String) redisService.get(REDIS_CODE_BLUETOOTH_LOGIN, mobile);

        LOGGER.info("蓝牙APP-短信验证码登录,发送验证为userId=", redisCode);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码失效！");
        }
        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码错误!");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_name",mobile);
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonObject);
        List<BlueToothPubUserVo> list = JSONObject.parseObject(result,new TypeReference<List<BlueToothPubUserVo>>() {});
        //用户不存在时 进行注册
        if (list.isEmpty()) {
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
            JSONObject userObject = new JSONObject();
            userObject.put("quick_type", QUICK_TYPE_BLUETOOTH.getId());
            userObject.put("login_name", mobile);
            userObject.put("mobile", mobile);
            userObject.put("create_time", DateUtil.getNowTimeStr());
            userObject.put("id",id);
            //userObject.put("password",MD5Util.md5(password));//暂无初始密码
            userObject.put("user_status", UserStatus.USER_STATUS_USING.getId());
            String result2 = callDataCenter(USER_INSERT_USER, userObject);
            LOGGER.info("蓝牙APP-验证码登录即注册,开始生成token入库");
            String token = MD5Util.md5(DateUtil.getNowTimeStr() + mobile).toLowerCase();
            tokenService.insert(id,token,TOKEN_EXPIRE.getValue());
            LOGGER.info("蓝牙APP-验证码登录即注册,结束生成token入库");
            LoginResultVo loginResultVo = saveToken(id,redisCode);//用验证码生成token
            loginResultVo.setStatus(PASSWORDNOSET.getId());//未设置
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginResultVo);
        }
        BlueToothPubUserVo blueToothPubUserVo = list.get(0);
        LoginResultVo loginResultVo = saveToken(blueToothPubUserVo.getId(),redisCode);//用验证码生成token
        loginResultVo.setStatus(PASSWORDSET.getId());//已设置
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginResultVo);
    }

    /**
     * @Description 更新用户登录状态
     * @Author whl
     */
    @Override
    public ResponseJson updateFirstLogin() throws Exception{
        String userId = getLoginUserId();
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_login", 1);//更新不是首次登陆
        jsonObject.put("id", userId);
        String result = callDataCenter(USER_UPDATE_USER, jsonObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);

    }

    /**
     * 用户登出
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson loginOut()  throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("蓝牙APP-用户开始退出账号id={}", userId);
        tokenService.delToken(userId);
        LOGGER.info("蓝牙APP-用户结束退出账号id={}", userId);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "");
    }

    /**
     * @Description 用户登录保存Token
     * @Author whl
     */
    private LoginResultVo saveToken(String userId,String password) throws Exception{
        LOGGER.info("蓝牙APP-登录后token开始放入缓存=", userId);
        String token = tokenService.createToken(userId,password);
        LOGGER.info("蓝牙APP-登录后token结束放入缓存=", userId+"|token=="+token);
        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setToken(token);
        loginResultVo.setUserId(userId);
        return loginResultVo;
    }
}
