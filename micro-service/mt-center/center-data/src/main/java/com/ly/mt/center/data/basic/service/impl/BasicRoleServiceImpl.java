package com.ly.mt.center.data.basic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.basic.service.BasicRoleService;
import com.ly.mt.center.data.basic.mapper.BasicRoleMapper;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @program:mt-center
 * @description:查询用户角色信息
 * @author: wanghongliang
 * @create: 2019-09-05 16:59
 **/
@Service
public class BasicRoleServiceImpl implements BasicRoleService {
    private final static Logger Logger = LoggerFactory.getLogger(BasicRoleServiceImpl.class);
    @Resource
    BasicRoleMapper mapper;

    @Override
    public ResponseJson getBasicRole(JSONObject jsonObject) {
        try {
            String userId =String.valueOf(jsonObject.get("userId"));
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户id不能为空");
            }
            String roleType = mapper.selectRoleTypeByUserId(userId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,roleType);
        } catch (Exception e) {
            Logger.error("BasicRoleServiceImpl.getBasicRole出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}