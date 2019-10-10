package com.ly.mt.core.feign.client.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.client.DataFeginClient;
import org.springframework.stereotype.Component;

/**
 * 数据中心接口feigh调用fallback
 *
 * @author taoye
 */
@Component
public class DataFeginHystrix implements DataFeginClient {
    @Override
    public JSONObject requestDistribute(JSONObject requestJson) {
        throw new RuntimeException("center-data服务异常!");
    }
}