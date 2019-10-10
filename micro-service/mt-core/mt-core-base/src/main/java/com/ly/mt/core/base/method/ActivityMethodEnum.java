package com.ly.mt.core.base.method;


/**
 * @description
 * 活动模块  服务bean-method枚举
 * @author panjingtian
 * @date 2019/6/13 10:35 PM
 *//** @deprecated */
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
    /******************************** 活动模块买家方法 *************************************/
    BUYER_SEND_ORDER_DYNAMICCODE("hdShopUserServiceImpl","sendCode","发送下单验证码"),
    BUYER_REGIST_ORDER("hdShopUserServiceImpl","registAndOrder","用户注册耦合下单"),
    BUYER_MSG("hdShopUserServiceImpl","getUserByAttIdPhone","获取用户信息"),
    /************************** 活动模块订单方法 ***********************/
    SHOP_ORDER_CHANGE("shopAttOrderServiceImpl","shopChangeOrderStatus","修改订单状态"),
    SHOP_ORDER_FINDALL("shopAttOrderServiceImpl","shopOrders","查询所有订单"),
    BUYER_ORDER_FIND("shopAttOrderServiceImpl","buyerGetOrder","用户订单查询"),

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
