package com.ly.mt.task.gy.service;

import com.alibaba.fastjson.JSONObject;

public interface GyService {
    JSONObject getReqJSONObject();

    String getSign(String str);

    String postGY(JSONObject jsonObject);
}