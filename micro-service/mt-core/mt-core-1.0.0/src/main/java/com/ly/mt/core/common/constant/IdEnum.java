package com.ly.mt.core.common.constant;

/**
 * @Description Snowflake主键id生成配置枚举
 * @Author taoye
 */
public enum IdEnum {
    USER(1, 1, "user"),
    USER_ADDRESS(1, 2, "user_address"),
    USER_UPDATE_LOGIN_NAME_LOGS(1, 12, "user_update_login_name_logs"),
    USER_FEEDBACK(1, 13, "user_feedback"),
    USER_FEEDBACK_IMAGES(1, 14, "user_feedback_images"),


    TRADE_ORDERS(1,3,"trade_orders"),
    ORDER_NO(1,4,"订单号"),
    TRADE_ORDER_ITEMS(1,5,"trade_order_items"),
    TRADE_ORDER_COUPON(1, 6, "trade_order_coupon"),
    ORDERS_BATTLE_INFO(1,7,"orders_battle_info"),
    ORDERS_BATTLE_SHOP(1,8,"orders_battle_shop"),
    ORDERS_BATTLE_LOGS(1,9,"orders_battle_logs"),
    ORDER_BATTLE_CHECK_LOGS(1,10,"order_battle_check_logs"),
    ORDER_BATTLE_EXPRESSES(1,11,"order_battle_expresses"),

    GOODS_SPU_INFO(2, 1, "goods_spu_info"),
    COUPON_CODE(2, 2, "coupon_code"),
    COUPON_INFO(2, 3, "coupon_info"),
    ROTATION_INFO(2, 4, "rotation_info"),
    GOODS_SKU_PICTURE(2, 5, "goods_sku_picture"),

    POINT_GRADE(3, 2, "point_grade"),

    USER_POINT_DATA(3, 1, "user_point_data"),
    USER_POINT_GRADE(3, 3, "user_point_grade"),
    USER_PUNCH_CARD(3, 4, "user_punch_card"),

    PAYMENT_DETAIL(4, 1, "payment_detail"),
    PAYMENT_DEAL(4, 2, "payment_deal"),

    ACTIVITY_USER_GRADE_DETAIL(5, 1, "activity_user_grade_detail"),

    GZG_HOTEL_INFO(6,1,"gzg_hotel"),
    GZG_INFO(6,2,"gzg_info"),

    REPLENISH_ORDER(5,1,"replenish_order"),
    REPLENISH_ORDER_RECORD(5,2,"replenish_order_record"),

    SHOP_USER(6,1,"shop_user"),
    SHOP_USER_ADDRESS(6,2,"shop_user_address"),
    LODE_RUNNER_TREES(6,3,"lode_runner_trees"),
    SHOP_USER_LICENSES(6,4,"shop_user_licenses"),
    SHOP_PROFIT_LOGS(6,5,"shop_profit_logs"),
    SHOP_PROFITS(6,6,"shop_profits"),
    SHOP_ADDRESS(6,7,"shop_address"),


    HD_ACTIVITY_USER(7,1,"hd_activity_user"),
    HD_OPERATOR_INFO(7,2,"hd_operator_info"),
    HD_SHOP_ATT_DETAIL(7,3,"hd_shop_att_detail"),
    HD_SHOP_ATT_GOODS_SPU(7,4,"hd_shop_att_goods_spu"),
    HD_SHOP_ATT_ORDER(7,5,"hd_shop_att_order"),
    HD_SHOP_ATT_ORDER_DETAIL(7,6,"hd_shop_att_order_detail"),
    /**
     * 退款单单号
     */
    ORDER_REFUND_ID(8,1,"退款单单号"),
    ORDER_REFUND_ITEM_ID(8,2,"退款单详细单号"),

    /**
     * 投票表
     */
    VOTE_CITY_ID(9,1,"一小时达投票主键"),

    RANDOM(31, 31, "随机数");

    private final long workerId;
    private final long datacenterId;
    private final String describe;

    IdEnum(long workerId, long datacenterId, String describe) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.describe = describe;
    }

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }
}