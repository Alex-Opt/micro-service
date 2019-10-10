package com.ly.mt.purchase.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PurchaseServerHystrix implements PurchaseServerClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("purchase-server服务异常!");
    }
}