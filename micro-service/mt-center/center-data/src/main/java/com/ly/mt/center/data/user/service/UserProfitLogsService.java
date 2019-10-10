package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserProfitLogsService {
    /**
     * @Description 保存UserProfitLogs
     * @Author taoye
     */
    ResponseJson insertUserProfitLogs(JSONObject jsonObject);

    /**
     * @Description 删除UserProfitLogs
     * @Author taoye
     */
    ResponseJson deleteUserProfitLogs(JSONObject jsonObject);

    /**
     * @Description 更新UserProfitLogs
     * @Author taoye
     */
    ResponseJson updateUserProfitLogs(JSONObject jsonObject);

    /**
     * @Description 查询UserProfitLogs
     * @Author taoye
     */
    ResponseJson getUserProfitLogs(JSONObject jsonObject);
}