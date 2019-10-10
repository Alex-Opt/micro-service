package com.ly.mt.cabinet.b.service.impl;

import com.ly.mt.cabinet.b.common.cache.RedisServer;
import com.ly.mt.cabinet.b.service.UserService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private RedisServer redisServer;

    @Override
    public Resp login(String phoneNo, String dynamicCode) {
        return null;
    }
}
