package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderMqLogMapper;
import com.ly.mt.center.data.trade.service.TradeOrderMqLogService;
import com.ly.mt.center.data.trade.entity.TradeOrderMqLog;
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
public class TradeOrderMqLogServiceImpl extends BaseServiceImpl implements TradeOrderMqLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderMqLogServiceImpl.class);
    @Resource
    TradeOrderMqLogMapper mapper;

    /**
     * @Description 保存TradeOrderMqLog
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderMqLog(JSONObject jsonObject) {
        try {
            TradeOrderMqLog tradeOrderMqLog = JSONObject.toJavaObject(jsonObject, TradeOrderMqLog.class);
            if (StringUtil.isEmpty(tradeOrderMqLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderMqLog(tradeOrderMqLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderMqLogServiceImpl.insertTradeOrderMqLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderMqLog
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderMqLog(JSONObject jsonObject) {
        try {
            TradeOrderMqLog tradeOrderMqLog = JSONObject.toJavaObject(jsonObject, TradeOrderMqLog.class);
            if (StringUtil.isEmpty(tradeOrderMqLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderMqLog(tradeOrderMqLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderMqLogServiceImpl.deleteTradeOrderMqLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderMqLog
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderMqLog(JSONObject jsonObject) {
        try {
            TradeOrderMqLog tradeOrderMqLog = JSONObject.toJavaObject(jsonObject, TradeOrderMqLog.class);
            if (StringUtil.isEmpty(tradeOrderMqLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderMqLog(tradeOrderMqLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderMqLogServiceImpl.updateTradeOrderMqLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderMqLog
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderMqLog(JSONObject jsonObject) {
        try {
            TradeOrderMqLog tradeOrderMqLog = JSONObject.toJavaObject(jsonObject, TradeOrderMqLog.class);
            if (StringUtil.isEmpty(tradeOrderMqLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderMqLog = mapper.getTradeOrderMqLog(tradeOrderMqLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderMqLog);
        } catch (Exception e) {
            LOGGER.error("TradeOrderMqLogServiceImpl.getTradeOrderMqLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}