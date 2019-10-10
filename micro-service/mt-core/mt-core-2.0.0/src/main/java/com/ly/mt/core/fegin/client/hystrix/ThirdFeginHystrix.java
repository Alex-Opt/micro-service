package com.ly.mt.core.fegin.client.hystrix;

import com.ly.mt.core.entity.RequestJson;
import com.ly.mt.core.entity.ResponseJson;
import com.ly.mt.core.fegin.client.ThirdFeginClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ThirdFeginHystrix implements ThirdFeginClient {
    @Override
    public ResponseJson requestDistribute(RequestJson requestJson) {
        throw new RuntimeException("center-third服务异常!");
    }

    @Override
    public ResponseJson uploadFile(MultipartFile file, String path) {
        throw new RuntimeException("center-third服务异常!");
    }
}