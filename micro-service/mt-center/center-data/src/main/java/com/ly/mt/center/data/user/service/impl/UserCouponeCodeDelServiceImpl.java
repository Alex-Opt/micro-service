package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserCouponeCodeDelMapper;
import com.ly.mt.center.data.user.service.UserCouponeCodeDelService;
import com.ly.mt.center.data.user.entity.UserCouponeCodeDel;
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
public class UserCouponeCodeDelServiceImpl extends BaseServiceImpl implements UserCouponeCodeDelService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserCouponeCodeDelServiceImpl.class);
    @Resource
    UserCouponeCodeDelMapper mapper;

    /**
     * @Description 保存UserCouponeCodeDel
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserCouponeCodeDel(JSONObject jsonObject) {
        try {
            UserCouponeCodeDel userCouponeCodeDel = JSONObject.toJavaObject(jsonObject, UserCouponeCodeDel.class);
            if (StringUtil.isEmpty(userCouponeCodeDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserCouponeCodeDel(userCouponeCodeDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserCouponeCodeDelServiceImpl.insertUserCouponeCodeDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserCouponeCodeDel
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserCouponeCodeDel(JSONObject jsonObject) {
        try {
            UserCouponeCodeDel userCouponeCodeDel = JSONObject.toJavaObject(jsonObject, UserCouponeCodeDel.class);
            if (StringUtil.isEmpty(userCouponeCodeDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserCouponeCodeDel(userCouponeCodeDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserCouponeCodeDelServiceImpl.deleteUserCouponeCodeDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserCouponeCodeDel
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserCouponeCodeDel(JSONObject jsonObject) {
        try {
            UserCouponeCodeDel userCouponeCodeDel = JSONObject.toJavaObject(jsonObject, UserCouponeCodeDel.class);
            if (StringUtil.isEmpty(userCouponeCodeDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserCouponeCodeDel(userCouponeCodeDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserCouponeCodeDelServiceImpl.updateUserCouponeCodeDelById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserCouponeCodeDel
     * @Author taoye
     */
    @Override
    public ResponseJson getUserCouponeCodeDel(JSONObject jsonObject) {
        try {
            UserCouponeCodeDel userCouponeCodeDel = JSONObject.toJavaObject(jsonObject, UserCouponeCodeDel.class);
            if (StringUtil.isEmpty(userCouponeCodeDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userCouponeCodeDel = mapper.getUserCouponeCodeDel(userCouponeCodeDel);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userCouponeCodeDel);
        } catch (Exception e) {
            LOGGER.error("UserCouponeCodeDelServiceImpl.getUserCouponeCodeDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}