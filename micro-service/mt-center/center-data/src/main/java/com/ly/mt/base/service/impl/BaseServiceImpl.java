package com.ly.mt.base.service.impl;

import com.ly.mt.core.redis.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl {
    @Resource
    public RedisService redisService;
}