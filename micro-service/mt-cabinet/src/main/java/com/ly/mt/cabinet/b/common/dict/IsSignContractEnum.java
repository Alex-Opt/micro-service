package com.ly.mt.cabinet.b.common.dict;

import org.springframework.util.StringUtils;

/**
 * 是否创建合同
 * cabinet_bussiness_coorperation   表的  is_sign_contract 字段
 */
public enum IsSignContractEnum {
    YES(48,"是"),
    NO(49,"否")
    ;


    private int key;
    private String value;

    IsSignContractEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getByKey(int key){
        for (IsSignContractEnum entity : IsSignContractEnum.values()) {
            if (key == entity.key)
                return entity.value;
        }
        return null;
    }

    public static int getByValue(String value){
        for (IsSignContractEnum isSignContractEnum : IsSignContractEnum.values()) {
            if (StringUtils.pathEquals(value,isSignContractEnum.value))
                return isSignContractEnum.key;
        }
        return -1;
    }

}
