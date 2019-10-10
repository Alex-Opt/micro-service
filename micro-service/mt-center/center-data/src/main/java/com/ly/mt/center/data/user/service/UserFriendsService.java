package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserFriendsService {
    /**
     * @Description 保存UserFriends
     * @Author taoye
     */
    ResponseJson insertUserFriends(JSONObject jsonObject);

    /**
     * @Description 删除UserFriends
     * @Author taoye
     */
    ResponseJson deleteUserFriends(JSONObject jsonObject);

    /**
     * @Description 更新UserFriends
     * @Author taoye
     */
    ResponseJson updateUserFriends(JSONObject jsonObject);

    /**
     * @Description 查询UserFriends
     * @Author taoye
     */
    ResponseJson getUserFriends(JSONObject jsonObject);
}