package com.ly.mt.core.base.dict;

/**
 * 订单来源枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-27
 */
public enum OrderSource {
    ORDER_SOURCE_H5("1", "H5", "到家C"),
    ORDER_SOURCE_APPLETS("2", "小程序", "到家C"),
    ORDER_SOURCE_APP("3", "APP", "到家C"),
    ORDER_SOURCE_PERSONAL_SHOP("4", "专属小店", "到家C"),
    ORDER_SOURCE_HOME_B_H5("10", "H5", "到家B"),
    ORDER_SOURCE_HOME_B_SMALL("11", "小程序", "到家B"),
    ORDER_SOURCE_HOME_B_APP("12", "APP", "到家B"),
    ORDER_SOURCE_CABINET_C_H5("30:", "H5", "格子柜C"),
    ORDER_SOURCE_OFO("999999", "OFO订单", "ofo订单"),
    ORDER_SOURCE_POKE("1000000", "扑克牌订单", "扑克牌订单");

    private final String id;
    private final String name;
    private final String desc;

    OrderSource(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
