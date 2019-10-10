package com.ly.mt.core.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.entity.sms.SmsResult;
import com.ly.mt.core.oss.OssServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_NOTICE_ALARM;

/**
 * @Description 短信服务接口
 * @Author taoye
 */
@Component
@RefreshScope
public class SmsServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssServer.class);
    @Value("${sms.domain}")
    private String domain;
    @Value("${sms.version}")
    private String version;
    @Value("${sms.action}")
    private String action;
    @Value("${sms.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.accessSecret}")
    private String accessSecret;
    @Value("${sms.alarmPhone}")
    private String alarmPhone;

    /**
     * @Description 短信服务
     * @Author taoye
     */
    public void sendDynamicCodeSms(String phone, String dynamicCode, SmsTemplateEnum smsTemplateEnum) {
        String templateParam = "{\"code\":\"" + dynamicCode + "\"}";
        sendSms(phone, smsTemplateEnum, templateParam);
    }

    /**
     * @Description 发送报警信息到管理员
     * @Author taoye
     */
    public void sendAlarmSms(String msg) {
        String templateParem = "{\"msg\":\"" + msg + "\"}";
        sendSms(alarmPhone, SMS_TEMPLATE_NOTICE_ALARM, templateParem);
    }

    /**
     * @Description 发送短信
     * @Param phone 手机号，多个用英文逗号隔开
     * @Param templateCode 短信模板编码
     * @Param templateParam 模板占位符参数，{\"code\":\"1111\"}
     * @Author taoye
     */
    private void sendSms(String phone, SmsTemplateEnum smsTemplateEnum, String templateParam) {
        String signName = smsTemplateEnum.getSignName();
        String templateCode = smsTemplateEnum.getTemplateCode();
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        LOGGER.info(
                "发送短信服务:phone={},signName={},templateCode={},templateParam={}",
                phone, signName, templateCode, templateParam
        );
        try {
            CommonResponse response = client.getCommonResponse(request);
            String resultJson = response.getData();
            SmsResult result = JSONObject.parseObject(resultJson, SmsResult.class);
            if ("OK".equals(result.getCode())) {
                return;
            }
            LOGGER.error("发送短信出错:code={},msg={}", result.getCode(), result.getMessage());
        } catch (Exception e) {
            LOGGER.error("发送短信出错:", e);
        }
        throw new RuntimeException("发送短信异常");
    }
}