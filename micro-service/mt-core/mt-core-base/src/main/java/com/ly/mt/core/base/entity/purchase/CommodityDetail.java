package com.ly.mt.core.base.entity.purchase;

import java.io.Serializable;
/**
 * 商品详情实体
 */
/** @deprecated */
public class CommodityDetail implements Serializable {

    private static final long serialVersionUID = -7232453355678993846L;

    private String id;
    private String name;
    private String spuId;
    private String marketPrice;
    /**
     * 商品阶梯价
     */
    private String price;
    private String promotionRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(String promotionRate) {
        this.promotionRate = promotionRate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    @Override
    public String toString() {
        return "CommodityDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", spuId='" + spuId + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", price='" + price + '\'' +
                ", promotionRate='" + promotionRate + '\'' +
                '}';
    }
}
