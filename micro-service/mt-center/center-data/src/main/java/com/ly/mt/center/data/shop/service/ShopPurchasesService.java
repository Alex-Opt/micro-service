package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesService {
    /**
     * @Description 保存ShopPurchases
     * @Author taoye
     */
    ResponseJson insertShopPurchases(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurchases
     * @Author taoye
     */
    ResponseJson deleteShopPurchases(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurchases
     * @Author taoye
     */
    ResponseJson updateShopPurchases(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchases
     * @Author taoye
     */
    ResponseJson getShopPurchases(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchasesList
     * @Author taoye
     */
    ResponseJson getShopPurchasesList(JSONObject jsonObject);
}