package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothIndexService {
    /**
     * @Description 插入BluetoothIndex
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothIndex(JSONObject jsonObject);

    /**
     * @Description 根据id删除BluetoothIndex
     * @Author wanghongliang
     */
    ResponseJson deleteBluetoothIndexByCondtion(JSONObject jsonObject);

    /**
     * @Description 根据id更新BluetoothIndex
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothIndexById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询BluetoothIndex
     * @Author wanghongliang
     */
    ResponseJson getBluetoothIndex(JSONObject jsonObject);

    /**
     * @Description 根据id查询BluetoothIndex
     * @Author wanghongliang
     */
    ResponseJson getBluetoothIndexById(JSONObject jsonObject);
}