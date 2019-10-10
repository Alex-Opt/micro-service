package com.ly.mt.core.aliyun.sms.service;

import com.ly.mt.core.aliyun.sms.dict.SmsTemplate;

/**
 * 短信服务接口
 *
 * @author taoye
 */
public interface SmsService {
    /**
     * 发送短信
     *
     * @param smsTemplate   短信模版枚举
     * @param templateParam 短信模版占位符替换值
     * @param phones        手机号,多个用逗号分隔
     * @author taoye
     */
    void sendSms(SmsTemplate smsTemplate, String templateParam, String phones) throws Exception;
}