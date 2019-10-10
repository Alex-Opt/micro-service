package com.ly.mt.center.data.point.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PointConfigService {
    /**
     * @Description 插入PointConfig
     * @Author taoye
     */
    ResponseJson insertPointConfig(JSONObject jsonObject);

    /**
     * @Description 根据id删除PointConfig
     * @Author taoye
     */
    ResponseJson deletePointConfigById(JSONObject jsonObject);

    /**
     * @Description 根据id更新PointConfig
     * @Author taoye
     */
    ResponseJson updatePointConfigById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询PointConfig
     * @Author taoye
     */
    ResponseJson getPointConfig(JSONObject jsonObject);

    /**
     * @Description 根据id查询PointConfig
     * @Author taoye
     */
    ResponseJson getPointConfigById(JSONObject jsonObject);
}