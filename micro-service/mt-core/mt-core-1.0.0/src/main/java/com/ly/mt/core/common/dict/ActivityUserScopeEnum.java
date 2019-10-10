package com.ly.mt.core.common.dict;

/**
 * 促销活动使用范围枚举类
 * @author zhanglifeng
 */
public enum ActivityUserScopeEnum {
    ACTIVITY_ALL_CATEGORIES("-1","活动-全品类"),
    ACTIVITY_PART_CATEGORIES("1","活动-指定品类");
    private final String id;
    private final String name;

    ActivityUserScopeEnum(String id, String name) {
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
