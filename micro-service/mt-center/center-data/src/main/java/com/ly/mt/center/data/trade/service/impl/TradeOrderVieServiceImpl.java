package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderVieMapper;
import com.ly.mt.center.data.trade.service.TradeOrderVieService;
import com.ly.mt.center.data.trade.entity.TradeOrderVie;
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
public class TradeOrderVieServiceImpl extends BaseServiceImpl implements TradeOrderVieService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderVieServiceImpl.class);
    @Resource
    TradeOrderVieMapper mapper;

    /**
     * @Description 保存TradeOrderVie
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderVie(JSONObject jsonObject) {
        try {
            TradeOrderVie tradeOrderVie = JSONObject.toJavaObject(jsonObject, TradeOrderVie.class);
            if (StringUtil.isEmpty(tradeOrderVie.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderVie(tradeOrderVie);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderVieServiceImpl.insertTradeOrderVie出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderVie
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderVie(JSONObject jsonObject) {
        try {
            TradeOrderVie tradeOrderVie = JSONObject.toJavaObject(jsonObject, TradeOrderVie.class);
            if (StringUtil.isEmpty(tradeOrderVie.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderVie(tradeOrderVie);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderVieServiceImpl.deleteTradeOrderVie出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderVie
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderVie(JSONObject jsonObject) {
        try {
            TradeOrderVie tradeOrderVie = JSONObject.toJavaObject(jsonObject, TradeOrderVie.class);
            if (StringUtil.isEmpty(tradeOrderVie.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderVie(tradeOrderVie);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderVieServiceImpl.updateTradeOrderVieById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderVie
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderVie(JSONObject jsonObject) {
        try {
            TradeOrderVie tradeOrderVie = JSONObject.toJavaObject(jsonObject, TradeOrderVie.class);
            if (StringUtil.isEmpty(tradeOrderVie.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderVie = mapper.getTradeOrderVie(tradeOrderVie);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderVie);
        } catch (Exception e) {
            LOGGER.error("TradeOrderVieServiceImpl.getTradeOrderVie出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}