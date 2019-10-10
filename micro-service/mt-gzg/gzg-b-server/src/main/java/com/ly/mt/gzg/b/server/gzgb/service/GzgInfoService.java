package com.ly.mt.gzg.b.server.gzgb.service;

import com.alibaba.fastjson.JSONObject;

public interface GzgInfoService {

    JSONObject saveGzg(String jsonParam);

    JSONObject getInfos(String jsonParam);

    JSONObject saveGzgUser(String jsonParam);

    JSONObject findGzgUsers(String jsonParam);

    JSONObject findUserByPhone(String jsonParam);

    JSONObject delGzgUserRelation(String jsonParam);

    JSONObject gzgPlans(String json);
}
