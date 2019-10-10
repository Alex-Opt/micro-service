package com.ly.mt.core.common.constant.gzg;

public enum GzgReplenishStatusEnum {
    STAY_REPLENISH(0,"待补货"),
    LOCK(1,"锁定"),
    FINISHED(2,"完成");

    private int key;
    private String value;

    GzgReplenishStatusEnum(int key,String value){
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
