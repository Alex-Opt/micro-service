package com.ly.mt.cabinet.b.common.dict;

/**
 * 格子柜类型枚举类
 * @author zhanglifneg
 * @date 2019-08-26
 */
public enum CabinetTypeEnum {

    CABINET_TYPE_YI_LIAN("1","亿联","YL"),
    CABINET_TYPE_ZAN_GUI("2","展柜","ZG"),
    CABINET_TYPE_RU_JIN("3","如金","RJ"),;
    private String key;
    private String value;
    private String code;

    CabinetTypeEnum(String key, String value,String code) {
        this.key = key;
        this.value = value;
        this.code = code;
    }

    public static void valueOf() {
    }

    public String getKey() {
        return key;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }}
