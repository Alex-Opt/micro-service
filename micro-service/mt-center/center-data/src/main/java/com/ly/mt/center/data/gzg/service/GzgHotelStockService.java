package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgHotelStockService {
    /**
     * @Description 保存GzgHotelStock
     * @Author taoye
     */
    ResponseJson insertGzgHotelStock(JSONObject jsonObject);

    /**
     * @Description 删除GzgHotelStock
     * @Author taoye
     */
    ResponseJson deleteGzgHotelStock(JSONObject jsonObject);

    /**
     * @Description 更新GzgHotelStock
     * @Author taoye
     */
    ResponseJson updateGzgHotelStock(JSONObject jsonObject);

    /**
     * @Description 查询GzgHotelStock
     * @Author taoye
     */
    ResponseJson getGzgHotelStock(JSONObject jsonObject);
}