package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserNoticesService {
    /**
     * @Description 保存UserNotices
     * @Author taoye
     */
    ResponseJson insertUserNotices(JSONObject jsonObject);

    /**
     * @Description 删除UserNotices
     * @Author taoye
     */
    ResponseJson deleteUserNotices(JSONObject jsonObject);

    /**
     * @Description 更新UserNotices
     * @Author taoye
     */
    ResponseJson updateUserNotices(JSONObject jsonObject);

    /**
     * @Description 查询UserNotices
     * @Author taoye
     */
    ResponseJson getUserNotices(JSONObject jsonObject);
}