package com.ly.mt.center.data.wxshop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WxShop {
    @ApiModelProperty("")
    private String shop_id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("门店名称")
    private String shop_name;
    @ApiModelProperty("主营业务")
    private String main_business;
    @ApiModelProperty("门店地址")
    private String shop_address;
    @ApiModelProperty("营业时间")
    private String business_hours;
    @ApiModelProperty("进货渠道")
    private String supply_channels;
    @ApiModelProperty("门头照片url")
    private String photo_url;
    @ApiModelProperty("微信用户openId")
    private String wx_open_id;
    @ApiModelProperty("授权状态 0:待授权 1:已授权 2:授权不通过")
    private String auth_status;
    @ApiModelProperty("城市名字")
    private String city_name;
    @ApiModelProperty("坐标 示例39.894416,116.690420")
    private String coordinate;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String update_time;


    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getMain_business() {
        return main_business;
    }

    public void setMain_business(String main_business) {
        this.main_business = main_business;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public String getSupply_channels() {
        return supply_channels;
    }

    public void setSupply_channels(String supply_channels) {
        this.supply_channels = supply_channels;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getWx_open_id() {
        return wx_open_id;
    }

    public void setWx_open_id(String wx_open_id) {
        this.wx_open_id = wx_open_id;
    }

    public String getAuth_status() {
        return auth_status;
    }

    public void setAuth_status(String auth_status) {
        this.auth_status = auth_status;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}