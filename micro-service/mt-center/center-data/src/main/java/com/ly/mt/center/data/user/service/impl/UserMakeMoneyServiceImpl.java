package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserMakeMoneyMapper;
import com.ly.mt.center.data.user.service.UserMakeMoneyService;
import com.ly.mt.center.data.user.entity.UserMakeMoney;
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
public class UserMakeMoneyServiceImpl extends BaseServiceImpl implements UserMakeMoneyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserMakeMoneyServiceImpl.class);
    @Resource
    UserMakeMoneyMapper mapper;

    /**
     * @Description 保存UserMakeMoney
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserMakeMoney(JSONObject jsonObject) {
        try {
            UserMakeMoney userMakeMoney = JSONObject.toJavaObject(jsonObject, UserMakeMoney.class);
            if (StringUtil.isEmpty(userMakeMoney.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserMakeMoney(userMakeMoney);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyServiceImpl.insertUserMakeMoney出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserMakeMoney
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserMakeMoney(JSONObject jsonObject) {
        try {
            UserMakeMoney userMakeMoney = JSONObject.toJavaObject(jsonObject, UserMakeMoney.class);
            if (StringUtil.isEmpty(userMakeMoney.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserMakeMoney(userMakeMoney);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyServiceImpl.deleteUserMakeMoney出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserMakeMoney
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserMakeMoney(JSONObject jsonObject) {
        try {
            UserMakeMoney userMakeMoney = JSONObject.toJavaObject(jsonObject, UserMakeMoney.class);
            if (StringUtil.isEmpty(userMakeMoney.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserMakeMoney(userMakeMoney);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyServiceImpl.updateUserMakeMoneyById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserMakeMoney
     * @Author taoye
     */
    @Override
    public ResponseJson getUserMakeMoney(JSONObject jsonObject) {
        try {
            UserMakeMoney userMakeMoney = JSONObject.toJavaObject(jsonObject, UserMakeMoney.class);
            if (StringUtil.isEmpty(userMakeMoney.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userMakeMoney = mapper.getUserMakeMoney(userMakeMoney);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userMakeMoney);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyServiceImpl.getUserMakeMoney出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}