package com.ly.mt.core.feign.client.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.client.ThirdFeginClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 第三方服务接口feign调用fallback
 *
 * @author taoye
 */
@Component
public class ThirdFeginHystrix implements ThirdFeginClient {
    @Override
    public JSONObject requestDistribute(JSONObject requestJson) {
        throw new RuntimeException("center-third服务异常!");
    }

    @Override
    public JSONObject uploadFile(MultipartFile file, String path) {
        throw new RuntimeException("center-third服务异常!");
    }

    @Override
    public JSONObject uploadFiles(String Path, MultipartFile... file) {
        throw new RuntimeException("center-third服务异常!");
    }

    /**
     * 第三方服务上传文件接口
     *
     * @param files：文件集合
     * @param paths：文件路径集合
     * @return JSONObject
     * @author wanghongliang
     */
    @Override
    public JSONObject uploadsMultipleFiles(MultipartFile[] files, String[] paths) {
        throw new RuntimeException("center-third服务异常!");
    }
}