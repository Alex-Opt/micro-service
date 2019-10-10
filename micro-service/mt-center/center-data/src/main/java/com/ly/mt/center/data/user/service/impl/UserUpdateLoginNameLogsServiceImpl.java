package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserUpdateLoginNameLogsMapper;
import com.ly.mt.center.data.user.service.UserUpdateLoginNameLogsService;
import com.ly.mt.center.data.user.entity.UserUpdateLoginNameLogs;
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
public class UserUpdateLoginNameLogsServiceImpl extends BaseServiceImpl implements UserUpdateLoginNameLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserUpdateLoginNameLogsServiceImpl.class);
    @Resource
    UserUpdateLoginNameLogsMapper mapper;

    /**
     * @Description 保存UserUpdateLoginNameLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserUpdateLoginNameLogs(JSONObject jsonObject) {
        try {
            UserUpdateLoginNameLogs userUpdateLoginNameLogs = JSONObject.toJavaObject(jsonObject, UserUpdateLoginNameLogs.class);
            if (StringUtil.isEmpty(userUpdateLoginNameLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserUpdateLoginNameLogs(userUpdateLoginNameLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserUpdateLoginNameLogsServiceImpl.insertUserUpdateLoginNameLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserUpdateLoginNameLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserUpdateLoginNameLogs(JSONObject jsonObject) {
        try {
            UserUpdateLoginNameLogs userUpdateLoginNameLogs = JSONObject.toJavaObject(jsonObject, UserUpdateLoginNameLogs.class);
            if (StringUtil.isEmpty(userUpdateLoginNameLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserUpdateLoginNameLogs(userUpdateLoginNameLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserUpdateLoginNameLogsServiceImpl.deleteUserUpdateLoginNameLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserUpdateLoginNameLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserUpdateLoginNameLogs(JSONObject jsonObject) {
        try {
            UserUpdateLoginNameLogs userUpdateLoginNameLogs = JSONObject.toJavaObject(jsonObject, UserUpdateLoginNameLogs.class);
            if (StringUtil.isEmpty(userUpdateLoginNameLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserUpdateLoginNameLogs(userUpdateLoginNameLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserUpdateLoginNameLogsServiceImpl.updateUserUpdateLoginNameLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserUpdateLoginNameLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getUserUpdateLoginNameLogs(JSONObject jsonObject) {
        try {
            UserUpdateLoginNameLogs userUpdateLoginNameLogs = JSONObject.toJavaObject(jsonObject, UserUpdateLoginNameLogs.class);
            if (StringUtil.isEmpty(userUpdateLoginNameLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userUpdateLoginNameLogs = mapper.getUserUpdateLoginNameLogs(userUpdateLoginNameLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userUpdateLoginNameLogs);
        } catch (Exception e) {
            LOGGER.error("UserUpdateLoginNameLogsServiceImpl.getUserUpdateLoginNameLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}