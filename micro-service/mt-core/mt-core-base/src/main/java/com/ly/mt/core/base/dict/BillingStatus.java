package com.ly.mt.core.base.dict;

/**
 * 结算状态枚举类  1：待结算，2正常，3：取消',
 */
public enum BillingStatus {
    BILLING_STATUS_WAITING_BILL("1","待结算"),
    BILLING_STATUS_NORMAL_BILL("2","正常"),
    BILLING_STATUS_CANCEL_BILL("3","取消"),;
    private final String id;
    private final String name;

    BillingStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}



