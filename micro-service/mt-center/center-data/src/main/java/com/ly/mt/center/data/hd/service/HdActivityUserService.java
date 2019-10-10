package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface HdActivityUserService {
    /**
     * @Description 保存HdActivityUser
     * @Author taoye
     */
    ResponseJson insertHdActivityUser(JSONObject jsonObject);

    /**
     * @Description 删除HdActivityUser
     * @Author taoye
     */
    ResponseJson deleteHdActivityUser(JSONObject jsonObject);

    /**
     * @Description 更新HdActivityUser
     * @Author taoye
     */
    ResponseJson updateHdActivityUser(JSONObject jsonObject);

    /**
     * @Description 查询HdActivityUser
     * @Author taoye
     */
    ResponseJson getHdActivityUser(JSONObject jsonObject);
}