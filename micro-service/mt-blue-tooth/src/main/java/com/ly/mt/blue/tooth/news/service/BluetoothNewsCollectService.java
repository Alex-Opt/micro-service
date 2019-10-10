package com.ly.mt.blue.tooth.news.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface BluetoothNewsCollectService {

    /**
     * @Description 根据id删除用户收藏 删除时需要减去收藏数量
     * @Author wanghongliang
     */
    ResponseJson cancelBluetoothNewsCollect(String newsId);

    /**
     * @Description 用户加入收藏 加入收藏时需要更新新闻收藏数量
     * @Author wanghongliang
     */
    ResponseJson joinBluetoothNewsCollect(String newsId);

}