package com.ly.mt.order.server.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.coupon.CouponInfo;

import java.util.List;

/**
 * 优惠券业务层
 */
public interface CouponInfoService {

    /**
     * 用户领取优惠券
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject saveCouponCode(String json) throws Exception;

    /**
     * 根据优惠券id集合查询当前时间有效的优惠券
     * @param ids
     * @return
     */
    public List<CouponInfo> getValidCouponListByIds(List<String> ids) ;

    /**
     * 根据优惠券id集合查询表coupon_code,联表coupon_info在当前时间有效的优惠券
     * @param ids
     * @param userId
     * @return
     */
    public List<CouponInfo> getValidCouponListFromCouponCodeByIds(List<String> ids,Long userId) ;

    /**
     * 生成一批优惠券兑换码接口
     * @return
     * @throws Exception
     */
    public List<String> generateRedemptionCode() throws Exception;

    /**
     * 用户兑换 兑换码
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject redemptionCode(String json) throws Exception;

    /**
     * 用户查询优惠券接口
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject getUserCouponList(String json) throws Exception;

    /**
     * 获取当前时间有效的通用优惠券
     * @return
     * @throws Exception
     */
    public List<String> getCouponIdsByLimitType(String nowDate);
}
