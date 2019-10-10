package com.ly.mt.task.redis.service;

import com.ly.mt.core.data.redis.entity.RedisRefresh;

/**
 * coupon_* 缓存更新处理
 *
 * @author taoye
 */
public interface CouponService {
    /**
     * CouponInfo缓存更新处理
     *
     * @param refresh 参数
     * @throws Exception 异常
     * @author taoye
     */
    void refreshCouponInfo(RedisRefresh refresh) throws Exception;
}