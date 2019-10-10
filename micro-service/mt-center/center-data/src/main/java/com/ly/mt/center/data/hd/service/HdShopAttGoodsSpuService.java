package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdShopAttGoodsSpuService {
    /**
     * @Description 保存HdShopAttGoodsSpu
     * @Author taoye
     */
    ResponseJson insertHdShopAttGoodsSpu(JSONObject jsonObject);

    /**
     * @Description 删除HdShopAttGoodsSpu
     * @Author taoye
     */
    ResponseJson deleteHdShopAttGoodsSpu(JSONObject jsonObject);

    /**
     * @Description 更新HdShopAttGoodsSpu
     * @Author taoye
     */
    ResponseJson updateHdShopAttGoodsSpu(JSONObject jsonObject);

    /**
     * @Description 查询HdShopAttGoodsSpu
     * @Author taoye
     */
    ResponseJson getHdShopAttGoodsSpu(JSONObject jsonObject);
}