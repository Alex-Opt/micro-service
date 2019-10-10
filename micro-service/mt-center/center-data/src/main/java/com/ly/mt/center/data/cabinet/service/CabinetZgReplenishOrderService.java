package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 补货数据中心层
 * @author wanghongliang
 * @date 2019-09-18
 */
public interface CabinetZgReplenishOrderService {
    /**
     * 插入一条展柜补货单
     * @param jsonObject
     * @return
     */
    ResponseJson insertCabinetZgReplenish(JSONObject jsonObject);

    /**
     * 查询展柜补货列表
     * @param
     * @return
     */
    ResponseJson getCabinetZgReplenishOrderList(JSONObject jsonObject);

    /**
     * 通过补货订单id更新
     * @param
     * @return
     */
    ResponseJson updateCabinetZgReplenishOrderById(JSONObject jsonObject);
}
