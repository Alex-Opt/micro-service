package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserMakeMoneyMonthsMapper;
import com.ly.mt.center.data.user.service.UserMakeMoneyMonthsService;
import com.ly.mt.center.data.user.entity.UserMakeMoneyMonths;
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
public class UserMakeMoneyMonthsServiceImpl extends BaseServiceImpl implements UserMakeMoneyMonthsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserMakeMoneyMonthsServiceImpl.class);
    @Resource
    UserMakeMoneyMonthsMapper mapper;

    /**
     * @Description 保存UserMakeMoneyMonths
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserMakeMoneyMonths(JSONObject jsonObject) {
        try {
            UserMakeMoneyMonths userMakeMoneyMonths = JSONObject.toJavaObject(jsonObject, UserMakeMoneyMonths.class);
            if (StringUtil.isEmpty(userMakeMoneyMonths.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserMakeMoneyMonths(userMakeMoneyMonths);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyMonthsServiceImpl.insertUserMakeMoneyMonths出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserMakeMoneyMonths
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserMakeMoneyMonths(JSONObject jsonObject) {
        try {
            UserMakeMoneyMonths userMakeMoneyMonths = JSONObject.toJavaObject(jsonObject, UserMakeMoneyMonths.class);
            if (StringUtil.isEmpty(userMakeMoneyMonths.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserMakeMoneyMonths(userMakeMoneyMonths);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyMonthsServiceImpl.deleteUserMakeMoneyMonths出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserMakeMoneyMonths
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserMakeMoneyMonths(JSONObject jsonObject) {
        try {
            UserMakeMoneyMonths userMakeMoneyMonths = JSONObject.toJavaObject(jsonObject, UserMakeMoneyMonths.class);
            if (StringUtil.isEmpty(userMakeMoneyMonths.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserMakeMoneyMonths(userMakeMoneyMonths);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyMonthsServiceImpl.updateUserMakeMoneyMonthsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserMakeMoneyMonths
     * @Author taoye
     */
    @Override
    public ResponseJson getUserMakeMoneyMonths(JSONObject jsonObject) {
        try {
            UserMakeMoneyMonths userMakeMoneyMonths = JSONObject.toJavaObject(jsonObject, UserMakeMoneyMonths.class);
            if (StringUtil.isEmpty(userMakeMoneyMonths.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userMakeMoneyMonths = mapper.getUserMakeMoneyMonths(userMakeMoneyMonths);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userMakeMoneyMonths);
        } catch (Exception e) {
            LOGGER.error("UserMakeMoneyMonthsServiceImpl.getUserMakeMoneyMonths出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}