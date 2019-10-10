package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * 商家进货优惠
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 22:40:40
 */
public class ShopPurchasesDiscount implements Serializable {

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

    public String getShopCouponId() {
        return shopCouponId;
    }

    public void setShopCouponId(String shopCouponId) {
        this.shopCouponId = shopCouponId;
    }

    @Override
    public String toString() {
        return "ShopPurchasesDiscount{" +
                "id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", userId='" + userId + '\'' +
                ", shopCouponId='" + shopCouponId + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
