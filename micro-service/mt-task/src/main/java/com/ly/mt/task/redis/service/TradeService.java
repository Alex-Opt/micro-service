package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * trade_* 缓存更新处理
 *
 * @author taoye
 */
public interface TradeService {
    /**
     * TradeOrders缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshTradeOrders(RedisRefresh refresh) throws Exception;

    /**
     * TradeOrdersItems缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshTradeOrderItems(RedisRefresh refresh) throws Exception;

    /**
     * TradeOrderCoupon缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshTradeOrderCoupon(RedisRefresh refresh);
}