package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesRefundItemService {
    /**
     * @Description 保存ShopPurchasesRefundItem
     * @Author taoye
     */
    ResponseJson insertShopPurchasesRefundItem(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurchasesRefundItem
     * @Author taoye
     */
    ResponseJson deleteShopPurchasesRefundItem(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurchasesRefundItem
     * @Author taoye
     */
    ResponseJson updateShopPurchasesRefundItem(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchasesRefundItem
     * @Author taoye
     */
    ResponseJson getShopPurchasesRefundItem(JSONObject jsonObject);

    /**
     * 获取商家退货总数
     * @param jsonObject
     * @return
     */
    ResponseJson getShopRefundItemNum(JSONObject jsonObject);
}