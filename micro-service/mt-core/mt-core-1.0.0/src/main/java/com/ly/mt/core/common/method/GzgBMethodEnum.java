package com.ly.mt.core.common.method;

/**
 * @Description 后台服务接口枚举
 * @Author taoye
 */
public enum GzgBMethodEnum {
    GZG_PLAN("gzgInfoServiceImpl","gzgPlans","获取配货方案列表"),
    GZG_B_RULE_INFO("replenishServiceImpl","ruleInfo","规则时间奖励"),
    GZG_UPLOAD_FILE("replenishServiceImpl","upload","上传图片保存进度"),
    GZG_REPLENISH_REWARD("replenishServiceImpl","replenishReward","补货奖励"),
    GZG_REPLENISH_DETAIL("replenishServiceImpl","replenishDetail","补货详情"),
    GZG_B_COMMIT_AUDIT("replenishServiceImpl","commitAudit","提交审核"),
    GZG_B_CABINET_CHECK("replenishServiceImpl","cabinetCheck","格子柜校验"),
    GZG_B_SEND_CODE("dynamicCodeServiceImpl","send","发送动态验证码"),
    GZG_B_GOODS_CHECK("replenishServiceImpl","goodsCheck","商品校验"),
    GZG_B_WY_REPLENISH("replenishServiceImpl","wyReplenish","待补货"),
    GZG_B_STAY_REPLENISH("replenishServiceImpl","stayReplenish","待补货"),
    GZG_B_REPLENISH("replenishServiceImpl","replenishList","补货列表"),
    GZG_HOTEL_INFO_SAVE("gzgHotelServiceImpl", "saveHotel", "新增酒店"),
    GZG_USER_LOGIN("loginServiceImpl","login","用户登录"),
    GZG_USER_REGISTER("registerServiceImpl","register","用户注册"),
    GZG_USER_OUT("loginServiceImpl","out","退出登录"),
    GZG_INFO_SAVE("gzgInfoServiceImpl", "saveGzg", "新增格子柜"),
    GZG_C_NOTIF_B("cNotifyBServiceImpl","cNotify","c端生成订单通知b端生成补货单"),
    GZG_GET_INFOS("gzgInfoServiceImpl", "getInfos", "根据酒店ID获取格子柜列表"),
    GZG_BUSER_RELATION_SAVE("gzgInfoServiceImpl", "saveGzgUser", "保存格子柜用户关系"),
    GZG_GET_BUSER_BY_NICKNAME("gzgInfoServiceImpl", "findUserByPhone", "根据格子柜ID和用户手机号模糊查询用户"),
    GZG_DEL_GZG_USER_RELATION("gzgInfoServiceImpl", "delGzgUserRelation", "根据格子柜ID和用户ID删除关系"),
    GZG_GET_USER_GZG_RE("gzgInfoServiceImpl", "findGzgUsers", "获取格子柜补货员列表"),
    GZG_GET_ORDER_LIST("gzgOrdersServiceImpl","geOrders","查询格子柜订单列表"),
    GZG_GET_SKUID_NAME_LIST("gzgHotelStockServiceImpl","getSkus","获取酒店配货方案的skuI和name列表"),
    GZG_SAVE_HOTEL_STOCK("gzgHotelStockServiceImpl","saveHotelStock","保存酒店库存"),
    GZG_GET_ALL_BY_HOTELID("gzgHotelStockServiceImpl","findHotelSocks","获取酒店库存列表"),
    WY_REPLENISH("replenishServiceImpl","wyReplenish","我要补货"),
    GIVE_UP_REPLENISH("replenishServiceImpl","giveUpReplenish","放弃补货"),
    ;

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

    GzgBMethodEnum(String serviceName, String functionName, String describe) {
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