package com.ly.mt.order.server.coupon.service;

import com.ly.mt.core.base.entity.coupon.CouponGoods;

import java.util.List;

/**
 * 优惠券-商品接口
 *
 * @author zhanglifeng
 * @date 2019-05-25
 */
public interface CouponGoodsService {

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    CouponGoods selectByPrimaryKey(Long id);

    /**
     * 根据限定商品的优惠券id集合，购买商品spuId集合查询coupon_goods表是否有满足条件的优惠券存在。
     *
     * @param spuIdList
     * @param couponId
     * @return
     */
    List<CouponGoods> selectByCouponIdAndSpuIds(List spuIdList, String couponId);
}
