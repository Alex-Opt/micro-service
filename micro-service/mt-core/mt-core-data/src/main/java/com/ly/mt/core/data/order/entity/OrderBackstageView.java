package com.ly.mt.core.data.order.entity;

/**
 * order_backstage_view
 * 客服系统订单视图
 *
 * @author taoye
 */
public class OrderBackstageView {
    /**
     * 订单ID
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 卖家ID
     */
    private String sellerId;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 商品名称
     */
    private String goodsNames;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 配送服务
     */
    private String distributionId;
    /**
     * 订单来源
     */
    private String orderSource;
    /**
     * 支付方式
     */
    private String paymentType;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 订单金额
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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