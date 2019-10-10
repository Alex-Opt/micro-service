package com.ly.mt.marketing.server.lode.service;

import com.alibaba.fastjson.JSONObject;

/**
 *@Description 淘金人树接口
 *@Author  zhuyh
 */
public interface LodeRunnerTreesService {
    /**
     *@Description 查询淘金明细
     *@Author  zhuyh
     */
    JSONObject getDetails(String json)throws Exception;

    /**
     *@Description 查询团队淘金明细
     *@Author  zhuyh
     */
    JSONObject getTeamHeadDetails(String json) throws Exception;

    /**
     *@Description 获取我的淘金
     *@Author  zhuyh
     */
    JSONObject getMine(String json) throws Exception;
}
