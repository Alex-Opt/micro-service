package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 奖励明细记录
 */
public interface CabinetRewardRecordService {
    /**
     * 新增一条奖励明细信息
     * @param jsonObject
     * @return
     */
    ResponseJson insert(JSONObject jsonObject);

    /**
     * 根据StoreId查询奖励明细记录
     * @param jsonObject
     * @return
     */
    ResponseJson queryCabinetRewardRecordByStoreId(JSONObject jsonObject);

    /**
     * 根据id查询奖励明细记录
     * @param jsonObject
     * @return
     */
    ResponseJson queryCabinetRewardRecordById(JSONObject jsonObject);

    /**
     * 根据id更新奖励明细记录结算状态
     * @param jsonObject
     * @return
     */
    ResponseJson updateCabinetRewardRecordById(JSONObject jsonObject);

    /**
     * 根据create_time,查询以前的未结算明细记录
     * @param jsonObject
     * @return
     */
    ResponseJson queryCabinetRewardRecordByCreateTime(JSONObject jsonObject);
    /**
     * 修改奖励明细记录结算状态
     * @param jsonObject
     * @return
     */
    ResponseJson updateCabinetRewardRecordByIdArray(JSONObject jsonObject);
}
