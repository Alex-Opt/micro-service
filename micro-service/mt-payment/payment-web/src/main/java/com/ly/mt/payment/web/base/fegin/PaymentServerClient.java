package com.ly.mt.payment.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "payment-server", fallback = PaymentServerHystrix.class)
public interface PaymentServerClient {
    @RequestMapping(value = "/payment/server/requestDistribute", method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject jsonObject);
}