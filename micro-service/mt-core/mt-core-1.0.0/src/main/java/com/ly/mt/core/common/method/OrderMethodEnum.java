package com.ly.mt.core.common.method;

/**
 * @Description 订单中心服务接口枚举
 * @Author taoye
 */
public enum OrderMethodEnum {
    TRADE_ORDER_LIST("tradeOrdersServiceImpl", "queryOrderListByUserId", "订单管理-查询用户订单列表接口"),
    TRADE_ORDER_DETAIL("tradeOrdersServiceImpl", "queryOrderDetailByOrderId", "订单管理-查询一条订单详情接口"),
    TRADE_ORDER_HTML_SHOW("tradeOrdersServiceImpl", "queryPreOrderInfo", "订单管理-下单页面查询预订单信息接口"),
    TRADE_ORDER_GENERATE("tradeOrdersServiceImpl", "orderGenerate", "订单管理-下订单接口"),
    TRADE_ORDER_CHECK("tradeOrdersServiceImpl", "orderCheck", "订单管理-校验订单中使用的优惠活动是否有效接口"),
    TRADE_ORDER_CANCEL_ORDER("tradeOrdersServiceImpl", "cancelOrder", "订单管理-取消订单接口"),
    TRADE_ORDER_DELIVERY_INFO("tradeOrdersServiceImpl", "getOrderDeliveryInfo", "订单管理-查询订单发货物流信息接口"),
    TRADE_ORDER_PAGE_PROMOTION("tradeOrdersServiceImpl", "generatePagePromotionOrder", "订单管理-活动页面推广生成订单接口"),
    TRADE_PRE_ORDER_STORY("tradeOrdersServiceImpl", "preOrderByStoryCar", "订单管理-从Redis中通过UserId获取用户的商品，实现预订单功能"),
    TRADE_ORDER_AGREE_NEXT_DAY_REACH("rabbitMqServiceImpl", "agreeNextDayOrder", "订单管理-用户确认将一小时达订单转为次日达订单。"),

    //到家b-我的
    MY_INFO_SHOP_PURCHASE_LIST("bOrderMyInfoServiceImpl", "getShopPurchaseList", "查询进货订单列表"),
    MY_INFO_SHOP_PURCHASE_STACK("bOrderMyInfoServiceImpl", "getShopPurchaseStack", "查询我的进货详情"),
    MY_INFO_SHOP_COUPON_INFO("bOrderMyInfoServiceImpl", "getMyShopCouponInfo", "查询我的优惠券信息"),
    MY_INFO_USER_ACCOUNT_INFO("bOrderMyInfoServiceImpl", "userAccountInfo", "查询用户账户信息接口"),
    MY_INFO_SHOP_DEFAULT_ADDRESS("bOrderMyInfoServiceImpl", "getShopDefaultAddress", "查询店铺默认地址接口"),
    MY_INFO_SHOP_ADDRESS_BY_ID("bOrderMyInfoServiceImpl", "getShopAddressById", "根据店铺地址id查询店铺地址接口"),
    MY_INFO_UPDATE_LOGIN_NAME("bOrderMyInfoServiceImpl","updateLoginName","根据用户-userId修改用户名-loginName"),
    MY_INFO_SHOP_ADDRESS_LIST("bOrderMyInfoServiceImpl","getShopAddressList","查询店铺的收货地址列表"),
    MY_INFO_ADD_SHOP_ADDRESS("bOrderMyInfoServiceImpl","addShopAddress","新增店铺收货地址"),
    MY_INFO_UPDATE_DEFAULT_SHOP_ADDRESS("bOrderMyInfoServiceImpl","updateDefaultShopAddress","更新店铺默认收货地址"),
    MY_INFO_UPDATE_SHOP_ADDRESS("bOrderMyInfoServiceImpl","updateShopAddress","修改店铺收货地址"),
    MY_INFO_MODIFY_MOBILE("bOrderMyInfoServiceImpl","modifyMobile","修改手机号接口"),

    PURCHASE_CHECK_PURCHASE_ORDER("bOrderPurchasePaymentServiceImpl","checkPurchaseOrder","进货单支付前订单校验接口"),


    ORDER_BATTLE_QUERY_LIST("bOrderBattleServiceImpl","queryOrderBattleList","查询小B抢单的列表信息"),
    ORDER_BATTLE_GRAB_ORDER("bOrderBattleServiceImpl","grabOrder","抢单接口"),
    ORDER_BATTLE_RELEASE_GRAB_ORDER("bOrderBattleServiceImpl","releaseGrabOrder","释放抢单接口"),
    ORDER_BATTLE_CANCEL_GRAB_ORDER("bOrderBattleServiceImpl","cancelGrabOrder","取消订单接口"),
    ORDER_BATTLE_CHECK_GOODS("bOrderBattleServiceImpl","checkGoodsEffectiveness","通过扫描校验商品的有效性接口"),
    ORDER_BATTLE_CALL_BACK_METHOD("bOrderBattleServiceImpl","callBackMethodByHummingBird","蜂鸟推送订单回调接口"),
    ORDER_BATTLE_CONFIRM_FINISH_ORDER("bOrderBattleServiceImpl","confirmFinishOrder","确认收货接口"),
    TRADE_ORDER_ADD_REFUND("tradeOrderRefundServiceImpl","addTradeOrderRefund","申请售后/退货接口"),
    TRADE_ORDER_GET_BUYER_REFUND_LIST("tradeOrderRefundServiceImpl","getBuyerRefunds","根据买家id获取退货列表"),
    TRADE_ORDER_UPDATE_REFUND_STATUS("tradeOrderRefundServiceImpl","updateRefundStatus","根据id更新退货单状态"),
    TRADE_ORDER_UPDATE_REFUND_INFO("tradeOrderRefundServiceImpl","updateRefundInfo","根据id更新退货单信息"),
    TRADE_ORDER_UPDATE_REFUND_CANCEL("tradeOrderRefundServiceImpl","updateRefundCancel","取消退货单申请"),
    TRADE_ORDER_GET_REFUND_ITEM("tradeOrderRefundServiceImpl","getRefundItem","根据退货单Id查看详情"),
    ORDER_BATTLE_INFO("bOrderBattleServiceImpl","getOrderBattleInfo","查询商品校验的超时时间,订单状态等信息接口"),
    ORDER_BATTLE_QUERY_ORDER_DETAIL("bOrderBattleServiceImpl","queryOrderDetail","查询订单商品详情接口"),
    ORDER_BATTLE_SEND_ORDER("bOrderBattleServiceImpl","sendOrderToHummingBird","查询订单商品详情接口"),
    ORDER_BATTLE_QUERY_CARRIER_POSITION("bOrderBattleServiceImpl","queryCarrierPosition","查询骑手位置接口"),;


    /**
     * @Description 接口名字
     */
    private final String serviceName;
    /**
     * @Description 方法名字
     */
    private final String functionName;
    /**
     * @Description 方法描述
     */
    private final String describe;

    OrderMethodEnum(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }
}