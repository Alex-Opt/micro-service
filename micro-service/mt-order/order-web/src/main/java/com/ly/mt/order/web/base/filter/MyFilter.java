package com.ly.mt.order.web.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.order.web.base.config.YmlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_NOT_LOGIN;
import static com.ly.mt.core.redis.RedisKey.*;

@Component
public class MyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private YmlConfig yml;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            HttpSession session = request.getSession();
            LOGGER.info("1请求sessionId={},url={}", session.getId(), request.getRequestURL());
            LOGGER.info("1请求session——userId={}", session.getAttribute("userId"));
            LOGGER.info("1请求session——userName={}", session.getAttribute("userName"));
            LOGGER.info("1请求session——loginName={}", session.getAttribute("loginName"));
            LOGGER.info("1请求session——ipAddress={}", session.getAttribute("ipAddress"));
            LOGGER.info("1请求session——mobile={}", session.getAttribute("mobile"));
            LOGGER.info("1请求session——token={}", session.getAttribute("token"));
            String uri = request.getRequestURI();
            // 放行地址
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            //新增过滤器逻辑：微信小程序通过token关联session,为session赋值------start
            String wxAppletToken = request.getHeader("token");
            if (StringUtil.isNotEmpty(wxAppletToken)) {
                String userCache = redisService.get(REDIS_WX_LOGIN_TOKEN, wxAppletToken);
                if (StringUtil.isEmpty(userCache)) {
                    LOGGER.info("用户名或token为空,未登录拦截");
                    ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                    return;
                }
                JSONObject userJson = JSONObject.parseObject(userCache);
                String userId = userJson.getString("id");
                session.setAttribute("userId", userId);
                session.setAttribute("userName", userJson.getString("user_name"));
                session.setAttribute("loginName", userJson.getString("login_name"));
                session.setAttribute("mobile", userJson.getString("mobile"));
                session.setAttribute("token", wxAppletToken);
                String runnerTree = redisService.get(REDIS_RUNNER_TREE_ID, userId);
                session.setAttribute("runnerTree", runnerTree);
                session.setAttribute("ipAddress", RequestUtil.getIpAddress(request));
            }
            //新增过滤器逻辑：微信小程序通过token关联session,为session赋值------end
            LOGGER.info("2请求sessionId={},url={}", session.getId(), request.getRequestURL());
            LOGGER.info("2请求session——userId={}", session.getAttribute("userId"));
            LOGGER.info("2请求session——userName={}", session.getAttribute("userName"));
            LOGGER.info("2请求session——loginName={}", session.getAttribute("loginName"));
            LOGGER.info("2请求session——ipAddress={}", session.getAttribute("ipAddress"));
            LOGGER.info("2请求session——mobile={}", session.getAttribute("mobile"));
            LOGGER.info("2请求session——token={}", session.getAttribute("token"));
            // 未登录拦截
            String loginName = String.valueOf(session.getAttribute("loginName"));
            String token = String.valueOf(session.getAttribute("token"));
            LOGGER.info("=======loginName=====:" + loginName + "=======token====:" + token);
            if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(token)) {
                LOGGER.info("用户名或token为空,未登录拦截");
                notLogin(loginName, session, request, response);
                return;
            }
            String redisToken = redisService.get(REDIS_TOKEN_LOGIN_MALL_H5, loginName);
            LOGGER.info("redisToken: " + redisToken);
            if (!token.equals(redisToken)) {
                LOGGER.info("token错误,未登录拦截");
                notLogin(loginName, session, request, response);
                return;
            }
            // 登录放行
            login(redisToken, loginName, session, request, response);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
        }
    }

    /**
     * @Description 未登录拦截
     * @Author taoye
     */
    private void notLogin(String loginName, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("！！！！！！！！！！未登录拦截，销毁session，清空cookie");
        // 销毁session
        session.invalidate();
        // 删除redis
        redisService.del(REDIS_TOKEN_LOGIN_MALL_H5, loginName);
        // 删除cookie token
        Cookie tokenCookie = getTokenCookie(request);
        if (null != tokenCookie) {
            tokenCookie.setMaxAge(0);
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
        }
        ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
    }

    /**
     * @Description 登录放行
     * @Author taoye
     */
    private Object login(String token, String loginName, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 刷新session有效时间30分钟
        session.setMaxInactiveInterval(604800);
        // 刷新redis有效时间30分钟
        redisService.set(REDIS_TOKEN_LOGIN_MALL_H5, loginName, token, 10080L, TimeUnit.MINUTES);
        // 刷新cookie token有效时间30分钟
        Cookie tokenCookie = getTokenCookie(request);
        if (null == tokenCookie) {
            tokenCookie = new Cookie("token", token);
        }
        tokenCookie.setMaxAge(604800);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
        return null;
    }

    /**
     * @Description 获取返回前端的token cookie
     * @Author taoye
     */
    private Cookie getTokenCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}