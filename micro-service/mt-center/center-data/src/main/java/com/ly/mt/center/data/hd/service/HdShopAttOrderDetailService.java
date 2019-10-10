package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdShopAttOrderDetailService {
    /**
     * @Description 保存HdShopAttOrderDetail
     * @Author taoye
     */
    ResponseJson insertHdShopAttOrderDetail(JSONObject jsonObject);

    /**
     * @Description 删除HdShopAttOrderDetail
     * @Author taoye
     */
    ResponseJson deleteHdShopAttOrderDetail(JSONObject jsonObject);

    /**
     * @Description 更新HdShopAttOrderDetail
     * @Author taoye
     */
    ResponseJson updateHdShopAttOrderDetail(JSONObject jsonObject);

    /**
     * @Description 查询HdShopAttOrderDetail
     * @Author taoye
     */
    ResponseJson getHdShopAttOrderDetail(JSONObject jsonObject);
}