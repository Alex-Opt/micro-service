package com.ly.mt.order.server.base.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MtThirdServiceHystrix implements MtThirdServiceClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("third-server服务异常!");
    }
}