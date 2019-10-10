package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 商品SKU图片表
 */
public class GoodsSkuPicture extends BaseEntity {
    private String skuId;//商品SKU编号
    private String pictureUrl;// 图片url
    private String sortNumber;// 排序号,排序号最小的作为主图，从1开始

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }
}