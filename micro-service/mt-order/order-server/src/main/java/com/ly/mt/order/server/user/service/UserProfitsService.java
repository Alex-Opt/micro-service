package com.ly.mt.order.server.user.service;

import com.alibaba.fastjson.JSONObject;

public interface UserProfitsService {

    JSONObject getByUserId(Long userId);

    JSONObject getUserById(Long userId);

    int updateRefundProfits(JSONObject jsonObject);

}
