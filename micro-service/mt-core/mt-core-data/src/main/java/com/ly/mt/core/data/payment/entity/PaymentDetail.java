package com.ly.mt.core.data.payment.entity;

/**
 * payment_detail
 *
 * @author taoye
 */
public class PaymentDetail {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 收款方，如果支付方向为 1，则为平台，用-1标记。 如果是2，则为用户的Id
     */
    private String payee;
    /**
     * 付款方，如果支付方向为 1，则为用户的Id， 如果为 2，则为平台，用-1标记
     */
    private String payer;
    /**
     * 金额
     */
    private String money;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 支付方式
     */
    private String paymentType;
    /**
     * 支付单号
     */
    private String paymentNo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 备注，支付/退款失败的说明
     */
    private String remark;


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

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}