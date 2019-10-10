package com.ly.mt.cabinet.b.sms.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface SmsService {
    /**
     * 发送短信验证码
     * @throws Exception
     */
    ResponseJson sendSms(String mobile, String smsType) throws Exception;

    /**
     * 发送通知类短信
     * @throws Exception
     */
    ResponseJson sendNotificationSms(String mobile, String type, String templteParms) throws Exception;
}