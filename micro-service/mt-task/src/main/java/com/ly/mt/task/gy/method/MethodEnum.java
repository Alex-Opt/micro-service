package com.ly.mt.task.gy.method;

/**
 * @Description 管易接口方法枚举类
 * @Author taoye
 */
public enum MethodEnum {
    GY_ERP_TRADE_DELIVERYS_GET("gy.erp.trade.deliverys.get", "订单物流查询");

    /**
     * @Description 方法名字
     */
    private final String methodName;

    /**
     * @Description 方法描述
     */
    private final String methodDescribe;

    MethodEnum(String methodName, String methodDescribe) {
        this.methodName = methodName;
        this.methodDescribe = methodDescribe;
    }

    public String getMethodName() {
        return methodName;
    }
}