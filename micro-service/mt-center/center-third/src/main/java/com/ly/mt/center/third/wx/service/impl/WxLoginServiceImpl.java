package com.ly.mt.center.third.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.wx.service.WxLoginService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import jodd.datetime.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_NEED;
import static com.ly.mt.core.redis.RedisKey.REDIS_WX_APPLET_ACCESS_TOKEN;


/**
 * @Description 微信平台登陆接口
 * @Author zhanglifeng
 */
@Service
public class WxLoginServiceImpl extends BaseServiceImpl implements WxLoginService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxLoginServiceImpl.class);
    @Resource
    YmlConfig yml;

    @Override
    public ResponseJson appletAuthCode2Session(JSONObject jsonObject) {
        LOGGER.info("===================调用微信小程序登陆凭证校验接口==========================");
        String code = jsonObject.getString("code");
        if (StringUtil.isEmpty(code)) {
            LOGGER.info("微信授权接口获取code值为空！");
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "缺少入参code值！");
        }
        Map map = new ConcurrentHashMap(4);
        map.put("appid", yml.getWxDaoJiaCAppletPayAppId());
        map.put("secret", yml.getWxDaoJiaCAppletPayAppSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String returnJson = restTemplateGet(yml.getJsCode2SessionUri(), map);
        LOGGER.info("微信小程序登录凭证校验接口返回信息：" + returnJson);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, returnJson);
    }

    @Override
    public ResponseJson getWxAppletAccessToken(JSONObject jsonObject) {
        LOGGER.info("===================获取小程序全局唯一后台接口调用凭据ACCESS_TOKEN接口===========START===============");
        // 不为空，检查有效期，如果没有失效直接返回token
        String access_token = redisService.get(REDIS_WX_APPLET_ACCESS_TOKEN);
        if (StringUtil.isNotEmpty(access_token)) {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, access_token);
        }
        // 走到此处说明没有有效的token
        String url = yml.getAccessTokenUri() + "&appid=" + yml.getWxDaoJiaCAppletPayAppId() + "&secret=" + yml.getWxDaoJiaCAppletPayAppSecret();
        String json = restTemplateGet(url, null);
        LOGGER.info("-------------------获取小程序全局唯一后台接口调用凭据接口返回信息：" + json);
        JSONObject jsonObj = JSONObject.parseObject(json);
        String accessToken = jsonObj.getString("access_token");
        if (StringUtil.isEmpty(accessToken)) {
            Integer errcode = (Integer) jsonObj.get("errcode");
            String errmsg = (String) jsonObj.get("errmsg");
            throw new RuntimeException("access_token获取异常: errcode:" + errcode + ",errmsg:" + errmsg);
        }
        redisService.set(REDIS_WX_APPLET_ACCESS_TOKEN, accessToken, 10L, TimeUnit.MINUTES);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, accessToken);
    }

    @Override
    public ResponseJson auth(JSONObject jsonObject) {
        String code = jsonObject.getString("code");
        if (StringUtil.isEmpty(code)) {
            LOGGER.info("微信授权接口获取code值为空！");
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "缺少入参code值！");
        }

        Map map = new ConcurrentHashMap(4);
        map.put("appid", yml.getWxPayAppId());
        map.put("secret", yml.getWxPayAppSecret());
        map.put("code", code);
        map.put("grant_type", "authorization_code");

        String returnJson = restTemplateGet(yml.getWxPayConfirmAccessUri(), map);
        JSONObject wxJson = JSONObject.parseObject(returnJson);
        LOGGER.info("微新网页授权信息：{}" + returnJson);

        redisService.set(RedisKey.REDIS_WX_AUTH_TOKEN, wxJson.getString("access_token"), returnJson, 2, TimeUnit.HOURS);
        redisService.set(RedisKey.REDIS_WX_AUTH_REFRESH_TOKEN, wxJson.getString("refresh_token"), returnJson, 30, TimeUnit.DAYS);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, returnJson);
    }

    @Override
    public ResponseJson refreshToken(JSONObject jsonObject) {
        String refreshToken = jsonObject.getString("refresh_token");
        if (StringUtil.isEmpty(refreshToken)) {
            LOGGER.info("微信授权refreshToken接口获取refresh_token值为空！");
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "缺少入参refresh_token值！");
        }

        Map map = new ConcurrentHashMap(3);
        map.put("appid", yml.getWxPayAppId());
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        String returnJson = restTemplateGet(yml.getWxPayRefreshTokenUri(), map);
        JSONObject wxJson = JSONObject.parseObject(returnJson);
        LOGGER.info("微新refresh_token信息：{}" + returnJson);

        redisService.set(RedisKey.REDIS_WX_AUTH_TOKEN, wxJson.getString("access_token"), returnJson, 2, TimeUnit.HOURS);
        redisService.set(RedisKey.REDIS_WX_AUTH_REFRESH_TOKEN, wxJson.getString("refresh_token"), returnJson, 30, TimeUnit.DAYS);
        redisService.del(RedisKey.REDIS_WX_AUTH_REFRESH_TOKEN, refreshToken);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, returnJson);
    }
}