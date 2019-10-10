package com.ly.mt;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 拦截器
 * @Author taoye
 * @Date 2018/01/22
 **/

@Component
public class Filter extends ZuulFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(Filter.class);

    /**
     * 过滤器的类型
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤的顺序，数字越大越靠后
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * @Description 拦截地址过滤，true过滤，false不过滤
     * @Author taoye
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Description 未登录拦截
     * @Author taoye
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            HttpSession session = request.getSession();
            LOGGER.info("请求sessionId={},url={}", session.getId(), request.getRequestURL());
            return null;
        } catch (Exception e) {
            LOGGER.error("zuul-filter出错:", e);
            return null;
        }
    }
}