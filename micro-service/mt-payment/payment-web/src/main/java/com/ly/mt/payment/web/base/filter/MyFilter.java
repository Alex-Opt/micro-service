package com.ly.mt.payment.web.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.redis.RedisKey.REDIS_RUNNER_TREE_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_WX_LOGIN_TOKEN;

@Component
public class MyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private RedisService redisService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            HttpSession session = request.getSession();
            LOGGER.info("请求sessionId={},url={}", session.getId(), request.getRequestURL());
            LOGGER.info("请求session——userId={}", session.getAttribute("userId"));
            LOGGER.info("请求session——userName={}", session.getAttribute("userName"));
            LOGGER.info("请求session——loginName={}", session.getAttribute("loginName"));
            LOGGER.info("请求session——ipAddress={}", session.getAttribute("ipAddress"));
            LOGGER.info("请求session——mobile={}", session.getAttribute("mobile"));
            LOGGER.info("请求session——token={}", session.getAttribute("token"));
            //新增过滤器逻辑：微信小程序通过token关联session,为session赋值------start
            String wxAppletToken = request.getHeader("token");
            if (StringUtil.isNotEmpty(wxAppletToken)) {
                String userCache = redisService.get(REDIS_WX_LOGIN_TOKEN, wxAppletToken);
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
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
        }
    }
}