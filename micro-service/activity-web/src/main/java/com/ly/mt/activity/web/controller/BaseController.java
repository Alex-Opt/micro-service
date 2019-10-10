package com.ly.mt.activity.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.web.feign.ActivityFeignClient;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import com.ly.mt.core.oss.OssServer;
import com.ly.mt.core.web.CoreController;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 *
 * 活动web层基类
 *
 * @author panjingtian
 * @date 2019/6/14 1:29 AM
 */
@Controller
public class BaseController extends CoreController {
    private final static Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private ActivityFeignClient activityServiceClient;
    @Autowired
    public OssServer ossServer;

    /**
     * 活动服务商家登录token约束名称
     */
    private static final String TOKEN_NAME = "token";

    /**
     * 获取活动商家登录token
     * 简单版本没有加密验证
     * @param request
     * @return
     */
    public String getShopToken(HttpServletRequest request){
        String token = (String) request.getAttribute(TOKEN_NAME);
        if (StringUtils.isEmpty(token)){
            return request.getHeader(TOKEN_NAME);
        }
        return null;
    }

    public JSONObject callActivityServer(ActivityMethodEnum methodEnum, JSONObject map){
        String serviceName = methodEnum.getServiceName();
        String functionName = methodEnum.getFunctionName();

        try {
            map.put("serviceName",serviceName);
            map.put("functionName",functionName);
            long start = System.currentTimeMillis();
            JSONObject obj = activityServiceClient.requestDistribute(map);
            long end = System.currentTimeMillis();
            log.info("调用user-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return obj;
        }catch (Exception e){
            log.error("调用user-server:接口={}方法={},异常={}", serviceName, functionName, e);
            return null;
        }
    }

}
