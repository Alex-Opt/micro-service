package com.ly.mt.blue.tooth.news.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothNewsService {

    /**
     * @Description 查询新闻列表数据
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsList();

    /**
     * @Description 查询用户收藏新闻列表数据
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsCollectList();

    /**
     * @Description 更新新闻阅读次数+1
     * 1.更新新闻阅读数量
     * 2.更细新闻收藏人数
     * @Author wanghongliang
     */
    ResponseJson updateBluetoothNewsReadNumber(String newsId);

    /**
     * @Description 查询新闻详情
     * @Author wanghongliang
     */
    ResponseJson getBluetoothNewsDetail(String newsId);

}