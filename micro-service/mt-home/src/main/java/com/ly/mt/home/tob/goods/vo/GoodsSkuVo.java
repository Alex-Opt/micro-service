package com.ly.mt.home.tob.goods.vo;

import java.util.List;

public class GoodsSkuVo {
    private String id;
    private String name;
    private String code;
    private String spuId;
    private String skuStatus;
    private String barCode;
    private String marketPrice;
    private String wholesalePrice;
    private String attributes;
    private String productNo;
    private String createTime;
    private String nameGy;
    private String updateTime;
    private List<String> pictureList;

    public GoodsSkuVo() {
    }

    private GoodsSkuVo(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setCode(builder.code);
        setSpuId(builder.spuId);
        setSkuStatus(builder.skuStatus);
        setBarCode(builder.barCode);
        setMarketPrice(builder.marketPrice);
        setWholesalePrice(builder.wholesalePrice);
        setAttributes(builder.attributes);
        setProductNo(builder.productNo);
        setCreateTime(builder.createTime);
        setNameGy(builder.nameGy);
        setUpdateTime(builder.updateTime);
        setPictureList(builder.pictureList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        this.skuStatus = skuStatus;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNameGy() {
        return nameGy;
    }

    public void setNameGy(String nameGy) {
        this.nameGy = nameGy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }


    public static final class Builder {
        private String id;
        private String name;
        private String code;
        private String spuId;
        private String skuStatus;
        private String barCode;
        private String marketPrice;
        private String wholesalePrice;
        private String attributes;
        private String productNo;
        private String createTime;
        private String nameGy;
        private String updateTime;
        private List<String> pictureList;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder spuId(String val) {
            spuId = val;
            return this;
        }

        public Builder skuStatus(String val) {
            skuStatus = val;
            return this;
        }

        public Builder barCode(String val) {
            barCode = val;
            return this;
        }

        public Builder marketPrice(String val) {
            marketPrice = val;
            return this;
        }

        public Builder wholesalePrice(String val) {
            wholesalePrice = val;
            return this;
        }

        public Builder attributes(String val) {
            attributes = val;
            return this;
        }

        public Builder productNo(String val) {
            productNo = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public Builder nameGy(String val) {
            nameGy = val;
            return this;
        }

        public Builder updateTime(String val) {
            updateTime = val;
            return this;
        }

        public Builder pictureList(List<String> val) {
            pictureList = val;
            return this;
        }

        public GoodsSkuVo build() {
            return new GoodsSkuVo(this);
        }
    }


}
