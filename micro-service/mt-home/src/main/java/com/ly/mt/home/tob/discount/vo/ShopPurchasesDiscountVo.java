package com.ly.mt.home.tob.discount.vo;

import java.io.Serializable;

/**
 * 商家进货优惠
 *
 * @author: linan
 * @date: 2019/9/16
 **/
public class ShopPurchasesDiscountVo implements Serializable {

    private static final long serialVersionUID = 7015771472147984701L;

    private String id;

    /**
     * 店铺编号
     */
    private String shopId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 进货单号
     */
    private String purchasesId;

    /**
     * 优惠券Id
     */
    private String shopCouponId;

    /**
     * 优惠类型
     */
    private String discountType;

    /**
     * 优惠金额
     */
    private String discountPrice;

    /**
     * 状态 1：正常，2：异常（退货或其他情况）
     */
    private String status;

    private String createTime;

    private String modifyTime;

    private String discountRate;

    private String remarks;

    public ShopPurchasesDiscountVo() {
    }

    private ShopPurchasesDiscountVo(Builder builder) {
        setId(builder.id);
        setShopId(builder.shopId);
        setUserId(builder.userId);
        setPurchasesId(builder.purchasesId);
        setShopCouponId(builder.shopCouponId);
        setDiscountType(builder.discountType);
        setDiscountPrice(builder.discountPrice);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setModifyTime(builder.modifyTime);
        setDiscountRate(builder.discountRate);
        setRemarks(builder.remarks);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurchasesId() {
        return purchasesId;
    }

    public void setPurchasesId(String purchasesId) {
        this.purchasesId = purchasesId;
    }

    public String getShopCouponId() {
        return shopCouponId;
    }

    public void setShopCouponId(String shopCouponId) {
        this.shopCouponId = shopCouponId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
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
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static final class Builder {
        private String id;
        private String shopId;
        private String userId;
        private String purchasesId;
        private String shopCouponId;
        private String discountType;
        private String discountPrice;
        private String status;
        private String createTime;
        private String modifyTime;
        private String discountRate;
        private String remarks;

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

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder purchasesId(String val) {
            purchasesId = val;
            return this;
        }

        public Builder shopCouponId(String val) {
            shopCouponId = val;
            return this;
        }

        public Builder discountType(String val) {
            discountType = val;
            return this;
        }

        public Builder discountPrice(String val) {
            discountPrice = val;
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

        public Builder discountRate(String val) {
            discountRate = val;
            return this;
        }

        public Builder remarks(String val) {
            remarks = val;
            return this;
        }

        public ShopPurchasesDiscountVo build() {
            return new ShopPurchasesDiscountVo(this);
        }
    }
}
