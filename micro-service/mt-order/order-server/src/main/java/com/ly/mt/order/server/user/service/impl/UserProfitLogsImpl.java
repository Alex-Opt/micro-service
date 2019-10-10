package com.ly.mt.order.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.user.mapper.UserProfitLogsMapper;
import com.ly.mt.order.server.user.service.UserProfitLogsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserProfitLogsImpl extends BaseServiceImpl implements UserProfitLogsService {
    @Resource
    UserProfitLogsMapper userProfitLogsMapper;
    @Override
    public int addBatchUserProfitLogs(List<JSONObject> jsonObjectList) {
        return userProfitLogsMapper.addBatchUserProfitLogs(jsonObjectList);
    }
}
