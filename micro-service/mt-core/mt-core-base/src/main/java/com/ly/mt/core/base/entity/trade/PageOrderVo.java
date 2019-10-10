package com.ly.mt.core.base.entity.trade;

/** @deprecated */
public class PageOrderVo{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 电话
     */
    private String mobile;


    /**
     * 地址
     */
    private String address;

    /**
     * SPU  Id
     */
    private String spuId;

    /**
     * 商品SKU
     */
    private String skuId;

    /**
     * 购买商品（sku）数量
     */
    private String skuNum;

    /**
     * 下单来源
     */
    private String orderSource;


    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
