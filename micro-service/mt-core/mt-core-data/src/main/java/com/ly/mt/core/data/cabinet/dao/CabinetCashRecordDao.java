package com.ly.mt.core.data.cabinet.dao;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;

import java.util.List;

public interface CabinetCashRecordDao {

    /**
     * 根据id修改申请记录
     * @param jsonObject
     * @return
     */
    ResponseJson updateById(JSONObject jsonObject);

    /**
     * 根据状态status 查询申请数据
     * @param jsonObject
     * @return
     */
    List<CabinetCashRecord> getRecordByStatus(JSONObject jsonObject);
}
