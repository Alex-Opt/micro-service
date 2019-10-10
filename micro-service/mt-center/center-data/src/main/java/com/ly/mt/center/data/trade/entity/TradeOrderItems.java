package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrderItems {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单Id")
    private String order_id;
    @ApiModelProperty("spu名字")
    private String spu_name;
    @ApiModelProperty("SPU")
    private String spu_id;
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
    @ApiModelProperty("运费分摊金额（分）")
    private String freight_price;
    @ApiModelProperty("管易ItemId")
    private String gy_spu_id;
    @ApiModelProperty("管易Item代码")
    private String gy_spu_code;
    @ApiModelProperty("管易规格代码")
    private String gy_sku_code;
    @ApiModelProperty("实付金额")
    private String payment_price;
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

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
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

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }

    public String getGy_spu_id() {
        return gy_spu_id;
    }

    public void setGy_spu_id(String gy_spu_id) {
        this.gy_spu_id = gy_spu_id;
    }

    public String getGy_spu_code() {
        return gy_spu_code;
    }

    public void setGy_spu_code(String gy_spu_code) {
        this.gy_spu_code = gy_spu_code;
    }

    public String getGy_sku_code() {
        return gy_sku_code;
    }

    public void setGy_sku_code(String gy_sku_code) {
        this.gy_sku_code = gy_sku_code;
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

}