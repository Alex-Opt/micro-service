package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderRefundMapper;
import com.ly.mt.center.data.trade.service.TradeOrderRefundService;
import com.ly.mt.center.data.trade.entity.TradeOrderRefund;
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
public class TradeOrderRefundServiceImpl extends BaseServiceImpl implements TradeOrderRefundService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderRefundServiceImpl.class);
    @Resource
    TradeOrderRefundMapper mapper;

    /**
     * @Description 保存TradeOrderRefund
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderRefund(JSONObject jsonObject) {
        try {
            TradeOrderRefund tradeOrderRefund = JSONObject.toJavaObject(jsonObject, TradeOrderRefund.class);
            if (StringUtil.isEmpty(tradeOrderRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderRefund(tradeOrderRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundServiceImpl.insertTradeOrderRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderRefund
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderRefund(JSONObject jsonObject) {
        try {
            TradeOrderRefund tradeOrderRefund = JSONObject.toJavaObject(jsonObject, TradeOrderRefund.class);
            if (StringUtil.isEmpty(tradeOrderRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderRefund(tradeOrderRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundServiceImpl.deleteTradeOrderRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderRefund
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderRefund(JSONObject jsonObject) {
        try {
            TradeOrderRefund tradeOrderRefund = JSONObject.toJavaObject(jsonObject, TradeOrderRefund.class);
            if (StringUtil.isEmpty(tradeOrderRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderRefund(tradeOrderRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundServiceImpl.updateTradeOrderRefundById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderRefund
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderRefund(JSONObject jsonObject) {
        try {
            TradeOrderRefund tradeOrderRefund = JSONObject.toJavaObject(jsonObject, TradeOrderRefund.class);
            if (StringUtil.isEmpty(tradeOrderRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderRefund = mapper.getTradeOrderRefund(tradeOrderRefund);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderRefund);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundServiceImpl.getTradeOrderRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}