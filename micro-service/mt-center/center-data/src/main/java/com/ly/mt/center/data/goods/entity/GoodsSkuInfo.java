package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsSkuInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("sku名称")
    private String name;
    @ApiModelProperty("sku code")
    private String code;
    @ApiModelProperty("spu id")
    private String spu_id;
    @ApiModelProperty("sku 状态 1、正常，2、停用")
    private String sku_status;
    @ApiModelProperty("商品条形码")
    private String bar_code;
    @ApiModelProperty("市场零售价")
    private String market_price;
    @ApiModelProperty("市场批发价")
    private String wholesale_price;
    @ApiModelProperty("商品属性值Id列表 id:id:id")
    private String attributes;
    @ApiModelProperty("商品货号")
    private String product_no;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("管易sku名称")
    private String name_gy;
    @ApiModelProperty("信息更新时间")
    private String update_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_status() {
        return sku_status;
    }

    public void setSku_status(String sku_status) {
        this.sku_status = sku_status;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getWholesale_price() {
        return wholesale_price;
    }

    public void setWholesale_price(String wholesale_price) {
        this.wholesale_price = wholesale_price;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getName_gy() {
        return name_gy;
    }

    public void setName_gy(String name_gy) {
        this.name_gy = name_gy;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

}