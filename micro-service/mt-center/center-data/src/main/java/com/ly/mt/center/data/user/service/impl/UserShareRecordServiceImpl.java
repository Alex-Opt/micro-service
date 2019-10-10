package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.entity.UserShareRecord;
import com.ly.mt.center.data.user.mapper.UserShareRecordMapper;
import com.ly.mt.center.data.user.service.UserShareRecordService;
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
public class UserShareRecordServiceImpl implements UserShareRecordService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserShareRecordServiceImpl.class);
    @Resource
    UserShareRecordMapper mapper;
    @Override
    public ResponseJson insertUserShareRecord(JSONObject jsonObject) {

        try {
            UserShareRecord userShareRecord = JSONObject.toJavaObject(jsonObject, UserShareRecord.class);
            if (StringUtil.isEmpty(userShareRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insert(userShareRecord);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserShareRecordServiceImpl.insertUserShareRecord出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson addUserShareRecord(JSONObject jsonObject) {

        try {
            UserShareRecord userShareRecord = JSONObject.toJavaObject(jsonObject, UserShareRecord.class);
            if (StringUtil.isEmpty(userShareRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.addUserShareRecord(Long.parseLong(userShareRecord.getUser_id()),Long.parseLong(userShareRecord.getSku_id()));
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserShareRecordServiceImpl.insertUserShareRecord出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
