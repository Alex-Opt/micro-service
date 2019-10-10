package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothIndex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BluetoothIndexMapper {
    /**
     * @Description 批量插入BluetoothIndex
     * @Author wanghongliang
     */
    int insertBluetoothIndex(List<BluetoothIndex> bluetoothIndex);

    /**
     * @Description 根据id删除BluetoothIndex
     * @Author wanghongliang
     */
    int deleteBluetoothIndexByCondtion(BluetoothIndex bluetoothIndex);

    /**
     * @Description 根据id更新BluetoothIndex
     * @Author wanghongliang
     */
    int updateBluetoothIndexById(BluetoothIndex bluetoothIndex);

    /**
     * @Description 根据条件查询BluetoothIndex
     * @Author wanghongliang
     */
    List<BluetoothIndex> getBluetoothIndex(BluetoothIndex bluetoothIndex);

    /**
     * @Description 根据id查询BluetoothIndex
     * @Author wanghongliang
     */
    BluetoothIndex getBluetoothIndexById(BluetoothIndex bluetoothIndex);
}