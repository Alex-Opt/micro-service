package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopRefundImagesService {
    /**
     * @Description 保存ShopRefundImages
     * @Author taoye
     */
    ResponseJson insertShopRefundImages(JSONObject jsonObject);

    /**
     * @Description 删除ShopRefundImages
     * @Author taoye
     */
    ResponseJson deleteShopRefundImages(JSONObject jsonObject);

    /**
     * @Description 更新ShopRefundImages
     * @Author taoye
     */
    ResponseJson updateShopRefundImages(JSONObject jsonObject);

    /**
     * @Description 查询ShopRefundImages
     * @Author taoye
     */
    ResponseJson getShopRefundImages(JSONObject jsonObject);
}