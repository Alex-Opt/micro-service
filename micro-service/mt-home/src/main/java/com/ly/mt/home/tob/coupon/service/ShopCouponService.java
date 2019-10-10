package com.ly.mt.home.tob.coupon.service;

import com.ly.mt.home.tob.coupon.vo.CouponInfoVo;

import java.util.List;

/**
 * 商家优惠券接口类
 *
 * @author: linan
 * @date: 2019/7/17
 */
public interface ShopCouponService {

    /**
     * 根据id查询优惠券
     *
     * @param id
     * @return
     */
    CouponInfoVo getCoupon(String id);


    /**
     * 根据couponId查询优惠券
     *
     * @param couponId
     * @param userId
     * @return
     */
    CouponInfoVo getCoupon(String couponId, String userId);

    /**
     * 查询优惠券列表
     *
     * @param status
     * @return
     */
    List<CouponInfoVo> shopCouponList(String status);

    /**
     * 更新优惠券状态
     *
     * @param id
     * @param status
     */
    void updateCouponStatus(String id, String status);

    /**
     * 发送活动优惠券
     *
     * @param userId
     */
    void sendActivityCoupon(String userId);
}
