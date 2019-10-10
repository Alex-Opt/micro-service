package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdShopAttDetailService {
    /**
     * @Description 保存HdShopAttDetail
     * @Author taoye
     */
    ResponseJson insertHdShopAttDetail(JSONObject jsonObject);

    /**
     * @Description 删除HdShopAttDetail
     * @Author taoye
     */
    ResponseJson deleteHdShopAttDetail(JSONObject jsonObject);

    /**
     * @Description 更新HdShopAttDetail
     * @Author taoye
     */
    ResponseJson updateHdShopAttDetail(JSONObject jsonObject);

    /**
     * @Description 查询HdShopAttDetail
     * @Author taoye
     */
    ResponseJson getHdShopAttDetail(JSONObject jsonObject);
}