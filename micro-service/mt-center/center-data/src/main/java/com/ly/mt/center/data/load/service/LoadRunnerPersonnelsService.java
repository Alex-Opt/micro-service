package com.ly.mt.center.data.load.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LoadRunnerPersonnelsService {
    /**
     * @Description 保存LoadRunnerPersonnels
     * @Author taoye
     */
    ResponseJson insertLoadRunnerPersonnels(JSONObject jsonObject);

    /**
     * @Description 删除LoadRunnerPersonnels
     * @Author taoye
     */
    ResponseJson deleteLoadRunnerPersonnels(JSONObject jsonObject);

    /**
     * @Description 更新LoadRunnerPersonnels
     * @Author taoye
     */
    ResponseJson updateLoadRunnerPersonnels(JSONObject jsonObject);

    /**
     * @Description 查询LoadRunnerPersonnels
     * @Author taoye
     */
    ResponseJson getLoadRunnerPersonnels(JSONObject jsonObject);
}