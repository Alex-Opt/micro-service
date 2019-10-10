package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserMakeMoneyMonthsService {
    /**
     * @Description 保存UserMakeMoneyMonths
     * @Author taoye
     */
    ResponseJson insertUserMakeMoneyMonths(JSONObject jsonObject);

    /**
     * @Description 删除UserMakeMoneyMonths
     * @Author taoye
     */
    ResponseJson deleteUserMakeMoneyMonths(JSONObject jsonObject);

    /**
     * @Description 更新UserMakeMoneyMonths
     * @Author taoye
     */
    ResponseJson updateUserMakeMoneyMonths(JSONObject jsonObject);

    /**
     * @Description 查询UserMakeMoneyMonths
     * @Author taoye
     */
    ResponseJson getUserMakeMoneyMonths(JSONObject jsonObject);
}