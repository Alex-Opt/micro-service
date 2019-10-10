package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothUserSuggestions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BluetoothUserSuggestionsMapper {
    /**
     * @Description 插入BluetoothUserSuggestions
     * @Author wanghongliang
     */
    int insertBluetoothUserSuggestions(BluetoothUserSuggestions bluetoothUserSuggestions);

    /**
     * @Description 根据id删除BluetoothUserSuggestions
     * @Author wanghongliang
     */
    void deleteBluetoothUserSuggestionsById(BluetoothUserSuggestions bluetoothUserSuggestions);

    /**
     * @Description 根据id更新BluetoothUserSuggestions
     * @Author wanghongliang
     */
    int updateBluetoothUserSuggestionsById(BluetoothUserSuggestions bluetoothUserSuggestions);

    /**
     * @Description 根据条件查询BluetoothUserSuggestions
     * @Author wanghongliang
     */
    BluetoothUserSuggestions getBluetoothUserSuggestions(BluetoothUserSuggestions bluetoothUserSuggestions);

    /**
     * @Description 根据id查询BluetoothUserSuggestions
     * @Author wanghongliang
     */
    BluetoothUserSuggestions getBluetoothUserSuggestionsById(BluetoothUserSuggestions bluetoothUserSuggestions);
}