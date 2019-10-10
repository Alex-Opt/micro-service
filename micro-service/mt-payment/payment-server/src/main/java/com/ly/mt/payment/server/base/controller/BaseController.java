package com.ly.mt.payment.server.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.payment.server.base.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;

@RestController
@RequestMapping("/payment/server")
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/requestDistribute", method = RequestMethod.POST)
    public JSONObject requestDistribute(@RequestBody JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        String functionName = jsonObject.getString("functionName");
        try {
            long start = System.currentTimeMillis();
            if (StringUtil.isEmpty(serviceName) || StringUtil.isEmpty(functionName)) {
                LOGGER.error("接口名={}或方法名={}为空", serviceName, functionName);
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }
            Object obj = SpringBeanUtil.getBean(serviceName);
            if (null == obj) {
                LOGGER.error("spring未找到bean={}", serviceName);
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }
            Method method = obj.getClass().getDeclaredMethod(functionName, new Class[]{String.class});
            jsonObject = (JSONObject) method.invoke(obj, JSONObject.toJSONString(jsonObject));
            long end = System.currentTimeMillis();
            LOGGER.info("payment-server:接口={},方法={},执行耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("payment-server:接口={},方法={},调用异常:", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}