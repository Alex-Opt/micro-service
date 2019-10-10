package com.ly.mt.core.common.dict;

/**
 * 收益类型 枚举类
 * @author zhanglifeng
 */
public enum ProfitTypeEnum {
    PROFIT_TYPE_INVITE("1","邀请"),
    PROFIT_TYPE_ADDRESS_BOOK("2","通讯录"),
    PROFIT_TYPE_FLOW("3","流量"),
    PROFIT_TYPE_GRAB_REWARD("4","抢单奖励"),
    PROFIT_TYPE_GRAB_AMOUNT("5","抢单金额"),
    PROFIT_TYPE_EXCLUSIVE_ORDER("6","专属订单"),
    PROFIT_TYPE_REFUND("7","退款"),
    PROFIT_TYPE_PURCHASE("8","进货"),
    PROFIT_TYPE_GRAB_CASH("10","抢单提现"),
    PROFIT_TYPE_GOLD_RUSH("11","淘金提现"),
    PROFIT_TYPE_EXCLUSIVE_ORDER_WITHDRAWAL("12","专属订单提现"),
    PROFIT_TYPE_GRAB_REWARD_CASH("13","抢单奖励提现"),;


    private final String id;

    private final String name;

    ProfitTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
