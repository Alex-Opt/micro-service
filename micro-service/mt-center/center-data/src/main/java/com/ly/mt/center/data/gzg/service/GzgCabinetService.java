package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgCabinetService {
    /**
     * @Description 保存GzgCabinet
     * @Author taoye
     */
    ResponseJson insertGzgCabinet(JSONObject jsonObject);

    /**
     * @Description 删除GzgCabinet
     * @Author taoye
     */
    ResponseJson deleteGzgCabinet(JSONObject jsonObject);

    /**
     * @Description 更新GzgCabinet
     * @Author taoye
     */
    ResponseJson updateGzgCabinet(JSONObject jsonObject);

    /**
     * @Description 查询GzgCabinet
     * @Author taoye
     */
    ResponseJson getGzgCabinet(JSONObject jsonObject);

    /**
     *
     * @param jsonObject
     * @return
     */
    ResponseJson getGzgCabinetBySellNo(JSONObject jsonObject);
}