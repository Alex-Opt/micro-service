package com.ly.mt.payment.server.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description 属性配置
 * @Author taoye
 */
@Component
@RefreshScope
public class PaymentServerYml {
    /**
     * @Description 阿里支付
     * @Author taoye
     */
    @Value("${alipay.appId}")
    private String alipayAppId;
    @Value("${alipay.format}")
    private String alipayFormat;
    @Value("${alipay.charset}")
    private String alipayCharset;
    @Value("${alipay.signType}")
    private String alipaySignType;
    @Value("${alipay.api.server}")
    private String alipayApiServer;
    @Value("${alipay.api.notify}")
    private String alipayApiNotify;
    @Value("${alipay.key.private}")
    private String alipayKeyPrivate;
    @Value("${alipay.key.public}")
    private String alipayKeyPublic;
    @Value("${alipay.seller.id}")
    private String alipaySellerId;
    @Value("${alipay.seller.email}")
    private String alipaySellerEmail;


    /**
     * @Description 微信支付
     * @Author taoye
     */
    @Value("${wxpay.appId}")
    private String wxpayAppId;
    @Value("${wxpay.merchant.id}")
    private String wxpayMerchantId;
    @Value("${wxpay.merchant.key}")
    private String wxpayMerchantKey;
    @Value("${wxpay.h5.tradeType}")
    private String wxpayH5TradeType;
    @Value("${wxpay.h5.sceneInfo}")
    private String wxpayH5SceneInfo;
    @Value("${wxpay.api.notify}")
    private String wxpayApiNotify;
    @Value("${wxpay.api.unifiedOrder}")
    private String wxpayApiUnifiedOrder;
    @Value("${wxpay.api.refund}")
    private String wxpayApiRefund;
    @Value("${wxpay.api.orderQuery}")
    private String wxpayApiOrderQuery;


    public String getAlipayAppId() {
        return alipayAppId;
    }

    public String getAlipayFormat() {
        return alipayFormat;
    }

    public String getAlipayCharset() {
        return alipayCharset;
    }

    public String getAlipaySignType() {
        return alipaySignType;
    }

    public String getAlipayApiServer() {
        return alipayApiServer;
    }

    public String getAlipayApiNotify() {
        return alipayApiNotify;
    }

    public String getAlipayKeyPrivate() {
        return alipayKeyPrivate;
    }

    public String getAlipayKeyPublic() {
        return alipayKeyPublic;
    }

    public String getAlipaySellerId() {
        return alipaySellerId;
    }

    public String getAlipaySellerEmail() {
        return alipaySellerEmail;
    }

    public String getWxpayAppId() {
        return wxpayAppId;
    }

    public String getWxpayMerchantId() {
        return wxpayMerchantId;
    }

    public String getWxpayMerchantKey() {
        return wxpayMerchantKey;
    }

    public String getWxpayH5TradeType() {
        return wxpayH5TradeType;
    }

    public String getWxpayH5SceneInfo() {
        return wxpayH5SceneInfo;
    }

    public String getWxpayApiNotify() {
        return wxpayApiNotify;
    }

    public String getWxpayApiUnifiedOrder() {
        return wxpayApiUnifiedOrder;
    }

    public String getWxpayApiRefund() {
        return wxpayApiRefund;
    }

    public String getWxpayApiOrderQuery() {
        return wxpayApiOrderQuery;
    }
}