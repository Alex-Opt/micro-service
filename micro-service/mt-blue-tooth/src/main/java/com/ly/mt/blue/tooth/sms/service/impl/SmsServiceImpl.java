package com.ly.mt.blue.tooth.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.base.dict.SmsTypeEnum;
import com.ly.mt.blue.tooth.sms.service.SmsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_SMS_SEND;
import static com.ly.mt.core.redis.RedisKey.*;
import static com.ly.mt.core.redis.dict.AlSmsTemplate.*;

@Service
public class SmsServiceImpl extends BaseServiceImpl implements SmsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * 发送短信
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson sendSms(String mobile, String smsType) throws Exception {
        //如果是注册则取注册KEY否则取重置密码KEY
        String redisJson = "";
        //发送短信对象
        JSONObject smsObject = new JSONObject();
        smsObject.put("phone", mobile);
        //注册
        if (smsType.equals(SmsTypeEnum.REGISTER.getId())) {
            redisJson = redisService.get(REDIS_CODE_BLUETOOTH_REGIST, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_BLUETOOTH_REGIST.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_BLUETOOTH_REGIST.getSignName());
        } else if (smsType.equals(SmsTypeEnum.RESETPASSWORD.getId())) {//重置密码
            redisJson = redisService.get(REDIS_CODE_BLUETOOTH_PASSWORD, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_BLUETOOTH_PASSWORD.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_BLUETOOTH_PASSWORD.getSignName());
        } else if (smsType.equals(SmsTypeEnum.LOGIN.getId())) {//登录
            redisJson = redisService.get(REDIS_CODE_BLUETOOTH_LOGIN, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_BLUETOOTH_LOGIN.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_BLUETOOTH_LOGIN.getSignName());
        } else if (smsType.equals(SmsTypeEnum.OLDPHONE.getId())) {//更换绑定手机号-旧手机号发送验证码
            redisJson = redisService.get(REDIS_CODE_BLUETOOTH_OLD_PHONE, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_BLUETOOTH_CHANGE_BIND_MOBILE.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_BLUETOOTH_CHANGE_BIND_MOBILE.getSignName());
        } else if (smsType.equals(SmsTypeEnum.NEWPHONE.getId())) {//更换绑定手机号-新手机号发送验证码
            redisJson = redisService.get(REDIS_CODE_BLUETOOTH_NEW_PHONE, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_BLUETOOTH_BIND_MOBILE.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_BLUETOOTH_BIND_MOBILE.getSignName());
        } else {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码类型不正确！");
        }
        // 校验90秒内重复获取
        if (StringUtil.isNotEmpty(redisJson)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "90秒内不可重复发送短信！");
        }
        // 生成并发送验证码
        String dynamicCode = StringUtil.getRandomIntByLength(6);
        LOGGER.info("蓝牙APP-发送短信验证码：" + dynamicCode + "|类型为:" + smsType);
        String templateParam = "{\"code\":\"value\"}";
        templateParam = templateParam.replace("value", dynamicCode);
        smsObject.put("templateParam", templateParam);//短信验证码
        //调用后台发送短信 暂时注释
        String result = callThirdCenter(AL_SMS_SEND, smsObject);//暂时注释掉为了调试
        //放缓存
        if (smsType.equals(SmsTypeEnum.REGISTER.getId())) {
            redisService.set(REDIS_CODE_BLUETOOTH_REGIST, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
        } else if (smsType.equals(SmsTypeEnum.RESETPASSWORD.getId())) {//重置密码发送短信
            redisService.set(REDIS_CODE_BLUETOOTH_PASSWORD, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
        } else if (smsType.equals(SmsTypeEnum.LOGIN.getId())) {//登录
            redisService.set(REDIS_CODE_BLUETOOTH_LOGIN, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
        } else if (smsType.equals(SmsTypeEnum.OLDPHONE.getId())) {//更换绑定手机号
            redisService.set(REDIS_CODE_BLUETOOTH_OLD_PHONE, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
        } else if (smsType.equals(SmsTypeEnum.NEWPHONE.getId())) {//更换绑定手机号
            redisService.set(REDIS_CODE_BLUETOOTH_NEW_PHONE, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "发送成功");
    }
}
