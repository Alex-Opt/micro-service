package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserRealsMapper;
import com.ly.mt.center.data.user.service.UserRealsService;
import com.ly.mt.center.data.user.entity.UserReals;
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
public class UserRealsServiceImpl extends BaseServiceImpl implements UserRealsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserRealsServiceImpl.class);
    @Resource
    UserRealsMapper mapper;

    /**
     * @Description 保存UserReals
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserReals(JSONObject jsonObject) {
        try {
            UserReals userReals = JSONObject.toJavaObject(jsonObject, UserReals.class);
            if (StringUtil.isEmpty(userReals.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserReals(userReals);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserRealsServiceImpl.insertUserReals出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserReals
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserReals(JSONObject jsonObject) {
        try {
            UserReals userReals = JSONObject.toJavaObject(jsonObject, UserReals.class);
            if (StringUtil.isEmpty(userReals.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserReals(userReals);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserRealsServiceImpl.deleteUserReals出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserReals
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserReals(JSONObject jsonObject) {
        try {
            UserReals userReals = JSONObject.toJavaObject(jsonObject, UserReals.class);
            if (StringUtil.isEmpty(userReals.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserReals(userReals);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserRealsServiceImpl.updateUserRealsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserReals
     * @Author taoye
     */
    @Override
    public ResponseJson getUserReals(JSONObject jsonObject) {
        try {
            UserReals userReals = JSONObject.toJavaObject(jsonObject, UserReals.class);
            if (StringUtil.isEmpty(userReals.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userReals = mapper.getUserReals(userReals);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userReals);
        } catch (Exception e) {
            LOGGER.error("UserRealsServiceImpl.getUserReals出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}