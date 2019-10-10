package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopLicensesService {
    /**
     * @Description 保存ShopLicenses
     * @Author taoye
     */
    ResponseJson insertShopLicenses(JSONObject jsonObject);

    /**
     * @Description 删除ShopLicenses
     * @Author taoye
     */
    ResponseJson deleteShopLicenses(JSONObject jsonObject);

    /**
     * @Description 更新ShopLicenses
     * @Author taoye
     */
    ResponseJson updateShopLicenses(JSONObject jsonObject);

    /**
     * @Description 查询ShopLicenses
     * @Author taoye
     */
    ResponseJson getShopLicenses(JSONObject jsonObject);
}