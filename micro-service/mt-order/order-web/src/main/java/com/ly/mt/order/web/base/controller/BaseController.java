package com.ly.mt.order.web.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.method.OrderMethodEnum;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.order.web.base.fegin.OrderServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;

@Controller
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private OrderServerClient orderServer;

    /**
     * @Description 获取用户登录用户id
     * @Author taoye
     */
    public String getLoginUserId(HttpServletRequest request) {
        return String.valueOf(request.getSession().getAttribute("userId"));
    }

    /**
     * @Description order-web调用order-server服务
     * @Author taoye
     */
    public JSONObject callOrderServer(OrderMethodEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            jsonObject.put("serviceName", serviceName);
            jsonObject.put("functionName", functionName);
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (null != requestAttributes) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                HttpSession session = request.getSession();
                jsonObject.put("userId", session.getAttribute("userId"));
                jsonObject.put("userName", session.getAttribute("userName"));
                jsonObject.put("loginName", session.getAttribute("loginName"));
                jsonObject.put("shopId", session.getAttribute("shopId"));
                jsonObject.put("mobile", session.getAttribute("mobile"));
                jsonObject.put("ipAddress", session.getAttribute("ipAddress"));
                jsonObject.put("token", session.getAttribute("token"));
                jsonObject.put("runnerTree", session.getAttribute("runnerTree"));
            }
            LOGGER.info("调用order-server:接口={},方法={},参数={}", serviceName, functionName, jsonObject.toJSONString());
            long start = System.currentTimeMillis();
            jsonObject = orderServer.requestDistribute(jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("调用order-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用order-server:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}