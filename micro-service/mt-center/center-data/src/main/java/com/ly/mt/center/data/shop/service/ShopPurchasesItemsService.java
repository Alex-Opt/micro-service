package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesItemsService {
    /**
     * @Description 保存ShopPurchasesItems
     * @Author taoye
     */
    ResponseJson insertShopPurchasesItems(JSONObject jsonObject);

    /**
     * @Description 删除ShopPurchasesItems
     * @Author taoye
     */
    ResponseJson deleteShopPurchasesItems(JSONObject jsonObject);

    /**
     * @Description 更新ShopPurchasesItems
     * @Author taoye
     */
    ResponseJson updateShopPurchasesItems(JSONObject jsonObject);

    /**
     * @Description 查询ShopPurchasesItems
     * @Author taoye
     */
    ResponseJson getShopPurchasesItems(JSONObject jsonObject);

    /**
     * 通过订单Id获取
     * @param jsonParameter
     * @return
     */
    ResponseJson getItemList(JSONObject jsonParameter);

    /**
     * 获取商家进货总数
     * @param jsonObject
     * @return
     */
    ResponseJson getShopItemNumByShopId(JSONObject jsonObject);


    /**
     * 获取spu销量
     * @param jsonObject
     * @return
     */
    ResponseJson getShopItemNumBySpu(JSONObject jsonObject);
}