package com.ly.mt.core.base.dict;

/**
 * @author zhanglifeng
 *会员等级限定类型枚举类
 */
public enum GradeLimitType {
    GRADE_NO_LIMIT("-1","不限定等级"),
    GRADE_MIN_LIMIT("1","限定最低等级"),
    GRADE_MAX_LIMIT("2","限定最高等级"),
    GRADE_MIN_MAX_LIMIT("3","限定最低等级和最高等级");

    private final String id;
    private final String name;

    GradeLimitType(String id, String name) {
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
