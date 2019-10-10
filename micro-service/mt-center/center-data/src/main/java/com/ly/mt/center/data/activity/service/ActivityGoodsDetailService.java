package com.ly.mt.center.data.activity.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ActivityGoodsDetailService {
    /**
     * @Description 保存ActivityGoodsDetail
     * @Author taoye
     */
    ResponseJson insertActivityGoodsDetail(JSONObject jsonObject);

    /**
     * @Description 删除ActivityGoodsDetail
     * @Author taoye
     */
    ResponseJson deleteActivityGoodsDetail(JSONObject jsonObject);

    /**
     * @Description 更新ActivityGoodsDetail
     * @Author taoye
     */
    ResponseJson updateActivityGoodsDetail(JSONObject jsonObject);

    /**
     * @Description 查询ActivityGoodsDetail
     * @Author taoye
     */
    ResponseJson getActivityGoodsDetail(JSONObject jsonObject);
}