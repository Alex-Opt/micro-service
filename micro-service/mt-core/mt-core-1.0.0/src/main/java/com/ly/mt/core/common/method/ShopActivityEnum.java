package com.ly.mt.core.common.method;

/**
 * @description 门店活动 service method 映射枚举
 *
 * @author panjingtian
 * @date 2019/6/11 4:06 PM
 */
public enum ShopActivityEnum {


    ;


    ShopActivityEnum(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

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


    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getDescribe() {
        return describe;
    }
}
