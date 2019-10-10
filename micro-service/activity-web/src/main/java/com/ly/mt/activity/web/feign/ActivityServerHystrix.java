package com.ly.mt.activity.web.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ActivityServerHystrix implements ActivityFeignClient{
    @Override
    public JSONObject requestDistribute(JSONObject map) {
        throw new RuntimeException("activity-server服务异常!");
    }
}
