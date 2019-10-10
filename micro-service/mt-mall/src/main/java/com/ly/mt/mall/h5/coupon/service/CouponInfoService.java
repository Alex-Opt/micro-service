package com.ly.mt.mall.h5.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

/**
 * 优惠券业务层
 */
public interface CouponInfoService {

    /**
     * 用户领取优惠券
     *
     * @param couponId
     * @return
     * @throws Exception
     */
    public ResponseJson saveCouponCode(String couponId) throws Exception;

    /**
     * 批量领取优惠券接口处理层
     * @param couponIds 优惠券id的集合
     * @param userId 用户id
     * @param times 单张优惠券允许被每个用户领取的次数限制
     * @return
     * @throws Exception
     */
    ResponseJson batchDrawCoupons(String couponIds,String userId,String times) throws Exception;


    /**
     * 用户兑换 兑换码
     * @param couponCode
     * @return
     * @throws Exception
     */
    public ResponseJson redemptionCode(String couponCode) throws Exception;

    /**
     * 根据兑换码兑换全品类优惠券接口，兑换码逻辑是一个码可以使用多次，但限制每个人只能兑换一次
     * @param couponCode
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseJson reuseRedemptionCode(String couponCode,String userId) throws Exception;

    /**
     * 用户查询优惠券接口
     * @param
     * @return
     * @throws Exception
     */
    public ResponseJson getUserCouponList() throws Exception;

    /**
     * 为新用户生成一批新人优惠券接口
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseJson createNewCustomerCoupons(String userId) throws Exception;

    /**
     * 用户领取优惠券大礼包
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseJson receiveNewCustomerCoupons(String userId) throws Exception;


    /**
     * 创建一张优惠券接口
     * @param json
     * @return
     * @throws Exception
     */
    ResponseJson createCouponInfo(String json) throws Exception;

    /**
     * 查询用户领取大礼包的优惠券查询接口
     * @return
     * @throws Exception
     */
    ResponseJson getCustomerCouponsSpree() throws Exception;


    /**
     * 首次分享成功，生成优惠券
     * @return
     * @throws Exception
     */
    ResponseJson getShareCoupon(String skuId) throws  Exception;

}
