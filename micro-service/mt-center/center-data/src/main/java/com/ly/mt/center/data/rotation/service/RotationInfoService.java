package com.ly.mt.center.data.rotation.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface RotationInfoService {
    /**
     * @Description 保存RotationInfo
     * @Author taoye
     */
    ResponseJson insertRotationInfo(JSONObject jsonObject);

    /**
     * @Description 删除RotationInfo
     * @Author taoye
     */
    ResponseJson deleteRotationInfo(JSONObject jsonObject);

    /**
     * @Description 更新RotationInfo
     * @Author taoye
     */
    ResponseJson updateRotationInfo(JSONObject jsonObject);

    /**
     * @Description 查询RotationInfo
     * @Author taoye
     */
    ResponseJson getRotationInfo(JSONObject jsonObject);

    /**
     * @Description 查询RotationInfo集合
     * @Author taoye
     */
    ResponseJson listRotationInfo(JSONObject jsonObject);
}