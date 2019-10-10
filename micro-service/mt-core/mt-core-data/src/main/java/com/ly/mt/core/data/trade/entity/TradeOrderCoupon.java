package com.ly.mt.core.data.trade.entity;

/**
 * trade_order_coupon
 *
 * @author taoye
 */
public class TradeOrderCoupon {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 优惠活动类型：1-参加优惠活动(activity_info表)，2-使用优惠券(coupon_info表)
     */
    private String couponType;
    /**
     * 优惠券/活动ID
     */
    private String couponActivityId;
    /**
     * 优惠金额
     */
    private String discountPrice;
    /**
     * 优惠折扣
     */
    private String discountRate;
    /**
     * 创建时间
     */
    private String useTime;


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
        this.couponType = couponType;
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