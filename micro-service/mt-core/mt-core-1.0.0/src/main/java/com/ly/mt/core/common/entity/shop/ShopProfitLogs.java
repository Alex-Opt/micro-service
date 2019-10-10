package com.ly.mt.core.common.entity.shop;


/**
 * 商家收益日志
 * @author zhanglifeng
 * @String 2019-06-18
 */
public class ShopProfitLogs {
    private String id;

    private String orderId;

    private String shopPurchasesId;

    private String shopId;

    private String userId;

    private String providerShopId;

    private String providerId;

    private String profitType;

    private String profit;

    private String status;

    private String createTime;

    private String modifyTime;

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

    public String getShopPurchasesId() {
        return shopPurchasesId;
    }

    public void setShopPurchasesId(String shopPurchasesId) {
        this.shopPurchasesId = shopPurchasesId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderShopId() {
        return providerShopId;
    }

    public void setProviderShopId(String providerShopId) {
        this.providerShopId = providerShopId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}