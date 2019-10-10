package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSkuPriceService {
    /**
     * @Description 保存GoodsSkuPrice
     * @Author taoye
     */
    ResponseJson insertGoodsSkuPrice(JSONObject jsonObject);

    /**
     * @Description 删除GoodsSkuPrice
     * @Author taoye
     */
    ResponseJson deleteGoodsSkuPrice(JSONObject jsonObject);

    /**
     * @Description 更新GoodsSkuPrice
     * @Author taoye
     */
    ResponseJson updateGoodsSkuPrice(JSONObject jsonObject);

    /**
     * @Description 查询GoodsSkuPrice
     * @Author taoye
     */
    ResponseJson getGoodsSkuPrice(JSONObject jsonObject);
}