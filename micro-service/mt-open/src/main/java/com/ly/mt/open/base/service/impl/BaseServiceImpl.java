package com.ly.mt.open.base.service.impl;

import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.mq.service.MqService;
import com.ly.mt.open.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    @Resource
    public MqService mqService;
}