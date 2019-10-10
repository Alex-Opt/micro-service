package com.ly.mt.center.third.fn.dict;

/**
 * @Description 蜂鸟配送接口Api枚举
 * @Author taoye
 */
public enum FnApi {
    FN_API_GET_TOKEN("/get_access_token", "获取token"),
    FN_API_ORDER_CREATE("/v2/order", "创建订单"),
    FN_API_ORDER_CANCEL("/v2/order/cancel", "取消订单"),
    FN_API_ORDER_QUERY("/v2/order/query", "订单查询"),
    FN_API_CARRIER_QUERY("/v2/order/carrier", "查询骑手位置");

    private String api;
    private String desc;

    FnApi(String api, String desc) {
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