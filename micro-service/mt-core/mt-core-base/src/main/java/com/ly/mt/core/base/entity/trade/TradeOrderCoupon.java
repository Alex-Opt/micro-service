package com.ly.mt.core.base.entity.trade;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 订单使用优惠券,促销活动实体类
 * @author zhanglifeng
 * @String 2019-05-22
 *//** @deprecated */
public class TradeOrderCoupon extends BaseEntity {
    private String id;

    private String orderId;

    private String couponType;

    private String couponActivityId;

    private String discountPrice;

    private String discountRate;

    private String useTime;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public String getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(String couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}