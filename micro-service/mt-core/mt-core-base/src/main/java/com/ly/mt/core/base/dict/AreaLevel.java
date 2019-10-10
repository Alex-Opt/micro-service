package com.ly.mt.core.base.dict;

/**
 * 区域级别枚举
 * basic_area.level
 * basic_role_right.right_level
 *
 * @author taoye
 */
public enum AreaLevel {
    AREA_LEVEL_ZERO("0", "大区"),
    AREA_LEVEL_ONE("1", "省"),
    AREA_LEVEL_TWO("2", "市"),
    AREA_LEVEL_THREE("3", "县");

    private final String id;
    private final String name;

    AreaLevel(String id, String name) {
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