package com.ly.mt.center.data.activity.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ActivityInfoService {
    /**
     * @Description 保存ActivityInfo
     * @Author taoye
     */
    ResponseJson insertActivityInfo(JSONObject jsonObject);

    /**
     * @Description 删除ActivityInfo
     * @Author taoye
     */
    ResponseJson deleteActivityInfo(JSONObject jsonObject);

    /**
     * @Description 更新ActivityInfo
     * @Author taoye
     */
    ResponseJson updateActivityInfo(JSONObject jsonObject);

    /**
     * @Description 查询ActivityInfo
     * @Author taoye
     */
    ResponseJson getActivityInfo(JSONObject jsonObject);

    /**
     * @Description 根据spuId查询优惠活动数据
     * @Author xinguangzhi
     */
    ResponseJson getActivityInfoBySpuId(JSONObject jsonObject);
}