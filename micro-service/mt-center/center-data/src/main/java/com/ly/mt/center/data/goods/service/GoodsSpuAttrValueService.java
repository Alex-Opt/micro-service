package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSpuAttrValueService {
    /**
     * @Description 插入GoodsSpuAttrValue
     * @Author taoye
     */
    ResponseJson insertGoodsSpuAttrValue(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSpuAttrValue
     * @Author taoye
     */
    ResponseJson deleteGoodsSpuAttrValueById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSpuAttrValue
     * @Author taoye
     */
    ResponseJson updateGoodsSpuAttrValueById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSpuAttrValue
     * @Author taoye
     */
    ResponseJson getGoodsSpuAttrValue(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSpuAttrValue
     * @Author taoye
     */
    ResponseJson getGoodsSpuAttrValueById(JSONObject jsonObject);

    /**
     * 根据spu id查询商品属性值数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuAttrValueBySpuId(JSONObject jsonObject);
    /**
     * 根据spu id查询商品所有属性值数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuAllAttrValueBySpuId(JSONObject jsonObject);
}