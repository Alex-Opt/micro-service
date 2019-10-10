package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsHourSkuService {


    /**
     * 根据spuId 查询sku集合数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSkuHourInfoBySkuIds(JSONObject jsonObject);

    /**
     * 根据skuid 查询goodsSkuHour集合数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsHourSkuBySkuId(JSONObject jsonObject);

}
