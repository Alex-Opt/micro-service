package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserFriendsMapper;
import com.ly.mt.center.data.user.service.UserFriendsService;
import com.ly.mt.center.data.user.entity.UserFriends;
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
public class UserFriendsServiceImpl extends BaseServiceImpl implements UserFriendsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserFriendsServiceImpl.class);
    @Resource
    UserFriendsMapper mapper;

    /**
     * @Description 保存UserFriends
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserFriends(JSONObject jsonObject) {
        try {
            UserFriends userFriends = JSONObject.toJavaObject(jsonObject, UserFriends.class);
            if (StringUtil.isEmpty(userFriends.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserFriends(userFriends);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFriendsServiceImpl.insertUserFriends出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserFriends
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserFriends(JSONObject jsonObject) {
        try {
            UserFriends userFriends = JSONObject.toJavaObject(jsonObject, UserFriends.class);
            if (StringUtil.isEmpty(userFriends.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserFriends(userFriends);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFriendsServiceImpl.deleteUserFriends出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserFriends
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserFriends(JSONObject jsonObject) {
        try {
            UserFriends userFriends = JSONObject.toJavaObject(jsonObject, UserFriends.class);
            if (StringUtil.isEmpty(userFriends.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserFriends(userFriends);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserFriendsServiceImpl.updateUserFriendsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserFriends
     * @Author taoye
     */
    @Override
    public ResponseJson getUserFriends(JSONObject jsonObject) {
        try {
            UserFriends userFriends = JSONObject.toJavaObject(jsonObject, UserFriends.class);
            if (StringUtil.isEmpty(userFriends.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userFriends = mapper.getUserFriends(userFriends);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userFriends);
        } catch (Exception e) {
            LOGGER.error("UserFriendsServiceImpl.getUserFriends出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}