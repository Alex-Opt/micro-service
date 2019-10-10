package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserMakeMoneyService {
    /**
     * @Description 保存UserMakeMoney
     * @Author taoye
     */
    ResponseJson insertUserMakeMoney(JSONObject jsonObject);

    /**
     * @Description 删除UserMakeMoney
     * @Author taoye
     */
    ResponseJson deleteUserMakeMoney(JSONObject jsonObject);

    /**
     * @Description 更新UserMakeMoney
     * @Author taoye
     */
    ResponseJson updateUserMakeMoney(JSONObject jsonObject);

    /**
     * @Description 查询UserMakeMoney
     * @Author taoye
     */
    ResponseJson getUserMakeMoney(JSONObject jsonObject);
}