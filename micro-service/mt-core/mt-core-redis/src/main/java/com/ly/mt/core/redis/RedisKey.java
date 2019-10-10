package com.ly.mt.core.redis;

/**
 * redis缓存key枚举 规范如下
 * 1、枚举中不再设置desc描述值，如果对枚举说明请使用注释解决
 * 2、数据类缓存枚举名命：REDIS_表名_缓存类型(String/List/Entity)_键
 * 3、业务类缓存枚举名命：REDIS_模块名_缓存类型(String/List/Entity)_作用_键
 * 4、枚举必须按照对应关系放入两个横线注释中间
 *
 * @author taoye
 */
public enum RedisKey {
/*____________________________________________________mt-core-data____________________________________________________*/
    /**
     * ActivitySecurityCode
     */
    REDIS_ACTIVITY_SECURITY_CODE_ENTITY_SECURITY_CODE("activity_security_code:entity:security_code:"),

    /**
     * BasicArea
     */
    REDIS_BASIC_AREA_LIST_M_PID("basic_area:list:m_pid:"),
    REDIS_BASIC_AREA_ENTITY_ID("basic_area:entity:id:"),
    REDIS_BASIC_AREA_ENTITY_M_ID("basic_area:entity:m_id:"),

    /**
     * BasicFunc
     */
    REDIS_BASIC_FUNC_ENTITY_ID("basic_func:entity:id:"),
    REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_FUNC_LEVEL("basic_func:list:project_type:func_level:"),
    REDIS_BASIC_FUNC_LIST_PROJECT_TYPE_PARENT_ID("basic_func:list:project_type:parent_id:"),
    REDIS_BASIC_FUNC_STRING_MAX_SORT_PARENT_ID("basic_func:string:max_sort:parent_id:"),

    /**
     * BasicRole
     */
    REDIS_BASIC_ROLE_ENTITY_ID("basic_role:entity:id:"),
    REDIS_BASIC_ROLE_LIST_PARENT_ID("basic_role:list:parent_id:"),

    /**
     * BasicRoleRight
     */
    REDIS_BASIC_ROLE_RIGHT_LIST_ROLE_ID_RIGHT_TYPE("basic_role_right:list:role_id:right_type:"),

    /**
     * BasicUserRole
     */
    REDIS_BASIC_USER_ROLE_LIST_ROLE_ID("basic_user_role:list:role_id:"),
    REDIS_BASIC_USER_ROLE_LIST_USER_ID("basic_user_role:list:user_id:"),

    /**
     * CabinetCategroy
     */
    REDIS_CABINET_CATEGROY_ENTITY_ID("cabinet_categroy:entity:id"),

    /**
     * CabinetPlan
     */
    REDIS_CABINET_PLAN_ENTITY_ID("cabinet_plan:entity:id:"),

    /**
     * CouponInfo
     */
    REDIS_COUPON_INFO_ENTITY_ID("coupon_info:entity:id:"),

    /**
     * GoodsCategroyInfo
     */
    REDIS_GOODS_CATEGROY_INFO_ENTITY_ID("goods_categroy_info:entity:id:"),
    REDIS_GOODS_CATEGROY_INFO_LIST_PARENT_ID("goods_categroy_info:list:parent_id:"),

    /**
     * GoodsSkuInfo
     */
    REDIS_GOODS_SKU_INFO_ENTITY_ID("goods_sku_info:entity:id:"),

    /**
     * GoodsSkuPicture
     */
    REDIS_GOODS_SKU_PICTURE_ENTITY_SKU_ID("goods_sku_picture:entity:sku_id:"),

    /**
     * GoodsSkuCode
     */
    REDIS_GOODS_SKU_CODE_ENTITY_SKU_ID("goods_sku_code:entity:sku_id:"),

    /**
     * GoodsSpuInfo
     */
    REDIS_GOODS_SPU_INFO_ENTITY_ID("goods_spu_info:entity:id:"),

    /**
     * GzgInfo
     */
    REDIS_GZG_INFO_ENTITY_CODE("gzg_info:entity:code:"),

    /**
     * GzgHotel
     */
    REDIS_GZG_HOTEL_ENTITY_ID("gzg_hotel:entity:id:"),

    /**
     * GzgOrder
     */
    REDIS_GZG_ORDER_ENTITY_ID("gzg_order:entity:id:"),

    /**
     * GzgOrderItem
     */
    REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID("gzg_order_item:list:order_id:"),

    /**
     * OrdersBattleInfo
     */
    REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID("orders_battle_info:entity:order_id:"),

    /**
     * RedisRefresh
     */
    REDIS_REDIS_REFRESH_STRING_ID_REFRESH_TYPE_TRIGGER_TYPE_ID("redis_refresh:string:refresh_type:trigger_type:id:"),

    /**
     * ShopInfo
     */
    REDIS_SHOP_INFO_ENTITY_ID("shop_info:entity:id:"),

    /**
     * ShopPurchasesItems
     */
    REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID("shop_info:entity:id:"),

    /**
     * ShopExclusiveDiscount
     */
    REDIS_SHOP_EXCLUSIVE_DISCOUNT_ENTITY_SHOP_ID("shop_exclusive_discount:entity:shop_d:"),

    /**
     * TradeOrders
     */
    REDIS_TRADE_ORDERS_ENTITY_ID("trade_orders:entity:id:"),

    /**
     * TradeOrderCoupon
     */
    REDIS_TRADE_ORDER_COUPON_LIST_COUPON_TYPE_ORDER_ID("trade_order_coupon:list:coupon_type:order_id:"),

    /**
     * TradeOrderItems
     */
    REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID("trade_order_items:list:order_id:"),

    /**
     * OrderHbpView
     */
    REDIS_ORDER_HBP_VIEW_ENTITY_ID("order_hbp_view:entity:id:"),

    /**
     * OrderHbsView
     */
    REDIS_ORDER_HBS_VIEW_ENTITY_ID("order_hbs_view:entity:id:"),

    /**
     * OrderHcView
     */
    REDIS_ORDER_HC_VIEW_ENTITY_ID("order_hc_view:entity:id:"),

    /**
     * User
     */
    REDIS_USER_ENTITY_PROJECT_TYPE_ID("user:entity:project_type:id:"),
    REDIS_USER_ENTITY_PROJECT_TYPE_MOBILE("user:entity:project_type:login_name:"),
    REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME("user:entity:project_type:login_name:"),
    REDIS_USER_ENTITY_LOGIN_WX_OPEN_ID("user:entity:login:wx_open_id:"),
    REDIS_USER_LIST_ROLE_ID("user:list:role_id:"),

    /**
     * UserAddress
     */
    REDIS_USER_ADDRESS_ENTITY_ID("user_address:entity:id:"),
/*____________________________________________________mt-core-data____________________________________________________*/





/*____________________________________________________mt-core-fn______________________________________________________*/
    /**
     * 蜂鸟接口业务缓存
     */
    REDIS_FN_STRING_TOKEN("fn:string:token"),
/*____________________________________________________mt-core-fn______________________________________________________*/





/*____________________________________________________mt-mis__________________________________________________________*/
    /**
     * 登录token
     */
    REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME("mis:string:login_token:login_name:"),

    /**
     * 登录错误次数
     */
    REDIS_MIS_STRING_LOGIN_ERROR_COUNT_SESSION_ID("mis:string:login_error_count:session_id:"),

    /**
     * 上传追踪码文件内容缓存
     */
    REDIS_MIS_LIST_TRACKING_CODE_UPLOAD_TEMP_KEY("mis:list:tracking_code_upload:temp_key:"),
/*____________________________________________________mt-mis__________________________________________________________*/


    REDIS_CABINET_CASH_RECORD_STRING_USER_ID("cabinet_cash_record:string:user_id:"),




/*____________________________________________________mt-activity_____________________________________________________*/
    /**
     * 登录token
     */
    REDIS_ACTIVITY_STRING_LOGIN_TOKEN_LOGIN_NAME("activity:string:login_token:login_name:"),
/*____________________________________________________mt-activity_____________________________________________________*/





/*____________________________________________________deprecated______________________________________________________*/
    /**
     * @deprecated 为避免项目之间有相同登录帐号引起bug, 弃用该缓存, 使用REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME
     */
    REDIS_USER_ENTITY_LOGIN_NAME("user:entity:login_name:"),

    /**
     * @deprecated 为避免项目之间有相同手机号引起bug, 弃用该缓存, 使用REDIS_USER_ENTITY_PROJECT_TYPE_MOBILE
     */
    REDIS_USER_ENTITY_MOBILE("user:entity:mobile:"),

    /**
     * @deprecated 弃用该缓存, 使用REDIS_USER_ENTITY_PROJECT_TYPE_ID
     */
    REDIS_USER_ENTITY_ID("user:entity:id:"),

    /**
     * @deprecated 此缓存缺失关键信息，请使用REDIS_USER_ENTITY_LOGIN_WX_OPEN_ID
     */
    REDIS_USER_ENTITY_LOGIN_OPEN_ID("user:entity:login:open_id:"),

    /**
     * @deprecated 规范修改，请使用REDIS_FN_STRING_TOKEN
     */
    REDIS_TOKEN_API_FN("token:api:fn", "蜂鸟接口授权token"),

    REDIS_TOKEN_LOGIN_MALL_H5("token:login:mall:h5:", "H5商城登录token"),
    REDIS_CODE_MALL_H5_REGIST("code:dynamic:mall:h5:regist:", "H5商城注册验证码"),
    REDIS_CODE_MALL_H5_LOGIN("code:dynamic:mall:h5:login:", "H5商城登录验证码"),
    REDIS_CODE_MALL_H5_PASSWORD("code:dynamic:mall:h5:password:", "H5商城修改密码验证码"),
    REDIS_CODE_MALL_H5_BIND_MOBILE("code:dynamic:mall:h5:bind:mobile:", "H5商城信息变更验证码"),
    REDIS_CODE_HOME_B_USER_REGIST("code:dynamic:homeb:h5:regist:", "到家b端注册验证码"),
    REDIS_CODE_HOME_B_USER_LONGIN("code:dynamic:homeb:h5:login:", "到家b端登录验证码"),
    REDIS_CODE_HOME_B_USER_PASSWORD("code:dynamic:homeb:h5:password:", "到家b端设置密码验证码"),
    REDIS_CODE_ACTIVITY_HN_REGIST("code:dynamic:activity:hn:regist", "海南门店活动注册验证码"),
    REDIS_CODE_ACTIVITY_HN_GET_PRODUCT("code:dynamic:activity:hn:get:product", "海南门店活动取货验证码"),
    REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET("code:dynamic:activity:apply:sign_up:cabinet:", "格子柜入驻报名验证码"),
    REDIS_TOKEN_BLUETOOTH_LOGIN("token:login:bluetooth", "蓝牙APP登录token"),
    REDIS_CODE_BLUETOOTH_REGIST("code:dynamic:bluetooth:regist", "蓝牙APP注册验证码"),
    REDIS_CODE_BLUETOOTH_LOGIN("code:dynamic:bluetooth:login", "蓝牙APP登录验证码"),
    REDIS_CODE_BLUETOOTH_PASSWORD("code:dynamic:bluetooth:password", "蓝牙APP修改密码验证码"),
    REDIS_CODE_BLUETOOTH_OLD_PHONE("code:dynamic:bluetooth:old:phone", "蓝牙APP更换绑定手机-旧手机号验证码"),
    REDIS_CODE_BLUETOOTH_NEW_PHONE("code:dynamic:bluetooth:new:phone", "蓝牙APP更换绑定手机-新手机号验证码"),
    REDIS_WX_LOGIN_CODE_AUTH("wx:login:code:auth:", "微信小程序登录凭证校验返回值缓存"),
    REDIS_WX_LOGIN_TOKEN("wx:login:token:", "微信小程序登录后token对应的缓存用户信息"),
    REDIS_WX_AUTH_TOKEN("wx:auth:token:", "微信网页授权token缓存"),
    REDIS_WX_AUTH_REFRESH_TOKEN("wx:auth:refresh:token:", "微信网页授权refresh_token缓存"),
    REDIS_WX_APPLET_ACCESS_TOKEN("wx:applet:access:token:", "微信小程序缓存access_token"),
    REDIS_WX_APPLET_FORM_ID("wx:applet:formId:", "微信小程序缓存不同微信openid的formId"),
    REDIS_ENTITY_USER_ID("entity:user:id:", "用户信息id缓存"),
    REDIS_ENTITY_USER_MOBILE("entity:user:mobile:", "用户信息mobile缓存"),
    REDIS_ENTITY_USER_LOGIN_NAME("entity:user:login_name:", "用户信息login_name缓存"),
    REDIS_ENTITY_USER_LOGIN_TOKEN("entity:user:login:token:", "登录token"),
    REDIS_ENTITY_USER_LOGIN_OPEN_ID("entity:user:login:openid:", "微信小程序登录用户openid等信息缓存"),
    REDIS_ENTITY_PAY_ORDER_AL("pay:order:al:", "阿里支付订单信息"),
    REDIS_ENTITY_PAY_ORDER_WX("pay:order:wx:", "微信支付订单信息"),
    //gzg格子柜Redis相关服务
    REDIS_CABINET_B_LOGIN("token:login:cabinet-b:", "格子柜B端登录TOKEN"),
    REDIS_CODE_CABINET_B_LOGIN("code:dynamic:cabinet-b:login", "格子柜B端登录验证码"),
    REDIS_GZG_CODE_CABINET("gzg:code:cabinet:", "生成订单，锁定格子柜具体格子号商品"),
    REDIS_GZG_CODE_LOGIN("code:dynamic:gzg:login:", "格子柜登录验证码"),
    REDIS_GZG_SKU_SELL_NUM("gzg:skuid:", "格子柜skuid货物卖出数量"),
    REDIS_GZG_OPEN_DEVICE_LOGIN("gzg:open:device:code:login:", "格子柜维护人员打开设备登录"),
    REDIS_GZG_OPEN_DEVICE_LOGIN_TOKEN("gzg:open:device:login:token:", "格子柜维护人员打开设备登录后获取的token"),
    REDIS_RUNNER_TREE_ID("entity:runner:tree:", "淘金五级别联系人"),
    REDIS_ENTITY_CAR_ID("entity:car:id:", "购物车买家id缓存"),
    REDIS_ENTITY_STORE_ID("entity:store:id:", "收藏车买家id缓存"),
    REDIS_ENTITY_SHOP_CAR_ID("shop:car:id:", "购物车商家id缓存"),
    REDIS_ENTITY_SHOP_LADDER_PRICE("shop:purchases:ladder", "商家进货阶梯价配置"),
    REDIS_ENTITY_GOODS_SPU_INFO_ID("shop:goods:id:", "商品spuId"),
    REDIS_GOODS_SELLER_COUNT("goods:seller:number", "某商品的月销量的缓存"),
    REDIS_USER_WX_OPENID("user:ex:openid:", "用户授权微信登陆后用户的openId缓存"),
    LOGIN_TOKEN_REDIS("login:token:", "登录token"),
    LOGIN_CODE_REDIS("login:code:", "登录验证码"),
    LOGIN_CODE_ERROR_REDIS("login:error:", "验证码错误次数"),
    MAGE_VERIFY_CODE_REGIST("verify:imageVerifyCode:regist", "登录图片验证码"),
    IMAGE_VERIFY_CODE_LOGIN("verify:imageVerifyCode:login", "注册图片验证码"),
    IMAGE_VERIFY_CODE_RESET_PASSWORD("verify:imageVerifyCode:modifypasswd", "重置密码图片验证码"),
    IMAGE_VERIFY_CODE_BIND_MOBILE("verify:imageVerifyCode:binbMobile", "绑定手机号图片验证码"),
    ENTITY_USER_MOBILE_REDIS("entity:user:mobile:", "用户信息根据mobile缓存"),
    ENTITY_USER_SHARE_REDIS("entity:user:wxshare:", "用户分享缓存"),
    ENTITY_SHOP_USER_MOBILE_REDIS("entity:shopUser:mobile:", "b用户店铺信息根据mobile缓存"),
    ENTITY_SHOP_USER_SENDADDRESS_REDIS("entity:shopUser:sendAddress:", "b端用户发货地址信息缓存"),
    ENTITY_USER_LOGIN_NAME_REDIS("entity:user:loginName:", "用户信息根据loginName缓存"),
    ENTITY_CAR_BUYER_ID_REDIS("entity:car:buyer:id:", "购物车买家id缓存"),
    ENTITY_STORE_ID_REDIS("entity:store:id:", "收藏车买家id缓存"),
    ENTITY_ORDER_PAYMENT_ALIPAY("entity:order:payment:alipay:", "阿里支付订单"),
    ENTITY_ORDER_PAYMENT_WXPAY("entity:order:payment:wxpay:", "微信支付订单"),
    ORDER_BATTLE_REDID("order:battle:info:", "抢单号-供小B抢单的抢单信息缓存"),
    ORDER_BATTLE_CHECK_GOODS("order:battle:check:goods:", "校验商品记录信息缓存"),
    REDIS_TOKEN_FN("token.fn", "蜂鸟token"),
    GZG_REPLENISH_ORDER_LOCK("gzg:replenish:order:lock:", "补货订单锁定"),
    GZG_REPLENISH_PROGRESS("gzg:replenish:progress:", "补货流程"),
    GZG_GOODS_CHECK("gzg:goods:check:", "商品校验,格子柜校验"),
    LOGIN_ACTIVITY_SHOP_TOKEN("login:activity:shop:token:", "门店活动管理员登录token"),
    LOGIN_ACTIVITY_SHOP_CODE("login:activity:shop:code:", "门店活动登录验证码"),
    ACTIVITY_BUYER_ORDER_CODE("activity:buyer:order:code:", "门店活动买家下单"),
    REDIS_OFO_TIME_IP("open:ofo:time:", "记录ofo  ip调用时间"),
    REDIS_TOKEN_ANUBIS("token.anubis", "蜂鸟token"),
    COUPON_NEW_USER_SPREE("coupon:new:user:spree:", "新人大礼包优惠券数量值"),
    COUPON_USER_SPREE_COLLECTION("coupon:user:spree:collection:", "新人大礼包已经领取的用户记录"),
    REDIS_COUPON_SPREE_INFO("redis:coupon:spree:info", "缓存优惠券大礼包数据信息"),
    REDIS_COUPON_CODE_REDEMPTION("redis:coupon:code:redemption", "缓存以兑换优惠券的用户数据"),
    USER_CERTIFICATION_SAVE_BY_USER_ID("redis:user:certification:info:", "到家C-根据用户id缓存实名认证信息"),
    //针对不同方法的防抖，重复点击临时缓存--------------start
    REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER("redis:repeat:click:action:generateOrder:", "到家C下单接口防重复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_ORDER_DELIVERY_INFO("redis:repeat:click:action:orderDeliveryInfo:", "到家C查询订单物流信息接口防重复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_QUERY_PRE_ORDER_INFO("redis:repeat:click:action:queryPreOrderInfo:", "到家C预订单查询接口防重复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_CATEGORY("redis:repeat:click:action:activity:safe:category:", "MT-ACTIVITY模块防过快频率访问的限制用的缓存"),
    REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_USERREGIST("redis:repeat:click:action:activity:safe:userRegist:", "MT-ACTIVITY模块防复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_APPLY_WITHDRAWAL("redis:repeat:click:action:applyWithdrawal:", "格子柜B申请提现接口防重复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_UPDATE_ADDRESS("redis:repeat:click:action:updateAddress:", "到家C用户新增地址-防重复点击缓存"),
    REDIS_REPEAT_CLICK_ACTION_ADD_ADDRESS("redis:repeat:click:action:addAddress:", "到家C用户新增地址-防重复点击缓存");
    //针对不同方法的防抖，重复点击临时缓存--------------end
    private final String key;
    private final String desc;

    RedisKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    RedisKey(String key) {
        this.key = key;
        this.desc = null;
    }

    public String getKey() {
        return key;
    }
}