package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerTreesService {
    /**
     * @Description 保存LodeRunnerTrees
     * @Author taoye
     */
    ResponseJson insertLodeRunnerTrees(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerTrees
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerTrees(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerTrees
     * @Author taoye
     */
    ResponseJson updateLodeRunnerTrees(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerTrees
     * @Author taoye
     */
    ResponseJson getLodeRunnerTreesByUserId(JSONObject jsonObject);
}