package com.ly.mt.mis.base.filter;


import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.redis.RedisKey.REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME;

/**
 * 请求拦截过滤
 *
 * @author taoye
 */
@Component
public class MyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    private final static String FILTER = "/lib,/css,/js,/images,/mis/login/browser,/mis/login/login";
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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            HttpSession session = request.getSession();
            LOGGER.info("mt-mis-filter:sessionId={},url={}", session.getId(), request.getRequestURL());
            // 放行地址
            String uri = request.getRequestURI().toString();
            if (FilterUtil.checkFilter(uri, FILTER)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            // 未登录跳转登录页面
            String loginName = String.valueOf(session.getAttribute("loginName"));
            String token = String.valueOf(session.getAttribute("token"));
            String loginUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/mis/login/login";
            if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(token)) {
                session.invalidate();// 销毁当前session
                ResponseUtil.outPrint(response, "<script type='text/javascript'>window.parent.location='" + loginUrl + "'</script>");
                return;
            }
            String redisToken = redisService.get(REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME, loginName);
            if (!token.equals(redisToken)) {
                session.invalidate();// 销毁当前session
                ResponseUtil.outPrint(response, "<script type='text/javascript'>window.parent.location='" + loginUrl + "'</script>");
                return;
            }
            // 刷新有效时间30分钟
            session.setMaxInactiveInterval(18000);
            // 刷新登录状态有效时间30分钟
            redisService.set(REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME, loginName, token, 30L, TimeUnit.MINUTES);
            String mainUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/mis/main/main";
            if ("/".equals(uri)) {
                ResponseUtil.outPrint(response, "<script type='text/javascript'>window.parent.location='" + mainUrl + "'</script>");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("mt-mis-filter:执行异常:", e);
        }
    }
}