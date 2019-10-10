package com.ly.mt.core.feign;

/**
 * 数据中心服务接口配置枚举
 *
 * @author taoye
 */
public enum DataCenterMethod {
    /**
     * 事务测试
     */
    TXC_ADD1("txcServiceImpl", "add1", ""),
    TXC_ADD2("txcServiceImpl", "add2", ""),


    /**
     * 促销优惠活动限制spu商品接口
     *
     * @author taoye
     */
    ACTIVITY_GOODS_DETAIL_INSERT("activityGoodsDetailServiceImpl", "inserActivityGoodsDetail", "保存ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_DELETE("activityGoodsDetailServiceImpl", "deleteActivityGoodsDetail", "删除ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_UPDATE("activityGoodsDetailServiceImpl", "updateActivityGoodsDetail", "更新ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_GET("activityGoodsDetailServiceImpl", "getActivityGoodsDetail", "查询ActivityGoodsDetail"),


    /**
     * 促销优惠活动信息接口
     *
     * @author taoye
     */
    ACTIVITY_INFO_INSERT("activityInfoServiceImpl", "insertActivityInfo", "保存ActivityInfo"),
    ACTIVITY_INFO_DELETE("activityInfoServiceImpl", "deleteActivityInfo", "删除ActivityInfo"),
    ACTIVITY_INFO_UPDATE("activityInfoServiceImpl", "updateActivityInfo", "更新ActivityInfo"),
    ACTIVITY_INFO_GET("activityInfoServiceImpl", "getActivityInfo", "查询ActivityInfo"),
    ACTIVITY_INFO_GET_BY_SPU_ID("activityInfoServiceImpl", "getActivityInfoBySpuId", "根据spuId查询优惠活动数据"),


    /**
     * 格子柜入驻报名信息接口
     *
     * @author taoye
     */
    ACTIVITY_SIGN_UP_CABINET_INSERT("activitySignUpCabinetServiceImpl", "insertActivitySignUpCabinet", "保存ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_DELETE("activitySignUpCabinetServiceImpl", "deleteActivitySignUpCabinet", "删除ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_UPDATE("activitySignUpCabinetServiceImpl", "updateActivitySignUpCabinet", "更新ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_GET("activitySignUpCabinetServiceImpl", "getActivitySignUpCabinet", "查询ActivitySignUpCabinet"),


    /**
     * 促销优惠活动限制用户等级接口
     *
     * @author taoye
     */
    ACTIVITY_USER_GRADE_INSERT("activityUserGradeServiceImpl", "insertActivityUserGrade", "保存ActivityUserGrade"),
    ACTIVITY_USER_GRADE_DELETE("activityUserGradeServiceImpl", "deleteActivityUserGrade", "删除ActivityUserGrade"),
    ACTIVITY_USER_GRADE_UPDATE("activityUserGradeServiceImpl", "updateActivityUserGrade", "修改ActivityUserGrade"),
    ACTIVITY_USER_GRADE_GET("activityUserGradeServiceImpl", "getActivityUserGrade", "查询ActivityUserGrade"),


    /**
     * 参与促销优惠活动的等级用户使用明细接口
     *
     * @author taoye
     */
    ACTIVITY_USER_GRADE_DETAIL_INSERT("activityUserGradeDetailServiceImpl", "insertActivityUserGradeDetail", "保存ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_DELETE("activityUserGradeDetailServiceImpl", "deleteActivityUserGradeDetail", "删除ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_UPDATE("activityUserGradeDetailServiceImpl", "updateActivityUserGradeDetail", "修改ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_GET("activityUserGradeDetailServiceImpl", "getActivityUserGradeDetail", "查询ActivityUserGradeDetail"),


    /**
     * 行政区域信息接口
     *
     * @author taoye
     */
    BASIC_AREA_INSERT("basicAreaServiceImpl", "insertBasicArea", "保存BasicArea"),
    BASIC_AREA_DELETE("basicAreaServiceImpl", "deleteBasicArea", "删除BasicArea"),
    BASIC_AREA_UPDATE("basicAreaServiceImpl", "updateBasicArea", "修改BasicArea"),
    BASIC_AREA_GET("basicAreaServiceImpl", "getBasicArea", "查询BasicArea"),

    /**
     * 用户角色查询
     */
    BASIC_ROLE_GET("basicRoleServiceImpl", "getBasicRole", "查询BasicBasicRole"),

    /**
     * 一小时达开通城市接口
     *
     * @author taoye
     */
    CITY_ONE_HOUR_INSERT("cityOneHourServiceImpl", "insertCityOneHour", "保存CityOneHour"),
    CITY_ONE_HOUR_DELETE("cityOneHourServiceImpl", "deleteCityOneHour", "删除CityOneHour"),
    CITY_ONE_HOUR_UPDATE("cityOneHourServiceImpl", "updateCityOneHour", "修改CityOneHour"),
    CITY_ONE_HOUR_GET("cityOneHourServiceImpl", "getCityOneHour", "查询CityOneHour"),
    CITY_ONE_HOUR_FINDBYAREA("cityOneHourServiceImpl", "findByAreaId", "根据areaId查询"),


    /**
     * 优惠券码接口
     *
     * @author taoye
     */
    COUPON_CODE_INSERT("couponCodeServiceImpl", "insertCouponCode", "保存CouponCode"),
    COUPON_CODE_DELETE("couponCodeServiceImpl", "deleteCouponCode", "删除CouponCode"),
    COUPON_CODE_UPDATE("couponCodeServiceImpl", "updateCouponCode", "修改CouponCode"),
    COUPON_CODE_GET("couponCodeServiceImpl", "getCouponCode", "查询CouponCode"),
    COUPON_CODE_GET_ONE("couponCodeServiceImpl", "getOneRepeatCouponCode", "查询一张尚未兑换的复用兑换码"),
    COUPON_CODE_UPDATE_BY_CODE("couponCodeServiceImpl", "updateCouponCodeByCode", "用户兑换兑换码"),
    COUPON_CODE_QUERY_BY_USERID_AND_COUPONID("couponCodeServiceImpl", "getUserCouponByUserIdAndCouponIdList", "根据用户id,优惠券id查询用户是否领取该优惠券"),
    COUPON_CODE_UPDATE_USER_INFO("couponCodeServiceImpl", "updateCouponCodeUserInfo", "更新用户领取优惠券大礼包的数据信息到coupon_code"),
    COUPON_CODE_GET_BY_USERID_AND_COUPONIDS("couponCodeServiceImpl", "batchQueryCouponCode", "根据用户id,couponIds查询用户领取的对应优惠券的信息"),
    COUPON_CODE_GET_BY_USERID_AND_COUPONCODE("couponCodeServiceImpl", "getUserCouponByUserIdAndCouponCode", "根据用户id,couponCode查询用户兑换优惠券情况"),
    COUPON_CODE_GET_FOR_SHOP("couponCodeServiceImpl", "getCouponCodeForShop", "查询CouponCode"),

    /**
     * 优惠券对应商品接口
     *
     * @author taoye
     */
    COUPON_GOODS_INSERT("couponGoodsServiceImpl", "insertCouponGoods", "保存CouponGoods"),
    COUPON_GOODS_DELETE("couponGoodsServiceImpl", "deleteCouponGoods", "删除CouponGoods"),
    COUPON_GOODS_UPDATE("couponGoodsServiceImpl", "updateCouponGoods", "修改CouponGoods"),
    COUPON_GOODS_GET("couponGoodsServiceImpl", "getCouponGoods", "查询CouponGoods"),


    /**
     * 优惠券接口
     *
     * @author taoye
     */
    COUPON_INFO_INSERT("couponInfoServiceImpl", "insertCouponInfo", "保存CouponInfo"),
    COUPON_INFO_DELETE("couponInfoServiceImpl", "deleteCouponInfo", "删除CouponInfo"),
    COUPON_INFO_UPDATE("couponInfoServiceImpl", "updateCouponInfo", "修改CouponInfo"),
    COUPON_INFO_GET("couponInfoServiceImpl", "getCouponInfo", "查询CouponInfo"),
    COUPON_INFO_GET_BY_USER_ID("couponInfoServiceImpl", "getCouponInfoByUserId", "根据userId查询CouponInfo"),
    COUPON_INFO_GET_BY_SPU_ID("couponInfoServiceImpl", "getCouponInfoBySpuId", "根据spuId查询CouponInfo"),
    COUPON_INFO_FOR_NEW_USER("couponInfoServiceImpl", "getNewUserCoupons", "获取新人优惠券-系统发放优惠券"),
    COUPON_INFO_FOR_NEW_USER_SPREE("couponInfoServiceImpl", "getNewUserCouponsSpree", "获取新人优惠券大礼包-手动领取"),
    COUPON_INFO_FOR_SHOP("couponInfoServiceImpl", "getCouponInfoByUserIdForShop", "获取商家优惠券"),


    /**
     * 店铺信息接口
     *
     * @author taoye
     */
    CABINET_STORE_INSERT("cabinetStoreServiceImpl", "insertCabinetStore", "保存CabinetStore"),
    CABINET_STORE_DELETE("cabinetStoreServiceImpl", "deleteCabinetStore", "删除CabinetStore"),
    CABINET_STORE_UPDATE("cabinetStoreServiceImpl", "updateCabinetStore", "修改CabinetStore"),
    CABINET_STORE_UPDATE_STATUS_BY_ID("cabinetStoreServiceImpl", "updateCreateStatusById", "修改CabinetStore"),
    CABINET_STORE_GET("cabinetStoreServiceImpl", "getCabinetStore", "查询CabinetStore"),
    CABINET_STORE_GET_BY_USERID("cabinetStoreServiceImpl", "getCabinetStoreByUserId", "库管查询自己负责的店铺信息"),
    CABINET_STORE_GET_BY_ID("cabinetStoreServiceImpl", "getCabinetStoreById", "根据id查询店铺信息"),


    /**
     * 店铺收益总汇表
     *
     * @author taoye
     */
    CABINET_STORE_PROFIT_INSERT("cabinetStoreProfitServiceImpl", "insertCabinetStoreProfit", "保存 CabinetStoreProfit"),
    CABINET_STORE_PROFIT_DELETE("cabinetStoreProfitServiceImpl", "deleteCabinetStoreProfit", "删除 CabinetStoreProfit"),
    CABINET_STORE_PROFIT_UPDATE("cabinetStoreProfitServiceImpl", "updateCabinetStoreProfit", "修改 CabinetStoreProfit"),
    CABINET_STORE_PROFIT_GET("cabinetStoreProfitServiceImpl", "getCabinetStoreProfit", "查询 CabinetStoreProfit"),
    CABINET_STORE_PROFIT_GET_BY_STORE_ID("cabinetStoreProfitServiceImpl", "getCabinetStoreProfitByStoreId", "查询 CabinetStoreProfit"),

    /**
     * 店铺收益记录日志表
     *
     * @author taoye
     */
    CABINET_STORE_PROFIT_LOG_INSERT("cabinetStoreProfitLogServiceImpl", "insertCabinetStoreProfitLog", "保存 CabinetStoreProfitLog"),
    CABINET_STORE_PROFIT_LOG_DELETE("cabinetStoreProfitLogServiceImpl", "deleteCabinetStoreProfitLog", "删除 CabinetStoreProfitLog"),
    CABINET_STORE_PROFIT_LOG_UPDATE("cabinetStoreProfitLogServiceImpl", "updateCabinetStoreProfitLog", "修改 CabinetStoreProfitLog"),
    CABINET_STORE_PROFIT_LOG_GET("cabinetStoreProfitLogServiceImpl", "getCabinetStoreProfitLog", "查询 CabinetStoreProfitLog"),


    /**
     * 店铺信息接口
     *
     * @author taoye
     */
    CABINET_INFO_INSERT("cabinetInfoServiceImpl", "insertCabinetInfo", "保存 CabinetInfo"),
    CABINET_INFODELETE("cabinetInfoServiceImpl", "deleteCabinetInfo", "删除 CabinetInfo"),
    CABINET_INFO_UPDATE("cabinetInfoServiceImpl", "updateCabinetInfo", "修改 CabinetInfo"),
    CABINET_INFO_GET("cabinetInfoServiceImpl", "getCabinetInfo", "查询 CabinetInfo"),
    CABINET_INFO_GET_PROGRAMME_LIST("cabinetInfoServiceImpl", "getCabinetProgrammeList", "库管查询所有柜子的配货方案"),
    CABINET_INFO_GET_BY_IMEI("cabinetInfoServiceImpl", "getCabinetInfoByImei", "查询 CabinetInfo"),
    CABINET_INFO_GET_BY_STORE_ID("cabinetInfoServiceImpl", "getCabinetInfoByStoreId", "查询 CabinetInfo"),

    /**
     * 店铺管理员接口
     *
     * @author taoye
     */

    CABINET_ADMIN_FIND_IMEIS("cabinetAdminServiceImpl", "findCabinetImeis", "店铺管理员查询自己的所有货柜号"),
    CABINET_ADMIN_CONDITION_ORDERS("cabinetAdminServiceImpl", "findConditionsOrders", "店铺管理员订单"),

    /**
     * 格子柜上下架记录
     *
     * @author taoye
     */
    CABINET_CRATE_RECORD_INSERT("cabinetCreateRecordServiceImpl", "addCabinetCreateRecord", "保存 CabinetCreateRecord"),


    /**
     * 商品属性信息接口
     *
     * @author taoye
     */
    GOODS_ATTR_INSERT("goodsAttrServiceImpl", "insertGoodsAttr", "保存GoodsAttr"),
    GOODS_ATTR_DELETE("goodsAttrServiceImpl", "deleteGoodsAttr", "删除GoodsAttr"),
    GOODS_ATTR_UPDATE("goodsAttrServiceImpl", "updateGoodsAttr", "修改GoodsAttr"),
    GOODS_ATTR_GET("goodsAttrServiceImpl", "getGoodsAttr", "查询GoodsAttr"),


    /**
     * 商品属性值接口
     *
     * @author taoye
     */
    GOODS_ATTR_VALUE_INSERT("goodsAttrValueServiceImpl", "insertGoodsAttrValue", "保存GoodsAttrValue"),
    GOODS_ATTR_VALUE_DELETE("goodsAttrValueServiceImpl", "deleteGoodsAttrValue", "删除GoodsAttrValue"),
    GOODS_ATTR_VALUE_UPDATE("goodsAttrValueServiceImpl", "updateGoodsAttrValue", "修改GoodsAttrValue"),
    GOODS_ATTR_VALUE_GET("goodsAttrValueServiceImpl", "getGoodsAttrValue", "查询GoodsAttrValue"),
    GOODS_ATTR_BY_VALUEID("goodsAttrValueServiceImpl", "getGoodsAttrByValueId", "查询GoodsAttrValue"),

    /**
     * 品牌信息接口
     *
     * @author taoye
     */
    GOODS_BRAND_INFO_INSERT("goodsBrandInfoServiceImpl", "insertGoodsBrandInfo", "保存GoodsBrandInfo"),
    GOODS_BRAND_INFO_DELETE("goodsBrandInfoServiceImpl", "deleteGoodsBrandInfo", "删除GoodsBrandInfo"),
    GOODS_BRAND_INFO_UPDATE("goodsBrandInfoServiceImpl", "updateGoodsBrandInfo", "修改GoodsBrandInfo"),
    GOODS_BRAND_INFO_GET("goodsBrandInfoServiceImpl", "getGoodsBrandInfo", "查询GoodsBrandInfo"),


    /**
     * 类目信息接口
     *
     * @author taoye
     */
    GOODS_CATEGROY_INFO_INSERT("goodsCategroyInfoServiceImpl", "insertGoodsCategroyInfo", "保存GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_DELETE("goodsCategroyInfoServiceImpl", "deleteGoodsCategroyInfo", "删除GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_UPDATE("goodsCategroyInfoServiceImpl", "updateGoodsCategroyInfo", "修改GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_GET("goodsCategroyInfoServiceImpl", "getGoodsCategroyInfo", "查询GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_GET_BY_PARENT_ID("goodsCategroyInfoServiceImpl", "getGoodsCategroyInfoByParentId", "根据上级id，查询商品类目数据 -查询接口"),

    /**
     * 一小时达关联商品接口
     *
     * @author taoye
     */
    GOODS_HOUR_SKU_INSERT("goodsHourSkuServiceImpl", "insertGoodsHourSku", "保存GoodsHourSku"),
    GOODS_HOUR_SKU_DELETE("goodsHourSkuServiceImpl", "deleteGoodsHourSku", "删除GoodsHourSku"),
    GOODS_HOUR_SKU_UPDATE("goodsHourSkuServiceImpl", "updateGoodsHourSku", "修改GoodsHourSku"),
    GOODS_HOUR_SKU_GET("goodsHourSkuServiceImpl", "getGoodsHourSkuBySkuId", "查询GoodsHourSku"),
    GOODS_HOUR_QUERY_BY_SKUIDLIST("goodsHourSkuServiceImpl", "getGoodsSkuHourInfoBySkuIds", "根据的入参skuId集合查询一小时达的商品信息集合"),


    /**
     * 商品唯一标识码接口
     *
     * @author taoye
     */
    GOODS_SKU_CODE_INSERT("goodsSkuCodeServiceImpl", "insertGoodsSkuCode", "保存GoodsSkuCode"),
    GOODS_SKU_CODE_DELETE("goodsSkuCodeServiceImpl", "deleteGoodsSkuCode", "删除GoodsSkuCode"),
    GOODS_SKU_CODE_UPDATE("goodsSkuCodeServiceImpl", "updateGoodsSkuCode", "修改GoodsSkuCode"),
    GOODS_SKU_CODE_GET("goodsSkuCodeServiceImpl", "getGoodsSkuCode", "查询GoodsSkuCode"),


    /**
     * 商品SKU信息接口
     *
     * @author taoye
     */
    GOODS_SKU_INFO_INSERT("goodsSkuInfoServiceImpl", "insertGoodsSkuInfo", "保存GoodsSkuInfo"),
    GOODS_SKU_INFO_DELETE("goodsSkuInfoServiceImpl", "deleteGoodsSkuInfo", "删除GoodsSkuInfo"),
    GOODS_SKU_INFO_UPDATE("goodsSkuInfoServiceImpl", "updateGoodsSkuInfo", "修改GoodsSkuInfo"),
    GOODS_SKU_INFO_GET("goodsSkuInfoServiceImpl", "getGoodsSkuInfo", "查询GoodsSkuInfo"),
    GOODS_SKU_INFO_GET_BY_ID("goodsSkuInfoServiceImpl", "getGoodsSkuInfoById", "查询GoodsSkuInfo"),


    /**
     * 商品SKU图片接口
     *
     * @author taoye
     */
    GOODS_SKU_PICTURE_INSERT("goodsSkuPictureServiceImpl", "insertGoodsSkuPicture", "保存GoodsSkuPicture"),
    GOODS_SKU_PICTURE_DELETE("goodsSkuPictureServiceImpl", "deleteGoodsSkuPicture", "删除GoodsSkuPicture"),
    GOODS_SKU_PICTURE_UPDATE("goodsSkuPictureServiceImpl", "updateGoodsSkuPicture", "修改GoodsSkuPicture"),
    GOODS_SKU_PICTURE_GET("goodsSkuPictureServiceImpl", "getGoodsSkuPictureBySkuId", "查询GoodsSkuPicture"),
    GOODS_SKU_PICTURE_BY_SKUID("goodsSkuPictureServiceImpl", "getSkuPictureBySkuId", "查询GoodsSkuPicture"),


    /**
     * 商品SKU价格接口
     *
     * @author taoye
     */
    GOODS_SKU_PRICE_INSERT("goodsSkuPriceServiceImpl", "insertGoodsSkuPrice", "保存GoodsSkuPrice"),
    GOODS_SKU_PRICE_DELETE("goodsSkuPriceServiceImpl", "deleteGoodsSkuPrice", "删除GoodsSkuPrice"),
    GOODS_SKU_PRICE_UPDATE("goodsSkuPriceServiceImpl", "updateGoodsSkuPrice", "修改GoodsSkuPrice"),
    GOODS_SKU_PRICE_GET("goodsSkuPriceServiceImpl", "getGoodsSkuPrice", "查询GoodsSkuPrice"),


    /**
     * 管易店铺信息接口
     *
     * @author taoye
     */
    GY_SHOP_INFO_INSERT("gyShopInfoServiceImpl", "insertGyShopInfo", "保存GyShopInfo"),
    GY_SHOP_INFO_DELETE("gyShopInfoServiceImpl", "deleteGyShopInfo", "删除GyShopInfo"),
    GY_SHOP_INFO_UPDATE("gyShopInfoServiceImpl", "updateGyShopInfo", "修改GyShopInfo"),
    GY_SHOP_INFO_GET("gyShopInfoServiceImpl", "getGyShopInfo", "查询GyShopInfo"),


    /**
     * 格子柜B端用户接口
     *
     * @author taoye
     */
    GZG_B_USER_INSERT("gzgBUserServiceImpl", "insertGzgBUser", "保存GzgBUser"),
    GZG_B_USER_DELETE("gzgBUserServiceImpl", "deleteGzgBUser", "删除GzgBUser"),
    GZG_B_USER_UPDATE("gzgBUserServiceImpl", "updateGzgBUser", "修改GzgBUser"),
    GZG_B_USER_GET("gzgBUserServiceImpl", "getGzgBUser", "查询GzgBUser"),


    /**
     * 格子柜补货员关系接口
     *
     * @author taoye
     */
    GZG_B_USER_RELATION_INSERT("gzgBUserRelationServiceImpl", "insertGzgBUserRelation", "保存GzgBUserRelation"),
    GZG_B_USER_RELATION_DELETE("gzgBUserRelationServiceImpl", "deleteGzgBUserRelation", "删除GzgBUserRelation"),
    GZG_B_USER_RELATION_UPDATE("gzgBUserRelationServiceImpl", "updateGzgBUserRelation", "修改GzgBUserRelation"),
    GZG_B_USER_RELATION_GET("gzgBUserRelationServiceImpl", "getGzgBUserRelation", "查询GzgBUserRelation"),


    /**
     * @Description
     * @author taoye
     */
    GZG_BANNER_PICTURE_INSERT("gzgBannerPictureServiceImpl", "insertGzgBannerPicture", "保存GzgBannerPicture"),
    GZG_BANNER_PICTURE_DELETE("gzgBannerPictureServiceImpl", "deleteGzgBannerPicture", "删除GzgBannerPicture"),
    GZG_BANNER_PICTURE_UPDATE("gzgBannerPictureServiceImpl", "updateGzgBannerPicture", "修改GzgBannerPicture"),
    GZG_BANNER_PICTURE_GET("gzgBannerPictureServiceImpl", "getGzgBannerPicture", "查询GzgBannerPicture"),


    /**
     * 格子柜通道接口
     *
     * @author taoye
     */
    GZG_CABINET_INSERT("gzgCabinetServiceImpl", "insertGzgCabinet", "保存GzgCabinet"),
    GZG_CABINET_DELETE("gzgCabinetServiceImpl", "deleteGzgCabinet", "删除GzgCabinet"),
    GZG_CABINET_UPDATE("gzgCabinetServiceImpl", "updateGzgCabinet", "修改GzgCabinet"),
    GZG_CABINET_GET("gzgCabinetServiceImpl", "getGzgCabinet", "查询GzgCabinet"),
    GZG_CABINET_GET_BY_SELL_NUM("gzgCabinetServiceImpl", "getGzgCabinetBySellNo", "查询GzgCabinet"),
    GZG_NAME_INSERT("gzgNameServiceImpl", "insertGzgName", "生成格子柜编号并保存数据库"),
    GET_GZG_NAME_LIST("gzgNameServiceImpl", "getGzgNameBatch", "批量获取格子柜编号"),

    /**
     * 格子柜渠道商品code接口
     *
     * @author taoye
     */
    GZG_CHANNEL_CODE_INSERT("gzgChannelCodeServiceImpl", "insertGzgChannelCode", "保存GzgChannelCode"),
    GZG_CHANNEL_CODE_DELETE("gzgChannelCodeServiceImpl", "deleteGzgChannelCode", "删除GzgChannelCode"),
    GZG_CHANNEL_CODE_UPDATE("gzgChannelCodeServiceImpl", "updateGzgChannelCode", "修改GzgChannelCode"),
    GZG_CHANNEL_CODE_GET("gzgChannelCodeServiceImpl", "getGzgChannelCode", "查询GzgChannelCode"),


    /**
     * 格子柜所在城市接口
     *
     * @author taoye
     */
    GZG_CITY_INSERT("gzgCityServiceImpl", "insertGzgCity", "保存GzgCity"),
    GZG_CITY_DELETE("gzgCityServiceImpl", "deleteGzgCity", "删除GzgCity"),
    GZG_CITY_UPDATE("gzgCityServiceImpl", "updateGzgCity", "修改GzgCity"),
    GZG_CITY_GET("gzgCityServiceImpl", "getGzgCity", "查询GzgCity"),


    /**
     * 格子柜优惠券码接口
     *
     * @author taoye
     */
    GZG_COUPON_CODE_INSERT("gzgCouponCodeServiceImpl", "insertGzgCouponCode", "保存GzgCouponCode"),
    GZG_COUPON_CODE_DELETE("gzgCouponCodeServiceImpl", "deleteGzgCouponCode", "删除GzgCouponCode"),
    GZG_COUPON_CODE_UPDATE("gzgCouponCodeServiceImpl", "updateGzgCouponCode", "修改GzgCouponCode"),
    GZG_COUPON_CODE_GET("gzgCouponCodeServiceImpl", "getGzgCouponCode", "查询GzgCouponCode"),
    GZG_COUPON_CODE_GET_NOT_USED("gzgCouponCodeServiceImpl", "getGzgCouponCodeNotUsed", " 查询格子柜用户领取未使用的优惠券(集合)"),
    GZG_COUPON_CODE_GET_USED_INFO("gzgCouponCodeServiceImpl", "getGzgCouponCodeUsedInfo", " 查询格子柜用户领取的某个优惠券目前是否可用，为空：不可用，不为空：可用"),
    GZG_COUPON_CODE_UPDATE_USE_STATUS("gzgCouponCodeServiceImpl", "updateGzgCouponUseStatus", " 更新格子柜用户优惠券为已使用"),

    GZG_COUPON_CODE_GET_BY_USERID("gzgCouponCodeServiceImpl", "selectAllCouponByUserId", "通过用户id查询该用户所有优惠券信息"),


    /**
     * 格子柜优惠券对应商品接口
     *
     * @author taoye
     */
    GZG_COUPON_GOODS_INSERT("gzgCouponGoodsServiceImpl", "insertGzgCouponGoods", "保存GzgCouponGoods"),
    GZG_COUPON_GOODS_DELETE("gzgCouponGoodsServiceImpl", "deleteGzgCouponGoods", "删除GzgCouponGoods"),
    GZG_COUPON_GOODS_UPDATE("gzgCouponGoodsServiceImpl", "updateGzgCouponGoods", "修改GzgCouponGoods"),
    GZG_COUPON_GOODS_GET("gzgCouponGoodsServiceImpl", "getGzgCouponGoods", "查询GzgCouponGoods"),


    /**
     * 格子柜优惠券接口
     *
     * @author taoye
     */
    GZG_COUPON_INFO_INSERT("gzgCouponInfoServiceImpl", "insertGzgCouponInfo", "保存GzgCouponInfo"),
    GZG_COUPON_INFO_DELETE("gzgCouponInfoServiceImpl", "deleteGzgCouponInfo", "删除GzgCouponInfo"),
    GZG_COUPON_INFO_UPDATE("gzgCouponInfoServiceImpl", "updateGzgCouponInfo", "修改GzgCouponInfo"),
    GZG_COUPON_INFO_GET("gzgCouponInfoServiceImpl", "getGzgCouponInfo", "查询GzgCouponInfo"),


    /**
     * 格子柜
     *
     * @author taoye
     */
    GZG_DEVICE_CHECK_LOG_INSERT("gzgDeviceCheckLogServiceImpl", "insertGzgDeviceCheckLog", "保存GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_DELETE("gzgDeviceCheckLogServiceImpl", "deleteGzgDeviceCheckLog", "删除GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_UPDATE("gzgDeviceCheckLogServiceImpl", "updateGzgDeviceCheckLog", "修改GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_GET("gzgDeviceCheckLogServiceImpl", "getGzgDeviceCheckLog", "查询GzgDeviceCheckLog"),


    /**
     * 格子柜酒店信息接口
     *
     * @author taoye
     */
    GZG_HOTEL_INSERT("gzgHotelServiceImpl", "insertGzgHotel", "保存GzgHotel"),
    GZG_HOTEL_DELETE("gzgHotelServiceImpl", "deleteGzgHotel", "删除GzgHotel"),
    GZG_HOTEL_UPDATE("gzgHotelServiceImpl", "updateGzgHotel", "修改GzgHotel"),
    GZG_HOTEL_GET("gzgHotelServiceImpl", "getGzgHotel", "查询GzgHotel"),


    /**
     * 格子柜酒店库存接口
     *
     * @author taoye
     */
    GZG_HOTEL_STOCK_INSERT("gzgHotelStockServiceImpl", "insertGzgHotelStock", "保存GzgHotelStock"),
    GZG_HOTEL_STOCK_DELETE("gzgHotelStockServiceImpl", "deleteGzgHotelStock", "删除GzgHotelStock"),
    GZG_HOTEL_STOCK_UPDATE("gzgHotelStockServiceImpl", "updateGzgHotelStock", "修改GzgHotelStock"),
    GZG_HOTEL_STOCK_GET("gzgHotelStockServiceImpl", "getGzgHotelStock", "查询GzgHotelStock"),


    /**
     * 格子柜用户酒店关系接口
     *
     * @author taoye
     */
    GZG_HOTEL_USER_RELATION_INSERT("gzgHotelUserRelationServiceImpl", "insertGzgHotelUserRelation", "保存GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_DELETE("gzgHotelUserRelationServiceImpl", "deleteGzgHotelUserRelation", "删除GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_UPDATE("gzgHotelUserRelationServiceImpl", "updateGzgHotelUserRelation", "修改GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_GET("gzgHotelUserRelationServiceImpl", "getGzgHotelUserRelation", "查询GzgHotelUserRelation"),


    /**
     * 格子柜订单商品接口
     *
     * @author taoye
     */
    GZG_ORDER_ITEMS_INSERT("gzgOrderItemsServiceImpl", "insertGzgOrderItems", "保存GzgOrderItems"),
    GZG_ORDER_ITEMS_DELETE("gzgOrderItemsServiceImpl", "deleteGzgOrderItems", "删除GzgOrderItems"),
    GZG_ORDER_ITEMS_UPDATE("gzgOrderItemsServiceImpl", "updateGzgOrderItems", "修改GzgOrderItems"),
    GZG_ORDER_ITEMS_UPDATE_BY_ORDER_ID("gzgOrderItemsServiceImpl", "updateGzgOrderItemsByOrderId", "根据订单ID修改GzgOrderItems"),
    GZG_ORDER_ITEMS_GET("gzgOrderItemsServiceImpl", "getGzgOrderItems", "查询GzgOrderItems"),
    GZG_ORDER_ITEMS_SELL_NUM_GET("gzgOrderItemsServiceImpl", "getSellNumBySkuId", "通过skuid查询某个商品销售数量"),
    GZG_ORDER_ITEMS_GET_LIST("gzgOrderItemsServiceImpl", "getGzgOrderItemsByOrderId", "通过orderId查询GzgOrderItems"),


    /**
     * 格子柜
     *
     * @author taoye
     */
    GZG_ORDER_PAY_INSERT("gzgOrderPayServiceImpl", "insertGzgOrderPay", "保存GzgOrderPay"),
    GZG_ORDER_PAY_DELETE("gzgOrderPayServiceImpl", "deleteGzgOrderPay", "删除GzgOrderPay"),
    GZG_ORDER_PAY_UPDATE("gzgOrderPayServiceImpl", "updateGzgOrderPay", "修改GzgOrderPay"),
    GZG_ORDER_PAY_GET("gzgOrderPayServiceImpl", "getGzgOrderPay", "查询GzgOrderPay"),


    /**
     * 格子柜订单接口
     *
     * @author taoye
     */
    GZG_ORDER_INSERT("gzgOrdersServiceImpl", "insertGzgOrders", "保存GzgOrders"),
    GZG_ORDER_DELETE("gzgOrdersServiceImpl", "deleteGzgOrders", "删除GzgOrders"),
    GZG_ORDER_UPDATE("gzgOrdersServiceImpl", "updateGzgOrders", "修改GzgOrders"),
    GZG_ORDER_GET("gzgOrdersServiceImpl", "getGzgOrdersById", "查询GzgOrders"),
    GZG_ORDER_GET_LIST("gzgOrdersServiceImpl", "getGzgOrdersList", "查询GzgOrderList"),

    GZG_ORDER_GET_DIVIDE_SCALE("gzgOrdersServiceImpl", "getGzgOrderDivideScale", "查询订单完成后商家的分成比例"),


    /**
     * 格子柜补货订单接口
     */
    GZG_REPLENISH_ORDER_INSERT("gzgReplenishOrderServiceImpl", "insertGzgReplenishOrder", "保存GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_DELETE("gzgReplenishOrderServiceImpl", "deleteGzgReplenishOrder", "删除GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_UPDATE("gzgReplenishOrderServiceImpl", "updateGzgReplenishOrder", "修改GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_GET("gzgReplenishOrderServiceImpl", "getGzgReplenishOrder", "查询GzgReplenishOrder"),
    GZG_CABINET_REPLENISH_INSERT("cabinetReplenishServiceImpl", "insertCabinetReplenish", "保存格子柜补货信息：cabinet_replenish"),
    GZG_CABINET_REPLENISH_GET("cabinetReplenishServiceImpl", "getCabinetReplenishList", "获取补货列表"),
    GZG_CABINET_REPLENISH_GET_DETAIL("cabinetReplenishServiceImpl", "getCabinetReplenishById", "获取补货订单详情"),
    GZG_CABINET_REPLENISH_UPDATE("cabinetReplenishServiceImpl", "updateCabinetReplenishById", "更新补货单"),
    GZG_GOODS_INFO_NAME_GET("cabinetReplenishServiceImpl", "getGoodsInfoNameBySkuId", "获取商品名称信息"),

    GZG_REWARD_RECORD_INSERT("cabinetRewardRecordServiceImpl", "insert", "新增奖励明细记录数据"),
    /**
     * 展柜补货订单操作service
     */
    GZG_CABINET_ZGREPLENISH_ORDER_INSERT("cabinetZgReplenishOrderServiceImpl", "insertCabinetZgReplenish", "插入一条展柜补货单：cabinet_replenish"),
    GZG_CABINET_ZGREPLENISH_ORDER_GET("cabinetZgReplenishOrderServiceImpl", "getCabinetZgReplenishOrderList", "查询展柜补货列表"),
    GZG_CABINET_ZGREPLENISH_ORDER_UPDATE("cabinetZgReplenishOrderServiceImpl", "updateCabinetZgReplenishOrderById", "更新展柜补货单"),
    /**
     * 展柜补货订单理由操作service
     */
    GZG_CABINET_ZGREPLENISH_ORDER_REASON_INSERT("cabinetZgReplenishOrderReasonServiceImpl", "insertBatch", "批量插入展柜补货单理由"),
    GZG_CABINET_ZGREPLENISH_ORDER_REASON_GET("cabinetZgReplenishOrderReasonServiceImpl", "getCabinetZgReplenishReasonByCondtion", "查询展柜补货理由列表"),
    GZG_CABINET_ZGREPLENISH_ORDER_REASON_UPDATE("cabinetZgReplenishOrderReasonServiceImpl", "updateCabinetZgReplenishReasonById", "更新展柜补货单理由"),

    /**
     * getGzgGoodsPlanSuitNum
     * 格子消息接口
     */
    GZG_CABINET_MESSAGE_GET("cabinetMessageServiceImpl", "getCabinetMessage", "获取消息列表"),
    GZG_CABINET_MESSAGE_UPDATE("cabinetMessageServiceImpl", "updateCabinetMessageById", "更新消息"),
    GZG_CABINET_MESSAGE_INSERT("cabinetMessageServiceImpl", "insertCabinetMessage", "插入消息"),

    GZG_SUIT_BARCODE_INFO_GET("gzgSuitBarcodeInfoServiceImpl", "queryBySuitSpuId", "根据套装sku_id查询套装下的商品信息"),


    /**
     * 格子柜补货记录接口
     *
     * @author taoye
     */
    GZG_REPLENISH_RECORD_INSERT("gzgReplenishRecordServiceImpl", "insertGzgReplenishRecord", "保存GzgReplenishRecord"),
    GZG_REPLENISH_RECORD_DELETE("gzgReplenishRecordServiceImpl", "deleteGzgReplenishRecord", "删除GzgReplenishRecord"),
    GZG_REPLENISH_RECORD_UPDATE("gzgReplenishRecordServiceImpl", "updateGzgReplenishRecord", "修改GzgReplenishRecord"),
    GZG_REPLENISH_RECORD_GET("gzgReplenishRecordServiceImpl", "getGzgReplenishRecord", "查询GzgReplenishRecord"),


    /**
     * @Description 格子柜商品SKU图片接口
     * @Author taoye
     */
    GZG_SKU_PICTURE_INSERT("gzgSkuPictureServiceImpl", "insertGzgSkuPicture", "保存GzgSkuPicture"),
    GZG_SKU_PICTURE_DELETE("gzgSkuPictureServiceImpl", "deleteGzgSkuPicture", "删除GzgSkuPicture"),
    GZG_SKU_PICTURE_UPDATE("gzgSkuPictureServiceImpl", "updateGzgSkuPicture", "修改GzgSkuPicture"),
    GZG_SKU_PICTURE_GET("gzgSkuPictureServiceImpl", "getGzgSkuPicture", "查询GzgSkuPicture"),


    /**
     * @Description 格子柜
     * @Author taoye
     */
    GZG_STORE_INFO_INSERT("gzgStoreInfoServiceImpl", "insertGzgStoreInfo", "保存GzgStoreInfo"),
    GZG_STORE_INFO_DELETE("gzgStoreInfoServiceImpl", "deleteGzgStoreInfo", "删除GzgStoreInfo"),
    GZG_STORE_INFO_UPDATE("gzgStoreInfoServiceImpl", "updateGzgStoreInfo", "修改GzgStoreInfo"),
    GZG_STORE_INFO_GET("gzgStoreInfoServiceImpl", "getGzgStoreInfo", "查询GzgStoreInfo"),

    /**
     * @Description 如金格子柜
     * @Author taoye
     */
    GZG_RUJIN_RELATION_INSERT("gzgStoreInfoServiceImpl", "insertGzgStoreInfo", "保存GzgStoreInfo"),
    GZG_RUJIN_RELATION_DELETE("gzgStoreInfoServiceImpl", "deleteGzgStoreInfo", "删除GzgStoreInfo"),
    GZG_RUJIN_RELATION_UPDATE("gzgRujinRelationServiceImpl", "updateGzgRujinRelationById", "修改 GzgRujinRelation"),
    GZG_RUJIN_RELATION_INSERT_("gzgRujinRelationServiceImpl", "insertGzgRujinRelation", "修改 GzgRujinRelation"),
    GZG_RUJIN_RELATION_GET("gzgRujinRelationServiceImpl", "getGzgRujinRelationByNameAndTname", "查找 GzgRujinRelation"),
    GZG_RUJIN_RELATION_GET_LIST("gzgRujinRelationServiceImpl", "getGzgRujinRelationByNameAndTid", "查询 GzgRujinRelation"),
    GZG_RUJIN_RELATION_GET_BY_NAME("gzgRujinRelationServiceImpl", "getGzgRujinRelationByName", "查询 GzgRujinRelation"),
    GZG_RUJIN_RELATION_GET_BY_TID("gzgRujinRelationServiceImpl", "getGzgRujinRelationByTid", "通过 tid 查询 GzgRujinRelation"),
    GZG_RUJIN_RELATION_GET_BY_TNAME("gzgRujinRelationServiceImpl", "getGzgRujinRelationByTname", "通过 tname 查询 GzgRujinRelation"),


    /**
     * @Description 配货方案，竖向
     * @Author taoye
     */
    GZG_PROGRAMME_INSERT("gzgStoreInfoServiceImpl", "insertGzgStoreInfo", "保存 GzgProgramme"),
    GZG_PROGRAMME_DELETE("gzgStoreInfoServiceImpl", "deleteGzgStoreInfo", "删除 GzgProgramme"),
    GZG_PROGRAMMEUPDATE("gzgRujinRelationServiceImpl", "updateGzgRujinRelationById", "修改 GzgProgramme"),
    GZG_PROGRAMME_GET_LIST_BY_NAME("gzgProgrammeServiceImpl", "getGzgProgrammeListByName", "查询 GzgProgramme"),


    /**
     * @Description
     * @Author taoye
     */
    HD_ACTIVITY_USER_INSERT("hdActivityUserServiceImpl", "insertHdActivityUser", "保存HdActivityUser"),
    HD_ACTIVITY_USER_DELETE("hdActivityUserServiceImpl", "deleteHdActivityUser", "删除HdActivityUser"),
    HD_ACTIVITY_USER_UPDATE("hdActivityUserServiceImpl", "updateHdActivityUser", "修改HdActivityUser"),
    HD_ACTIVITY_USER_GET("hdActivityUserServiceImpl", "getHdActivityUser", "查询HdActivityUser"),


    /**
     * @Description 代理商接口
     * @Author taoye
     */
    HD_OPERATOR_INFO_INSERT("hdOperatorInfoServiceImpl", "insertHdOperatorInfo", "保存HdOperatorInfo"),
    HD_OPERATOR_INFO_DELETE("hdOperatorInfoServiceImpl", "deleteHdOperatorInfo", "删除HdOperatorInfo"),
    HD_OPERATOR_INFO_UPDATE("hdOperatorInfoServiceImpl", "updateHdOperatorInfo", "修改HdOperatorInfo"),
    HD_OPERATOR_INFO_GET("hdOperatorInfoServiceImpl", "getHdOperatorInfo", "查询HdOperatorInfo"),


    /**
     * @Description 门店活动详情接口
     * @Author taoye
     */
    HD_SHOP_ATT_DETAIL_INSERT("hdShopAttDetailServiceImpl", "insertHdShopAttDetail", "保存HdShopAttDetail"),
    HD_SHOP_ATT_DETAIL_DELETE("hdShopAttDetailServiceImpl", "deleteHdShopAttDetail", "删除HdShopAttDetail"),
    HD_SHOP_ATT_DETAIL_UPDATE("hdShopAttDetailServiceImpl", "updateHdShopAttDetail", "修改HdShopAttDetail"),
    HD_SHOP_ATT_DETAIL_GET("hdShopAttDetailServiceImpl", "getHdShopAttDetail", "查询HdShopAttDetail"),


    /**
     * @Description 门店活动门店的商品sku接口
     * @Author taoye
     */
    HD_SHOP_ATT_GOODS_SPU_INSERT("hdShopAttGoodsSpuServiceImpl", "insertHdShopAttGoodsSpu", "保存HdShopAttGoodsSpu"),
    HD_SHOP_ATT_GOODS_SPU_DELETE("hdShopAttGoodsSpuServiceImpl", "deleteHdShopAttGoodsSpu", "删除HdShopAttGoodsSpu"),
    HD_SHOP_ATT_GOODS_SPU_UPDATE("hdShopAttGoodsSpuServiceImpl", "updateHdShopAttGoodsSpu", "修改HdShopAttGoodsSpu"),
    HD_SHOP_ATT_GOODS_SPU_GET("hdShopAttGoodsSpuServiceImpl", "getHdShopAttGoodsSpu", "查询HdShopAttGoodsSpu"),


    /**
     * @Description 门店活动订单接口
     * @Author taoye
     */
    HD_SHOP_ATT_ORDER_INSERT("hdShopAttOrderServiceImpl", "insertHdShopAttOrder", "保存HdShopAttOrder"),
    HD_SHOP_ATT_ORDER_DELETE("hdShopAttOrderServiceImpl", "deleteHdShopAttOrder", "删除HdShopAttOrder"),
    HD_SHOP_ATT_ORDER_UPDATE("hdShopAttOrderServiceImpl", "updateHdShopAttOrder", "修改HdShopAttOrder"),
    HD_SHOP_ATT_ORDER_GET("hdShopAttOrderServiceImpl", "getHdShopAttOrder", "查询HdShopAttOrder"),


    /**
     * @Description 门店活动订单详情接口
     * @Author taoye
     */
    HD_SHOP_ATT_ORDER_DETAIL_INSERT("hdShopAttOrderDetailServiceImpl", "insertHdShopAttOrderDetail", "保存HdShopAttOrderDetail"),
    HD_SHOP_ATT_ORDER_DETAIL_DELETE("hdShopAttOrderDetailServiceImpl", "deleteHdShopAttOrderDetail", "删除HdShopAttOrderDetail"),
    HD_SHOP_ATT_ORDER_DETAIL_UPDATE("hdShopAttOrderDetailServiceImpl", "updateHdShopAttOrderDetail", "修改HdShopAttOrderDetail"),
    HD_SHOP_ATT_ORDER_DETAIL_GET("hdShopAttOrderDetailServiceImpl", "getHdShopAttOrderDetail", "查询HdShopAttOrderDetail"),


    /**
     * @Description 淘金人推荐人才接口
     * @Author taoye
     */
    LOAD_RUNNER_PERSONNELS_INSERT("loadRunnerPersonnelsServiceImpl", "insertLoadRunnerPersonnels", "保存LoadRunnerPersonnels"),
    LOAD_RUNNER_PERSONNELS_DELETE("loadRunnerPersonnelsServiceImpl", "deleteLoadRunnerPersonnels", "删除LoadRunnerPersonnels"),
    LOAD_RUNNER_PERSONNELS_UPDATE("loadRunnerPersonnelsServiceImpl", "updateLoadRunnerPersonnels", "修改LoadRunnerPersonnels"),
    LOAD_RUNNER_PERSONNELS_GET("loadRunnerPersonnelsServiceImpl", "getLoadRunnerPersonnels", "查询LoadRunnerPersonnels"),


    /**
     * @Description 淘金人线索接口
     * @Author taoye
     */
    LODE_RUNNER_CLUES_INSERT("lodeRunnerCluesServiceImpl", "insertLodeRunnerClues", "保存LodeRunnerClues"),
    LODE_RUNNER_CLUES_DELETE("lodeRunnerCluesServiceImpl", "deleteLodeRunnerClues", "删除LodeRunnerClues"),
    LODE_RUNNER_CLUES_UPDATE("lodeRunnerCluesServiceImpl", "updateLodeRunnerClues", "修改LodeRunnerClues"),
    LODE_RUNNER_CLUES_GET("lodeRunnerCluesServiceImpl", "getLodeRunnerClues", "查询LodeRunnerClues"),


    /**
     * @Description 淘金者专属邀请码接口
     * @Author taoye
     */
    LODE_RUNNER_CODES_INSERT("lodeRunnerCodesServiceImpl", "insertLodeRunnerCodes", "保存LodeRunnerCodes"),
    LODE_RUNNER_CODES_DELETE("lodeRunnerCodesServiceImpl", "deleteLodeRunnerCodes", "删除LodeRunnerCodes"),
    LODE_RUNNER_CODES_UPDATE("lodeRunnerCodesServiceImpl", "updateLodeRunnerCodes", "修改LodeRunnerCodes"),
    LODE_RUNNER_CODES_GET_BY_INVITECODE("lodeRunnerCodesServiceImpl", "getLodeRunnerCodesByInviteCode", "根据inviteCode查询LodeRunnerCodes"),


    /**
     * @Description 淘金人配置接口
     * @Author taoye
     */
    LODE_RUNNER_CONFIGS_INSERT("lodeRunnerConfigsServiceImpl", "insertLodeRunnerConfigs", "保存LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_DELETE("lodeRunnerConfigsServiceImpl", "deleteLodeRunnerConfigs", "删除LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_UPDATE("lodeRunnerConfigsServiceImpl", "updateLodeRunnerConfigs", "修改LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_GET("lodeRunnerConfigsServiceImpl", "getLodeRunnerConfigs", "查询LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_GET_ALL("lodeRunnerConfigsServiceImpl", "getAllLodeRunnerConfigs", "查询LodeRunnerConfigs"),


    /**
     * @Description 淘金人树接口
     * @Author taoye
     */
    LODE_RUNNER_TREES_INSERT("lodeRunnerTreesServiceImpl", "insertLodeRunnerTrees", "保存LodeRunnerTrees"),
    LODE_RUNNER_TREES_DELETE("lodeRunnerTreesServiceImpl", "deleteLodeRunnerTrees", "删除LodeRunnerTrees"),
    LODE_RUNNER_TREES_UPDATE("lodeRunnerTreesServiceImpl", "updateLodeRunnerTrees", "修改LodeRunnerTrees"),
    LODE_RUNNER_TREES_GET_BY_USERID("lodeRunnerTreesServiceImpl", "getLodeRunnerTreesByUserId", "查询LodeRunnerTrees"),
    LODE_RUNNER_TREES_ADD_INVITE_NUM("lodeRunnerCodesServiceImpl", "addInviteNums", "将淘金人的邀请数量加1"),


    /**
     * @Description C端用户专属邀请码接口
     * @Author taoye
     */
    LODE_RUNNER_USER_CODES_INSERT("lodeRunnerUserCodesServiceImpl", "insertLodeRunnerUserCodes", "保存LodeRunnerUserCodes"),
    LODE_RUNNER_USER_CODES_DELETE("lodeRunnerUserCodesServiceImpl", "deleteLodeRunnerUserCodes", "删除LodeRunnerUserCodes"),
    LODE_RUNNER_USER_CODES_UPDATE("lodeRunnerUserCodesServiceImpl", "updateLodeRunnerUserCodes", "修改LodeRunnerUserCodes"),
    LODE_RUNNER_USER_CODES_GET("lodeRunnerUserCodesServiceImpl", "getLodeRunnerUserCodes", "查询LodeRunnerUserCodes"),


    /**
     * @Description C端赚钱配置接口
     * @Author taoye
     */
    LODE_RUNNER_USER_CONFIGS_INSERT("lodeRunnerUserConfigsServiceImpl", "insertLodeRunnerUserConfigs", "保存LodeRunnerUserConfigs"),
    LODE_RUNNER_USER_CONFIGS_DELETE("lodeRunnerUserConfigsServiceImpl", "deleteLodeRunnerUserConfigs", "删除LodeRunnerUserConfigs"),
    LODE_RUNNER_USER_CONFIGS_UPDATE("lodeRunnerUserConfigsServiceImpl", "updateLodeRunnerUserConfigs", "修改LodeRunnerUserConfigs"),
    LODE_RUNNER_USER_CONFIGS_GET("lodeRunnerUserConfigsServiceImpl", "getLodeRunnerUserConfigs", "查询LodeRunnerUserConfigs"),


    /**
     * @Description C端赚钱人树接口
     * @Author taoye
     */
    LODE_RUNNER_USER_TREES_INSERT("lodeRunnerUserTreesServiceImpl", "insertLodeRunnerUserTrees", "保存LodeRunnerUserTrees"),
    LODE_RUNNER_USER_TREES_DELETE("lodeRunnerUserTreesServiceImpl", "deleteLodeRunnerUserTrees", "删除LodeRunnerUserTrees"),
    LODE_RUNNER_USER_TREES_UPDATE("lodeRunnerUserTreesServiceImpl", "updateLodeRunnerUserTrees", "修改LodeRunnerUserTrees"),
    LODE_RUNNER_USER_TREES_GET("lodeRunnerUserTreesServiceImpl", "getLodeRunnerUserTrees", "查询LodeRunnerUserTrees"),
    LODE_RUNNER_USER_TREES_GETFIVE("rLodeRunnerUserTreesServiceImpl", "fiveTrees", "查询五级别联系人"),
    LODE_RUNNER_USER_LOG_INSERT("rUserProfitsServiceImpl", "insterProfitsLogsAndProfits", "赚钱人收益日志及其总汇入库"),


    /**
     * @Description 通知日志接口
     * @Author taoye
     */
    NOTICE_LOGS_INSERT("noticeLogsServiceImpl", "insertNoticeLogs", "保存NoticeLogs"),
    NOTICE_LOGS_DELETE("noticeLogsServiceImpl", "deleteNoticeLogs", "删除NoticeLogs"),
    NOTICE_LOGS_UPDATE("noticeLogsServiceImpl", "updateNoticeLogs", "修改NoticeLogs"),
    NOTICE_LOGS_GET("noticeLogsServiceImpl", "getNoticeLogs", "查询NoticeLogs"),


    /**
     * @Description 商家订单检货日志接口
     * @Author taoye
     */
    ORDER_BATTLE_CHECK_LOGS_INSERT("orderBattleCheckLogsServiceImpl", "insertOrderBattleCheckLogs", "保存OrderBattleCheckLogs"),
    ORDER_BATTLE_CHECK_LOGS_DELETE("orderBattleCheckLogsServiceImpl", "deleteOrderBattleCheckLogs", "删除OrderBattleCheckLogs"),
    ORDER_BATTLE_CHECK_LOGS_UPDATE("orderBattleCheckLogsServiceImpl", "updateOrderBattleCheckLogs", "修改OrderBattleCheckLogs"),
    ORDER_BATTLE_CHECK_LOGS_GET("orderBattleCheckLogsServiceImpl", "getOrderBattleCheckLogs", "查询OrderBattleCheckLogs"),


    /**
     * @Description 订单快递信息接口
     * @Author taoye
     */
    ORDER_BATTLE_EXPRESSES_INSERT("orderBattleExpressesServiceImpl", "insertOrderBattleExpresses", "保存OrderBattleExpresses"),
    ORDER_BATTLE_EXPRESSES_DELETE("orderBattleExpressesServiceImpl", "deleteOrderBattleExpresses", "删除OrderBattleExpresses"),
    ORDER_BATTLE_EXPRESSES_UPDATE("orderBattleExpressesServiceImpl", "updateOrderBattleExpresses", "修改OrderBattleExpresses"),
    ORDER_BATTLE_EXPRESSES_GET("orderBattleExpressesServiceImpl", "getOrderBattleExpresses", "查询OrderBattleExpresses"),


    /**
     * @Description 抢单日志接口
     * @Author taoye
     */
    ORDER_BATTLE_LOGS_INSERT("orderBattleLogsServiceImpl", "insertOrderBattleLogs", "保存OrderBattleLogs"),
    ORDER_BATTLE_LOGS_DELETE("orderBattleLogsServiceImpl", "deleteOrderBattleLogs", "删除OrderBattleLogs"),
    ORDER_BATTLE_LOGS_UPDATE("orderBattleLogsServiceImpl", "updateOrderBattleLogs", "修改OrderBattleLogs"),
    ORDER_BATTLE_LOGS_GET("orderBattleLogsServiceImpl", "getOrderBattleLogs", "查询OrderBattleLogs"),


    /**
     * @Description 订单抢单关联接口
     * @Author taoye
     */
    ORDER_BATTLE_SHOP_INSERT("orderBattleShopServiceImpl", "insertOrderBattleShop", "保存OrderBattleShop"),
    ORDER_BATTLE_SHOP_DELETE("orderBattleShopServiceImpl", "deleteOrderBattleShop", "删除OrderBattleShop"),
    ORDER_BATTLE_SHOP_UPDATE("orderBattleShopServiceImpl", "updateOrderBattleShop", "修改OrderBattleShop"),
    ORDER_BATTLE_SHOP_GET("orderBattleShopServiceImpl", "getOrderBattleShop", "查询OrderBattleShop"),


    /**
     * @Description 商家抢单信息接口
     * @Author taoye
     */
    ORDERS_BATTLE_INFO_INSERT("ordersBattleInfoServiceImpl", "insertOrdersBattleInfo", "保存OrdersBattleInfo"),
    ORDERS_BATTLE_INFO_DELETE("ordersBattleInfoServiceImpl", "deleteOrdersBattleInfo", "删除OrdersBattleInfo"),
    ORDERS_BATTLE_INFO_UPDATE("ordersBattleInfoServiceImpl", "updateOrdersBattleInfo", "修改OrdersBattleInfo"),
    ORDERS_BATTLE_INFO_GET("ordersBattleInfoServiceImpl", "getOrdersBattleInfo", "查询OrdersBattleInfo"),


    /**
     * @Description 支付流水接口
     */
    PAYMENT_DETAIL_INSERT("paymentDetailServiceImpl", "insertPaymentDetail", "保存交易流水"),


    /**
     * @Description 物流公司信息接口
     * @Author taoye
     */
    PLATFORM_LOGISTICS_INSERT("platformLogisticsServiceImpl", "insertPlatformLogistics", "保存PlatformLogistics"),
    PLATFORM_LOGISTICS_DELETE("platformLogisticsServiceImpl", "deletePlatformLogistics", "删除PlatformLogistics"),
    PLATFORM_LOGISTICS_UPDATE("platformLogisticsServiceImpl", "updatePlatformLogistics", "修改PlatformLogistics"),
    PLATFORM_LOGISTICS_GET("platformLogisticsServiceImpl", "getPlatformLogistics", "查询PlatformLogistics"),


    /**
     * @Description 积分规则配置接口
     * @Author taoye
     */
    POINT_CONFIG_INSERT("pointConfigServiceImpl", "insertPointConfig", "保存PointConfig"),
    POINT_CONFIG_DELETE("pointConfigServiceImpl", "deletePointConfig", "删除PointConfig"),
    POINT_CONFIG_UPDATE("pointConfigServiceImpl", "updatePointConfig", "修改PointConfig"),
    POINT_CONFIG_GET("pointConfigServiceImpl", "getPointConfig", "查询PointConfig"),


    /**
     * @Description 积分等级配置接口
     * @Author taoye
     */
    POINT_GRADE_INSERT("pointGradeServiceImpl", "insertPointGrade", "保存PointGrade"),
    POINT_GRADE_DELETE("pointGradeServiceImpl", "deletePointGrade", "删除PointGrade"),
    POINT_GRADE_UPDATE("pointGradeServiceImpl", "updatePointGrade", "修改PointGrade"),
    POINT_GRADE_GET("pointGradeServiceImpl", "getPointGrade", "查询PointGrade"),


    /**
     * @Description 积分商城兑换规则配置接口
     * @Author taoye
     */
    POINT_MALL_RULE_INSERT("pointMallRuleServiceImpl", "insertPointMallRule", "保存PointMallRule"),
    POINT_MALL_RULE_DELETE("pointMallRuleServiceImpl", "deletePointMallRule", "删除PointMallRule"),
    POINT_MALL_RULE_UPDATE("pointMallRuleServiceImpl", "updatePointMallRule", "修改PointMallRule"),
    POINT_MALL_RULE_GET("pointMallRuleServiceImpl", "getPointMallRule", "查询PointMallRule"),


    /**
     * @Description 积分商城规则详细信息接口
     * @Author taoye
     */
    POINT_MALL_RULE_DETAIL_INSERT("pointMallRuleDetailServiceImpl", "insertPointMallRuleDetail", "保存PointMallRuleDetail"),
    POINT_MALL_RULE_DETAIL_DELETE("pointMallRuleDetailServiceImpl", "deletePointMallRuleDetail", "删除PointMallRuleDetail"),
    POINT_MALL_RULE_DETAIL_UPDATE("pointMallRuleDetailServiceImpl", "updatePointMallRuleDetail", "修改PointMallRuleDetail"),
    POINT_MALL_RULE_DETAIL_GET("pointMallRuleDetailServiceImpl", "getPointMallRuleDetail", "查询PointMallRuleDetail"),


    /**
     * @Description 打卡关联活动优惠接口
     * @Author taoye
     */
    PUNCH_CARD_COUPON_INSERT("punchCardCouponServiceImpl", "insertPunchCardCoupon", "保存PunchCardCoupon"),
    PUNCH_CARD_COUPON_DELETE("punchCardCouponServiceImpl", "deletePunchCardCoupon", "删除PunchCardCoupon"),
    PUNCH_CARD_COUPON_UPDATE("punchCardCouponServiceImpl", "updatePunchCardCoupon", "修改PunchCardCoupon"),
    PUNCH_CARD_COUPON_GET("punchCardCouponServiceImpl", "getPunchCardCoupon", "查询PunchCardCoupon"),


    /**
     * @Description 打卡关联积分接口
     * @Author taoye
     */
    PUNCH_CARD_POINT_INSERT("punchCardPointServiceImpl", "insertPunchCardPoint", "保存PunchCardPoint"),
    PUNCH_CARD_POINT_DELETE("punchCardPointServiceImpl", "deletePunchCardPoint", "删除PunchCardPoint"),
    PUNCH_CARD_POINT_UPDATE("punchCardPointServiceImpl", "updatePunchCardPoint", "修改PunchCardPoint"),
    PUNCH_CARD_POINT_GET("punchCardPointServiceImpl", "getPunchCardPoint", "查询PunchCardPoint"),


    /**
     * @Description 轮播图接口
     * @Author taoye
     */
    ROTATION_INFO_INSERT("rotationInfoServiceImpl", "insertRotationInfo", "保存RotationInfo"),
    ROTATION_INFO_DELETE("rotationInfoServiceImpl", "deleteRotationInfo", "删除RotationInfo"),
    ROTATION_INFO_UPDATE("rotationInfoServiceImpl", "updateRotationInfo", "修改RotationInfo"),
    ROTATION_INFO_GET("rotationInfoServiceImpl", "getRotationInfo", "查询RotationInfo"),
    ROTATION_INFO_LIST("rotationInfoServiceImpl", "listRotationInfo", "查询RotationInfo集合"),


    /**
     * @Description 分享商品接口
     * @Author taoye
     */
    SHARE_GOODS_INSERT("shareGoodsServiceImpl", "insertShareGoods", "保存ShareGoods"),
    SHARE_GOODS_DELETE("shareGoodsServiceImpl", "deleteShareGoods", "删除ShareGoods"),
    SHARE_GOODS_UPDATE("shareGoodsServiceImpl", "updateShareGoods", "修改ShareGoods"),
    SHARE_GOODS_GET("shareGoodsServiceImpl", "getShareGoods", "查询ShareGoods"),


    /**
     * @Description 分享商品图片接口
     * @Author taoye
     */
    SHARE_GOODS_PICTURE_DEL_INSERT("shareGoodsPictureDelServiceImpl", "insertShareGoodsPictureDel", "保存ShareGoodsPictureDel"),
    SHARE_GOODS_PICTURE_DEL_DELETE("shareGoodsPictureDelServiceImpl", "deleteShareGoodsPictureDel", "删除ShareGoodsPictureDel"),
    SHARE_GOODS_PICTURE_DEL_UPDATE("shareGoodsPictureDelServiceImpl", "updateShareGoodsPictureDel", "修改ShareGoodsPictureDel"),
    SHARE_GOODS_PICTURE_DEL_GET("shareGoodsPictureDelServiceImpl", "getShareGoodsPictureDel", "查询ShareGoodsPictureDel"),


    /**
     * @Description 商家发货地址信息接口
     * @Author taoye
     */
    SHOP_ADDRESS_INSERT("shopAddressServiceImpl", "insertShopAddress", "保存ShopAddress"),
    SHOP_ADDRESS_DELETE("shopAddressServiceImpl", "deleteShopAddressById", "删除ShopAddress"),
    SHOP_ADDRESS_UPDATE("shopAddressServiceImpl", "updateShopAddressById", "修改ShopAddress"),
    SHOP_ADDRESS_GET("shopAddressServiceImpl", "getShopAddress", "查询ShopAddress"),
    SHOP_ADDRESS_LIST_GET("shopAddressServiceImpl", "getShopAddressList", "查询ShopAddressList"),

    /**
     * @Description 店铺优惠券信息接口
     * @Author taoye
     */
    SHOP_COUPON_INSERT("shopCouponServiceImpl", "insertShopCoupon", "保存ShopCoupon"),
    SHOP_COUPON_DELETE("shopCouponServiceImpl", "deleteShopCoupon", "删除ShopCoupon"),
    SHOP_COUPON_UPDATE("shopCouponServiceImpl", "updateShopCoupon", "修改ShopCoupon"),
    SHOP_COUPON_GET("shopCouponServiceImpl", "getShopCoupon", "查询ShopCoupon"),
    SHOP_COUPON_LIST_GET("shopCouponServiceImpl", "getShopCouponList", "查询ShopCouponList"),

    /**
     * 商家专属优惠
     *
     * @Author linan
     */
    SHOP_EXCLUSIVE_DISCOUNT_GET("shopExclusiveDiscountServiceImpl", "getShopExclusiveDiscount", "查询ShopExclusiveDiscount"),

    /**
     * @Description 兜底商家信息接口
     * @Author taoye
     */
    SHOP_DEFAULT_INSERT("shopDefaultServiceImpl", "insertShopDefault", "保存ShopDefault"),
    SHOP_DEFAULT_DELETE("shopDefaultServiceImpl", "deleteShopDefault", "删除ShopDefault"),
    SHOP_DEFAULT_UPDATE("shopDefaultServiceImpl", "updateShopDefault", "修改ShopDefault"),
    SHOP_DEFAULT_GET("shopDefaultServiceImpl", "getShopDefault", "查询ShopDefault"),


    /**
     * @Description 商家抢单黑名单接口
     * @Author taoye
     */
    SHOP_GRAB_BLACK_LIST_INSERT("shopGrabBlackListServiceImpl", "insertShopGrabBlackList", "保存ShopGrabBlackList"),
    SHOP_GRAB_BLACK_LIST_DELETE("shopGrabBlackListServiceImpl", "deleteShopGrabBlackList", "删除ShopGrabBlackList"),
    SHOP_GRAB_BLACK_LIST_UPDATE("shopGrabBlackListServiceImpl", "updateShopGrabBlackList", "修改ShopGrabBlackList"),
    SHOP_GRAB_BLACK_LIST_GET("shopGrabBlackListServiceImpl", "getShopGrabBlackList", "查询ShopGrabBlackList"),


    /**
     * @Description 店铺会员等级配置接口
     * @Author taoye
     */
    SHOP_GRADE_INSERT("shopGradeServiceImpl", "insertShopGrade", "保存ShopGrade"),
    SHOP_GRADE_DELETE("shopGradeServiceImpl", "deleteShopGrade", "删除ShopGrade"),
    SHOP_GRADE_UPDATE("shopGradeServiceImpl", "updateShopGrade", "修改ShopGrade"),
    SHOP_GRADE_GET("shopGradeServiceImpl", "getShopGrade", "查询ShopGrade"),


    /**
     * @Description 店铺小B信息接口
     * @Author taoye
     */
    SHOP_INFO_INSERT("shopInfoServiceImpl", "insertShopInfo", "保存ShopInfo"),
    SHOP_INFO_DELETE("shopInfoServiceImpl", "deleteShopInfo", "删除ShopInfo"),
    SHOP_INFO_UPDATE("shopInfoServiceImpl", "updateShopInfo", "修改ShopInfo"),
    SHOP_INFO_GET_BY_MOBILE("shopInfoServiceImpl", "getShopInfoByMobile", "根据手机号查询ShopInfo"),
    SHOP_INFO_GET_BY_ID("shopInfoServiceImpl", "getShopInfoById", "根据ID查询ShopInfo"),
    SHOP_INFO_GET_BY_USERID("shopInfoServiceImpl", "getShopInfoByUserId", "根据ID查询ShopInfo"),


    /**
     * @Description 商家营业执照接口
     * @Author taoye
     */
    SHOP_LICENSES_INSERT("shopLicensesServiceImpl", "insertShopLicenses", "保存ShopLicenses"),
    SHOP_LICENSES_DELETE("shopLicensesServiceImpl", "deleteShopLicenses", "删除ShopLicenses"),
    SHOP_LICENSES_UPDATE("shopLicensesServiceImpl", "updateShopLicenses", "修改ShopLicenses"),
    SHOP_LICENSES_GET("shopLicensesServiceImpl", "getShopLicenses", "查询ShopLicenses"),


    /**
     * @Description 商家收益日志接口
     * @Author taoye
     */
    SHOP_PROFIT_LOGS_INSERT("shopProfitLogsServiceImpl", "insertShopProfitLogs", "保存ShopProfitLogs"),
    SHOP_PROFIT_LOGS_DELETE("shopProfitLogsServiceImpl", "deleteShopProfitLogs", "删除ShopProfitLogs"),
    SHOP_PROFIT_LOGS_UPDATE("shopProfitLogsServiceImpl", "updateShopProfitLogs", "修改ShopProfitLogs"),
    SHOP_PROFIT_LOGS_GET("shopProfitLogsServiceImpl", "getShopProfitLogs", "查询ShopProfitLogs"),


    /**
     * @Description 商家收益汇总接口
     * @Author taoye
     */
    SHOP_PROFITS_INSERT("shopProfitsServiceImpl", "insertShopProfits", "保存ShopProfits"),
    SHOP_PROFITS_DELETE("shopProfitsServiceImpl", "deleteShopProfits", "删除ShopProfits"),
    SHOP_PROFITS_UPDATE("shopProfitsServiceImpl", "updateShopProfits", "修改ShopProfits"),
    SHOP_PROFITS_GET("shopProfitsServiceImpl", "getShopProfits", "查询ShopProfits"),


    /**
     * @Description 商家进货阶梯价配置接口
     * @Author taoye
     */
    SHOP_PURACHASES_LADDER_PRICE_INSERT("shopPurachasesLadderPriceServiceImpl", "insertShopPurachasesLadderPrice", "保存ShopPurachasesLadderPrice"),
    SHOP_PURACHASES_LADDER_PRICE_DELETE("shopPurachasesLadderPriceServiceImpl", "deleteShopPurachasesLadderPrice", "删除ShopPurachasesLadderPrice"),
    SHOP_PURACHASES_LADDER_PRICE_UPDATE("shopPurachasesLadderPriceServiceImpl", "updateShopPurachasesLadderPrice", "修改ShopPurachasesLadderPrice"),
    SHOP_PURACHASES_LADDER_PRICE_GET("shopPurachasesLadderPriceServiceImpl", "getShopPurachasesLadderPrice", "查询ShopPurachasesLadderPrice"),
    SHOP_PURACHASES_LADDER_PRICE_LIST("shopPurachasesLadderPriceServiceImpl", "getShopPurachasesLadderPriceList", "查询ShopPurachasesLadderPrice集合"),

    /**
     * @Description 商家进货订单接口
     * @Author taoye
     */
    SHOP_PURCHASES_INSERT("shopPurchasesServiceImpl", "insertShopPurchases", "保存ShopPurchases"),
    SHOP_PURCHASES_DELETE("shopPurchasesServiceImpl", "deleteShopPurchases", "删除ShopPurchases"),
    SHOP_PURCHASES_UPDATE("shopPurchasesServiceImpl", "updateShopPurchases", "修改ShopPurchases"),
    SHOP_PURCHASES_GET("shopPurchasesServiceImpl", "getShopPurchases", "查询ShopPurchases"),
    SHOP_PURCHASES_LIST_GET("shopPurchasesServiceImpl", "getShopPurchasesList", "查询ShopPurchasesList"),


    /**
     * @Description 商家进货优惠接口
     * @Author taoye
     */
    SHOP_PURCHASES_DISCOUNT_INSERT("shopPurchasesDiscountServiceImpl", "insertShopPurchasesDiscount", "保存ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_DELETE("shopPurchasesDiscountServiceImpl", "deleteShopPurchasesDiscount", "删除ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_UPDATE("shopPurchasesDiscountServiceImpl", "updateShopPurchasesDiscount", "修改ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_GET("shopPurchasesDiscountServiceImpl", "getShopPurchasesDiscount", "查询ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_SHOP_DISCOUNT("shopPurchasesDiscountServiceImpl", "getShopTotalDiscount", "查询商家累计优惠金额"),
    SHOP_PURCHASES_DISCOUNT_LIST("shopPurchasesDiscountServiceImpl", "getShopPurchasesDiscountList", "查询ShopPurchasesDiscount"),


    /**
     * @Description 进货商品接口
     * @Author taoye
     */
    SHOP_PURCHASES_ITEMS_INSERT("shopPurchasesItemsServiceImpl", "insertShopPurchasesItems", "保存ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_DELETE("shopPurchasesItemsServiceImpl", "deleteShopPurchasesItems", "删除ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_UPDATE("shopPurchasesItemsServiceImpl", "updateShopPurchasesItems", "修改ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_GET("shopPurchasesItemsServiceImpl", "getShopPurchasesItems", "查询ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_LIST_GET("shopPurchasesItemsServiceImpl", "getItemList", "获取item列表"),
    SHOP_PURCHASES_ITEMS_NUM_GET("shopPurchasesItemsServiceImpl", "getShopItemNumByShopId", "获取商家进货总数"),
    SHOP_PURCHASES_ITEMS_SPU_NUM_GET("shopPurchasesItemsServiceImpl", "getShopItemNumBySpu", "获取spu销量"),

    /**
     * @Description 商家进货退货接口
     * @Author taoye
     */
    SHOP_PURCHASES_REFUND_INSERT("shopPurchasesRefundServiceImpl", "insertShopPurchasesRefund", "保存ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_DELETE("shopPurchasesRefundServiceImpl", "deleteShopPurchasesRefund", "删除ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_UPDATE("shopPurchasesRefundServiceImpl", "updateShopPurchasesRefund", "修改ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_GET("shopPurchasesRefundServiceImpl", "getShopPurchasesRefund", "查询ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_FILE_UPLOAD("shopPurachaseRefundUploadServiceImpl", "uploadFile", "退货图片上传"),


    /**
     * @Description 商家进货退货详情接口
     * @Author taoye
     */
    SHOP_PURCHASES_REFUND_ITEM_INSERT("shopPurchasesRefundItemServiceImpl", "insertShopPurchasesRefundItem", "保存ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_DELETE("shopPurchasesRefundItemServiceImpl", "deleteShopPurchasesRefundItem", "删除ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_UPDATE("shopPurchasesRefundItemServiceImpl", "updateShopPurchasesRefundItem", "修改ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_GET("shopPurchasesRefundItemServiceImpl", "getShopPurchasesRefundItem", "查询ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_NUM_GET("shopPurchasesRefundItemServiceImpl", "getShopRefundItemNum", "查询商家退货总数"),

    /**
     * @Description 退款图片接口
     * @Author taoye
     */
    SHOP_REFUND_IMAGES_INSERT("shopRefundImagesServiceImpl", "insertShopRefundImages", "保存ShopRefundImages"),
    SHOP_REFUND_IMAGES_DELETE("shopRefundImagesServiceImpl", "deleteShopRefundImages", "删除ShopRefundImages"),
    SHOP_REFUND_IMAGES_UPDATE("shopRefundImagesServiceImpl", "updateShopRefundImages", "修改ShopRefundImages"),
    SHOP_REFUND_IMAGES_GET("shopRefundImagesServiceImpl", "getShopRefundImages", "查询ShopRefundImages"),


    /**
     * @Description 分享操作日志接口
     * @Author taoye
     */
    SHOP_SHARE_LOG_INSERT("shopShareLogServiceImpl", "insertShopShareLog", "保存ShopShareLog"),
    SHOP_SHARE_LOG_DELETE("shopShareLogServiceImpl", "deleteShopShareLog", "删除ShopShareLog"),
    SHOP_SHARE_LOG_UPDATE("shopShareLogServiceImpl", "updateShopShareLog", "修改ShopShareLog"),
    SHOP_SHARE_LOG_GET("shopShareLogServiceImpl", "getShopShareLog", "查询ShopShareLog"),


    /**
     * @Description 商家库存接口
     * @Author taoye
     */
    SHOP_STOCKS_INSERT("shopStocksServiceImpl", "insertShopStocks", "保存ShopStocks"),
    SHOP_STOCKS_DELETE("shopStocksServiceImpl", "deleteShopStocks", "删除ShopStocks"),
    SHOP_STOCKS_UPDATE("shopStocksServiceImpl", "updateShopStocks", "修改ShopStocks"),
    SHOP_STOCKS_GET("shopStocksServiceImpl", "getShopStocks", "查询ShopStocks"),


    /**
     * @Description 发票接口
     * @Author taoye
     */
    TRADE_INVOICE_INSERT("tradeInvoiceServiceImpl", "insertTradeInvoice", "保存TradeInvoice"),
    TRADE_INVOICE_DELETE("tradeInvoiceServiceImpl", "deleteTradeInvoice", "删除TradeInvoice"),
    TRADE_INVOICE_UPDATE("tradeInvoiceServiceImpl", "updateTradeInvoice", "修改TradeInvoice"),
    TRADE_INVOICE_GET("tradeInvoiceServiceImpl", "getTradeInvoice", "查询TradeInvoice"),


    /**
     * @Description 订单优惠信息接口
     * @Author taoye
     */
    TRADE_ORDER_COUPON_INSERT("tradeOrderCouponServiceImpl", "insertTradeOrderCoupon", "保存TradeOrderCoupon"),
    TRADE_ORDER_COUPON_DELETE("tradeOrderCouponServiceImpl", "deleteTradeOrderCoupon", "删除TradeOrderCoupon"),
    TRADE_ORDER_COUPON_UPDATE("tradeOrderCouponServiceImpl", "updateTradeOrderCoupon", "修改TradeOrderCoupon"),
    TRADE_ORDER_COUPON_GET("tradeOrderCouponServiceImpl", "getTradeOrderCoupon", "查询TradeOrderCoupon"),


    /**
     * @Description 订单商品表接口
     * @Author taoye
     */
    TRADE_ORDER_ITEMS_INSERT("tradeOrderItemsServiceImpl", "insertTradeOrderItems", "保存TradeOrderItems"),
    TRADE_ORDER_ITEMS_DELETE("tradeOrderItemsServiceImpl", "deleteTradeOrderItems", "删除TradeOrderItems"),
    TRADE_ORDER_ITEMS_UPDATE("tradeOrderItemsServiceImpl", "updateTradeOrderItems", "修改TradeOrderItems"),
    TRADE_ORDER_ITEMS_GET("tradeOrderItemsServiceImpl", "getTradeOrderItems", "查询TradeOrderItems"),
    TRADE_ORDER_ITEMS_LIST("tradeOrderItemsServiceImpl", "listOrderItemsByOrderId", "查询TradeOrderItems集合"),
    TRADE_ORDER_ITEMS_LITTLE_SMOKE_COUNT("tradeOrderItemsServiceImpl", "getFreeLittleSmokeCount", "根据userId和skuId查询小燕的购买数量"),
    TRADE_ORDER_ITEMS_MTGO_ZEROPRICE_COUNT("tradeOrderItemsServiceImpl", "getmogozeropriceCount", "根据userId和skuId查询0元mtgo的购买数量"),
    TRADE_ORDER_ITEMS_TAOZHUANG_ZEROPRICE_COUNT("tradeOrderItemsServiceImpl", "gettaozhuangzeropriceCount", "根据userId和skuId查询0元mtgo的购买数量"),


    /**
     * @Description 订单推送抢单日志接口
     * @Author taoye
     */
    TRADE_ORDER_MQ_LOG_INSERT("tradeOrderMqLogServiceImpl", "insertTradeOrderMqLog", "保存TradeOrderMqLog"),
    TRADE_ORDER_MQ_LOG_DELETE("tradeOrderMqLogServiceImpl", "deleteTradeOrderMqLog", "删除TradeOrderMqLog"),
    TRADE_ORDER_MQ_LOG_UPDATE("tradeOrderMqLogServiceImpl", "updateTradeOrderMqLog", "修改TradeOrderMqLog"),
    TRADE_ORDER_MQ_LOG_GET("tradeOrderMqLogServiceImpl", "getTradeOrderMqLog", "查询TradeOrderMqLog"),


    /**
     * @Description 订单退货信息接口
     * @Author taoye
     */
    TRADE_ORDER_REFUND_INSERT("tradeOrderRefundServiceImpl", "insertTradeOrderRefund", "保存TradeOrderRefund"),
    TRADE_ORDER_REFUND_DELETE("tradeOrderRefundServiceImpl", "deleteTradeOrderRefund", "删除TradeOrderRefund"),
    TRADE_ORDER_REFUND_UPDATE("tradeOrderRefundServiceImpl", "updateTradeOrderRefund", "修改TradeOrderRefund"),
    TRADE_ORDER_REFUND_GET("tradeOrderRefundServiceImpl", "getTradeOrderRefund", "查询TradeOrderRefund"),


    /**
     * @Description 退货详情接口
     * @Author taoye
     */
    TRADE_ORDER_REFUND_ITEM_INSERT("tradeOrderRefundItemServiceImpl", "insertTradeOrderRefundItem", "保存TradeOrderRefundItem"),
    TRADE_ORDER_REFUND_ITEM_DELETE("tradeOrderRefundItemServiceImpl", "deleteTradeOrderRefundItem", "删除TradeOrderRefundItem"),
    TRADE_ORDER_REFUND_ITEM_UPDATE("tradeOrderRefundItemServiceImpl", "updateTradeOrderRefundItem", "修改TradeOrderRefundItem"),
    TRADE_ORDER_REFUND_ITEM_GET("tradeOrderRefundItemServiceImpl", "getTradeOrderRefundItem", "查询TradeOrderRefundItem"),


    /**
     * @Description 抢单流水接口
     * @Author taoye
     */
    TRADE_ORDER_VIE_INSERT("tradeOrderVieServiceImpl", "insertTradeOrderVie", "保存TradeOrderVie"),
    TRADE_ORDER_VIE_DELETE("tradeOrderVieServiceImpl", "deleteTradeOrderVie", "删除TradeOrderVie"),
    TRADE_ORDER_VIE_UPDATE("tradeOrderVieServiceImpl", "updateTradeOrderVie", "修改TradeOrderVie"),
    TRADE_ORDER_VIE_GET("tradeOrderVieServiceImpl", "getTradeOrderVie", "查询TradeOrderVie"),

    /**
     * 物流
     */
    TRADE_ORDER_DELIVERY_INFO("tradeOrdersServiceImpl", "getOrderDeliveryInfo", "订单管理-查询订单发货物流信息接口"),

    /**
     * @Description 订单接口
     * @Author taoye
     */
    TRADE_ORDERS_INSERT("tradeOrdersServiceImpl", "insertTradeOrders", "保存TradeOrders"),
    TRADE_ORDERS_DELETE("tradeOrdersServiceImpl", "deleteTradeOrders", "删除TradeOrders"),
    TRADE_ORDERS_UPDATE("tradeOrdersServiceImpl", "updateTradeOrders", "修改TradeOrders"),
    TRADE_ORDERS_GET("tradeOrdersServiceImpl", "getTradeOrders", "查询TradeOrders"),
    TRADE_ORDERS_UPDATE_PAYMENTTYPE("tradeOrdersServiceImpl", "updateTradeOrdersPaymentType", "修改TradeOrders"),
    TRADE_ORDERS_BY_ORDERSOURCE("tradeOrdersServiceImpl", "getTradeOrdersByOrderSource", "根据来源查询TradeOrders"),

    TRADE_ORDER_LIST("tradeOrdersServiceImpl", "queryOrderListByUserId", "订单管理-查询用户订单列表接口"),

    ORDER_GET_ORDER("tradeOrdersServiceImpl", "getTradeOrders", "查询订单信息"),
    ORDER_GET_BY_ORDERNO("tradeOrdersServiceImpl", "getTradeOrdersByOrderNo", "根据订单编号查询订单数据"),
    ORDER_UPDATE_ORDER("tradeOrdersServiceImpl", "updateTradeOrders", "查询订单信息"),
    ORDER_LIST_ORDER("tradeOrdersServiceImpl", "listTradeOrders", "查询订单信息集合"),
    ORDER_LIST_BY_SOURCE("tradeOrdersServiceImpl", "getTradeOrdersBySource", "根据来源，查询订单信息集合"),


    TRADE_ORDERS_MEDIA_INSERT("tradeOrderMediaServiceImpl", "insertTradeOrderMeida", "保存TradeOrderMedia"),

    /**
     * @Description 用户信息接口
     * @Author taoye
     */
    USER_INSERT("userServiceImpl", "insertUser", "保存User"),
    USER_DELETE("userServiceImpl", "deleteUser", "删除User"),
    USER_UPDATE("userServiceImpl", "updateUser", "更新User"),
    USER_SET_PASSWORD("userServiceImpl", "setPassword", "设置密码"),
    USER_GET_USER_BY_ID("userServiceImpl", "getUserById", "根据id查询User"),
    USER_GET_USER_BY_MOBILE("userServiceImpl", "getUserByMobile", "根据mobile查询User"),
    USER_GET_USER_BY_MOBILE_ACTIVITY("userServiceImpl", "getUserByMobileForActivity", "根据mobile查询User"),
    USER_GET_USER_BY_LOGIN_NAME("userServiceImpl", "getUserByLoginName", "根据login_name查询User"),
    USER_GET_LOGIN_USER_BY_MOBILE("userServiceImpl", "getLoginUserByMobile", "登录，根据mobile查询User"),
    USER_GET_USER_BY_WX_OPEN_ID("userServiceImpl", "getUserByWxOpenId", "根据open_id查询User"),


    /**
     * @Description 用户绑定接口
     * @Author taoye
     */
    USER_BINDS_INSERT("userBindsServiceImpl", "insertUserBinds", "保存UserBinds"),
    USER_BINDS_DELETE("userBindsServiceImpl", "deleteUserBinds", "删除UserBinds"),
    USER_BINDS_UPDATE("userBindsServiceImpl", "updateUserBinds", "修改UserBinds"),
    USER_BINDS_GET("userBindsServiceImpl", "getUserBinds", "查询UserBinds"),


    /**
     * @Description 用户人脉接口
     * @Author taoye
     */
    USER_CONNECTIONS_INSERT("userConnectionsServiceImpl", "insertUserConnections", "保存UserConnections"),
    USER_CONNECTIONS_DELETE("userConnectionsServiceImpl", "deleteUserConnections", "删除UserConnections"),
    USER_CONNECTIONS_UPDATE("userConnectionsServiceImpl", "updateUserConnections", "修改UserConnections"),
    USER_CONNECTIONS_GET("userConnectionsServiceImpl", "getUserConnections", "查询UserConnections"),


    /**
     * @Description 用户优惠信息接口
     * @Author taoye
     */
    USER_COUPONE_CODE_DEL_INSERT("userCouponeCodeDelServiceImpl", "insertUserCouponeCodeDel", "保存UserCouponeCodeDel"),
    USER_COUPONE_CODE_DEL_DELETE("userCouponeCodeDelServiceImpl", "deleteUserCouponeCodeDel", "删除UserCouponeCodeDel"),
    USER_COUPONE_CODE_DEL_UPDATE("userCouponeCodeDelServiceImpl", "updateUserCouponeCodeDel", "修改UserCouponeCodeDel"),
    USER_COUPONE_CODE_DEL_GET("userCouponeCodeDelServiceImpl", "getUserCouponeCodeDel", "查询UserCouponeCodeDel"),


    /**
     * @Description 用户反馈接口
     * @Author taoye
     */
    USER_FEEDBACK_INSERT("userFeedbackServiceImpl", "insertUserFeedback", "保存UserFeedback"),
    USER_FEEDBACK_DELETE("userFeedbackServiceImpl", "deleteUserFeedback", "删除UserFeedback"),
    USER_FEEDBACK_UPDATE("userFeedbackServiceImpl", "updateUserFeedback", "修改UserFeedback"),
    USER_FEEDBACK_GET("userFeedbackServiceImpl", "getUserFeedback", "查询UserFeedback"),


    /**
     * @Description 问题反馈图片接口
     * @Author taoye
     */
    USER_FEEDBACK_IMAGES_INSERT("userFeedbackImagesServiceImpl", "insertUserFeedbackImages", "保存UserFeedbackImages"),
    USER_FEEDBACK_IMAGES_DELETE("userFeedbackImagesServiceImpl", "deleteUserFeedbackImages", "删除UserFeedbackImages"),
    USER_FEEDBACK_IMAGES_UPDATE("userFeedbackImagesServiceImpl", "updateUserFeedbackImages", "修改UserFeedbackImages"),
    USER_FEEDBACK_IMAGES_GET("userFeedbackImagesServiceImpl", "getUserFeedbackImages", "查询UserFeedbackImages"),


    /**
     * @Description 用户好友接口
     * @Author taoye
     */
    USER_FRIENDS_INSERT("userFriendsServiceImpl", "insertUserFriends", "保存UserFriends"),
    USER_FRIENDS_DELETE("userFriendsServiceImpl", "deleteUserFriends", "删除UserFriends"),
    USER_FRIENDS_UPDATE("userFriendsServiceImpl", "updateUserFriends", "修改UserFriends"),
    USER_FRIENDS_GET("userFriendsServiceImpl", "getUserFriends", "查询UserFriends"),


    /**
     * @Description 用户个人赚钱接口
     * @Author taoye
     */
    USER_MAKE_MONEY_INSERT("userMakeMoneyServiceImpl", "insertUserMakeMoney", "保存UserMakeMoney"),
    USER_MAKE_MONEY_DELETE("userMakeMoneyServiceImpl", "deleteUserMakeMoney", "删除UserMakeMoney"),
    USER_MAKE_MONEY_UPDATE("userMakeMoneyServiceImpl", "updateUserMakeMoney", "修改UserMakeMoney"),
    USER_MAKE_MONEY_GET("userMakeMoneyServiceImpl", "getUserMakeMoney", "查询UserMakeMoney"),


    /**
     * @Description 用户个人赚钱每月统计接口
     * @Author taoye
     */
    USER_MAKE_MONEY_MONTHS_INSERT("userMakeMoneyMonthsServiceImpl", "insertUserMakeMoneyMonths", "保存UserMakeMoneyMonths"),
    USER_MAKE_MONEY_MONTHS_DELETE("userMakeMoneyMonthsServiceImpl", "deleteUserMakeMoneyMonths", "删除UserMakeMoneyMonths"),
    USER_MAKE_MONEY_MONTHS_UPDATE("userMakeMoneyMonthsServiceImpl", "updateUserMakeMoneyMonths", "修改UserMakeMoneyMonths"),
    USER_MAKE_MONEY_MONTHS_GET("userMakeMoneyMonthsServiceImpl", "getUserMakeMoneyMonths", "查询UserMakeMoneyMonths"),


    /**
     * @Description 通知接口
     * @Author taoye
     */
    USER_NOTICES_INSERT("userNoticesServiceImpl", "insertUserNotices", "保存UserNotices"),
    USER_NOTICES_DELETE("userNoticesServiceImpl", "deleteUserNotices", "删除UserNotices"),
    USER_NOTICES_UPDATE("userNoticesServiceImpl", "updateUserNotices", "修改UserNotices"),
    USER_NOTICES_GET("userNoticesServiceImpl", "getUserNotices", "查询UserNotices"),


    /**
     * @Description C端收益日志接口
     * @Author taoye
     */
    USER_PROFIT_LOGS_INSERT("userProfitLogsServiceImpl", "insertUserProfitLogs", "保存UserProfitLogs"),
    USER_PROFIT_LOGS_DELETE("userProfitLogsServiceImpl", "deleteUserProfitLogs", "删除UserProfitLogs"),
    USER_PROFIT_LOGS_UPDATE("userProfitLogsServiceImpl", "updateUserProfitLogs", "修改UserProfitLogs"),
    USER_PROFIT_LOGS_GET("userProfitLogsServiceImpl", "getUserProfitLogs", "查询UserProfitLogs"),


    /**
     * @Description C端用户收益汇总接口
     * @Author taoye
     */
    USER_PROFITS_INSERT("userProfitsServiceImpl", "insertUserProfits", "保存UserProfits"),
    USER_PROFITS_DELETE("userProfitsServiceImpl", "deleteUserProfits", "删除UserProfits"),
    USER_PROFITS_UPDATE("userProfitsServiceImpl", "updateUserProfits", "修改UserProfits"),
    USER_PROFITS_GET("userProfitsServiceImpl", "getUserProfits", "查询UserProfits"),


    /**
     * @Description 用户实名信息接口
     * @Author taoye
     */
    USER_REALS_INSERT("userRealsServiceImpl", "insertUserReals", "保存UserReals"),
    USER_REALS_DELETE("userRealsServiceImpl", "deleteUserReals", "删除UserReals"),
    USER_REALS_UPDATE("userRealsServiceImpl", "updateUserReals", "修改UserReals"),
    USER_REALS_GET("userRealsServiceImpl", "getUserReals", "查询UserReals"),


    /**
     * @Description 用户任务接口
     * @Author taoye
     */
    USER_TASK_INSERT("userTaskServiceImpl", "insertUserTask", "保存UserTask"),
    USER_TASK_DELETE("userTaskServiceImpl", "deleteUserTask", "删除UserTask"),
    USER_TASK_UPDATE("userTaskServiceImpl", "updateUserTask", "修改UserTask"),
    USER_TASK_GET("userTaskServiceImpl", "getUserTask", "查询UserTask"),


    /**
     * @Description 用户任务完成接口
     * @Author taoye
     */
    USER_TASK_FINISHED_INSERT("userTaskFinishedServiceImpl", "insertUserTaskFinished", "保存UserTaskFinished"),
    USER_TASK_FINISHED_DELETE("userTaskFinishedServiceImpl", "deleteUserTaskFinished", "删除UserTaskFinished"),
    USER_TASK_FINISHED_UPDATE("userTaskFinishedServiceImpl", "updateUserTaskFinished", "修改UserTaskFinished"),
    USER_TASK_FINISHED_GET("userTaskFinishedServiceImpl", "getUserTaskFinished", "查询UserTaskFinished"),


    /**
     * @Description 用户名修改日志记录接口
     * @Author taoye
     */
    USER_UPDATE_LOGIN_NAME_LOGS_INSERT("userUpdateLoginNameLogsServiceImpl", "insertUserUpdateLoginNameLogs", "保存UserUpdateLoginNameLogs"),
    USER_UPDATE_LOGIN_NAME_LOGS_DELETE("userUpdateLoginNameLogsServiceImpl", "deleteUserUpdateLoginNameLogs", "删除UserUpdateLoginNameLogs"),
    USER_UPDATE_LOGIN_NAME_LOGS_UPDATE("userUpdateLoginNameLogsServiceImpl", "updateUserUpdateLoginNameLogs", "修改UserUpdateLoginNameLogs"),
    USER_UPDATE_LOGIN_NAME_LOGS_GET("userUpdateLoginNameLogsServiceImpl", "getUserUpdateLoginNameLogs", "查询UserUpdateLoginNameLogs"),


    /**
     * @Description 用户钱包流水接口
     * @Author taoye
     */
    USER_WALLET_LOGS_INSERT("userWalletLogsServiceImpl", "insertUserWalletLogs", "保存UserWalletLogs"),
    USER_WALLET_LOGS_DELETE("userWalletLogsServiceImpl", "deleteUserWalletLogs", "删除UserWalletLogs"),
    USER_WALLET_LOGS_UPDATE("userWalletLogsServiceImpl", "updateUserWalletLogs", "修改UserWalletLogs"),
    USER_WALLET_LOGS_GET("userWalletLogsServiceImpl", "getUserWalletLogs", "查询UserWalletLogs"),


    /**
     * @Description 用户钱包接口
     * @Author taoye
     */
    USER_WALLETS_INSERT("userWalletsServiceImpl", "insertUserWallets", "保存UserWallets"),
    USER_WALLETS_DELETE("userWalletsServiceImpl", "deleteUserWallets", "删除UserWallets"),
    USER_WALLETS_UPDATE("userWalletsServiceImpl", "updateUserWallets", "修改UserWallets"),
    USER_WALLETS_GET("userWalletsServiceImpl", "getUserWallets", "查询UserWallets"),


    USER_SHARE_RECORD_INSERT("userShareRecordServiceImpl", "insertUserShareRecord", "插入userShareRecord"),
    USER_SHARE_RECORD_ADD("userShareRecordServiceImpl", "addUserShareRecord", "插入userShareRecord"),


    USER_CERTIFACATION_GET("userCertificationServiceImpl", "selectUserCertification", "查询用户认证信息"),
    USER_CERTIFACATION_GET_BY_CARDID("userCertificationServiceImpl", "selectUserCertificationByCardId", "根据身份证号查询用户认证信息"),
    USER_CERTIFACATION_INSERT("userCertificationServiceImpl", "insertUserCertification", "插入用户认证信息"),
    USER_CERTIFACATION_UPDATE("userCertificationServiceImpl", "updateUserCertification", "更新用户认证状态"),
    USER_CERTIFACATION_GET_ISCERTIFACATE("userCertificationServiceImpl", "getUserCertificationByIdCardMobileName", "查询用户是否已认证"),


    /**
     * @Description 一小时达城市投票接口
     * @Author taoye
     */
    VOTE_CITY_INSERT("voteCityServiceImpl", "insertVoteCity", "保存VoteCity"),
    VOTE_CITY_DELETE("voteCityServiceImpl", "deleteVoteCity", "删除VoteCity"),
    VOTE_CITY_UPDATE("voteCityServiceImpl", "updateVoteCity", "修改VoteCity"),
    VOTE_CITY_GET("voteCityServiceImpl", "getVoteCity", "查询VoteCity"),
    HOME_VOTE_CITY_HOUR_FIND("voteCityServiceImpl", "find", "到家c一小时达投票查询"),
    HOME_VOTE_CITY_HOUR_INSERT("voteCityServiceImpl", "insert", "到家c一小时达头票插入"),
    HOME_VOTE_CITY_HOUR_FINDCOUNT("voteCityServiceImpl", "findCount", "到家c一小时达区域投票数查询"),


    /**
     * @Description 一小时达城市投票配置接口
     * @Author taoye
     */
    VOTE_CONFIG_INSERT("voteConfigServiceImpl", "insertVoteConfig", "保存VoteConfig"),
    VOTE_CONFIG_DELETE("voteConfigServiceImpl", "deleteVoteConfig", "删除VoteConfig"),
    VOTE_CONFIG_UPDATE("voteConfigServiceImpl", "updateVoteConfig", "修改VoteConfig"),
    VOTE_CONFIG_GET("voteConfigServiceImpl", "getVoteConfig", "查询VoteConfig"),


    /**
     * @Description 仓库信息接口
     * @Author taoye
     */
    WAREHOUSE_INFO_INSERT("warehouseInfoServiceImpl", "insertWarehouseInfo", "保存WarehouseInfo"),
    WAREHOUSE_INFO_DELETE("warehouseInfoServiceImpl", "deleteWarehouseInfo", "删除WarehouseInfo"),
    WAREHOUSE_INFO_UPDATE("warehouseInfoServiceImpl", "updateWarehouseInfo", "修改WarehouseInfo"),
    WAREHOUSE_INFO_GET("warehouseInfoServiceImpl", "getWarehouseInfo", "查询WarehouseInfo"),


    /**
     * @Description 不规范命名-禁止使用
     * @Author taoye
     */
    GZG_GOODS_PLAN_INSERT("gzgGoodsPlanServiceImpl", "insertGzgGoodsPlan", "保存GzgGoodsPlan"),
    GZG_GOODS_PLAN_DELETE("gzgGoodsPlanServiceImpl", "deleteGzgGoodsPlan", "删除GzgGoodsPlan"),
    GZG_RUJIN_YILIAN_GOODS_PLAN_UPDATE("gzgGoodsPlanServiceImpl", "updateGzgGoodsPlan", "修改GzgGoodsPlan"),
    GZG_SHOWCASE_GOODS_PLAN_UPDATE("gzgGoodsPlanServiceImpl", "updateGzgZgGoodsPlanNum", "修改GzgGoodsPlan"),
    GZG_GOODS_PLAN_GET("gzgGoodsPlanServiceImpl", "getGzgGoodsPlan", "查询GzgGoodsPlan"),
    GZG_GOODS_PLAN_UPDATE_STOCK("gzgGoodsPlanServiceImpl", "updateGzgGoodsPlanStock", "更新库存结果"),
    GZG_GOODS_PLAN_INSERT_BATCHS("gzgGoodsPlanServiceImpl", "insertBatchGzgGoodsPlans", "批量保存ZGGoodsPlan"),
    GZG_GOODS_PLAN_UPDATE_BATCH("gzgGoodsPlanServiceImpl", "updateBatchGzgGoodsPlan", "批量更新GzgGoodsPlan"),
    GZG_GOODS_PLAN_INSERT_BATCH("gzgGoodsPlanServiceImpl", "insertBatchGzgGoodsPlan", "批量保存GzgGoodsPlan"),
    GZG_GOODS_PLAN_SUIT_GET("gzgGoodsPlanServiceImpl", "getGzgGoodsPlanSuitNum", "查询货柜套装数量"),
    GZG_GOODS_PLAN_TOP3_GET("gzgGoodsPlanServiceImpl", "getTop3GzgGoodsPlan", "查询展柜top3商品"),

    /**
     * 有权限直接打开格子柜柜门的运维人员
     */
    GZG_USER_OPEN_DEVICE_GET_BY_PHONE("gzgUserOpenDeviceServiceImpl", "getGzgUserOpenDeviceByPhone", "通过手机号码查询可以直接打开格子柜柜门的运维人员信息"),

    /**
     * @Description: 数据中心--赚钱
     */
    PROFITS_USER_PROFITS_DETAILS("profitsUserProfitsServiceImpl", "getProfitsDetails", "查询赚钱详情"),
    PROFITS_USER_TASK_NOT_FINISH("profitsUserTaskServiceImpl", "getNotFinishTaskByUId", "没有完成的任务"),
    PROFITS_USER_PROFIT_LOGS("profitsUserProfitLogsServiceImpl", "getLogs", "获取赚钱记录日志"),
    PROFITS_USER_PROFIT_LOGS_FRIEDNS_ORDER_RANK("profitsUserProfitLogsServiceImpl", "getFriednsOrderRank", "获取好友下单赚钱排行"),

    /**
     * 蓝牙APP -烟弹信息操作接口
     */
    BLUETOOTH_TASTE_INFO_GET("bluetoothTasteInfoServiceImpl", "getBluetoothTasteList", "获取所有烟弹列表"),
    BLUETOOTH_USER_TASTE_INERT("bluetoothUserTasteServiceImpl", "insertBluetoothUserTaste", "用户添加烟弹"),
    BLUETOOTH_USER_TASTE_GET("bluetoothUserTasteServiceImpl", "getBluetoothUserTaste", "根据条件获取用户最新添加烟弹"),
    BLUETOOTH_TASTE_BEST_GET("bluetoothTasteBestServiceImpl", "getBluetoothTasteBestByTastId", "根据烟弹id获取最佳匹配"),
    /**
     * 蓝牙APP -用户首页操作设置接口
     */
    BLUETOOTH_INDEX_BATCH_INSERT("bluetoothIndexServiceImpl", "insertBluetoothIndex", "批量插入用户设置的指标信息"),
    BLUETOOTH_INDEX_GET("bluetoothIndexServiceImpl", "getBluetoothIndex", "获取用户指标设置"),
    BLUETOOTH_INDEX_DEL("bluetoothIndexServiceImpl", "deleteBluetoothIndexByCondtion", "删除并更新用户指标设置"),
    /**
     * 蓝牙APP -蓝牙日志数据操作接口
     */
    BLUETOOTH_INSERT_LOG("bluetoothLogServiceImpl", "insertBluetoothLog", "批量插入蓝牙日志"),
    BLUETOOTH_LOG_SMOKING_DATA_HOUR_COUNT("bluetoothLogServiceImpl", "countBluetoothLogSmokingDataHour", "计算每小时抽烟数据"),
    BLUETOOTH_LOG_SMOKING_DATA_DAY_COUNT("bluetoothLogServiceImpl", "countBluetoothLogSmokingDataDay", "计算按天抽烟数据-日期连续"),
    BLUETOOTH_LOG_SMOKING_HAVE_DATA_DAY_COUNT("bluetoothLogServiceImpl", "countBluetoothLogSmokingHaveDataDay", "计算有抽烟数据的天数-日期不连续"),
    BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL("bluetoothLogServiceImpl", "totalBluetoothLogSmokingMonthNumber", "累计抽烟总口数"),


    /**
     * 蓝牙APP -附属信息
     */
    BLUETOOTH_SUBSIDARY_INSERT("bluetoothUserSubsidiaryServiceImpl", "insertBluetoothUserSubsidiary", "保存用户目标设定"),
    BLUETOOTH_SUBSIDARY_UPDATE("bluetoothUserSubsidiaryServiceImpl", "updateBluetoothUserSubsidiary", "更新用户目标设定"),
    BLUETOOTH_SUBSIDARY_GET("bluetoothUserSubsidiaryServiceImpl", "getBluetoothUserSubsidiary", "获取用户目标设定"),
    BLUETOOTH_SUBSIDARY_COUNT("bluetoothUserSubsidiaryServiceImpl", "countBluetoothUserSubsidiary", "统计用户是否设置目标设定"),
    BLUETOOTH_USER_SUGGESTIONS_INSERT("bluetoothUserSuggestionsServiceImpl", "insertBluetoothUserSuggestions", "保存用户投诉建议"),
    BLUETOOTH_FIRMWARE_GET("bluetoothFirmwareSettingServiceImpl", "getBluetoothFirmwareSetting", "获取最新固件版本"),

    /**
     * 蓝牙APP -烟杆信息操作接口
     */
    BLUETOOTH_USER_BIND_INERT("bluetoothUserBindServiceImpl", "insertBluetoothUserBind", "用户绑定烟杆"),
    BLUETOOTH_USER_BIND_UPDATE("bluetoothUserBindServiceImpl", "updateBluetoothUserBindById", "用户修改名称/解绑烟杆/设置儿童锁"),
    BLUETOOTH_USER_BIND_GET("bluetoothUserBindServiceImpl", "getBluetoothUserBindByCondtions", "获取用户烟杆绑定列表"),
    BLUETOOTH_USER_BIND_GET_ONE("bluetoothUserBindServiceImpl", "getBluetoothUserBindById", "获取单个烟杆信息"),
    BLUETOOTH_USER_BIND_UPDATE_BY_CONDTIONS("bluetoothUserBindServiceImpl", "updateBluetoothUserBindByCondtion", "根据条件更新烟杆"),

    /**
     * 蓝牙APP -token操作
     */
    BLUETOOTH_TOKEN_INSERT("bluetoothTokenServiceImpl", "insertBluetoothToken", "保存用户token"),
    BLUETOOTH_TOKEN_UPDATE("bluetoothTokenServiceImpl", "updateBluetoothToken", "更新用户token"),
    BLUETOOTH_TOKEN_GET("bluetoothTokenServiceImpl", "getBluetoothToken", "获取用户token"),

    /**
     * 蓝牙APP -报修操作service
     */
    BLUETOOTH_REPAIRS_INERT("blueToothRepairesServiceImpl", "insertBlueToothRepairs", "生成报修单"),
    BLUETOOTH_REPAIRS_GET("blueToothRepairesServiceImpl", "getBluetoothRepair", "获取报修单信息"),
    BLUETOOTH_REPAIRS_UPDATE("blueToothRepairesServiceImpl", "updateBluetoothRepair", "更新报修单信息"),

    /**
     * 蓝牙APP -新闻信息操作service
     */
    BLUETOOTH_NEWS_GET("bluetoothNewsServiceImpl", "getBluetoothNewsList", "查询新闻列表数据"),
    BLUETOOTH_NEWS_COLLECT_GET("bluetoothNewsServiceImpl", "getBluetoothNewsCollectList", "查询用户收藏新闻列表数据"),
    BLUETOOTH_NEWSR_UPDATE_READ_NUMBER("bluetoothNewsServiceImpl", "updateBluetoothNewsReadNumber", "更新新闻阅读次数+1"),
    BLUETOOTH_NEWSR_UPDATE_COLLECT_NUMBER("bluetoothNewsServiceImpl", "updateBluetoothNewsCollectNumber", "更新收藏人数加1"),
    BLUETOOTH_NEWSR_UPDATE_COLLECT_MINUS_NUMBER("bluetoothNewsServiceImpl", "updateBluetoothNewsCollectMinusNumber", "更新收藏人数减1"),
    BLUETOOTH_NEWSR_GET_DETAIL("bluetoothNewsServiceImpl", "getBluetoothNewsDetail", "查询新闻详情"),

    /**
     * 蓝牙APP -新闻收藏操作service
     */
    BLUETOOTH_NEWS_COLLECT_DELETE("bluetoothNewsCollectServiceImpl", "deleteBluetoothNewsCollect", "用户删除收藏"),
    BLUETOOTH_NEWS_COLLECT_INSERT("bluetoothNewsCollectServiceImpl", "insertBluetoothNewsCollect", "查询用户收藏新闻列表数据"),
    BLUETOOTH_NEWS_COLLECT_GET_USER("bluetoothNewsCollectServiceImpl", "getBluetoothNewsCollect", "查询用户是否收藏"),


    /**
     * 微信-店铺
     */
    WX_SHOP_INSERT("wxShopServiceImpl", "insertWxShop", "保存店铺信息"),
    WX_SHOP_GET("wxShopServiceImpl", "getWxShopList", "获取微信店铺列表"),
    WX_SHOP_WEB_GET("wxShopWebServiceImpl", "getWxShopList", "WEB后台获取微信店铺列表"),
    WX_SHOP_DELETE("wxShopWebServiceImpl", "deleteWxShop", "删除店铺信息"),
    WX_SHOP_UPDATE("wxShopWebServiceImpl", "updateWxShop", "更新店铺信息"),
    WX_SHOP_WEB_GET_ONE("wxShopWebServiceImpl", "getWxShop", "WEB后台获取单个微信店铺信息"),


    /**
     * cabinet
     */
    CABINET_OPTIONS_GETOPTIONS("cabinetOptionsServiceImpl", "getOptions", "获取合作信息options"),
    CABINET_BUSSINESS_COORPERATIONS("cabinetBusinessCoorperationServiceImpl", "createBussinessCoorperation", "创建合作"),
    CABINET_BUSSINESS_COORPERATIONS_SAVE("cabinetBusinessCoorperationServiceImpl", "cabCoorperationSave", "格子柜保存合作"),
    CABINET_BUSSINESS_COORPERATIONS_CREATE("cabinetBusinessCoorperationServiceImpl", "cabCoorperationCreate", "格子柜创建合作"),
    SHOWCASE_BUSSINESS_COORPERATIONS_SAVE("cabinetBusinessCoorperationServiceImpl", "caseCoorperationSave", "展柜创建合作"),
    SHOWCASE_BUSSINESS_COORPERATIONS_CREATE("cabinetBusinessCoorperationServiceImpl", "caseCoorperationCreate", "展柜创建合作"),
    CABINET_BD_STORE_SEARCH("cabinetBusinessCoorperationServiceImpl", "search", "搜索"),
    CABINET_STORE_BASIC_AREA("cabinetBusinessCoorperationServiceImpl", "basicArea", "搜索"),
    CABINET_BD_NAME("cabinetBusinessCoorperationServiceImpl", "findBdName", "搜索BdName"),
    CABINET_COORPERATION_INFO("cabinetBusinessCoorperationServiceImpl", "coorperationInfo", "合作信息"),
    CABINET_COORPERATION_TREAT_CREATE("cabinetBusinessCoorperationServiceImpl", "coorperationTreatCreate", "待创建"),
    CABINET_COORPERATION_CREATED("cabinetBusinessCoorperationServiceImpl", "coorperationCreated", "已创建"),
    CABINET_COORPERATION_ALREADY_USED("cabinetBusinessCoorperationServiceImpl", "coorperationAlreadyUsed", "待创建"),
    CABINET_COORPERATION_ALL("cabinetBusinessCoorperationServiceImpl", "coorperationAll", "待创建"),
    CABINET_COORPERATION_GET_BY_STRORE("cabinetBusinessCoorperationServiceImpl", "coorperationGetByStroreId", "通过店铺id查询商务合作信息"),
    CABINET_COORPERATION_GET_BY_STRORE_STATUS("cabinetBusinessCoorperationServiceImpl", "selectByStoreIdAndStatus", "通过店铺id和status查询商务合作信息"),

    CABINET_FIND_CABINETS_BY_USER("cabinetInfoServiceImpl", "cabinatsByUser", "根据用户查询旗下管理的格子柜"),
    CABINET_SQUARE_CABINET_COOP_MESSAGE("cabinetInfoServiceImpl", "cabinatsCoopMessage", "单个格子柜绑定合作信息"),
    CABINET_DOWN_SQUARE_CABINET("cabinetInfoServiceImpl", "downSquareCabinet", "下架单个格子柜"),
    CABINET_FIND_SHOW_CABINETS_BY_USER("cabinetInfoServiceImpl", "showCabinatsByUser", "查询bd展柜列表"),
    CABINET_SHOW_CABINET_COOP_MESSAGE("cabinetInfoServiceImpl", "showcaseCabinatsCoopMsg", "展柜合作信息"),
    CABINET_DOWN_SHOWCASE_CABINET("cabinetInfoServiceImpl", "downShowcaseCabinet", "下架单个展柜"),
    CABINET_UP_SHOWCASE_CABINET("cabinetInfoServiceImpl", "upShowcaseCabinet", "上架单个展柜"),
    CABINET_FIND_BD_SHOPS("cabinetStoreServiceImpl", "findBdShops", "bd下面的全部店铺"),
    CABINET_FIND_BD_DATA_DETAIL("cabinetStoreServiceImpl", "findBdDataDetail", "bd数据统计汇总详情信息"),
    CABINET_FIND_BD_DATA_STATISTICS("cabinetStoreServiceImpl", "findBdDataStatistics", "bd店铺的数据统计详情集合"),
    CABINET_FIND_BD_STORE_ORDERS("cabinetStoreServiceImpl", "findBdStoreOrders", "bd指定店铺的数据统计"),

    CASH_RECORD_BY_USERID_TODAY("cabinetCashRecordServiceImpl", "selectRecordByUserIdToday", "根据userId,查询今日提现申请成功数据；当userId为空，查询今日所有成功数据 -查询接口"),
    CABINET_CASH_RECORD_INSERT("cabinetCashRecordServiceImpl", "insert", "添加用户提现申请数据——新增接口"),
    CABINET_CASH_RECORD_UPDATE("cabinetCashRecordServiceImpl", "updateById", "修改用户提现申请数据——修改接口"),
    CABINET_CASH_RECORD_BY_STATUS("cabinetCashRecordServiceImpl", "selectRecordByStatus", "根据status,查询提现申请数据——查询接口"),
    CABINET_REPLENISH_REWARD_BY_USERID("cabinetReplenishRewardServiceImpl", "queryCabinetReplenishRewardByUserId", "根据userId,查询用户的奖励数据,type可为空——查询接口"),
    UPDATE_REPLENISH_REWARD_BYID("cabinetReplenishRewardServiceImpl", "updateReplenishRewardById", "根据id,修改用户的奖励金额数据——修改接口"),
    CABINET_REPLENISH_REWARD_INSERT("cabinetReplenishRewardServiceImpl", "insertCabinetReplenishReward", "新增奖励数据——新增接口"),

    /**
     * @Description 数据中心——店铺信息接口
     */
    SHOP_GET_SHOP_BY_ID("shopInfoServiceImpl", "getShopInfoById", "根据店铺id查询店铺信息"),
    SHOP_GET_SHOP_ADDRESS_BY_SHOP_ID("shopAddressServiceImpl", "getShopDefaultAddressByShopId", "根据店铺id查询店铺默认地址信息"),
    GZG_PLAN_QUERY("gzgPlanServiceImpl", "getGzgPlan", "查询格子柜所对应的的配货方案"),
    GZG_INFO_QUERY("gzgInfoServiceImpl", "getGzgInfo", "查询格子柜所对应的的配货方案的id"),
    GOODS_SKU_QUERY("goodsSkuInfoServiceImpl", "getGoodsSkuInfoById", "查询商品销售属性 -查询接口"),
    GOODS_SPU_QUERY("goodsSpuInfoServiceImpl", "getGoodsSpuInfoById", "查询商品spu -查询接口"),
    GOODS_SKU_PICTURE_QUERY("goodsSkuPictureServiceImpl", "getGoodsSkuPictureBySkuId", "查询商品sku图片 -查询接口"),
    GOODS_SPU_QUERY_BY_CATEGROY("goodsSpuInfoServiceImpl", "getGoodsSpuInfoByCategroy", "根据分类id，查询商品spu数据 -查询接口"),
    GOODS_SPU_QUERY_BY_AEROSOL("goodsSpuInfoServiceImpl", "getGoodsSpuInfoByAerosol", "根据雾化弹分类id，查询雾化弹spu数据 -查询接口"),
    GOODS_HOUR_SPU_QUERY_LIST("goodsSpuInfoServiceImpl", "queryHourSpu", "分页查询一小时达商品spu列表 -查询接口"),
    GOODS_SPU_INFO_TOP_FIVE_LIST("goodsSpuInfoServiceImpl", "getListTop5ByCid", "根据类目查询top5商品"),
    GOODS_SPU_QUERY_SELLER_BY_ID("goodsSpuInfoServiceImpl", "getSpuGoodsSellerNumberEachMonth", "统计商品月销量接口"),
    GOODS_SPU_PICTURE_BY_SPUID("goodsSpuPictureServiceImpl", "getGoodsSpuPictureBySpuId", "根据商品spu id，查询商品图片数据 -查询接口"),
    GOODS_SPU_ATTR_BY_SPUID("goodsSpuAttrServiceImpl", "getGoodsSpuAttrBySpuId", "根据商品spu id，查询商品属性数据 -查询接口"),
    GOODS_SPU_ATTR_VALUE_BY_SPUID("goodsSpuAttrValueServiceImpl", "getGoodsSpuAttrValueBySpuId", "根据商品spu id，查询商品属性值数据 -查询接口"),
    GOODS_SPU_ALL_ATTR_VALUE_BY_SPUID("goodsSpuAttrValueServiceImpl", "getGoodsSpuAllAttrValueBySpuId", "根据商品spu id，查询商品所有属性值数据 -查询接口"),
    GOODS_SPU_HOUR_ATTR_VALUE_BY_SPUID("goodsSpuAttrServiceImpl", "getGoodsHourSpuAttrValueBySpuId", "根据商品spu id，查询一小时达商品对应sku商品属性值数据 -查询接口"),
    GOODS_SKU_BY_SPUID_ATTR("goodsSkuInfoServiceImpl", "getGoodsSkuInfoBySpuIdAndAttr", "根据spuId和attrbutes，查询商品sku -查询接口"),
    GOODS_SKU_BY_SPUID("goodsSkuInfoServiceImpl", "getGoodsSkuInfoBySpuId", "根据spuId，查询商品sku -查询接口"),
    GOODS_SKU_BY_CONDTION("goodsSkuInfoServiceImpl", "getGoodsSkuInfoBySpuIdNew", "根据spuId，查询商品sku -查询接口"),
    GOODS_SPU_LIST_FOR_SHOP("goodsSpuInfoServiceImpl", "getSpuListForShop", "查询商家spuList"),
    GZG_BANNER_PICTURE_QUERY("gzgBannerPictureServiceImpl", "getGzgBannerPicture", "查询格子柜首页滚动图片地址"),
    USER_INSERT_USER("userServiceImpl", "insertUser", "保存User"),
    USER_DELETE_USER("userServiceImpl", "deleteUser", "删除User"),
    USER_UPDATE_USER("userServiceImpl", "updateUser", "更新User"),
    USER_GET_USER_BY_CONDTIONS("userServiceImpl", "getUserByCondtions", "根据查询条件查询用户信息"),
    DEFAULT_USERADDRESS_BY_USERID("userAddressServiceImpl", "getDefaultUserAddressByUserId", "根据userId查询用户默认收货地址"),
    USERADDRESS_INSERT("userAddressServiceImpl", "insertUserAddress", "新增用户收货地址"),
    USERADDRESS_BY_USERID("userAddressServiceImpl", "getUserAddressByUserId", "根据userId分页查询用户收货地址"),
    USERADDRESS_DEFAULT_UPDATE_BY_USERID("userAddressServiceImpl", "updateUserAddressDefaultByUserId", "根据userId，设置用户收货地址是否是默认收货地址"),
    USERADDRESS_BY_ID("userAddressServiceImpl", "getUserAddressById", "根据id查询收货地址"),
    USERADDRESS_DEFAULT_UPDATE_BY_ID("userAddressServiceImpl", "updateUserAddressDefaultById", "根据id,设置收货地址是否是默认收货地址"),
    USERADDRESS_UPDATE_BY_ID("userAddressServiceImpl", "updateUserAddressById", "根据id,修改收货地址"),
    USERADDRESS_UPDATE("userAddressServiceImpl", "updateUserAddress", "根据条件,修改收货地址"),
    PUNCH_PIONT_DATA_BY_USERID("userPointDataServiceImpl", "getUserPointDataByUserId", "根据userId，查询用户打卡数据 -查询接口"),
    PUNCH_PIONT_DATA_BY_USERID_TODAY("userPointDataServiceImpl", "getUserPointDataByUserIdAndToday", "根据userId，查询用户今天打卡数据 -查询接口"),
    PUNCH_PIONT_GRADE_BY_USERID("userPointGradeServiceImpl", "getUserPointGradeByUserId", "根据userId，查询用户打卡总积分数据 -查询接口"),
    PUNCH_CARD_POINT_BY_STATUS("punchCardPointServiceImpl", "getPunchCardPointByStatus", "根据userId，查询用户打卡总积分数据 -查询接口"),
    POINT_CONFIG_BY_ID("pointConfigServiceImpl", "getPointConfigById", "根据id，查询积分规则数据 -查询接口"),
    POINT_GRAD_BY_SCORE("pointGradeServiceImpl", "getPointGradeByScore", "根据score，查询积分等级数据 -查询接口"),
    PUNCH_PIONT_GRADE_UPDATE_BY_ID("userPointGradeServiceImpl", "updateUserPointGradeById", "根据id，更新用户打卡总积分数据 -查询接口"),
    PUNCH_PIONT_GRADE_INSERT("userPointGradeServiceImpl", "insertUserPointGrade", "新增用户打卡总积分数据 -查询接口"),
    PUNCH_PIONT_DATA_INSERT("userPointDataServiceImpl", "insertUserPointData", "新增用户打卡数据 -查询接口"),
    PUNCH_CARD_COUPON_BY_STATUS("punchCardCouponServiceImpl", "getPunchCardCouponByStatus", "根据状态status,查询 打卡关联活动优惠数据 -查询接口"),
    USER_PUNCH_CARD_INSERT("userPunchCardServiceImpl", "insertUserPunchCard", "新增用户打卡数据 -查询接口"),
    USER_UPDATE_BY_ID("userServiceImpl", "updateUserById", "根据id更新User"),
    GOODS_SPU_QUERY_ALL("goodsSpuInfoServiceImpl", "queryGoodsSpuInfo", "查询有效的商品spu -查询接口"),

    GOODS_SPU_CONFIGURATION_QUERY("goodsSpuConfigurationServiceImpl", "querySpuConfigGoods", "查询功能配置商品表的商品集合接口"),

    GOODS_SPU_INFO_BY_SPU_IDS("goodsSpuInfoServiceImpl", "getGoodsSpuInfoBySpuIdList", "根据商品spu_id，查询商品图片价格等数据信息接口"),

    GOODS_FRONT_QUERY("goodsFrontServiceImpl", "getGoodsFront", "查询front商品 -查询接口"),
    GOODS_FRONT_SKU_BY_SPUID("goodsFrontServiceImpl", "getGoodsSkuBySpuId", "查询front sku数据 -查询接口"),
    GOODS_FRONT_SKU_BY_SKUID("goodsFrontServiceImpl", "getGoodsSkuBySkuId", "查询front sku数据 -查询接口"),
    GOODS_FRONT_BY_APPTYPE_ACTICLASS("goodsFrontServiceImpl", "queryGoodsFrontByActiClass", "查询front数据 -查询接口"),
    GOODS_FRONT_SPUID_QUERY("goodsFrontServiceImpl", "getGoodsFrontSpuId", "查询front 商品spuId -查询接口"),

    HD_PARTNER_QEURY_BY_MOBILE("hdPartnerServiceImpl", "queryPartnerByMobile", "根据手机号查询合伙人是否已经注册过。"),
    HD_PARTNER_ADD("hdPartnerServiceImpl", "addPartner", "新增一位MOTI合伙人");


    /**
     * 接口名字
     */
    private final String serviceName;
    /**
     * 方法名字
     */
    private final String functionName;
    /**
     * 方法描述
     */
    private final String desc;

    DataCenterMethod(String serviceName, String functionName, String desc) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.desc = desc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getDesc() {
        return desc;
    }
}