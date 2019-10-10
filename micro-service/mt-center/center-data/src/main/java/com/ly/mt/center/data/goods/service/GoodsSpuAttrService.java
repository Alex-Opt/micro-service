package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSpuAttrService {
    /**
     * @Description 插入GoodsSpuAttr
     * @Author taoye
     */
    ResponseJson insertGoodsSpuAttr(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSpuAttr
     * @Author taoye
     */
    ResponseJson deleteGoodsSpuAttrById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSpuAttr
     * @Author taoye
     */
    ResponseJson updateGoodsSpuAttrById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSpuAttr
     * @Author taoye
     */
    ResponseJson getGoodsSpuAttr(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSpuAttr
     * @Author taoye
     */
    ResponseJson getGoodsSpuAttrById(JSONObject jsonObject);

    /**
     * 根据spu id查询商品属性数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuAttrBySpuId(JSONObject jsonObject);

    /**
     * 根据一小时达的商品spu对应sku对应的属性数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsHourSpuAttrValueBySpuId(JSONObject jsonObject);
}