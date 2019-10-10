package com.ly.mt.core.base.dict;

/**
 * app类型
 *
 * @author
 * @date 2
 */
public enum AppType {
    APP_TYPE_H5("1", "商城", "商城"),
    APP_TYPE_DAOJIAC("2", "到家C", "到家C"),
    APP_TYPE_DAOJIAB("3", "到家B", "到家B"),
    APP_TYPE_HAINAN("4", "海南活动", "海南活动"),
    APP_TYPE_MEDIA("5", "媒体活动", "媒体活动"),
    APP_TYPE_OFO("6", "OFO", "ofo"),
    APP_TYPE_POKE("7", "扑克牌", "扑克牌");

    private final String id;
    private final String name;
    private final String desc;

    AppType(String id, String name, String desc) {
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
