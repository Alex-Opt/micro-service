package com.ly.mt.gzg.b.server.gzgb.service;

import com.alibaba.fastjson.JSONObject;

public interface RefundService {
    JSONObject wxRefund(String json);
    JSONObject alipayRefund(String json);
}
