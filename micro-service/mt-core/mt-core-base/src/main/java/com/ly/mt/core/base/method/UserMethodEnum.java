package com.ly.mt.core.base.method;

/**
 * @Description 后台服务接口枚举
 * @Author taoye
 *//** @deprecated */
public enum UserMethodEnum {
    DYNAMIC_CODE_GET_DYNAMIC_CODE("dynamicCodeServiceImpl", "getDynamicCode", "获取动态验证码发送到手机"),

    LOGIN_NAME_LOGIN("loginServiceImpl", "nameLogin", "账号密码登录"),
    LOGIN_MOBILE_LOGIN("loginServiceImpl", "mobileLogin", "手机号登录"),

    USER_CHECK_LOGIN_NAME("userServiceImpl", "checkLoginName", "校验账号是否已注册"),
    USER_CHECK_USER_MOBILE("userServiceImpl", "checkUserMobile", "校验手机号是否已注册"),
    USER_M_H5_NAME_REGIST("userServiceImpl", "mH5NameRegist", "M端H5商城帐号密码注册"),
    USER_M_H5_MOBILE_REGIST("userServiceImpl", "mH5MobileRegist", "M端H5商城手机号注册"),
    USER_ACTIVITY_H5_REGIST("userServiceImpl", "activityH5Regist", "活动H5页面注册"),


    USER_INFO_AUTHENTICATION("userInfoServiceImpl", "authentication", "身份认证"),
    USER_INFO_UPLOAD_AVATAR_PIC("userInfoServiceImpl", "uploadAvatarPic", "头像修改"),
    USER_INFO_BIND_MOBILE("userInfoServiceImpl", "bindMobile", "绑定手机"),
    USER_INFO_UPDATE_INFO("userInfoServiceImpl", "updateInfo", "修改信息"),
    USER_INFO_LOAD_INFO_BY_LOGIN_ID("userInfoServiceImpl", "loadInfoByLoginId", "根据登录用户id获取用户信息"),
    USER_INFO_LOAD_INFO_BY_USER_MOBILE("userInfoServiceImpl", "loadInfoByUserMobile", "根据用户手机号获取用户信息"),
    USER_INFO_MODIFY_PASSWORD("userInfoServiceImpl", "modifyPassword", "修改密码"),
    USER_INFO_PERFECT_INFO("userInfoServiceImpl", "perfectInfo", "用户信息补全"),
    USER_INFO_UPDATE_LOGINNAME("userInfoServiceImpl", "updateLoginName", "修改用户名"),
    USER_INFO_SAVE_CLIENTID("userInfoServiceImpl", "saveClientId", "保存clientId"),


    USER_ADDRESS_SAVE("userAddressServiceImpl", "saveAddress", "用户中心-收货地址-添加接口"),
    USER_ADDRESS_LIST("userAddressServiceImpl", "listAddress", "用户中心-收货地址-查询列表接口"),
    USER_ADDRESS_SET_DEFAULT("userAddressServiceImpl", "defaultAddress", "用户中心-收货地址-设置默认接口"),
    USER_ADDRESS_UPDATE("userAddressServiceImpl", "updateAddress", "用户中心-收货地址-更新接口"),
    USER_ADDRESS_DELETE("userAddressServiceImpl", "deleteAddress", "用户中心-收货地址-删除接口"),
    USER_ADDRESS_GET_DELETE("userAddressServiceImpl", "getDefaultAddressByUserId", "用户中心-收货地址-获取默认收货地址接口"),
    USER_ADDRESS_GET_BY_ID("userAddressServiceImpl", "getAddressById", "用户中心-收货地址-根据地址的id获取一条收货地址接口"),

    B_SHOP_USER_CHECK_USER_MOBILE("shopUserServiceImpl","bShopUserCheckMobile","B端检测手机号码是否已注册"),
    B_SHOP_USER_REGIST("shopUserServiceImpl","bShopUserRegist","B端用户注册"),
    B_SHOP_USER_SET_PASSWORD("shopUserServiceImpl","bShopUserSetPassword","B端用户设置密码"),
    B_SHOP_USER_MODIFY_PASSWORD("shopUserServiceImpl","bShopUserModifyPassword","B端用户设置密码"),
    B_SHOP_USER_NAME_LOGIN("loginShopUserServiceImpl","nameLogin","b端用户名（手机号），密码登录"),
    B_SHOP_USER_MOBILE_LOGIN("loginShopUserServiceImpl","mobileLogin","b端手机号，验证码登录"),

    B_SHOP_USER_FINISH_INFO("shopUserInfoServiceImpl","finishInfo","B端用户基本信息补全"),
    B_SHOP_USER_SAVE_LICENSES("shopUserInfoServiceImpl","saveLicenses","B端用户保存营业执照"),
    B_SHOP_USER_SCAN_IDCARD("shopUserInfoServiceImpl","secondGenerationIdCardAnalysis","B端用户扫描保存身份证件照片"),
    B_SHOP_USER_SAVE_IDCARD("shopUserInfoServiceImpl","saveIdCard","B端用户保存商家身份信息"),
    B_SHOP_USER_THREE_ElEMENT_CHECK("shopUserInfoServiceImpl","threeElementCheck","B端用户三要素检测"),
    B_SHOP_USER_PORTRAIT_CHECK("shopUserInfoServiceImpl","portraitCompare","B端人脸活体检测比对"),
    B_SHOP_USER_BIND_WX("shopUserInfoServiceImpl","bindWx","B端绑定微信"),
    B_SHOP_USER_FEED_BACK("shopUserInfoServiceImpl","userFeedBack","用户反馈接口");
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


    UserMethodEnum(String serviceName, String functionName, String describe) {
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