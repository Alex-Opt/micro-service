package com.ly.mt.cabinet.b.sms.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.ly.mt.cabinet.b.common.dict.SmsTypeEnum;
import com.ly.mt.cabinet.b.sms.service.SmsService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
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
        //登录
        if (smsType.equals(SmsTypeEnum.LOGIN.getId())) {
            redisJson = redisService.get(REDIS_CODE_CABINET_B_LOGIN, mobile);
            smsObject.put("templateCode", SMS_TEMPLATE_CODE_CABAINET_LOGIN.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_CODE_CABAINET_LOGIN.getSignName());
        } else {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码类型不正确！");
        }
        // 校验90秒内重复获取
        if (StringUtil.isNotEmpty(redisJson)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "60秒内不可重复发送短信！");
        }
        // 生成并发送验证码
        String dynamicCode = StringUtil.getRandomIntByLength(6);
        LOGGER.info("CABINET-B-APP-发送短信验证码：" + dynamicCode + "|类型为:" + smsType);
        String templateParam = "{\"code\":\"value\"}";
        templateParam = templateParam.replace("value", dynamicCode);
        smsObject.put("templateParam", templateParam);//短信验证码
        //调用后台发送短信 暂时注释
        String result = callThirdCenter(AL_SMS_SEND, smsObject);//暂时注释掉为了调试
        //放缓存
        if (smsType.equals(SmsTypeEnum.LOGIN.getId())) {//登录
            redisService.set(REDIS_CODE_CABINET_B_LOGIN, mobile, dynamicCode, 60, TimeUnit.SECONDS);
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "发送成功");
    }


    /**
     * 发送通知类短信
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson sendNotificationSms(String mobile, String type, String templteParms) throws Exception {
        //发送短信对象
        JSONObject smsObject = new JSONObject();
        smsObject.put("phone", mobile);
        //格子柜BD补货通知
        if (type.equals(SmsTypeEnum.NOTIFICATION_GZG_BD.getId())) {
            smsObject.put("templateCode", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_GZG_BD.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_GZG_BD.getSignName());
        } else if (type.equals(SmsTypeEnum.NOTIFICATION_ZG_BD.getId())) { //展柜BD补货通知
            smsObject.put("templateCode", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD.getTemplateCode());
            smsObject.put("signName", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD.getSignName());
        } else {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码类型不正确！");
        }
        LOGGER.info("CABINET-B-APP-发送短信通知：" + mobile + "|类型为:" + type);

        smsObject.put("templateParam", templteParms);//通知类短信
        //调用后台发送短信 暂时注释
        String result = callThirdCenter(AL_SMS_SEND, smsObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "发送成功");
    }
}
