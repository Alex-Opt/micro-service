package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothUserSubsidiary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothUserSubsidiaryMapper {
    /**
     * @Description 插入BluetoothUserSubsidiary
     * @Author wanghongliang
     */
    int insertBluetoothUserSubsidiary(BluetoothUserSubsidiary bluetoothUserSubsidiary);
    /**
     * @Description 根据条件查询BluetoothUserSubsidiary
     * @Author wanghongliang
     */
    BluetoothUserSubsidiary getBluetoothUserSubsidiary(BluetoothUserSubsidiary bluetoothUserSubsidiary);

    /**
     * @Description 统计BluetoothUserSubsidiary
     * @Author wanghongliang
     */
    int countBluetoothUserSubsidiary(BluetoothUserSubsidiary bluetoothUserSubsidiary);

    /**
     * @Description 更新BluetoothUserSubsidiary
     * @Author wanghongliang
     */
    int updateBluetoothUserSubsidiary(BluetoothUserSubsidiary bluetoothUserSubsidiary);

}