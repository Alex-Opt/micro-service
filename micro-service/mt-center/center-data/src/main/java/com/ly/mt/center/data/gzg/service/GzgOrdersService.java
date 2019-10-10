package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrdersService {
    /**
     * @Description 保存GzgOrders
     * @Author taoye
     */
    ResponseJson insertGzgOrders(JSONObject jsonObject);

    /**
     * @Description 删除GzgOrders
     * @Author taoye
     */
    ResponseJson deleteGzgOrders(JSONObject jsonObject);

    /**
     * @Description 更新GzgOrders
     * @Author taoye
     */
    ResponseJson updateGzgOrders(JSONObject jsonObject);

    /**
     * @Description 查询GzgOrders
     * @Author taoye
     */
    ResponseJson getGzgOrders(JSONObject jsonObject);

    ResponseJson getGzgOrdersById(JSONObject jsonObject);

    /**
     * 根据buyer_id查询该用户所有订单信息
     * @param jsonObject
     * @return
     */
    ResponseJson getGzgOrdersList(JSONObject jsonObject);


    /**
     * 根据imei查询店家分成比例和店铺id
     * @param jsonObject
     * @return
     */
    ResponseJson getGzgOrderDivideScale(JSONObject jsonObject);
}