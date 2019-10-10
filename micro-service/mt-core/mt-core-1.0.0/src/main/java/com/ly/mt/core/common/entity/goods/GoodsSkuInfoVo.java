package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

import java.util.List;

/**
 * 商品skuVO类
 */
public class GoodsSkuInfoVo extends GoodsSkuInfo {

   // private GoodsSkuPrice price;//价格
    private List<GoodsSkuPicture> pictureList;//图片

    /*public GoodsSkuPrice getPrice() {
        return price;
    }

    public void setPrice(GoodsSkuPrice price) {
        this.price = price;
    }*/

    public List<GoodsSkuPicture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<GoodsSkuPicture> pictureList) {
        this.pictureList = pictureList;
    }
}