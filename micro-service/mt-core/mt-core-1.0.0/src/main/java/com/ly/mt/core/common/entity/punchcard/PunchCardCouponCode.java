package com.ly.mt.core.common.entity.punchcard;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 优惠券码表
 * @author  ypmu
 * @date 20190529
 */
public class PunchCardCouponCode  extends BaseEntity {

    /**
     * 优惠券id
     */
    private String couponId;

    /**
     * 优惠码
     */
    private String couponCode;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 领取状态 1：未领取，2：已领取
     */
    private String pullStatus;

    /**
     * 领取时间
     */
    private String pullTime;

    /**
     * 使用状态  1：未使用，2：已使用
     */
    private String useStatus;

    /**
     * 使用时间
     */
    private String useTime;

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
