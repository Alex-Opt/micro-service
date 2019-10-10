package com.ly.mt.center.data.battle.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OrderBattleShopService {
    /**
     * @Description 保存OrderBattleShop
     * @Author taoye
     */
    ResponseJson insertOrderBattleShop(JSONObject jsonObject);

    /**
     * @Description 删除OrderBattleShop
     * @Author taoye
     */
    ResponseJson deleteOrderBattleShop(JSONObject jsonObject);

    /**
     * @Description 更新OrderBattleShop
     * @Author taoye
     */
    ResponseJson updateOrderBattleShop(JSONObject jsonObject);

    /**
     * @Description 查询OrderBattleShop
     * @Author taoye
     */
    ResponseJson getOrderBattleShop(JSONObject jsonObject);
}