package com.ly.mt.center.data.coupon.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CouponCode {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("优惠券id")
    private String coupon_id;
    @ApiModelProperty("优惠码")
    private String coupon_code;
    @ApiModelProperty("用户Id")
    private String user_id;
    @ApiModelProperty("领取状态 1：未领取，2：已领取")
    private String pull_status;
    @ApiModelProperty("领取时间")
    private String pull_time;
    @ApiModelProperty("新人领取的优惠券失效时间")
    private String Invalid_time;
    @ApiModelProperty("使用状态  1：未使用，2：已使用")
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

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPull_status() {
        return pull_status;
    }

    public void setPull_status(String pull_status) {
        this.pull_status = pull_status;
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

    public void setInvalid_time(String Invalid_time) {
        this.Invalid_time = Invalid_time;
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