package com.ly.mt.center.data.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CouponCodeService {
    /**
     * @Description 保存CouponCode
     * @Author taoye
     */
    ResponseJson insertCouponCode(JSONObject jsonObject);

    /**
     * @Description 删除CouponCode
     * @Author taoye
     */
    ResponseJson deleteCouponCode(JSONObject jsonObject);

    /**
     * @Description 更新CouponCode
     * @Author taoye
     */
    ResponseJson updateCouponCode(JSONObject jsonObject);

    /**
     * @Description 查询CouponCode
     * @Author taoye
     */
    ResponseJson getCouponCode(JSONObject jsonObject);

    /**
     * 用户兑换 兑换码
     *
     * @return
     */
    ResponseJson updateCouponCodeByCode(JSONObject jsonObject);

    /**
     * 根据用户id,优惠券id查询用户是否领取该优惠券
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getUserCouponByUserIdAndCouponIdList(JSONObject jsonObject);

    /**
     * 更新用户领取优惠券大礼包的数据信息到coupon_code
     *
     * @param jsonObject
     * @return
     */
    ResponseJson updateCouponCodeUserInfo(JSONObject jsonObject);

    /**
     * 根据用户id,couponIds查询用户领取的对应优惠券的信息
     *
     * @param jsonObject
     * @return
     */
    ResponseJson batchQueryCouponCode(JSONObject jsonObject);

    /**
     * 根据用户id,兑换码code查询用户是否领取该优惠券
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getUserCouponByUserIdAndCouponCode(JSONObject jsonObject);

    /**
     * 根据兑换码查询出一个尚未兑换的兑换码数据。
     * @param jsonObject
     * @return
     */
    ResponseJson getOneRepeatCouponCode(JSONObject jsonObject);

    /**
     * 到家b查询couponCode
     * @param jsonObject
     * @return
     */
    ResponseJson getCouponCodeForShop(JSONObject jsonObject);
}