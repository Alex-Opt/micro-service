package com.ly.mt.center.third.kd100.entity;

/**
 * @Description 快递100返回json实体
 * @Author taoye
 */
public class Kd100Result {
    private String status;
    private String message;
    private String state;
    private String nu;
    private String com;
    private String ischeck;
    private String condition;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}