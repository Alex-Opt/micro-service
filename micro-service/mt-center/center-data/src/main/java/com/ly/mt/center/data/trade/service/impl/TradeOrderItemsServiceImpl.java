package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.mapper.TradeOrderItemsMapper;
import com.ly.mt.center.data.trade.service.TradeOrderItemsService;
import com.ly.mt.center.data.trade.entity.TradeOrderItems;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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

@Service
public class TradeOrderItemsServiceImpl extends BaseServiceImpl implements TradeOrderItemsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderItemsServiceImpl.class);
    @Resource
    TradeOrderItemsMapper mapper;

    /**
     * @Description 保存TradeOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrderItems(JSONObject jsonObject) {
        try {
            TradeOrderItems tradeOrderItems = JSONObject.toJavaObject(jsonObject, TradeOrderItems.class);
            if (StringUtil.isEmpty(tradeOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrderItems(tradeOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.insertTradeOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrderItems(JSONObject jsonObject) {
        try {
            TradeOrderItems tradeOrderItems = JSONObject.toJavaObject(jsonObject, TradeOrderItems.class);
            if (StringUtil.isEmpty(tradeOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteTradeOrderItems(tradeOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.deleteTradeOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrderItems(JSONObject jsonObject) {
        try {
            TradeOrderItems tradeOrderItems = JSONObject.toJavaObject(jsonObject, TradeOrderItems.class);
            if (StringUtil.isEmpty(tradeOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrderItems(tradeOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.updateTradeOrderItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrderItems(JSONObject jsonObject) {
        try {
            TradeOrderItems tradeOrderItems = JSONObject.toJavaObject(jsonObject, TradeOrderItems.class);
            if (StringUtil.isEmpty(tradeOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            tradeOrderItems = mapper.getTradeOrderItems(tradeOrderItems);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrderItems);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.getTradeOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据order_id查询TradeOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson listOrderItemsByOrderId(JSONObject jsonObject) {
        try {
            Long orderId = jsonObject.getLong("id");
            List<TradeOrderItems> tradeOrderItems = mapper.listOrderItemsByOrderId(orderId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,tradeOrderItems);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.getTradeOrderItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getFreeLittleSmokeCount(JSONObject jsonObject) {
        try {
            String userId = jsonObject.getString("userId");
            String skuId = jsonObject.getString("skuId");
            int num = mapper.getFreeLittleSmokeCount(Long.parseLong(userId),Long.parseLong(skuId));
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,num);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.getTradeOrderItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getmogozeropriceCount(JSONObject jsonObject) {
        try {
            String userId = jsonObject.getString("userId");
            String skuId = jsonObject.getString("skuId");
            int num = mapper.getmogozeropriceCount(Long.parseLong(userId),Long.parseLong(skuId));
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,num);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.getmogozeropriceCount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson gettaozhuangzeropriceCount(JSONObject jsonObject) {
        try {
            String userId = jsonObject.getString("userId");
            String skuId = jsonObject.getString("skuId");
            int num = mapper.gettaozhuangzeropriceCount(Long.parseLong(userId),Long.parseLong(skuId));
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,num);
        } catch (Exception e) {
            LOGGER.error("TradeOrderItemsServiceImpl.getmogozeropriceCount出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}