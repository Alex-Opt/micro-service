package com.ly.mt.core.common.method;

/**
 * @author zhanglifeng
 * third-server 服务调用蜂鸟服务层接口枚举类
 */
public enum ThirdServerMethodEnum {

    FN_ORDER_CREATE("fnOrderServiceImpl", "orderCreate", "蜂鸟服务层-推送订单接口"),
    FN_ORDER_CANCEL("fnOrderServiceImpl", "orderCancel", "蜂鸟服务层-取消订单接口"),
    FN_ORDER_QUERY("fnOrderServiceImpl", "orderQuery", "蜂鸟服务层-查询订单，物流信息接口"),
    FN_CARRIER_QUERY("fnCarrierServiceImpl", "carrierQuery", "蜂鸟服务层-查询骑手位置接口"),

    KD100_QUERY_DELIVERY("kd100ServiceImpl","getDeliveryInfo","快递100-查询订单的物流信息"),

    AL_SMS_SEND("alSmsServiceImpl", "sendSms", "阿里-发送短信"),;
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

    ThirdServerMethodEnum(String serviceName, String functionName, String describe) {
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
