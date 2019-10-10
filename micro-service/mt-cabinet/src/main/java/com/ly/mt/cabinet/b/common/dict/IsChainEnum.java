package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

/**
 * 是否连锁
 */
public enum IsChainEnum {
    A(33,"是"),
    B(34,"否");

    int key;
    String value;

    IsChainEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static String getByKey(int key){
        for (IsChainEnum entry : IsChainEnum.values()){
            if (entry.getKey() == key){
                return entry.getValue();
            }
        }
        return null;
    }

    public static int getByValue(String value){
        for (IsChainEnum entry : IsChainEnum.values()){
            if (StringUtils.equals(entry.getValue(), value)){
                return entry.getKey();
            }
        }
        return -1;
    }
}

