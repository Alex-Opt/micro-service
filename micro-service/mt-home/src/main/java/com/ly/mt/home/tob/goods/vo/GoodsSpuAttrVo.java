package com.ly.mt.home.tob.goods.vo;

import io.swagger.annotations.ApiModel;

@ApiModel
public class GoodsSpuAttrVo {
    private String id;
    private String spuId;
    private String attrId;
    private String createTime;

    public GoodsSpuAttrVo() {
    }

    private GoodsSpuAttrVo(Builder builder) {
        setId(builder.id);
        setSpuId(builder.spuId);
        setAttrId(builder.attrId);
        setCreateTime(builder.createTime);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public static final class Builder {
        private String id;
        private String spuId;
        private String attrId;
        private String createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder spuId(String val) {
            spuId = val;
            return this;
        }

        public Builder attrId(String val) {
            attrId = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public GoodsSpuAttrVo build() {
            return new GoodsSpuAttrVo(this);
        }
    }
}