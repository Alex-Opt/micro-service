package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * goods_* 缓存更新处理
 *
 * @author taoye
 */
public interface GoodsService {
    /**
     * GoodsSkuPicture缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGoodsSkuPicture(RedisRefresh refresh) throws Exception;

    /**
     * GoodsSkuCode缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGoodsSkuCode(RedisRefresh refresh) throws Exception;

    /**
     * GoodsSkuInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGoodsSkuInfo(RedisRefresh refresh) throws Exception;

    /**
     * GoodsSpuInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshGoodsSpuInfo(RedisRefresh refresh) throws Exception;
}