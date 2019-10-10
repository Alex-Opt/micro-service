package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeInvoiceMapper;
import com.ly.mt.center.data.trade.service.TradeInvoiceService;
import com.ly.mt.center.data.trade.entity.TradeInvoice;
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
public class TradeInvoiceServiceImpl extends BaseServiceImpl implements TradeInvoiceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeInvoiceServiceImpl.class);
    @Resource
    TradeInvoiceMapper mapper;

    /**
     * @Description 保存TradeInvoice
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeInvoice(JSONObject jsonObject) {
        try {
            TradeInvoice tradeInvoice = JSONObject.toJavaObject(jsonObject, TradeInvoice.class);
            if (StringUtil.isEmpty(tradeInvoice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeInvoice(tradeInvoice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeInvoiceServiceImpl.insertTradeInvoice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeInvoice
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeInvoice(JSONObject jsonObject) {
        try {
            TradeInvoice tradeInvoice = JSONObject.toJavaObject(jsonObject, TradeInvoice.class);
            if (StringUtil.isEmpty(tradeInvoice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeInvoice(tradeInvoice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeInvoiceServiceImpl.deleteTradeInvoice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeInvoice
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeInvoice(JSONObject jsonObject) {
        try {
            TradeInvoice tradeInvoice = JSONObject.toJavaObject(jsonObject, TradeInvoice.class);
            if (StringUtil.isEmpty(tradeInvoice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeInvoice(tradeInvoice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeInvoiceServiceImpl.updateTradeInvoiceById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeInvoice
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeInvoice(JSONObject jsonObject) {
        try {
            TradeInvoice tradeInvoice = JSONObject.toJavaObject(jsonObject, TradeInvoice.class);
            if (StringUtil.isEmpty(tradeInvoice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeInvoice = mapper.getTradeInvoice(tradeInvoice);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeInvoice);
        } catch (Exception e) {
            LOGGER.error("TradeInvoiceServiceImpl.getTradeInvoice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}