package com.ly.mt.core.common.entity.coupon;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 优惠券码表
 */
public class CouponCode extends BaseEntity {

    private String id;

    private String couponId;//优惠券id

    private String couponCode;//优惠码

    private String userId;//用户Id

    private String pullStatus;//领取状态 1：未领取，2：已领取

    private String pullTime;//领取时间

    private String useStatus;//使用状态  1：未使用，2：已使用

    private String useTime;//使用时间

    private CouponInfo couponInfo;//优惠卷详情信息

    public CouponInfo getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPullStatus() {
        return pullStatus;
    }

    public void setPullStatus(String pullStatus) {
        this.pullStatus = pullStatus;
    }

    public String getPullTime() {
        return pullTime;
    }

    public void setPullTime(String pullTime) {
        this.pullTime = pullTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}
