package com.ly.mt.core.base.entity.shop;

import com.ly.mt.core.base.entity.base.BaseEntity;

import java.io.Serializable;
/** @deprecated */
public class ShopPurchasesItems extends BaseEntity implements Serializable  {

    private String id;


    private String shopPurchasesId;


    private String spuId;

    private String skuId;


    private String skuName;


    private String skuPrice;


    private String skuNum;


    private String pomotionPrice;


    private String freightPrice;


    private String gySpuId;


    private String gySpuCode;


    private String gySkuCode;


    private String paymentPrice;


    private String createTime;
    private String spuName;


    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getGySpuId() {
        return gySpuId;
    }

    public void setGySpuId(String gySpuId) {
        this.gySpuId = gySpuId;
    }

    public String getGySpuCode() {
        return gySpuCode;
    }

    public void setGySpuCode(String gySpuCode) {
        this.gySpuCode = gySpuCode;
    }

    public String getGySkuCode() {
        return gySkuCode;
    }

    public void setGySkuCode(String gySkuCode) {
        this.gySkuCode = gySkuCode;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }
}