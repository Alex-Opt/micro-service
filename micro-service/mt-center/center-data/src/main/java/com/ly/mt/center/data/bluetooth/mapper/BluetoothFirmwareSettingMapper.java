package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothFirmwareSetting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothFirmwareSettingMapper {
    /**
     * @Description 根据条件查询BluetoothFirmwareSetting
     * @Author wanghongliang
     */
    BluetoothFirmwareSetting getBluetoothFirmwareSetting(BluetoothFirmwareSetting bluetoothFirmwareSettingParam);

}