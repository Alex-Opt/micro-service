package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * shop_* 缓存更新处理
 *
 * @author taoye
 */
public interface ShopService {
    /**
     * ShopPurchases缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshShopPurchases(RedisRefresh refresh) throws Exception;

    /**
     * ShopPurchasesItems缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshShopPurchasesItems(RedisRefresh refresh) throws Exception;

    /**
     * ShopInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshShopInfo(RedisRefresh refresh) throws Exception;
}