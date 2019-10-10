package com.ly.mt.core.base.dict;

/**
 * 功能级别枚举
 * basic_func.func_level
 * basic_role_right.right_level
 *
 * @author taoye
 */
public enum FuncLevel {
    FUNC_LEVEL_ONE("1", "一级"),
    FUNC_LEVEL_TWO("2", "二级"),
    FUNC_LEVEL_THREE("3", "三级"),
    FUNC_LEVEL_FOUR("4", "四级");

    private final String id;
    private final String name;

    FuncLevel(String id, String name) {
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