package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;
import java.util.Map;

public interface CabinetAreaService {

    ResponseJson getByMPid(JSONObject data);
}
