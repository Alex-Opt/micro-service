package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothFirmwareSettingService {

    /**
     * @Description 根据条件查询BluetoothFirmwareSetting
     * @Author wanghongliang
     */
    ResponseJson getBluetoothFirmwareSetting(JSONObject jsonObject);
    
}