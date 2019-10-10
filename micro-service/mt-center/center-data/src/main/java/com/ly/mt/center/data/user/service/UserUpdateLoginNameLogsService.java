package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserUpdateLoginNameLogsService {
    /**
     * @Description 保存UserUpdateLoginNameLogs
     * @Author taoye
     */
    ResponseJson insertUserUpdateLoginNameLogs(JSONObject jsonObject);

    /**
     * @Description 删除UserUpdateLoginNameLogs
     * @Author taoye
     */
    ResponseJson deleteUserUpdateLoginNameLogs(JSONObject jsonObject);

    /**
     * @Description 更新UserUpdateLoginNameLogs
     * @Author taoye
     */
    ResponseJson updateUserUpdateLoginNameLogs(JSONObject jsonObject);

    /**
     * @Description 查询UserUpdateLoginNameLogs
     * @Author taoye
     */
    ResponseJson getUserUpdateLoginNameLogs(JSONObject jsonObject);
}