package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserWalletsMapper;
import com.ly.mt.center.data.user.service.UserWalletsService;
import com.ly.mt.center.data.user.entity.UserWallets;
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
public class UserWalletsServiceImpl extends BaseServiceImpl implements UserWalletsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserWalletsServiceImpl.class);
    @Resource
    UserWalletsMapper mapper;

    /**
     * @Description 保存UserWallets
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserWallets(JSONObject jsonObject) {
        try {
            UserWallets userWallets = JSONObject.toJavaObject(jsonObject, UserWallets.class);
            if (StringUtil.isEmpty(userWallets.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserWallets(userWallets);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletsServiceImpl.insertUserWallets出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserWallets
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserWallets(JSONObject jsonObject) {
        try {
            UserWallets userWallets = JSONObject.toJavaObject(jsonObject, UserWallets.class);
            if (StringUtil.isEmpty(userWallets.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserWallets(userWallets);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletsServiceImpl.deleteUserWallets出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserWallets
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserWallets(JSONObject jsonObject) {
        try {
            UserWallets userWallets = JSONObject.toJavaObject(jsonObject, UserWallets.class);
            if (StringUtil.isEmpty(userWallets.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserWallets(userWallets);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserWalletsServiceImpl.updateUserWalletsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserWallets
     * @Author taoye
     */
    @Override
    public ResponseJson getUserWallets(JSONObject jsonObject) {
        try {
            UserWallets userWallets = JSONObject.toJavaObject(jsonObject, UserWallets.class);
            if (StringUtil.isEmpty(userWallets.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userWallets = mapper.getUserWallets(userWallets);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userWallets);
        } catch (Exception e) {
            LOGGER.error("UserWalletsServiceImpl.getUserWallets出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}