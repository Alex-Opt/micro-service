package com.ly.mt.cabinet.b.common.dict;

/**
 * 表cabinet_info.status字段
 */
public enum CabinetInfoStatusEnum {
    A("0","上架"),
    B("1","下架");

    private String key;
    private String value;

    CabinetInfoStatusEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
