package com.ly.mt.gzg.b.server.gzgb.service;

import com.alibaba.fastjson.JSONObject;

public interface ReplenishService {
    JSONObject replenishList(String json);
    JSONObject giveUpReplenish(String json);
    JSONObject wyReplenish(String json);
    JSONObject goodsCheck(String json);
    JSONObject cabinetCheck(String json);
    JSONObject commitAudit(String json);
    JSONObject replenishReward(String json);
    JSONObject replenishDetail(String json);
    JSONObject upload(String json);
    JSONObject ruleInfo(String json);
}
