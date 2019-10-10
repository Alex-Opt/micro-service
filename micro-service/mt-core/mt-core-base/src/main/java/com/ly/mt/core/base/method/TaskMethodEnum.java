package com.ly.mt.core.base.method;

/**
 * MtTask调用管易接口服务枚举类
 *//** @deprecated */
public enum TaskMethodEnum {

    GY_DELIVERY_INFO("orderTimingServiceImpl", "getDeliveryInfoByOrderId", "管易中心-查询订单发货物流信息接口"),

    GY_SEND_ORDER("orderTimingServiceImpl","sendTrade","管易中心-推送订单给管易系统");
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

    TaskMethodEnum(String serviceName, String functionName, String describe) {
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
