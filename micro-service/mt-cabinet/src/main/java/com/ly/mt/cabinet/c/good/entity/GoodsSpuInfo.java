package com.ly.mt.cabinet.c.good.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsSpuInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("备份管易的商品名称")
    private String name_gy;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品代码")
    private String code;
    @ApiModelProperty("类目id")
    private String cid;
    @ApiModelProperty("品牌Id")
    private String brand_id;
    @ApiModelProperty("市场零售价")
    private String market_price;
    @ApiModelProperty("市场批发价")
    private String wholesale_price;
    @ApiModelProperty("重量（克）")
    private String weight;
    @ApiModelProperty("图片url")
    private String picture_url;
    @ApiModelProperty("到家c图片url")
    private String picture_url_c;
    @ApiModelProperty("商品描述url，存在oss中")
    private String describe_url;
    @ApiModelProperty("商品属性，商品属性表里会有对应的列表信息 这里保存的是用json方式保存列表信息")
    private String spu_attr;
    @ApiModelProperty("商品状态 1：上架，2：下架")
    private String spu_status;
    @ApiModelProperty("数据来源")
    private String data_source;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;
    @ApiModelProperty("备注")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_gy() {
        return name_gy;
    }

    public void setName_gy(String name_gy) {
        this.name_gy = name_gy;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getDescribe_url() {
        return describe_url;
    }

    public void setDescribe_url(String describe_url) {
        this.describe_url = describe_url;
    }

    public String getSpu_attr() {
        return spu_attr;
    }

    public void setSpu_attr(String spu_attr) {
        this.spu_attr = spu_attr;
    }

    public String getSpu_status() {
        return spu_status;
    }

    public void setSpu_status(String spu_status) {
        this.spu_status = spu_status;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicture_url_c() {
        return picture_url_c;
    }

    public void setPicture_url_c(String picture_url_c) {
        this.picture_url_c = picture_url_c;
    }
}