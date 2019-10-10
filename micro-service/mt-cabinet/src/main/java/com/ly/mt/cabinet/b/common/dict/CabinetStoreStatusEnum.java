package com.ly.mt.cabinet.b.common.dict;

public enum CabinetStoreStatusEnum {
    A("0","有效"),
    B("1","无效");

    private String key;
    private String value;

    CabinetStoreStatusEnum(String key,String value){
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
