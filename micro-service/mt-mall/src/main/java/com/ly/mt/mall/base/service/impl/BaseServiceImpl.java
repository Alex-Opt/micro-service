package com.ly.mt.mall.base.service.impl;

import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.mq.service.MqCallbackService;
import com.ly.mt.core.mq.service.MqService;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.mall.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 模块公共接口，处理本模块公共方法
 *
 * @author taoye
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    @Autowired
    public RedisService redisService;
    @Autowired
    public MqService mqService;
    @Autowired
    public MqCallbackService mqCallbackService;

    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(sra, "sra must not be null");
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }

    @Override
    public String getLoginUserName() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(sra, "sra must not be null");
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userName"));
    }

    @Override
    public String getLoginUserIp() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(sra, "sra must not be null");
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("ipAddress"));
    }
}