package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserPointGradeService {
    /**
     * @Description 插入UserPointGrade
     * @Author taoye
     */
    ResponseJson insertUserPointGrade(JSONObject jsonObject);

    /**
     * @Description 根据id删除UserPointGrade
     * @Author taoye
     */
    ResponseJson deleteUserPointGradeById(JSONObject jsonObject);

    /**
     * @Description 根据id更新UserPointGrade
     * @Author taoye
     */
    ResponseJson updateUserPointGradeById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询UserPointGrade
     * @Author taoye
     */
    ResponseJson getUserPointGrade(JSONObject jsonObject);

    /**
     * @Description 根据id查询UserPointGrade
     * @Author taoye
     */
    ResponseJson getUserPointGradeById(JSONObject jsonObject);

    /**
     * 根据userId查询UserPointGrade
     * @param jsonObject
     * @return
     */
    ResponseJson getUserPointGradeByUserId(JSONObject jsonObject);
}