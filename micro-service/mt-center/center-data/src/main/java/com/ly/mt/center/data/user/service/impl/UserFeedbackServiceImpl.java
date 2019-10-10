package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserFeedbackMapper;
import com.ly.mt.center.data.user.service.UserFeedbackService;
import com.ly.mt.center.data.user.entity.UserFeedback;
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
public class UserFeedbackServiceImpl extends BaseServiceImpl implements UserFeedbackService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserFeedbackServiceImpl.class);
    @Resource
    UserFeedbackMapper mapper;

    /**
     * @Description 保存UserFeedback
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserFeedback(JSONObject jsonObject) {
        try {
            UserFeedback userFeedback = JSONObject.toJavaObject(jsonObject, UserFeedback.class);
            if (StringUtil.isEmpty(userFeedback.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserFeedback(userFeedback);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFeedbackServiceImpl.insertUserFeedback出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserFeedback
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserFeedback(JSONObject jsonObject) {
        try {
            UserFeedback userFeedback = JSONObject.toJavaObject(jsonObject, UserFeedback.class);
            if (StringUtil.isEmpty(userFeedback.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserFeedback(userFeedback);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFeedbackServiceImpl.deleteUserFeedback出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserFeedback
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserFeedback(JSONObject jsonObject) {
        try {
            UserFeedback userFeedback = JSONObject.toJavaObject(jsonObject, UserFeedback.class);
            if (StringUtil.isEmpty(userFeedback.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserFeedback(userFeedback);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFeedbackServiceImpl.updateUserFeedbackById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserFeedback
     * @Author taoye
     */
    @Override
    public ResponseJson getUserFeedback(JSONObject jsonObject) {
        try {
            UserFeedback userFeedback = JSONObject.toJavaObject(jsonObject, UserFeedback.class);
            if (StringUtil.isEmpty(userFeedback.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userFeedback = mapper.getUserFeedback(userFeedback);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userFeedback);
        } catch (Exception e) {
            LOGGER.error("UserFeedbackServiceImpl.getUserFeedback出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}