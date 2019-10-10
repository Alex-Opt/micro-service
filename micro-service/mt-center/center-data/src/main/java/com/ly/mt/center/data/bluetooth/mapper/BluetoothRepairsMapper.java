package com.ly.mt.center.data.bluetooth.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.ly.mt.center.data.bluetooth.entity.BluetoothRepairs;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BluetoothRepairsMapper {
    /**
     * 插入报修信息
     * @param reairs
     * @return
     */
    int insertBluetoothRepairs(BluetoothRepairs reairs);

    /**
     * 更新用户报修信息
     * @param
     * @return
     */
    int updateBluetoothRepairsByPrimaryKey(BluetoothRepairs reairs);

    /**
     * 根据userId查询报修信息
     * @param user_id
     * @return
     */
    BluetoothRepairs getRepairByUserId(@Param("user_id") Long user_id);

}