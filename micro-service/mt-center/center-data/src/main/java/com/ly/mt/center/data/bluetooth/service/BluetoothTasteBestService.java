package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothTasteBest;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

public interface BluetoothTasteBestService {
    /**
     * @Description 插入BluetoothTasteBest
     * @Author WHL
     */
    ResponseJson insertBluetoothTasteBest(JSONObject jsonObject);

    /**
     * @Description 根据id删除BluetoothTasteBest
     * @Author WHL
     */
    ResponseJson deleteBluetoothTasteBestById(JSONObject jsonObject);

    /**
     * @Description 根据id更新BluetoothTasteBest
     * @Author WHL
     */
    ResponseJson updateBluetoothTasteBestById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询BluetoothTasteBest
     * @Author WHL
     */
    ResponseJson getBluetoothTasteBest(JSONObject jsonObject);

    /**
     * @Description 根据id查询BluetoothTasteBest
     * @Author WHL
     */
    ResponseJson getBluetoothTasteBestById(JSONObject jsonObject);
    /*
     * @Description 根据烟弹id 获取烟弹最佳口感
     * @Author WHL
     */
    ResponseJson getBluetoothTasteBestByTastId(JSONObject jsonObject);
}