package com.ly.mt.purchase.server.base.service.impl;

import com.ly.mt.core.server.service.impl.CoreServiceImpl;
import com.ly.mt.core.sms.SmsServer;
import com.ly.mt.purchase.server.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl extends CoreServiceImpl implements BaseService {
    @Resource
    public SmsServer smsServer;


}