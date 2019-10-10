package com.ly.mt.core.base.entity.payment;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 支付处理表对应实体
 * @author zhanglifeng
 * @String 2019-06-03
 *//** @deprecated */
public class PaymentDeal extends BaseEntity {
    private String id;

    private String orderId;

    private String buyerId;

    private String paymentPrice;

    private String dealStatus;

    private String createTime;

    private String modifyTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
