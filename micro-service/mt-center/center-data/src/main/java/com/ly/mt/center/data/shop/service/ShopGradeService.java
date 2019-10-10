package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopGradeService {
    /**
     * @Description 保存ShopGrade
     * @Author taoye
     */
    ResponseJson insertShopGrade(JSONObject jsonObject);

    /**
     * @Description 删除ShopGrade
     * @Author taoye
     */
    ResponseJson deleteShopGrade(JSONObject jsonObject);

    /**
     * @Description 更新ShopGrade
     * @Author taoye
     */
    ResponseJson updateShopGrade(JSONObject jsonObject);

    /**
     * @Description 查询ShopGrade
     * @Author taoye
     */
    ResponseJson getShopGrade(JSONObject jsonObject);
}