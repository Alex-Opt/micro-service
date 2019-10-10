package com.ly.mt.center.data.punch.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PunchCardPointService {
    /**
     * @Description 插入PunchCardPoint
     * @Author taoye
     */
    ResponseJson insertPunchCardPoint(JSONObject jsonObject);

    /**
     * @Description 根据id删除PunchCardPoint
     * @Author taoye
     */
    ResponseJson deletePunchCardPointById(JSONObject jsonObject);

    /**
     * @Description 根据id更新PunchCardPoint
     * @Author taoye
     */
    ResponseJson updatePunchCardPointById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询PunchCardPoint
     * @Author taoye
     */
    ResponseJson getPunchCardPoint(JSONObject jsonObject);

    /**
     * @Description 根据id查询PunchCardPoint
     * @Author taoye
     */
    ResponseJson getPunchCardPointById(JSONObject jsonObject);

    /**
     * //查找打卡是否有积分
     * @param jsonObject
     * @return
     */
    ResponseJson getPunchCardPointByStatus(JSONObject jsonObject);
}