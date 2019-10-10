package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserRealsService {
    /**
     * @Description 保存UserReals
     * @Author taoye
     */
    ResponseJson insertUserReals(JSONObject jsonObject);

    /**
     * @Description 删除UserReals
     * @Author taoye
     */
    ResponseJson deleteUserReals(JSONObject jsonObject);

    /**
     * @Description 更新UserReals
     * @Author taoye
     */
    ResponseJson updateUserReals(JSONObject jsonObject);

    /**
     * @Description 查询UserReals
     * @Author taoye
     */
    ResponseJson getUserReals(JSONObject jsonObject);
}