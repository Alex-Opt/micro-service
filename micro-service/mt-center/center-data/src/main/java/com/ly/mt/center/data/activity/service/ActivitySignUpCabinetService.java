package com.ly.mt.center.data.activity.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ActivitySignUpCabinetService {
    /**
     * @Description 保存ActivitySignUpCabinet
     * @Author taoye
     */
    ResponseJson insertActivitySignUpCabinet(JSONObject jsonObject);

    /**
     * @Description 删除ActivitySignUpCabinet
     * @Author taoye
     */
    ResponseJson deleteActivitySignUpCabinet(JSONObject jsonObject);

    /**
     * @Description 更新ActivitySignUpCabinet
     * @Author taoye
     */
    ResponseJson updateActivitySignUpCabinet(JSONObject jsonObject);

    /**
     * @Description 查询ActivitySignUpCabinet
     * @Author taoye
     */
    ResponseJson getActivitySignUpCabinet(JSONObject jsonObject);
}