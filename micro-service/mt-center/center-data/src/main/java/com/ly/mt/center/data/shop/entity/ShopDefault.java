package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopDefault {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("兜底商家的用户编号")
    private String user_id;
    @ApiModelProperty("兜底商家的真实姓名")
    private String real_name;
    @ApiModelProperty("兜底商家的手机号码")
    private String mobile;
    @ApiModelProperty("兜底商家的状态 1：正常，2：删除")
    private String status;
    @ApiModelProperty("店铺名称")
    private String shop_name;
    @ApiModelProperty("店铺地址经度")
    private String shop_lon;
    @ApiModelProperty("店铺地址纬度")
    private String shop_lat;
    @ApiModelProperty("店铺详细地址")
    private String shop_address;
    @ApiModelProperty("店铺地址国家")
    private String shop_country;
    @ApiModelProperty("店铺地址省")
    private String shop_province_code;
    @ApiModelProperty("店铺地址城市")
    private String shop_city_code;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_lon() {
        return shop_lon;
    }

    public void setShop_lon(String shop_lon) {
        this.shop_lon = shop_lon;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_country() {
        return shop_country;
    }

    public void setShop_country(String shop_country) {
        this.shop_country = shop_country;
    }

    public String getShop_province_code() {
        return shop_province_code;
    }

    public void setShop_province_code(String shop_province_code) {
        this.shop_province_code = shop_province_code;
    }

    public String getShop_city_code() {
        return shop_city_code;
    }

    public void setShop_city_code(String shop_city_code) {
        this.shop_city_code = shop_city_code;
    }

}