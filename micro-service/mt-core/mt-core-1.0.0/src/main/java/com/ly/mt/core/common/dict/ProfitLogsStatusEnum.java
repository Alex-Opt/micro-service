package com.ly.mt.core.common.dict;

public enum  ProfitLogsStatusEnum {
    PROFIT_LOGS_STATUS_WAITING_BILL("1","待结算"),
    PROFIT_LOGS_STATUS_USING("1","正常"),
    PROFIT_LOGS_STATUS_CANCEL("1","取消"),

    ;


    private final String id;

    private final String name;

    ProfitLogsStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}

