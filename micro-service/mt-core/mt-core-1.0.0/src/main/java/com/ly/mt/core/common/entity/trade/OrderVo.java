package com.ly.mt.core.common.entity.trade;

/**
 * @author zhanglifeng
 * @description 订单模型-封装前端入参订单重要参数的模型，不需要extends BaseEntity
 * @date 2019-05-23
 */
public class OrderVo {
    /**
     * 买家Id
     */
    private String buyerId;

    /**
     * 买家留言
     */
    private String buyerMemo;

    /**
     * 卖家Id
     */
    private String sellerId;

    /**
     * 配送方式Id
     */
    private String distributionId;

    /**
     * 用户地址Id
     */
    private String addressId;

    /**
     * 订单原始金额
     */
    private String orderOldMoney;

    /**
     * 订单实付金额
     */
    private String orderMoney;

    /**
     * 订单优惠金额
     */
    private String orderDiscountMoney;

    /**
     * 只记录订单商品都享受的优惠金额
     */
    private String sharingDiscountMoney;

    /**
     * 运费
     */
    private String freight;

    /**
     * 订单来源 1：H5，2：小程序，3：APP
     */
    private String orderSource;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getOrderOldMoney() {
        return orderOldMoney;
    }

    public void setOrderOldMoney(String orderOldMoney) {
        this.orderOldMoney = orderOldMoney;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderDiscountMoney() {
        return orderDiscountMoney;
    }

    public void setOrderDiscountMoney(String orderDiscountMoney) {
        this.orderDiscountMoney = orderDiscountMoney;
    }

    public String getSharingDiscountMoney() {
        return sharingDiscountMoney;
    }

    public void setSharingDiscountMoney(String sharingDiscountMoney) {
        this.sharingDiscountMoney = sharingDiscountMoney;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }
}
