package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserShareRecordService {

    /**
     * @Description 插入User
     * @Author wanglong
     */
    ResponseJson insertUserShareRecord(JSONObject jsonObject);

    /**
     * 添加分享数量
     * @param jsonObject
     * @Author wanglong
     * @return
     */
    ResponseJson addUserShareRecord(JSONObject jsonObject);
}
