package com.ly.mt.center.data.basic.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface BasicAreaService {
    /**
     * @Description 保存BasicArea
     * @Author taoye
     */
    ResponseJson insertBasicArea(JSONObject jsonObject);

    /**
     * @Description 删除BasicArea
     * @Author taoye
     */
    ResponseJson deleteBasicArea(JSONObject jsonObject);

    /**
     * @Description 更新BasicArea
     * @Author taoye
     */
    ResponseJson updateBasicArea(JSONObject jsonObject);

    /**
     * @Description 查询BasicArea
     * @Author taoye
     */
    ResponseJson getBasicArea(JSONObject jsonObject);
}