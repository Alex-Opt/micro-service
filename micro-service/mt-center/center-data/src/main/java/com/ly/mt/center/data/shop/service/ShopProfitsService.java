package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopProfitsService {
    /**
     * @Description 保存ShopProfits
     * @Author taoye
     */
    ResponseJson insertShopProfits(JSONObject jsonObject);

    /**
     * @Description 删除ShopProfits
     * @Author taoye
     */
    ResponseJson deleteShopProfits(JSONObject jsonObject);

    /**
     * @Description 更新ShopProfits
     * @Author taoye
     */
    ResponseJson updateShopProfits(JSONObject jsonObject);

    /**
     * @Description 查询ShopProfits
     * @Author taoye
     */
    ResponseJson getShopProfits(JSONObject jsonObject);
}