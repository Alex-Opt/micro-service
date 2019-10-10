package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfit;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfitLog;
import com.ly.mt.center.data.cabinet.mapper.CabinetStoreProfitLogMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetStoreProfitMapper;
import com.ly.mt.center.data.cabinet.service.CabinetStoreProfitLogService;
import com.ly.mt.center.data.cabinet.service.CabinetStoreProfitService;
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
public class CabinetStoreProfitLogServiceImpl extends BaseServiceImpl implements CabinetStoreProfitLogService {

    private static final Logger log = LoggerFactory.getLogger(CabinetStoreProfitLogServiceImpl.class);

    @Resource
    private CabinetStoreProfitLogMapper cabinetStoreProfitLogMapper;

    @Override
    public ResponseJson insertCabinetStoreProfitLog(JSONObject jsonObject) {
        try {
            CabinetStoreProfitLog cabinetStoreProfitLog = JSONObject.toJavaObject(jsonObject, CabinetStoreProfitLog.class);
            if (StringUtil.isEmpty(cabinetStoreProfitLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetStoreProfitLogMapper.insert(cabinetStoreProfitLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetStoreProfitLogServiceImpl.insertCabinetStoreProfitLog 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getCabinetStoreProfitLog(JSONObject jsonObject) {
        try {
            String gzg_order_id = jsonObject.getString("gzg_order_id");
            CabinetStoreProfitLog cabinetStoreProfitLog = cabinetStoreProfitLogMapper.getCabinetStoreProfitLog(gzg_order_id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetStoreProfitLog);
        } catch (Exception e) {
            log.error("CabinetStoreProfitLogServiceImpl.getCabinetStoreProfitLog 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}
