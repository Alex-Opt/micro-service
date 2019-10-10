package com.ly.mt.core.common.method;


/**
 * @description
 * 活动模块  服务bean-method枚举
 * @author panjingtian
 * @date 2019/6/13 10:35 PM
 */
public enum ActivityMethodEnum {

    /**************************  活动模块商家门店方法  ****************************/
    SHOP_SEND_ACTIVITY("shopManagerOperationImpl","sendActivityDynamicCode","活动服务门店验证注册"),
    SHOP_REGIST_ACTIVITY("shopManagerOperationImpl","registShopActivity","门店活动注册"),
    //SHOP_REGIST_INTOSHOP("shopManagerOperationImpl","intoShop","活动页商品展示"),
    SHOP_LOGIN_CODE("shopManagerOperationImpl","sendLoginCode","门店活动商家登录验证码"),
    SHOP_INFO_FIND("shopManagerOperationImpl","findShopByOperator","根据代理商查询门店id"),
    SHOP_ADMIN_LOGIN("shopManagerOperationImpl","login","门店活动管理员登录"),
    SHOP_GET_ACTIVITY("shopManagerOperationImpl","findAttDetailById","查询门店信息"),
    SHOP_SHOW_PRODUCT("shopManagerOperationImpl","showProducts","展示当前门店下的商品"),
    SHOP_GET_ATT_DETAIL_ID("shopManagerOperationImpl","getAttdetailId","获取活动门店信息主键id"),
    SHUO_GET_OPERATORS("shopManagerOperationImpl","findOperator","查询全部代理商"),
    SHUO_GET_FIND_MANAGER("shopManagerOperationImpl","findManager","查询门店负责人，做校验账号用"),
    SHOP_ADD_OPERATOR("shopManagerOperationImpl","addOperator","添加代理商"),
    SHOP_ADD_SHOPINFO("shopManagerOperationImpl","addShop","添加门店"),
    /******************************** 活动模块买家方法 *************************************/
    BUYER_SEND_ORDER_DYNAMICCODE("hdShopUserServiceImpl","sendCode","发送下单验证码"),
    BUYER_REGIST_ORDER("hdShopUserServiceImpl","registAndOrder","用户注册耦合下单"),
    BUYER_MSG("hdShopUserServiceImpl","getUserByAttIdPhone","获取用户信息"),
    /************************** 活动模块订单方法 ***********************/
    SHOP_ORDER_CHANGE("shopAttOrderServiceImpl","shopChangeOrderStatus","修改订单状态"),
    SHOP_ORDER_FINDALL("shopAttOrderServiceImpl","shopOrders","查询所有订单"),
    BUYER_ORDER_FIND("shopAttOrderServiceImpl","buyerGetOrder","用户订单查询"),

    WECHAT_USER_ADD("wechatUserServiceImpl","addWechatUser","保存微信用户数据"),
    HELP_MASTER_BY_USERID("wechatHelpServiceImpl","lookHelpUserStatus","查询用户参加的活动任务数据"),
    TASK_MASTER_BY_ACTIID("activityTaskServiceImpl","tasks","根据活动id，查询主任务数据"),
    HELP_MASTER_SAVE("wechatHelpServiceImpl","addWechatHelpuser","保存用户参加活动任务数据"),
    TASK_SUB_BY_TASKID("activityTaskServiceImpl","taskSubs","根据主任务id，查询子任务数据"),
    HELP_SUB_BY_OPENID("wechatHelpServiceImpl","findWechatHelpuser","查询助力数据"),
    HELP_SUB_SAVE("wechatHelpServiceImpl","doHelp","保存助力数据"),
    HELP_MASTER_PICTURE_SAVE("wechatHelpServiceImpl","motiPicCommit","保存图片数据"),
    USER_COUPON_CODE_GET("wechatHelpServiceImpl","findUserCouponCode","兑换优惠码"),
    USER_FIND_USER_COUNT("wechatHelpServiceImpl","findUserCount","兑换优惠码"),
    HELP_SEND_CODE("wechatHelpServiceImpl","sendCode","发送验证码"),
    HELP_UPDATE_PHONE("wechatHelpServiceImpl","updatePhone","校验短信更新用户信息"),
    USER_FIND_CODE_BY_OPENID("wechatHelpServiceImpl","findCodeByopenId","根据openid查询兑换码"),
    ;

    ActivityMethodEnum(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

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

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getDescribe() {
        return describe;
    }
}
