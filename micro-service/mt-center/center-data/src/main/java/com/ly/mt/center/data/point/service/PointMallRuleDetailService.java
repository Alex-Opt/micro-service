package com.ly.mt.center.data.point.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PointMallRuleDetailService {
    /**
     * @Description 保存PointMallRuleDetail
     * @Author taoye
     */
    ResponseJson insertPointMallRuleDetail(JSONObject jsonObject);

    /**
     * @Description 删除PointMallRuleDetail
     * @Author taoye
     */
    ResponseJson deletePointMallRuleDetail(JSONObject jsonObject);

    /**
     * @Description 更新PointMallRuleDetail
     * @Author taoye
     */
    ResponseJson updatePointMallRuleDetail(JSONObject jsonObject);

    /**
     * @Description 查询PointMallRuleDetail
     * @Author taoye
     */
    ResponseJson getPointMallRuleDetail(JSONObject jsonObject);
}