package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
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