package com.ly.mt.core.base.entity.purchase;

import java.io.Serializable;
/**
 * 商家地址信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-25 22:53:53
 */
/** @deprecated */
public class ShopAddressVO implements Serializable {

    private static final long serialVersionUID = 5636657323410345563L;

    private String receiveName;

    private String receivePhone;

    private String provinceName;

    private String cityName;

    private String districtName;

    private String userAddress;

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

    @Override
    public String toString() {
        return "ShopAddressVO{" +
                "receiveName='" + receiveName + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
