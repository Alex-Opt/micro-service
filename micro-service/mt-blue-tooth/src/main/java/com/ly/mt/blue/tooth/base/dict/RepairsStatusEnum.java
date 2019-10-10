package com.ly.mt.blue.tooth.base.dict;

/**
* @program: mt-blue-tooth
* @description: 报修状态
* @author: wanghongliang
* @create: 2019/8/27 16:04
**/
public enum RepairsStatusEnum {
    FILLED("0", "已填写"),
    CLOSED("1", "已关闭"),
    TO_AUDIT("2", "待审核"),
    RECEIVED("3", "已收货");

    private final String value;
    private final String desc;

    RepairsStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }}