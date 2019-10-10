package com.ly.mt.cabinet.c.response;

public class GoodsInfoResp {
    private int cabinetNo;
    private String goodsName;

    public GoodsInfoResp() {
    }

    public GoodsInfoResp(int cabinetNo, String goodsName) {
        this.cabinetNo = cabinetNo;
        this.goodsName = goodsName;
    }

    public int getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(int cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
