package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgHotelUserRelationService {
    /**
     * @Description 保存GzgHotelUserRelation
     * @Author taoye
     */
    ResponseJson insertGzgHotelUserRelation(JSONObject jsonObject);

    /**
     * @Description 删除GzgHotelUserRelation
     * @Author taoye
     */
    ResponseJson deleteGzgHotelUserRelation(JSONObject jsonObject);

    /**
     * @Description 更新GzgHotelUserRelation
     * @Author taoye
     */
    ResponseJson updateGzgHotelUserRelation(JSONObject jsonObject);

    /**
     * @Description 查询GzgHotelUserRelation
     * @Author taoye
     */
    ResponseJson getGzgHotelUserRelation(JSONObject jsonObject);
}