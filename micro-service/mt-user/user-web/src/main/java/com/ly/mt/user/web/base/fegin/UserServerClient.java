package com.ly.mt.user.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-server", fallback = UserServerHystrix.class)
public interface UserServerClient {
    @RequestMapping(value = "/user/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}