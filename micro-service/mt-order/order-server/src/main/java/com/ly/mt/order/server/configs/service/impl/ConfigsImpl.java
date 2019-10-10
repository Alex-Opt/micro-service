package com.ly.mt.order.server.configs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.configs.mapper.ConfigsMapper;
import com.ly.mt.order.server.configs.service.ConfigsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigsImpl  extends BaseServiceImpl implements ConfigsService {
    @Resource
    ConfigsMapper configsMapper;
    @Override
    public List<JSONObject> getConfigs() {
        return configsMapper.getConfigs();
    }
}
