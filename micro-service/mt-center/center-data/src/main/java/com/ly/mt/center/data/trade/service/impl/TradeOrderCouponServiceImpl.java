package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderCouponMapper;
import com.ly.mt.center.data.trade.service.TradeOrderCouponService;
import com.ly.mt.center.data.trade.entity.TradeOrderCoupon;
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
public class TradeOrderCouponServiceImpl extends BaseServiceImpl implements TradeOrderCouponService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderCouponServiceImpl.class);
    @Resource
    TradeOrderCouponMapper mapper;

    /**
     * @Description 保存TradeOrderCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderCoupon(JSONObject jsonObject) {
        try {
            TradeOrderCoupon tradeOrderCoupon = JSONObject.toJavaObject(jsonObject, TradeOrderCoupon.class);
            if (StringUtil.isEmpty(tradeOrderCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderCoupon(tradeOrderCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderCouponServiceImpl.insertTradeOrderCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderCoupon(JSONObject jsonObject) {
        try {
            TradeOrderCoupon tradeOrderCoupon = JSONObject.toJavaObject(jsonObject, TradeOrderCoupon.class);
            if (StringUtil.isEmpty(tradeOrderCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderCoupon(tradeOrderCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderCouponServiceImpl.deleteTradeOrderCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderCoupon(JSONObject jsonObject) {
        try {
            TradeOrderCoupon tradeOrderCoupon = JSONObject.toJavaObject(jsonObject, TradeOrderCoupon.class);
            if (StringUtil.isEmpty(tradeOrderCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderCoupon(tradeOrderCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderCouponServiceImpl.updateTradeOrderCouponById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderCoupon(JSONObject jsonObject) {
        try {
            TradeOrderCoupon tradeOrderCoupon = JSONObject.toJavaObject(jsonObject, TradeOrderCoupon.class);
            if (StringUtil.isEmpty(tradeOrderCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderCoupon = mapper.getTradeOrderCoupon(tradeOrderCoupon);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderCoupon);
        } catch (Exception e) {
            LOGGER.error("TradeOrderCouponServiceImpl.getTradeOrderCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}