package com.ly.mt.core.base.entity.coupon;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class CouponInfo extends BaseEntity {
    private String id;
    /**
     * 新人领取的优惠券有效天数
     */
    private String validityDay;
    /**
     * 优惠券面向的系统 10:到家C,20:格子柜,30:到家B
     */
    private String couponUseSystem;
    /**
     * 优惠券类型 10:新人券-系统发放,20:新人券-自领,30:普通券
     */
    private String couponType;

    private String denomination;

    private String discountRate;

    private String limitType;

    private String startFee;

    private String createTime;

    private String couponName;//优惠券名称

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String remark;//描述

    private String createrId;//创建人

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

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public String getStartFee() {
        return startFee;
    }

    public void setStartFee(String startFee) {
        this.startFee = startFee;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(String validityDay) {
        this.validityDay = validityDay;
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
}
