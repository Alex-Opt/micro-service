package com.ly.mt.core.common.dict;

/**
 * @Description valid_status枚举
 * @Author taoye
 */
public enum ValidStatusEnum {
    VALID_STATUS_NO("0", "无效"),
    VALID_STATUS_YES("1", "有效");

    private final String id;
    private final String name;

    ValidStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}