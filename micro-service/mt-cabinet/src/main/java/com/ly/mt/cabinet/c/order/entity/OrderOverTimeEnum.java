package com.ly.mt.cabinet.c.order.entity;

public enum OrderOverTimeEnum {
    OVERTIME(180,"订单超时时间"),
    GOODS_LOCK(30,"商品锁定时间")

    ;
    private int time;
    private String des;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    OrderOverTimeEnum(int time, String des) {
        this.time = time;
        this.des = des;
    }
}
