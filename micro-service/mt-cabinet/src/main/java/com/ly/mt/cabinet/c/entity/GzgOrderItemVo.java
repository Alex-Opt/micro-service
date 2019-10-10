package com.ly.mt.cabinet.c.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单明细表  返回前端用Vo
 *
 * @author gongjy
 * @String 2019-05-21
 */
@ApiModel
public class GzgOrderItemVo {
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
    @ApiModelProperty("商品缩略图url")
    private String picture_url;


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

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}