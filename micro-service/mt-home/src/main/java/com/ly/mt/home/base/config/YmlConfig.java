package com.ly.mt.home.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YmlConfig {
    /**
     * @Description filter
     */
    @Value("${filter}")
    private String filter;

    /**
     * @Description 动态验证码长度
     */
    @Value("${dynamic.code.length}")
    private int dynamicCodeLength;

    /**
     * @Description 微信WAP支付
     */
    @Value("${wx.appId.publicAccount}")
    private String wxPublicAccountId;
    @Value("${wx.appId.merchantKey}")
    private String wxMerchantKey;
    @Value("${wx.wapPay.sceneInfo}")
    private String wxWapPaySceneInfo;


    public String getFilter() {
        return filter;
    }

    public int getDynamicCodeLength() {
        return dynamicCodeLength;
    }

    public String getWxPublicAccountId() {
        return wxPublicAccountId;
    }

    public String getWxWapPaySceneInfo() {
        return wxWapPaySceneInfo;
    }

    public String getWxMerchantKey() {
        return wxMerchantKey;
    }
}