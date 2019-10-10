package com.ly.mt.core.fegin.method;

/**
 * @Description 第三方服务接口
 * @Author taoye
 */
public enum ThirdCenterMethod {
    /**
     * @Description 蜂鸟服务
     */
    FN_ORDER_CREATE("fNOrderServiceImpl", "orderCreate", "蜂鸟配送-订单推送"),
    FN_ORDER_CANCEL("fNOrderServiceImpl", "orderCancel", "蜂鸟配送-同步取消订单"),
    FN_ORDER_QUERY("fNOrderServiceImpl", "orderQuery", "蜂鸟配送-订单查询，物流信息"),
    FN_ORDER_NOTIFY("fNOrderServiceImpl", "orderNotify", "蜂鸟配送-状态回调"),
    FN_CARRIER_POSITION("fNCarrierServiceImpl", "carrierQuery", "蜂鸟配送-骑手位置"),

    /**
     * @Description 个推服务
     */
    GT_PUSH_SINGLE("gTOrderServiceImpl", "pushSingle", "消息推送-推送给单个人"),
    GT_PUSH_LIST("gTOrderServiceImpl", "pushList", "消息推送-推送给多个人"),
    GT_PUSH_APP("gTOrderServiceImpl", "pushAPP", "消息推送-推送给按分组，按区域的app用户"),

    /**
     * @Description 阿里服务
     */
    AL_SMS_SEND("alSmsServiceImpl", "sendSms", "阿里-发送短信"),
    AL_PAY_WAP_PAY("alPayServiceImpl", "wapPay", "阿里-发起WAP支付"),
    AL_PAY_NOTIFY("alPayServiceImpl", "notify", "阿里-支付回调"),
    AL_PAY_STATUS("alPayServiceImpl", "status", "阿里-支付状态查询"),

    /**
     * @Description 微信服务
     */
    WX_PAY_PAY("wxPayServiceImpl", "pay", "微信-发起支付"),
    WX_PAY_NOTIFY("wxPayServiceImpl", "notify", "微信-支付回调"),
    WX_PAY_STATUS("wxPayServiceImpl", "status", "微信-支付状态查询"),
    WX_PAY_CONFIRM_AUTHORIZATION("wxPayServiceImpl","confirmAuthorization","微信-授权登陆接口");


    /**
     * @Description 接口名字
     */
    private final String serviceName;
    /**
     * @Description 方法名字
     */
    private final String functionName;
    /**
     * @Description 方法描述
     */
    private final String describe;

    ThirdCenterMethod(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }
}