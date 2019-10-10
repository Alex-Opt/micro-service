package com.ly.mt.core.common.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ThirdFeginHystrix implements ThirdFeginClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("server-third服务异常!");
    }
}