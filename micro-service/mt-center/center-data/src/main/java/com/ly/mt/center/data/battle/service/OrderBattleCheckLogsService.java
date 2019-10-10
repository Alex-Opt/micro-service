package com.ly.mt.center.data.battle.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OrderBattleCheckLogsService {
    /**
     * @Description 保存OrderBattleCheckLogs
     * @Author taoye
     */
    ResponseJson insertOrderBattleCheckLogs(JSONObject jsonObject);

    /**
     * @Description 删除OrderBattleCheckLogs
     * @Author taoye
     */
    ResponseJson deleteOrderBattleCheckLogs(JSONObject jsonObject);

    /**
     * @Description 更新OrderBattleCheckLogs
     * @Author taoye
     */
    ResponseJson updateOrderBattleCheckLogs(JSONObject jsonObject);

    /**
     * @Description 查询OrderBattleCheckLogs
     * @Author taoye
     */
    ResponseJson getOrderBattleCheckLogs(JSONObject jsonObject);
}