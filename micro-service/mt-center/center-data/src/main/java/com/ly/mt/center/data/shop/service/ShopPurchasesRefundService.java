package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesRefundService {
    /**
     * @Description 保存ShopPurchasesRefund
     * @Author taoye
     */
    ResponseJson insertShopPurchasesRefund(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurchasesRefund
     * @Author taoye
     */
    ResponseJson deleteShopPurchasesRefund(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurchasesRefund
     * @Author taoye
     */
    ResponseJson updateShopPurchasesRefund(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchasesRefund
     * @Author taoye
     */
    ResponseJson getShopPurchasesRefund(JSONObject jsonObject);
}