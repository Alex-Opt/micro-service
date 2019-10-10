package com.ly.mt.blue.tooth.base.service.impl;

import com.ly.mt.blue.tooth.base.service.BaseService;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.mq.service.MqService;
import com.ly.mt.core.redis.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 模块公共实现类，处理本模块公共方法
 * @Author whl
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    @Resource
    public RedisService redisService;
    @Resource
    public MqService mqService;

    /**
     * @Description 获取登录用户id
     * @Author whl
     */
    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String userIdStr = request.getHeader("userId");//返回userId
        return userIdStr;
    }
}