package com.ly.mt.cabinet.c.coupon.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CouponCodeVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("优惠券id")
    private String coupon_id;

    @ApiModelProperty("用户Id")
    private String user_id;

    @ApiModelProperty("优惠券名称")
    private String coupon_name;

    @ApiModelProperty("优惠券面向的系统 10:到家C,20:格子柜,30:到家B")
    private String coupon_use_system;

    @ApiModelProperty("优惠金额")
    private String denomination;

    @ApiModelProperty("领取时间")
    private String pull_time;

    @ApiModelProperty("领取的优惠券失效时间")
    private String Invalid_time;

    @ApiModelProperty("使用状态  1：未使用，2：已使用，3：已过期")
    private String use_status;

    @ApiModelProperty("使用时间")
    private String use_time;

    @ApiModelProperty("创建时间")
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_use_system() {
        return coupon_use_system;
    }

    public void setCoupon_use_system(String coupon_use_system) {
        this.coupon_use_system = coupon_use_system;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getPull_time() {
        return pull_time;
    }

    public void setPull_time(String pull_time) {
        this.pull_time = pull_time;
    }

    public String getInvalid_time() {
        return Invalid_time;
    }

    public void setInvalid_time(String invalid_time) {
        Invalid_time = invalid_time;
    }

    public String getUse_status() {
        return use_status;
    }

    public void setUse_status(String use_status) {
        this.use_status = use_status;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}