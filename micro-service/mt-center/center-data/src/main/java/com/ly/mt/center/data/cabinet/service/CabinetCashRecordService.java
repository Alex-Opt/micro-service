package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CabinetCashRecordService {

    /**
     * 添加提现申请记录
     * @param jsonObject
     * @return
     */
    ResponseJson insert(JSONObject jsonObject);

    /**
     * 根据id修改申请记录
     * @param jsonObject
     * @return
     */
    ResponseJson updateById(JSONObject jsonObject);

    /**
     * 根据用户id查询申请数据
     * @param jsonObject
     * @return
     */
    ResponseJson selectRecordByUserId(JSONObject jsonObject);

    /**
     * 根据用户id查询今日申请数据（status=0,1,2）；当userId为空，查询今日所有成功数据
     * @param jsonObject
     * @return
     */
    ResponseJson selectRecordByUserIdToday(JSONObject jsonObject);

    /**
     * 根据状态status 查询申请数据
     * @param jsonObject
     * @return
     */
    ResponseJson selectRecordByStatus(JSONObject jsonObject);
}
