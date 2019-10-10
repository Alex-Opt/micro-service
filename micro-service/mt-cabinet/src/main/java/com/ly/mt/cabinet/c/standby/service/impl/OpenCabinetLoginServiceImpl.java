package com.ly.mt.cabinet.c.standby.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.util.AesUtils;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.standby.entity.GzgUserOpenDevice;
import com.ly.mt.cabinet.c.standby.entity.LoginRespVo;
import com.ly.mt.cabinet.c.standby.entity.OpenCabinetLoginRequestVo;
import com.ly.mt.cabinet.c.standby.service.OpenCabinetLoginService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.feign.DataCenterMethod.GZG_USER_OPEN_DEVICE_GET_BY_PHONE;
import static com.ly.mt.core.redis.RedisKey.*;

@Service
public class OpenCabinetLoginServiceImpl extends BaseServiceImpl implements OpenCabinetLoginService {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(OpenCabinetLoginServiceImpl.class);

    private static final long EXPIRETIME = 1000 * 60 * 60;

    @Override
    public ResponseJson loginByPhone(OpenCabinetLoginRequestVo body) {
        String token = "";

        // 校验登录验证码
        String dynamicCode = (String) redisService.get(REDIS_GZG_OPEN_DEVICE_LOGIN, body.getPhone());
        if (StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "登录验证码失效！");
        }
        if (!dynamicCode.equals(dynamicCode)) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "登录验证码错误！");
        }

        GzgUserOpenDevice gzgUserOpenDevice = getGzgUserOpenDevice(body.getPhone());
        if (gzgUserOpenDevice == null) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "该用户无访问权限");
        }
        token = creareToken(gzgUserOpenDevice.getId(), body.getPhone());

        LoginRespVo loginRespVo = new LoginRespVo(token, body.getPhone());

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, loginRespVo);


    }


    @Override
    public ResponseJson loginOut(String phone) {
        redisService.del(REDIS_CABINET_B_LOGIN, phone);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }


    /**
     * 一小时过期
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
        redisService.set(REDIS_GZG_OPEN_DEVICE_LOGIN_TOKEN, phone, token, EXPIRETIME, TimeUnit.MILLISECONDS);
        return token;
    }

    public GzgUserOpenDevice getGzgUserOpenDevice(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        String result = callDataCenter(GZG_USER_OPEN_DEVICE_GET_BY_PHONE, jsonObject);
        GzgUserOpenDevice gzgUserOpenDevice = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgUserOpenDevice.class);
        return gzgUserOpenDevice;
    }


}
