package com.ly.mt.core.data.user.entity;

/**
 * MOTI到家B端用户
 * user_hb_view
 *
 * @author taoye
 */
public class UserHbView extends User {
    /**
     * 省（直辖市）名称
     */
    private String provinceName;
    /**
     * 省辖市名称
     */
    private String cityName;
    /**
     * 县区名称
     */
    private String districtName;
    /**
     * 累计售卖件数
     */
    private String salesCount;
    /**
     * 累计售卖金额
     */
    private String salesMoney;
    /**
     * 最近售卖时间
     */
    private String lastSalesTime;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 店铺地址
     */
    private String shopAddress;


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

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }

    public String getSalesMoney() {
        return salesMoney;
    }

    public void setSalesMoney(String salesMoney) {
        this.salesMoney = salesMoney;
    }

    public String getLastSalesTime() {
        return lastSalesTime;
    }

    public void setLastSalesTime(String lastSalesTime) {
        this.lastSalesTime = lastSalesTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}