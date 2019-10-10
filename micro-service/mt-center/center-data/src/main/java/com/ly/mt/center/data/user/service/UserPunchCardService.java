package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserPunchCardService {
    /**
     * @Description 插入UserPunchCard
     * @Author taoye
     */
    ResponseJson insertUserPunchCard(JSONObject jsonObject);

    /**
     * @Description 根据id删除UserPunchCard
     * @Author taoye
     */
    ResponseJson deleteUserPunchCardById(JSONObject jsonObject);

    /**
     * @Description 根据id更新UserPunchCard
     * @Author taoye
     */
    ResponseJson updateUserPunchCardById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询UserPunchCard
     * @Author taoye
     */
    ResponseJson getUserPunchCard(JSONObject jsonObject);

    /**
     * @Description 根据id查询UserPunchCard
     * @Author taoye
     */
    ResponseJson getUserPunchCardById(JSONObject jsonObject);
}