package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsAttrValueService {
    /**
     * @Description 保存GoodsAttrValue
     * @Author taoye
     */
    ResponseJson insertGoodsAttrValue(JSONObject jsonObject);

    /**
     * @Description 删除GoodsAttrValue
     * @Author taoye
     */
    ResponseJson deleteGoodsAttrValue(JSONObject jsonObject);

    /**
     * @Description 更新GoodsAttrValue
     * @Author taoye
     */
    ResponseJson updateGoodsAttrValue(JSONObject jsonObject);

    /**
     * @Description 查询GoodsAttrValue
     * @Author taoye
     */
    ResponseJson getGoodsAttrValue(JSONObject jsonObject);

    /**
     *根据属性值id查询属性值和属性
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsAttrByValueId(JSONObject jsonObject);
}