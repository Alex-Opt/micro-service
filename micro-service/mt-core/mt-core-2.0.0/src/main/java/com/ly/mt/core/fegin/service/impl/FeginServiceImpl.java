package com.ly.mt.core.fegin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.service.impl.CoreServiceImpl;
import com.ly.mt.core.entity.RequestJson;
import com.ly.mt.core.entity.ResponseJson;
import com.ly.mt.core.fegin.client.DataFeginClient;
import com.ly.mt.core.fegin.client.ThirdFeginClient;
import com.ly.mt.core.fegin.method.DataCenterMethod;
import com.ly.mt.core.fegin.method.ThirdCenterMethod;
import com.ly.mt.core.fegin.service.FeginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.ly.mt.core.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class FeginServiceImpl extends CoreServiceImpl implements FeginService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FeginServiceImpl.class);
    @Autowired
    ThirdFeginClient thirdFeginClient;
    @Autowired
    DataFeginClient dataFeginClient;

    /**
     * @Description 调用数据中心服务接口
     * @Author taoye
     */
    @Override
    public String callDataCenter(DataCenterMethod method, JSONObject jsonObject) throws Exception {
        RequestJson requestJson = new RequestJson();
        requestJson.setServerName(method.getServiceName());
        requestJson.setFunctionName(method.getFunctionName());
        requestJson.setJsonObject(jsonObject);
        LOGGER.info("callDataCenter:requestJson={}", requestJson.toString());
        ResponseJson responseJson = dataFeginClient.requestDistribute(requestJson);
        LOGGER.info("callDataCenter:responseJson={}", responseJson.toString());
        if (!RESPONSE_CODE_SUCCESS.getCode().equals(responseJson.getCode())) {
            throw new RuntimeException("数据中心服务接口处理异常");
        }
        return responseJson.getResult();
    }

    /**
     * @Description 调用第三方服务接口
     * @Author taoye
     */
    @Override
    public String callThirdCenter(ThirdCenterMethod method, JSONObject jsonObject) throws Exception {
        RequestJson requestJson = new RequestJson();
        requestJson.setServerName(method.getServiceName());
        requestJson.setFunctionName(method.getFunctionName());
        requestJson.setJsonObject(jsonObject);
        LOGGER.info("callThirdCenter:requestJson={}", requestJson.toString());
        ResponseJson responseJson = thirdFeginClient.requestDistribute(requestJson);
        LOGGER.info("callThirdCenter:responseJson={}", responseJson.toString());
        if (!RESPONSE_CODE_SUCCESS.getCode().equals(responseJson.getCode())) {
            throw new RuntimeException("第三方服务接口处理异常");
        }
        return responseJson.getResult();
    }

    /**
     * @Description 调用第三方服务接口上传文件
     * @Author taoye
     */
    @Override
    public String callThirdCenter(MultipartFile file, String path) throws Exception {
        LOGGER.info("callDataCenter:path={}", path);
        ResponseJson responseJson = thirdFeginClient.uploadFile(file, path);
        LOGGER.info("callDataCenter:responseJson={}", responseJson.toString());
        if (!RESPONSE_CODE_SUCCESS.getCode().equals(responseJson.getCode())) {
            throw new RuntimeException("第三方服务上传文件处理异常");
        }
        return responseJson.getResult();
    }
}