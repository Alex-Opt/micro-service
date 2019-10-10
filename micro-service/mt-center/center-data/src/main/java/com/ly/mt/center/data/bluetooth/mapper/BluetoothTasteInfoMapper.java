package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothUserTasteInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BluetoothTasteInfoMapper {
    /**
     * @Description 获取所有烟弹列表
     * @Author wanghongliang
     */
    List<BluetoothUserTasteInfo> getBluetoothTasteList(BluetoothUserTasteInfo bluetoothUserTasteInfo);

}