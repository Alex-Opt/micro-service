package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPurchasesItems {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商铺编号")
    private String shop_id;
    @ApiModelProperty("进货订单编号")
    private String shop_purchases_id;
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
    @ApiModelProperty("运费分摊金额")
    private String freight_price;
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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_purchases_id() {
        return shop_purchases_id;
    }

    public void setShop_purchases_id(String shop_purchases_id) {
        this.shop_purchases_id = shop_purchases_id;
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