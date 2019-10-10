package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothTokenService {
    /**
     * @Description 插入用户token
     * @Author whl
     */
    ResponseJson insertBluetoothToken(JSONObject jsonObject);

    /**
     * @Description 获取用户token
     * @Author whl
     */
    ResponseJson getBluetoothToken(JSONObject jsonObject);

    /**
     * @Description 更新用户token
     * @Author whl
     */
    ResponseJson updateBluetoothToken(JSONObject jsonObject);

}