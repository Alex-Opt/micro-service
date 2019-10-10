package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserBindsService {
    /**
     * @Description 保存UserBinds
     * @Author taoye
     */
    ResponseJson insertUserBinds(JSONObject jsonObject);

    /**
     * @Description 删除UserBinds
     * @Author taoye
     */
    ResponseJson deleteUserBinds(JSONObject jsonObject);

    /**
     * @Description 更新UserBinds
     * @Author taoye
     */
    ResponseJson updateUserBinds(JSONObject jsonObject);

    /**
     * @Description 查询UserBinds
     * @Author taoye
     */
    ResponseJson getUserBinds(JSONObject jsonObject);
}