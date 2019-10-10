package com.ly.mt.mall.base.config;

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
     * @Description appId：微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @Value("${wx.appId.publicAccount}")
    private String wxPublicAccountId;
    /**
     * 微信小程序appId
     */
    @Value("${wx.appId.appletAccount}")
    private String wxAppletAccountId;
    /**
     * app微信支付的appId
     */
    @Value("${wx.appId.appAccount}")
    private String wxAppAccountId;
    @Value("${wx.wapPay.sceneInfo}")
    private String wxWapPaySceneInfo;
    @Value("${wx.appId.merchantKey}")
    private String wxMerchantKey;

    public String getFilter() {
        return filter;
    }

    public int getDynamicCodeLength() {
        return dynamicCodeLength;
    }

    public String getWxPublicAccountId() {
        return wxPublicAccountId;
    }

    public String getWxAppletAccountId() {
        return wxAppletAccountId;
    }

    public String getWxAppAccountId() {
        return wxAppAccountId;
    }

    public String getWxWapPaySceneInfo() {
        return wxWapPaySceneInfo;
    }

    public String getWxMerchantKey() {
        return wxMerchantKey;
    }
}