package com.ly.mt.core.base.entity.coupon;

/** @deprecated */
public class CouponModel {
    /**
     * 优惠券id
     */
    private String couponId;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 优惠券-优惠金额
     */
    private String denomination;

    /**
     * 优惠券-优惠折扣，和优惠活动金额是互斥的
     */
    private String discountRate;

    /**
     * 优惠开始时间
     */
    private String startTime;

    /**
     * 优惠结束时间
     */
    private String endTime;

    /**
     * 优惠券使用状态  1：未使用，2：已使用
     */
    private String useStatus;

    /**
     *优惠类型 1：全品类，2：限定商品
     */
    private String limitType;

    /**
     *最小订单金额
     */
    private String startFee;

    private String userId;

    private String couponCode;

    private String InvalidTime;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getStartFee() {
        return startFee;
    }

    public void setStartFee(String startFee) {
        this.startFee = startFee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getInvalidTime() {
        return InvalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        InvalidTime = invalidTime;
    }
}
