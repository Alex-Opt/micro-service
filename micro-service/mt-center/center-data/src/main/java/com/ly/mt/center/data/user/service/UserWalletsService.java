package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserWalletsService {
    /**
     * @Description 保存UserWallets
     * @Author taoye
     */
    ResponseJson insertUserWallets(JSONObject jsonObject);

    /**
     * @Description 删除UserWallets
     * @Author taoye
     */
    ResponseJson deleteUserWallets(JSONObject jsonObject);

    /**
     * @Description 更新UserWallets
     * @Author taoye
     */
    ResponseJson updateUserWallets(JSONObject jsonObject);

    /**
     * @Description 查询UserWallets
     * @Author taoye
     */
    ResponseJson getUserWallets(JSONObject jsonObject);
}