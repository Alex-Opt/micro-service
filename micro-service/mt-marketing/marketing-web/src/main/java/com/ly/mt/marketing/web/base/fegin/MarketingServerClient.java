package com.ly.mt.marketing.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *@Description
 *@Author  zhuyh
 */
@FeignClient(value = "marketing-server", fallback = MarketingServerHystrix.class)
public interface MarketingServerClient {
    @RequestMapping(value = "/marketing/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody(required = false) JSONObject jsonObject);
}
