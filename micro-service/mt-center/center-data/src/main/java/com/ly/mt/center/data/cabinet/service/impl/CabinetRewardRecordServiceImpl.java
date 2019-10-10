package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.center.data.cabinet.entity.CabinetRewardRecord;
import com.ly.mt.center.data.cabinet.mapper.CabinetReplenishRewardMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetRewardRecordMapper;
import com.ly.mt.center.data.cabinet.service.CabinetRewardRecordService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 *
 * 奖励明细数据中心层
 */
@Service
public class CabinetRewardRecordServiceImpl extends BaseServiceImpl implements CabinetRewardRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetRewardRecordServiceImpl.class);
    @Resource
    private CabinetRewardRecordMapper mapper;

    @Override
    public ResponseJson insert(JSONObject jsonObject) {
        try {
            CabinetRewardRecord record = JSONObject.toJavaObject(jsonObject, CabinetRewardRecord.class);
            if (StringUtil.isEmpty(record.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insert(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.insert:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryCabinetRewardRecordByStoreId(JSONObject jsonObject) {
        try {
            String store_id = jsonObject.getString("store_id");
            if (StringUtil.isEmpty(store_id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "store_id不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.queryRecordByStoreId(store_id));
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.queryCabinetRewardRecordByStoreId:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryCabinetRewardRecordById(JSONObject jsonObject) {
        try {
            CabinetRewardRecord record = JSONObject.toJavaObject(jsonObject, CabinetRewardRecord.class);
            if (StringUtil.isEmpty(record.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.queryRecordById(record.getId()));
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.queryCabinetRewardRecordById:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateCabinetRewardRecordById(JSONObject jsonObject) {
        try {
            CabinetRewardRecord record = JSONObject.toJavaObject(jsonObject, CabinetRewardRecord.class);
            if (StringUtil.isEmpty(record.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.updateRecordById(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.updateCabinetRewardRecordById:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryCabinetRewardRecordByCreateTime(JSONObject jsonObject) {
        try {
            CabinetRewardRecord record = JSONObject.toJavaObject(jsonObject, CabinetRewardRecord.class);
            if (StringUtil.isEmpty(record.getCreate_time())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "create_time不能为空");
            }
            mapper.queryRecordListByCreateTime(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.queryCabinetRewardRecordByCreateTime:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateCabinetRewardRecordByIdArray(JSONObject jsonObject) {
        try {
            String ids = jsonObject.getString("ids");
            String update_time = jsonObject.getString("update_time");
            if (StringUtil.isEmpty(ids)||StringUtil.isEmpty(update_time)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "ids和update_time不能为空");
            }
            String[] idArray = ids.split(",");
            mapper.updateRewardByIdArray(idArray,update_time);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetRewardRecordServiceImpl.updateCabinetRewardRecordByIdArray:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}
