package com.ly.mt.core.common.constant.gzg;

public enum  ReplenishProcessStatusEnum {
    FAIL(0,"fail"),
    SUCCESS(1,"success");

    private int key;
    private String value;

    ReplenishProcessStatusEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
