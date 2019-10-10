package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserProfitsMapper;
import com.ly.mt.center.data.user.service.UserProfitsService;
import com.ly.mt.center.data.user.entity.UserProfits;
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
public class UserProfitsServiceImpl extends BaseServiceImpl implements UserProfitsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserProfitsServiceImpl.class);
    @Resource
    UserProfitsMapper mapper;

    /**
     * @Description 保存UserProfits
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserProfits(JSONObject jsonObject) {
        try {
            UserProfits userProfits = JSONObject.toJavaObject(jsonObject, UserProfits.class);
            if (StringUtil.isEmpty(userProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserProfits(userProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserProfitsServiceImpl.insertUserProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserProfits
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserProfits(JSONObject jsonObject) {
        try {
            UserProfits userProfits = JSONObject.toJavaObject(jsonObject, UserProfits.class);
            if (StringUtil.isEmpty(userProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserProfits(userProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserProfitsServiceImpl.deleteUserProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserProfits
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserProfits(JSONObject jsonObject) {
        try {
            UserProfits userProfits = JSONObject.toJavaObject(jsonObject, UserProfits.class);
            if (StringUtil.isEmpty(userProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserProfits(userProfits);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserProfitsServiceImpl.updateUserProfitsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserProfits
     * @Author taoye
     */
    @Override
    public ResponseJson getUserProfits(JSONObject jsonObject) {
        try {
            UserProfits userProfits = JSONObject.toJavaObject(jsonObject, UserProfits.class);
            if (StringUtil.isEmpty(userProfits.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userProfits = mapper.getUserProfits(userProfits);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userProfits);
        } catch (Exception e) {
            LOGGER.error("UserProfitsServiceImpl.getUserProfits出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}