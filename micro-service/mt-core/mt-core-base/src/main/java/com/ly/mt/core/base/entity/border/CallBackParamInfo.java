package com.ly.mt.core.base.entity.border;

import com.alibaba.fastjson.annotation.JSONField;

/** @deprecated */
public class CallBackParamInfo {
    /**商户自己的订单号*/
    @JSONField(name ="partner_order_code")
    private String orderNo;

    private String orderId;

    /**状态码*/
    @JSONField(name = "order_status")
    private Integer orderStatus;

    /**状态推送时间(毫秒)*/
    @JSONField(name = "push_time")
    private Long pushTime;

    /**蜂鸟配送员姓名*/
    @JSONField(name = "carrier_driver_name")
    private String deliverymanName;

    /**蜂鸟配送员电话*/
    @JSONField(name = "carrier_driver_phone")
    private String deliverymanPhone	;

    /**描述信息*/
    private String description;

    /**定点次日达服务独有的字段: 微仓地址*/
    private String address;

    /**定点次日达服务独有的字段: 微仓纬度*/
    private Long latitude;

    /**定点次日达服务独有的字段: 微仓经度*/
    private Long longitude;

    /**订单取消原因. 1:用户取消, 2:商家取消*/
    @JSONField(name = "cancel_reason")
    private Integer cancelReason;

    /**错误编码*/
    @JSONField(name = "error_code")
    private String errorCode;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getPushTime() {
        return pushTime;
    }

    public void setPushTime(Long pushTime) {
        this.pushTime = pushTime;
    }

    public String getDeliverymanName() {
        return deliverymanName;
    }

    public void setDeliverymanName(String deliverymanName) {
        this.deliverymanName = deliverymanName;
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Integer getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(Integer cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CallBackParamInfo{" +
                "orderNo='" + orderNo + '\'' +
                "orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                ", pushTime=" + pushTime +
                ", deliverymanName='" + deliverymanName + '\'' +
                ", deliverymanPhone='" + deliverymanPhone + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cancelReason=" + cancelReason +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
