package com.ly.mt.gzg.b.server.gzgb.service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {
    JSONObject login(String json);
    JSONObject out(String json);
}
