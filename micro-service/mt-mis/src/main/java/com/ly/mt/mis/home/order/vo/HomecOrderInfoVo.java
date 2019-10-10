package com.ly.mt.mis.home.order.vo;

import java.util.List;

/**
 * MOTI到家-订单管理-订单详情
 *
 * @author taoye
 */
public class HomecOrderInfoVo {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 订单分类
     */
    private String orderTypeName;
    /**
     * 订单状态
     */
    private String orderStatusName;


    /**
     * 商品信息
     */
    private List<HomecOrderInfoGoodsVo> goods;


    /**
     * 支付方式
     */
    private String paymentTypeName;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 交易单号
     */
    private String paymentNo;
    /**
     * 原始金额
     */
    private String oldMoney;
    /**
     * 优惠券
     */
    private String discountRate;
    /**
     * 优惠金额
     */
    private String denomination;
    /**
     * 运费
     */
    private String freight;
    /**
     * 实际支付金额
     */
    private String payMoney;


    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 配送服务
     */
    private String distributionName;
    /**
     * 物流单号
     */
    private String logisticsNo;
    /**
     * 物流状态
     */
    private String logisticsStateName;
    /**
     * 物流公司信息
     */
    private String logisticsName;
    /**
     * 骑手
     */
    private String rider;


    /**
     * 客户UID
     */
    private String buyerId;
    /**
     * 客户注册时间
     */
    private String buyerRegisterTime;
    /**
     * 客户姓名
     */
    private String buyerName;
    /**
     * 联系方式
     */
    private String buyerMobile;
    /**
     * 客户留言
     */
    private String buyerMemo;
    /**
     * 收货地址
     */
    private String receivingAddress;


    /**
     * 商家UID
     */
    private String sellerId;
    /**
     * 商家注册时间
     */
    private String sellerRegisterTime;
    /**
     * 商家姓名
     */
    private String sellerName;
    /**
     * 店铺地址
     */
    private String shopAddress;
    /**
     * 联系方式
     */
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

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public List<HomecOrderInfoGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<HomecOrderInfoGoodsVo> goods) {
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

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsStateName() {
        return logisticsStateName;
    }

    public void setLogisticsStateName(String logisticsStateName) {
        this.logisticsStateName = logisticsStateName;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
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
}