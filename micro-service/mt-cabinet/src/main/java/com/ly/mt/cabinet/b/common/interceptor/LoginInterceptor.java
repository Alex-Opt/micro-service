package com.ly.mt.cabinet.b.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.login.service.LoginTokenService;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.util.TokenHS256;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ly.mt.core.base.entity.ResponseCode.*;

/**
* @program: mt-cabinet
* @description: 登录拦截器
* @author: wanghongliang
* @create: 2019/9/3 21:54
**/
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private YmlConfig yml;
    @Autowired
    private LoginTokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            LOGGER.info("请求url={}", request.getRequestURL());
            String uri = request.getRequestURI().toString();
            String token = request.getHeader("Authorization");//解析token
            //放行地址
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                return true;
            }
            //获取钥匙
            TokenInfo tokenInfo = TokenHS256.validToken(token);
            LOGGER.info("=======userIdStr=====:" + tokenInfo.getUserId() + "=======token====:" + token);
            if (tokenInfo==null || StringUtil.isEmpty(tokenInfo.getToken())) {
                LOGGER.info("用户id或token为空,未登录拦截");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                return false;
            }
            String storedToken =tokenService.getToken(tokenInfo.getUserId());//获取存储token
            if (StringUtils.isEmpty(storedToken)) {
                LOGGER.info("token无效,未登录拦截");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_TOKEN_INVALID_DESC)));
                return false;
            }
            if (!token.equals(storedToken)) {
                LOGGER.info("token错误,未登录拦截");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_TOKEN_INVALID_DESC)));
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
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
