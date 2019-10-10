package com.ly.mt.core.base.dict;

/**
 * @author zhanglifeng
 * 抢单状态 枚举类
 */
public enum GrabStatus {
    GRAB_STATUS_SUCCESS("1","抢单成功"),
    GRAB_STATUS_FAILURE("2","抢单失败");
    private final String id;
    private final String name;

    GrabStatus(String id, String name) {
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
