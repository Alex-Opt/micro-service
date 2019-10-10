package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

/**
 * 装修情况
 */
public enum DecorateTypeEnum {
    A(29,"装修"),
    B(30,"简单"),
    C(31,"普通"),
    D(32,"精装修")
    ;

    int code;
    String value;

    DecorateTypeEnum(int code,String value){
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static String getValueByCode(int code){
        for (DecorateTypeEnum x : DecorateTypeEnum.values()){
            if (x.getCode() == code){
                return x.getValue();
            }
        }
        return null;
    }

    public static int getCodeByValue(String value){
        for (DecorateTypeEnum x : DecorateTypeEnum.values()){
            if (StringUtils.equals(x.getValue(),value)){
                return x.getCode();
            }
        }
        return -1;
    }
}
