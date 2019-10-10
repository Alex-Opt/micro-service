package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerUserCodesService {
    /**
     * @Description 保存LodeRunnerUserCodes
     * @Author taoye
     */
    ResponseJson insertLodeRunnerUserCodes(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerUserCodes
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerUserCodes(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerUserCodes
     * @Author taoye
     */
    ResponseJson updateLodeRunnerUserCodes(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerUserCodes
     * @Author taoye
     */
    ResponseJson getLodeRunnerUserCodes(JSONObject jsonObject);
}