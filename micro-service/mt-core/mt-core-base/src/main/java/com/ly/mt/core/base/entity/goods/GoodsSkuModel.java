package com.ly.mt.core.base.entity.goods;

import java.util.List;

/** @deprecated */
public class GoodsSkuModel {

    /**
     * SPU  Id
     */
    private String spuId;

    /**
     * 商品spu名称
     */
    private String spuName;

    /**
     * 商品SKU
     */
    private String skuId;

    /**
     * 购买商品（sku）数量
     */
    private String skuNum;

    /**
     * 商品的名称
     */
    private String skuName;

    /**
     * 商品属性值
     */
    private List<String> attrValue;

    /**
     * 单个商品的原始价格
     */
    private String skuPrice;

    /**
     * 每个商品的优惠分摊
     */
    private String discountApportion;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

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

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public List<String> getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(List<String> attrValue) {
        this.attrValue = attrValue;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getDiscountApportion() {
        return discountApportion;
    }

    public void setDiscountApportion(String discountApportion) {
        this.discountApportion = discountApportion;
    }
}
