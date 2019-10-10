package com.ly.mt.activity.advertisement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.service.DynamicCodeService;
import com.ly.mt.activity.base.config.YmlConfig;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.dict.AlSmsTemplate;
import com.ly.mt.core.redis.dict.DynamicCodeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_SMS_SEND;

@Service
public class DynamicCodeServiceImpl extends BaseServiceImpl implements DynamicCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicCodeServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * @Description 发送动态验证到手机
     * @Author taoye
     */
    @Override
    public ResponseJson sendDynamicCode(String mobile, String id) {
        try {
            DynamicCodeType dynamicCodeType = DynamicCodeType.getDynamicCodeTypeById(id);
            // 校验90秒内重复获取
            String redisCode = redisService.get(dynamicCodeType.getRedisKey(), mobile);
            if (StringUtil.isNotEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "90秒内不能重复获取!");
            }
            // 生成并发送验证码
            String dynamicCode = StringUtil.getRandomIntByLength(yml.getDynamicCodeLength());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", mobile);
            AlSmsTemplate alSmsTemplate = dynamicCodeType.getAlSmsTemplate();
            jsonObject.put("signName", alSmsTemplate.getSignName());
            jsonObject.put("templateCode", alSmsTemplate.getTemplateCode());
            String templateParam = "{\"code\":\"" + dynamicCode + "\"}";
            jsonObject.put("templateParam", templateParam);
            callThirdCenter(AL_SMS_SEND, jsonObject);
            // 放入redis
            redisService.set(dynamicCodeType.getRedisKey(), mobile,  dynamicCode, 90L, TimeUnit.SECONDS);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("发送动态验证码到手机出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}