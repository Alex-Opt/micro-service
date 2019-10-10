package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerConfigsService {
    /**
     * @Description 保存LodeRunnerConfigs
     * @Author taoye
     */
    ResponseJson insertLodeRunnerConfigs(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerConfigs
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerConfigs(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerConfigs
     * @Author taoye
     */
    ResponseJson updateLodeRunnerConfigs(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerConfigs
     * @Author taoye
     */
    ResponseJson getLodeRunnerConfigs(JSONObject jsonObject);

    /**
     * 获取所有
     * @return
     */
    ResponseJson getAllLodeRunnerConfigs(JSONObject jsonObject);
}