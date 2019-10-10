package com.ly.mt.center.third.fn.entity;

/**
 * @Description 蜂鸟订单推送实体-
 * @Author taoye
 */
public class FnOrderCreateReceiverInfo {
    // 收货人姓名
    private String receiver_name;
    // 收货人联系方式，只支持手机号，400开头电话，座机号码以及95013开头、长度13位的虚拟电话
    private String receiver_primary_phone;
    // 收货人备用联系方式，只支持手机号，400开头电话，座机号码以及95013开头、长度13位的虚拟电话-非必填
    private String receiver_second_phone;
    // 收货人地址
    private String receiver_address;
    // 收货人经度，取值范围0～180
    private String receiver_longitude;
    // 收货人纬度，取值范围0～90
    private String receiver_latitude;
    // 收货人经纬度来源（1:腾讯地图, 2:百度地图, 3:高德地图），蜂鸟建议使用高德地图
    private String position_source;

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_primary_phone() {
        return receiver_primary_phone;
    }

    public void setReceiver_primary_phone(String receiver_primary_phone) {
        this.receiver_primary_phone = receiver_primary_phone;
    }

    public String getReceiver_second_phone() {
        return receiver_second_phone;
    }

    public void setReceiver_second_phone(String receiver_second_phone) {
        this.receiver_second_phone = receiver_second_phone;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_longitude() {
        return receiver_longitude;
    }

    public void setReceiver_longitude(String receiver_longitude) {
        this.receiver_longitude = receiver_longitude;
    }

    public String getReceiver_latitude() {
        return receiver_latitude;
    }

    public void setReceiver_latitude(String receiver_latitude) {
        this.receiver_latitude = receiver_latitude;
    }

    public String getPosition_source() {
        return position_source;
    }

    public void setPosition_source(String position_source) {
        this.position_source = position_source;
    }
}