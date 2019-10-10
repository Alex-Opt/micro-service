package com.ly.mt.core.data.goods.entity;

/**
 * goods_sku_info
 *
 * @author taoye
 */
public class GoodsSkuInfo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * SKU名称
     */
    private String name;
    /**
     * SKU CODE
     */
    private String code;
    /**
     * SPU ID
     */
    private String spuId;
    /**
     * 状态
     *
     * @see com.ly.mt.core.base.dict.SkuStatus
     */
    private String skuStatus;
    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 市场零售价
     */
    private String marketPrice;
    /**
     * 市场批发价
     */
    private String wholesalePrice;
    /**
     * 商品货号
     */
    private String productNo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        this.skuStatus = skuStatus;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}