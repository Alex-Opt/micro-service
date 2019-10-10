package com.ly.mt.purchase.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *@Description
 *@Author  yls
 */
@FeignClient(value = "purchase-server", fallback = PurchaseServerHystrix.class)
public interface PurchaseServerClient {
    @RequestMapping(value = "/purchase/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody(required = false) JSONObject jsonObject);
}
