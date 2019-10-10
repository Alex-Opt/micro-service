package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothIndexLogService {
    /**
     * @Description 插入BluetoothIndexLog
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothIndexLog(JSONObject jsonObject);

    /**
     * @Description 根据id删除BluetoothIndexLog
     * @Author wanghongliang
     */
    ResponseJson deleteBluetoothIndexLogById(JSONObject jsonObject);

    /**
     * @Description 根据id更新BluetoothIndexLog
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothIndexLogById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询BluetoothIndexLog
     * @Author wanghongliang
     */
    ResponseJson getBluetoothIndexLog(JSONObject jsonObject);

    /**
     * @Description 根据id查询BluetoothIndexLog
     * @Author wanghongliang
     */
    ResponseJson getBluetoothIndexLogById(JSONObject jsonObject);
}