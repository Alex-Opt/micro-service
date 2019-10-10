package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrderItemsService {
    /**
     * @Description 插入GzgOrderItems
     * @Author taoye
     */
    ResponseJson insertGzgOrderItems(JSONObject jsonObject);

    /**
     * @Description 根据id删除GzgOrderItems
     * @Author taoye
     */
    ResponseJson deleteGzgOrderItemsById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GzgOrderItems
     * @Author taoye
     */
    ResponseJson updateGzgOrderItemsById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GzgOrderItems
     * @Author taoye
     */
    ResponseJson getGzgOrderItems(JSONObject jsonObject);

    /**
     * @Description 根据id查询GzgOrderItems
     * @Author taoye
     */
    ResponseJson getGzgOrderItemsByOrderId(JSONObject jsonObject);


    /**
     * @Description 根据skuid查询获取卖出数量
     * @Author taoye
     */
    ResponseJson getSellNumBySkuId(JSONObject jsonObject);


    ResponseJson updateGzgOrderItemsByOrderId(JSONObject jsonObject);
}