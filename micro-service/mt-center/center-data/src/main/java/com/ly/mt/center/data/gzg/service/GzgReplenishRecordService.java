package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgReplenishRecordService {
    /**
     * @Description 保存GzgReplenishRecord
     * @Author taoye
     */
    ResponseJson insertGzgReplenishRecord(JSONObject jsonObject);

    /**
     * @Description 删除GzgReplenishRecord
     * @Author taoye
     */
    ResponseJson deleteGzgReplenishRecord(JSONObject jsonObject);

    /**
     * @Description 更新GzgReplenishRecord
     * @Author taoye
     */
    ResponseJson updateGzgReplenishRecord(JSONObject jsonObject);

    /**
     * @Description 查询GzgReplenishRecord
     * @Author taoye
     */
    ResponseJson getGzgReplenishRecord(JSONObject jsonObject);
}