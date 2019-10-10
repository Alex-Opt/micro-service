package com.ly.mt.activity.base.service.impl;

import com.ly.mt.activity.base.service.BaseService;
import com.ly.mt.core.data.activity.dao.ActivitySecurityCodeDao;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.redis.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 模块公共实现类，处理本模块公共方法
 * @Author taoye
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    @Resource
    public RedisService redisService;

    @Resource
    public ActivitySecurityCodeDao activitySecurityCodeDao;


    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }
}