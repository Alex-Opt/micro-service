package com.ly.mt.core.fegin.client;

import com.ly.mt.core.entity.RequestJson;
import com.ly.mt.core.entity.ResponseJson;
import com.ly.mt.core.fegin.client.hystrix.ThirdFeginHystrix;
import com.ly.mt.core.fegin.config.MultipartSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "center-third", configuration = MultipartSupportConfig.class, fallback = ThirdFeginHystrix.class)
public interface ThirdFeginClient {
    @PostMapping("/center/third/requestDistribute")
    ResponseJson requestDistribute(@RequestBody RequestJson requestJson);

    @PostMapping(value = "/center/third/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseJson uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "path") String Path);
}