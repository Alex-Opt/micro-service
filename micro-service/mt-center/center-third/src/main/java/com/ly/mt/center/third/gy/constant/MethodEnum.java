package com.ly.mt.center.third.gy.constant;

/**
 * @Description 管易接口方法枚举类
 * @Author taoye
 */
public enum MethodEnum {
    GY_ERP_SHOP_GET("gy.erp.shop.get", "店铺查询"),
    GY_ERP_ITEMS_GET("gy.erp.items.get", "商品查询"),
    GY_ERP_AREA_GET("gy.erp.area.get", "行政区域"),
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