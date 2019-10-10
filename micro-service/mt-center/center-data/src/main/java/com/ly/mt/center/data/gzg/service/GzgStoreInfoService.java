package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgStoreInfoService {
    /**
     * @Description 保存GzgStoreInfo
     * @Author taoye
     */
    ResponseJson insertGzgStoreInfo(JSONObject jsonObject);

    /**
     * @Description 删除GzgStoreInfo
     * @Author taoye
     */
    ResponseJson deleteGzgStoreInfo(JSONObject jsonObject);

    /**
     * @Description 更新GzgStoreInfo
     * @Author taoye
     */
    ResponseJson updateGzgStoreInfo(JSONObject jsonObject);

    /**
     * @Description 查询GzgStoreInfo
     * @Author taoye
     */
    ResponseJson getGzgStoreInfo(JSONObject jsonObject);
}