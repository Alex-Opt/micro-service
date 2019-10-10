package com.ly.mt.core.feign;

/**
 * 第三方服务接口
 *
 * @author taoye
 */
public enum ThirdCenterMethod {
    /**
     * 蜂鸟服务
     */
    FN_ORDER_CREATE("fNOrderServiceImpl", "orderCreate", "蜂鸟配送-订单推送"),
    FN_ORDER_CANCEL("fNOrderServiceImpl", "orderCancel", "蜂鸟配送-同步取消订单"),
    FN_ORDER_QUERY("fNOrderServiceImpl", "orderQuery", "蜂鸟配送-订单查询，物流信息"),
    FN_ORDER_NOTIFY("fNOrderServiceImpl", "orderNotify", "蜂鸟配送-状态回调"),
    FN_CARRIER_POSITION("fNCarrierServiceImpl", "carrierQuery", "蜂鸟配送-骑手位置"),

    /**
     * 个推服务
     */
    GT_PUSH_SINGLE("gTOrderServiceImpl", "pushSingle", "消息推送-推送给单个人"),
    GT_PUSH_LIST("gTOrderServiceImpl", "pushList", "消息推送-推送给多个人"),
    GT_PUSH_APP("gTOrderServiceImpl", "pushAPP", "消息推送-推送给按分组，按区域的app用户"),

    /**
     * 阿里服务
     */
    AL_SMS_SEND("alSmsServiceImpl", "sendSms", "阿里-发送短信"),
    AL_PAY_WAP_PAY("alPayServiceImpl", "wapPay", "阿里-发起WAP支付"),
    AL_PAY_APP_PAY("alPayServiceImpl", "appPay", "阿里-发起APP支付"),
    AL_PAY_NOTIFY("alPayServiceImpl", "notify", "阿里-支付回调"),
    AL_PAY_STATUS("alPayServiceImpl", "status", "阿里-支付状态查询"),

    /**
     * 微信服务
     */
    WX_PAY_PAY("wxPayServiceImpl", "pay", "微信-发起支付"),
    WX_PAY_NOTIFY("wxPayServiceImpl", "notify", "微信-支付回调"),
    WX_PAY_STATUS("wxPayServiceImpl", "status", "微信-支付状态查询"),
    WX_PAY_CONFIRM_AUTHORIZATION("wxPayServiceImpl", "confirmAuthorization", "微信-授权登陆接口"),
    WX_PAY_REFUND("wxPayServiceImpl", "refund", "微信-微信退款"),
    WX_SHARE_GETSIGN("wxPayServiceImpl", "getShareParam", "微信-分享签名"),
    WX_PAY_SIGN_AGAIN("wxPayServiceImpl", "paySignAgain", "微信-微信小程序支付需要的再次签名方法"),
    WX_PAY_WITHDRAWAL("wxPayServiceImpl", "withdrawal", "微信-商户支付个人"),
    WX_PAY_WITHDRAWAL_QUERY("wxPayServiceImpl", "queryWithdrawal", "查询微信-商户支付个人结果"),

    WX_TEMPLATE_MESSAGE_SEND("wxTemplateMessageSendServiceImpl","sendTemplateMessage","微信小程序-发送模版消息"),

    WX_APPLET_CODE_UNLIMIT("wxAppletCodeServiceImpl", "wxaCodeGetUnlimited", "微信小程序-生成分享二维码接口"),

    WX_GENERTATE_SIGNNATURE("wxGenerateSignatureServiceImpl", "generateWxSignature", "微信-公众号获取签名"),

    WX_APPLET_LOGIN_AUTH("wxLoginServiceImpl", "appletAuthCode2Session", "微信-小程序登陆授权"),
    WX_APPLET_ACCESS_TOKEN_GET("wxLoginServiceImpl", "getWxAppletAccessToken", "微信-获取小程序全局唯一后台接口调用凭据ACCESS_TOKEN接口"),
    /**
     * 管易业务
     */
    GY_GET_DELIVER_INFO("gyServiceImpl", "gyDeliverInfo", "管易拉取快递订单"),

    /**
     * 快递100业务
     */
    KD100_GET_EXPRESS_INFO("kd100ServiceImpl", "getDeliveryInfo", "获取物流信息"),

    /**
     * 用户实名认证
     */
    USER_CERTIFICATION_SCAN_IDCARD("userCertificateServiceImpl", "idCardScan", "身份证信息扫描"),
    USER_CERTIFICATION_THREE_ELEMENT_CHECK("userCertificateServiceImpl", "threeElementCheck", "三要素检测，姓名，身份证号，电话");

    /**
     * 接口名字
     */
    private final String serviceName;
    /**
     * 方法名字
     */
    private final String functionName;
    /**
     * 方法描述
     */
    private final String desc;

    ThirdCenterMethod(String serviceName, String functionName, String desc) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.desc = desc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }
}