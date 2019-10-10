package com.ly.mt.center.third.fn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.fn.dict.FnApi;
import com.ly.mt.center.third.fn.entity.FnResult;
import com.ly.mt.center.third.fn.service.FnTokenService;
import com.ly.mt.center.third.fn.util.FnMd5Util;
import com.ly.mt.center.third.fn.util.FnRandomUtil;
import com.ly.mt.center.third.fn.util.FnUrlUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.redis.RedisKey.REDIS_FN_STRING_TOKEN;

/**
 * @Description 蜂鸟凭证接口
 * @Author taoye
 */
@Service
public class FnTokenServiceImpl extends BaseServiceImpl implements FnTokenService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FnTokenServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * @Description 获取请求地址
     * @Author taoye
     */
    @Override
    public String getRequestUtl(FnApi fnApi) {
        return yml.getFnUrl() + fnApi.getApi();
    }


    /**
     * @Description 请求接口的签名
     * @Author taoye
     */
    @Override
    public String getBusinessSign(String json, int salt) {
        LOGGER.info("获取fn配置信息：appId-"+yml.getFnAppId()+",notifyUrl-"+yml.getFnNotifyUrl()+",fnSecretKey-"+yml.getFnSecretKey()+",-"+yml.getFnUrl());
        Map<String, Object> sigStr = new LinkedHashMap<>();
        // 应该如下面一样各个key值顺序一致
        sigStr.put("app_id", yml.getFnAppId());
        sigStr.put("access_token", getToken());
        sigStr.put("data", json);
        sigStr.put("salt", salt);
        StringBuilder seed = new StringBuilder();
        Set<String> set = sigStr.keySet();
        String sign = "";
        try {
            for (String key : set) {
                seed.append(key).append("=").append(sigStr.get(key)).append("&");
            }
            String queryString = StringUtils.stripEnd(seed.toString(), "&");
            LOGGER.info(String.format("---------------------MD5计算签名前的入参："+queryString));
            sign = FnMd5Util.getMD5Code(queryString);
            LOGGER.info("计算出来的签名sign:"+sign);
        } catch (Exception e) {
            LOGGER.error("请求接口的签名获取出错:", e);
        }
        return sign;
    }


    /**
     * @Description 获取token
     * @Author taoye
     */
    private String getToken() {
        String token = redisService.get(REDIS_FN_STRING_TOKEN);
        if (StringUtil.isNotEmpty(token)) {
            return token;
        }
        return refreshToken();
    }


    /**
     * @Description 刷新token
     * @Author taoye
     */
    private String refreshToken() {
        // 签名获取
        int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
        String signature = getTokenSign(salt);
        // 接口调用
        Map<String, Object> map = new HashMap<>(3);
        map.put("app_id", yml.getFnAppId());
        map.put("salt", salt);
        map.put("signature", signature);
        String url = getRequestUtl(FnApi.FN_API_GET_TOKEN);
        LOGGER.info("======================定位蜂鸟获取token地址=======================url："+url);
        // 返回解析
        FnResult result = JSONObject.parseObject(restTemplateGet(url, map), FnResult.class);
        String successCode = "200";
        if (null == result || !successCode.equals(result.getCode())) {
            throw new RuntimeException("TOKEN获取出错");
        }
        String resultJson = result.getData();
        if (StringUtil.isEmpty(resultJson)) {
            throw new RuntimeException("TOKEN获取出错");
        }
        JSONObject jsonObject = JSONObject.parseObject(result.getData());
        String token = jsonObject.getString("access_token");
        if (StringUtil.isEmpty(token)) {
            throw new RuntimeException("TOKEN获取出错");
        }
        redisService.set(REDIS_FN_STRING_TOKEN, token, 1L, TimeUnit.HOURS);
        return token;
    }


    /**
     * @Description 请求token的签名
     * @Author taoye
     */
    private String getTokenSign(int salt) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_id=").append(yml.getFnAppId())
                .append("&salt=").append(salt)
                .append("&secret_key=").append(yml.getFnSecretKey());
        String sign = "";
        try {
            String encodeString = FnUrlUtil.getInstance().urlEncode(sb.toString());
            sign = FnMd5Util.getMD5Code(encodeString);
        } catch (Exception e) {
            LOGGER.error("请求token的签名获取出错:", e);
        }
        return sign;
    }
}