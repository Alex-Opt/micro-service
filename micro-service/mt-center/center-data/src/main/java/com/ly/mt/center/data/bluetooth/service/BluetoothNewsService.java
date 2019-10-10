package com.ly.mt.center.data.bluetooth.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothNewsService {

    /**
     * @Description 查询新闻列表数据
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsList(JSONObject jsonObject);

    /**
     * @Description 查询用户收藏新闻列表数据
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsCollectList(JSONObject jsonObject);

    /**
     * @Description 更新新闻阅读次数+1
     * 1.更新新闻阅读数量
     * 2.更细新闻收藏人数
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothNewsReadNumber(JSONObject jsonObject);

    /**
     * @Description 更新收藏人数加1
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothNewsCollectNumber(JSONObject jsonObject);

    /**
     * @Description 更新收藏人数减1
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothNewsCollectMinusNumber(JSONObject jsonObject);

    /**
     * @Description 查询新闻详情
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsDetail(JSONObject jsonObject);




}