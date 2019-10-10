package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerCodesService {
    /**
     * @Description 保存LodeRunnerCodes
     * @Author taoye
     */
    ResponseJson insertLodeRunnerCodes(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerCodes
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerCodes(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerCodes
     * @Author taoye
     */
    ResponseJson updateLodeRunnerCodes(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerCodes
     * @Author taoye
     */
    ResponseJson getLodeRunnerCodesByInviteCode(JSONObject jsonObject);

    /**
     * 将淘金者的邀请数量加1
     * @param jsonObject
     * @return
     */
    ResponseJson addInviteNums(JSONObject jsonObject);
}