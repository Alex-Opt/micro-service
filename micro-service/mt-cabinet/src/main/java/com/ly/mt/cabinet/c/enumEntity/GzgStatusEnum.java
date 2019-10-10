package com.ly.mt.cabinet.c.enumEntity;

//柜子上下架状态
public enum GzgStatusEnum {
    ONSALE("0","上架"),
    DOWNSTALE("1","下架"),
    ;

    GzgStatusEnum(String key, String describe) {
        this.key = key;
        this.describe = describe;
    }


    public String getKey() {
        return key;
    }

    public String getDescribe() {
        return describe;
    }

    private final String key;
    private final String describe;
}
