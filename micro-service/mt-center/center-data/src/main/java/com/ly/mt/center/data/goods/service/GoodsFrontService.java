package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsFrontService {
    /**
     * @Description 保存GoodsFront
     * @Author
     */
    ResponseJson insertGoodsFront(JSONObject jsonObject);

    /**
     * @Description 更新GoodsFront
     * @Author
     */
    ResponseJson updateGoodsFront(JSONObject jsonObject);

    /**
     * @Description 查询GoodsFront spuId（去重）
     * @Author
     */
    ResponseJson getGoodsFrontSpuId(JSONObject jsonObject);
    /**
     * @Description 查询GoodsFront
     * @Author
     */
    ResponseJson getGoodsFront(JSONObject jsonObject);
    /**
     * @Description 根据spuId查询GoodsFront
     * @Author
     */
    ResponseJson getGoodsFrontBySpuId(JSONObject jsonObject);

    /**
     * @Description 根据spuId查询sku数据
     * @Author
     */
    ResponseJson getGoodsSkuBySpuId(JSONObject jsonObject);

    /**
     * @Description 根据skuId查询sku数据
     * @Author
     */
    ResponseJson getGoodsSkuBySkuId(JSONObject jsonObject);

    /**
     * 根据APPType和actiClass 查询数据
     * @param jsonObject
     * @return
     */
    ResponseJson queryGoodsFrontByActiClass(JSONObject jsonObject);
}