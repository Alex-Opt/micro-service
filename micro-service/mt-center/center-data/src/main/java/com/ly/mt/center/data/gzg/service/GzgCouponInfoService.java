package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgCouponInfoService {
    /**
     * @Description 保存GzgCouponInfo
     * @Author taoye
     */
    ResponseJson insertGzgCouponInfo(JSONObject jsonObject);

    /**
     * @Description 删除GzgCouponInfo
     * @Author taoye
     */
    ResponseJson deleteGzgCouponInfo(JSONObject jsonObject);

    /**
     * @Description 更新GzgCouponInfo
     * @Author taoye
     */
    ResponseJson updateGzgCouponInfo(JSONObject jsonObject);

    /**
     * @Description 查询GzgCouponInfo
     * @Author taoye
     */
    ResponseJson getGzgCouponInfo(JSONObject jsonObject);
}