package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopInfoService {
    /**
     * @Description 插入ShopInfo
     * @Author taoye
     */
    ResponseJson insertShopInfo(JSONObject jsonObject);

    /**
     * @Description 根据id删除ShopInfo
     * @Author taoye
     */
    ResponseJson deleteShopInfoById(JSONObject jsonObject);

    /**
     * @Description 根据id更新ShopInfo
     * @Author taoye
     */
    ResponseJson updateShopInfoById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询ShopInfo
     * @Author taoye
     */
    ResponseJson getShopInfo(JSONObject jsonObject);

    /**
     * @Description 根据id查询ShopInfo
     * @Author taoye
     */
    ResponseJson getShopInfoById(JSONObject jsonObject);

    /**
     * 根据电话号码查询ShopInfo
     * @param jsonObject
     * @return
     */
    ResponseJson getShopInfoByMobile(JSONObject jsonObject);

    /**
     * 根据userId查询ShopInfo
     * @param jsonObject
     * @return
     */
    ResponseJson getShopInfoByUserId (JSONObject jsonObject);
}