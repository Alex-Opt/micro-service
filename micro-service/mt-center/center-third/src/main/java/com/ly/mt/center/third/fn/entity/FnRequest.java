package com.ly.mt.center.third.fn.entity;

/**
 * @Description 蜂鸟实体公共参数
 * @Author taoye
 */
public class FnRequest {
    private String app_id;
    private String salt;
    private String signature;
    private String data;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}