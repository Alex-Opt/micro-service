package com.ly.mt.center.data.point.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PointGradeService {
    /**
     * @Description 插入PointGrade
     * @Author taoye
     */
    ResponseJson insertPointGrade(JSONObject jsonObject);

    /**
     * @Description 根据id删除PointGrade
     * @Author taoye
     */
    ResponseJson deletePointGradeById(JSONObject jsonObject);

    /**
     * @Description 根据id更新PointGrade
     * @Author taoye
     */
    ResponseJson updatePointGradeById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询PointGrade
     * @Author taoye
     */
    ResponseJson getPointGrade(JSONObject jsonObject);

    /**
     * @Description 根据id查询PointGrade
     * @Author taoye
     */
    ResponseJson getPointGradeById(JSONObject jsonObject);

    /**
     * 根据score查询积分等级数据
     * @param jsonObject
     * @return
     */
    ResponseJson getPointGradeByScore(JSONObject jsonObject);
}