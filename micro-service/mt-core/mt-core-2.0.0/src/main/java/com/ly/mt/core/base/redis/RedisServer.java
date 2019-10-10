package com.ly.mt.core.base.redis;

import com.alibaba.fastjson.JSON;
import com.ly.mt.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @Description redis服务接口
 * @Author taoye
 */
@Component
public class RedisServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServer.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * @Description 删除redis
     * @Author taoye
     */
    public long del(RedisKey rk) {
        return del(rk.getKey());
    }

    public long del(RedisKey rk, String param) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("删除redis入参为空:{}", key);
            return 0L;
        }
        return del(key + param);
    }


    /**
     * @Description 查询redis
     * @Author taoye
     */
    public String get(RedisKey rk) {
        return get(rk.getKey());
    }

    public String get(RedisKey rk, String param) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("查询redis入参为空:key={}", key);
            return null;
        }
        return get(rk.getKey() + param);
    }


    /**
     * @Description 保存redis实体默认存活时间24小时
     * @Author taoye
     */
    public <E> void setExEntity(RedisKey rk, E entity) {
        setEx(rk.getKey(), 86400L, JSON.toJSONString(entity));
    }

    public <E> void setExEntity(RedisKey rk, String param, E entity) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:{}", key);
            return;
        }
        setEx(rk.getKey() + param, 86400L, JSON.toJSONString(entity));
    }

    /**
     * @Description 保存redis实体设置存活时间
     * @Author taoye
     */
    public <E> void setExEntity(RedisKey rk, long second, E entity) {
        setEx(rk.getKey(), second, JSON.toJSONString(entity));
    }

    public <E> void setExEntity(RedisKey rk, String param, long second, E entity) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:{}", key);
            return;
        }
        setEx(rk.getKey() + param, second, JSON.toJSONString(entity));
    }


    /**
     * @Description 保存redis设置存活时间
     * @Author taoye
     */
    public void setEx(RedisKey rk, long second, String value) {
        setEx(rk.getKey(), second, value);
    }

    public void setEx(RedisKey rk, String param, long second, String value) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:{}", key);
            return;
        }
        setEx(rk.getKey() + param, second, value);
    }


    /**
     * @Description 保存redis
     * @Author taoye
     */
    public void set(RedisKey rk, String value) {
        set(rk.getKey(), value);
    }

    public void set(RedisKey rk, String param, String value) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:{}", key);
            return;
        }
        set(rk.getKey() + param, value);
    }

    /**
     * @Description 保存redis实体
     * @Author taoye
     */
    public <E> void setEntity(RedisKey rk, E entity) {
        set(rk.getKey(), JSON.toJSONString(entity));
    }

    public <E> void setEntity(RedisKey rk, String param, E entity) {
        String key = rk.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:{}", key);
            return;
        }
        set(rk.getKey() + param, JSON.toJSONString(entity));
    }


    /**
     * @Description 私有保存方法，禁止改为public，设置有效时间
     * @Author taoye
     */
    private void setEx(String key, long second, String value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis保存key={},value={},second={}", key, value, second);
            connection.setEx(key.getBytes("UTF-8"), second, value.getBytes("UTF-8"));
        } catch (Exception e) {
            LOGGER.error("redis保存key={},value={},second={}出错:", key, value, second, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    /**
     * @Description 私有删除方法，禁止改为public
     * @Author taoye
     */
    private long del(String key) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis删除key={}", key);
            return connection.del(key.getBytes("UTF-8"));
        } catch (Exception e) {
            LOGGER.error("redis删除key={}出错:", key, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return 0L;
    }


    /**
     * @Description 私有查询方法，禁止改为public
     * @Author taoye
     */
    private String get(String key) {
        String result = null;
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            byte[] bytes = connection.get(key.getBytes("UTF-8"));
            if (null != bytes) {
                result = new String(bytes, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("redis查询key={}出错:", key, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        LOGGER.info("redis查询key={},value={}", key, result);
        return result;
    }


    /**
     * @Description 私有保存方法，禁止改为public
     * @Author taoye
     */
    private void set(String key, String value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis保存key={},value={}", key, value);
            connection.set(key.getBytes("UTF-8"), value.getBytes("UTF-8"));
        } catch (Exception e) {
            LOGGER.error("redis保存key={},value={}出错:", key, value, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * @Description 设置存活时间
     * @Author WHl
     */
    public void expire(RedisKey rk, String param, long timestamp) {
        expire(rk.getKey(), param, timestamp);
    }

    private void expire(String key, String param, long timestamp) {
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("redis设置存活时间入参为空:key={}", key);
            return;
        }
        key += param;
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis设置存活时间key={},timestamp={}", key, timestamp);
            boolean result = connection.expire(key.getBytes("UTF-8"), timestamp);
            if (!result) {
                LOGGER.error("redis设置存活时间key={},timestamp={}失败", key, timestamp);
            }
        } catch (Exception e) {
            LOGGER.error("redis设置存活时间key={},timestamp={}出错:", key, timestamp, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}