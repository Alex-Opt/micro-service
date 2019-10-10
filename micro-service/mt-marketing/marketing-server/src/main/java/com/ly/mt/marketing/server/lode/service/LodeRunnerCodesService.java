package com.ly.mt.marketing.server.lode.service;

import com.alibaba.fastjson.JSONObject;

/**
 *@Description 淘金者专属邀请码
 *@Author  zhuyh
 */
public interface LodeRunnerCodesService {


    /**
     *@Description 获取邀请码
     *@Author  zhuyh
     */
    JSONObject getCode(String json) throws Exception;
}
