package com.ly.mt.core.common.entity.hd.vo;

import java.util.List;

public class FrontCategoryVoBody {

    /**
     * 主键
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;


    /**
     * 零售价格
     */
    private Double marketPrice;

    /**
     * 图片地址
     */
    private String pictureUrl;

    /**
     * 简介地址
     */
    private String describeUrl;
    private List<FrontSkuBody> skus;

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

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescribeUrl() {
        return describeUrl;
    }

    public void setDescribeUrl(String describeUrl) {
        this.describeUrl = describeUrl;
    }

    public List<FrontSkuBody> getSkus() {
        return skus;
    }

    public void setSkus(List<FrontSkuBody> skus) {
        this.skus = skus;
    }

}
