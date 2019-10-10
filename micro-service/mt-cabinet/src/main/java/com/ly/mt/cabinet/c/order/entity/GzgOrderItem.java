package com.ly.mt.cabinet.c.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgOrderItem implements Comparable<GzgOrderItem>{
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("订单Id")
    private String order_id;

    @ApiModelProperty("商品SKU")
    private String sku_id;

    @ApiModelProperty("SKU名称")
    private String sku_name;

    @ApiModelProperty("商品单价")
    private String sku_price;

    @ApiModelProperty("数量")
    private String sku_num;

    @ApiModelProperty("优惠分摊金额")
    private String pomotion_price;

    @ApiModelProperty("实付金额")
    private String payment_price;

    @ApiModelProperty("创建时间")
    private String create_time;

    @ApiModelProperty("商品所在格子的号码")
    private String cabinet_no;

    @ApiModelProperty("商品所在格子柜的code值")
    private String imei;

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

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getSku_num() {
        return sku_num;
    }

    public void setSku_num(String sku_num) {
        this.sku_num = sku_num;
    }

    public String getPomotion_price() {
        return pomotion_price;
    }

    public void setPomotion_price(String pomotion_price) {
        this.pomotion_price = pomotion_price;
    }

    public String getPayment_price() {
        return payment_price;
    }

    public void setPayment_price(String payment_price) {
        this.payment_price = payment_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public int compareTo(GzgOrderItem o) {
        return this.sku_price.compareTo(o.getSku_price());
    }
}