package com.ly.mt.order.server.base.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MtTaskHystrix implements MtTaskClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("mt-task服务异常!");
    }
}