package com.ly.mt.core.data.trade.entity;

/**
 * trade_orderItems
 */
public class TradeOrderItems {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * SPU名字
     */
    private String spuName;
    /**
     * SPU ID
     */
    private String spuId;
    /**
     * SKU ID
     */
    private String skuId;
    /**
     * SKU名字
     */
    private String skuName;
    /**
     * 商品单价
     */
    private String skuPrice;
    /**
     * 数量
     */
    private String skuNum;
    /**
     * 优惠分摊金额
     */
    private String pomotionPrice;
    /**
     * 运费分摊金额（分）
     */
    private String freightPrice;
    /**
     * 实付金额
     */
    private String paymentPrice;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public String getPomotionPrice() {
        return pomotionPrice;
    }

    public void setPomotionPrice(String pomotionPrice) {
        this.pomotionPrice = pomotionPrice;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}