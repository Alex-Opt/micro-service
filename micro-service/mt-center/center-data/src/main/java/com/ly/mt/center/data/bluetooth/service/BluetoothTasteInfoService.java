package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.stereotype.Service;

/**
 * 烟弹信息service
 */
public interface BluetoothTasteInfoService {

    /**
     * @Description 获取烟弹列表
     * @Author wanghongliang
     */
    ResponseJson getBluetoothTasteList(JSONObject jsonObject);

}