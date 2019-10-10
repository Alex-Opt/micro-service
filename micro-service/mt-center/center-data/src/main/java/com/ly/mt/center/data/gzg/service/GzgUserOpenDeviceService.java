package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgUserOpenDeviceService {


    /**
     * @Description 根据条件查询 GzgUserOpenDevice
     * @Author
     */
    ResponseJson getGzgUserOpenDeviceByPhone(JSONObject jsonObject);


}