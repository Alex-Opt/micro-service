package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopStocksService {
    /**
     * @Description 保存ShopStocks
     * @Author taoye
     */
    ResponseJson insertShopStocks(JSONObject jsonObject);

    /**
     * @Description 删除ShopStocks
     * @Author taoye
     */
    ResponseJson deleteShopStocks(JSONObject jsonObject);

    /**
     * @Description 更新ShopStocks
     * @Author taoye
     */
    ResponseJson updateShopStocks(JSONObject jsonObject);

    /**
     * @Description 查询ShopStocks
     * @Author taoye
     */
    ResponseJson getShopStocks(JSONObject jsonObject);
}