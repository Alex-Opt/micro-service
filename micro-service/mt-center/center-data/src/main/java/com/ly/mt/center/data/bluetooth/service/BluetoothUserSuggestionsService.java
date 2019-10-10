package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothUserSuggestionsService {
    /**
     * @Description 插入BluetoothUserSuggestions
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothUserSuggestions(JSONObject jsonObject);

}