package com.ly.mt.core.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;
import java.sql.Time;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key,String value,int expire,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,expire,TimeUnit.MINUTES);
    }
    public String get(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return (String) o;
    }

    public String getCachByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void putCacheByKey(String key, String value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,expire,timeUnit);
    }

    public void putCacheByKeyNoExpirt(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public void putCacheByKeyTime(String key, String value, int time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }

    public Map<String,Object> getCacheBykeyAsMap(String key){
        String value = (String) redisTemplate.opsForValue().get("key");
        return JSON.parseObject(value,new TypeReference<Map<String,Object>>(){});
    }

    public boolean containsKey(String key){
        return redisTemplate.hasKey(key);
    }

    public void putMap(String key,Map<String,Object> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    public boolean setNx(final String key, final Serializable value, final long expire){
        Boolean b = (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                RedisSerializer keySerializer = redisTemplate.getKeySerializer();
                Object o = connection.execute("set",
                        keySerializer.serialize(key),
                        valueSerializer.serialize(value),
                        SafeEncoder.encode("NX"),
                        SafeEncoder.encode("EX"),
                        Protocol.toByteArray(expire));
                return o != null;
            }
        });
        return  b;
    }
}
