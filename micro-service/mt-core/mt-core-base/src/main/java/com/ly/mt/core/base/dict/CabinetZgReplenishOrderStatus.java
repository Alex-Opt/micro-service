package com.ly.mt.core.base.dict;

/**
 * 展柜补货状态枚举
 *
 * @author wanghongliang
 */
public enum CabinetZgReplenishOrderStatus {
    GENERATION_OF_REPLENISHMENT("0", "待补货"),
    COMPLETED("1", "已完成");

    private final String value;
    private final String desc;

    CabinetZgReplenishOrderStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }}
