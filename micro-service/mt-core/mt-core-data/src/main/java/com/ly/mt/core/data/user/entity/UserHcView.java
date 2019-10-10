package com.ly.mt.core.data.user.entity;

/**
 * MOTI到家C端用户
 * user_hc_view
 *
 * @author taoye
 */
public class UserHcView extends User {
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
     * 最近订单ID
     */
    private String orderId;


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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}