package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopStocks {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("店铺编号")
    private String shop_id;
    @ApiModelProperty("商品SPU")
    private String spu_id;
    @ApiModelProperty("商品SKU")
    private String sku_id;
    @ApiModelProperty("sku名称")
    private String sku_name;
    @ApiModelProperty("数量")
    private String nums;
    @ApiModelProperty("代发货数量")
    private String delivery_nums;
    @ApiModelProperty("原价")
    private String original_price;
    @ApiModelProperty("售价")
    private String price;
    @ApiModelProperty("累计销量")
    private String sales;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


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

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getDelivery_nums() {
        return delivery_nums;
    }

    public void setDelivery_nums(String delivery_nums) {
        this.delivery_nums = delivery_nums;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}