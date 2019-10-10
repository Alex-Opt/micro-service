package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.entity.UserCertification;
import com.ly.mt.center.data.user.mapper.UserCertificationMapper;
import com.ly.mt.center.data.user.service.UserCertificationService;
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
public class UserCertificationServiceImpl implements UserCertificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserCertificationServiceImpl.class);
    @Resource
    private UserCertificationMapper mapper;

    @Override
    public ResponseJson insertUserCertification(JSONObject jsonObject) {
        try {
            UserCertification userCertification = JSONObject.toJavaObject(jsonObject, UserCertification.class);
            if (StringUtil.isEmpty(userCertification.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insert(userCertification);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserCertificationServiceImpl.insertUserCertification出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 认证通过
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateUserCertification(JSONObject jsonObject) {
        try {
            UserCertification userCertification = JSONObject.toJavaObject(jsonObject, UserCertification.class);
            if (StringUtil.isEmpty(userCertification.getUser_id()) && StringUtil.isEmpty(userCertification.getStatus())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户编号和认证状态不能为空");
            }
            UserCertification userCertification1 = mapper.selectByUserId(userCertification.getUser_id());
            userCertification1.setStatus(userCertification.getStatus());
            userCertification1.setModify_time(userCertification.getModify_time());
            userCertification1.setMobile(userCertification.getMobile());
            mapper.updateByPrimaryKey(userCertification);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserCertificationServiceImpl.updateUserCertification:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 查看认证状态
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson selectUserCertification(JSONObject jsonObject) {
        try {
            UserCertification userCertification = JSONObject.toJavaObject(jsonObject, UserCertification.class);
            if (StringUtil.isEmpty(userCertification.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户编号不能为空");
            }
            UserCertification userCertification1 = mapper.selectByUserId(userCertification.getUser_id());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userCertification1);
        } catch (Exception e) {
            LOGGER.error("UserCertificationServiceImpl.selectUserCertification:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserCertificationByIdCardMobileName(JSONObject jsonObject) {
        try {
            UserCertification userCertification = JSONObject.toJavaObject(jsonObject, UserCertification.class);
            if (StringUtil.isEmpty(userCertification.getMobile()) ||
                    StringUtil.isEmpty(userCertification.getCard_id()) ||
                    StringUtil.isEmpty(userCertification.getCard_name())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "真实姓名，身份证号，手机号不能为空");
            }
            UserCertification userCertification1 = mapper.getUserCertificationByIdCardMobileName(userCertification);
            if (userCertification1 != null) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userCertification1);
            } else {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
            }
        } catch (Exception e) {
            LOGGER.error("UserCertificationServiceImpl.selectUserCertification:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson selectUserCertificationByCardId(JSONObject jsonObject) {
        try {
            String cardId = jsonObject.getString("cardId");
            UserCertification userCertification = mapper.getUserCertificationByIdCard(cardId);
            if (userCertification != null && StringUtil.isNotEmpty(userCertification.getId())) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, true);
            } else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, false);
            }
        } catch (Exception e) {
            LOGGER.error("UserCertificationServiceImpl.selectUserCertificationByCardId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
