package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class GoodsAttrValue extends BaseEntity {

    private String attrId;//属性id

    private String attrStatus;//属性状态

    private String attrValue;//属性值

    private String sortNum;//排序

    private String attrName;//属性名

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrStatus() {
        return attrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        this.attrStatus = attrStatus;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }
}