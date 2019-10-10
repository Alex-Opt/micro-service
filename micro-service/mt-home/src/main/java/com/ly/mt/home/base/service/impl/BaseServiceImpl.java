package com.ly.mt.home.base.service.impl;

import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.home.base.constant.JwtConstant;
import com.ly.mt.home.base.service.BaseService;
import com.ly.mt.home.tob.shopuser.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 模块公共实现类，处理本模块公共方法
 * @Author taoye
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    public RedisService redisService;

    @Resource
    public TokenService tokenService;

    /**
     * 获取登录用户id
     *
     * @return
     */
    @Override
    public String getLoginUserId() {
        return tokenService.getClaimsFromToken(getToken()).get(JwtConstant.CLAIM_KEY_USER, String.class);
    }

    /**
     * 获取登录用户姓名
     *
     * @return
     */
    @Override
    public String getLoginUserName() {
        return tokenService.getClaimsFromToken(getToken()).get(JwtConstant.CLAIM_KEY_USERNAME, String.class);
    }

    /**
     * 获取登录用户shopId
     *
     * @return
     */
    @Override
    public String getLoginShopId() {
        return tokenService.getClaimsFromToken(getToken()).get(JwtConstant.CLAIM_KEY_SHOP, String.class);
    }

    /**
     * 获取登录用户ip
     *
     * @return
     */
    @Override
    public String getLoginUserIp() {
        return String.valueOf(RequestUtil.getIpAddress(getHttpServletRequest()));
    }

    /**
     * 获取token
     * @return
     */
    @Override
    public String getToken() {
        return getHttpServletRequest().getHeader("Authorization");
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest;
    }


}