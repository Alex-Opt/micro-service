package com.ly.mt.home.base.filter;

import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.base.config.YmlConfig;
import com.ly.mt.home.tob.shopuser.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Component
public class MyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private YmlConfig yml;

    @Autowired
    private TokenService tokenService;

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
            LOGGER.info("请求sessionId={},url={}", session.getId(), request.getRequestURL());
            String uri = request.getRequestURI().toString();
            // 放行地址
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
        }
    }
}