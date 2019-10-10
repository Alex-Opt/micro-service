package com.ly.mt.core.fegin.method;

/**
 * @Description 数据中心服务接口
 * @Author taoye
 */
public enum DataCenterMethod {
    /**
     * @Description 事务测试
     */
    TXC_ADD1("txcServiceImpl", "add1", ""),
    TXC_ADD2("txcServiceImpl", "add2", ""),


    /**
     * @Description 促销优惠活动限制spu商品接口
     * @Author taoye
     */
    ACTIVITY_GOODS_DETAIL_INSERT("activityGoodsDetailServiceImpl", "inserActivityGoodsDetail", "保存ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_DELETE("activityGoodsDetailServiceImpl", "deleteActivityGoodsDetail", "删除ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_UPDATE("activityGoodsDetailServiceImpl", "updateActivityGoodsDetail", "更新ActivityGoodsDetail"),
    ACTIVITY_GOODS_DETAIL_GET("activityGoodsDetailServiceImpl", "getActivityGoodsDetail", "查询ActivityGoodsDetail"),


    /**
     * @Description 促销优惠活动信息接口
     * @Author taoye
     */
    ACTIVITY_INFO_INSERT("activityInfoServiceImpl", "insertActivityInfo", "保存ActivityInfo"),
    ACTIVITY_INFO_DELETE("activityInfoServiceImpl", "deleteActivityInfo", "删除ActivityInfo"),
    ACTIVITY_INFO_UPDATE("activityInfoServiceImpl", "updateActivityInfo", "更新ActivityInfo"),
    ACTIVITY_INFO_GET("activityInfoServiceImpl", "getActivityInfo", "查询ActivityInfo"),
    ACTIVITY_INFO_GET_BY_SPU_ID("activityInfoServiceImpl", "getActivityInfoBySpuId", "根据spuId查询优惠活动数据"),


    /**
     * @Description 格子柜入驻报名信息接口
     * @Author taoye
     */
    ACTIVITY_SIGN_UP_CABINET_INSERT("activitySignUpCabinetServiceImpl", "insertActivitySignUpCabinet", "保存ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_DELETE("activitySignUpCabinetServiceImpl", "deleteActivitySignUpCabinet", "删除ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_UPDATE("activitySignUpCabinetServiceImpl", "updateActivitySignUpCabinet", "更新ActivitySignUpCabinet"),
    ACTIVITY_SIGN_UP_CABINET_GET("activitySignUpCabinetServiceImpl", "getActivitySignUpCabinet", "查询ActivitySignUpCabinet"),


    /**
     * @Description 促销优惠活动限制用户等级接口
     * @Author taoye
     */
    ACTIVITY_USER_GRADE_INSERT("activityUserGradeServiceImpl", "insertActivityUserGrade", "保存ActivityUserGrade"),
    ACTIVITY_USER_GRADE_DELETE("activityUserGradeServiceImpl", "deleteActivityUserGrade", "删除ActivityUserGrade"),
    ACTIVITY_USER_GRADE_UPDATE("activityUserGradeServiceImpl", "updateActivityUserGrade", "修改ActivityUserGrade"),
    ACTIVITY_USER_GRADE_GET("activityUserGradeServiceImpl", "getActivityUserGrade", "查询ActivityUserGrade"),


    /**
     * @Description 参与促销优惠活动的等级用户使用明细接口
     * @Author taoye
     */
    ACTIVITY_USER_GRADE_DETAIL_INSERT("activityUserGradeDetailServiceImpl", "insertActivityUserGradeDetail", "保存ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_DELETE("activityUserGradeDetailServiceImpl", "deleteActivityUserGradeDetail", "删除ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_UPDATE("activityUserGradeDetailServiceImpl", "updateActivityUserGradeDetail", "修改ActivityUserGradeDetail"),
    ACTIVITY_USER_GRADE_DETAIL_GET("activityUserGradeDetailServiceImpl", "getActivityUserGradeDetail", "查询ActivityUserGradeDetail"),


    /**
     * @Description 行政区域信息接口
     * @Author taoye
     */
    BASIC_AREA_INSERT("basicAreaServiceImpl", "insertBasicArea", "保存BasicArea"),
    BASIC_AREA_DELETE("basicAreaServiceImpl", "deleteBasicArea", "删除BasicArea"),
    BASIC_AREA_UPDATE("basicAreaServiceImpl", "updateBasicArea", "修改BasicArea"),
    BASIC_AREA_GET("basicAreaServiceImpl", "getBasicArea", "查询BasicArea"),


    /**
     * @Description 一小时达开通城市接口
     * @Author taoye
     */
    CITY_ONE_HOUR_INSERT("cityOneHourServiceImpl", "insertCityOneHour", "保存CityOneHour"),
    CITY_ONE_HOUR_DELETE("cityOneHourServiceImpl", "deleteCityOneHour", "删除CityOneHour"),
    CITY_ONE_HOUR_UPDATE("cityOneHourServiceImpl", "updateCityOneHour", "修改CityOneHour"),
    CITY_ONE_HOUR_GET("cityOneHourServiceImpl", "getCityOneHour", "查询CityOneHour"),
    CITY_ONE_HOUR_FINDBYAREA("cityOneHourServiceImpl", "findByAreaId", "根据areaId查询"),


    /**
     * @Description 优惠券码接口
     * @Author taoye
     */
    COUPON_CODE_INSERT("couponCodeServiceImpl", "insertCouponCode", "保存CouponCode"),
    COUPON_CODE_DELETE("couponCodeServiceImpl", "deleteCouponCode", "删除CouponCode"),
    COUPON_CODE_UPDATE("couponCodeServiceImpl", "updateCouponCode", "修改CouponCode"),
    COUPON_CODE_GET("couponCodeServiceImpl", "getCouponCode", "查询CouponCode"),
    COUPON_CODE_UPDATE_BY_CODE("couponCodeServiceImpl", "updateConponCodeByCode", "用户兑换兑换码"),


    /**
     * @Description 优惠券对应商品接口
     * @Author taoye
     */
    COUPON_GOODS_INSERT("couponGoodsServiceImpl", "insertCouponGoods", "保存CouponGoods"),
    COUPON_GOODS_DELETE("couponGoodsServiceImpl", "deleteCouponGoods", "删除CouponGoods"),
    COUPON_GOODS_UPDATE("couponGoodsServiceImpl", "updateCouponGoods", "修改CouponGoods"),
    COUPON_GOODS_GET("couponGoodsServiceImpl", "getCouponGoods", "查询CouponGoods"),


    /**
     * @Description 优惠券接口
     * @Author taoye
     */
    COUPON_INFO_INSERT("couponInfoServiceImpl", "insertCouponInfo", "保存CouponInfo"),
    COUPON_INFO_DELETE("couponInfoServiceImpl", "deleteCouponInfo", "删除CouponInfo"),
    COUPON_INFO_UPDATE("couponInfoServiceImpl", "updateCouponInfo", "修改CouponInfo"),
    COUPON_INFO_GET("couponInfoServiceImpl", "getCouponInfo", "查询CouponInfo"),
    COUPON_INFO_GET_BY_USER_ID("couponInfoServiceImpl", "getCouponInfoByUserId", "根据userId查询CouponInfo"),
    COUPON_INFO_GET_BY_SPU_ID("couponInfoServiceImpl", "getCouponInfoBySpuId", "根据spuId查询CouponInfo"),
    COUPON_INFO_FOR_NEW_USER("couponInfoServiceImpl", "getNewUserCoupons", "获取新人优惠券"),

    /**
     * @Description 商品属性信息接口
     * @Author taoye
     */
    GOODS_ATTR_INSERT("goodsAttrServiceImpl", "insertGoodsAttr", "保存GoodsAttr"),
    GOODS_ATTR_DELETE("goodsAttrServiceImpl", "deleteGoodsAttr", "删除GoodsAttr"),
    GOODS_ATTR_UPDATE("goodsAttrServiceImpl", "updateGoodsAttr", "修改GoodsAttr"),
    GOODS_ATTR_GET("goodsAttrServiceImpl", "getGoodsAttr", "查询GoodsAttr"),


    /**
     * @Description 商品属性值接口
     * @Author taoye
     */
    GOODS_ATTR_VALUE_INSERT("goodsAttrValueServiceImpl", "insertGoodsAttrValue", "保存GoodsAttrValue"),
    GOODS_ATTR_VALUE_DELETE("goodsAttrValueServiceImpl", "deleteGoodsAttrValue", "删除GoodsAttrValue"),
    GOODS_ATTR_VALUE_UPDATE("goodsAttrValueServiceImpl", "updateGoodsAttrValue", "修改GoodsAttrValue"),
    GOODS_ATTR_VALUE_GET("goodsAttrValueServiceImpl", "getGoodsAttrValue", "查询GoodsAttrValue"),


    /**
     * @Description 品牌信息接口
     * @Author taoye
     */
    GOODS_BRAND_INFO_INSERT("goodsBrandInfoServiceImpl", "insertGoodsBrandInfo", "保存GoodsBrandInfo"),
    GOODS_BRAND_INFO_DELETE("goodsBrandInfoServiceImpl", "deleteGoodsBrandInfo", "删除GoodsBrandInfo"),
    GOODS_BRAND_INFO_UPDATE("goodsBrandInfoServiceImpl", "updateGoodsBrandInfo", "修改GoodsBrandInfo"),
    GOODS_BRAND_INFO_GET("goodsBrandInfoServiceImpl", "getGoodsBrandInfo", "查询GoodsBrandInfo"),


    /**
     * @Description 类目信息接口
     * @Author taoye
     */
    GOODS_CATEGROY_INFO_INSERT("goodsCategroyInfoServiceImpl", "insertGoodsCategroyInfo", "保存GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_DELETE("goodsCategroyInfoServiceImpl", "deleteGoodsCategroyInfo", "删除GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_UPDATE("goodsCategroyInfoServiceImpl", "updateGoodsCategroyInfo", "修改GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_GET("goodsCategroyInfoServiceImpl", "getGoodsCategroyInfo", "查询GoodsCategroyInfo"),
    GOODS_CATEGROY_INFO_GET_BY_PARENT_ID("goodsCategroyInfoServiceImpl", "getGoodsCategroyInfoByParentId", "根据上级id，查询商品类目数据 -查询接口"),

    /**
     * @Description 一小时达关联商品接口
     * @Author taoye
     */
    GOODS_HOUR_SKU_INSERT("goodsHourSkuServiceImpl", "insertGoodsHourSku", "保存GoodsHourSku"),
    GOODS_HOUR_SKU_DELETE("goodsHourSkuServiceImpl", "deleteGoodsHourSku", "删除GoodsHourSku"),
    GOODS_HOUR_SKU_UPDATE("goodsHourSkuServiceImpl", "updateGoodsHourSku", "修改GoodsHourSku"),
    GOODS_HOUR_SKU_GET("goodsHourSkuServiceImpl", "getGoodsHourSku", "查询GoodsHourSku"),
    GOODS_HOUR_QUERY_BY_SKUIDLIST("goodsHourSkuServiceImpl", "getGoodsSkuHourInfoBySkuIds", "根据的入参skuId集合查询一小时达的商品信息集合"),


    /**
     * @Description 商品唯一标识码接口
     * @Author taoye
     */
    GOODS_SKU_CODE_INSERT("goodsSkuCodeServiceImpl", "insertGoodsSkuCode", "保存GoodsSkuCode"),
    GOODS_SKU_CODE_DELETE("goodsSkuCodeServiceImpl", "deleteGoodsSkuCode", "删除GoodsSkuCode"),
    GOODS_SKU_CODE_UPDATE("goodsSkuCodeServiceImpl", "updateGoodsSkuCode", "修改GoodsSkuCode"),
    GOODS_SKU_CODE_GET("goodsSkuCodeServiceImpl", "getGoodsSkuCode", "查询GoodsSkuCode"),


    /**
     * @Description 商品SKU信息接口
     * @Author taoye
     */
    GOODS_SKU_INFO_INSERT("goodsSkuInfoServiceImpl", "insertGoodsSkuInfo", "保存GoodsSkuInfo"),
    GOODS_SKU_INFO_DELETE("goodsSkuInfoServiceImpl", "deleteGoodsSkuInfo", "删除GoodsSkuInfo"),
    GOODS_SKU_INFO_UPDATE("goodsSkuInfoServiceImpl", "updateGoodsSkuInfo", "修改GoodsSkuInfo"),
    GOODS_SKU_INFO_GET("goodsSkuInfoServiceImpl", "getGoodsSkuInfo", "查询GoodsSkuInfo"),


    /**
     * @Description 商品SKU图片接口
     * @Author taoye
     */
    GOODS_SKU_PICTURE_INSERT("goodsSkuPictureServiceImpl", "insertGoodsSkuPicture", "保存GoodsSkuPicture"),
    GOODS_SKU_PICTURE_DELETE("goodsSkuPictureServiceImpl", "deleteGoodsSkuPicture", "删除GoodsSkuPicture"),
    GOODS_SKU_PICTURE_UPDATE("goodsSkuPictureServiceImpl", "updateGoodsSkuPicture", "修改GoodsSkuPicture"),
    GOODS_SKU_PICTURE_GET("goodsSkuPictureServiceImpl", "getGoodsSkuPicture", "查询GoodsSkuPicture"),


    /**
     * @Description 商品SKU价格接口
     * @Author taoye
     */
    GOODS_SKU_PRICE_INSERT("goodsSkuPriceServiceImpl", "insertGoodsSkuPrice", "保存GoodsSkuPrice"),
    GOODS_SKU_PRICE_DELETE("goodsSkuPriceServiceImpl", "deleteGoodsSkuPrice", "删除GoodsSkuPrice"),
    GOODS_SKU_PRICE_UPDATE("goodsSkuPriceServiceImpl", "updateGoodsSkuPrice", "修改GoodsSkuPrice"),
    GOODS_SKU_PRICE_GET("goodsSkuPriceServiceImpl", "getGoodsSkuPrice", "查询GoodsSkuPrice"),


    /**
     * @Description 管易店铺信息接口
     * @Author taoye
     */
    GY_SHOP_INFO_INSERT("gyShopInfoServiceImpl", "insertGyShopInfo", "保存GyShopInfo"),
    GY_SHOP_INFO_DELETE("gyShopInfoServiceImpl", "deleteGyShopInfo", "删除GyShopInfo"),
    GY_SHOP_INFO_UPDATE("gyShopInfoServiceImpl", "updateGyShopInfo", "修改GyShopInfo"),
    GY_SHOP_INFO_GET("gyShopInfoServiceImpl", "getGyShopInfo", "查询GyShopInfo"),


    /**
     * @Description 格子柜B端用户接口
     * @Author taoye
     */
    GZG_B_USER_INSERT("gzgBUserServiceImpl", "insertGzgBUser", "保存GzgBUser"),
    GZG_B_USER_DELETE("gzgBUserServiceImpl", "deleteGzgBUser", "删除GzgBUser"),
    GZG_B_USER_UPDATE("gzgBUserServiceImpl", "updateGzgBUser", "修改GzgBUser"),
    GZG_B_USER_GET("gzgBUserServiceImpl", "getGzgBUser", "查询GzgBUser"),


    /**
     * @Description 格子柜补货员关系接口
     * @Author taoye
     */
    GZG_B_USER_RELATION_INSERT("gzgBUserRelationServiceImpl", "insertGzgBUserRelation", "保存GzgBUserRelation"),
    GZG_B_USER_RELATION_DELETE("gzgBUserRelationServiceImpl", "deleteGzgBUserRelation", "删除GzgBUserRelation"),
    GZG_B_USER_RELATION_UPDATE("gzgBUserRelationServiceImpl", "updateGzgBUserRelation", "修改GzgBUserRelation"),
    GZG_B_USER_RELATION_GET("gzgBUserRelationServiceImpl", "getGzgBUserRelation", "查询GzgBUserRelation"),


    /**
     * @Description
     * @Author taoye
     */
    GZG_BANNER_PICTURE_INSERT("gzgBannerPictureServiceImpl", "insertGzgBannerPicture", "保存GzgBannerPicture"),
    GZG_BANNER_PICTURE_DELETE("gzgBannerPictureServiceImpl", "deleteGzgBannerPicture", "删除GzgBannerPicture"),
    GZG_BANNER_PICTURE_UPDATE("gzgBannerPictureServiceImpl", "updateGzgBannerPicture", "修改GzgBannerPicture"),
    GZG_BANNER_PICTURE_GET("gzgBannerPictureServiceImpl", "getGzgBannerPicture", "查询GzgBannerPicture"),


    /**
     * @Description 格子柜通道接口
     * @Author taoye
     */
    GZG_CABINET_INSERT("gzgCabinetServiceImpl", "insertGzgCabinet", "保存GzgCabinet"),
    GZG_CABINET_DELETE("gzgCabinetServiceImpl", "deleteGzgCabinet", "删除GzgCabinet"),
    GZG_CABINET_UPDATE("gzgCabinetServiceImpl", "updateGzgCabinet", "修改GzgCabinet"),
    GZG_CABINET_GET("gzgCabinetServiceImpl", "getGzgCabinet", "查询GzgCabinet"),


    /**
     * @Description 格子柜格子货物接口
     * @Author taoye
     */
    GZG_GOODS_PLAN_INSERT("gzgGoodsPlanServiceImpl", "insertGzgGoodsPlan", "保存GzgGoodsPlan"),
    GZG_GOODS_PLAN_DELETE("gzgGoodsPlanServiceImpl", "deleteGzgGoodsPlan", "删除GzgGoodsPlan"),
    GZG_GOODS_PLAN_UPDATE("gzgGoodsPlanServiceImpl", "updateGzgGoodsPlan", "修改GzgGoodsPlan"),
    GZG_GOODS_PLAN_GET("gzgGoodsPlanServiceImpl", "getGzgGoodsPlan", "查询GzgGoodsPlan"),


    /**
     * @Description 格子柜渠道商品code接口
     * @Author taoye
     */
    GZG_CHANNEL_CODE_INSERT("gzgChannelCodeServiceImpl", "insertGzgChannelCode", "保存GzgChannelCode"),
    GZG_CHANNEL_CODE_DELETE("gzgChannelCodeServiceImpl", "deleteGzgChannelCode", "删除GzgChannelCode"),
    GZG_CHANNEL_CODE_UPDATE("gzgChannelCodeServiceImpl", "updateGzgChannelCode", "修改GzgChannelCode"),
    GZG_CHANNEL_CODE_GET("gzgChannelCodeServiceImpl", "getGzgChannelCode", "查询GzgChannelCode"),


    /**
     * @Description 格子柜所在城市接口
     * @Author taoye
     */
    GZG_CITY_INSERT("gzgCityServiceImpl", "insertGzgCity", "保存GzgCity"),
    GZG_CITY_DELETE("gzgCityServiceImpl", "deleteGzgCity", "删除GzgCity"),
    GZG_CITY_UPDATE("gzgCityServiceImpl", "updateGzgCity", "修改GzgCity"),
    GZG_CITY_GET("gzgCityServiceImpl", "getGzgCity", "查询GzgCity"),


    /**
     * @Description 格子柜优惠券码接口
     * @Author taoye
     */
    GZG_COUPON_CODE_INSERT("gzgCouponCodeServiceImpl", "insertGzgCouponCode", "保存GzgCouponCode"),
    GZG_COUPON_CODE_DELETE("gzgCouponCodeServiceImpl", "deleteGzgCouponCode", "删除GzgCouponCode"),
    GZG_COUPON_CODE_UPDATE("gzgCouponCodeServiceImpl", "updateGzgCouponCode", "修改GzgCouponCode"),
    GZG_COUPON_CODE_GET("gzgCouponCodeServiceImpl", "getGzgCouponCode", "查询GzgCouponCode"),
    GZG_COUPON_CODE_QUERY_NOT_USED("gzgCouponCodeServiceImpl","getGzgCouponCodeNotUsed", " 查询格子柜用户领取未使用的优惠券"),


    /**
     * @Description 格子柜优惠券对应商品接口
     * @Author taoye
     */
    GZG_COUPON_GOODS_INSERT("gzgCouponGoodsServiceImpl", "insertGzgCouponGoods", "保存GzgCouponGoods"),
    GZG_COUPON_GOODS_DELETE("gzgCouponGoodsServiceImpl", "deleteGzgCouponGoods", "删除GzgCouponGoods"),
    GZG_COUPON_GOODS_UPDATE("gzgCouponGoodsServiceImpl", "updateGzgCouponGoods", "修改GzgCouponGoods"),
    GZG_COUPON_GOODS_GET("gzgCouponGoodsServiceImpl", "getGzgCouponGoods", "查询GzgCouponGoods"),


    /**
     * @Description 格子柜优惠券接口
     * @Author taoye
     */
    GZG_COUPON_INFO_INSERT("gzgCouponInfoServiceImpl", "insertGzgCouponInfo", "保存GzgCouponInfo"),
    GZG_COUPON_INFO_DELETE("gzgCouponInfoServiceImpl", "deleteGzgCouponInfo", "删除GzgCouponInfo"),
    GZG_COUPON_INFO_UPDATE("gzgCouponInfoServiceImpl", "updateGzgCouponInfo", "修改GzgCouponInfo"),
    GZG_COUPON_INFO_GET("gzgCouponInfoServiceImpl", "getGzgCouponInfo", "查询GzgCouponInfo"),


    /**
     * @Description 格子柜
     * @Author taoye
     */
    GZG_DEVICE_CHECK_LOG_INSERT("gzgDeviceCheckLogServiceImpl", "insertGzgDeviceCheckLog", "保存GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_DELETE("gzgDeviceCheckLogServiceImpl", "deleteGzgDeviceCheckLog", "删除GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_UPDATE("gzgDeviceCheckLogServiceImpl", "updateGzgDeviceCheckLog", "修改GzgDeviceCheckLog"),
    GZG_DEVICE_CHECK_LOG_GET("gzgDeviceCheckLogServiceImpl", "getGzgDeviceCheckLog", "查询GzgDeviceCheckLog"),


    /**
     * @Description 格子柜酒店信息接口
     * @Author taoye
     */
    GZG_HOTEL_INSERT("gzgHotelServiceImpl", "insertGzgHotel", "保存GzgHotel"),
    GZG_HOTEL_DELETE("gzgHotelServiceImpl", "deleteGzgHotel", "删除GzgHotel"),
    GZG_HOTEL_UPDATE("gzgHotelServiceImpl", "updateGzgHotel", "修改GzgHotel"),
    GZG_HOTEL_GET("gzgHotelServiceImpl", "getGzgHotel", "查询GzgHotel"),


    /**
     * @Description 格子柜酒店库存接口
     * @Author taoye
     */
    GZG_HOTEL_STOCK_INSERT("gzgHotelStockServiceImpl", "insertGzgHotelStock", "保存GzgHotelStock"),
    GZG_HOTEL_STOCK_DELETE("gzgHotelStockServiceImpl", "deleteGzgHotelStock", "删除GzgHotelStock"),
    GZG_HOTEL_STOCK_UPDATE("gzgHotelStockServiceImpl", "updateGzgHotelStock", "修改GzgHotelStock"),
    GZG_HOTEL_STOCK_GET("gzgHotelStockServiceImpl", "getGzgHotelStock", "查询GzgHotelStock"),


    /**
     * @Description 格子柜用户酒店关系接口
     * @Author taoye
     */
    GZG_HOTEL_USER_RELATION_INSERT("gzgHotelUserRelationServiceImpl", "insertGzgHotelUserRelation", "保存GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_DELETE("gzgHotelUserRelationServiceImpl", "deleteGzgHotelUserRelation", "删除GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_UPDATE("gzgHotelUserRelationServiceImpl", "updateGzgHotelUserRelation", "修改GzgHotelUserRelation"),
    GZG_HOTEL_USER_RELATION_GET("gzgHotelUserRelationServiceImpl", "getGzgHotelUserRelation", "查询GzgHotelUserRelation"),


    /**
     * @Description 格子柜订单商品接口
     * @Author taoye
     */
    GZG_ORDER_ITEMS_INSERT("gzgOrderItemsServiceImpl", "insertGzgOrderItems", "保存GzgOrderItems"),
    GZG_ORDER_ITEMS_DELETE("gzgOrderItemsServiceImpl", "deleteGzgOrderItems", "删除GzgOrderItems"),
    GZG_ORDER_ITEMS_UPDATE("gzgOrderItemsServiceImpl", "updateGzgOrderItems", "修改GzgOrderItems"),
    GZG_ORDER_ITEMS_GET("gzgOrderItemsServiceImpl", "getGzgOrderItems", "查询GzgOrderItems"),


    /**
     * @Description 格子柜
     * @Author taoye
     */
    GZG_ORDER_PAY_INSERT("gzgOrderPayServiceImpl", "insertGzgOrderPay", "保存GzgOrderPay"),
    GZG_ORDER_PAY_DELETE("gzgOrderPayServiceImpl", "deleteGzgOrderPay", "删除GzgOrderPay"),
    GZG_ORDER_PAY_UPDATE("gzgOrderPayServiceImpl", "updateGzgOrderPay", "修改GzgOrderPay"),
    GZG_ORDER_PAY_GET("gzgOrderPayServiceImpl", "getGzgOrderPay", "查询GzgOrderPay"),


    /**
     * @Description 格子柜订单接口
     * @Author taoye
     */
    GZG_ORDERS_INSERT("gzgOrdersServiceImpl", "insertGzgOrders", "保存GzgOrders"),
    GZG_ORDERS_DELETE("gzgOrdersServiceImpl", "deleteGzgOrders", "删除GzgOrders"),
    GZG_ORDERS_UPDATE("gzgOrdersServiceImpl", "updateGzgOrders", "修改GzgOrders"),
    GZG_ORDERS_GET("gzgOrdersServiceImpl", "getGzgOrders", "查询GzgOrders"),


    /**
     * @Description 格子柜补货订单接口
     * @Author taoye
     */
    GZG_REPLENISH_ORDER_INSERT("gzgReplenishOrderServiceImpl", "insertGzgReplenishOrder", "保存GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_DELETE("gzgReplenishOrderServiceImpl", "deleteGzgReplenishOrder", "删除GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_UPDATE("gzgReplenishOrderServiceImpl", "updateGzgReplenishOrder", "修改GzgReplenishOrder"),
    GZG_REPLENISH_ORDER_GET("gzgReplenishOrderServiceImpl", "getGzgReplenishOrder", "查询GzgReplenishOrder"),


    /**
     * @Description 格子柜补货记录接口
     * @Author taoye
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
    LODE_RUNNER_CODES_GET("lodeRunnerCodesServiceImpl", "getLodeRunnerCodes", "查询LodeRunnerCodes"),


    /**
     * @Description 淘金人配置接口
     * @Author taoye
     */
    LODE_RUNNER_CONFIGS_INSERT("lodeRunnerConfigsServiceImpl", "insertLodeRunnerConfigs", "保存LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_DELETE("lodeRunnerConfigsServiceImpl", "deleteLodeRunnerConfigs", "删除LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_UPDATE("lodeRunnerConfigsServiceImpl", "updateLodeRunnerConfigs", "修改LodeRunnerConfigs"),
    LODE_RUNNER_CONFIGS_GET("lodeRunnerConfigsServiceImpl", "getLodeRunnerConfigs", "查询LodeRunnerConfigs"),


    /**
     * @Description 淘金人树接口
     * @Author taoye
     */
    LODE_RUNNER_TREES_INSERT("lodeRunnerTreesServiceImpl", "insertLodeRunnerTrees", "保存LodeRunnerTrees"),
    LODE_RUNNER_TREES_DELETE("lodeRunnerTreesServiceImpl", "deleteLodeRunnerTrees", "删除LodeRunnerTrees"),
    LODE_RUNNER_TREES_UPDATE("lodeRunnerTreesServiceImpl", "updateLodeRunnerTrees", "修改LodeRunnerTrees"),
    LODE_RUNNER_TREES_GET("lodeRunnerTreesServiceImpl", "getLodeRunnerTrees", "查询LodeRunnerTrees"),


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
    SHOP_ADDRESS_DELETE("shopAddressServiceImpl", "deleteShopAddress", "删除ShopAddress"),
    SHOP_ADDRESS_UPDATE("shopAddressServiceImpl", "updateShopAddress", "修改ShopAddress"),
    SHOP_ADDRESS_GET("shopAddressServiceImpl", "getShopAddress", "查询ShopAddress"),


    /**
     * @Description 店铺优惠券信息接口
     * @Author taoye
     */
    SHOP_COUPON_INSERT("shopCouponServiceImpl", "insertShopCoupon", "保存ShopCoupon"),
    SHOP_COUPON_DELETE("shopCouponServiceImpl", "deleteShopCoupon", "删除ShopCoupon"),
    SHOP_COUPON_UPDATE("shopCouponServiceImpl", "updateShopCoupon", "修改ShopCoupon"),
    SHOP_COUPON_GET("shopCouponServiceImpl", "getShopCoupon", "查询ShopCoupon"),


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
    SHOP_INFO_GET("shopInfoServiceImpl", "getShopInfo", "查询ShopInfo"),


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
    SHOP_PURACHASES_LADDER_PRICE_LIST("shopPurachasesLadderPriceServiceImpl", "getLadderList", "查询ShopPurachasesLadderPrice集合"),

    /**
     * @Description 商家进货订单接口
     * @Author taoye
     */
    SHOP_PURCHASES_INSERT("shopPurchasesServiceImpl", "insertShopPurchases", "保存ShopPurchases"),
    SHOP_PURCHASES_DELETE("shopPurchasesServiceImpl", "deleteShopPurchases", "删除ShopPurchases"),
    SHOP_PURCHASES_UPDATE("shopPurchasesServiceImpl", "updateShopPurchases", "修改ShopPurchases"),
    SHOP_PURCHASES_GET("shopPurchasesServiceImpl", "getShopPurchases", "查询ShopPurchases"),


    /**
     * @Description 商家进货优惠接口
     * @Author taoye
     */
    SHOP_PURCHASES_DISCOUNT_INSERT("shopPurchasesDiscountServiceImpl", "insertShopPurchasesDiscount", "保存ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_DELETE("shopPurchasesDiscountServiceImpl", "deleteShopPurchasesDiscount", "删除ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_UPDATE("shopPurchasesDiscountServiceImpl", "updateShopPurchasesDiscount", "修改ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_GET("shopPurchasesDiscountServiceImpl", "getShopPurchasesDiscount", "查询ShopPurchasesDiscount"),
    SHOP_PURCHASES_DISCOUNT_SHOP_DISCOUNT("shopPurchasesDiscountServiceImpl", "getShopTotalDiscount", "查询商家累计优惠金额"),


    /**
     * @Description 进货商品接口
     * @Author taoye
     */
    SHOP_PURCHASES_ITEMS_INSERT("shopPurchasesItemsServiceImpl", "insertShopPurchasesItems", "保存ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_DELETE("shopPurchasesItemsServiceImpl", "deleteShopPurchasesItems", "删除ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_UPDATE("shopPurchasesItemsServiceImpl", "updateShopPurchasesItems", "修改ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_GET("shopPurchasesItemsServiceImpl", "getShopPurchasesItems", "查询ShopPurchasesItems"),
    SHOP_PURCHASES_ITEMS_LIST_GET("shopPurchasesItemsServiceImpl", "getItemList", "获取item列表"),


    /**
     * @Description 商家进货退货接口
     * @Author taoye
     */
    SHOP_PURCHASES_REFUND_INSERT("shopPurchasesRefundServiceImpl", "insertShopPurchasesRefund", "保存ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_DELETE("shopPurchasesRefundServiceImpl", "deleteShopPurchasesRefund", "删除ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_UPDATE("shopPurchasesRefundServiceImpl", "updateShopPurchasesRefund", "修改ShopPurchasesRefund"),
    SHOP_PURCHASES_REFUND_GET("shopPurchasesRefundServiceImpl", "getShopPurchasesRefund", "查询ShopPurchasesRefund"),


    /**
     * @Description 商家进货退货详情接口
     * @Author taoye
     */
    SHOP_PURCHASES_REFUND_ITEM_INSERT("shopPurchasesRefundItemServiceImpl", "insertShopPurchasesRefundItem", "保存ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_DELETE("shopPurchasesRefundItemServiceImpl", "deleteShopPurchasesRefundItem", "删除ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_UPDATE("shopPurchasesRefundItemServiceImpl", "updateShopPurchasesRefundItem", "修改ShopPurchasesRefundItem"),
    SHOP_PURCHASES_REFUND_ITEM_GET("shopPurchasesRefundItemServiceImpl", "getShopPurchasesRefundItem", "查询ShopPurchasesRefundItem"),


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
     * @Description 订单接口
     * @Author taoye
     */
    TRADE_ORDERS_INSERT("tradeOrdersServiceImpl", "insertTradeOrders", "保存TradeOrders"),
    TRADE_ORDERS_DELETE("tradeOrdersServiceImpl", "deleteTradeOrders", "删除TradeOrders"),
    TRADE_ORDERS_UPDATE("tradeOrdersServiceImpl", "updateTradeOrders", "修改TradeOrders"),
    TRADE_ORDERS_GET("tradeOrdersServiceImpl", "getTradeOrders", "查询TradeOrders"),

    TRADE_ORDER_LIST("tradeOrdersServiceImpl", "queryOrderListByUserId", "订单管理-查询用户订单列表接口"),

    ORDER_GET_ORDER("tradeOrdersServiceImpl", "getTradeOrders", "查询订单信息"),
    ORDER_UPDATE_ORDER("tradeOrdersServiceImpl", "updateTradeOrders", "查询订单信息"),
    ORDER_LIST_ORDER("tradeOrdersServiceImpl", "listTradeOrders", "查询订单信息集合"),


    TRADE_ORDERS_MEDIA_INSERT("tradeOrderMediaServiceImpl", "insertTradeOrderMeida", "保存TradeOrderMedia"),

    /**
     * @Description 用户信息接口
     * @Author taoye
     */
    USER_INSERT("userServiceImpl", "insertUser", "保存User"),
    USER_DELETE("userServiceImpl", "deleteUser", "删除User"),
    USER_UPDATE("userServiceImpl", "updateUser", "更新User"),
    USER_GET_USER_BY_ID("userServiceImpl", "getUserById", "根据id查询User"),
    USER_GET_USER_BY_MOBILE("userServiceImpl", "getUserByMobile", "根据mobile查询User"),
    USER_UPDATE_BY_ID("userServiceImpl", "updateUserById", "根据id更新User"),
    USER_GET_USER_BY_LOGIN_NAME("userServiceImpl", "getUserByLoginName", "根据login_name查询User"),


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


    /**
     * @Description 一小时达城市投票接口
     * @Author taoye
     */
    VOTE_CITY_INSERT("voteCityServiceImpl", "insertVoteCity", "保存VoteCity"),
    VOTE_CITY_DELETE("voteCityServiceImpl", "deleteVoteCity", "删除VoteCity"),
    VOTE_CITY_UPDATE("voteCityServiceImpl", "updateVoteCity", "修改VoteCity"),
    VOTE_CITY_GET("voteCityServiceImpl", "getVoteCity", "查询VoteCity"),
    HOME_VOTE_CITY_HOUR_FIND("voteCityServiceImpl","find","到家c一小时达投票查询"),
    HOME_VOTE_CITY_HOUR_INSERT("voteCityServiceImpl","insert","到家c一小时达头票插入"),
    HOME_VOTE_CITY_HOUR_FINDCOUNT("voteCityServiceImpl","findCount","到家c一小时达区域投票数查询"),


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
    BLUETOOTH_LOG_COUNT("bluetoothLogServiceImpl", "countBluetoothLog", "根据条件统计抽烟数据"),
    BLUETOOTH_LOG_COUNT_HOUR("bluetoothLogServiceImpl", "countHourBluetoothLog", "统计每小时抽烟数据"),
    BLUETOOTH_LOG_COUNT_DAY("bluetoothLogServiceImpl", "countDayBluetoothLog", "统计每天抽烟数据"),

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
    BLUETOOTH_USER_BIND_UPDATE_BY_CONDTIONS("bluetoothUserBindServiceImpl", "updateBluetoothUserBindByCondtion", "根据条件更新烟杆"),


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
    GOODS_SPU_QUERY_SELLER_BY_ID("goodsSpuInfoServiceImpl","getSpuGoodsSellerNumberEachMonth","统计商品月销量接口"),
    GOODS_SPU_PICTURE_BY_SPUID("goodsSpuPictureServiceImpl", "getGoodsSpuPictureBySpuId", "根据商品spu id，查询商品图片数据 -查询接口"),
    GOODS_SPU_ATTR_BY_SPUID("goodsSpuAttrServiceImpl", "getGoodsSpuAttrBySpuId", "根据商品spu id，查询商品属性数据 -查询接口"),
    GOODS_SPU_ATTR_VALUE_BY_SPUID("goodsSpuAttrValueServiceImpl", "getGoodsSpuAttrValueBySpuId", "根据商品spu id，查询商品属性值数据 -查询接口"),
    GOODS_SPU_HOUR_ATTR_VALUE_BY_SPUID("goodsSpuAttrServiceImpl", "getGoodsHourSpuAttrValueBySpuId", "根据商品spu id，查询一小时达商品对应sku商品属性值数据 -查询接口"),
    GOODS_SKU_BY_SPUID_ATTR("goodsSkuInfoServiceImpl", "getGoodsSkuInfoBySpuIdAndAttr", "根据spuId和attrbutes，查询商品sku -查询接口"),
    GOODS_SKU_BY_SPUID("goodsSkuInfoServiceImpl", "getGoodsSkuInfoBySpuId", "根据spuId，查询商品sku -查询接口"),
    GZG_BANNER_PICTURE_QUERY("gzgBannerPictureServiceImpl", "getGzgBannerPicture", "查询格子柜首页滚动图片地址"),




    /**
     * @Description: 数据中心--赚钱
     */
    PROFITS_USER_PROFITS_DETAILS("profitsUserProfitsServiceImpl", "getProfitsDetails", "查询赚钱详情"),
    PROFITS_USER_TASK_NOT_FINISH("profitsUserTaskServiceImpl", "getNotFinishTaskByUId", "没有完成的任务"),
    PROFITS_USER_PROFIT_LOGS("profitsUserProfitLogsServiceImpl", "getLogs", "获取赚钱记录日志"),
    PROFITS_USER_PROFIT_LOGS_FRIEDNS_ORDER_RANK("profitsUserProfitLogsServiceImpl", "getFriednsOrderRank", "获取好友下单赚钱排行"),




    /**
     * @Description 数据中心——交易接口
     */
    PAYMENT_INSERT_PAYMENT_DETAIL("paymentDetailServiceImpl", "insertPaymentDetail", "保存交易流水");



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