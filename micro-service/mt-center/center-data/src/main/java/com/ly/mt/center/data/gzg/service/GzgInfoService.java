package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgInfoService {
    /**
     * @Description 插入GzgInfo
     * @Author taoye
     */
    ResponseJson insertGzgInfo(JSONObject jsonObject);

    /**
     * @Description 根据id删除GzgInfo
     * @Author taoye
     */
    ResponseJson deleteGzgInfoById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GzgInfo
     * @Author taoye
     */
    ResponseJson updateGzgInfoById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GzgInfo
     * @Author taoye
     */
    ResponseJson getGzgInfo(JSONObject jsonObject);

    /**
     * @Description 根据id查询GzgInfo
     * @Author taoye
     */
    ResponseJson getGzgInfoById(JSONObject jsonObject);
}