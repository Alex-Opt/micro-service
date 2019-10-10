package com.ly.mt.user.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class UserServerHystrix implements UserServerClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("user-server服务异常!");
    }
}