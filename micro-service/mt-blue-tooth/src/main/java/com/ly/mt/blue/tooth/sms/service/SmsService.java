package com.ly.mt.blue.tooth.sms.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface SmsService {
    /**
     * 发送短信
     * @throws Exception
     */
    ResponseJson sendSms(String mobile,String smsType) throws Exception;

}