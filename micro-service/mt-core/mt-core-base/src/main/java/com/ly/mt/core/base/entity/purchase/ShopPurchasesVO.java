package com.ly.mt.core.base.entity.purchase;

import java.io.Serializable;
/**
 * 最新购买情况
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 22:20:20
 */
/** @deprecated */
public class ShopPurchasesVO implements Serializable {

    private static final long serialVersionUID = 5364722578120850365L;

    /**
     * 买家Id
     */
    private String userId;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 订单支付时间
     */
    private String payedTime;

    /**
     * 购买数量
     */
    private String nums;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(String payedTime) {
        this.payedTime = payedTime;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "ShopPurchasesVO{" +
                "userId='" + userId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", payedTime='" + payedTime + '\'' +
                ", nums='" + nums + '\'' +
                '}';
    }
}
