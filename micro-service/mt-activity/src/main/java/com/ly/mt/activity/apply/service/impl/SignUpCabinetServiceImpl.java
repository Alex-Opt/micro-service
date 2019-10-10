package com.ly.mt.activity.apply.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.apply.service.SignUpCabinetService;
import com.ly.mt.activity.apply.vo.SignUpCabinet;
import com.ly.mt.activity.base.config.YmlConfig;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_ACTIVITY_SIGN_UP_CABINET;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.ACTIVITY_SIGN_UP_CABINET_INSERT;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_SMS_SEND;
import static com.ly.mt.core.redis.RedisKey.REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET;
import static com.ly.mt.core.redis.dict.AlSmsTemplate.SMS_TEMPLATE_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET;

@Service
public class SignUpCabinetServiceImpl extends BaseServiceImpl implements SignUpCabinetService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SignUpCabinetServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * @Description 发送动态验证码
     * @Author taoye
     */
    @Override
    public ResponseJson sendDynamicCode(String mobile) {
        try {
            // 校验90秒内重复获取
            String redisCode = redisService.get(REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET, mobile);
            if (StringUtil.isNotEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "90秒内不能重复获取!");
            }
            // 生成并发送验证码
            String dynamicCode = StringUtil.getRandomIntByLength(yml.getDynamicCodeLength());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", mobile);
            jsonObject.put("signName", SMS_TEMPLATE_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET.getSignName());
            jsonObject.put("templateCode", SMS_TEMPLATE_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET.getTemplateCode());
            String templateParam = "{\"code\":\"" + dynamicCode + "\"}";
            jsonObject.put("templateParam", templateParam);
            callThirdCenter(AL_SMS_SEND, jsonObject);
            // 放入redis
            redisService.set(REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET, mobile, dynamicCode, 90L, TimeUnit.SECONDS);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("发送动态验证码出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 保存报名信息
     * @Author taoye
     */
    @Override
    public ResponseJson saveSignUpInfo(SignUpCabinet cabinet) {
        try {
            // 校验动态验证码
            String redisCode = redisService.get(REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET, cabinet.getMobile());
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
            }
            if (!cabinet.getDynamicCode().equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
            }
            // 保存报名信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_ACTIVITY_SIGN_UP_CABINET));
            jsonObject.put("user_name", cabinet.getUserName());
            jsonObject.put("mobile", cabinet.getMobile());
            jsonObject.put("province_name", cabinet.getProvinceName());
            jsonObject.put("city_name", cabinet.getCityName());
            jsonObject.put("area_name", cabinet.getAreaName());
            jsonObject.put("shop_name", cabinet.getShopName());
            jsonObject.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(ACTIVITY_SIGN_UP_CABINET_INSERT, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("格子柜入驻报名出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}