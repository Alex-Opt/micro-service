package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothTokenMapper {
    /**
     * @Description 插入Token
     * @Author whl
     */
    int insert(BluetoothToken bluetoothToken);

    /**
     * @Description 获取Token
     * @Author wanghongliang
     */
    String getToken(BluetoothToken bluetoothToken);

    /**
     * @Description 更新token
     * @Author
     */
    int updateToken(BluetoothToken bluetoothToken);

}