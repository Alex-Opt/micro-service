package com.ly.mt.center.data.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PlatformLogisticsService {
    /**
     * @Description 保存PlatformLogistics
     * @Author taoye
     */
    ResponseJson insertPlatformLogistics(JSONObject jsonObject);

    /**
     * @Description 删除PlatformLogistics
     * @Author taoye
     */
    ResponseJson deletePlatformLogistics(JSONObject jsonObject);

    /**
     * @Description 更新PlatformLogistics
     * @Author taoye
     */
    ResponseJson updatePlatformLogistics(JSONObject jsonObject);

    /**
     * @Description 查询PlatformLogistics
     * @Author taoye
     */
    ResponseJson getPlatformLogistics(JSONObject jsonObject);
}