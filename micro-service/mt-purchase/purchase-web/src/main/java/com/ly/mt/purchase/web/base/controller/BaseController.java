package com.ly.mt.purchase.web.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.PurchaseMethodEnum;
import com.ly.mt.core.common.method.UserMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.oss.OssServer;
import com.ly.mt.core.web.CoreController;
import com.ly.mt.purchase.web.base.fegin.PurchaseServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

@Controller
public class BaseController extends CoreController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private PurchaseServerClient purchaseServer;

    /**
     * @Description purchase-web调用purchase-server服务
     * @Author taoye
     */
    public JSONObject callPurchaseServer(PurchaseMethodEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            jsonObject.put("serviceName", serviceName);
            jsonObject.put("functionName", functionName);
            long start = System.currentTimeMillis();
            jsonObject = purchaseServer.requestDistribute(jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("调用purchase-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用purchase-server:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}