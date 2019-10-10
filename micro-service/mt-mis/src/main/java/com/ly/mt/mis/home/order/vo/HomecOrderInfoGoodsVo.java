package com.ly.mt.mis.home.order.vo;

/**
 * MOTI到家-订单管理-订单详情货品信息
 *
 * @author taoye
 */
public class HomecOrderInfoGoodsVo {
    /**
     * 商品SPU名称
     */
    private String spuName;
    /**
     * 商品SKU
     */
    private String skuId;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 追踪码
     */
    private String code;
    /**
     * 数量
     */
    private String skuNum;
    /**
     * 原始金额
     */
    private String oldMoney;
    /**
     * 实付金额
     */
    private String paymentPrice;


    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public String getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }
}