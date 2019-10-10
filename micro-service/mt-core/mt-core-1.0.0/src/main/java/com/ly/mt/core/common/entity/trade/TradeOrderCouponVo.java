package com.ly.mt.core.common.entity.trade;

/**
 * 订单使用优惠券信息 返回前端用Vo
 * @author zhanglifeng
 * @String 2019-05-22
 */
public class TradeOrderCouponVo {
    private String id;

    private String orderId;

    private String couponType;

    private String couponActivityId;

    private String discountPrice;

    private String discountRate;

    private String useTime;
    /**
     *优惠活动类型名称，如满减，秒杀，优惠券等
     */
    private String couponName;


    public String getId() {
        return id;
    }
    
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

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}