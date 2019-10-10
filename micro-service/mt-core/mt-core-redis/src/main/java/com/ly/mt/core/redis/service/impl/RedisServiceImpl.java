package com.ly.mt.core.redis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis操作接口
 *
 * @author taoye
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean del(RedisKey rk) {
        return del(rk.getKey());
    }

    @Override
    public boolean del(RedisKey rk, String param) {
        Assert.notNull(param, "RedisServiceImpl.del param must not be null");
        return del(rk.getKey() + param);
    }

    @Override
    public String get(RedisKey rk) {
        return get(rk.getKey());
    }

    @Override
    public String get(RedisKey rk, String param) {
        Assert.notNull(param, "RedisServiceImpl.get param must not be null");
        return get(rk.getKey() + param);
    }

    @Override
    public void set(RedisKey rk, String value) {
        set(rk.getKey(), value);
    }

    @Override
    public void set(RedisKey rk, String param, String value) {
        Assert.notNull(param, "RedisServiceImpl.set param must not be null");
        set(rk.getKey() + param, value);
    }

    @Override
    public void set(RedisKey rk, String value, long expireTime, TimeUnit timeUnit) {
        set(rk.getKey(), value, expireTime, timeUnit);
    }

    @Override
    public void set(RedisKey rk, String param, String value, long expireTime, TimeUnit timeUnit) {
        Assert.notNull(param, "RedisServiceImpl.set param must not be null");
        set(rk.getKey() + param, value, expireTime, timeUnit);
    }

    @Override
    public <E> void setEntity(RedisKey rk, E entity) {
        set(rk.getKey(), JSONObject.toJSONString(entity));
    }

    @Override
    public <E> void setEntity(RedisKey rk, String param, E entity) {
        Assert.notNull(param, "RedisServiceImpl.setEntity param must not be null");
        set(rk.getKey() + param, JSONObject.toJSONString(entity));
    }

    @Override
    public <E> void setEntity(RedisKey rk, E entity, long expireTime, TimeUnit timeUnit) {
        set(rk.getKey(), JSONObject.toJSONString(entity), expireTime, timeUnit);
    }

    @Override
    public <E> void setEntity(RedisKey rk, String param, E entity, long expireTime, TimeUnit timeUnit) {
        Assert.notNull(param, "RedisServiceImpl.setEntity param must not be null");
        set(rk.getKey() + param, JSONObject.toJSONString(entity), expireTime, timeUnit);
    }


    /**
     * 私有删除方法，禁止改为public
     *
     * @Author taoye
     */
    private boolean del(String key) {
        LOGGER.info("redis删除key={}", key);
        return stringRedisTemplate.delete(key);
    }


    /**
     * 私有查询方法，禁止改为public
     *
     * @Author taoye
     */
    private String get(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        LOGGER.info("redis查询key={},value={}", key, value);
        return value;
    }


    /**
     * 私有保存方法，禁止改为public
     *
     * @Author taoye
     */
    private void set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            LOGGER.info("redis保存key={},value={}", key, value);
        } catch (Exception e) {
            LOGGER.error("redis保存key={},value={}出错:", key, value, e);
        }
    }


    /**
     * 私有设置有效时间保存方法，禁止改为public
     *
     * @Author taoye
     */
    private void set(String key, String value, long expireTime, TimeUnit timeUnit) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
            LOGGER.info("redis保存key={},value={},expireTime={},timeUnit={}", key, value, expireTime, timeUnit);
        } catch (Exception e) {
            LOGGER.error("redis保存key={},value={},expireTime={},timeUnit={}出错:", key, value, expireTime, timeUnit, e);
        }
    }
}