package com.ly.mt.core.common.server;

import com.alibaba.fastjson.JSON;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

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
    public long del(RedisEnum re, String param) {
        String key = re.getKey();
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("删除redis入参为空:{}", key);
            return 0L;
        }
        key += param;
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
     * @Description 保存redis
     * @Author taoye
     */
    public <E> void setEntity(RedisEnum re, String param, E entity) {
        String json = JSON.toJSONString(entity);
        set(re, param, json);
    }

    public void set(RedisEnum re, String param, String value) {
        set(re.getKey(), param, value);
    }

    public void set(SmsTemplateEnum ste, String param, String value) {
        set(ste.getRedisKey(), param, value);
    }

    private void set(String key, String param, String value) {
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("保存redis入参为空:key={},value={}", key, value);
            return;
        }
        key += param;
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
     * @Description 保存redis并且设置存活时长
     * @Author taoye
     */
    public void saveWithExpire(RedisEnum re, String value, long timestamp) {
        String key = re.getKey();
        if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value)) {
            LOGGER.error("redis保存参数错误:key={},value={}", key, value);
            return;
        }
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis保存key={},value={}", key, value);
            connection.set(re.getKey().getBytes("UTF-8"), value.getBytes("UTF-8"));
            boolean result = connection.expire(key.getBytes("UTF-8"), timestamp);
            if (!result) {
                LOGGER.error("redis设置存活时间key={},value={},timestamp={}失败", key, value, timestamp);
            }
        } catch (Exception e) {
            LOGGER.error("redis保存key={},value={},timestamp={}出错:", key, value, timestamp, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    /**
     * @Description 查询redis
     * @Author taoye
     */
    public String get(RedisEnum re) {
        return get(re.getKey());
    }

    public String get(RedisEnum re, String param) {
        return get(re.getKey(), param);
    }

    public String get(SmsTemplateEnum ste, String param) {
        return get(ste.getRedisKey(), param);
    }

    private String get(String key, String param) {
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("查询redis入参为空:key={}", key);
            return null;
        }
        key += param;
        return get(key);
    }

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
     * @Description 设置存活时间
     * @Author taoye
     */
    public void expire(RedisEnum re, String param, long timestamp) {
        expire(re.getKey(), param, timestamp);
    }

    public void expire(SmsTemplateEnum ste, String param, long timestamp) {
        expire(ste.getRedisKey(), param, timestamp);
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


    /**
     * @Description 设置过期时间
     * @Author taoye
     */
    public void expireAt(RedisEnum re, String param, long timestamp) {
        expireAt(re.getKey(), param, timestamp);
    }
    public boolean hasKey(RedisEnum re,String key){
        RedisConnection connection = redisConnectionFactory.getConnection();
        String cacheKey = re.getKey()+key;
        Boolean exist = false;
        try {
            exist = connection.exists(cacheKey.getBytes());
        }catch (Exception e){
            LOGGER.error("redis是否包含某个键出错,key {} ,错误信息:{}",cacheKey,e.getMessage());
        }
        return exist;
    }

    private void expireAt(String key, String param, long timestamp) {
        if (StringUtil.isEmpty(param)) {
            LOGGER.error("redis设置过期时间入参为空:key={}", key);
            return;
        }
        key += param;
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis设置过期时间key={},timestamp={}", key, timestamp);
            boolean result = connection.expireAt(key.getBytes("UTF-8"), timestamp);
            if (!result) {
                LOGGER.error("redis设置过期时间key={},timestamp={}失败", key, timestamp);
            }
        } catch (Exception e) {
            LOGGER.error("redis设置过期时间key={},timestamp={}出错:", key, timestamp, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 保存key并设置过期时间,当过期时间为0时候则不设置过期时间
     * @auth panjingtian
     * key = sts + param
     * @param sts {@link SmsTemplateEnum} key上半部分
     * @param param key下半部分绑定参数 ，例如手机号
     * @param value value参数
     * @param seconds 过期时间 单位/秒 ,0时候为不设置过期时间
     */
    public void setEx(SmsTemplateEnum sts,String param,String value ,Long seconds){
        if (seconds<0){
            throw new IllegalArgumentException("参数非法");
        }
        if (seconds == 0){
            set(sts.getRedisKey()+param,value);
        }else {
            setEx(sts.getRedisKey()+param,value,seconds);
        }
    }

    public void setExString(String key,String value,Long seconds){
        if (seconds<0){
            throw new IllegalArgumentException("参数非法");
        }
        if (seconds == 0){
            set(key,value);
        }else {
            setEx(key,value,seconds);
        }

    }

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
     * 公开获取key的方法
     * @auth panjingtian
     * @param key
     * @return
     */
    public String getVauel(String key){
        return get(key);
    }
    /**
     * 设置待过期时间的key
     * @auth panjingtian
     * @param key
     * @param value
     * @param seconds
     */
    private void setEx(String key,String value,Long seconds){
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            LOGGER.info("redis保存key={},value={},expiration={}", key, value,seconds);
            connection.setEx(key.getBytes("UTF-8"),seconds,value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("redis保存key={},value={},expiration={},e={}", key, value, seconds,e);
        }finally {
            if (connection != null){
                connection.close();
            }
        }

    }


    /**
     * 多线程安全操作用一个key对应的数值
     * @param key
     * @param value
     */
    private void incrSync(String key,Long value){
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.incrBy(key.getBytes("UTF-8"),value);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("redis保存key={},value={},e={}", key, value,e);
        }finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    /**
     * @param rem
     * @param param
     * @param value 可转为常用数值类型（int,long,double）的字符串
     */
    public void changeValueSync(RedisEnum rem,String param,String value){
        String key = rem.getKey();
        if(StringUtil.isEmpty(key)){
            LOGGER.error("redis设置数值的入参为空:key={}", key);
            return;
        }
        key += param;
        try {
            long val = Long.parseLong(value);
            incrSync(key,val);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("入参的value转long类型异常:value="+value);
        }
    }



}