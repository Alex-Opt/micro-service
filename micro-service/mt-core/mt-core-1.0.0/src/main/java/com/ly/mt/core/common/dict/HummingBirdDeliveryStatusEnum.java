package com.ly.mt.core.common.dict;

/**
 * 蜂鸟配送系统的配送状态枚举类
 * @author zhanglifeng
 *
 */
public enum HummingBirdDeliveryStatusEnum {
    DELIVERY_STATUS_SYSTEM_RECEIVED_ORDER(1,"系统已接单"),
    DELIVERY_STATUS_ALLOCATED_RIDER(20,"已分配骑手"),
    DELIVERY_STATUS_RIDER_ARRIVED_STORE(80,"骑手已到店"),
    DELIVERY_STATUS_IN_DISTRIBUTION(2,"配送中"),
    DELIVERY_STATUS_ARRIVED(3,"已送达"),
    DELIVERY_STATUS_ABNORMAL_DELIVERY(5,"系统拒单/配送异常"),;


    private final Integer id;

    private final String name;

    HummingBirdDeliveryStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
