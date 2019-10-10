package com.ly.mt.home.tob.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.dict.AlSmsTemplate;
import com.ly.mt.core.redis.dict.DynamicCodeType;
import com.ly.mt.home.base.config.YmlConfig;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.code.service.DynamicCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_SMS_SEND;

/**
 * 短信验证码实现类
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Service
public class DynamicCodeServiceImpl extends BaseServiceImpl implements DynamicCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicCodeServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     *
     * @param mobile 手机号
     * @param id 验证码类型id
     * @return ResponseJson
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
            redisService.set(dynamicCodeType.getRedisKey(), mobile, dynamicCode, 90L, TimeUnit.SECONDS);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("发送动态验证码到手机出错:", e);
            throw new MTException(RESPONSE_CODE_ERROR);
        }
    }
}