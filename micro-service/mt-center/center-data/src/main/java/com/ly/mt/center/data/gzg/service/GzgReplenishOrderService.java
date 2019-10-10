package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgReplenishOrderService {
    /**
     * @Description 保存GzgReplenishOrder
     * @Author taoye
     */
    ResponseJson insertGzgReplenishOrder(JSONObject jsonObject);

    /**
     * @Description 删除GzgReplenishOrder
     * @Author taoye
     */
    ResponseJson deleteGzgReplenishOrder(JSONObject jsonObject);

    /**
     * @Description 更新GzgReplenishOrder
     * @Author taoye
     */
    ResponseJson updateGzgReplenishOrder(JSONObject jsonObject);

    /**
     * @Description 查询GzgReplenishOrder
     * @Author taoye
     */
    ResponseJson getGzgReplenishOrder(JSONObject jsonObject);
}