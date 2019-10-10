package com.ly.mt.task.redis.dict;

/**
 * 缓存刷新处理类型静态常量类
 * 以下所有数值不能轻易改动，如改动需改动相应的触发器
 *
 * @author taoye
 */
public class RefreshType {
    /**
     * GoodsSkuPicture
     */
    public static final int REDIS_REFRESH_TYPE_GOODS_SKU_PICTURE = 1;
    /**
     * GoodsSkuCode
     */
    public static final int REDIS_REFRESH_TYPE_GOODS_SKU_CODE = 2;
    /**
     * GoodsSkuInfo
     */
    public static final int REDIS_REFRESH_TYPE_GOODS_SKU_INFO = 3;
    /**
     * GoodsSpuInfo
     */
    public static final int REDIS_REFRESH_TYPE_GOODS_SPU_INFO = 4;


    /**
     * User
     */
    public static final int REDIS_REFRESH_TYPE_USER = 50;
    /**
     * UserAddress
     */
    public static final int REDIS_REFRESH_TYPE_USER_ADDRESS = 51;


    /**
     * CouponInfo
     */
    public static final int REDIS_REFRESH_TYPE_COUPON_INFO = 100;


    /**
     * GzgHotel
     */
    public static final int REDIS_REFRESH_TYPE_GZG_HOTEL = 150;
    /**
     * GzgInfo
     */
    public static final int REDIS_REFRESH_TYPE_GZG_INFO = 151;
    /**
     * GzgOrder
     */
    public static final int REDIS_REFRESH_TYPE_GZG_ORDER = 152;
    /**
     * GzgOrderItem
     */
    public static final int REDIS_REFRESH_TYPE_GZG_ORDER_ITEM = 153;


    /**
     * ShopPurchases
     */
    public static final int REDIS_REFRESH_TYPE_SHOP_PURCHASES = 200;
    /**
     * ShopPurchasesItems
     */
    public static final int REDIS_REFRESH_TYPE_SHOP_PURCHASES_ITEMS = 201;
    /**
     * ShopInfo
     */
    public static final int REDIS_REFRESH_TYPE_SHOP_INFO = 202;


    /**
     * TradeOrders
     */
    public static final int REDIS_REFRESH_TYPE_TRADE_ORDERS = 250;
    /**
     * TradeOrderItems
     */
    public static final int REDIS_REFRESH_TYPE_TRADE_ORDER_TIMES = 251;
    /**
     * TradeOrderCoupon
     */
    public static final int REDIS_REFRESH_TYPE_TRADE_ORDER_COUPON = 252;


    /**
     * OrdersBattleInfo
     */
    public static final int REDIS_REFRESH_TYPE_ORDERS_BATTLE_INFO = 300;
}