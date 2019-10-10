package com.ly.mt.gzg.b.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "gzg-b-server", fallback = GzgBServerHystrix.class)
public interface GzgBServerClient {
    @RequestMapping(value = "/gzgb/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}