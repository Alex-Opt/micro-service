package com.ly.mt.core.base.dict;

/**
 * @Description token枚举
 * @Author whl
 */
public enum TokenEnum {
    TOKEN_EXPIRE(10000000L, "过期时间");

    private final Long value;
    private final String desc;

    TokenEnum(Long id, String name) {
        this.value = id;
        this.desc = name;
    }

    public Long getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}