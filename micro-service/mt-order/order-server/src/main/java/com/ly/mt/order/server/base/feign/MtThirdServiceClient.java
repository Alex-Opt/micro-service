package com.ly.mt.order.server.base.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "center-third", fallback = MtThirdServiceHystrix.class)
public interface MtThirdServiceClient {
    @RequestMapping(value = "/center/third/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody(required = false) JSONObject jsonObject);
}