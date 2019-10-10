package com.ly.mt.core.fegin.client;

import com.ly.mt.core.entity.RequestJson;
import com.ly.mt.core.entity.ResponseJson;
import com.ly.mt.core.fegin.client.hystrix.DataFeginHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "center-data", fallback = DataFeginHystrix.class)
public interface DataFeginClient {
    @PostMapping("/center/data/requestDistribute")
    ResponseJson requestDistribute(@RequestBody RequestJson requestJson);
}