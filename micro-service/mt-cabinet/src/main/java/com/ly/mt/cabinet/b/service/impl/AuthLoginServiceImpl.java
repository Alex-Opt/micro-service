package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.common.request.LoginRequestBody;
import com.ly.mt.cabinet.b.common.response.TokenRespVo;
import com.ly.mt.cabinet.b.service.AuthLoginService;
import com.ly.mt.cabinet.b.user.vo.CabInetBUserVo;
import com.ly.mt.cabinet.b.util.AesUtils;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.UserStatus;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_GZG_B;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_GZG_B;
import static com.ly.mt.core.base.dict.RoleType.ROLE_TYPE_GB_BD;
import static com.ly.mt.core.base.dict.RoleType.ROLE_TYPE_GB_UNKNOWN;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_CABINET_B_LOGIN;
import static com.ly.mt.core.redis.RedisKey.REDIS_CODE_CABINET_B_LOGIN;

@Service
public class AuthLoginServiceImpl extends BaseServiceImpl implements AuthLoginService {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthLoginServiceImpl.class);

    private static final long EXPIRETIME = 1000 * 60 * 60 * 24 * 7;

    @Override
    public ResponseJson loginByPhone(LoginRequestBody body) {
        String token = "";
        String roleType = "";
        JSONObject roleParam = new JSONObject(1);

        // 校验登录验证码
        String dynamicCode = (String) redisService.get(REDIS_CODE_CABINET_B_LOGIN, body.getPhone());
        if (StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码失效！");
        }
        if (!dynamicCode.equals(body.getDynamicCode())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "登录验证码错误!");
        }
        JSONObject jsonObject = new JSONObject(2);
        jsonObject.put("mobile", body.getPhone());
        //jsonObject.put("quick_type", QUICK_TYPE_GZG_B.getId());
        jsonObject.put("project_type", PROJECT_TYPE_GZG_B.getId());
        LOGGER.info("CABINET-B-APP-查询登录人信息："+JSONObject.toJSONString(jsonObject));
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonObject);
        LOGGER.info("CABINET-B-APP-查询返回登录人信息："+JSONObject.toJSONString(result));

        List<CabInetBUserVo> list = JSONObject.parseObject(result, new TypeReference<List<CabInetBUserVo>>() {});

        //用户不存在时 进行注册
        if (list.isEmpty() || list.size() == 0) {
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
            JSONObject userObject = new JSONObject();
            userObject.put("quick_type", QUICK_TYPE_GZG_B.getId());//注册来源
            userObject.put("login_name", body.getPhone());
            userObject.put("mobile", body.getPhone());
            userObject.put("create_time", DateUtil.getNowTimeStr());
            userObject.put("id", id);
            userObject.put("user_status", UserStatus.USER_STATUS_USING.getId());
            userObject.put("client_id", body.getClientId());
            userObject.put("project_type", PROJECT_TYPE_GZG_B.getId());
            callDataCenter(USER_INSERT_USER, userObject);
            token = creareToken(id, body.getPhone());
            roleType=ROLE_TYPE_GB_UNKNOWN.getId();//新注册时 角色一定是未知的
            //返回token
            TokenRespVo tokenRespVo = new TokenRespVo(token, roleType, body.getPhone());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tokenRespVo);
        }
        CabInetBUserVo cabInetBUserVo = list.get(0);
        token = creareToken(cabInetBUserVo.getId(), body.getPhone());
        roleParam.put("userId", cabInetBUserVo.getId());
        LOGGER.info("CABINET-B-APP-查询登录人角色信息："+JSONObject.toJSONString(roleParam));
        roleType = callDataCenter(BASIC_ROLE_GET, roleParam);
        LOGGER.info("CABINET-B-APP-查询返回登录人角色信息："+JSONObject.toJSONString(roleType));
        if(StringUtil.isEmpty(roleType)){//空则是未知角色
            roleType=ROLE_TYPE_GB_UNKNOWN.getId();
        }
        //更新设备id
        JSONObject paramObject = new JSONObject(2);
        paramObject.put("id", cabInetBUserVo.getId());
        paramObject.put("client_id", body.getClientId());//用于appPush用
        LOGGER.info("CABINET-B-APP-验证码登录开始更新clientId："+JSONObject.toJSONString(paramObject));
        callDataCenter(USER_UPDATE, paramObject);
        //返回token
        TokenRespVo tokenRespVo = new TokenRespVo(token, roleType, body.getPhone());
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,tokenRespVo);
    }


    @Override
    public Resp loginOut(String phone) {
        redisService.del(REDIS_CABINET_B_LOGIN, phone);
        return Resp.createBySuccess();
    }


    /**
     * 一周过期
     *
     * @param userId
     * @param phone
     * @return
     */
    private String creareToken(String userId, String phone) {
        JSONObject jsonObject = new JSONObject(2);
        jsonObject.put("userId", userId);
        jsonObject.put("phoneNo", phone);
        String token = AesUtils.encryptEcbMode(jsonObject.toJSONString());
        redisService.set(REDIS_CABINET_B_LOGIN, phone, token, EXPIRETIME, TimeUnit.MILLISECONDS);
        return token;
    }

}
