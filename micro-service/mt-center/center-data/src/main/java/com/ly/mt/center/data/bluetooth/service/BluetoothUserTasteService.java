package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothUserTasteService {
    /**
     * @Description 插入BluetoothUserTaste
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothUserTaste(JSONObject jsonObject);

    /**
     * @Description 根据id删除BluetoothUserTaste
     * @Author wanghongliang
     */
    ResponseJson deleteBluetoothUserTasteById(JSONObject jsonObject);

    /**
     * @Description 根据id更新BluetoothUserTaste
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothUserTasteById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询BluetoothUserTaste
     * @Author wanghongliang
     */
    ResponseJson getBluetoothUserTaste(JSONObject jsonObject);

    /**
     * @Description 根据id查询BluetoothUserTaste
     * @Author wanghongliang
     */
    ResponseJson getBluetoothUserTasteById(JSONObject jsonObject);
}