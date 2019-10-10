package com.ly.mt.core.base.entity.shop;

/**
 * 兜底商家信息
 * @author zhanglifeng
 * @date 2019-07-04
 *//** @deprecated */
public class ShopDefault {
    private String id;

    private String userId;

    private String realName;

    private String mobile;

    private String status;

    private String shopName;

    private String shopLon;

    private String shopLat;

    private String shopAddress;

    private String shopCountry;

    private String shopProvinceCode;

    private String shopCityCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopLon() {
        return shopLon;
    }

    public void setShopLon(String shopLon) {
        this.shopLon = shopLon == null ? null : shopLon.trim();
    }

    public String getShopLat() {
        return shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat == null ? null : shopLat.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getShopCountry() {
        return shopCountry;
    }

    public void setShopCountry(String shopCountry) {
        this.shopCountry = shopCountry == null ? null : shopCountry.trim();
    }

    public String getShopProvinceCode() {
        return shopProvinceCode;
    }

    public void setShopProvinceCode(String shopProvinceCode) {
        this.shopProvinceCode = shopProvinceCode == null ? null : shopProvinceCode.trim();
    }

    public String getShopCityCode() {
        return shopCityCode;
    }

    public void setShopCityCode(String shopCityCode) {
        this.shopCityCode = shopCityCode == null ? null : shopCityCode.trim();
    }
}