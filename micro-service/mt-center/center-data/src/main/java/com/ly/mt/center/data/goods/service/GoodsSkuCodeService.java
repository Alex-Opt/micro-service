package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSkuCodeService {
    /**
     * @Description 保存GoodsSkuCode
     * @Author taoye
     */
    ResponseJson insertGoodsSkuCode(JSONObject jsonObject);

    /**
     * @Description 删除GoodsSkuCode
     * @Author taoye
     */
    ResponseJson deleteGoodsSkuCode(JSONObject jsonObject);

    /**
     * @Description 更新GoodsSkuCode
     * @Author taoye
     */
    ResponseJson updateGoodsSkuCode(JSONObject jsonObject);

    /**
     * @Description 查询GoodsSkuCode
     * @Author taoye
     */
    ResponseJson getGoodsSkuCode(JSONObject jsonObject);
}