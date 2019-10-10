package com.ly.mt.open.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.open.base.config.YmlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_NOT_LOGIN;

@Component
public class AuthenticationFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
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
            String uri = request.getRequestURI().toString();
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            // 未认证拦截
            HttpSession session = request.getSession();
            String account = request.getHeader("account");
            String code = request.getHeader("code");
            LOGGER.info("=======account=====:" + account + "=======code====:" + code);
            if (StringUtil.isEmpty(account) || StringUtil.isEmpty(code)) {
                LOGGER.info("account或者code为空,未登录拦截");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                return;
            }
            String localAccount = String.valueOf(session.getAttribute("account"));
            String localCode = String.valueOf(session.getAttribute("code"));
            LOGGER.info("=======localAccount=====:" + localAccount + "=======localCode====:" + localCode);
            if (StringUtil.isEmpty(localAccount) || StringUtil.isEmpty(localCode)) {
                LOGGER.info("localAccount或者localCode为空,登录状态已失效");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                return;
            }
            if (!account.equals(localAccount) || !code.equals(localCode)) {
                LOGGER.info("参数校验错误，认证失败");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
        }
    }
}