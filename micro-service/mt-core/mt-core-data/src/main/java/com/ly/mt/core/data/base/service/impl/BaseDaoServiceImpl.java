package com.ly.mt.core.data.base.service.impl;

import com.ly.mt.core.data.base.service.BaseDaoService;
import com.ly.mt.core.redis.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseDaoServiceImpl implements BaseDaoService {
    @Resource
    public RedisService redisService;
}