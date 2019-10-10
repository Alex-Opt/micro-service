package com.ly.mt.center.data.activity.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ActivityUserGradeDetailService {
    /**
     * @Description 保存ActivityUserGradeDetail
     * @Author taoye
     */
    ResponseJson insertActivityUserGradeDetail(JSONObject jsonObject);

    /**
     * @Description 删除ActivityUserGradeDetail
     * @Author taoye
     */
    ResponseJson deleteActivityUserGradeDetail(JSONObject jsonObject);

    /**
     * @Description 更新ActivityUserGradeDetail
     * @Author taoye
     */
    ResponseJson updateActivityUserGradeDetail(JSONObject jsonObject);

    /**
     * @Description 查询ActivityUserGradeDetail
     * @Author taoye
     */
    ResponseJson getActivityUserGradeDetail(JSONObject jsonObject);
}