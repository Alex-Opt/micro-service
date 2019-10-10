package com.ly.mt.mis.home.order.vo;

/**
 * MOTI到家-订单管理-订单表格
 *
 * @author taoye
 */
public class HomecOrderDatagridVo {
    /**
     * 订单ID
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 客户UID
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
     * 订单分类
     */
    private String orderType;
    /**
     * 订单分类名称
     */
    private String orderTypeName;
    /**
     * 配送服务
     */
    private String distributionId;
    /**
     * 配送服务名称
     */
    private String distributionName;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName;
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