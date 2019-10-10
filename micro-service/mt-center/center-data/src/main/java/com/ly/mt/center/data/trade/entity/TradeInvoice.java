package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeInvoice {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单Id")
    private String order_id;
    @ApiModelProperty("发票类型 1=普票，2=增票")
    private String invoice_type;
    @ApiModelProperty("发票抬头")
    private String invoice_title;
    @ApiModelProperty("实付金额")
    private String payment_price;
    @ApiModelProperty("发票金额")
    private String invoice_price;
    @ApiModelProperty("收票人姓名")
    private String contact_person;
    @ApiModelProperty("收票人电话")
    private String contact_phone;
    @ApiModelProperty("创建时间")
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

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getPayment_price() {
        return payment_price;
    }

    public void setPayment_price(String payment_price) {
        this.payment_price = payment_price;
    }

    public String getInvoice_price() {
        return invoice_price;
    }

    public void setInvoice_price(String invoice_price) {
        this.invoice_price = invoice_price;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}