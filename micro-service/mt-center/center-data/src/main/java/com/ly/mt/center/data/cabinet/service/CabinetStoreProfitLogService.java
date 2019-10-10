package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

public interface CabinetStoreProfitLogService {
    /**
     * 插入
     *
     * @param jsonObject
     * @return
     */
    ResponseJson insertCabinetStoreProfitLog(JSONObject jsonObject);

    /**
     * 判断订单信息是否插入
     * @param jsonObject
     * @return
     */
     ResponseJson getCabinetStoreProfitLog(JSONObject jsonObject);


}
