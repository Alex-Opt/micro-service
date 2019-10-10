package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * B端进货商品实体
 */
public class CommodityInfo implements Serializable {

    private static final long serialVersionUID = 4073524038348403524L;

    private String spuId;
    private String skuId;
    private String marketPrice;
    private String minNum;
    private String num;
    private String maxRate;
    private String minPrice;
    private String minRate;
    private String maxPrice;
    private String name;
    private String cId;
    private String spuPictureUrl;

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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getMinNum() {
        return minNum;
    }

    public void setMinNum(String minNum) {
        this.minNum = minNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getSpuPictureUrl() {
        return spuPictureUrl;
    }

    public void setSpuPictureUrl(String spuPictureUrl) {
        this.spuPictureUrl = spuPictureUrl;
    }

    @Override
    public String toString() {
        return "CommodityInfo{" +
                "spuId='" + spuId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", minNum='" + minNum + '\'' +
                ", num='" + num + '\'' +
                ", maxRate='" + maxRate + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", minRate='" + minRate + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", name='" + name + '\'' +
                ", cId='" + cId + '\'' +
                ", spuPictureUrl='" + spuPictureUrl + '\'' +
                '}';
    }
}
