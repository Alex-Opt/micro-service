package com.ly.mt.home.tob.address.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: ShopAddressVo
 * @author: linan
 * @date: 2019/8/9
 */
@ApiModel
public class ShopAddressVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "店铺编号", hidden = true)
    private String shopId;
    @ApiModelProperty(value = "用户Id", hidden = true)
    private String userId;
    @ApiModelProperty(value = "用户名", hidden = true)
    private String userName;
    @ApiModelProperty("收货人姓名")
    private String receiveName;
    @ApiModelProperty("收货人电话")
    private String receivePhone;
    @ApiModelProperty("省（直辖市）代码")
    private String provinceCode;
    @ApiModelProperty("省（直辖市）名称")
    private String provinceName;
    @ApiModelProperty("省辖市代码")
    private String cityCode;
    @ApiModelProperty("省辖市名称")
    private String cityName;
    @ApiModelProperty("县区代码")
    private String districtCode;
    @ApiModelProperty("县区名称")
    private String districtName;
    @ApiModelProperty("详细地址")
    private String userAddress;
    @ApiModelProperty("是否默认地址 0-非默认地址  1-默认地址")
    private String isDefault;
    @ApiModelProperty("地址经度")
    private String sendLon;
    @ApiModelProperty("地址纬度")
    private String sendLat;
    @ApiModelProperty(value = "1：有效，2：无效", hidden = true)
    private String validStatus;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    @ApiModelProperty(value = "更新时间", hidden = true)
    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getSendLon() {
        return sendLon;
    }

    public void setSendLon(String sendLon) {
        this.sendLon = sendLon;
    }

    public String getSendLat() {
        return sendLat;
    }

    public void setSendLat(String sendLat) {
        this.sendLat = sendLat;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "ShopAddressVo{" +
                "id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", districtName='" + districtName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", isDefault='" + isDefault + '\'' +
                ", sendLon='" + sendLon + '\'' +
                ", sendLat='" + sendLat + '\'' +
                ", validStatus='" + validStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}