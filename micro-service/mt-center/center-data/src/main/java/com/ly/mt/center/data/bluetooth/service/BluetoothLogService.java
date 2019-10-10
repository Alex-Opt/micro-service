package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothLogService {
    /**
     * @Description 插入BluetoothLog
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothLog(JSONObject jsonObject);

    /**
     * @Description 计算每小时抽烟数据
     * @Author wanghongliang
     */
    ResponseJson countBluetoothLogSmokingDataHour(JSONObject jsonObject);

    /**
     * @Description 计算按天抽烟数据-日期连续
     * @Author wanghongliang
     */
    ResponseJson countBluetoothLogSmokingDataDay(JSONObject jsonObject);

    /**
     * @Description 计算有抽烟数据的天数-日期不连续
     * @Author wanghongliang
     */
    ResponseJson countBluetoothLogSmokingHaveDataDay(JSONObject jsonObject);

    /**
     * @Description 根据条件统计抽烟口数
     * @Author wanghongliang
     */
    ResponseJson totalBluetoothLogSmokingMonthNumber(JSONObject jsonObject);
}