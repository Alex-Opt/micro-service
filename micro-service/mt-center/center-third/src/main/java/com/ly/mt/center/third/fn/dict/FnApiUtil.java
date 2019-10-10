package com.ly.mt.center.third.fn.dict;

/**
 * @Description 蜂鸟配送接口Api枚举
 * @Author taoye
 */
public enum FnApiUtil {
    GET_TOKEN("/get_access_token", "获取token"),
    ORDER_CREATE("/v2/order", "推送订单到蜂鸟"),
    ORDER_CANCEL("/v2/order/cancel", "同步取消订单"),
    ORDER_QUERY("/v2/order/query", "订单查询，物流信息"),
    CARRIER_QUERY("/v2/order/carrier", "查询骑手位置");

    private String api;
    private String desc;

    FnApiUtil(String api, String desc) {
        this.api = api;
        this.desc = desc;
    }

    public String getApi() {
        return api;
    }

    public String getDesc() {
        return desc;
    }
}