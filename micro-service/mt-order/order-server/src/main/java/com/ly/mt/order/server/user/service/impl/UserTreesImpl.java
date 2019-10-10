package com.ly.mt.order.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.user.mapper.UserTreesMapper;
import com.ly.mt.order.server.user.service.UserTreesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserTreesImpl extends BaseServiceImpl implements UserTreesService {

    @Resource
    UserTreesMapper userTreesMapper;
    @Override
    public JSONObject getByUserId(Long userId) {
        return userTreesMapper.getByUserId(userId);
    }
}
