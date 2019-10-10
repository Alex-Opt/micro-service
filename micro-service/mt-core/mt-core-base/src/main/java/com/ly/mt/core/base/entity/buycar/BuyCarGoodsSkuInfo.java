package com.ly.mt.core.base.entity.buycar;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class BuyCarGoodsSkuInfo extends BaseEntity {
    /**
     * @Description SPU ID
     */
    private String spuId;
    /**
     * @Description 状态
     */
    private String skuStatus;
    /**
     * @Description 条形码
     */
    private String barCode;
    /**
     * @Description 市场零售价
     */
    private String marketPrice;
    /**
     * @Description 市场批发价
     */
    private String wholesalePrice;
    /**
     * @Description 商品属性
     */
    private String skuAttr;

    /**
     * @Description 商品属性Ids id:id:id
     */
    private String attributes;

    /**
     * @Description 商品货号
     */
    private String productNo;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        this.skuStatus = skuStatus;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
}