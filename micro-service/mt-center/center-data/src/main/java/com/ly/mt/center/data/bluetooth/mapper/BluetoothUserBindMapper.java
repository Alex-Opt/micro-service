package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothUserBind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BluetoothUserBindMapper {
    /**
     * @Description 插入BluetoothUserBind
     * @Author WHL
     */
    void insertBluetoothUserBind(BluetoothUserBind bluetoothUserBind);

    /**
     * @Description 根据id更新BluetoothUserBind
     * @Author WHL
     */
    int updateBluetoothUserBindById(BluetoothUserBind bluetoothUserBind);

    /**
     * @Description 根据条件更新BluetoothUserBind
     * @Author WHL
     */
    int updateBluetoothUserBindByCondtion(BluetoothUserBind bluetoothUserBind);

    /**
     * @Description 根据id查询BluetoothUserBind
     * @Author WHL
     */
    List<BluetoothUserBind> getBluetoothUserBindByCondtions(BluetoothUserBind bluetoothUserBind);

    /**
     * 根据烟杆id获取烟杆详情
     * @param id
     * @return
     */
    BluetoothUserBind  getBluetoothUserBindById(String id);
}