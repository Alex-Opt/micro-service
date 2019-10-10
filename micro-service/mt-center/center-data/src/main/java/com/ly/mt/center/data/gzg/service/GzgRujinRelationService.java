package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgRujinRelationService {
    /**
     * @Description 插入 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson insertGzgRujinRelation(JSONObject jsonObject);

    /**
     * @Description 根据id删除 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson deleteGzgRujinRelationById(JSONObject jsonObject);

    /**
     * @Description 根据id更新 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson updateGzgRujinRelationById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson getGzgRujinRelationByNameAndTid(JSONObject jsonObject);

    /**
     * @Description 通过 tname 查询 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson getGzgRujinRelationByTname(JSONObject jsonObject);


    /**
     * @Description 通过 tid 查询 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson getGzgRujinRelationByTid(JSONObject jsonObject);

    /**
     * @Description 根据条件查询 GzgRujinRelation
     * @Author taoye
     */
    ResponseJson getGzgRujinRelationByName(JSONObject jsonObject);

    ResponseJson getGzgRujinRelationByNameAndTname(JSONObject jsonObject);
}