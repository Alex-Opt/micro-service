package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdShopAttOrderService {
    /**
     * @Description 保存HdShopAttOrder
     * @Author taoye
     */
    ResponseJson insertHdShopAttOrder(JSONObject jsonObject);

    /**
     * @Description 删除HdShopAttOrder
     * @Author taoye
     */
    ResponseJson deleteHdShopAttOrder(JSONObject jsonObject);

    /**
     * @Description 更新HdShopAttOrder
     * @Author taoye
     */
    ResponseJson updateHdShopAttOrder(JSONObject jsonObject);

    /**
     * @Description 查询HdShopAttOrder
     * @Author taoye
     */
    ResponseJson getHdShopAttOrder(JSONObject jsonObject);
}