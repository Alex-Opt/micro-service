package com.ly.mt.activity.server.wechat.server;

import com.alibaba.fastjson.JSONObject;

/**
 * 活动任务接口
 * @author pjt
 */
public interface ActivityTaskService {


    /**
     * 查询所有任务
     * @return
     */
    JSONObject tasks(JSONObject jsonObject);

    //查询子任务

    /**
     * 查询子任务根据主任务id
     * @param jsonObject
     * @return
     */
    JSONObject taskSubs(JSONObject jsonObject);


    /**
     * 查询主任务附带子任务详情
     * @param jsonObject
     * @return
     */
    JSONObject task(JSONObject jsonObject);


}
