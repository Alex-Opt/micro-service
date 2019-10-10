package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class GoodsSkuPrice extends BaseEntity {
    private String id;
    /**
     * 商品SPU编号
     */
    private String spuId;
    /**
     * 商品sku编号
     */
    private String skuId;
    /**
     * 价格名称，如 用户等级价格
     */
    private String priceName;
    /**
     * SKU商品价格
     */
    private String skuPrice;
    /**
     * 备注
     */
    private String remark;

    private String createTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}