package com.ly.mt.core.base.dict;

/**
 * func_type
 * 功能类型
 *
 * @author taoye
 */
public enum TriggerType {
    TRIGGER_TYPE_INSERT("1", "新增"),
    TRIGGER_TYPE_UPDATE("2", "修改"),
    TRIGGER_TYPE_DELETE("3", "删除");

    private final String id;
    private final String name;

    TriggerType(String id, String name) {
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