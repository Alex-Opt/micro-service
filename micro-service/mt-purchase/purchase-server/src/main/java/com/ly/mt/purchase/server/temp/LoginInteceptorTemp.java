package com.ly.mt.purchase.server.temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 临时模拟用户登录
 * @author xiaobei-ihmhny
 * @date 2019-06-18 14:00:00
 */
@Component
public class LoginInteceptorTemp extends HandlerInterceptorAdapter {


    private static Logger LOGGER = LoggerFactory.getLogger(LoginInteceptorTemp.class);

    /**
     * 全局用户信息配置，临时使用！！！！！
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("userId","580842177651931121");
        session.setAttribute("shopId","605628996517617800");
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);
        return true;
    }
}
