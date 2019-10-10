package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserTaskMapper;
import com.ly.mt.center.data.user.service.UserTaskService;
import com.ly.mt.center.data.user.entity.UserTask;
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
public class UserTaskServiceImpl extends BaseServiceImpl implements UserTaskService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserTaskServiceImpl.class);
    @Resource
    UserTaskMapper mapper;

    /**
     * @Description 保存UserTask
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserTask(JSONObject jsonObject) {
        try {
            UserTask userTask = JSONObject.toJavaObject(jsonObject, UserTask.class);
            if (StringUtil.isEmpty(userTask.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserTask(userTask);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskServiceImpl.insertUserTask出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserTask
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserTask(JSONObject jsonObject) {
        try {
            UserTask userTask = JSONObject.toJavaObject(jsonObject, UserTask.class);
            if (StringUtil.isEmpty(userTask.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserTask(userTask);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskServiceImpl.deleteUserTask出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserTask
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserTask(JSONObject jsonObject) {
        try {
            UserTask userTask = JSONObject.toJavaObject(jsonObject, UserTask.class);
            if (StringUtil.isEmpty(userTask.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserTask(userTask);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserTaskServiceImpl.updateUserTaskById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserTask
     * @Author taoye
     */
    @Override
    public ResponseJson getUserTask(JSONObject jsonObject) {
        try {
            UserTask userTask = JSONObject.toJavaObject(jsonObject, UserTask.class);
            if (StringUtil.isEmpty(userTask.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userTask = mapper.getUserTask(userTask);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userTask);
        } catch (Exception e) {
            LOGGER.error("UserTaskServiceImpl.getUserTask出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}