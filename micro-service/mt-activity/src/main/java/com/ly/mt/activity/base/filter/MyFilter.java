package com.ly.mt.activity.base.filter;

import com.ly.mt.activity.base.config.YmlConfig;
import com.ly.mt.core.base.util.FilterUtil;
import com.ly.mt.core.base.util.RequestUtil;
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

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.redis.RedisKey.REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_CATEGORY;

/**
 * 活动页模块的拦截器比较特殊。因为大都没有登录太直接下单，所以目前可以说是没有任何拦截措施。
 */
@Component
public class MyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private YmlConfig yml;

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
            LOGGER.info("请求sessionId={},url={}", session.getId(), request.getRequestURL());
            String uri = request.getRequestURI();
            // 放行地址
            if (FilterUtil.checkFilter(uri, yml.getFilter())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            //对于开发的活动页接口，增加一个请求频次的限制逻辑。通过ip+接口地址作为key,设置几秒的生命周期。只有获取不到时，才放行。
            String ipAddress = RequestUtil.getIpAddress(request);
            LOGGER.info("---------------------------------------当前请求的ip地址：{}", ipAddress);
            LOGGER.info("---------------------------------------请求的接口地址：{}", uri);
            String url = uri.replace("/", "_");
            String requestRecord = redisService.get(REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_CATEGORY, url + "_" + ipAddress);
            if (StringUtil.isNotEmpty(requestRecord)) {
                LOGGER.info("-----------------------------------------操作过于频繁，请过个3-5秒在试试~");
                return;
            } else {
                //设置一个6秒的缓存拦截
                redisService.set(REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_CATEGORY, url + "_" + ipAddress, ipAddress, 6L, TimeUnit.SECONDS);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error("filter执行异常:", e);
            ResponseUtil.outPrint(response, ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR).toString());
        }
    }
}