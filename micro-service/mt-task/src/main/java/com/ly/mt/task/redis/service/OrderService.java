package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * order_* 缓存更新处理
 *
 * @author taoye
 */
public interface OrderService {
    /**
     * OrdersBattleInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshOrdersBattleInfo(RedisRefresh refresh) throws Exception;
}