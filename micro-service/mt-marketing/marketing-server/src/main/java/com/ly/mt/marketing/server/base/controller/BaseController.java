package com.ly.mt.marketing.server.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.server.config.SpringBeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

/**
 * @创建人 zhuyh
 * @描述
 */

@RestController
@RequestMapping("/marketing/server")
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/requestDistribute", method = RequestMethod.POST)
    public JSONObject requestDistribute(@RequestBody(required = false) JSONObject jsonObject) {
        String serviceName = jsonObject.getString("serviceName");
        String functionName = jsonObject.getString("functionName");
        try {
            long start = System.currentTimeMillis();
            if (StringUtil.isEmpty(serviceName) || StringUtil.isEmpty(functionName)) {
                LOGGER.error("接口名={}或方法名={}为空", serviceName, functionName);
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }
            Object obj = SpringBeanConfig.getBean(serviceName);
            if (null == obj) {
                LOGGER.error("spring未找到bean={}", serviceName);
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }
            Method method = obj.getClass().getDeclaredMethod(functionName, new Class[]{String.class});
            jsonObject = (JSONObject) method.invoke(obj, JSONObject.toJSONString(jsonObject));
            long end = System.currentTimeMillis();
            LOGGER.info("marketing-server:接口={},方法={},执行耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("marketing-server:接口={},方法={},调用异常:", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}
