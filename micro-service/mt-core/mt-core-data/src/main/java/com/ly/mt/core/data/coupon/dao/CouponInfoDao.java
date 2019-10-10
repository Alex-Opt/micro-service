package com.ly.mt.core.data.coupon.dao;

import com.ly.mt.core.data.coupon.entity.CouponInfo;

/**
 * CouponInfo操作接口
 *
 * @author taoye
 */
public interface CouponInfoDao {
    /**
     * 从redis根据id查询CouponInfo
     * redis不存在则查询mysql
     *
     * @param id 优惠券ID
     * @return CouponInfo
     * @author taoye
     */
    CouponInfo getCouponInfoByIdFromRedis(String id);
}