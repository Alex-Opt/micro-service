package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * gzg_* 缓存更新处理
 *
 * @author taoye
 */
public interface GzgService {
    /**
     * GzgHotel缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGzgHotel(RedisRefresh refresh) throws Exception;

    /**
     * GzgInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGzgInfo(RedisRefresh refresh) throws Exception;

    /**
     * GzgOrder缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGzgOrder(RedisRefresh refresh) throws Exception;

    /**
     * GzgOrderItem缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGzgOrderItem(RedisRefresh refresh) throws Exception;
}