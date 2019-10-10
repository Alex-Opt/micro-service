package com.ly.mt.home.tob.goods.vo;

import io.swagger.annotations.ApiModel;

@ApiModel
public class GoodsAttrValueVo {
    private String id;
    private String attrValue;
    private String attrValueId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(String attrValueId) {
        this.attrValueId = attrValueId;
    }
}