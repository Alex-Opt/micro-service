package com.ly.mt.core.common.constant;

/**
 * @deprecated 使用mt-core-redis中的RedisKey
 */
public enum RedisEnum {
    LOGIN_TOKEN_REDIS("login:token:", "登录token"),
    LOGIN_CODE_REDIS("login:code:", "登录验证码"),
    LOGIN_CODE_ERROR_REDIS("login:error:", "验证码错误次数"),

    MAGE_VERIFY_CODE_REGIST("verify:imageVerifyCode:regist","登录图片验证码"),
    IMAGE_VERIFY_CODE_LOGIN("verify:imageVerifyCode:login","注册图片验证码"),
    IMAGE_VERIFY_CODE_RESET_PASSWORD("verify:imageVerifyCode:modifypasswd","重置密码图片验证码"),
    IMAGE_VERIFY_CODE_BIND_MOBILE("verify:imageVerifyCode:binbMobile","绑定手机号图片验证码"),

    ENTITY_USER_MOBILE_REDIS("entity:user:mobile:", "用户信息根据mobile缓存"),
    ENTITY_SHOP_USER_MOBILE_REDIS("entity:shopUser:mobile:", "b用户店铺信息根据mobile缓存"),
    ENTITY_SHOP_USER_SENDADDRESS_REDIS("entity:shopUser:sendAddress:", "b端用户发货地址信息缓存"),

    ENTITY_USER_LOGIN_NAME_REDIS("entity:user:loginName:", "用户信息根据loginName缓存"),
    ENTITY_CAR_BUYER_ID_REDIS("entity:car:buyer:id:", "购物车买家id缓存"),
    ENTITY_STORE_ID_REDIS("entity:store:id:", "收藏车买家id缓存"),

    ENTITY_AREA_ID("entity:area:id:", "管易行政区域id"),
    REDIS_BASIC_AREA_ENTITY_M_ID("entity:area:mid:", "H5商城行政区域id"),

    ENTITY_ORDER_PAYMENT_ALIPAY("entity:order:payment:alipay:", "阿里支付订单"),
    ENTITY_ORDER_PAYMENT_WXPAY("entity:order:payment:wxpay:", "微信支付订单"),

    ORDER_BATTLE_REDID("order:battle:info:","抢单号-供小B抢单的抢单信息缓存"),
    ORDER_BATTLE_CHECK_GOODS("order:battle:check:goods:","校验商品记录信息缓存"),

    REDIS_TOKEN_FN("token.fn", "蜂鸟token"),

    GZG_REPLENISH_ORDER_LOCK("gzg:replenish:order:lock:", "补货订单锁定"),
    GZG_REPLENISH_PROGRESS("gzg:replenish:progress:","补货流程"),
    GZG_GOODS_CHECK("gzg:goods:check:","商品校验,格子柜校验"),

    LOGIN_ACTIVITY_SHOP_TOKEN("login:activity:shop:token:","门店活动管理员登录token"),
    LOGIN_ACTIVITY_SHOP_CODE("login:activity:shop:code:","门店活动登录验证码"),
    ACTIVITY_BUYER_ORDER_CODE("activity:buyer:order:code:","门店活动买家下单"),

    REDIS_TOKEN_ANUBIS("token.anubis", "蜂鸟token"),

    REDIS_GOODS_SELLER_COUNT("goods:seller:number","某商品的月销量的缓存");

    private final String key;
    private final String desc;

    RedisEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }
}