package com.ly.mt.cabinet.b.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate template;

    public String getCachByKey(String key) {
        return template.opsForValue().get(key);
    }

    public void putCacheByKey(String key, String value, long expire, TimeUnit timeUnit) {
        template.opsForValue().set(key,value,expire,timeUnit);
    }

    public void putCacheByKeyNoExpirt(String key, String value) {
        template.opsForValue().set(key,value);
    }

    public void putCacheByKeyTime(String key, String value, int time, TimeUnit unit) {
        template.opsForValue().set(key, value, time, unit);
    }

    public void deleteCache(String key) {
        template.delete(key);
    }

    public boolean setNx(String key,String value,long expireTime,TimeUnit timeUnit){
        return template.opsForValue().setIfAbsent(key,value,expireTime,timeUnit);
    }

    public Map<String,Object> getCacheBykeyAsMap(String key){
        String value = template.opsForValue().get("key");
        return JSON.parseObject(value,new TypeReference<Map<String,Object>>(){});
    }

    public boolean containsKey(String key){
        return template.hasKey(key);
    }

    public void putMap(String key,Map<String,String> map){
        template.opsForHash().putAll(key,map);
    }
}
