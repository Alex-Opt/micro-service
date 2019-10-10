package com.ly.mt.center.data.lode.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface LodeRunnerCluesService {
    /**
     * @Description 保存LodeRunnerClues
     * @Author taoye
     */
    ResponseJson insertLodeRunnerClues(JSONObject jsonObject);

    /**
     * @Description 删除LodeRunnerClues
     * @Author taoye
     */
    ResponseJson deleteLodeRunnerClues(JSONObject jsonObject);

    /**
     * @Description 更新LodeRunnerClues
     * @Author taoye
     */
    ResponseJson updateLodeRunnerClues(JSONObject jsonObject);

    /**
     * @Description 查询LodeRunnerClues
     * @Author taoye
     */
    ResponseJson getLodeRunnerClues(JSONObject jsonObject);
}