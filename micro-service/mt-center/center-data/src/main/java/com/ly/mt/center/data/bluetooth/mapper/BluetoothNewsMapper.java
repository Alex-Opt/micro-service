package com.ly.mt.center.data.bluetooth.mapper;

import com.ly.mt.center.data.bluetooth.entity.BluetoothNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BluetoothNewsMapper {
    /**
     * @Description 查询新闻列表
     * @Author wanghongliang
     */
    List<BluetoothNews> selectBluetoothNews();

    /**
     * @Description 查询收藏新闻列表
     * @Author wanghongliang
     */
    List<BluetoothNews> selectBluetoothNewsCollect(@Param("userId") String userId);

    /**
     * @Description 更新新闻阅读次数+1
     * 更新收藏
     * @Author wanghongliang
     */
    int updateBluetoothNewsReadNumber(@Param("id") String id);

    /**
     * @Description 更新收藏人数加1
     * 更新收藏
     * @Author wanghongliang
     */
    int updateBluetoothNewsCollectNumber(@Param("id") String id);

    /**
     * @Description 更新收藏人数减1
     * 更新收藏
     * @Author wanghongliang
     */
    int updateBluetoothNewsCollectMinusNumber(@Param("id") String id);

    /**
     * @Description 查询新闻详情
     * @Author wanghongliang
     */
    BluetoothNews getBluetoothNewsDetail(@Param("id") String id);

}