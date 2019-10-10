package com.ly.mt.home.tob.coupon.vo;

import com.ly.mt.core.base.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CouponInfoVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("优惠券名称")
    private String couponName;
    @ApiModelProperty("优惠开始时间")
    private String startTime;
    @ApiModelProperty("优惠结束时间")
    private String endTime;
    @ApiModelProperty("新人领取的优惠券有效天数")
    private String validityDay;
    @ApiModelProperty("优惠金额")
    private String denomination;
    @ApiModelProperty("优惠折扣，和优惠活动金额是互斥的")
    private String discountRate;
    @ApiModelProperty("优惠类型 1：全品类，2：限定商品")
    private String limitType;
    @ApiModelProperty("最小订单金额")
    private String startFee;
    @ApiModelProperty("优惠券面向的系统  10:到家C,20:格子柜,30:到家B")
    private String couponUseSystem;
    @ApiModelProperty("优惠券类型 10:新人券-系统发放,20:新人券-自领,30:普通券")
    private String couponType;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("创建人")
    private String createrId;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("优惠码")
    private String couponCode;
    @ApiModelProperty("使用状态  1：未使用，2：已使用")
    private String useStatus;
    @ApiModelProperty("用户Id")
    private String userId;
    private String InvalidTime;
    private String couponCodeId;

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
        return DateUtil.dateFormat(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return DateUtil.dateFormat(endTime);
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
        return DateUtil.dateFormat(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvalidTime() {
        return DateUtil.dateFormat(InvalidTime);
    }

    public void setInvalidTime(String invalidTime) {
        InvalidTime = invalidTime;
    }

    public String getCouponCodeId() {
        return couponCodeId;
    }

    public void setCouponCodeId(String couponCodeId) {
        this.couponCodeId = couponCodeId;
    }
}