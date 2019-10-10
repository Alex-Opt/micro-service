package com.ly.mt.center.data.coupon.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CouponModel {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("优惠券名称")
    private String coupon_name;
    @ApiModelProperty("优惠开始时间")
    private String start_time;
    @ApiModelProperty("优惠结束时间")
    private String end_time;
    @ApiModelProperty("新人领取的优惠券有效天数")
    private String validity_day;
    @ApiModelProperty("优惠金额")
    private String denomination;
    @ApiModelProperty("优惠折扣，和优惠活动金额是互斥的")
    private String discount_rate;
    @ApiModelProperty("优惠类型 1：全品类，2：限定商品")
    private String limit_type;
    @ApiModelProperty("最小订单金额")
    private String start_fee;
    @ApiModelProperty("优惠券面向的系统  10:到家C,20:格子柜,30:到家B")
    private String coupon_use_system;
    @ApiModelProperty("优惠券类型 10:新人券-系统发放,20:新人券-自领,30:普通券")
    private String coupon_type;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("创建人")
    private String creater_id;
    @ApiModelProperty("创建时间")
    private String create_time;

    private String use_status;
    private String coupon_code;
    private String Invalid_time;
    private String coupon_id;
    private String user_id;
    private String coupon_code_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getValidity_day() {
        return validity_day;
    }

    public void setValidity_day(String validity_day) {
        this.validity_day = validity_day;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getLimit_type() {
        return limit_type;
    }

    public void setLimit_type(String limit_type) {
        this.limit_type = limit_type;
    }

    public String getStart_fee() {
        return start_fee;
    }

    public void setStart_fee(String start_fee) {
        this.start_fee = start_fee;
    }

    public String getCoupon_use_system() {
        return coupon_use_system;
    }

    public void setCoupon_use_system(String coupon_use_system) {
        this.coupon_use_system = coupon_use_system;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(String creater_id) {
        this.creater_id = creater_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUse_status() {
        return use_status;
    }

    public void setUse_status(String use_status) {
        this.use_status = use_status;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getInvalid_time() {
        return Invalid_time;
    }

    public void setInvalid_time(String invalid_time) {
        Invalid_time = invalid_time;
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

    public String getCoupon_code_id() {
        return coupon_code_id;
    }

    public void setCoupon_code_id(String coupon_code_id) {
        this.coupon_code_id = coupon_code_id;
    }
}