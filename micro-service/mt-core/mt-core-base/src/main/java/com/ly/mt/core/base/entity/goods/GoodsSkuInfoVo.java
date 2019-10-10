package com.ly.mt.core.base.entity.goods;

import java.util.List;

/** @deprecated */
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