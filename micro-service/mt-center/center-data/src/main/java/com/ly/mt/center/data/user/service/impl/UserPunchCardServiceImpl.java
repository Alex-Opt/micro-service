package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserPunchCardMapper;
import com.ly.mt.center.data.user.service.UserPunchCardService;
import com.ly.mt.center.data.user.entity.UserPunchCard;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class UserPunchCardServiceImpl extends BaseServiceImpl implements UserPunchCardService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserPunchCardServiceImpl.class);
    @Resource
    UserPunchCardMapper mapper;

    /**
     * @Description 插入UserPunchCard
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserPunchCard(JSONObject jsonObject) {
        try {
            UserPunchCard userPunchCard = JSONObject.toJavaObject(jsonObject, UserPunchCard.class);
            mapper.insertUserPunchCard(userPunchCard);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPunchCardServiceImpl.insertUserPunchCard出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除UserPunchCard
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserPunchCardById(JSONObject jsonObject) {
        try {
            UserPunchCard userPunchCard = JSONObject.toJavaObject(jsonObject, UserPunchCard.class);
            mapper.deleteUserPunchCardById(userPunchCard);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPunchCardServiceImpl.deleteUserPunchCardById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新UserPunchCard
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserPunchCardById(JSONObject jsonObject) {
        try {
            UserPunchCard userPunchCard = JSONObject.toJavaObject(jsonObject, UserPunchCard.class);
            mapper.updateUserPunchCardById(userPunchCard);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPunchCardServiceImpl.updateUserPunchCardById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询UserPunchCard
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPunchCard(JSONObject jsonObject) {
        try {
            UserPunchCard userPunchCard = JSONObject.toJavaObject(jsonObject, UserPunchCard.class);
            mapper.getUserPunchCard(userPunchCard);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPunchCardServiceImpl.getUserPunchCard出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询UserPunchCard
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPunchCardById(JSONObject jsonObject) {
        try {
            UserPunchCard userPunchCard = JSONObject.toJavaObject(jsonObject, UserPunchCard.class);
            mapper.getUserPunchCardById(userPunchCard);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPunchCardServiceImpl.getUserPunchCardById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}