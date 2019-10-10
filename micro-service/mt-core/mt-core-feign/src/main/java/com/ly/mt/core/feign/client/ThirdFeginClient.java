package com.ly.mt.core.feign.client;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.client.hystrix.ThirdFeginHystrix;
import com.ly.mt.core.feign.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 第三方服务接口feign调用
 *
 * @author taoye
 */
@FeignClient(value = "center-third", configuration = MultipartSupportConfig.class, fallback = ThirdFeginHystrix.class)
public interface ThirdFeginClient {
    /**
     * 第三方服务feign调用统一接口
     *
     * @param requestJson：请求参数
     * @return JSONObject
     * @author taoye
     */
    @PostMapping("/center/third/requestDistribute")
    JSONObject requestDistribute(@RequestBody JSONObject requestJson);

    /**
     * 第三方服务上传文件接口
     *
     * @param file：文件
     * @param Path：文件路径
     * @return JSONObject
     * @author taoye
     */
    @PostMapping(value = "/center/third/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    JSONObject uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "path") String Path);

    @PostMapping(value = "/center/third/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    JSONObject uploadFiles(@RequestParam(value = "path") String Path,@RequestPart(value = "file") MultipartFile... file);

    /**
     * 第三方服务上传文件接口
     *
     * @param files：文件集合
     * @param paths：文件路径集合
     * @return JSONObject
     * @author wanghongliang
     */
    @PostMapping(value = "/center/third/uploadsMultipleFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    JSONObject uploadsMultipleFiles(@RequestPart("files") MultipartFile[] files,@RequestParam(value = "paths") String[] paths);
}