package com.ly.mt.mis.home.user.vo;

/**
 * HomebUserOrderBuyDatagridVo
 *
 * @author taoye
 */
public class HomebUserOrderBuyDatagridVo {
    /**
     * 订单ID/订单编号
     */
    private String id;
    /**
     * 商家UID
     */
    private String userId;
    /**
     * 商家注册时间
     */
    private String userRegistTime;
    /**
     * 店铺名称
     */
    private String shopName;
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
     * 实付金额
     */
    private String actualAmount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRegistTime() {
        return userRegistTime;
    }

    public void setUserRegistTime(String userRegistTime) {
        this.userRegistTime = userRegistTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }
}