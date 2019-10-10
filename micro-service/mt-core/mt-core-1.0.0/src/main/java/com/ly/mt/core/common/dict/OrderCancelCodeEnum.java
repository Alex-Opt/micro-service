package com.ly.mt.core.common.dict;

/**
 * @author zhanglifeng
 * 订单取消编码
 */
public enum OrderCancelCodeEnum {
    ORDER_CANCEL_LOGISTICS_REASONS_NOT_DELIVER_RIDER("1", "物流原因-订单长时间未分配骑手"),
    ORDER_CANCEL_LOGISTICS_REASONS_RIDER_UNFETCHED("2", "物流原因-分配骑手后，骑手长时间未取件"),
    ORDER_CANCEL_LOGISTICS_REASONS_INFORMS_CANCEL("3", "物流原因-骑手告知不配送，让取消订单"),
    ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK("4", "商品缺货/无法出货/已售完"),
    ORDER_CANCEL_LOGISTICS_REASONS_CLOSE_DOOR("5", "商户联系不上门店/门店关门了"),
    ORDER_CANCEL_LOGISTICS_REASONS_WRONG_ORDER("6", "商户发错单"),
    ORDER_CANCEL_LOGISTICS_REASONS_POSITIONING_ERROR("7", "商户/顾客自身定位错误"),
    ORDER_CANCEL_LOGISTICS_REASONS_REPLACE("8", "商户改其他第三方配送"),
    ORDER_CANCEL_LOGISTICS_REASONS_GIVE_UP("9", "顾客下错单/临时不想要了"),
    ORDER_CANCEL_LOGISTICS_REASONS_NOT_HOME("10", "顾客自取/不在家/要求另改时间配送"),
    ;


    private final String id;

    private final String name;

    OrderCancelCodeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
