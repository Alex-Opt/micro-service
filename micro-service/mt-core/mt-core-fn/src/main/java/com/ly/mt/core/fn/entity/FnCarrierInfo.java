package com.ly.mt.core.fn.entity;

/**
 * 蜂鸟骑手信息
 *
 * @author taoye
 */
public class FnCarrierInfo {
    /**
     * 骑手手机号
     */
    private String carrierPhone;
    /**
     * 骑手姓名
     */
    private String carrierName;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;


    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrierPhone) {
        this.carrierPhone = carrierPhone;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}