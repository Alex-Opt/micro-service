package com.ly.mt.core.base.entity.user;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * @author zhanglifeng
 * @description 用户收货地址实体类user_address
 * @String 2019-05-20
 *//** @deprecated */
public class UserAddress extends BaseEntity {
    private String userId;
    private String userName;
    private String receiveName;
    private String receivePhone;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String lon;
    private String lat;
    private String pickerValue;
    private String userAddress;
    private String isDefault;


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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    @Override
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    @Override
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    @Override
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    @Override
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    @Override
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    @Override
    public String getProvinceCode() {
        return provinceCode;
    }

    @Override
    public String getProvinceName() {
        return provinceName;
    }

    @Override
    public String getCityCode() {
        return cityCode;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getDistrictCode() {
        return districtCode;
    }

    @Override
    public String getDistrictName() {
        return districtName;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPickerValue() {
        return pickerValue;
    }

    public void setPickerValue(String pickerValue) {
        this.pickerValue = pickerValue;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}