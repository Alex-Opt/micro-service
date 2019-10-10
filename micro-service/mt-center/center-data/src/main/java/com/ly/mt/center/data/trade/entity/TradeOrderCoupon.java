package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrderCoupon {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单Id")
    private String order_id;
    @ApiModelProperty("优惠活动类型：1-参加优惠活动(activity_info表)，2-使用优惠券(coupon_info表)")
    private String coupon_type;
    @ApiModelProperty("优惠活动Id")
    private String coupon_activity_id;
    @ApiModelProperty("优惠金额")
    private String discount_price;
    @ApiModelProperty("优惠折扣")
    private String discount_rate;
    @ApiModelProperty("创建时间")
    private String use_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getCoupon_activity_id() {
        return coupon_activity_id;
    }

    public void setCoupon_activity_id(String coupon_activity_id) {
        this.coupon_activity_id = coupon_activity_id;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

}