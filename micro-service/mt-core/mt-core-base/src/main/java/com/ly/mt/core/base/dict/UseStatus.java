package com.ly.mt.core.base.dict;

/**
 * 使用状态枚举类
 * coupon_code.use_status
 *
 * @Author taoye
 */
public enum UseStatus {
    USE_STATUS_NO("1", "未使用"),
    USE_STATUS_YES("2", "已使用");

    private final String id;
    private final String name;

    UseStatus(String id, String name) {
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