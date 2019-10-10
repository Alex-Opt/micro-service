package com.ly.mt.task.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.task.base.config.SpringBeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@RestController
@RequestMapping("/mt/task")
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/requestDistribute", method = RequestMethod.POST)
    public JSONObject requestDistribute(@RequestBody JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        String functionName = jsonObject.getString("functionName");
        try {
            long start = System.currentTimeMillis();
            if (StringUtil.isEmpty(serviceName) || StringUtil.isEmpty(functionName)) {
                LOGGER.error("mt-task:接口名={}或方法名={}为空", serviceName, functionName);
                return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
            }
            Object obj = SpringBeanConfig.getBean(serviceName);
            if (null == obj) {
                LOGGER.error("mt-task:spring未找到bean={}", serviceName);
                return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
            }
            Method method = obj.getClass().getDeclaredMethod(functionName, new Class[]{String.class});
            jsonObject = (JSONObject) method.invoke(obj, JSONObject.toJSONString(jsonObject));
            long end = System.currentTimeMillis();
            LOGGER.info("mt-task:接口={},方法={},执行耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("mt-task:接口={},方法={},调用异常:", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
    }
}