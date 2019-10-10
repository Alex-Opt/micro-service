package com.ly.mt.activity.advertisement.vo;

/**
 * @author wanglong
 * @Description 支付订单vo
 * @Author taoye
 */
public class PayOrderVo {
    private String orderMoney;
    private String sellerId;
    private String buyerId;

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
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
}