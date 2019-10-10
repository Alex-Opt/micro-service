package com.ly.mt.core.common.method;

/**
 *@Description 进货业务服务接口枚举
 *@Author  yls
 */
public enum PurchaseMethodEnum {

    // B端商品操作
    PURCHASE_COMMODITY_INFO("purchaseCommodityServiceImpl", "getCommodityInfo", "查询B端进货商品信息"),
    PURCHASE_COMMODITY_DETAIL("purchaseCommodityServiceImpl", "getCommodityDetail", "查询B端进货商品详情"),
    PURCHASE_COMMODITY_BY_SPUID("purchaseCommodityServiceImpl", "getCommodityBySpuId", "通过spuID查询B端进货商品信息"),
    PURCHASE_COMMODITY_LADDER_PRICE("purchaseCommodityServiceImpl", "getCommodityLadderPrice", "查询B端进货商品详情"),
    PURCHASE_SHOP_COUPON_INFO("purchaseCommodityServiceImpl", "getShopCouponInfo", "查询B端店铺优惠劵信息"),


    //B端购物车
    PURCHASE_CART_GET_CART("cartServiceImpl", "getPurchaseCart", "购物车-从Redis中通过UserId获取用户的购物车"),
    PURCHASE_CART_ADD_CART_SKU("cartServiceImpl", "addCart", "购物车-增加购物车商品或数量"),
    PURCHASE_CART_SUB_CART_SKU_NUM("cartServiceImpl", "subCartSkuNum", "购物车-减少购物车商品数量"),
    PURCHASE_CART_DEL_CART_SKU("cartServiceImpl", "delCartSku", "购物车-删除购物车商品"),
    PURCHASE_CART_SYNC_CART_SKU("cartServiceImpl", "syncCart", "购物车-同步购物车商品"),

    // B端商家优惠信息
    PURCHASE_SHOP_PURCHASES_DISCOUNT_BY_SHOPID("shopPurchasesDiscountServiceImpl","getPurchasesDiscountInfoByShopId","根据shopId查询B端用户当前优惠信息"),
    // B端查询会员信息
    PURCHASE_MEMBER_INFO_BY_USERID("memberInfoServiceImpl","getInfoByUserId","根据用户id查询会员信息"),
    // B端最新购买情况
    PURCHASE_NEWEST_INFO("purchaseServiceImpl","getNewestPurchasesInfo","查询B端最新购买情况"),
    // B端类目查询
    PURCHASE_CATEGROY_LIST_BY_PARENTID("goodsServiceImpl","queryCategroyListByParentId","B端根据类目父id查询所有一级子类目"),
    // B端销量top5
    PURCHASE_LISTTOP5_BY_CID("goodsServiceImpl","queryListTop5ByCid","B端根据类目id查询当前类目下销量top5"),
    // B端查询会员等级情况
    PURCHASE_SHOP_GRADE_LIST("shopGradeServiceImpl","listAll","B端查询会员等级配置情况")

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

    PurchaseMethodEnum(String serviceName, String functionName, String describe) {
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
