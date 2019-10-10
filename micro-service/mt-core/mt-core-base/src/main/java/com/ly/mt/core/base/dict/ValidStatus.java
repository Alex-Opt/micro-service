package com.ly.mt.core.base.dict;

/**
 * valid_status
 * 有效状态
 *
 * @author taoye
 */
public enum ValidStatus {
    VALID_STATUS_NO("0", "无效"),
    VALID_STATUS_YES("1", "有效");

    private final String id;
    private final String name;

    ValidStatus(String id, String name) {
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