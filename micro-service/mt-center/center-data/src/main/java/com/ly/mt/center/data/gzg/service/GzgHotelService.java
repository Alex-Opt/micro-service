package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgHotelService {
    /**
     * @Description 保存GzgHotel
     * @Author taoye
     */
    ResponseJson insertGzgHotel(JSONObject jsonObject);

    /**
     * @Description 删除GzgHotel
     * @Author taoye
     */
    ResponseJson deleteGzgHotel(JSONObject jsonObject);

    /**
     * @Description 更新GzgHotel
     * @Author taoye
     */
    ResponseJson updateGzgHotel(JSONObject jsonObject);

    /**
     * @Description 查询GzgHotel
     * @Author taoye
     */
    ResponseJson getGzgHotel(JSONObject jsonObject);
}