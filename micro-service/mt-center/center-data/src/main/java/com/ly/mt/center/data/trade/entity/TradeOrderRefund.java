package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrderRefund {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单Id")
    private String order_id;
    @ApiModelProperty("买家Id")
    private String buyer_id;
    @ApiModelProperty("退款申请的状态 10：申请状态，20：同意退货，30：拒绝退货，50：买家发货，60：平台收货，99：退货完成")
    private String refund_status;
    @ApiModelProperty("退货问题描述")
    private String refund_desc;
    @ApiModelProperty("退货原因")
    private String refund_reason;
    @ApiModelProperty("退款金额（分）")
    private String refund_price;
    @ApiModelProperty("退款运费金额（分）")
    private String refund_freight_price;
    @ApiModelProperty("扣减优惠金额（分）")
    private String subtract_coupon_price;
    @ApiModelProperty("退货地址")
    private String retund_address;
    @ApiModelProperty("退货类型 1：整单退货，2：部分退货")
    private String refund_type;
    @ApiModelProperty("退款单状态 1：正常，2：取消")
    private String status;
    @ApiModelProperty("收货人名字")
    private String consignee;
    @ApiModelProperty("收货人电话")
    private String phone;
    @ApiModelProperty("退货申请时间")
    private String create_time;


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

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public String getRefund_price() {
        return refund_price;
    }

    public void setRefund_price(String refund_price) {
        this.refund_price = refund_price;
    }

    public String getRefund_freight_price() {
        return refund_freight_price;
    }

    public void setRefund_freight_price(String refund_freight_price) {
        this.refund_freight_price = refund_freight_price;
    }

    public String getSubtract_coupon_price() {
        return subtract_coupon_price;
    }

    public void setSubtract_coupon_price(String subtract_coupon_price) {
        this.subtract_coupon_price = subtract_coupon_price;
    }

    public String getRetund_address() {
        return retund_address;
    }

    public void setRetund_address(String retund_address) {
        this.retund_address = retund_address;
    }

    public String getRefund_type() {
        return refund_type;
    }

    public void setRefund_type(String refund_type) {
        this.refund_type = refund_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}