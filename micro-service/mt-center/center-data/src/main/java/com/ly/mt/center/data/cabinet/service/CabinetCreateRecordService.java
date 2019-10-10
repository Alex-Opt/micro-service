package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜
 */
public interface CabinetCreateRecordService {

    /**
     *  添加格子柜上下架记录
     * @return
     */
    ResponseJson addCabinetCreateRecord(JSONObject jsonObject);


}
