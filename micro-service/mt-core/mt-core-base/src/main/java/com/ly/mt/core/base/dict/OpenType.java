package com.ly.mt.core.base.dict;

/**
 * @Description 推送消息的连接打开后的方式枚举类
 * @Author zhanglifeng
 */
public enum OpenType {
    OPEN_URL("1", "打开网页链接"),
    OPEN_APP("2", "打开app"),;

    private final String id;
    private final String name;

    OpenType(String id, String name) {
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