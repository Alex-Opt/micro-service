package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgPlanService {
    /**
     * @Description 插入GzgPlan
     * @Author taoye
     */
    ResponseJson insertGzgPlan(JSONObject jsonObject);

    /**
     * @Description 根据id删除GzgPlan
     * @Author taoye
     */
    ResponseJson deleteGzgPlanById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GzgPlan
     * @Author taoye
     */
    ResponseJson updateGzgPlanById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GzgPlan
     * @Author taoye
     */
    ResponseJson getGzgPlan(JSONObject jsonObject);


}