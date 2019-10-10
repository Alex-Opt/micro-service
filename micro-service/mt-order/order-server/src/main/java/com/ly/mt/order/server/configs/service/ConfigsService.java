package com.ly.mt.order.server.configs.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ConfigsService {

    List<JSONObject> getConfigs();

}
