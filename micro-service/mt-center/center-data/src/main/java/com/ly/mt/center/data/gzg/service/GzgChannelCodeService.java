package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgChannelCodeService {
    /**
     * @Description 保存GzgChannelCode
     * @Author taoye
     */
    ResponseJson insertGzgChannelCode(JSONObject jsonObject);

    /**
     * @Description 删除GzgChannelCode
     * @Author taoye
     */
    ResponseJson deleteGzgChannelCode(JSONObject jsonObject);

    /**
     * @Description 更新GzgChannelCode
     * @Author taoye
     */
    ResponseJson updateGzgChannelCode(JSONObject jsonObject);

    /**
     * @Description 查询GzgChannelCode
     * @Author taoye
     */
    ResponseJson getGzgChannelCode(JSONObject jsonObject);
}