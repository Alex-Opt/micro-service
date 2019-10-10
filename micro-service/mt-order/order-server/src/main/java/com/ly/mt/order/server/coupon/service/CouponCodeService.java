package com.ly.mt.order.server.coupon.service;

import com.ly.mt.core.base.entity.coupon.CouponModel;

import java.util.List;

/**
 * 优惠券码表
 *
 * @author zhanglifeng
 * @date 2019-05-24
 */
public interface CouponCodeService {

    /**
     * 根据用户id查询领取,尚未使用的优惠券集合
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> selectNotUsedCouponIdByUserId(Long userId);

    /**
     * 根据用户id查询领取,已经使用的优惠券集合
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> selectUsedCouponIdByUserId(Long userId);

    /**
     * 根据id更新优惠券的状态
     * @param userId
     * @param couponList
     * @return
     */
    void updateCouponCode(List<CouponModel> couponList, String userId);
}
