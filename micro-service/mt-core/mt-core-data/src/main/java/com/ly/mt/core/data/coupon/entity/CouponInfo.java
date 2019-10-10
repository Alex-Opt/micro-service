package com.ly.mt.core.data.coupon.entity;

public class CouponInfo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠开始时间
     */
    private String startTime;
    /**
     * 优惠结束时间
     */
    private String endTime;
    /**
     * 新人领取的优惠券有效天数
     */
    private String validityDay;
    /**
     * 优惠金额
     */
    private String denomination;
    /**
     * 优惠折扣，和优惠活动金额是互斥的
     */
    private String discountRate;
    /**
     * 优惠类型
     *
     * @see com.ly.mt.core.base.dict.CouponUseScope
     */
    private String limitType;
    /**
     * 最小订单金额
     */
    private String startFee;
    /**
     * 优惠券面向的系统
     *
     * @see com.ly.mt.core.base.dict.CouponUseSystem
     */
    private String couponUseSystem;
    /**
     * 优惠券类型
     */
    private String couponType;
    /**
     * 描述
     */
    private String remark;
    /**
     * 创建人
     */
    private String createrId;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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

    public String getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(String validityDay) {
        this.validityDay = validityDay;
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

    public String getCouponUseSystem() {
        return couponUseSystem;
    }

    public void setCouponUseSystem(String couponUseSystem) {
        this.couponUseSystem = couponUseSystem;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}