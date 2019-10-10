package com.ly.mt.core.common.entity.trade;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * @Author zhanglifeng
 * @Description 订单主表  实体类
 * @String 2019/5/21
 */
public class TradeOrder extends BaseEntity {
    private String id;

    private String orderNo;

    private String buyerId;

    private String buyerMemo;

    private String shopId;

    private String gyWarehouseCode;

    private String gyShopCode;

    private String sellerId;

    private String orderOldMoney;

    private String orderMoney;

    private String orderDiscountMoney;

    private String distributionId;

    private String logisticsCode;

    private String gyLogisticsCode;

    private String freight;

    private String orderStatus;

    private String orderSource;

    private String orderYn;

    private String cancleTime;

    private String isRefund;

    private String refundTime;

    private String paymentType;

    private String pushStatus;

    private String orderType;

    private String addressId;

    private String autoReceiveTime;

    private String autoCancelTime;

    private String orderSnapshootKey;

    private String locked;

    private String lockTime;

    private String deliveryTime;

    private String createTime;

    private String payTime;

    private String orderFinishTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGyWarehouseCode() {
        return gyWarehouseCode;
    }

    public void setGyWarehouseCode(String gyWarehouseCode) {
        this.gyWarehouseCode = gyWarehouseCode;
    }

    public String getGyShopCode() {
        return gyShopCode;
    }

    public void setGyShopCode(String gyShopCode) {
        this.gyShopCode = gyShopCode;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getGyLogisticsCode() {
        return gyLogisticsCode;
    }

    public void setGyLogisticsCode(String gyLogisticsCode) {
        this.gyLogisticsCode = gyLogisticsCode == null ? null : gyLogisticsCode.trim();
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderYn() {
        return orderYn;
    }

    public void setOrderYn(String orderYn) {
        this.orderYn = orderYn;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAutoReceiveTime() {
        return autoReceiveTime;
    }

    public void setAutoReceiveTime(String autoReceiveTime) {
        this.autoReceiveTime = autoReceiveTime;
    }

    public String getAutoCancelTime() {
        return autoCancelTime;
    }

    public void setAutoCancelTime(String autoCancelTime) {
        this.autoCancelTime = autoCancelTime;
    }

    public String getOrderSnapshootKey() {
        return orderSnapshootKey;
    }

    public void setOrderSnapshootKey(String orderSnapshootKey) {
        this.orderSnapshootKey = orderSnapshootKey == null ? null : orderSnapshootKey.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(String cancleTime) {
        this.cancleTime = cancleTime;
    }
}