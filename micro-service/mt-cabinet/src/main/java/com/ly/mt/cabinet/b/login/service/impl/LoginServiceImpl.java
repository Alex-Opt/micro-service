package com.ly.mt.cabinet.b.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.login.service.LoginService;
import com.ly.mt.cabinet.b.login.vo.LoginResultVo;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.user.vo.CabInetBUserVo;
import com.ly.mt.cabinet.b.util.TokenHS256;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.UserStatus;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_GZG_B;
import static com.ly.mt.core.base.dict.RoleType.ROLE_TYPE_GB_BD;
import static com.ly.mt.core.base.dict.RoleType.ROLE_TYPE_GB_UNKNOWN;
import static com.ly.mt.core.base.dict.TokenEnum.TOKEN_EXPIRE;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_CODE_CABINET_B_LOGIN;


@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    @Autowired
    private LoginTokenServiceImpl tokenService;

    //获取钥匙
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * 手机号/验证码登录
     * 每次用户登录时需要更新client_id
     * 首次登录注册时也需要更新client_id
     * @param mobile/dynamicCode
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson dynamicCodeLogin(String mobile,String dynamicCode,String clientId)  throws Exception {
        // 校验登录验证码
        String redisCode = (String) redisService.get(REDIS_CODE_CABINET_B_LOGIN, mobile);
        LOGGER.info("CABINET-B-APP-短信验证码登录,发送验证为userId=", redisCode);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码失效！");
        }
        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码错误!");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_name",mobile);
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonObject);
        List<CabInetBUserVo> list = JSONObject.parseObject(result,new TypeReference<List<CabInetBUserVo>>() {});
        //用户不存在时 进行注册
        if (list.isEmpty()) {
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
            JSONObject userObject = new JSONObject();
            userObject.put("quick_type", QUICK_TYPE_GZG_B.getId());//注册来源
            userObject.put("login_name", mobile);
            userObject.put("mobile", mobile);
            userObject.put("create_time", DateUtil.getNowTimeStr());
            userObject.put("id",id);
            userObject.put("user_status", UserStatus.USER_STATUS_USING.getId());
            userObject.put("client_id", clientId);
            String result2 = callDataCenter(USER_INSERT_USER, userObject);
            LOGGER.info("CABINET-B-APP-验证码登录即注册,开始生成token入库并放入缓存");
            //TokenInfo tokenInfo = getTokenInfo();
            LoginResultVo loginResultVo = createToken(id,mobile);
            tokenService.insert(id,loginResultVo.getToken(),TOKEN_EXPIRE.getValue());
            LOGGER.info("CABINET-B-APP-验证码登录即注册,结束生成token入库并放入缓存");
            loginResultVo.setRoleType(ROLE_TYPE_GB_UNKNOWN.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginResultVo);
        }
        CabInetBUserVo blueToothPubUserVo = list.get(0);
        LoginResultVo loginResultVo = createToken(blueToothPubUserVo.getId(),mobile);
        //查询角色
        JSONObject roleParam = new JSONObject();
        roleParam.put("userId",blueToothPubUserVo.getId());
        String roleResult = callDataCenter(BASIC_ROLE_GET, roleParam);
        //更新用户设备id
        JSONObject paramObject = new JSONObject();
        paramObject.put("id",blueToothPubUserVo.getId());
        paramObject.put("client_id",clientId);//用于appPush用
        LOGGER.info("CABINET-B-APP-验证码登录开始更新clientId");
        callDataCenter(USER_UPDATE, paramObject);
        LOGGER.info("CABINET-B-APP-验证码登录结束更新clientId");

        if(StringUtil.isEmpty(roleResult)){//空则是未知角色
            loginResultVo.setRoleType(ROLE_TYPE_GB_UNKNOWN.getId());
        }else{
            loginResultVo.setRoleType(ROLE_TYPE_GB_BD.getId());//BD角色
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginResultVo);
    }


    /**
     * 用户登出
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson loginOut()  throws Exception {
        TokenInfo tokenInfo = getTokenInfo();
        LOGGER.info("蓝牙APP-用户开始退出账号id={}", tokenInfo.getUserId());
        tokenService.delToken(tokenInfo.getUserId());
        LOGGER.info("蓝牙APP-用户结束退出账号id={}", tokenInfo.getUserId());
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "");
    }

    /**
     * @Description 用户登录创建token
     * @Author whl
     */
    private LoginResultVo createToken(String userId,String phone) throws Exception{
        LOGGER.info("CABINET-B-APP-登录后token开始更新缓存=", userId);
        //redisService.set(REDIS_CABINET_B_RSAKEY,userId,String.valueOf(key));//放入新的token进入缓存
        //创建Token
        String token = TokenHS256.createToken(userId,phone);
        //更新数据库和缓存中token
        tokenService.createToken(userId,token);
        LOGGER.info("CABINET-B-APP-登录后token结束更新缓存=", userId+"|token=="+token);
        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setToken(token);
        return loginResultVo;
    }
}
