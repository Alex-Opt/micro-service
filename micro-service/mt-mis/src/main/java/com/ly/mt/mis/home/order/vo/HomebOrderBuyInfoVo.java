package com.ly.mt.mis.home.order.vo;

import java.util.List;

/**
 * MOTI商家端-订单管理-进货订单-订单详情
 *
 * @author taoye
 */
public class HomebOrderBuyInfoVo {
    /**
     * 订单信息
     */
    private String orderNo;
    private String createTime;
    private String orderStatusName;
    /**
     * 商品信息
     */
    private List<HomebOrderBuyInfoGoodsVo> goods;
    /**
     * 支付信息
     */
    private String paymentTypeName;
    private String payTime;
    private String paymentNo;
    private String oldMoney;
    private String discountRate;
    private String denomination;
    private String freight;
    private String payMoney;
    /**
     * 物流信息
     */
    private String distributeTypeName;
    private String logisticsName;
    private String logisticsNo;
    private String logisticsStateName;
    private String receivingAddress;
    /**
     * 商家信息
     */
    private String userId;
    private String userRegisterTime;
    private String userName;
    private String shopAddress;
    private String userMobile;
    private String shopName;


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

    public List<HomebOrderBuyInfoGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<HomebOrderBuyInfoGoodsVo> goods) {
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

    public String getDistributeTypeName() {
        return distributeTypeName;
    }

    public void setDistributeTypeName(String distributeTypeName) {
        this.distributeTypeName = distributeTypeName;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
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

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}