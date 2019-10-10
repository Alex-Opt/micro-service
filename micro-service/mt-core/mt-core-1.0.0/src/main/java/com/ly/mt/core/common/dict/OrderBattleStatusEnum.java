package com.ly.mt.core.common.dict;

/**
 * 发货单的订单快递状态枚举类
 */
public enum OrderBattleStatusEnum {
    ORDER_BATTLE_STATUS_NOT_BATTLE("1", "未抢单"),
    ORDER_BATTLE_STATUS_ALREADY_BATTLE("3", "已被抢单"),
    ORDER_BATTLE_STATUS_ALREADY_CHECK("5", "商家已商品校验"),
    ORDER_BATTLE_STATUS_ALREADY_SEND("7", "订单已推送到蜂鸟系统"),
    ORDER_BATTLE_STATUS_REFUSED("9", "系统拒单/配送异常"),
    ORDER_BATTLE_STATUS_ACCEPT_ORDER("11", "蜂鸟系统已接单"),
    ORDER_BATTLE_STATUS_RIDER_FETCH("13", "已分配骑手"),
    ORDER_BATTLE_STATUS_RIDER_ARRIVAL("15", "骑手已到店"),
    ORDER_BATTLE_STATUS_TRANSPORTING("17", "配送中"),
    ORDER_BATTLE_STATUS_SERVICED("19", "已送达"),
    ORDER_BATTLE_STATUS_FINISHED("21", "商品签收完成"),
    ORDER_BATTLE_STATUS_CANCELED("23", "订单已取消"),
    RDER_BATTLE_STATUS_REJECT_SIGN("25", "商品被拒签"),
    RDER_BATTLE_STATUS_GOODS_RETURNING("27", "商品申请退货中"),
    RDER_BATTLE_STATUS_GOODS_RETURNED("29", "商品退货退款完成"),
    ;

    private final String id;
    private final String name;

    OrderBattleStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
