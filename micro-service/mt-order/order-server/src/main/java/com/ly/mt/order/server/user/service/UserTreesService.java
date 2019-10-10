package com.ly.mt.order.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 查询userTrees
 */
public interface UserTreesService {
    JSONObject getByUserId(Long json);
}
