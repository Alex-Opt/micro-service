package com.ly.mt.center.data.point.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PointMallRuleService {
    /**
     * @Description 保存PointMallRule
     * @Author taoye
     */
    ResponseJson insertPointMallRule(JSONObject jsonObject);

    /**
     * @Description 删除PointMallRule
     * @Author taoye
     */
    ResponseJson deletePointMallRule(JSONObject jsonObject);

    /**
     * @Description 更新PointMallRule
     * @Author taoye
     */
    ResponseJson updatePointMallRule(JSONObject jsonObject);

    /**
     * @Description 查询PointMallRule
     * @Author taoye
     */
    ResponseJson getPointMallRule(JSONObject jsonObject);
}