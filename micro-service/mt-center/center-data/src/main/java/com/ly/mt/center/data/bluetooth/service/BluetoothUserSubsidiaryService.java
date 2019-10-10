package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserSubsidiary;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothUserSubsidiaryService {
    /**
     * @Description 插入BluetoothUserSubsidiary
     * @Author whl
     */
    ResponseJson insertBluetoothUserSubsidiary(JSONObject jsonObject);
    /**
     * @Description 根据条件查询BluetoothUserSubsidiary
     * @Author whl
     */
    ResponseJson getBluetoothUserSubsidiary(JSONObject jsonObject);

    /**
     * @Description 统计BluetoothUserSubsidiary
     * @Author whl
     */
    ResponseJson countBluetoothUserSubsidiary(JSONObject jsonObject);

    /**
     * @Description 更新BluetoothUserSubsidiary
     * @Author whl
     */
    ResponseJson updateBluetoothUserSubsidiary(JSONObject jsonObject);
}