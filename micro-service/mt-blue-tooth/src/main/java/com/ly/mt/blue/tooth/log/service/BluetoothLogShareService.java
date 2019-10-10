package com.ly.mt.blue.tooth.log.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @program: my-blue-tooth
 * @description: 用户抽烟数据分享
 * @author: wanghongliang
 * @create: 2019-07-25 18:25
 **/
public interface BluetoothLogShareService {
    /**
     * 用户日抽烟数据分享
     * @throws Exception
     */
    ResponseJson shareSmokoingDataByDay(String id,String date) throws Exception;

    /**
     * 用户周抽烟数据分享
     * @throws Exception
     */
    ResponseJson shareSmokoingDataByWeek(String macAddress,String startTime,String endTime) throws Exception;

    /**
     * 用户月抽烟数据分享
     * @throws Exception
     */
    ResponseJson shareSmokoingDataByMonth(String macAddress,String startTime,String endTime) throws Exception;
}