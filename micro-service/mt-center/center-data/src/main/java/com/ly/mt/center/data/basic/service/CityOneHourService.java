package com.ly.mt.center.data.basic.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CityOneHourService {
    /**
     * @Description 保存CityOneHour
     * @Author taoye
     */
    ResponseJson insertCityOneHour(JSONObject jsonObject);

    /**
     * @Description 删除CityOneHour
     * @Author taoye
     */
    ResponseJson deleteCityOneHour(JSONObject jsonObject);

    /**
     * @Description 更新CityOneHour
     * @Author taoye
     */
    ResponseJson updateCityOneHour(JSONObject jsonObject);

    /**
     * @Description 查询CityOneHour
     * @Author taoye
     */
    ResponseJson getCityOneHour(JSONObject jsonObject);

    /**
     * 根据区域id查询一小时达城市
     * @param jsonObject key="areaId" {@link Long} 区域id
     * @return  areaOpen  true已经开通 false 未开通
     */
    ResponseJson findByAreaId(JSONObject jsonObject);
}