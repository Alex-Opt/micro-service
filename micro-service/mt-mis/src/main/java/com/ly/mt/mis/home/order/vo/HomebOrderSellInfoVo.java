package com.ly.mt.mis.home.order.vo;

import java.util.List;

/**
 * MOTI商家端-订单管理-售卖订单-订单详情
 *
 * @author taoye
 */
public class HomebOrderSellInfoVo {
    /**
     * 订单信息
     */
    private String orderNo;
    private String createTime;
    private String orderStatusName;
    /**
     * 商品信息
     */
    private List<HomebOrderSellInfoGoodsVo> goods;
    /**
     * 支付信息
     */
    private String paymentTypeName;
    private String payTime;
    private String paymentNo;
    private String oldMoney;
    private String payMoney;
    private String discountRate;
    private String denomination;
    private String freight;
    /**
     * 物流信息
     */
    private String logisticsNo;
    private String logisticsStatusName;
    private String rider;
    /**
     * 客户信息
     */
    private String buyerId;
    private String buyerRegisterTime;
    private String buyerName;
    private String buyerMobile;
    private String buyerMemo;
    private String receivingAddress;
    /**
     * 商家信息
     */
    private String shopName;
    private String sellerId;
    private String sellerRegisterTime;
    private String sellerName;
    private String shopAddress;
    private String sellerMobile;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public List<HomebOrderSellInfoGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<HomebOrderSellInfoGoodsVo> goods) {
        this.goods = goods;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsStatusName() {
        return logisticsStatusName;
    }

    public void setLogisticsStatusName(String logisticsStatusName) {
        this.logisticsStatusName = logisticsStatusName;
    }

    public String getRider() {
        return rider;
    }

    public void setRider(String rider) {
        this.rider = rider;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerRegisterTime() {
        return buyerRegisterTime;
    }

    public void setBuyerRegisterTime(String buyerRegisterTime) {
        this.buyerRegisterTime = buyerRegisterTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerRegisterTime() {
        return sellerRegisterTime;
    }

    public void setSellerRegisterTime(String sellerRegisterTime) {
        this.sellerRegisterTime = sellerRegisterTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }
}