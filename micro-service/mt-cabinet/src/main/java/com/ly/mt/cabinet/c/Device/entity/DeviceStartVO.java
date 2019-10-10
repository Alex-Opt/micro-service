package com.ly.mt.cabinet.c.Device.entity;

import java.util.List;

public class DeviceStartVO {
    private String mctId;
    private String deviceImei;
    private double money;
    private String payType;
    private String orderId;
    private List<DeviceStartGoodsInfoVO> goodsInfo;

    public String getMctId() {
        return mctId;
    }

    public void setMctId(String mctId) {
        this.mctId = mctId;
    }

    public String getDeviceImei() {
        return deviceImei;
    }

    public void setDeviceImei(String deviceImei) {
        this.deviceImei = deviceImei;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<DeviceStartGoodsInfoVO> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<DeviceStartGoodsInfoVO> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
