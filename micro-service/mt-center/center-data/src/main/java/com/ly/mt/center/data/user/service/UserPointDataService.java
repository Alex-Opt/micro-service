package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserPointDataService {
    /**
     * @Description 插入UserPointData
     * @Author taoye
     */
    ResponseJson insertUserPointData(JSONObject jsonObject);

    /**
     * @Description 根据id删除UserPointData
     * @Author taoye
     */
    ResponseJson deleteUserPointDataById(JSONObject jsonObject);

    /**
     * @Description 根据id更新UserPointData
     * @Author taoye
     */
    ResponseJson updateUserPointDataById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询UserPointData
     * @Author taoye
     */
    ResponseJson getUserPointData(JSONObject jsonObject);

    /**
     * @Description 根据id查询UserPointData
     * @Author taoye
     */
    ResponseJson getUserPointDataById(JSONObject jsonObject);

    /**
     * 根据userId查询用户的打卡记录数据
     * @param jsonObject
     * @return
     */
    ResponseJson getUserPointDataByUserId(JSONObject jsonObject);
    /**
     * 根据userId查询用户今天的打卡记录数据
     * @param jsonObject
     * @return
     */
    ResponseJson getUserPointDataByUserIdAndToday(JSONObject jsonObject);
}