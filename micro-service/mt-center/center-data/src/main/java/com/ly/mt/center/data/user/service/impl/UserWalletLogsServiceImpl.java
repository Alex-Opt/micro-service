package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserWalletLogsMapper;
import com.ly.mt.center.data.user.service.UserWalletLogsService;
import com.ly.mt.center.data.user.entity.UserWalletLogs;
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
public class UserWalletLogsServiceImpl extends BaseServiceImpl implements UserWalletLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserWalletLogsServiceImpl.class);
    @Resource
    UserWalletLogsMapper mapper;

    /**
     * @Description 保存UserWalletLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserWalletLogs(JSONObject jsonObject) {
        try {
            UserWalletLogs userWalletLogs = JSONObject.toJavaObject(jsonObject, UserWalletLogs.class);
            if (StringUtil.isEmpty(userWalletLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserWalletLogs(userWalletLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletLogsServiceImpl.insertUserWalletLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserWalletLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserWalletLogs(JSONObject jsonObject) {
        try {
            UserWalletLogs userWalletLogs = JSONObject.toJavaObject(jsonObject, UserWalletLogs.class);
            if (StringUtil.isEmpty(userWalletLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserWalletLogs(userWalletLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletLogsServiceImpl.deleteUserWalletLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserWalletLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserWalletLogs(JSONObject jsonObject) {
        try {
            UserWalletLogs userWalletLogs = JSONObject.toJavaObject(jsonObject, UserWalletLogs.class);
            if (StringUtil.isEmpty(userWalletLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserWalletLogs(userWalletLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletLogsServiceImpl.updateUserWalletLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserWalletLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getUserWalletLogs(JSONObject jsonObject) {
        try {
            UserWalletLogs userWalletLogs = JSONObject.toJavaObject(jsonObject, UserWalletLogs.class);
            if (StringUtil.isEmpty(userWalletLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userWalletLogs = mapper.getUserWalletLogs(userWalletLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userWalletLogs);
        } catch (Exception e) {
            LOGGER.error("UserWalletLogsServiceImpl.getUserWalletLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}