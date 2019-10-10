package com.ly.mt.core.base.dict;

/**
 * 蜂鸟配送系统的配送状态枚举类
 * @author zhanglifeng
 *
 */
public enum HummingBirdDeliveryStatus {
    DELIVERY_STATUS_SYSTEM_RECEIVED_ORDER("1","系统已接单"),
    DELIVERY_STATUS_ALLOCATED_RIDER("20","已分配骑手"),
    DELIVERY_STATUS_RIDER_ARRIVED_STORE("80","骑手已到店"),
    DELIVERY_STATUS_IN_DISTRIBUTION("2","配送中"),
    DELIVERY_STATUS_ARRIVED("3","已送达"),
    DELIVERY_STATUS_ABNORMAL_DELIVERY("5","系统拒单/配送异常"),;


    private final String id;

    private final String name;

    HummingBirdDeliveryStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
