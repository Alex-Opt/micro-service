package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 商品SKU属性关联表
 */
public class GoodsSkuAttr extends BaseEntity {
    private String skuId;//商品SKU编号
    private String attrId;// 商品SKU属性关联表

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }
}