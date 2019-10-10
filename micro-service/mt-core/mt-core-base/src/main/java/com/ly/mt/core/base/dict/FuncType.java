package com.ly.mt.core.base.dict;

/**
 * func_type
 * 功能类型
 *
 * @author taoye
 */
public enum FuncType {
    FUNC_TYPE_MODULE("1", "模块"),
    FUNC_TYPE_NAVIGATION("2", "导航"),
    FUNC_TYPE_MENU("3", "菜单"),
    FUNC_TYPE_BUTTON("4", "按钮");

    private final String id;
    private final String name;

    FuncType(String id, String name) {
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