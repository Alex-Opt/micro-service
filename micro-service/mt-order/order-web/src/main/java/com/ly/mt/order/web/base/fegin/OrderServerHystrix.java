package com.ly.mt.order.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class OrderServerHystrix implements OrderServerClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("order-server服务异常!");
    }
}