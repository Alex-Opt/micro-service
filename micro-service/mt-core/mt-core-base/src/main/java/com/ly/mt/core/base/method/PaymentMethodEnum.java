package com.ly.mt.core.base.method;

/**
 * @Description 支付接口枚举
 * @Author taoye
 *//** @deprecated */
public enum PaymentMethodEnum {
    WX_PAY_STATUS("wxPayServiceImpl", "status", "微信支付状态"),
    WX_PAY_NOTIFY("wxPayServiceImpl", "notify", "微信支付回调"),
    WX_PAY_WAP("wxPayServiceImpl", "wapPay", "微信WAP支付"),
    WX_REFUND_WAP("wxPayServiceImpl", "refund", "微信WAP退款"),

    ALI_PAY_STATUS("aliPayServiceImpl", "status", "阿里支付状态"),
    ALI_PAY_NOTIFY("aliPayServiceImpl", "notify", "阿里支付回调"),
    ALI_PAY_WAP("aliPayServiceImpl", "wapPay", "阿里WAP支付");
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

    PaymentMethodEnum(String serviceName, String functionName, String describe) {
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