package com.ly.mt.gzg.b.web.config.interceptor;

import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.JwtUtil;
import com.ly.mt.core.common.util.RedisUtil;
import com.ly.mt.gzg.b.web.config.annotation.Permission;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisServer redisServer;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Permission permission = null;
        if (handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            Permission annotation = method.getAnnotation(Permission.class);
            if (annotation != null){
                permission = annotation;
            }
        }
        log.info("注解权限Permission is null {}",(permission == null));
        if (permission != null){
            response.setCharacterEncoding("UTF-8");
            String token = request.getHeader("Authorization");
            log.info("拦截器拦截到访问请求,获取的token {}",token);
            if (token == null){
                response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_NOT_LOGIN_ERROR).toJSONString());
                return false;
            }
            /*String token = request.getHeader("token");*/
            if (StringUtils.isBlank(token)){
                response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_NOT_LOGIN_ERROR).toJSONString());
                return false;
            }else {
                Claims claims = JwtUtil.parseJWT(token, com.ly.mt.core.common.constant.gzg.Constant.BASE64KEY);
                if (claims == null){
                    response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_NOT_LOGIN_ERROR).toJSONString());
                    return false;
                }
                String userId = (String)claims.get("userId");
                if (userId == null){
                    response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_NOT_LOGIN_ERROR).toJSONString());
                    return false;
                }
                String phoneNo = (String) claims.get("phoneNo");
                //String cacheKey = Constant.USER_LOGIN_CACHE_KEY_PRE+userId+"_"+phoneNo;
                String cacheToken = redisServer.get(RedisEnum.LOGIN_TOKEN_REDIS, phoneNo);
                //String cacheToken = redisUtil.getCachByKey(Constant.USER_LOGIN_CACHE_KEY_PRE + userId + "_" + phoneNo);
                if (StringUtils.isBlank(cacheToken)){
                    response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.TOKEN_OVER_DUE).toJSONString());
                    return false;
                }
                if (!StringUtils.equals(token,cacheToken)){
                    response.getWriter().write(JsonUtil.getErrorJson(ResultCodeEnum.HAS_LOGIN_ON_OTHER_MACHINE).toJSONString());
                    return false;
                }
                request.setAttribute("userId",Long.valueOf(userId));
                request.setAttribute("phoneNo",phoneNo);
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
