package com.ly.mt.cabinet.b.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.annotation.AccessRequired;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.util.*;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.core.base.util.FilterUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ly.mt.core.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.ly.mt.core.redis.RedisKey.REDIS_CABINET_B_LOGIN;

@Component
public class LoginsInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private RedisService redisServer;
    @Autowired
    private YmlConfig yml;

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI().toString();
        //放行地址
        if (FilterUtil.checkFilter(uri, yml.getFilter())) {
            return true;
        }
        AccessRequired permission = null;
        if (handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            AccessRequired annotation = method.getAnnotation(AccessRequired.class);
            if (annotation != null){
                permission = annotation;
            }
        }
        log.info("注解权限Permission is null {}",(permission == null));
        if (permission != null){
            response.setCharacterEncoding("UTF-8");
            String token = request.getHeader("token");
            log.info("拦截器拦截到访问请求,获取的token {}",token);
            if (token == null){
                response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.RESULT_TOKEN_ERROR)));
                return false;
            }
            /*String token = request.getHeader("token");*/
            if (StringUtils.isBlank(token)){
                response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.RESULT_TOKEN_ERROR)));
                return false;
            }else {
                String s = AesUtils.decryptEcbMode(token);
                TokenUserMessage userMessage = JSONObject.toJavaObject(JSONObject.parseObject(s), TokenUserMessage.class);
                if (userMessage == null){
                    response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.RESULT_TOKEN_ERROR)));
                    return false;
                }
                String phoneNo = userMessage.getPhoneNo();
                String userId  = String.valueOf(userMessage.getUserId());
                if (userId == null){
                    response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.TOKEN_INVALID)));
                    return false;
                }
                String cacheToken = redisServer.get(REDIS_CABINET_B_LOGIN, phoneNo);
                if (StringUtils.isBlank(cacheToken)){
                    response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.USER_TOKEN_HAS_OVERDUE)));
                    return false;
                }
                if (!StringUtils.equals(token,cacheToken)){
                    response.getWriter().write(jsonUtil.bean2Json(Resp.createMessage(RespCode.ERRCODE_USER_LOGIN_OTHER_DEVICE)));
                    return false;
                }
                request.setAttribute("user_id",Long.valueOf(userId));
                request.setAttribute("phone",phoneNo);
            }
        }else {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }





}
