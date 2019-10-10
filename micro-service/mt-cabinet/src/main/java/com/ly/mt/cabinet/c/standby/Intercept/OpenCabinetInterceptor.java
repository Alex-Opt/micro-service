package com.ly.mt.cabinet.c.standby.Intercept;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.util.AesUtils;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_OPEN_DEVICE_LOGIN_TOKEN;

/**
 * 运维人员打开柜门
 * 配置拦截器进行拦截        HandlerInterceptorAdapter
 */
@Component
//public class OpenCabinetInterceptor extends HandlerInterceptorAdapter implements ServletContextAware {
public class OpenCabinetInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(OpenCabinetInterceptor.class);
    @Resource
    private RedisService redisService;

    private static final long EXPIRETIME = 1000 * 60 * 60;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(" preHandle 运行了这里");
//        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //判断如果不是请求control方法直接返回true
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        //判断访问的control是否添加LoginRequired注解
        Intercept loginRequired = method.getMethodAnnotation(Intercept.class);
        boolean required = true;
        if (loginRequired != null) {
            log.info("运维人员开门时，方法上有注解Intercept，进入拦截");
            required = loginRequired.required();
            //如果添加了requird注解，则在消息请求头中查看token是否存在
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                log.info("运维人员开门时，token不能为空");
                response.getWriter().write(ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "您未登录,请先登录!").toString());
                return false;
            }
            log.info("运维人员打开柜门 拦截器 获取的 token----------------:" + token);

            // 从 http 请求头中取出 token,获取token的内容并解析，然后判断用户是否存在、是否登录、是否在线超时等

            String s = AesUtils.decryptEcbMode(token);
            TokenUserMessage userMessage = JSONObject.toJavaObject(JSONObject.parseObject(s), TokenUserMessage.class);
            if (userMessage == null) {
                log.info("运维人员开门时，未登录,请提醒先登录");
                response.getWriter().write(ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "您未登录,请先登录!").toString());
                return false;
            }
            String phoneNo = userMessage.getPhoneNo();
            String userId = String.valueOf(userMessage.getUserId());
            if (userId == null) {
                response.getWriter().write(ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "无效token").toString());
                log.info("运维人员开门时，token无效");
                return false;
            }
            String cacheToken = (String) redisService.get(REDIS_GZG_OPEN_DEVICE_LOGIN_TOKEN, phoneNo);
            log.info("运维人员打开柜门操作，拦截器从redis获取的登录token =  {}", cacheToken);
            if (StringUtil.isEmpty(cacheToken)) {
                response.getWriter().write(ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "用户token已过期,请重新登陆").toString());
                log.info("运维人员开门时，用户token已过期,请重新登陆");
                return false;
            }
            if (!org.apache.commons.lang.StringUtils.equals(token, cacheToken)) {
                response.getWriter().write(ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "您的账号在另一台设备上登录,您被迫下线!").toString());
                log.info("运维人员开门时，账号在另一台设备上登录,您被迫下线!");
                return false;
            }
            request.setAttribute("user_id", Long.valueOf(userId));
            request.setAttribute("phone", phoneNo);
            redisService.set(REDIS_GZG_OPEN_DEVICE_LOGIN_TOKEN, phoneNo, token, EXPIRETIME, TimeUnit.MILLISECONDS);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        log.info("运维人员打开柜门 拦截器  postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        log.info("运维人员打开柜门 拦截器  afterCompletion()");
    }


}
