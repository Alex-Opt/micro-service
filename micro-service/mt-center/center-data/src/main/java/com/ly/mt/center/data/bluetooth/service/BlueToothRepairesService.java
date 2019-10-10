package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BlueToothRepairesService {
    /**
     * 插入一条报修记录
     * @param jsonObject
     * @return
     */
    ResponseJson insertBlueToothRepairs(JSONObject jsonObject);

    /**
     * 查看报修记录
     * @param jsonObject
     * @return
     */
    ResponseJson getBluetoothRepair(JSONObject jsonObject);

    /**
     * 更新报修记录
     * @param jsonObject
     * @return
     */
    ResponseJson updateBluetoothRepair(JSONObject jsonObject);
}
