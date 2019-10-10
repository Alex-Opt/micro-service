package com.ly.mt.core.common.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "third-server", fallback = ThirdFeginHystrix.class)
public interface ThirdFeginClient {
    @RequestMapping(value = "/third/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}