package com.ly.mt.example.txc.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TxcService {
    ResponseJson add1(JSONObject jsonObject);

    ResponseJson add2(JSONObject jsonObject);
}