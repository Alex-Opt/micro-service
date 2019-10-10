package com.ly.mt.blue.tooth.wxshop.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 微信Web店铺信息
* @author: wanghongliang
* @create: 2019/8/2 10:55
**/
@ApiModel
public class WxShopWebVo {
    @ApiModelProperty(value ="店铺id",required = false)
    private String shopId;
    @ApiModelProperty(value="姓名",required = true)
    private String name;
    @ApiModelProperty(value="手机号",required=true)
    private String mobile;
    @ApiModelProperty(value="门店名称",required=true)
    private String shopName;
    @ApiModelProperty(value="主营业务",required=true)
    private String mainBusiness;
    @ApiModelProperty(value="门店地址",required=true)
    private String shopAddress;
    @ApiModelProperty(value="营业时间",required=true)
    private String businessHours;
    @ApiModelProperty(value="进货渠道",required=true)
    private String supplyChannels;
    @ApiModelProperty(value="门头照片url",required=true)
    private String photoUrl;
    @ApiModelProperty(value="微信用户openId",required=true)
    private String wxOpenId;
    @ApiModelProperty(value="授权状态 0:待授权 1:已授权 2:授权不通过",required = false)
    private String authStatus;
    @ApiModelProperty(value="坐标腾讯地图坐标 纬度,经度",required=true,example = "39.894416,116.690420")
    private String coordinate;
    @ApiModelProperty(value="城市名称",required=true)
    private String cityName;
    @ApiModelProperty(value="创建时间",required=true)
    private String createTime;
    @ApiModelProperty(value="更新时间",required=true)
    private String updateTime;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getSupplyChannels() {
        return supplyChannels;
    }

    public void setSupplyChannels(String supplyChannels) {
        this.supplyChannels = supplyChannels;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}