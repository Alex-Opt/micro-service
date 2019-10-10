package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopProfitLogsService {
    /**
     * @Description 保存ShopProfitLogs
     * @Author taoye
     */
    ResponseJson insertShopProfitLogs(JSONObject jsonObject);

    /**
     * @Description 删除ShopProfitLogs
     * @Author taoye
     */
    ResponseJson deleteShopProfitLogs(JSONObject jsonObject);

    /**
     * @Description 更新ShopProfitLogs
     * @Author taoye
     */
    ResponseJson updateShopProfitLogs(JSONObject jsonObject);

    /**
     * @Description 查询ShopProfitLogs
     * @Author taoye
     */
    ResponseJson getShopProfitLogs(JSONObject jsonObject);
}