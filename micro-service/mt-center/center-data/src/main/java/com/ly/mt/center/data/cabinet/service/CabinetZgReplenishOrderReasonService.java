package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 补货理由数据中心层
 * @author wanghongliang
 * @date 2019-09-18
 */
public interface CabinetZgReplenishOrderReasonService {
    /**
     * 批量插入展柜补货单理由
     * @param jsonObject
     * @return
     */
    ResponseJson insertBatch(JSONObject jsonObject);

    /**
     * 查询展柜补货订单理由
     * @param
     * @return
     */
    ResponseJson getCabinetZgReplenishReasonByCondtion(JSONObject jsonObject);

    /**
     * 通过补货订单理由id更新
     * @param
     * @return
     */
    ResponseJson updateCabinetZgReplenishReasonById(JSONObject jsonObject);
}
