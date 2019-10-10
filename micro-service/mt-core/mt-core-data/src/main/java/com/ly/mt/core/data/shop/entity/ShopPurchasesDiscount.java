package com.ly.mt.core.data.shop.entity;

/**
 * shop_purchases_discount
 *
 * @author taoye
 */
public class ShopPurchasesDiscount {
    /**
     * 主键ID
     */
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
     * 进货单编号
     */
    private String purchasesId;
    /**
     * 优惠券Id
     */
    private String shopCouponId;
    /**
     * 优惠类型
     *
     * @see com.ly.mt.core.base.dict.DiscountType
     */
    private String discountType;
    /**
     * 优惠金额
     */
    private String discountPrice;
    /**
     * 优惠折扣
     */
    private String discountRate;
    /**
     * 状态1：正常，2：异常（退货或其他情况）
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;


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

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
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
}