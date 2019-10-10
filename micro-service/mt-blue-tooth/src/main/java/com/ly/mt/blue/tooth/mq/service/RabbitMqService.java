package com.ly.mt.blue.tooth.mq.service;

import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;

/**
 * mq消费业务处理层
 * @author whl
 */
public interface RabbitMqService {
    /**
     * 批量插入蓝牙数据入库
     * @param bluetoothLogMqVo 蓝牙抽烟数据对象
     * @return
     * @throws Exception
     */
    void handlerBlueToothLogMq(BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo) throws Exception;
}
