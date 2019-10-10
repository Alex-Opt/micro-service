package com.ly.mt.center.data.battle.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OrdersBattleInfoService {
    /**
     * @Description 保存OrdersBattleInfo
     * @Author taoye
     */
    ResponseJson insertOrdersBattleInfo(JSONObject jsonObject);

    /**
     * @Description 删除OrdersBattleInfo
     * @Author taoye
     */
    ResponseJson deleteOrdersBattleInfo(JSONObject jsonObject);

    /**
     * @Description 更新OrdersBattleInfo
     * @Author taoye
     */
    ResponseJson updateOrdersBattleInfo(JSONObject jsonObject);

    /**
     * @Description 查询OrdersBattleInfo
     * @Author taoye
     */
    ResponseJson getOrdersBattleInfo(JSONObject jsonObject);
}