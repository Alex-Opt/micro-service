package com.ly.mt.core.common.dict;

/**
 * 快递公司枚举类
 */
public enum ExpressCodeEnum {
    ZTO_CODE("ZTO", "中通快递"),
    SF_CODE("SF","顺丰标准快递"),
    QFKD_CODE("QFKD","全峰快递"),
    SFGJ_CODE("SFGJ","顺丰国际"),
    EMS_CODE("EMS","EMS快递"),
    HTKY_CODE("HTKY","百世汇通"),
    ZJS_CODE("ZJS","宅急送"),
    YTO_CODE("YTO","圆通速递"),
    CWPS_CODE("CWPS","生鲜常温派送"),;

    private final String id;
    private final String name;

    ExpressCodeEnum(String id, String name) {
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