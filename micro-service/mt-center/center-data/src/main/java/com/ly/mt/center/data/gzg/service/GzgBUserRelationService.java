package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgBUserRelationService {
    /**
     * @Description 保存GzgBUserRelation
     * @Author taoye
     */
    ResponseJson insertGzgBUserRelation(JSONObject jsonObject);

    /**
     * @Description 删除GzgBUserRelation
     * @Author taoye
     */
    ResponseJson deleteGzgBUserRelation(JSONObject jsonObject);

    /**
     * @Description 更新GzgBUserRelation
     * @Author taoye
     */
    ResponseJson updateGzgBUserRelation(JSONObject jsonObject);

    /**
     * @Description 查询GzgBUserRelation
     * @Author taoye
     */
    ResponseJson getGzgBUserRelation(JSONObject jsonObject);
}