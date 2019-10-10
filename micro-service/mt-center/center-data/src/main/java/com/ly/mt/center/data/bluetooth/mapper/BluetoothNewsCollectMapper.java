package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothNewsCollect;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothNewsCollectMapper {
    /**
     * @Description 根据id删除用户收藏 删除时需要减去收藏数量
     * @Author wanghongliang
     */
    int deleteBluetoothNewsCollect(BluetoothNewsCollect bluetoothNewsCollect);

    /**
     * @Description 用户加入收藏 加入收藏时需要更新新闻收藏数量
     * @Author wanghongliang
     */
    int insertBluetoothNewsCollect(BluetoothNewsCollect bluetoothNewsCollect);

    /**
     * @Description 查询用户是否收藏
     * @Author wanghongliang
     */
    BluetoothNewsCollect selectBluetoothNewsCollect(BluetoothNewsCollect bluetoothNewsCollect);
}