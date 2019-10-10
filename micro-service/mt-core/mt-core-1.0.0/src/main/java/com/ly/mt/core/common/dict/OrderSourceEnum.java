package com.ly.mt.core.common.dict;

/**
 * 订单来源枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-27
 */
public enum OrderSourceEnum {
    ORDER_SOURCE_H5("1", "到家C-H5"),
    ORDER_SOURCE_APPLETS("2", "到家C-微信小程序"),
    ORDER_SOURCE_APP("3", "到家C-APP"),
    ORDER_SOURCE_PERSONAL_SHOP("4", "专属小店(当来源是4时，shop_id 不能为空)"),
    ORDER_SOURCE_OFO("999999", "OFO订单"),;

    private final String id;

    private final String name;

    OrderSourceEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
