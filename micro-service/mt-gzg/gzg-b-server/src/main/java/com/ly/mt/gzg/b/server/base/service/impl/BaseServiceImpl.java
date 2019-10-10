package com.ly.mt.gzg.b.server.base.service.impl;

import com.ly.mt.core.server.service.impl.CoreServiceImpl;
import com.ly.mt.gzg.b.server.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl extends CoreServiceImpl implements BaseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

}