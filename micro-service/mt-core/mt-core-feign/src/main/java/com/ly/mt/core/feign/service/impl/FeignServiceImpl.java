package com.ly.mt.core.feign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.ThirdCenterMethod;
import com.ly.mt.core.feign.client.DataFeginClient;
import com.ly.mt.core.feign.client.ThirdFeginClient;
import com.ly.mt.core.feign.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 数据中心/第三方服务feign调用接口
 *
 * @author taoye
 */
@Service
public class FeignServiceImpl implements FeignService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FeignServiceImpl.class);
    @Autowired
    DataFeginClient dataFeginClient;
    @Autowired
    ThirdFeginClient thirdFeginClient;

    @Override
    public String callDataCenter(DataCenterMethod method, JSONObject jsonObject) {
        JSONObject requestJson = new JSONObject();
        requestJson.put("serverName", method.getServiceName());
        requestJson.put("functionName", method.getFunctionName());
        requestJson.put("jsonObject", jsonObject);
        LOGGER.info("callDataCenter:requestJson={}", requestJson.toJSONString());
        JSONObject responseJson = dataFeginClient.requestDistribute(requestJson);
        LOGGER.info("callDataCenter:responseJson={}", responseJson.toJSONString());
        String successCode = "0";
        String resultCode = responseJson.getString("code");
        if (!successCode.equals(resultCode)) {
            throw new RuntimeException("数据中心服务接口处理异常");
        }
        return responseJson.getString("result");
    }

    @Override
    public String callThirdCenter(ThirdCenterMethod method, JSONObject jsonObject) throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("serverName", method.getServiceName());
        requestJson.put("functionName", method.getFunctionName());
        requestJson.put("jsonObject", jsonObject);
/*        LOGGER.info("callThirdCenter:requestJson={}", requestJson.toJSONString());*/
        JSONObject responseJson = thirdFeginClient.requestDistribute(requestJson);
  /*      LOGGER.info("callThirdCenter:responseJson={}", responseJson.toJSONString());*/
        String successCode = "0";
        String resultCode = responseJson.getString("code");
        if (!successCode.equals(resultCode)) {
            throw new RuntimeException("第三方服务接口处理异常");
        }
        return responseJson.getString("result");
    }

    @Override
    public String callThirdCenter(MultipartFile file, String path) throws Exception {
        LOGGER.info("callDataCenter:path={}", path);
        JSONObject responseJson = thirdFeginClient.uploadFile(file, path);
        LOGGER.info("callDataCenter:responseJson={}", responseJson.toString());
        String successCode = "0";
        String resultCode = responseJson.getString("code");
        if (!successCode.equals(resultCode)) {
            throw new RuntimeException("第三方服务上传文件处理异常");
        }
        return responseJson.getString("result");
    }

    @Override
    public String callThirdCenter(String path, MultipartFile... file) {
        LOGGER.info("callDataCenter:path={}", path);
        JSONObject responseJson = thirdFeginClient.uploadFiles(path,file);
        LOGGER.info("callDataCenter:responseJson={}", responseJson.toString());
        String successCode = "0";
        String resultCode = responseJson.getString("code");
        if (!successCode.equals(resultCode)) {
            throw new RuntimeException("第三方服务上传文件处理异常");
        }
        return responseJson.getString("result");
    }

    @Override
    public String callThirdCenter(MultipartFile[] files,String[] paths) {
        LOGGER.info("callThirdCenter:path={}", JSONObject.toJSONString(paths));
        JSONObject responseJson = thirdFeginClient.uploadsMultipleFiles(files,paths);
        LOGGER.info("callThirdCenter:responseJson={}", responseJson.toString());
        String successCode = "0";
        String resultCode = responseJson.getString("code");
        if (!successCode.equals(resultCode)) {
            throw new RuntimeException("第三方服务上传文件处理异常");
        }
        return responseJson.getString("result");
    }
}