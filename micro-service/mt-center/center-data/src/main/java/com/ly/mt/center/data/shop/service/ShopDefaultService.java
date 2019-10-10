package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopDefaultService {
    /**
     * @Description 保存ShopDefault
     * @Author taoye
     */
    ResponseJson insertShopDefault(JSONObject jsonObject);

    /**
     * @Description 删除ShopDefault
     * @Author taoye
     */
    ResponseJson deleteShopDefault(JSONObject jsonObject);

    /**
     * @Description 更新ShopDefault
     * @Author taoye
     */
    ResponseJson updateShopDefault(JSONObject jsonObject);

    /**
     * @Description 查询ShopDefault
     * @Author taoye
     */
    ResponseJson getShopDefault(JSONObject jsonObject);
}