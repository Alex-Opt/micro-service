package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerUserConfigsService {
    /**
     * @Description 保存LodeRunnerUserConfigs
     * @Author taoye
     */
    ResponseJson insertLodeRunnerUserConfigs(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerUserConfigs
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerUserConfigs(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerUserConfigs
     * @Author taoye
     */
    ResponseJson updateLodeRunnerUserConfigs(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerUserConfigs
     * @Author taoye
     */
    ResponseJson getLodeRunnerUserConfigs(JSONObject jsonObject);
}