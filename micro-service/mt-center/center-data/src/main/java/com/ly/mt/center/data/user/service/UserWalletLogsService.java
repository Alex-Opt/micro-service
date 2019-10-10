package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserWalletLogsService {
    /**
     * @Description 保存UserWalletLogs
     * @Author taoye
     */
    ResponseJson insertUserWalletLogs(JSONObject jsonObject);

    /**
     * @Description 删除UserWalletLogs
     * @Author taoye
     */
    ResponseJson deleteUserWalletLogs(JSONObject jsonObject);

    /**
     * @Description 更新UserWalletLogs
     * @Author taoye
     */
    ResponseJson updateUserWalletLogs(JSONObject jsonObject);

    /**
     * @Description 查询UserWalletLogs
     * @Author taoye
     */
    ResponseJson getUserWalletLogs(JSONObject jsonObject);
}