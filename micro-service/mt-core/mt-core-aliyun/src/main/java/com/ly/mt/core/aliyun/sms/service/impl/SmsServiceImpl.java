package com.ly.mt.core.aliyun.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ly.mt.core.aliyun.sms.dict.SmsTemplate;
import com.ly.mt.core.aliyun.sms.entity.SmsResult;
import com.ly.mt.core.aliyun.sms.service.SmsService;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.ly.mt.core.aliyun.base.config.AliYunConstant.*;


/**
 * 短信服务接口
 *
 * @author taoye
 */
@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Override
    public void sendSms(SmsTemplate smsTemplate, String templateParam, String phones) throws Exception {
        Assert.notNull(smsTemplate, "SmsServiceImpl.sendSms smsTemplate must not be null");
        String code = smsTemplate.getCode();
        Assert.notNull(code, "SmsServiceImpl.sendSms smsTemplate.code must not be null");
        String sign = smsTemplate.getSign();
        Assert.notNull(sign, "SmsServiceImpl.sendSms smsTemplate.sign must not be null");
        Assert.notNull(templateParam, "SmsServiceImpl.sendSms templateParam must not be null");
        Assert.notNull(phones, "SmsServiceImpl.sendSms phones must not be null");
        String[] arr = phones.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (!StringUtil.isPhoneNumber(arr[i])) {
                throw new RuntimeException("SmsServiceImpl.sendSms phones error " + arr[i]);
            }
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", SMS_ACCESS_KEY_ID, SMS_ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SMS_DOMAIN);
        request.setVersion(SMS_VERSION);
        request.setAction(SMS_ACTION);
        request.putQueryParameter("PhoneNumbers", phones);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", code);
        request.putQueryParameter("TemplateParam", templateParam);
        LOGGER.info("SmsServiceImpl.sendSms phones={} sign={} code={} param={}", phones, sign, code, templateParam);
        CommonResponse response = client.getCommonResponse(request);
        String resultJson = response.getData();
        SmsResult result = JSONObject.parseObject(resultJson, SmsResult.class);
        String successCode = "OK";
        if (successCode.equals(result.getCode())) {
            LOGGER.info("SmsServiceImpl.sendSms success");
        }else{
            LOGGER.info("SmsServiceImpl.sendSms error code={} msg={}", result.getCode(), result.getMessage());
        }
    }
}