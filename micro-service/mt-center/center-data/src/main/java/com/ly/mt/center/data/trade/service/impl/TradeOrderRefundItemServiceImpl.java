package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderRefundItemMapper;
import com.ly.mt.center.data.trade.service.TradeOrderRefundItemService;
import com.ly.mt.center.data.trade.entity.TradeOrderRefundItem;
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
public class TradeOrderRefundItemServiceImpl extends BaseServiceImpl implements TradeOrderRefundItemService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderRefundItemServiceImpl.class);
    @Resource
    TradeOrderRefundItemMapper mapper;

    /**
     * @Description 保存TradeOrderRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderRefundItem(JSONObject jsonObject) {
        try {
            TradeOrderRefundItem tradeOrderRefundItem = JSONObject.toJavaObject(jsonObject, TradeOrderRefundItem.class);
            if (StringUtil.isEmpty(tradeOrderRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderRefundItem(tradeOrderRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundItemServiceImpl.insertTradeOrderRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderRefundItem(JSONObject jsonObject) {
        try {
            TradeOrderRefundItem tradeOrderRefundItem = JSONObject.toJavaObject(jsonObject, TradeOrderRefundItem.class);
            if (StringUtil.isEmpty(tradeOrderRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderRefundItem(tradeOrderRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundItemServiceImpl.deleteTradeOrderRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderRefundItem(JSONObject jsonObject) {
        try {
            TradeOrderRefundItem tradeOrderRefundItem = JSONObject.toJavaObject(jsonObject, TradeOrderRefundItem.class);
            if (StringUtil.isEmpty(tradeOrderRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderRefundItem(tradeOrderRefundItem);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundItemServiceImpl.updateTradeOrderRefundItemById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderRefundItem
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderRefundItem(JSONObject jsonObject) {
        try {
            TradeOrderRefundItem tradeOrderRefundItem = JSONObject.toJavaObject(jsonObject, TradeOrderRefundItem.class);
            if (StringUtil.isEmpty(tradeOrderRefundItem.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderRefundItem = mapper.getTradeOrderRefundItem(tradeOrderRefundItem);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderRefundItem);
        } catch (Exception e) {
            LOGGER.error("TradeOrderRefundItemServiceImpl.getTradeOrderRefundItem出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}