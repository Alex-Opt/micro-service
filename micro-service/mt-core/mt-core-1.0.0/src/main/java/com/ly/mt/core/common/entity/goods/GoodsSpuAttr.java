package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 商品SPU属性关联表
 */
public class GoodsSpuAttr extends BaseEntity {
    private String spuId;// 商品SPU编号
    private String attrId;// 商品属性Id


    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }
}