package com.ly.mt.cabinet.b.common.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.common.dict.RedisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServer {
    @Autowired
    private StringRedisTemplate template;

    public String get(RedisEnum redisEnum,String key) {
        return template.opsForValue().get(redisEnum.getKey()+key);
    }

    public void set(RedisEnum redisEnum,String key, String value, long expire, TimeUnit timeUnit) {
        template.opsForValue().set(redisEnum+key,value,expire,timeUnit);
    }

    public void setNoExpirt(RedisEnum redisEnum,String key, String value) {
        template.opsForValue().set(redisEnum+key,value);
    }

    public void putCacheByKeyTime(RedisEnum redisEnum,String key, String value, int time, TimeUnit unit) {
        template.opsForValue().set(redisEnum.getKey()+key, value, time, unit);
    }

    public void del(RedisEnum redisEnum,String key) {
        template.delete(redisEnum.getKey()+key);
    }

    public boolean setNx(RedisEnum redisEnum,String key,String value,long expireTime,TimeUnit timeUnit){
        return template.opsForValue().setIfAbsent(redisEnum.getKey()+key,value,expireTime,timeUnit);
    }

    public Map<String,Object> getAsMap(RedisEnum redisEnum,String key){
        String value = template.opsForValue().get(redisEnum.getKey()+key);
        return JSON.parseObject(value,new TypeReference<Map<String,Object>>(){});
    }

    public boolean containsKey(RedisEnum redisEnum,String key){
        return template.hasKey(redisEnum.getKey()+key);
    }

    public void putMap(RedisEnum redisEnum,String key,Map<String,String> map){
        template.opsForHash().putAll(redisEnum.getKey()+key,map);
    }

    public boolean tryLock(){
        return true;
    }

    /**
     * 获取过期值
     * @param redisEnum
     * @param key
     * @return
     */
    public long keyTTL(RedisEnum redisEnum,String key){
        return template.opsForValue().getOperations().getExpire(redisEnum.getKey()+":"+key);
    }

}
