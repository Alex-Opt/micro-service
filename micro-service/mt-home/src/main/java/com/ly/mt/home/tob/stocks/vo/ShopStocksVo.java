package com.ly.mt.home.tob.stocks.vo;

import com.ly.mt.core.base.util.DateUtil;
import io.swagger.annotations.ApiModel;

@ApiModel
public class ShopStocksVo {
    private String id;
    private String shopId;
    private String spuId;
    private String skuId;
    private String skuName;
    private String nums;
    private String deliveryNums;
    private String originalPrice;
    private String price;
    private String sales;
    private String status;
    private String createTime;
    private String modifyTime;

    public ShopStocksVo() {
    }

    private ShopStocksVo(Builder builder) {
        setId(builder.id);
        setShopId(builder.shopId);
        setSpuId(builder.spuId);
        setSkuId(builder.skuId);
        setSkuName(builder.skuName);
        setNums(builder.nums);
        setDeliveryNums(builder.deliveryNums);
        setOriginalPrice(builder.originalPrice);
        setPrice(builder.price);
        setSales(builder.sales);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getDeliveryNums() {
        return deliveryNums;
    }

    public void setDeliveryNums(String deliveryNums) {
        this.deliveryNums = deliveryNums;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return DateUtil.getNowTimeStr();
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public static final class Builder {
        private String id;
        private String shopId;
        private String spuId;
        private String skuId;
        private String skuName;
        private String nums;
        private String deliveryNums;
        private String originalPrice;
        private String price;
        private String sales;
        private String status;
        private String createTime;
        private String modifyTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder shopId(String val) {
            shopId = val;
            return this;
        }

        public Builder spuId(String val) {
            spuId = val;
            return this;
        }

        public Builder skuId(String val) {
            skuId = val;
            return this;
        }

        public Builder skuName(String val) {
            skuName = val;
            return this;
        }

        public Builder nums(String val) {
            nums = val;
            return this;
        }

        public Builder deliveryNums(String val) {
            deliveryNums = val;
            return this;
        }

        public Builder originalPrice(String val) {
            originalPrice = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public Builder sales(String val) {
            sales = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public Builder modifyTime(String val) {
            modifyTime = val;
            return this;
        }

        public ShopStocksVo build() {
            return new ShopStocksVo(this);
        }
    }
}