package com.ly.mt.center.data.activity.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ActivityUserGradeService {
    /**
     * @Description 保存ActivityUserGrade
     * @Author taoye
     */
    ResponseJson insertActivityUserGrade(JSONObject jsonObject);

    /**
     * @Description 删除ActivityUserGrade
     * @Author taoye
     */
    ResponseJson deleteActivityUserGrade(JSONObject jsonObject);

    /**
     * @Description 更新ActivityUserGrade
     * @Author taoye
     */
    ResponseJson updateActivityUserGrade(JSONObject jsonObject);

    /**
     * @Description 查询ActivityUserGrade
     * @Author taoye
     */
    ResponseJson getActivityUserGrade(JSONObject jsonObject);
}