package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserTaskFinishedService {
    /**
     * @Description 保存UserTaskFinished
     * @Author taoye
     */
    ResponseJson insertUserTaskFinished(JSONObject jsonObject);

    /**
     * @Description 删除UserTaskFinished
     * @Author taoye
     */
    ResponseJson deleteUserTaskFinished(JSONObject jsonObject);

    /**
     * @Description 更新UserTaskFinished
     * @Author taoye
     */
    ResponseJson updateUserTaskFinished(JSONObject jsonObject);

    /**
     * @Description 查询UserTaskFinished
     * @Author taoye
     */
    ResponseJson getUserTaskFinished(JSONObject jsonObject);
}