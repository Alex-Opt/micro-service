package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothUserBindService {
    /**
     * @Description 插入BluetoothUserBind
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothUserBind(JSONObject jsonObject);

    /**
     * @Description 根据id更新BluetoothUserBind
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothUserBindById(JSONObject jsonObject);

    /**
     * @Description 根据条件更新BluetoothUserBind
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothUserBindByCondtion(JSONObject jsonObject);


    /**
     * @Description 根据条件查询烟杆
     * @Author wanghongliang
     */
    ResponseJson getBluetoothUserBindByCondtions(JSONObject jsonObject);

    /**
     * @Description  根据id查询单个烟杆
     * @Author wanghongliang
     */
    ResponseJson getBluetoothUserBindById(JSONObject jsonObject);
}