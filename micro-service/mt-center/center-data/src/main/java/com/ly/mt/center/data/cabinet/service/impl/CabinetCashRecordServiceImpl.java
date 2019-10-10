package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.center.data.cabinet.entity.CabinetOptions;
import com.ly.mt.center.data.cabinet.mapper.CabinetCashRecordMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetOptionsMapper;
import com.ly.mt.center.data.cabinet.response.Option;
import com.ly.mt.center.data.cabinet.response.ResponseOptions;
import com.ly.mt.center.data.cabinet.service.CabinetCashRecordService;
import com.ly.mt.center.data.cabinet.service.CabinetOptionsService;
import com.ly.mt.center.data.user.entity.User;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.*;

@Service
public class CabinetCashRecordServiceImpl implements CabinetCashRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetCashRecordServiceImpl.class);

    @Resource
    private CabinetCashRecordMapper cabinetCashRecordMapper;

    @Override
    public ResponseJson insert(JSONObject jsonObject) {
        try {
            CabinetCashRecord record = JSONObject.toJavaObject(jsonObject, CabinetCashRecord.class);
            if (StringUtil.isEmpty(record.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetCashRecordMapper.insert(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetCashRecordServiceImpl.insert出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateById(JSONObject jsonObject) {
        try {
            CabinetCashRecord record = JSONObject.toJavaObject(jsonObject, CabinetCashRecord.class);
            if (StringUtil.isEmpty(record.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetCashRecordMapper.updateById(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetCashRecordServiceImpl.insert出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson selectRecordByUserId(JSONObject jsonObject) {
        return null;
    }

    @Override
    public ResponseJson selectRecordByUserIdToday(JSONObject jsonObject) {
        try {
            String userId= jsonObject.getString("user_id");
            String todayStartTime = jsonObject.getString("today_start_time");
            String todayEndTime = jsonObject.getString("today_end_time");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetCashRecordMapper.selectRecordByUserIdToday(userId,todayStartTime,todayEndTime));
        } catch (Exception e) {
            LOGGER.error("CabinetCashRecordServiceImpl.selectRecordByUserIdToday出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson selectRecordByStatus(JSONObject jsonObject) {
        try {
            CabinetCashRecord record = JSONObject.toJavaObject(jsonObject, CabinetCashRecord.class);
            if (StringUtil.isEmpty(record.getStatus())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "状态status不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetCashRecordMapper.selectRecordByStatus(record));
        } catch (Exception e) {
            LOGGER.error("CabinetCashRecordServiceImpl.insert出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
