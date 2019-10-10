package com.ly.mt.open.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * wechat公众号配置信息
 * @author pjt
 */
@Component
@ConfigurationProperties("wechat")
public class WechatAccountConfig {

    //公众平台id
    private String mpAppId;

    //公众平台密钥
    private String mpAppSecret;

    //微信支付异步通知地址
    private String notifyUrl;

    //内部认证url地址
    private String innerAuthUrl;


    public WechatAccountConfig() {
    }

    public String getMpAppId() {
        return mpAppId;
    }

    public void setMpAppId(String mpAppId) {
        this.mpAppId = mpAppId;
    }

    public String getMpAppSecret() {
        return mpAppSecret;
    }

    public void setMpAppSecret(String mpAppSecret) {
        this.mpAppSecret = mpAppSecret;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getInnerAuthUrl() {
        return innerAuthUrl;
    }

    public void setInnerAuthUrl(String innerAuthUrl) {
        this.innerAuthUrl = innerAuthUrl;
    }
}
