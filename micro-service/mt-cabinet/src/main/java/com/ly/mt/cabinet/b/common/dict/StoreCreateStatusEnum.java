package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

/**
 * 店铺创建状态
 */
public enum StoreCreateStatusEnum {

    A(0,"待创建"),
    B(1,"已创建"),
    C(2,"已使用");

    private int key;
    private String value;

    StoreCreateStatusEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getByKey(int key){
        for (StoreCreateStatusEnum entry : values()){
            if (entry.getKey() == key){
                return value;
            }
        }
        return null;
    }

    public int getByValue(String value){
        for (StoreCreateStatusEnum entry : values()){
            if (StringUtils.equals(entry.getValue(), value)){
                return entry.getKey();
            }
        }
        return -1;
    }
}
