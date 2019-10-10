package com.ly.mt.core.fegin.client.hystrix;

import com.ly.mt.core.entity.RequestJson;
import com.ly.mt.core.entity.ResponseJson;
import com.ly.mt.core.fegin.client.DataFeginClient;
import org.springframework.stereotype.Component;

@Component
public class DataFeginHystrix implements DataFeginClient {
    @Override
    public ResponseJson requestDistribute(RequestJson requestJson) {
        throw new RuntimeException("center-data服务异常!");
    }
}