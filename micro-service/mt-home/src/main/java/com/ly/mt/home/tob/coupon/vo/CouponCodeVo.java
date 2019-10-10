package com.ly.mt.home.tob.coupon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CouponCodeVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("优惠券id")
    private String couponId;
    @ApiModelProperty("优惠码")
    private String couponCode;
    @ApiModelProperty("用户Id")
    private String userId;
    @ApiModelProperty("领取状态 1：未领取，2：已领取")
    private String pullStatus;
    @ApiModelProperty("领取时间")
    private String pullTime;
    @ApiModelProperty("新人领取的优惠券失效时间")
    private String InvalidTime;
    @ApiModelProperty("使用状态  1：未使用，2：已使用")
    private String useStatus;
    @ApiModelProperty("使用时间")
    private String useTime;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("优惠折扣")
    private String discountRate;

    public CouponCodeVo() {
    }

    private CouponCodeVo(Builder builder) {
        setId(builder.id);
        setCouponId(builder.couponId);
        setCouponCode(builder.couponCode);
        setUserId(builder.userId);
        setPullStatus(builder.pullStatus);
        setPullTime(builder.pullTime);
        setInvalidTime(builder.InvalidTime);
        setUseStatus(builder.useStatus);
        setUseTime(builder.useTime);
        setCreateTime(builder.createTime);
    }

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
        return InvalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        InvalidTime = invalidTime;
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

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public static final class Builder {
        private String id;
        private String couponId;
        private String couponCode;
        private String userId;
        private String pullStatus;
        private String pullTime;
        private String InvalidTime;
        private String useStatus;
        private String useTime;
        private String createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder couponId(String val) {
            couponId = val;
            return this;
        }

        public Builder couponCode(String val) {
            couponCode = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder pullStatus(String val) {
            pullStatus = val;
            return this;
        }

        public Builder pullTime(String val) {
            pullTime = val;
            return this;
        }

        public Builder InvalidTime(String val) {
            InvalidTime = val;
            return this;
        }

        public Builder useStatus(String val) {
            useStatus = val;
            return this;
        }

        public Builder useTime(String val) {
            useTime = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public CouponCodeVo build() {
            return new CouponCodeVo(this);
        }
    }
}