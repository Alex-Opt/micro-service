package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothUserTaste;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothUserTasteMapper {
    /**
     * @Description 插入BluetoothUserTaste
     * @Author wanghongliang
     */
    void insertBluetoothUserTaste(BluetoothUserTaste bluetoothUserTaste);

    /**
     * @Description 根据id删除BluetoothUserTaste
     * @Author wanghongliang
     */
    void deleteBluetoothUserTasteById(BluetoothUserTaste bluetoothUserTaste);

    /**
     * @Description 根据id更新BluetoothUserTaste
     * @Author wanghongliang
     */
    int updateBluetoothUserTasteById(BluetoothUserTaste bluetoothUserTaste);

    /**
     * @Description 根据条件查询BluetoothUserTaste
     * @Author wanghongliang
     */
    BluetoothUserTaste getBluetoothUserTaste(BluetoothUserTaste bluetoothUserTaste);

    /**
     * @Description 根据id查询BluetoothUserTaste
     * @Author wanghongliang
     */
    BluetoothUserTaste getBluetoothUserTasteById(BluetoothUserTaste bluetoothUserTaste);
}