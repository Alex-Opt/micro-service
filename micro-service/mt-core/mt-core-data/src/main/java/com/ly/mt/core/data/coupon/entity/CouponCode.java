package com.ly.mt.core.data.coupon.entity;

/**
 * coupon_code
 *
 * @author taoye
 */
public class CouponCode {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 优惠券ID
     */
    private String couponId;
    /**
     * 优惠码
     */
    private String couponCode;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 领取状态
     *
     * @see com.ly.mt.core.base.dict.PullStatus
     */
    private String pullStatus;
    /**
     * 领取时间
     */
    private String pullTime;
    /**
     * 失效时间
     */
    private String invalidTime;
    /**
     * 使用状态
     *
     * @see com.ly.mt.core.base.dict.UseStatus
     */
    private String useStatus;
    /**
     * 使用时间
     */
    private String useTime;
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

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}