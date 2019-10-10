package com.ly.mt.cabinet.c.enumEntity;

public enum GzgOrderSourceEnum {
    YILIAN("1","亿联订单"),
    RUJIN("3","如金订单"),
    SHOWCASE("2","展柜");

    private String key;
    private String desc;

    GzgOrderSourceEnum(String key, String value) {
        this.key = key;
        this.desc = value;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
