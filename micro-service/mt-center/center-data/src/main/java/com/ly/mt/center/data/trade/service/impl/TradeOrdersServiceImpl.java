package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.entity.TradeOrders;
import com.ly.mt.center.data.trade.mapper.TradeOrdersMapper;
import com.ly.mt.center.data.trade.service.TradeOrdersService;
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
public class TradeOrdersServiceImpl extends BaseServiceImpl implements TradeOrdersService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrdersServiceImpl.class);
    @Resource
    TradeOrdersMapper mapper;

    /**
     * @Description 插入TradeOrders
     * @Author taoye
     */
    @Override
    public ResponseJson insertTradeOrders(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertTradeOrders(tradeOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.insertTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除TradeOrders
     * @Author taoye
     */
    @Override
    public ResponseJson deleteTradeOrders(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            mapper.deleteTradeOrders(tradeOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.deleteTradeOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新TradeOrders
     * @Author taoye
     */
    @Override
    public ResponseJson updateTradeOrders(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getId()) && StringUtil.isEmpty(tradeOrders.getOrder_no())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrders(tradeOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.updateTradeOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrders
     * @Author taoye
     */
    @Override
    public ResponseJson getTradeOrders(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            /*if (StringUtil.isEmpty(tradeOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询不能为空");
            }*/
            tradeOrders = mapper.getTradeOrders(tradeOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrders);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.getTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询TradeOrders集合
     * @Author taoye
     */
    @Override
    public ResponseJson listTradeOrders(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getOrder_no())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询不能为空");
            }
            List<TradeOrders> list = mapper.listTradeOrders(tradeOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.listTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public  ResponseJson getTradeOrdersBySource(JSONObject jsonObject){
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getOrder_source())||StringUtil.isEmpty(tradeOrders.getCreate_time())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询参数不能为空");
            }
            List<TradeOrders> list = mapper.getTradeOrdersBySource(tradeOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.listTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 更新TradeOrders支付方式
     * @Author
     */
    @Override
    public ResponseJson updateTradeOrdersPaymentType(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateTradeOrdersPaymentType(tradeOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.updateTradeOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getTradeOrdersByOrderNo(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getOrder_no())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询不能为空");
            }
            tradeOrders = mapper.getTradeOrdersByOrderNo(tradeOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeOrders);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.getTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getTradeOrdersByOrderSource(JSONObject jsonObject) {
        try {
            TradeOrders tradeOrders = JSONObject.toJavaObject(jsonObject, TradeOrders.class);
            if (StringUtil.isEmpty(tradeOrders.getBuyer_id())||StringUtil.isEmpty(tradeOrders.getOrder_source())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询不能为空");
            }
            List list = mapper.getTradeOrdersByOrderSource(tradeOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("TradeOrdersServiceImpl.getTradeOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}