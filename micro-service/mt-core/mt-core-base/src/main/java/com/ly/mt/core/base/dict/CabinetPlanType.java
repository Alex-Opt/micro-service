package com.ly.mt.core.base.dict;

/**
 * 配货方案类型枚举
 * cabinet_plan_list.cabinet_plan_type
 *
 * @author taoye
 */
public enum CabinetPlanType {
    CABINET_PLAN_TYPE_DOOR("1", "舱门"),
    CABINET_PLAN_TYPE_AISLE("2", "货道");

    private final String id;
    private final String name;

    CabinetPlanType(String id, String name) {
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