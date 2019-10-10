package com.ly.mt.core.base.entity.purchase;

import java.io.Serializable;
/**
 * 订单确认页优惠券信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-25 23:13:13
 */
/** @deprecated */
public class ShopCouponVO implements Serializable {

    private static final long serialVersionUID = -4220481967512515190L;

    /**
     * 优惠券id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 折扣率
     */
    private String discountRate;

    /**
     * 优惠金额
     */
    private String discountPrice;

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

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "ShopCouponVO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", discountRate='" + discountRate + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                '}';
    }
}
