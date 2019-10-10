package com.ly.mt.marketing.web.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.MarketingMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.web.CoreController;
import com.ly.mt.marketing.web.base.fegin.MarketingServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

/**
 *@Description  收益父controller
 *@Author  zhuyh
 */
public class BaseController extends CoreController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MarketingServerClient marktingServer;


    /**
     *@Description  marketing-web调用marketing-server服务
     *@Author  zhuyh
     */
    public JSONObject callMarketingServer(MarketingMethodEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            jsonObject.put("serviceName", serviceName);
            jsonObject.put("functionName", functionName);
            long start = System.currentTimeMillis();
            jsonObject = marktingServer.requestDistribute(jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("调用marketing-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用marketing-server:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}
