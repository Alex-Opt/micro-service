package com.ly.mt.order.server.user.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserProfitLogsService {
    int addBatchUserProfitLogs(List<JSONObject> jsonObjectList);
}
