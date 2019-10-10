package com.ly.mt.core.base.dict;

/**
 * 注册来源枚举
 * user.quick_type
 *
 * @author taoye
 */
public enum QuickType {
    /**
     * @deprecated 到家C端H5
     */
    QUICK_TYPE_MALL_H5("1", "H5"),
    /**
     * @deprecated 到家C端微信小程序
     */
    QUICK_TYPE_MALL_WX_APPLET("2", "小程序"),
    /**
     * @deprecated 到家C端App
     */
    QUICK_TYPE_MALL_APP("3", "APP"),
    /**
     * @deprecated 到家C活动页注册
     */
    QUICK_TYPE_ACTIVITY_PAGE("4", "活动"),
    /**
     * @deprecated 格子柜C
     */
    QUICK_TYPE_GZG_C("5", "H5"),
    /**
     * @deprecated 格子柜B
     */
    QUICK_TYPE_GZG_B("6", "格子柜B"),
    /**
     * @deprecated 蓝牙APP
     */
    QUICK_TYPE_BLUETOOTH("7", "APP"),
    /**
     * @deprecated ofo
     */
    QUICK_TYPE_OFO("8", "ofo"),
    /**
     * @deprecated 到家B端H5
     */
    QUICK_TYPE_HOME_B_H5("9", "H5"),
    /**
     * @deprecated 到家B端活动
     */
    QUICK_TYPE_HOME_B_ACTIVITY("10", "活动"),

    QUICK_TYPE_APP("100", "APP"),
    QUICK_TYPE_H5("200", "H5"),
    QUICK_TYPE_ACTIVITY("300", "活动"),
    QUICK_TYPE_APPLET("400", "小程序"),
    QUICK_TYPE_MIS("1000", "运营后台");


    private final String id;
    private final String name;

    QuickType(String id, String name) {
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