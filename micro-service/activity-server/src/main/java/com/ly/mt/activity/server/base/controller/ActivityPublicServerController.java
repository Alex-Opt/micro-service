package com.ly.mt.activity.server.base.controller;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.server.config.SpringBeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

/**
 * @description
 *  活动模块对外服务
 * @author panjingtian
 * @date 2019/6/14 1:03 AM
 */
@RestController
@RequestMapping("/activity/public/server")
public class ActivityPublicServerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityPublicServerController.class);

    @PostMapping("/requestDistribute")
    public JSONObject requestDistribute(@RequestBody JSONObject jsonObject){
        String serviceName = (String) jsonObject.get("serviceName");
        String functionName = (String) jsonObject.get("functionName");
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
            Method method = obj.getClass().getDeclaredMethod(functionName,JSONObject.class);
            jsonObject = (JSONObject) method.invoke(obj,jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("user-server:接口={},方法={},执行耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("user-server:接口={},方法={},调用异常:", serviceName, functionName, e);
            return  JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

}
