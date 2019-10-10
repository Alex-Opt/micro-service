package com.ly.mt.activity.apply.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("报名入驻格子柜vo类")
public class SignUpCabinet {
    @ApiModelProperty(value = "姓名", required = true)
    private String userName;
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;
    @ApiModelProperty(value = "店铺所在省", required = true)
    private String provinceName;
    @ApiModelProperty(value = "店铺所在市", required = true)
    private String cityName;
    @ApiModelProperty(value = "店铺所在区", required = true)
    private String areaName;
    @ApiModelProperty(value = "动态验证码", required = true)
    private String dynamicCode;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}