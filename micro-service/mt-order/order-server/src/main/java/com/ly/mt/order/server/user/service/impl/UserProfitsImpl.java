package com.ly.mt.order.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.user.mapper.UserProfitsMapper;
import com.ly.mt.order.server.user.service.UserProfitsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserProfitsImpl  extends BaseServiceImpl implements UserProfitsService {
    @Resource
    UserProfitsMapper userProfitsMapper;
    @Override
    public JSONObject getByUserId(Long userId) {
        return userProfitsMapper.getByUserId(userId);
    }

    @Override
    public JSONObject getUserById(Long userId) {
        return userProfitsMapper.getUserById(userId);
    }

    @Override
    public int updateRefundProfits(JSONObject jsonObject) {
        return userProfitsMapper.updateRefundProfits(jsonObject);
    }
}
