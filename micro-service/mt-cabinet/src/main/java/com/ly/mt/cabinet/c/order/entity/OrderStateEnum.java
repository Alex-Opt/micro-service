package com.ly.mt.cabinet.c.order.entity;

public enum OrderStateEnum {
    TO_BE_PAY("0","待支付"),
    COMPLETED("1","已完成"),
    OVERTIME("2","超时"),
    IN_REFUND("3","退款中"),
    REFUNDED("4","已退款"),
    OPEN_FAIL("5","支付完成,柜门开启失败"),
    PAY_FAIL("6","支付失败"),
    WAIT_OPEN("7","支付完成等待开启柜门"),

    ;
    private String key;
    private String des;


    OrderStateEnum(String key, String des){
        this.key = key;
        this.des = des;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
