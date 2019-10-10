package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesDiscountService {
    /**
     * @Description 保存ShopPurchasesDiscount
     * @Author taoye
     */
    ResponseJson insertShopPurchasesDiscount(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurchasesDiscount
     * @Author taoye
     */
    ResponseJson deleteShopPurchasesDiscount(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurchasesDiscount
     * @Author taoye
     */
    ResponseJson updateShopPurchasesDiscount(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchasesDiscount
     * @Author taoye
     */
    ResponseJson getShopPurchasesDiscount(JSONObject jsonObject);

    /**
     * 查询商家累计优惠
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getShopTotalDiscount(JSONObject jsonObject);

    /**
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getShopPurchasesDiscountList(JSONObject jsonObject);
}