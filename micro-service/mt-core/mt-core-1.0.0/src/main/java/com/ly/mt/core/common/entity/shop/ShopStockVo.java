package com.ly.mt.core.common.entity.shop;

import java.util.List;

/**
 * 库存查询返回类
 */
public class ShopStockVo {
    //spuId
    private String spuId;

    //spuName
    private String spuName;
    //spu图片
    private String spuPictureUrl;
    //商品价格
    private String spuPrice;

    //商品库存list
    private List<ShopStocks>  stocks;


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

    public String getSpuPictureUrl() {
        return spuPictureUrl;
    }

    public void setSpuPictureUrl(String spuPictureUrl) {
        this.spuPictureUrl = spuPictureUrl;
    }

    public String getSpuPrice() {
        return spuPrice;
    }

    public void setSpuPrice(String spuPrice) {
        this.spuPrice = spuPrice;
    }

    public List<ShopStocks> getStocks() {
        return stocks;
    }

    public void setStocks(List<ShopStocks> stocks) {
        this.stocks = stocks;
    }
}
