package com.ly.mt.core.fn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.fn.entity.FnCarrierInfo;
import com.ly.mt.core.fn.entity.FnRequest;
import com.ly.mt.core.fn.entity.FnResponse;
import com.ly.mt.core.fn.service.FnService;
import com.ly.mt.core.fn.util.FnMd5Util;
import com.ly.mt.core.fn.util.FnRandomUtil;
import com.ly.mt.core.fn.util.FnUrlUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.core.rest.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.fn.config.FnConstantConfig.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_FN_STRING_TOKEN;

/**
 * 蜂鸟服务接口
 *
 * @author taoye
 */
@Service
public class FnServiceImpl implements FnService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FnServiceImpl.class);
    @Value("${spring.profiles.active}")
    private String profile;
    @Resource
    private RedisService redisService;
    @Resource
    private RestService restService;

    @Override
    public FnCarrierInfo carrierQuery(String partnerOrderCode) throws Exception {
        Assert.notNull(partnerOrderCode, "FnServiceImpl.carrierQuery partnerOrderCode must not be null");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("partner_order_code", partnerOrderCode);
        String requestJson = getFnRequest(jsonObject);
        String url = getRequestUtl(FN_API_CARRIER_QUERY);
        String resultJson = restService.restTemplatePost(url, requestJson);
        Assert.notNull(resultJson, "FnServiceImpl.carrierQuery resultJson must not be null");
        FnResponse fnResponse = JSONObject.parseObject(resultJson, FnResponse.class);
        if (!SUCCESS_CODE.equals(fnResponse.getCode())) {
            LOGGER.error("FnServiceImpl.carrierQuery code={} msg={}", fnResponse.getCode(), fnResponse.getMsg());
            throw new RuntimeException("FnServiceImpl.carrierQuery result.code must be 200");
        }
        String data = fnResponse.getData();
        if (StringUtils.isEmpty(data)) {
            throw new RuntimeException("FnServiceImpl.carrierQuery data is null");
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(data), FnCarrierInfo.class);
    }


    /**
     * 获取请求参数
     *
     * @author taoye
     */
    private String getFnRequest(JSONObject jsonObject) throws Exception {
        String json = jsonObject.toJSONString();
        int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
        String sign = getBusinessSign(json, salt);
        FnRequest FnRequest = new FnRequest();
        FnRequest.setApp_id(APP_ID);
        FnRequest.setSalt(String.valueOf(salt));
        FnRequest.setData(json);
        FnRequest.setSignature(sign);
        return JSONObject.toJSONString(FnRequest);
    }

    /**
     * 获取业务签名
     *
     * @author taoye
     */
    private String getBusinessSign(String json, int salt) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("app_id=").append(APP_ID)
                .append("&access_token=").append(getToken())
                .append("&data=").append(json)
                .append("&salt=").append(salt);
        LOGGER.info("FnServiceImpl.getBusinessSign in ", sb.toString());
        String sign = FnMd5Util.getMD5Code(sb.toString());
        LOGGER.info("FnServiceImpl.getBusinessSign out ", sign);
        return sign;
    }

    /**
     * 获取token签名
     *
     * @author taoye
     */
    private String getTokenSign(int salt) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("app_id=").append(APP_ID)
                .append("&salt=").append(salt)
                .append("&secret_key=").append(SECRET_KEY);
        LOGGER.info("FnServiceImpl.getTokenSign in ", sb.toString());
        String encodeString = FnUrlUtil.getInstance().urlEncode(sb.toString());
        String sign = FnMd5Util.getMD5Code(encodeString);
        LOGGER.info("FnServiceImpl.getTokenSign out ", sign);
        return sign;
    }

    /**
     * 获取token
     *
     * @author taoye
     */
    private String getToken() throws Exception {
        String token = redisService.get(REDIS_FN_STRING_TOKEN);
        if (null != token && !"".equals(token)) {
            return token;
        }
        int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
        String signature = getTokenSign(salt);
        Map<String, Object> map = new HashMap<>(3);
        map.put("app_id", APP_ID);
        map.put("salt", salt);
        map.put("signature", signature);
        String url = getRequestUtl(FN_API_GET_TOKEN);
        String resultJson = restService.restTemplateGet(url, map);
        Assert.notNull(resultJson, "FnServiceImpl.getToken resultJson must not be null");
        FnResponse fnResponse = JSONObject.parseObject(resultJson, FnResponse.class);
        if (!SUCCESS_CODE.equals(fnResponse.getCode())) {
            LOGGER.error("FnServiceImpl.getToken code={} msg={}", fnResponse.getCode(), fnResponse.getMsg());
            throw new RuntimeException("FnServiceImpl.getToken result.code must be 200");
        }
        String data = fnResponse.getData();
        Assert.notNull(data, "FnServiceImpl.getToken data must not be null");
        token = JSONObject.parseObject(data).getString("access_token");
        Assert.notNull(token, "FnServiceImpl.getToken token must not be null");
        redisService.set(REDIS_FN_STRING_TOKEN, token, 1L, TimeUnit.HOURS);
        return token;
    }

    /**
     * 获取请求地址
     *
     * @author taoye
     */
    private String getRequestUtl(String api) throws Exception {
        Assert.notNull(api, "FnServiceImpl.getRequestUtl api must not be null");
        if (DEV_PROFILE.equals(profile)) {
            LOGGER.info("FnServiceImpl.getRequestUtl profile={} url={} api={}", profile, DEV_URL, api);
            return DEV_URL + api;
        } else if (TEST_PROFILE.equals(profile)) {
            LOGGER.info("FnServiceImpl.getRequestUtl profile={} url={} api={}", profile, TEST_URL, api);
            return TEST_URL + api;
        } else if (GRAY_PROFILE.equals(profile)) {
            LOGGER.info("FnServiceImpl.getRequestUtl profile={} url={} api={}", profile, GRAY_URL, api);
            return GRAY_URL + api;
        } else if (PROD_PROFILE.equals(profile)) {
            LOGGER.info("FnServiceImpl.getRequestUtl profile={} url={} api={}", profile, PROD_URL, api);
            return PROD_URL + api;
        }
        throw new RuntimeException("FnServiceImpl.getRequestUtl profile must in [dev,test,gray,prod]");
    }
}