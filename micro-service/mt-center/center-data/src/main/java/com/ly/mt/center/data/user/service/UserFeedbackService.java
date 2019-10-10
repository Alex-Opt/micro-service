package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserFeedbackService {
    /**
     * @Description 保存UserFeedback
     * @Author taoye
     */
    ResponseJson insertUserFeedback(JSONObject jsonObject);

    /**
     * @Description 删除UserFeedback
     * @Author taoye
     */
    ResponseJson deleteUserFeedback(JSONObject jsonObject);

    /**
     * @Description 更新UserFeedback
     * @Author taoye
     */
    ResponseJson updateUserFeedback(JSONObject jsonObject);

    /**
     * @Description 查询UserFeedback
     * @Author taoye
     */
    ResponseJson getUserFeedback(JSONObject jsonObject);
}