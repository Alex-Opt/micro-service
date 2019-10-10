package com.ly.mt.core.dict;

/**
 * @Description Snowflake主键id生成配置枚举
 * @Author taoye
 */
public enum PrimaryKey {
    PRIMARY_KEY_USER(1, 1, "user"),
    PRIMARY_KEY_USER_ADDRESS(1, 2, "user_address"),

    PRIMARY_KEY_TRADE_ORDERS(1, 3, "trade_orders"),
    PRIMARY_KEY_ORDER_NO(1, 4, "订单号"),
    PRIMARY_KEY_TRADE_ORDER_ITEMS(1, 5, "trade_order_items"),
    PRIMARY_KEY_TRADE_ORDER_COUPON(1, 6, "trade_order_coupon"),
    PRIMARY_KEY_ORDERS_BATTLE_INFO(1, 7, "orders_battle_info"),
    PRIMARY_KEY_ORDERS_BATTLE_SHOP(1, 8, "orders_battle_shop"),
    PRIMARY_KEY_ORDERS_BATTLE_LOGS(1, 9, "orders_battle_logs"),

    PRIMARY_KEY_GOODS_SPU_INFO(2, 1, "goods_spu_info"),
    PRIMARY_KEY_COUPON_CODE(2, 2, "coupon_code"),
    PRIMARY_KEY_COUPON_INFO(2, 3, "coupon_info"),
    PRIMARY_KEY_ROTATION_INFO(2, 4, "rotation_info"),
    PRIMARY_KEY_GOODS_SKU_PICTURE(2, 5, "goods_sku_picture"),

    PRIMARY_KEY_POINT_GRADE(3, 2, "point_grade"),

    PRIMARY_KEY_USER_POINT_DATA(3, 1, "user_point_data"),
    PRIMARY_KEY_USER_POINT_GRADE(3, 3, "user_point_grade"),
    PRIMARY_KEY_USER_PUNCH_CARD(3, 4, "user_punch_card"),
    PRIMARY_KEY_TRADE_ORDER_MEDIA(3, 5, "trade_order_media"),

    PRIMARY_KEY_PAYMENT_DETAIL(4, 1, "payment_detail"),
    PRIMARY_KEY_PAYMENT_DEAL(4, 2, "payment_deal"),

    PRIMARY_KEY_ACTIVITY_USER_GRADE_DETAIL(5, 1, "activity_user_grade_detail"),
    PRIMARY_KEY_ACTIVITY_SIGN_UP_CABINET(5, 2, "activity_sign_up_cabinet"),

    PRIMARY_KEY_GZG_HOTEL_INFO(6, 1, "gzg_hotel"),
    PRIMARY_KEY_GZG_INFO(6, 2, "gzg_info"),

    PRIMARY_KEY_REPLENISH_ORDER(5, 1, "replenish_order"),
    PRIMARY_KEY_REPLENISH_ORDER_RECORD(5, 2, "replenish_order_record"),

    PRIMARY_KEY_SHOP_USER(6, 1, "shop_user"),
    PRIMARY_KEY_SHOP_USER_ADDRESS(6, 2, "shop_user_address"),
    PRIMARY_KEY_LODE_RUNNER_TREES(6, 3, "lode_runner_trees"),
    PRIMARY_KEY_SHOP_USER_LICENSES(6, 4, "shop_user_licenses"),

    PRIMARY_KEY_HD_ACTIVITY_USER(7, 1, "hd_activity_user"),
    PRIMARY_KEY_HD_OPERATOR_INFO(7, 2, "hd_operator_info"),
    PRIMARY_KEY_HD_SHOP_ATT_DETAIL(7, 3, "hd_shop_att_detail"),
    PRIMARY_KEY_HD_SHOP_ATT_GOODS_SPU(7, 4, "hd_shop_att_goods_spu"),
    PRIMARY_KEY_HD_SHOP_ATT_ORDER(7, 5, "hd_shop_att_order"),
    PRIMARY_KEY_HD_SHOP_ATT_ORDER_DETAIL(7, 6, "hd_shop_att_order_detail"),

    PRIMARY_KEY_USER_PROFIT_LOGS(8,1,"user_profit_logs"),
    PRIMARY_KEY_USER_PROFITS(8,2,"user_profits"),
    PRIMARY_KEY_LODE_RUNNER_USER_TREES(8,3,"lode_runner_user_trees"),

    PRIMARY_KEY_GZG_ORDERS(9,1,"gzg_orders"),
    PRIMARY_KEY_GZG_ORDER_ITEMS(9,2,"gzg_order_items"),
    PRIMARY_KEY_GZG_ORDER_PAY(9,3,"gzg_order_pay"),
    PRIMARY_KEY_GZG_COUPON_INFO(9,4,"gzg_coupon_info"),

    PRIMARY_KEY_VOTE_CITY(9,1,"vote_city"),

    /**
     * 蓝牙APP-相关表结构主键枚举定义
     */
    PRIMARY_KEY_BLUTETOOTH_USER_TASTE(10,1,"bluetooth_user_taste"),
    PRIMARY_KEY_BLUTETOOTH_INDEX(10,2,"bluetooth_index"),
    PRIMARY_KEY_BLUTETOOTH_USER_BIND(10,3,"bluetooth_user_bind"),
    PRIMARY_KEY_BLUTETOOTH_USER_SUBSIDIARY(10,4,"bluetooth_user_subsidiary"),
    PRIMARY_KEY_BLUTETOOTH_INDEX_LOG(10,5,"bluetooth_index_log"),
    PRIMARY_KEY_BLUTETOOTH_LOG(10,6,"bluetooth_log"),




    PRIMARY_KEY_RANDOM(31, 31, "随机数");

    private final long workerId;
    private final long datacenterId;
    private final String desc;

    PrimaryKey(long workerId, long datacenterId, String desc) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.desc = desc;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }
}