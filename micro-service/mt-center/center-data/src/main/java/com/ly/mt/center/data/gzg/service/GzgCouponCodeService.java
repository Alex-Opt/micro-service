package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgCouponCodeService {
    /**
     * @Description 插入GzgCouponCode
     * @Author taoye
     */
    ResponseJson insertGzgCouponCode(JSONObject jsonObject);

    /**
     * @Description 根据id删除GzgCouponCode
     * @Author taoye
     */
    ResponseJson deleteGzgCouponCodeById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GzgCouponCode
     * @Author taoye
     */
    ResponseJson updateGzgCouponCodeById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GzgCouponCode
     * @Author taoye
     */
    ResponseJson getGzgCouponCode(JSONObject jsonObject);


    /**
     * @Description 根据条件查询GzgCouponCode
     * @Author taoye
     */
    ResponseJson getGzgCouponCodeNotUsed(JSONObject jsonObject);

    /**
     * @Description 根据id查询GzgCouponCode
     * @Author taoye
     */
    ResponseJson getGzgCouponCodeById(JSONObject jsonObject);

    /**
     * 查询某个优惠券使用情况
     * @param jsonObject
     * @return
     */
    ResponseJson getGzgCouponCodeUsedInfo(JSONObject jsonObject);


    /**
     * 通过用户id获取该用户所有优惠券信息
     * @param jsonObject
     * @return
     */
    ResponseJson selectAllCouponByUserId(JSONObject jsonObject);


    /**
     * 更新优惠券使用状态
     * @param jsonObject
     * @return
     */
    ResponseJson updateGzgCouponUseStatus(JSONObject jsonObject);


}