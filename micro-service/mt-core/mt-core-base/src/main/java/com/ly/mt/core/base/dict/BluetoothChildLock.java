package com.ly.mt.core.base.dict;

/**
 * @Description 儿童锁状态枚举
 * @Author taoye
 */
public enum BluetoothChildLock {
    UNCHILD_LOCK("0", "关闭"),
    CHILD_LOCK("1", "开启"),;

    private final String id;
    private final String name;

    BluetoothChildLock(String id, String name) {
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