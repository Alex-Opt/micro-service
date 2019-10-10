package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserProfitsService {
    /**
     * @Description 保存UserProfits
     * @Author taoye
     */
    ResponseJson insertUserProfits(JSONObject jsonObject);

    /**
     * @Description 删除UserProfits
     * @Author taoye
     */
    ResponseJson deleteUserProfits(JSONObject jsonObject);

    /**
     * @Description 更新UserProfits
     * @Author taoye
     */
    ResponseJson updateUserProfits(JSONObject jsonObject);

    /**
     * @Description 查询UserProfits
     * @Author taoye
     */
    ResponseJson getUserProfits(JSONObject jsonObject);
}