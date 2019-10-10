package com.ly.mt.core.base.dict;

/**
 * @Description 微信店铺 授权状态
 * @Author wanghongliang
 */
public enum AuthStatus {
    UNAUTHORIZED ("0", "未授权"),
    AUTHORIZED("1","已授权"),
    REFUSEAUTHORIZED("2","拒绝授权");
    private final String id;
    private final String name;

    AuthStatus(String id, String name) {
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