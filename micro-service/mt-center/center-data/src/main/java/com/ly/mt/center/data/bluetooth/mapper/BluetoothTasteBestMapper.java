package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothTasteBest;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserTasteInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BluetoothTasteBestMapper {
    /**
     * @Description 插入BluetoothTasteBest
     * @Author wanghongliang
     */
    void insertBluetoothTasteBest(BluetoothTasteBest bluetoothTasteBest);

    /**
     * @Description 根据id删除BluetoothTasteBest
     * @Author wanghongliang
     */
    void deleteBluetoothTasteBestById(BluetoothTasteBest bluetoothTasteBest);

    /**
     * @Description 根据id更新BluetoothTasteBest
     * @Author wanghongliang
     */
    int updateBluetoothTasteBestById(BluetoothTasteBest bluetoothTasteBest);

    /**
     * @Description 根据条件查询BluetoothTasteBest
     * @Author wanghongliang
     */
    BluetoothTasteBest getBluetoothTasteBest(BluetoothTasteBest bluetoothTasteBest);

    /**
     * @Description 根据id查询BluetoothTasteBest
     * @Author wanghongliang
     */
    BluetoothTasteBest getBluetoothTasteBestById(BluetoothTasteBest bluetoothTasteBest);

    /**
     * @Description 根据烟弹id 获取烟弹最佳口感
     * @Author wanghongliang
     */
    List<BluetoothTasteBest> getBluetoothTasteBestByTastId(BluetoothTasteBest bluetoothTasteBest);
}