package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgBUserService {
    /**
     * @Description 保存GzgBUser
     * @Author taoye
     */
    ResponseJson insertGzgBUser(JSONObject jsonObject);

    /**
     * @Description 删除GzgBUser
     * @Author taoye
     */
    ResponseJson deleteGzgBUser(JSONObject jsonObject);

    /**
     * @Description 更新GzgBUser
     * @Author taoye
     */
    ResponseJson updateGzgBUser(JSONObject jsonObject);

    /**
     * @Description 查询GzgBUser
     * @Author taoye
     */
    ResponseJson getGzgBUser(JSONObject jsonObject);
}