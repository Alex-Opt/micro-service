package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.entity.GzgName;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgNameService {

    ResponseJson insertGzgName(JSONObject gzgName);

    ResponseJson getGzgNameBatch(JSONObject jsonObject);
}
