package com.ly.mt.core.base.dict;

/**
 * 领取状态枚举
 * coupon_code.pull_status
 *
 * @author taoye
 */
public enum PullStatus {
    PULL_STATUS_NO("1", "未领取"),
    PULL_STATUS_YES("2", "已领取");

    private final String id;
    private final String name;

    PullStatus(String id, String name) {
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