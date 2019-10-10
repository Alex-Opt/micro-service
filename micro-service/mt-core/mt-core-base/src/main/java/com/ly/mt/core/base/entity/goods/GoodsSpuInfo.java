package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class GoodsSpuInfo extends BaseEntity {
    /**
     * @Description 类目id
     */
    private String cid;
    /**
     * @Description 品牌Id
     */
    private String brandId;
    /**
     * @Description 市场零售价
     */
    private String marketPrice;
    /**
     * @Description 市场批发价
     */
    private String wholesalePrice;
    /**
     * @Description 重量（克）
     */
    private String weight;



    /**
     * 图片地址
     */
    private String pictureUrl;
    /**
     * 到家c图片地址
     */
    private String pictureUrlC;
    /**
     * @Description 商品描述url
     */
    private String describeUrl;
    /**
     * @Description 商品属性
     */
    private String spuAttr;
    /**
     * @Description 商品状态
     */
    private String spuStatus;
    /**
     * @Description 数据来源
     */
    private String dataSource;
    /**
     * @Description 备注
     */
    private String remark;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescribeUrl() {
        return describeUrl;
    }

    public void setDescribeUrl(String describeUrl) {
        this.describeUrl = describeUrl;
    }

    public String getSpuAttr() {
        return spuAttr;
    }

    public void setSpuAttr(String spuAttr) {
        this.spuAttr = spuAttr;
    }

    public String getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(String spuStatus) {
        this.spuStatus = spuStatus;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrlC() {
        return pictureUrlC;
    }

    public void setPictureUrlC(String pictureUrlC) {
        this.pictureUrlC = pictureUrlC;
    }
}