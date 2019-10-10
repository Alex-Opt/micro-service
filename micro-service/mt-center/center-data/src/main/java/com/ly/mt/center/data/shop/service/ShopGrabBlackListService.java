package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopGrabBlackListService {
    /**
     * @Description 保存ShopGrabBlackList
     * @Author taoye
     */
    ResponseJson insertShopGrabBlackList(JSONObject jsonObject);

    /**
     * @Description 删除ShopGrabBlackList
     * @Author taoye
     */
    ResponseJson deleteShopGrabBlackList(JSONObject jsonObject);

    /**
     * @Description 更新ShopGrabBlackList
     * @Author taoye
     */
    ResponseJson updateShopGrabBlackList(JSONObject jsonObject);

    /**
     * @Description 查询ShopGrabBlackList
     * @Author taoye
     */
    ResponseJson getShopGrabBlackList(JSONObject jsonObject);
}