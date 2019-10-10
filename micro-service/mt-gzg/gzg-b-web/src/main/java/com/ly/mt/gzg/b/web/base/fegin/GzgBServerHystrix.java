package com.ly.mt.gzg.b.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GzgBServerHystrix implements GzgBServerClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("system-server服务异常!");
    }
}