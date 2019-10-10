package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetCreateRecord;
import com.ly.mt.center.data.cabinet.entity.CabinetInfo;
import com.ly.mt.center.data.cabinet.mapper.CabinetCreateRecordMapper;
import com.ly.mt.center.data.cabinet.service.CabinetCreateRecordService;
import com.ly.mt.center.data.gzg.mapper.GzgGoodsPlanMapper;
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
public class CabinetCreateRecordServiceImpl  implements CabinetCreateRecordService {

    private static final Logger log = LoggerFactory.getLogger(CabinetCreateRecordServiceImpl.class);

    @Resource
    CabinetCreateRecordMapper cabinetCreateRecordMapper;
    @Override
    public ResponseJson addCabinetCreateRecord(JSONObject jsonObject) {
        try {
            CabinetCreateRecord cabinetCreateRecord = JSONObject.toJavaObject(jsonObject, CabinetCreateRecord.class);
            if (StringUtil.isEmpty(cabinetCreateRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetCreateRecordMapper.insert(cabinetCreateRecord);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetCreateRecordServiceImpl.addCabinetCreateRecord  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
