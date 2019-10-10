package com.ly.mt.center.data.cabinet.enums;

import org.apache.commons.lang.StringUtils;

public enum CabinetStoreCreateStatusEnum {
    A("0","待创建"),
    B("1","已创建"),
    C("2","已使用");

    String key;
    String value;

    CabinetStoreCreateStatusEnum(String key, String value) {
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

    public String getByKey(String key){
        for (CabinetStoreCreateStatusEnum entry : values()){
            if (StringUtils.equals(entry.getKey(),key)){
                return entry.getValue();
            }
        }
        return null;
    }

    public String getByValue(String value){
        for (CabinetStoreCreateStatusEnum entry : values()){
            if (StringUtils.equals(entry.getValue(), value)){
                return entry.getKey();
            }
        }
        return null;
    }
}
