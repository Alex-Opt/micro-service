package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerUserTreesService {
    /**
     * @Description 保存LodeRunnerUserTrees
     * @Author taoye
     */
    ResponseJson insertLodeRunnerUserTrees(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerUserTrees
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerUserTrees(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerUserTrees
     * @Author taoye
     */
    ResponseJson updateLodeRunnerUserTrees(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerUserTrees
     * @Author taoye
     */
    ResponseJson getLodeRunnerUserTrees(JSONObject jsonObject);
}