package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * 店铺优惠劵信息实体
 */
public class ShopCouponInfo implements Serializable {
    private static final long serialVersionUID = 3162897780789338017L;

    private String id;
    private String userId;
    private String shopId;
    private String discountPrice;
    private String discountRate;
    private String useStatus;
    private String startTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ShopCouponInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", discountRate='" + discountRate + '\'' +
                ", useStatus='" + useStatus + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
