package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrder;
import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrderReason;
import com.ly.mt.center.data.cabinet.mapper.CabinetZgReplenishOrderReasonMapper;
import com.ly.mt.center.data.cabinet.service.CabinetZgReplenishOrderReasonService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-19 17:13
 **/
@Service
public class CabinetZgReplenishOrderReasonServiceImpl implements CabinetZgReplenishOrderReasonService {
    private static final Logger logger = LoggerFactory.getLogger(CabinetZgReplenishOrderServiceImpl.class);
    @Resource
    private CabinetZgReplenishOrderReasonMapper cabinetZgReplenishOrderReasonMapper;
    /**
     * 批量插入展柜补货单理由
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson insertBatch(JSONObject jsonObject) {
        try {
            List<CabinetZgReplenishOrderReason> cabinetZgReplenishOrderReasonList = (List<CabinetZgReplenishOrderReason>)jsonObject.get("addList");
            int i = cabinetZgReplenishOrderReasonMapper.insertBatch(cabinetZgReplenishOrderReasonList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            logger.error("CabinetZgReplenishOrderReasonServiceImpl.insertBatch:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * 查询展柜补货订单理由
     * @param
     * @return
     */
    @Override
    public ResponseJson getCabinetZgReplenishReasonByCondtion(JSONObject jsonObject) {
        try {
            CabinetZgReplenishOrderReason cabinetZgReplenishOrderReason = JSONObject.toJavaObject(jsonObject,CabinetZgReplenishOrderReason.class);
            List<CabinetZgReplenishOrderReason> list = cabinetZgReplenishOrderReasonMapper.getCabinetZgReplenishReasonByCondtion(cabinetZgReplenishOrderReason);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,list);
        } catch (Exception e) {
            logger.error("CabinetZgReplenishOrderReasonServiceImpl.getCabinetZgReplenishReasonByCondtion:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 通过补货订单理由id更新
     * @param
     * @return
     */
    @Override
    public ResponseJson updateCabinetZgReplenishReasonById(JSONObject jsonObject) {
        try {
            CabinetZgReplenishOrderReason cabinetZgReplenishOrderReason = JSONObject.toJavaObject(jsonObject,CabinetZgReplenishOrderReason.class);
            int i = cabinetZgReplenishOrderReasonMapper.updateCabinetZgReplenishReasonById(cabinetZgReplenishOrderReason);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            logger.error("CabinetZgReplenishOrderReasonServiceImpl.updateCabinetZgReplenishReasonById:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}