package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopShareLogService {
    /**
     * @Description 保存ShopShareLog
     * @Author taoye
     */
    ResponseJson insertShopShareLog(JSONObject jsonObject);

    /**
     * @Description 删除ShopShareLog
     * @Author taoye
     */
    ResponseJson deleteShopShareLog(JSONObject jsonObject);

    /**
     * @Description 更新ShopShareLog
     * @Author taoye
     */
    ResponseJson updateShopShareLog(JSONObject jsonObject);

    /**
     * @Description 查询ShopShareLog
     * @Author taoye
     */
    ResponseJson getShopShareLog(JSONObject jsonObject);
}