package com.ly.mt.center.data.battle.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OrderBattleExpressesService {
    /**
     * @Description 保存OrderBattleExpresses
     * @Author taoye
     */
    ResponseJson insertOrderBattleExpresses(JSONObject jsonObject);

    /**
     * @Description 删除OrderBattleExpresses
     * @Author taoye
     */
    ResponseJson deleteOrderBattleExpresses(JSONObject jsonObject);

    /**
     * @Description 更新OrderBattleExpresses
     * @Author taoye
     */
    ResponseJson updateOrderBattleExpresses(JSONObject jsonObject);

    /**
     * @Description 查询OrderBattleExpresses
     * @Author taoye
     */
    ResponseJson getOrderBattleExpresses(JSONObject jsonObject);
}