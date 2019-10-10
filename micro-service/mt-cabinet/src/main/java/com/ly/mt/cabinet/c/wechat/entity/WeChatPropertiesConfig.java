package com.ly.mt.cabinet.c.wechat.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeChatPropertiesConfig {
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.merchantId}")
    private long merchantId;
    @Value("${wechat.merchantName}")
    private String merchantName;
    @Value("${wechat.merchantKey}")
    private String merchantKey;
    @Value("${wechat.notifyUrl}")
    private String notifyUrl;
    @Value("${wechat.returnUrl}")
    private  String returnUrl;
    @Value("${wechat.unifiedorder}")
    private String unifiedorder;
    @Value("${wechat.orderquery}")
    private String orderquery;
    @Value("${wechat.accessTocken}")
    private String accessTocken;
    @Value("${wechat.key}")
    private String key;


    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getUnifiedorder() {
        return unifiedorder;
    }

    public void setUnifiedorder(String unifiedorder) {
        this.unifiedorder = unifiedorder;
    }

    public String getOrderquery() {
        return orderquery;
    }

    public void setOrderquery(String orderquery) {
        this.orderquery = orderquery;
    }

    public String getAccessTocken() {
        return accessTocken;
    }

    public void setAccessTocken(String accessTocken) {
        this.accessTocken = accessTocken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
