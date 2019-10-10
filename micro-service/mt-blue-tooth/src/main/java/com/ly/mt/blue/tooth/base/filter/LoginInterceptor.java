package com.ly.mt.blue.tooth.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.config.YmlConfig;
import com.ly.mt.blue.tooth.base.token.service.TokenService;
import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ly.mt.core.base.entity.ResponseCode.*;

/**
 * @program: my-blue-tooth
 * @description: 登录访问拦截器
 * @author: wanghongliang
 * @create: 2019-08-06 15:34
 **/
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private YmlConfig yml;

    @Autowired
    private TokenService tokenService;

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     *  * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     *
      * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            LOGGER.info("请求url={}", request.getRequestURL());
            String uri = request.getRequestURI().toString();
           // String origin = request.getHeader("Origin");
            String userIdStr = request.getHeader("userId");
            String token = request.getHeader("token");
//            if (StringUtils.hasText(origin)) {
//                response.addHeader("Access-Control-Allow-Origin", origin);
//            }
//
//            //允许带有cookie访问
//            response.addHeader("Access-Control-Allow-Credentials", "true");
//            //告诉浏览器允许跨域访问的方法
           // response.addHeader("Access-Control-Allow-Methods", "*");
//            //告诉浏览器允许带有Content-Type,header1,header2头的请求访问
           //response.addHeader("Access-Control-Allow-Headers", "Content-Type,header1,header2");
            //设置支持所有的自定义请求头
//            String headers = request.getHeader("Access-Control-Request-Headers");
//            if (StringUtils.hasText(headers)){
//                response.addHeader("Access-Control-Allow-Headers", headers);
//            }
//            //告诉浏览器缓存OPTIONS预检请求1小时,避免非简单请求每次发送预检请求,提升性能
//            response.addHeader("Access-Control-Max-Age", "3600");
            //放行地址
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                return true;
            }

            LOGGER.info("=======userIdStr=====:" + userIdStr + "=======token====:" + token);
            if (StringUtil.isEmpty(userIdStr) || StringUtil.isEmpty(token)) {
                LOGGER.info("用户id或token为空,未登录拦截");
                ResponseUtil.outPrint(response, JSONObject.toJSONString(ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN)));
                return false;
            }
            String storedToken =tokenService.getToken(userIdStr);//获取存储token
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
            // TODO 登录放行
            return true;
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
        }
        return true;
    }
}