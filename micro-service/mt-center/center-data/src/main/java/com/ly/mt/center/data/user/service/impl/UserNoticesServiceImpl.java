package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserNoticesMapper;
import com.ly.mt.center.data.user.service.UserNoticesService;
import com.ly.mt.center.data.user.entity.UserNotices;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class UserNoticesServiceImpl extends BaseServiceImpl implements UserNoticesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserNoticesServiceImpl.class);
    @Resource
    UserNoticesMapper mapper;

    /**
     * @Description 保存UserNotices
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserNotices(JSONObject jsonObject) {
        try {
            UserNotices userNotices = JSONObject.toJavaObject(jsonObject, UserNotices.class);
            if (StringUtil.isEmpty(userNotices.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserNotices(userNotices);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserNoticesServiceImpl.insertUserNotices出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserNotices
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserNotices(JSONObject jsonObject) {
        try {
            UserNotices userNotices = JSONObject.toJavaObject(jsonObject, UserNotices.class);
            if (StringUtil.isEmpty(userNotices.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserNotices(userNotices);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserNoticesServiceImpl.deleteUserNotices出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserNotices
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserNotices(JSONObject jsonObject) {
        try {
            UserNotices userNotices = JSONObject.toJavaObject(jsonObject, UserNotices.class);
            if (StringUtil.isEmpty(userNotices.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserNotices(userNotices);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserNoticesServiceImpl.updateUserNoticesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserNotices
     * @Author taoye
     */
    @Override
    public ResponseJson getUserNotices(JSONObject jsonObject) {
        try {
            UserNotices userNotices = JSONObject.toJavaObject(jsonObject, UserNotices.class);
            if (StringUtil.isEmpty(userNotices.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userNotices = mapper.getUserNotices(userNotices);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userNotices);
        } catch (Exception e) {
            LOGGER.error("UserNoticesServiceImpl.getUserNotices出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}