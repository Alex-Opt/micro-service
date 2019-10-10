package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgDeviceCheckLogService {
    /**
     * @Description 保存GzgDeviceCheckLog
     * @Author taoye
     */
    ResponseJson insertGzgDeviceCheckLog(JSONObject jsonObject);

    /**
     * @Description 删除GzgDeviceCheckLog
     * @Author taoye
     */
    ResponseJson deleteGzgDeviceCheckLog(JSONObject jsonObject);

    /**
     * @Description 更新GzgDeviceCheckLog
     * @Author taoye
     */
    ResponseJson updateGzgDeviceCheckLog(JSONObject jsonObject);

    /**
     * @Description 查询GzgDeviceCheckLog
     * @Author taoye
     */
    ResponseJson getGzgDeviceCheckLog(JSONObject jsonObject);
}