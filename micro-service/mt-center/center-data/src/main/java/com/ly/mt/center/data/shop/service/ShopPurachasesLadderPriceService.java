package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurachasesLadderPriceService {
    /**
     * @Description 保存ShopPurachasesLadderPrice
     * @Author taoye
     */
    ResponseJson insertShopPurachasesLadderPrice(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurachasesLadderPrice
     * @Author taoye
     */
    ResponseJson deleteShopPurachasesLadderPrice(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurachasesLadderPrice
     * @Author taoye
     */
    ResponseJson updateShopPurachasesLadderPrice(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurachasesLadderPrice
     * @Author taoye
     */
    ResponseJson getShopPurachasesLadderPrice(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurachasesLadderPrice集合
     * @Author linan
     */
    ResponseJson getShopPurachasesLadderPriceList(JSONObject jsonObject);
}