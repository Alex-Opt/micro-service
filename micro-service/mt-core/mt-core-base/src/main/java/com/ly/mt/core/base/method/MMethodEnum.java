package com.ly.mt.core.base.method;

/**
 * @Description 手机端h5商城服务接口枚举
 * @Author taoye
 *//** @deprecated */
public enum MMethodEnum {
    DYNAMIC_CODE_GET_DYNAMIC_CODE("dynamicCodeServiceImpl", "getDynamicCode", "获取动态验证码发送到手机"),

    USER_CHECK_LOGIN_NAME("userServiceImpl", "checkLoginName", "校验账号是否已注册"),
    USER_CHECK_USER_MOBILE("userServiceImpl", "checkUserMobile", "校验手机号是否已注册"),
    USER_REGIST("userServiceImpl", "regist", "用户注册"),
    USER_MODIFY_PASSWORD("userServiceImpl", "modifyPassword", "修改密码"),

    USER_INFO_AUTHENTICATION("userInfoServiceImpl", "authentication", "身份认证"),
    USER_INFO_UPLOAD_AVATAR_PIC("userInfoServiceImpl", "uploadAvatarPic", "头像上传"),
    USER_INFO_BIND_MOBILE("userInfoServiceImpl", "bindMobile", "绑定手机"),
    USER_INFO_UPDATE_INFO("userInfoServiceImpl", "updateInfo", "修改信息"),
    USER_INFO_LOAD_INFO("userInfoServiceImpl", "loadInfo", "获取信息"),

    LOGIN_NAME_LOGIN("loginServiceImpl", "nameLogin", "账号密码登录"),
    LOGIN_MOBILE_LOGIN("loginServiceImpl", "mobileLogin", "手机号登录"),
    LOGIN_PERFECT_INFO("loginServiceImpl", "perfectInfo", "用户信息补全"),


    GOODS_SPU_QUERY("goodsSpuServiceImpl", "goodsSpuQuery", "商品spu数据-查询接口"),
    GOODS_SPU_CATEGROY("goodsSpuServiceImpl", "goodsSpuByCategroy", "根据类目查询商品spu-查询接口"),
    GOODS_CATEGROY_QUERY("goodsSpuServiceImpl", "queryCategroyList", "查询商品类目 -查询接口"),

    GOODS_SKU_QUERY_OK("goodsSkuServiceImpl", "getSkuInfoBySpuIdForOk", "商品sku数据-查询接口"),
    GOODS_SKU_QUERY("goodsSkuServiceImpl", "queryGoodsSku", "查询商品销售属性 -查询接口"),
    GOODS_SPU_AEROSOL("goodsSpuServiceImpl", "queryGoodsSpuByAerosol", "查询雾化弹类目商品spu-查询接口"),
    ROTATION_INFO_LIST("rotationInfoServiceImpl", "rotationInfoList", "轮播图查询"),

    COUPON_CODE_SAVE("couponInfoServiceImpl", "saveCouponCode", "用户领取优惠券 -添加接口"),
    COUPON_REDEMPTION_CODE("couponInfoServiceImpl", "redemptionCode", "兑换码生成接口 -添加接口"),
    COUPON_USER_LIST("couponInfoServiceImpl", "getUserCouponList", "用户优惠券查询接口"),


    BUYCAR_GET_CAR("buyCarServiceImpl", "getBuyCar", "购物车-从Redis中通过UserId获取用户的购物车"),
    BUYCAR_ADD_CAR_SKU("buyCarServiceImpl", "addCar", "购物车-增加购物车商品或数量"),
    BUYCAR_SUB_CAR_SKU_NUM("buyCarServiceImpl", "subCarSkuNum", "购物车-减少购物车商品数量"),
    BUYCAR_DEL_CAR_SKU("buyCarServiceImpl", "subCarSku", "购物车-删除购物车商品"),
    BUYCAR_SYNC_CAR_SKU("buyCarServiceImpl", "syncBuyCar", "购物车-同步购物车商品"),

    STORE_CAR_GET("storeCarServiceImpl", "getStoreCar", "收藏车-从Redis中通过UserId获取用户的收藏车"),
    STORE_CAR_SAVE("storeCarServiceImpl", "addStoreCar", "收藏车-增加收藏车中商品"),
    STORE_CAR_DEL("storeCarServiceImpl", "subStoreCarSku", "收藏车-删除收藏车中商品"),


    PUNCH_CARD_USER("punchCardServiceImpl", "punchCard", "用户打卡"),
    POINT_DATA_USER("punchCardServiceImpl", "queryPointDataByUserId", "查询用户打卡记录"),

    TRADE_ORDER_LIST("tradeOrdersServiceImpl", "queryOrderListByUserId", "订单管理-查询用户订单列表接口"),
    TRADE_ORDER_DETAIL("tradeOrdersServiceImpl", "queryOrderDetailByOrderId", "订单管理-查询一条订单详情接口"),
    TRADE_DISTRIBUTION_MODEL("tradeDistributionModelServiceImpl", "selectAll", "订单管理-查询一邮费"),
    TRADE_ORDER_HTML_SHOW("tradeOrdersServiceImpl", "queryPreOrderInfo", "订单管理-下单页面查询预订单信息接口"),
    TRADE_ORDER_GENERATE("tradeOrdersServiceImpl", "orderGenerate", "订单管理-下订单接口"),
    TRADE_ORDER_CHECK("tradeOrdersServiceImpl", "orderCheck", "订单管理-校验订单中使用的优惠活动是否有效接口"),
    TRADE_ORDER_CANCLE_ORDER("tradeOrdersServiceImpl", "cancleOrder", "订单管理-取消订单接口"),
    TRADE_ORDER_DELIVERY_INFO("tradeOrdersServiceImpl", "getOrderDeliveryInfo", "订单管理-查询订单发货物流信息接口"),
    TRADE_ORDER_PAGE_PROMOTION("tradeOrdersServiceImpl", "generatePagePromotionOrder", "订单管理-活动页面推广生成订单接口");
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

    MMethodEnum(String serviceName, String functionName, String describe) {
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