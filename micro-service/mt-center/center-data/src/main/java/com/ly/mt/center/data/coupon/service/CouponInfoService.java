package com.ly.mt.center.data.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CouponInfoService {
    /**
     * @Description 保存CouponInfo
     * @Author taoye
     */
    ResponseJson insertCouponInfo(JSONObject jsonObject);

    /**
     * @Description 删除CouponInfo
     * @Author taoye
     */
    ResponseJson deleteCouponInfo(JSONObject jsonObject);

    /**
     * @Description 更新CouponInfo
     * @Author taoye
     */
    ResponseJson updateCouponInfo(JSONObject jsonObject);

    /**
     * @Description 查询CouponInfo
     * @Author taoye
     */
    ResponseJson getCouponInfo(JSONObject jsonObject);

    /**
     * 根据spuId查询优惠券CouponInfo数据
     * @param jsonObject
     * @return
     */
    ResponseJson getCouponInfoBySpuId(JSONObject jsonObject);

    /**
     * 根据userId查询优惠券CouponInfo数据
     * @param jsonObject
     * @return
     */
    ResponseJson getCouponInfoByUserId(JSONObject jsonObject);

    /**
     * 查询新人优惠券集合
     * @param jsonObject
     * @return
     */
    ResponseJson getNewUserCoupons(JSONObject jsonObject);

    /**
     * 查询新人优惠券大礼包-手动领取类型
     * @param jsonObject
     * @return
     */
    ResponseJson getNewUserCouponsSpree(JSONObject jsonObject);

    /**
     * 查询商家优惠券
     * @param jsonObject
     * @return
     */
    ResponseJson getCouponInfoByUserIdForShop(JSONObject jsonObject);

}