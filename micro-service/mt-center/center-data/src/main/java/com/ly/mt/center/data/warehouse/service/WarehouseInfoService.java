package com.ly.mt.center.data.warehouse.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface WarehouseInfoService {
    /**
     * @Description 保存WarehouseInfo
     * @Author taoye
     */
    ResponseJson insertWarehouseInfo(JSONObject jsonObject);

    /**
     * @Description 删除WarehouseInfo
     * @Author taoye
     */
    ResponseJson deleteWarehouseInfo(JSONObject jsonObject);

    /**
     * @Description 更新WarehouseInfo
     * @Author taoye
     */
    ResponseJson updateWarehouseInfo(JSONObject jsonObject);

    /**
     * @Description 查询WarehouseInfo
     * @Author taoye
     */
    ResponseJson getWarehouseInfo(JSONObject jsonObject);
}