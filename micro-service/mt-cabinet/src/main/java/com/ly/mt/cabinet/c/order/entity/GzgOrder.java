package com.ly.mt.cabinet.c.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgOrder {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("支付流水号")
    private String payment_no;

    @ApiModelProperty("买家Id")
    private String buyer_id;

    @ApiModelProperty("订单原始金额")
    private String order_old_money;

    @ApiModelProperty("订单实付金额")
    private String order_money;

    @ApiModelProperty("订单优惠金额")
    private String order_discount_money;

    @ApiModelProperty("是否退货（1：否，2：退货审核，3：退货完成）")
    private String is_refund;

    @ApiModelProperty("申请退货时间")
    private String refund_time;

    @ApiModelProperty("支付方式（1 微信，2 支付宝）")
    private String payment_type;

    @ApiModelProperty("订单来源 1：亿联格子柜，2：如金柜子，3：展柜")
    private String order_source;

    @ApiModelProperty("格子柜的code码")
    private String imei;

    @ApiModelProperty("酒店ID")
    private String hotel_id;

    @ApiModelProperty("订单状态（0:待支付,1:已完成,2:超时,3:退款中,4:已退款，5：支付完成但未打开柜门）")
    private String order_status;

    @ApiModelProperty("下单时间")
    private String create_time;

    @ApiModelProperty("支付时间")
    private String pay_time;

    @ApiModelProperty("订单完成时间，也是支付时间")
    private String order_finish_time;


    @Override
    public String toString() {
        return "GzgOrder{" +
                "id='" + id + '\'' +
                ", payment_no='" + payment_no + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", order_old_money='" + order_old_money + '\'' +
                ", order_money='" + order_money + '\'' +
                ", order_discount_money='" + order_discount_money + '\'' +
                ", is_refund='" + is_refund + '\'' +
                ", refund_time='" + refund_time + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", order_source='" + order_source + '\'' +
                ", imei='" + imei + '\'' +
                ", hotel_id='" + hotel_id + '\'' +
                ", order_status='" + order_status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", order_finish_time='" + order_finish_time + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getOrder_old_money() {
        return order_old_money;
    }

    public void setOrder_old_money(String order_old_money) {
        this.order_old_money = order_old_money;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getOrder_discount_money() {
        return order_discount_money;
    }

    public void setOrder_discount_money(String order_discount_money) {
        this.order_discount_money = order_discount_money;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getIs_refund() {
        return is_refund;
    }

    public void setIs_refund(String is_refund) {
        this.is_refund = is_refund;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getOrder_finish_time() {
        return order_finish_time;
    }

    public void setOrder_finish_time(String order_finish_time) {
        this.order_finish_time = order_finish_time;
    }



}