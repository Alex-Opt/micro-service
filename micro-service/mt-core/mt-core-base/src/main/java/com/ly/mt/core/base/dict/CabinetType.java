package com.ly.mt.core.base.dict;

/**
 * 货柜类型枚举类
 * cabinet_categroy.cabinet_type
 *
 * @author taoye
 */
public enum CabinetType {
    CABINET_TYPE_LATTICE("1", "格子柜"),
    CABINET_TYPE_SHOWCASE("2", "展柜"),
    CABINET_TYPE_MIXING("3", "混合柜");

    private final String id;
    private final String name;

    CabinetType(String id, String name) {
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