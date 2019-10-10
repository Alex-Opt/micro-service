package com.ly.mt.home.tob.goods.vo;

import io.swagger.annotations.ApiModel;

@ApiModel
public class GoodsPictureVo {
    private String id;
    private String skuId;
    private String spuId;
    private String pictureUrl;
    private String sortNumber;
    private String createTime;

    public GoodsPictureVo() {
    }

    private GoodsPictureVo(Builder builder) {
        setId(builder.id);
        setSkuId(builder.skuId);
        setSpuId(builder.spuId);
        setPictureUrl(builder.pictureUrl);
        setSortNumber(builder.sortNumber);
        setCreateTime(builder.createTime);
    }

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

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public static final class Builder {
        private String id;
        private String skuId;
        private String spuId;
        private String pictureUrl;
        private String sortNumber;
        private String createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder skuId(String val) {
            skuId = val;
            return this;
        }

        public Builder spuId(String val) {
            spuId = val;
            return this;
        }

        public Builder pictureUrl(String val) {
            pictureUrl = val;
            return this;
        }

        public Builder sortNumber(String val) {
            sortNumber = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public GoodsPictureVo build() {
            return new GoodsPictureVo(this);
        }
    }
}