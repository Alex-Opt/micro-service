package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * 预估客流量
 */
public enum ForstCastEnum {
    A(22,"50人以下"),
    B(23,"50-80人/天"),
    C(24,"80-100人/天"),
    D(25,"100-120人/天"),
    E(26,"120-150人/天"),
    F(27,"150-200人/天"),
    G(28,"200人以上/天")
    ;

    int key;
    String value;

    ForstCastEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static int getByValue(String value) {
        for (ForstCastEnum entry : values()){
            if (StringUtils.equals(entry.getValue(),value)){
                return entry.getKey();
            }
        }
        return -1;
    }

    public static String getByKey(int key){
        for (ForstCastEnum entry : ForstCastEnum.values()){
            if (entry.getKey() == key){
                return entry.getValue();
            }
        }
        return null;
    }
}
