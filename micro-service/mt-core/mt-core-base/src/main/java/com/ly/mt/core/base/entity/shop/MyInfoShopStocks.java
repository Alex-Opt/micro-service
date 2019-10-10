package com.ly.mt.core.base.entity.shop;

import com.ly.mt.core.base.entity.base.BaseEntity;
import com.ly.mt.core.base.entity.goods.GoodsSpuInfo;

import java.io.Serializable;
/** @deprecated */
public class MyInfoShopStocks extends BaseEntity implements Serializable  {

    private String id;

    private String spuName;

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    private String skuName;


    private String shopId;

    private String spuId;

    private String skuId;

    private String nums;

    private String deliveryNums;

    private String originalPrice;

    private String price;

    private String sales;

    private String status;

    private String createTime;

    private String modifyTime;

    private GoodsSpuInfo goodsSpuInfo;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getDeliveryNums() {
        return deliveryNums;
    }

    public void setDeliveryNums(String deliveryNums) {
        this.deliveryNums = deliveryNums;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public GoodsSpuInfo getGoodsSpuInfo() {
        return goodsSpuInfo;
    }

    public void setGoodsSpuInfo(GoodsSpuInfo goodsSpuInfo) {
        this.goodsSpuInfo = goodsSpuInfo;
    }
}