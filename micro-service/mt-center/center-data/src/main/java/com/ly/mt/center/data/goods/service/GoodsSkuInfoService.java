package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSkuInfoService {
    /**
     * @Description 插入GoodsSkuInfo
     * @Author taoye
     */
    ResponseJson insertGoodsSkuInfo(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSkuInfo
     * @Author taoye
     */
    ResponseJson deleteGoodsSkuInfoById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSkuInfo
     * @Author taoye
     */
    ResponseJson updateGoodsSkuInfoById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSkuInfo
     * @Author taoye
     */
    ResponseJson getGoodsSkuInfo(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSkuInfo
     * @Author taoye
     */
    ResponseJson getGoodsSkuInfoById(JSONObject jsonObject);

    /**
     * 根据spuId和属性attrbutes查询sku数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSkuInfoBySpuIdAndAttr(JSONObject jsonObject);

    /**
     * 根据spuId 查询sku数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSkuInfoBySpuId(JSONObject jsonObject);

    /**
     * 展柜补货sku列表 只查询spuId
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSkuInfoBySpuIdNew(JSONObject jsonObject);
}