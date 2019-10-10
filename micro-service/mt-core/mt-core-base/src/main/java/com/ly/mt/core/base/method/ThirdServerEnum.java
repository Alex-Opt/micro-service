package com.ly.mt.core.base.method;

/**
 * @Description 第三方服务接口枚举
 * @Author taoye
 *//** @deprecated */
public enum ThirdServerEnum {
    FN_ORDER_CREATE("fNOrderServiceImpl", "orderCreate", "蜂鸟配送-订单推送");


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

    ThirdServerEnum(String serviceName, String functionName, String describe) {
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