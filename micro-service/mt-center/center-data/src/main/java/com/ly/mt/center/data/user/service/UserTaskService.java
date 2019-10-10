package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserTaskService {
    /**
     * @Description 保存UserTask
     * @Author taoye
     */
    ResponseJson insertUserTask(JSONObject jsonObject);

    /**
     * @Description 删除UserTask
     * @Author taoye
     */
    ResponseJson deleteUserTask(JSONObject jsonObject);

    /**
     * @Description 更新UserTask
     * @Author taoye
     */
    ResponseJson updateUserTask(JSONObject jsonObject);

    /**
     * @Description 查询UserTask
     * @Author taoye
     */
    ResponseJson getUserTask(JSONObject jsonObject);
}