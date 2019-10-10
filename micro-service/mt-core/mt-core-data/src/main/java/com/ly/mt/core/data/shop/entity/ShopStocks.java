package com.ly.mt.core.data.shop.entity;

/**
 * shop_stocks
 *
 * @author taoye
 */
public class ShopStocks {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 店铺编号
     */
    private String shopId;
    /**
     * 商品SPU
     */
    private String spuId;
    /**
     * 商品SKU
     */
    private String skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 数量
     */
    private String nums;
    /**
     * 代发货数量
     */
    private String deliveryNums;
    /**
     * 原价
     */
    private String originalPrice;
    /**
     * 售价
     */
    private String price;
    /**
     * 累计销量
     */
    private String sales;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}