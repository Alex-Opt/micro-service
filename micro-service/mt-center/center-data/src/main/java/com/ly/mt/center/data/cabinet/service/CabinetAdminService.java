package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜B店铺管理员
 */
public interface CabinetAdminService {


    /**
     * 店铺管理员的格子柜
     * @param jsonObject
     * @return
     */
    ResponseJson findCabinetImeis(JSONObject jsonObject);


    /**
     * 店铺管理员订单
     * @param jsonObject
     * @return
     */
    ResponseJson findConditionsOrders(JSONObject jsonObject);


}
