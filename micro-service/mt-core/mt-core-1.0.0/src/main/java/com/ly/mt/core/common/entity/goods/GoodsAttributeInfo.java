package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 商品属性信息
 */
public class GoodsAttributeInfo extends BaseEntity {

    private String attrName;//属性名称
    private String attrValue;// 属性值
    private String attrStatus;//属性状态 1：正常，2：停用

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        this.attrStatus = attrStatus;
    }
}