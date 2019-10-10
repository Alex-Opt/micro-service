package com.ly.mt.core.data.gzg.entity;

/**
 * gzg_orders
 *
 * @author taoye
 */
public class GzgOrder {
    /**
     * 主键ID/订单编号
     */
    private String id;
    /**
     * 支付流水号
     */
    private String paymentNo;
    /**
     * 原始金额
     */
    private String orderOldMoney;
    /**
     * 实际金额
     */
    private String orderMoney;
    /**
     * 优惠金额
     */
    private String orderDiscountMoney;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 订单来源
     */
    private String orderSource;
    /**
     * 支付状态
     */
    private String paymentType;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 完成时间
     */
    private String orderFinishTime;
    /**
     * 舱门打开状态
     */
    private String cellState;
    /**
     * 格子柜编码
     */
    private String code;
    /**
     * 酒店ID
     */
    private String hotelId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getCellState() {
        return cellState;
    }

    public void setCellState(String cellState) {
        this.cellState = cellState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}