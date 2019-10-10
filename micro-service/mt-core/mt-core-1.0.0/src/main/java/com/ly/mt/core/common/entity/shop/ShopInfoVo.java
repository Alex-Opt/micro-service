package com.ly.mt.core.common.entity.shop;

public class ShopInfoVo {

    //店铺ID
    private String shopId;
    //发货地址经度
    private  String  sendLon;
    //发货地址纬度
    private  String  sendLat;
    //发货详细地址 对应发货地址表的user_address
    private  String  sendAddress;
    //省（直辖市）代码
    private  String  sendProvinceCode;
    //发货省名
    private  String  sendProvinceName;

    //县区代码
    private String sendDistrictCode;

    //县区名称
    private String sendDistrictName;

    //省辖市代码 发货地址表 city_code
    private  String   sendCityCode;
    //发货城市名
    private  String   sendCityName;
    //店铺类型
    private  String   shopType ;
    //店铺名称
    private  String   shopName ;
    //电商店主
    private String eShopName;
    //电商url
    private String eShopUrl;
    //店铺经度
    private  String   shopLon;
    //店铺纬度
    private  String   shopLat;
    //店铺国家
    private String shopCountry;
    //店铺具体地址
    private  String   shopAddress ;
    //店铺省编码
    private  String   shopProvinceCode;
    //店铺城市编码
    private  String   shopCityCode;
    //修改时间
    private  String   modifyTime ;


    public String getShopCountry() {
        return shopCountry;
    }

    public void setShopCountry(String shopCountry) {
        this.shopCountry = shopCountry;
    }

    public String getSendProvinceName() {
        return sendProvinceName;
    }

    public void setSendProvinceName(String sendProvinceName) {
        this.sendProvinceName = sendProvinceName;
    }

    public String getSendDistrictCode() {
        return sendDistrictCode;
    }

    public void setSendDistrictCode(String sendDistrictCode) {
        this.sendDistrictCode = sendDistrictCode;
    }

    public String getSendDistrictName() {
        return sendDistrictName;
    }

    public void setSendDistrictName(String sendDistrictName) {
        this.sendDistrictName = sendDistrictName;
    }

    public String getSendCityName() {
        return sendCityName;
    }

    public void setSendCityName(String sendCityName) {
        this.sendCityName = sendCityName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSendLon() {
        return sendLon;
    }


    public String geteShopName() {
        return eShopName;
    }

    public void seteShopName(String eShopName) {
        this.eShopName = eShopName;
    }

    public String geteShopUrl() {
        return eShopUrl;
    }

    public void seteShopUrl(String eShopUrl) {
        this.eShopUrl = eShopUrl;
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

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendProvinceCode() {
        return sendProvinceCode;
    }

    public void setSendProvinceCode(String sendProvinceCode) {
        this.sendProvinceCode = sendProvinceCode;
    }

    public String getSendCityCode() {
        return sendCityCode;
    }

    public void setSendCityCode(String sendCityCode) {
        this.sendCityCode = sendCityCode;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLon() {
        return shopLon;
    }

    public void setShopLon(String shopLon) {
        this.shopLon = shopLon;
    }

    public String getShopLat() {
        return shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopProvinceCode() {
        return shopProvinceCode;
    }

    public void setShopProvinceCode(String shopProvinceCode) {
        this.shopProvinceCode = shopProvinceCode;
    }

    public String getShopCityCode() {
        return shopCityCode;
    }

    public void setShopCityCode(String shopCityCode) {
        this.shopCityCode = shopCityCode;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
