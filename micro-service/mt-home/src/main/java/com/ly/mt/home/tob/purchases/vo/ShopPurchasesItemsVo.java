package com.ly.mt.home.tob.purchases.vo;

import io.swagger.annotations.ApiModel;

/**
 * 进货商品
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@ApiModel
public class ShopPurchasesItemsVo {
    private String id;
    private String shopId;
    private String shopPurchasesId;
    private String spuId;
    private String skuId;
    private String skuName;
    private String skuPrice;
    private String skuNum;
    private String pomotionPrice;
    private String freightPrice;
    private String paymentPrice;
    private String createTime;

    private String spuName;
    private String spuPicUrl;
    private String skuPicUrl;

    public ShopPurchasesItemsVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopPurchasesId() {
        return shopPurchasesId;
    }

    public void setShopPurchasesId(String shopPurchasesId) {
        this.shopPurchasesId = shopPurchasesId;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuPicUrl() {
        return spuPicUrl;
    }

    public void setSpuPicUrl(String spuPicUrl) {
        this.spuPicUrl = spuPicUrl;
    }

    public String getSkuPicUrl() {
        return skuPicUrl;
    }

    public void setSkuPicUrl(String skuPicUrl) {
        this.skuPicUrl = skuPicUrl;
    }
}