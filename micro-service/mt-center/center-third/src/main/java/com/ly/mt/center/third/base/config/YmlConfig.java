package com.ly.mt.center.third.base.config;

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
     * @Description 蜂鸟配送
     * @Author taoye
     */
    @Value("${third.fn.appId}")
    private String fnAppId;
    @Value("${third.fn.secretKey}")
    private String fnSecretKey;
    @Value("${third.fn.url}")
    private String fnUrl;
    @Value("${third.fn.notifyUrl}")
    private String fnNotifyUrl;
    @Value("${third.fn.storeCode}")
    private String fnStoreCode;

    /**
     * @Description 到家B个推配置
     * @Author zhanglifeng
     */
    @Value("${third.gt.homeB.appId}")
    public String gtAppId;
    @Value("${third.gt.homeB.appKey}")
    public String gtAppKey;
    @Value("${third.gt.homeB.masterSecret}")
    public String gtMasterSecret;
    @Value("${third.gt.homeB.host}")
    public String gtHost;

    /**
     * @Description 快递一百配置
     * @Author zhanglifeng
     */
    @Value("${third.kd100.kdCustomer}")
    public String kd100Customer;
    @Value("${third.kd100.kdKey}")
    public String kd100Key;
    @Value("${third.kd100.kdUri}")
    public String kd100Uri;

    /**
     * @Description 阿里sms
     * @Author taoye
     */
    @Value("${third.al.sms.domain}")
    private String alSmsDomain;
    @Value("${third.al.sms.version}")
    private String alSmsVersion;
    @Value("${third.al.sms.action}")
    private String alSmsAction;
    @Value("${third.al.sms.accessKeyId}")
    private String alSmsAccessKeyId;
    @Value("${third.al.sms.accessSecret}")
    private String alSmsAccessSecret;

    /**
     * @Description 阿里oss
     * @Author taoye
     */
    @Value("${third.al.oss.bucketName}")
    private String alOssBucketName;
    @Value("${third.al.oss.accessKeyID}")
    private String alOssAccessKeyID;
    @Value("${third.al.oss.accessKeySecret}")
    private String alOssAccessKeySecret;
    @Value("${third.al.oss.endpoint}")
    private String alOssEndpoint;
    @Value("${third.al.oss.baseURL}")
    private String alOssBaseURL;

    /**
     * @Description 阿里pay
     * @Author taoye
     */
    @Value("${third.al.pay.appId}")
    private String alPayAppId;
    @Value("${third.al.pay.format}")
    private String alPayFormat;
    @Value("${third.al.pay.charset}")
    private String alPayCharset;
    @Value("${third.al.pay.signType}")
    private String alPaySignType;
    @Value("${third.al.pay.api.server}")
    private String alPayApiServer;
    @Value("${third.al.pay.api.notify}")
    private String alPayApiNotify;
    @Value("${third.al.pay.key.private}")
    private String alPayKeyPrivate;
    @Value("${third.al.pay.key.public}")
    private String alPayKeyPublic;
    @Value("${third.al.pay.seller.id}")
    private String alPaySellerId;
    @Value("${third.al.pay.seller.email}")
    private String alPaySellerEmail;

    /**
     * @Description 微信支付
     * @Author taoye
     */
    @Value("${third.wx.pay.appId}")
    private String wxPayAppId;
    @Value("${third.wx.pay.appSecret}")
    private String wxPayAppSecret;
    @Value("${third.wx.pay.merchant.id}")
    private String wxPayMerchantId;
    @Value("${third.wx.pay.merchant.key}")
    private String wxPayMerchantKey;
    @Value("${third.wx.pay.h5.tradeType}")
    private String wxPayH5TradeType;
    @Value("${third.wx.pay.h5.sceneInfo}")
    private String wxPayH5SceneInfo;
    @Value("${third.wx.pay.api.notify}")
    private String wxPayApiNotify;
    @Value("${third.wx.pay.api.unifiedOrder}")
    private String wxPayApiUnifiedOrder;
    @Value("${third.wx.pay.api.refund}")
    private String wxPayApiRefund;
    @Value("${third.wx.pay.api.orderQuery}")
    private String wxPayApiOrderQuery;
    /**
     * 通过code换取网页授权access_token接口地址
     */
    @Value("${third.wx.pay.api.confirmAccessUri}")
    private String wxPayConfirmAccessUri;

    /**
     * 微信小程序登录授权接口地址
     */
    @Value("${third.wx.pay.api.jsCode2SessionUri}")
    private String jsCode2SessionUri;

    /**
     *生成微信二维码接口地址
     */
    @Value("${third.wx.pay.api.wxaCodeUnlimitUri}")
    private String wxaCodeUnlimitUri;

    /**
     * 刷新access_token接口地址
     */
    @Value("${third.wx.pay.api.refreshTokenUri}")
    private String wxPayRefreshTokenUri;

    @Value("${third.wx.pay.api.accessTokenUri}")
    private  String accessTokenUri;

    @Value("${third.wx.pay.api.jsapiTicketUri}")
    private String jsapiTicketUri;

    /**
     * 到家C微信小程序appId
     */
    @Value("${third.wx.pay.daoJiaCAppletAppId}")
    private String wxDaoJiaCAppletPayAppId;

    /**
     * 到家C微信小程序secret
     */
    @Value("${third.wx.pay.daoJiaCAppletAppSecret}")
    private String wxDaoJiaCAppletPayAppSecret;

    @Value("${third.wx.pay.api.templateMessageSend}")
    private String templateMessageSendUri;

    /**
     * gy配置
     */
    @Value("${gy.serverUrl}")
    private String gyServerUrl;
    @Value("${gy.appKey}")
    private String gyAppKey;
    @Value("${gy.secret}")
    private String gySecret;
    @Value("${gy.sessionKey}")
    private String gySessionKey;
    @Value("${gy.shopIdCommon}")
    private String gyShopIdCommon;
    @Value("${gy.shopIdNextDay}")
    private String gyShopIdNextDay;
    @Value("${gy.warehouseId}")
    private String gyWarehouseId;

    /**
     * 实名认证配置
     * @return
     */
    @Value("${third.yyhd.identityCard.secret}")
    private String yyhdSercet;
    @Value("${third.yyhd.identityCard.accessKey}")
    private String yyhdAccessKey;
    @Value("${third.yyhd.api.ocrScanUrl}")
    private String yyhdOcrScanUrl;
    @Value("${third.yyhd.api.threeElementDetectionUrl}")
    private String yyhdThreeElementDetectionUrl;
    @Value("${third.wx.pay.api.transfers}")
    private String transfers;
    @Value("${third.wx.pay.api.gettransferinfo}")
    private String gettransferinfo;

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public String getJsapiTicketUri() {
        return jsapiTicketUri;
    }

    public String getFilter() {
        return filter;
    }

    public String getFnAppId() {
        return fnAppId;
    }

    public String getFnSecretKey() {
        return fnSecretKey;
    }

    public String getFnUrl() {
        return fnUrl;
    }

    public String getFnNotifyUrl() {
        return fnNotifyUrl;
    }

    public String getFnStoreCode() {
        return fnStoreCode;
    }

    public String getGtAppId() {
        return gtAppId;
    }

    public String getGtAppKey() {
        return gtAppKey;
    }

    public String getGtMasterSecret() {
        return gtMasterSecret;
    }

    public String getGtHost() {
        return gtHost;
    }

    public String getKd100Customer() {
        return kd100Customer;
    }

    public String getKd100Key() {
        return kd100Key;
    }

    public String getKd100Uri() {
        return kd100Uri;
    }

    public String getAlSmsDomain() {
        return alSmsDomain;
    }

    public String getAlSmsVersion() {
        return alSmsVersion;
    }

    public String getAlSmsAction() {
        return alSmsAction;
    }

    public String getAlSmsAccessKeyId() {
        return alSmsAccessKeyId;
    }

    public String getAlSmsAccessSecret() {
        return alSmsAccessSecret;
    }

    public String getAlOssBucketName() {
        return alOssBucketName;
    }

    public String getAlOssAccessKeyID() {
        return alOssAccessKeyID;
    }

    public String getAlOssAccessKeySecret() {
        return alOssAccessKeySecret;
    }

    public String getAlOssEndpoint() {
        return alOssEndpoint;
    }

    public String getAlOssBaseURL() {
        return alOssBaseURL;
    }

    public String getAlPayAppId() {
        return alPayAppId;
    }

    public String getAlPayFormat() {
        return alPayFormat;
    }

    public String getAlPayCharset() {
        return alPayCharset;
    }

    public String getAlPaySignType() {
        return alPaySignType;
    }

    public String getAlPayApiServer() {
        return alPayApiServer;
    }

    public String getAlPayApiNotify() {
        return alPayApiNotify;
    }

    public String getAlPayKeyPrivate() {
        return alPayKeyPrivate;
    }

    public String getAlPayKeyPublic() {
        return alPayKeyPublic;
    }

    public String getAlPaySellerId() {
        return alPaySellerId;
    }

    public String getAlPaySellerEmail() {
        return alPaySellerEmail;
    }

    public String getWxPayAppId() {
        return wxPayAppId;
    }

    public String getWxPayAppSecret() {
        return wxPayAppSecret;
    }

    public String getWxPayConfirmAccessUri() {
        return wxPayConfirmAccessUri;
    }

    public String getWxPayRefreshTokenUri() {
        return wxPayRefreshTokenUri;
    }

    public String getWxPayMerchantId() {
        return wxPayMerchantId;
    }

    public String getWxPayMerchantKey() {
        return wxPayMerchantKey;
    }

    public String getWxPayH5TradeType() {
        return wxPayH5TradeType;
    }

    public String getWxPayH5SceneInfo() {
        return wxPayH5SceneInfo;
    }

    public String getWxPayApiNotify() {
        return wxPayApiNotify;
    }

    public String getWxPayApiUnifiedOrder() {
        return wxPayApiUnifiedOrder;
    }

    public String getWxPayApiRefund() {
        return wxPayApiRefund;
    }

    public String getWxPayApiOrderQuery() {
        return wxPayApiOrderQuery;
    }

    public String getJsCode2SessionUri() {
        return jsCode2SessionUri;
    }

    public String getGyServerUrl() {
        return gyServerUrl;
    }

    public String getGyAppKey() {
        return gyAppKey;
    }

    public String getGySecret() {
        return gySecret;
    }

    public String getGySessionKey() {
        return gySessionKey;
    }

    public String getGyShopIdCommon() {
        return gyShopIdCommon;
    }

    public String getGyShopIdNextDay() {
        return gyShopIdNextDay;
    }

    public String getGyWarehouseId() {
        return gyWarehouseId;
    }

    public String getWxDaoJiaCAppletPayAppId() {
        return wxDaoJiaCAppletPayAppId;
    }

    public String getWxDaoJiaCAppletPayAppSecret() {
        return wxDaoJiaCAppletPayAppSecret;
    }

    public String getYyhdSercet() {
        return yyhdSercet;
    }

    public String getYyhdAccessKey() {
        return yyhdAccessKey;
    }

    public String getYyhdOcrScanUrl() {
        return yyhdOcrScanUrl;
    }

    public String getYyhdThreeElementDetectionUrl() {
        return yyhdThreeElementDetectionUrl;
    }

    public String getTransfers() {
        return transfers;
    }

    public String getGettransferinfo() {
        return gettransferinfo;
    }

    public String getTemplateMessageSendUri() {
        return templateMessageSendUri;
    }

    public String getWxaCodeUnlimitUri() {
        return wxaCodeUnlimitUri;
    }
}