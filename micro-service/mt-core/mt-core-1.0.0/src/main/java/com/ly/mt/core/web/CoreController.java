package com.ly.mt.core.web;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.fegin.ThirdFeginClient;
import com.ly.mt.core.common.method.ThirdServerEnum;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

@Primary
@Controller
public class CoreController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CoreController.class);
    @Resource
    public RedisServer redisServer;
    @Autowired
    private ThirdFeginClient thirdServer;

    /**
     * @Description 获取用户登录用户id
     * @Author taoye
     */
    public String getLoginUserId(HttpServletRequest request) {
        return String.valueOf(request.getSession().getAttribute("userId"));
    }

    public JSONObject callThirdServer(ThirdServerEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            jsonObject.put("serviceName", serviceName);
            jsonObject.put("functionName", functionName);
            long start = System.currentTimeMillis();
            jsonObject = thirdServer.requestDistribute(jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("调用third-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用third-server:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}