package com.ly.mt.core.data.trade.dao;

import com.ly.mt.core.base.dict.CouponType;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;

import java.util.List;

/**
 * TradeOrderCoupon操作接口
 *
 * @author taoye
 */
public interface TradeOrderCouponDao {
    /**
     * 从redis根据orderId、couponType查询List<TradeOrderCoupon>
     * redis不存在则查询mysql
     *
     * @param orderId    订单ID
     * @param couponType 优惠类型
     * @return List<TradeOrderCoupon>
     * @author taoye
     */
    List<TradeOrderCoupon> listTradeOrderCouponByOrderIdAndCouponTypeFromRedis(String orderId, String couponType);
}