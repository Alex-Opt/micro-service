package com.ly.mt.cabinet.c.response;

public class PayInfoResp {
    //订单编号
    private long orderNo;
    //支付时间
    private String payTime;
    //实付金额
    private double amount;
    //优惠金额
    private Double disCountMoney;
    //订单关闭时间
    private String closeTime;


    public Double getDisCountMoney() {
        return disCountMoney;
    }

    public void setDisCountMoney(Double disCountMoney) {
        this.disCountMoney = disCountMoney;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
