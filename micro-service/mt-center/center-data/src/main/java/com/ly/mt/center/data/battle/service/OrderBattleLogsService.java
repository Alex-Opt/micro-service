package com.ly.mt.center.data.battle.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OrderBattleLogsService {
    /**
     * @Description 保存OrderBattleLogs
     * @Author taoye
     */
    ResponseJson insertOrderBattleLogs(JSONObject jsonObject);

    /**
     * @Description 删除OrderBattleLogs
     * @Author taoye
     */
    ResponseJson deleteOrderBattleLogs(JSONObject jsonObject);

    /**
     * @Description 更新OrderBattleLogs
     * @Author taoye
     */
    ResponseJson updateOrderBattleLogs(JSONObject jsonObject);

    /**
     * @Description 查询OrderBattleLogs
     * @Author taoye
     */
    ResponseJson getOrderBattleLogs(JSONObject jsonObject);
}