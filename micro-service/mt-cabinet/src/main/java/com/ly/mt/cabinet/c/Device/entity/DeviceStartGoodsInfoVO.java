package com.ly.mt.cabinet.c.Device.entity;

public class DeviceStartGoodsInfoVO {
    private int cabinetNo;
    private int goodsCount;
    private double goodsPrice;
    private String goodsName;

    public int getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(int cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}

