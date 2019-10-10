package com.ly.mt.core.data.cabinet.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.cabinet.dao.CabinetCashRecordDao;
import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.core.data.cabinet.mapper.CabinetCashRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetCashRecordDaoImpl implements CabinetCashRecordDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetCashRecordDaoImpl.class);

    @Resource
    private CabinetCashRecordMapper cabinetCashRecordMapper;


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
    public List<CabinetCashRecord> getRecordByStatus(JSONObject jsonObject) {

            CabinetCashRecord record = JSONObject.toJavaObject(jsonObject, CabinetCashRecord.class);
            if (StringUtil.isEmpty(record.getStatus())) {
                return null;
            }
            return cabinetCashRecordMapper.getRecordByStatus(record);
    }
}
