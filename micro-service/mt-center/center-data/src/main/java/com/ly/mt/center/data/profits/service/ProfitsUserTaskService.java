package com.ly.mt.center.data.profits.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description: 用户任务
 * @Author: zhuyh
 */
public interface ProfitsUserTaskService {
    /**
     * @Description: 根据用户id获取未完成的任务
     * @Author: zhuyh
     */
    ResponseJson getNotFinishTaskByUId(JSONObject jsonObject);
}
