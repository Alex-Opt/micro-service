package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgCityService {
    /**
     * @Description 保存GzgCity
     * @Author taoye
     */
    ResponseJson insertGzgCity(JSONObject jsonObject);

    /**
     * @Description 删除GzgCity
     * @Author taoye
     */
    ResponseJson deleteGzgCity(JSONObject jsonObject);

    /**
     * @Description 更新GzgCity
     * @Author taoye
     */
    ResponseJson updateGzgCity(JSONObject jsonObject);

    /**
     * @Description 查询GzgCity
     * @Author taoye
     */
    ResponseJson getGzgCity(JSONObject jsonObject);
}