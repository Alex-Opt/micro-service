package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserConnectionsService {
    /**
     * @Description 保存UserConnections
     * @Author taoye
     */
    ResponseJson insertUserConnections(JSONObject jsonObject);

    /**
     * @Description 删除UserConnections
     * @Author taoye
     */
    ResponseJson deleteUserConnections(JSONObject jsonObject);

    /**
     * @Description 更新UserConnections
     * @Author taoye
     */
    ResponseJson updateUserConnections(JSONObject jsonObject);

    /**
     * @Description 查询UserConnections
     * @Author taoye
     */
    ResponseJson getUserConnections(JSONObject jsonObject);
}