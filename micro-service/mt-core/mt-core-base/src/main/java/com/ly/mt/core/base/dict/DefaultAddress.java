package com.ly.mt.core.base.dict;

/**
 * @Description is_default是否默认地址枚举
 * @Author taoye
 */
public enum DefaultAddress {
    DEFAULT_ADDRESS_NOT("0", "非默认地址"),
    DEFAULT_ADDRESS("1", "默认地址");

    private final String id;
    private final String name;

    DefaultAddress(String id, String name) {
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