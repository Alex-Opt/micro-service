package com.ly.mt.core.logistics.entity;

/**
 * 快递100入参
 *
 * @author taoye
 */
public class Kd100Request {
    private String com;
    private String num;
    private String phone;

    public Kd100Request(String com, String num, String phone) {
        this.com = com;
        this.num = num;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}