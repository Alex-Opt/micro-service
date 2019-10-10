package com.ly.mt.center.data.cabinet.enums;

/**
 * 格子柜枚举
 * <p>
 *     因为表中的有的状态字段是 int 有的是char 有的是varchar
 *     所以下面的两个状态属性，根据需要自行定义为intStatus 和stringStatus
 * <p/>
 *
 */
public enum CabinetEnums {
    CABINET_INFO_STATUS_UP("cabinet_info", "status", 0, "", "格子柜上架状态"),
    CABINET_INFO_STATUS_DOWN("cabinet_info","status",1,"","格子柜下架"),
    ;


    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段
     */
    private String field;

    /**
     * int类型的状态
     */
    private Integer intStatus;
    /**
     * string类型状态
     */
    private String stringStatus;

    /**
     * 备注信息
     */
    private String msg;

    CabinetEnums(String tableName, String field, Integer intStatus, String stringStatus, String msg) {
        this.tableName = tableName;
        this.field = field;
        this.intStatus = intStatus;
        this.stringStatus = stringStatus;
        this.msg = msg;
    }

    public String getField() {
        return field;
    }

    public String getTableName() {
        return tableName;
    }

    public Integer getIntStatus() {
        return intStatus;
    }

    public String getStringStatus() {
        return stringStatus;
    }

    public String getMsg() {
        return msg;
    }
}
