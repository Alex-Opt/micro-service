package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothNewsCollectService {

    /**
     * @Description 根据id删除用户收藏 删除时需要减去收藏数量
     * @Author wanghongliang
     */
    ResponseJson deleteBluetoothNewsCollect(JSONObject jsonObject);

    /**
     * @Description 用户加入收藏 加入收藏时需要更新新闻收藏数量
     * @Author wanghongliang
     */
    ResponseJson insertBluetoothNewsCollect(JSONObject jsonObject);

    /**
     * @Description 查询用户是否收藏
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsCollect(JSONObject jsonObject);
}