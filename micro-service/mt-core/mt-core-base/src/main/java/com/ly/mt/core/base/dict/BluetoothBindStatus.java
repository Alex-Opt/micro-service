package com.ly.mt.core.base.dict;

/**
 * @Description bind_status绑定状态枚举
 * @Author taoye
 */
public enum BluetoothBindStatus {
    BIND_STATUS("0", "绑定"),
    UNBIND_STATUS("1", "解绑"),;

    private final String id;
    private final String name;

    BluetoothBindStatus(String id, String name) {
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