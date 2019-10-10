package com.ly.mt.blue.tooth.log.service;

import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

/**
 * 蓝牙日志service操作接口
 */
public interface BluetoothLogService {
    /**
     * 获取烟弹日志数据
     * @throws Exception
     */
    ResponseJson getBuluetoothTasteLog(String macAddress) throws Exception;
    /**
     * 保存蓝牙日志数据--(MQ生产者)
     * @throws Exception
     */
    ResponseJson saveBlueToothLog(BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo) throws Exception;

    /**
     * 统计蓝牙日志数据
     * @throws Exception
     */
    ResponseJson countBlueToothLog(String type,String macAddress,String startTime,String endTime) throws Exception;

}