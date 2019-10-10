package com.ly.mt.core.redis.service;

import com.ly.mt.core.redis.RedisKey;

import java.util.concurrent.TimeUnit;

/**
 * redis操作接口
 *
 * @author taoye
 */
public interface RedisService {
    /**
     * 删除redis
     *
     * @param rk 缓存key枚举
     * @return 删除成功返回true，删除失败返回false
     * @author taoye
     */
    boolean del(RedisKey rk);

    /**
     * 删除redis
     *
     * @param rk    缓存key枚举
     * @param param 动态拼接缓存key的参数
     * @return 删除成功返回true，删除失败返回false
     * @author taoye
     */
    boolean del(RedisKey rk, String param);

    /**
     * 查询redis
     *
     * @param rk 缓存key枚举
     * @return 返回缓存数据
     * @author taoye
     */
    String get(RedisKey rk);

    /**
     * 查询redis
     *
     * @param rk    缓存key枚举
     * @param param 动态拼接缓存key的参数
     * @return 返回缓存数据
     * @author taoye
     */
    String get(RedisKey rk, String param);

    /**
     * 保存redis，因为该方法没有设置缓存过期时间，所以谨慎使用，如非必要请勿使用
     *
     * @param rk    缓存key枚举
     * @param value 要缓存的数据
     * @Author taoye
     */
    void set(RedisKey rk, String value);

    /**
     * 保存redis，因为该方法没有设置缓存过期时间，所以谨慎使用，如非必要请勿使用
     *
     * @param rk    缓存key枚举
     * @param param 动态拼接缓存key的参数
     * @param value 要缓存的数据
     * @author taoye
     */
    void set(RedisKey rk, String param, String value);

    /**
     * 保存redis
     *
     * @param rk         缓存key枚举
     * @param value      要缓存的数据
     * @param expireTime 缓存存活的时间
     * @param timeUnit   时间单位
     * @author taoye
     */
    void set(RedisKey rk, String value, long expireTime, TimeUnit timeUnit);

    /**
     * 保存redis
     *
     * @param rk         缓存key枚举
     * @param param      动态拼接缓存key的参数
     * @param value      要缓存的数据
     * @param expireTime 缓存存活的时间
     * @param timeUnit   时间单位
     * @author taoye
     */
    void set(RedisKey rk, String param, String value, long expireTime, TimeUnit timeUnit);

    /**
     * 保存redis，因为该方法没有设置缓存过期时间，所以谨慎使用，如非必要请勿使用
     *
     * @param rk     缓存key枚举
     * @param entity 要缓存的数据
     * @author taoye
     */
    <E> void setEntity(RedisKey rk, E entity);

    /**
     * 保存redis，因为该方法没有设置缓存过期时间，所以谨慎使用，如非必要请勿使用
     *
     * @param rk     缓存key枚举
     * @param param  动态拼接缓存key的参数
     * @param entity 要缓存的数据
     * @author taoye
     */
    <E> void setEntity(RedisKey rk, String param, E entity);

    /**
     * 保存redis
     *
     * @param rk         缓存key枚举
     * @param entity     要缓存的数据
     * @param expireTime 缓存存活的时间
     * @param timeUnit   时间单位
     * @author taoye
     */
    <E> void setEntity(RedisKey rk, E entity, long expireTime, TimeUnit timeUnit);

    /**
     * 保存redis
     *
     * @param rk         缓存key枚举
     * @param param      动态拼接缓存key的参数
     * @param entity     要缓存的数据
     * @param expireTime 缓存存活的时间
     * @param timeUnit   时间单位
     * @author taoye
     */
    <E> void setEntity(RedisKey rk, String param, E entity, long expireTime, TimeUnit timeUnit);
}