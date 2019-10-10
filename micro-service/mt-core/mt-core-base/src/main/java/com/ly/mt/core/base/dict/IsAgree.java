package com.ly.mt.core.base.dict;

/**
 * 是否同意举类
 * trade_orders.is_agree
 *
 * @author taoye
 */
public enum IsAgree {
    IS_AGREE_WAIT("1", "待确认"),
    IS_AGREE_YES("2", "已同意");

    private final String id;
    private final String name;

    IsAgree(String id, String name) {
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
