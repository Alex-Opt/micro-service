package com.ly.mt.core.base.dict;

/**
 * 用户状态
 */
public enum ShopInfoStatus {

    USER_STATUS_USING("0", "正常"),
    USER_STATUS_STOP_USING("1", "停用"),;

    private final String id;
    private final String name;

    ShopInfoStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
