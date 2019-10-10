package com.ly.mt.center.data.basic.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BasicRoleService {
    /**
     * 获取用户角色
     * @param jsonObject
     * @return
     */
    ResponseJson getBasicRole(JSONObject jsonObject);

}