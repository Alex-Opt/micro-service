package com.ly.mt.core.data.gzg.entity;

/**
 * gzg_order_item
 */
public class GzgOrderItem {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商品SKU
     */
    private String skuId;
    /**
     * SKU名称
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
     * 原始金额
     */
    private String oldMoney;
    /**
     * 优惠分摊金额
     */
    private String pomotionPrice;
    /**
     * 实付金额
     */
    private String paymentPrice;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 商品所在格子的号码
     */
    private String cabinetNo;
    /**
     * 商品所在格子柜的code值
     */
    private String code;


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

    public String getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getPomotionPrice() {
        return pomotionPrice;
    }

    public void setPomotionPrice(String pomotionPrice) {
        this.pomotionPrice = pomotionPrice;
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

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}