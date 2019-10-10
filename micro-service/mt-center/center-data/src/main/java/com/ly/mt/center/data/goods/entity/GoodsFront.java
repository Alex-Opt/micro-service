package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsFront {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品Id")
    private String spu_id;
    @ApiModelProperty("skuId")
    private String sku_id;
    @ApiModelProperty("状态 1：上架，2：下架")
    private String status;
    @ApiModelProperty("价格")
    private String price;
    @ApiModelProperty("排序号")
    private String sort_num;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("应用分类")
    private String app_type;
    @ApiModelProperty("业务分类")
    private String acti_class;
    @ApiModelProperty("图片地址")
    private String picture_url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getActi_class() {
        return acti_class;
    }

    public void setActi_class(String acti_class) {
        this.acti_class = acti_class;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}