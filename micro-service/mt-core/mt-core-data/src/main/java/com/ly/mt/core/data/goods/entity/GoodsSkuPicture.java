package com.ly.mt.core.data.goods.entity;

/**
 * goods_sku_picture
 *
 * @author taoye
 */
public class GoodsSkuPicture {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 商品SKU编号
     */
    private String skuId;
    /**
     * 图片url
     */
    private String pictureUrl;
    /**
     * 排序号
     */
    private String sortNumber;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}