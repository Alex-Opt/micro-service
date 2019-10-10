package com.ly.mt.cabinet.c.alipay.entity;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "alipay")
public class AliPayProperties {
    /**
     * 支付宝gatewayUrl
     */
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;
    /**
     * 商户应用id
     */
    @Value("${alipay.appid}")
    private String appid;
    /**
     * RSA私钥，用于对商户请求报文加签
     */
    @Value("${alipay.appPrivateKey}")
    private String appPrivateKey;
    /**
     * 支付宝RSA公钥，用于验签支付宝应答
     */
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    /**
     * 签名类型
     */
    @Value("alipay.signType")
    private String signType;
    /**
     * 格式
     */
    @Value("${alipay.formate}")
    private String formate;
    /**
     * 编码
     */
    @Value("${alipay.charset}")
    private String charset;
    /**
     * 同步地址
     */
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    /**
     * 异步地址
     */
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    @Value("${alipay.publicKey}")
    private String publicKey;
    /**
     * 最大查询次数
     */
    private static int maxQueryRetry;
    /**
     * 查询间隔（毫秒）
     */
    private static long queryDuration;
    /**
     * 最大撤销次数
     */
    private static int maxCancelRetry;
    /**
     * 撤销间隔（毫秒）
     */
    private static long cancelDuration;

    private AliPayProperties() {
    }

    private String getKeyDescription(String key) {
        int showLength = 6;
        if (StringUtils.isNotEmpty(key) && key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength)).append("******")
                    .append(key.substring(key.length() - showLength)).toString();
        }
        return null;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public String getSignType() {
        return signType;
    }

    public String getFormate() {
        return formate;
    }

    public String getCharset() {
        return charset;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }
}
