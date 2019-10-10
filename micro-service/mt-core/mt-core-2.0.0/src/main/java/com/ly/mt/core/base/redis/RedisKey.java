package com.ly.mt.core.base.redis;

/**
 * @Description redis缓存key枚举
 * @Author taoye
 */
public enum RedisKey {
    REDIS_TOKEN_LOGIN_MALL_H5("token:login:mall:h5:", "H5商城登录token"),
    REDIS_TOKEN_LOGIN_SYSTEM("token:login:system:", "系统后台登录token"),
    REDIS_TOKEN_API_FN("token:api:fn", "蜂鸟接口授权token"),

    REDIS_ERROR_LOGIN_SYSTEM("error:login:system:", "系统后台登录错误次数"),

    REDIS_CODE_MALL_H5_REGIST("code:dynamic:mall:h5:regist:", "H5商城注册验证码"),
    REDIS_CODE_MALL_H5_LOGIN("code:dynamic:mall:h5:login:", "H5商城登录验证码"),
    REDIS_CODE_MALL_H5_PASSWORD("code:dynamic:mall:h5:password:", "H5商城修改密码验证码"),
    REDIS_CODE_MALL_H5_BIND_MOBILE("code:dynamic:mall:h5:bind:mobile:", "H5商城信息变更验证码"),

    REDIS_CODE_ACTIVITY_HN_REGIST("code:dynamic:activity:hn:regist", "海南门店活动注册验证码"),
    REDIS_CODE_ACTIVITY_HN_GET_PRODUCT("code:dynamic:activity:hn:get:product", "海南门店活动取货验证码"),
    REDIS_CODE_ACTIVITY_APPLY_SIGN_UP_CABINET("code:dynamic:activity:apply:sign_up:cabinet:", "格子柜入驻报名验证码"),

    REDIS_TOKEN_BLUETOOTH_LOGIN("token:login:bluetooth", "蓝牙APP登录token"),
    REDIS_CODE_BLUETOOTH_REGIST("code:dynamic:bluetooth:regist", "蓝牙APP注册验证码"),
    REDIS_CODE_BLUETOOTH_LOGIN("code:dynamic:bluetooth:login", "蓝牙APP登录验证码"),
    REDIS_CODE_BLUETOOTH_PASSWORD("code:dynamic:bluetooth:password", "蓝牙APP修改密码验证码"),
    REDIS_CODE_BLUETOOTH_OLD_PHONE("code:dynamic:bluetooth:old:phone", "蓝牙APP更换绑定手机-旧手机号验证码"),
    REDIS_CODE_BLUETOOTH_NEW_PHONE("code:dynamic:bluetooth:new:phone", "蓝牙APP更换绑定手机-新手机号验证码"),


    REDIS_ENTITY_USER_ID("entity:user:id:", "用户信息id缓存"),
    REDIS_ENTITY_USER_MOBILE("entity:user:mobile:", "用户信息mobile缓存"),
    REDIS_ENTITY_USER_LOGIN_NAME("entity:user:login_name:", "用户信息login_name缓存"),

    REDIS_ENTITY_PAY_ORDER_AL("pay:order:al:", "阿里支付订单信息"),
    REDIS_ENTITY_PAY_ORDER_WX("pay:order:wx:", "微信支付订单信息"),

    //格子柜Redis相关服务
    REDIS_GZG_CODE_CABINET("gzg:code:cabinet:","生成订单，锁定格子柜具体格子号商品"),
    REDIS_GZG_CODE_LOGIN("code:dynamic:gzg:login:","格子柜登录验证码"),

    REDIS_RUNNER_TREE_ID("entity:runner:tree:", "淘金五级别联系人"),

    REDIS_ENTITY_CAR_ID("entity:car:id:", "购物车买家id缓存"),
    REDIS_ENTITY_STORE_ID("entity:store:id:", "收藏车买家id缓存"),

    REDIS_ENTITY_SHOP_CAR_ID("shop:car:id:", "购物车商家id缓存"),
    REDIS_ENTITY_SHOP_STORE_ID("shop:store:id:", "收藏车商家id缓存"),

    REDIS_GOODS_SELLER_COUNT("goods:seller:number","某商品的月销量的缓存"),

    REDIS_USER_WX_OPENID("user:ex:openid","用户授权微信登陆后用户的openId缓存");

    private final String key;
    private final String desc;

    RedisKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }
}