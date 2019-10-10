package com.ly.mt.mis.home.order.vo;

/**
 * MOTI商家端-订单管理-售卖订单
 *
 * @author taoye
 */
public class HomebOrderSellDatagridVo {
    /**
     * 订单ID
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户UID
     */
    private String buyerId;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 商品信息
     */
    private String goodsNames;
    /**
     * 支付方式
     */
    private String paymentType;
    /**
     * 支付方式名称
     */
    private String paymentTypeName;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 订单状态名称
     */
    private String orderStatusName;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 实付金额
     */
    private String orderMoney;


    public String getId() {
        return id;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {
        this.goodsNames = goodsNames;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }
}