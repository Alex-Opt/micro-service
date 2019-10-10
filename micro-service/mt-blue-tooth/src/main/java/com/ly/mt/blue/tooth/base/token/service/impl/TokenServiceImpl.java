package com.ly.mt.blue.tooth.base.token.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.base.token.service.TokenService;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_TOKEN_BLUETOOTH_LOGIN;

/**
* @program: mt-blue-tooth
* @description: 操作tokenService
* @author: wanghongliang
* @create: 2019/7/24 17:39
**/
@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenService {

    private final static Logger Logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    /**
     * 获取token
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public String getToken(String userId) throws Exception {
        String cachedToken = redisService.get(REDIS_TOKEN_BLUETOOTH_LOGIN,userId);
        String token = "";
        if (StringUtils.isEmpty(cachedToken)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", userId);
            token = callDataCenter(BLUETOOTH_TOKEN_GET, jsonObject);
        } else {
            token = cachedToken;
        }
        return token;
    }

    /**
     * 数据库中token放入缓存
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public String copyTokenToCache(String userId) throws Exception {
        String cachedToken = redisService.get(REDIS_TOKEN_BLUETOOTH_LOGIN,userId);
        if (StringUtils.isEmpty(cachedToken)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", userId);
            cachedToken = callDataCenter(BLUETOOTH_TOKEN_GET, jsonObject);
            redisService.set(REDIS_TOKEN_BLUETOOTH_LOGIN,userId,cachedToken);
        }
        return cachedToken;
    }

    /**
     * 产生新的token并更新
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public String createToken(String userId,String password) throws Exception {
        String token = MD5Util.md5(DateUtil.getNowTimeStr() + password).toLowerCase();
        redisService.set(REDIS_TOKEN_BLUETOOTH_LOGIN,userId,token);//放入新的token进入缓存
        updateToken(userId,token);//更新用户token
        return token;
    }

    @Override
    public Integer insert(String userId, String token, Long expire) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        jsonObject.put("token", token);
        jsonObject.put("expire", expire);
        String result = callDataCenter(BLUETOOTH_TOKEN_INSERT, jsonObject);
        Integer count = Integer.parseInt(result);
        if (count > 0) {
            redisService.set(REDIS_TOKEN_BLUETOOTH_LOGIN,userId,token);
        }
        return count;
    }

    @Override
    public Integer updateToken(String userId, String token) throws Exception {
        Logger.info("蓝牙APP更新用户token开始:"+userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        jsonObject.put("token", token);
        String result = callDataCenter(BLUETOOTH_TOKEN_UPDATE, jsonObject);
        Integer count = Integer.parseInt(result);
        if (count > 0) {
            redisService.set(REDIS_TOKEN_BLUETOOTH_LOGIN,userId,token);
        }
        Logger.info("蓝牙APP更新用户token结束:"+userId);
        return count;
    }

    /**
     * 删除token
     * @throws Exception
     */
    @Override
    public void delToken(String userId) throws Exception{
        Logger.info("蓝牙APP-用户{}登出开始",userId);
        redisService.del(REDIS_TOKEN_BLUETOOTH_LOGIN,userId);
        Logger.info("蓝牙APP-用户{}登出结束",userId);
    }
}
