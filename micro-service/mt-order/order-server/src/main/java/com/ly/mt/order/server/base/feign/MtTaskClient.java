package com.ly.mt.order.server.base.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mt-task", fallback = MtTaskHystrix.class)
public interface MtTaskClient {
    @RequestMapping(value = "/mt/task/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}