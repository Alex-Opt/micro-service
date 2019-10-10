package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserTaskFinishedMapper;
import com.ly.mt.center.data.user.service.UserTaskFinishedService;
import com.ly.mt.center.data.user.entity.UserTaskFinished;
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
public class UserTaskFinishedServiceImpl extends BaseServiceImpl implements UserTaskFinishedService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserTaskFinishedServiceImpl.class);
    @Resource
    UserTaskFinishedMapper mapper;

    /**
     * @Description 保存UserTaskFinished
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserTaskFinished(JSONObject jsonObject) {
        try {
            UserTaskFinished userTaskFinished = JSONObject.toJavaObject(jsonObject, UserTaskFinished.class);
            if (StringUtil.isEmpty(userTaskFinished.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserTaskFinished(userTaskFinished);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskFinishedServiceImpl.insertUserTaskFinished出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserTaskFinished
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserTaskFinished(JSONObject jsonObject) {
        try {
            UserTaskFinished userTaskFinished = JSONObject.toJavaObject(jsonObject, UserTaskFinished.class);
            if (StringUtil.isEmpty(userTaskFinished.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserTaskFinished(userTaskFinished);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskFinishedServiceImpl.deleteUserTaskFinished出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserTaskFinished
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserTaskFinished(JSONObject jsonObject) {
        try {
            UserTaskFinished userTaskFinished = JSONObject.toJavaObject(jsonObject, UserTaskFinished.class);
            if (StringUtil.isEmpty(userTaskFinished.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserTaskFinished(userTaskFinished);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskFinishedServiceImpl.updateUserTaskFinishedById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserTaskFinished
     * @Author taoye
     */
    @Override
    public ResponseJson getUserTaskFinished(JSONObject jsonObject) {
        try {
            UserTaskFinished userTaskFinished = JSONObject.toJavaObject(jsonObject, UserTaskFinished.class);
            if (StringUtil.isEmpty(userTaskFinished.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userTaskFinished = mapper.getUserTaskFinished(userTaskFinished);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userTaskFinished);
        } catch (Exception e) {
            LOGGER.error("UserTaskFinishedServiceImpl.getUserTaskFinished出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}