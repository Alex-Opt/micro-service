package com.ly.mt.order.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "order-server", fallback = OrderServerHystrix.class)
public interface OrderServerClient {
    @RequestMapping(value = "/order/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}