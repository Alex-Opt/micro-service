package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothCountLog;
import com.ly.mt.center.data.bluetooth.entity.BluetoothLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BluetoothLogMapper {
    /**
     * @Description 插入BluetoothLog
     * @Author whl
     */
    int insertBluetoothLog(List<BluetoothLog> bluetoothLog);

    /**
     * @Description 根据条件查询BluetoothLog
     * @Author whl
     */
    int getBluetoothLog(BluetoothLog bluetoothLog);

    /**
     * @Description 计算每小时抽烟数据
     * @Author whl
     */
    List<BluetoothCountLog> countBluetoothLogSmokingDataHour(BluetoothLog bluetoothLog);

    /**
     * @Description 计算按天抽烟数据-日期连续
     * @Author whl
     */
    List<BluetoothCountLog> countBluetoothLogSmokingDataDay(BluetoothLog bluetoothLog);

    /**
     * @Description 计算有抽烟数据的天数-日期不连续
     * @Author whl
     */
    List<BluetoothCountLog> countBluetoothLogSmokingHaveDataDay(BluetoothLog bluetoothLog);

    /**
     * @Description 累计抽烟总口数
     * @Author whl
     */
    int totalBluetoothLogSmokingMonthNumber(BluetoothLog bluetoothLog);
}