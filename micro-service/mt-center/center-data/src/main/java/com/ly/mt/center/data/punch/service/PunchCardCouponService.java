package com.ly.mt.center.data.punch.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PunchCardCouponService {
    /**
     * @Description 插入PunchCardCoupon
     * @Author taoye
     */
    ResponseJson insertPunchCardCoupon(JSONObject jsonObject);

    /**
     * @Description 根据id删除PunchCardCoupon
     * @Author taoye
     */
    ResponseJson deletePunchCardCouponById(JSONObject jsonObject);

    /**
     * @Description 根据id更新PunchCardCoupon
     * @Author taoye
     */
    ResponseJson updatePunchCardCouponById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询PunchCardCoupon
     * @Author taoye
     */
    ResponseJson getPunchCardCoupon(JSONObject jsonObject);

    /**
     * @Description 根据id查询PunchCardCoupon
     * @Author taoye
     */
    ResponseJson getPunchCardCouponById(JSONObject jsonObject);

    /**
     * 根据状态status查询unchCardCoupon
     * @param jsonObject
     * @return
     */
    ResponseJson getPunchCardCouponByStatus(JSONObject jsonObject);
}