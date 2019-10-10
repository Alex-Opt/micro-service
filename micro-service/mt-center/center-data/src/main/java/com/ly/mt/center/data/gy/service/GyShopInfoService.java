package com.ly.mt.center.data.gy.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GyShopInfoService {
    /**
     * @Description 保存GyShopInfo
     * @Author taoye
     */
    ResponseJson insertGyShopInfo(JSONObject jsonObject);

    /**
     * @Description 删除GyShopInfo
     * @Author taoye
     */
    ResponseJson deleteGyShopInfo(JSONObject jsonObject);

    /**
     * @Description 更新GyShopInfo
     * @Author taoye
     */
    ResponseJson updateGyShopInfo(JSONObject jsonObject);

    /**
     * @Description 查询GyShopInfo
     * @Author taoye
     */
    ResponseJson getGyShopInfo(JSONObject jsonObject);
}