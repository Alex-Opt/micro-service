package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsAttrService {
    /**
     * @Description 保存GoodsAttr
     * @Author taoye
     */
    ResponseJson insertGoodsAttr(JSONObject jsonObject);

    /**
     * @Description 删除GoodsAttr
     * @Author taoye
     */
    ResponseJson deleteGoodsAttr(JSONObject jsonObject);

    /**
     * @Description 更新GoodsAttr
     * @Author taoye
     */
    ResponseJson updateGoodsAttr(JSONObject jsonObject);

    /**
     * @Description 查询GoodsAttr
     * @Author taoye
     */
    ResponseJson getGoodsAttr(JSONObject jsonObject);
}