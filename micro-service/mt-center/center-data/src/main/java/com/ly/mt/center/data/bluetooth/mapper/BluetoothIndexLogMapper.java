package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothIndexLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothIndexLogMapper {
    /**
     * @Description 插入BluetoothIndexLog
     * @Author wanghongliang
     */
    void insertBluetoothIndexLog(BluetoothIndexLog bluetoothIndexLog);

    /**
     * @Description 根据id删除BluetoothIndexLog
     * @Author wanghongliang
     */
    void deleteBluetoothIndexLogById(BluetoothIndexLog bluetoothIndexLog);

    /**
     * @Description 根据id更新BluetoothIndexLog
     * @Author wanghongliang
     */
    int updateBluetoothIndexLogById(BluetoothIndexLog bluetoothIndexLog);

    /**
     * @Description 根据条件查询BluetoothIndexLog
     * @Author wanghongliang
     */
    BluetoothIndexLog getBluetoothIndexLog(BluetoothIndexLog bluetoothIndexLog);

    /**
     * @Description 根据id查询BluetoothIndexLog
     * @Author wanghongliang
     */
    BluetoothIndexLog getBluetoothIndexLogById(BluetoothIndexLog bluetoothIndexLog);
}