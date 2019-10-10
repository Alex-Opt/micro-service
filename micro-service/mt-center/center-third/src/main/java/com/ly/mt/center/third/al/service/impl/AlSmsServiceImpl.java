package com.ly.mt.center.third.al.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ly.mt.center.third.al.entity.AlSmsRequest;
import com.ly.mt.center.third.al.entity.AlSmsResult;
import com.ly.mt.center.third.al.service.AlSmsService;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @Description 短信服务接口
 * @Author taoye
 */
@Service
public class AlSmsServiceImpl extends BaseServiceImpl implements AlSmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlSmsServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * @Description 发送短信
     * @Author taoye
     */
    @Override
    public ResponseJson sendSms(JSONObject jsonObject) {
        try {
            AlSmsRequest alSmsRequest = JSONObject.toJavaObject(jsonObject, AlSmsRequest.class);
            String phone = alSmsRequest.getPhone();
            if (StringUtil.isEmpty(phone)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—手机号码为空");
            }
            String[] arr = phone.split(",");
            for (int i = 0; i < arr.length; i++) {
                if (!StringUtil.isPhoneNumber(arr[i])) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "手机号格式错误");
                }
            }
            String templateCode = alSmsRequest.getTemplateCode();
            if (StringUtil.isEmpty(templateCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—短信模版为空");
            }
            String signName = alSmsRequest.getSignName();
            if (StringUtil.isEmpty(signName)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—短信签名为空");
            }
            String templateParam = alSmsRequest.getTemplateParam();
            if (StringUtil.isEmpty(templateParam)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—短信模版占位符替换参数为空");
            }
            DefaultProfile profile = DefaultProfile.getProfile("default", yml.getAlSmsAccessKeyId(), yml.getAlSmsAccessSecret());
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain(yml.getAlSmsDomain());
            request.setVersion(yml.getAlSmsVersion());
            request.setAction(yml.getAlSmsAction());
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", signName);
            request.putQueryParameter("TemplateCode", templateCode);
            request.putQueryParameter("TemplateParam", templateParam);
            LOGGER.info("发送短信服务:phone={},signName={},templateCode={},templateParam={}", phone, signName, templateCode, templateParam);
            CommonResponse response = client.getCommonResponse(request);
            String resultJson = response.getData();
            AlSmsResult result = JSONObject.parseObject(resultJson, AlSmsResult.class);
            String successCode = "OK";
            if (successCode.equals(result.getCode())) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
            }
            LOGGER.error("发送短信失败:code={},msg={}", result.getCode(), result.getMessage());
        } catch (Exception e) {
            LOGGER.error("发送短信出错:", e);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
    }
}