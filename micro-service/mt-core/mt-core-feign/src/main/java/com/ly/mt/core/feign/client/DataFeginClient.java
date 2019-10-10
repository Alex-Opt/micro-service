package com.ly.mt.core.feign.client;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.client.hystrix.DataFeginHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 数据中心feigh调用
 *
 * @author taoye
 */
@FeignClient(value = "center-data", fallback = DataFeginHystrix.class)
public interface DataFeginClient {
    /**
     * 数据中心feigh调用统一接口
     *
     * @param requestJson：请求参数
     * @return JSONObject
     * @author taoye
     */
    @PostMapping("/center/data/requestDistribute")
    JSONObject requestDistribute(@RequestBody JSONObject requestJson);
}