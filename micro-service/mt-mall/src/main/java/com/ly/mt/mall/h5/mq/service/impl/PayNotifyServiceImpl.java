package com.ly.mt.mall.h5.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.mq.service.PayNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_PAYMENT;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.TradeType.TRADE_TYPE_PAY;
import static com.ly.mt.core.base.dict.WxAppletTemplateSendType.TEMPLATE_SEND_TYPE_PAY_RESULT;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_TEMPLATE_MESSAGE_SEND;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_HOME_B_PROVIDER;

@Service
public class PayNotifyServiceImpl extends BaseServiceImpl implements PayNotifyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PayNotifyServiceImpl.class);

    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    @Override
    public void payNotify(String message) {
        try {
            LOGGER.info("支付回调message:{}", message);
            JSONObject messageObject = JSONObject.parseObject(message);
            String orderNo = messageObject.getString("out_trade_no");
            JSONObject orderQuery = new JSONObject();
            orderQuery.put("order_no", orderNo);
            String orderJson = callDataCenter(ORDER_GET_BY_ORDERNO, orderQuery);
            LOGGER.info("根据订单号查询订单信息-----------orderJson：{}",orderJson);
            if (StringUtil.isEmpty(orderJson)) {
                LOGGER.info("支付回调MQ根据订单查询为空:order_no={}", orderNo);
                return;
            }
            JSONObject orderUpdate = JSONObject.parseObject(orderJson);
            String id = orderUpdate.getString("id");
            String orderStatus = orderUpdate.getString("order_status");
            if (!ORDER_STATUS_PENDING_PAYMENT.getId().equals(orderStatus)) {
                LOGGER.info("支付回调MQ处理失败:订单已经不是等待付款状态");
                return;
            }
            //是一小时达的订单发送mq
            JSONObject mqJson = new JSONObject();
            String distributionId = orderUpdate.getString("distribution_id");
            LOGGER.info("支付成功，判断订单是否为一小时达订单。distributionId：" + distributionId);
            if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(distributionId)) {
                LOGGER.info("订单为一小时达订单，发送mq到一小时达消息队列。");
                mqJson.put("out_trade_no", orderNo);
                mqService.sendMessage(RABBIT_MQ_HOME_B_PROVIDER, mqJson);
            }
            // 移除id，根据order_no更新数据
            orderUpdate.remove("id");
            String paymentType = messageObject.getString("paymentType");
            LOGGER.info("-----------------------支付回调的入参支付方式的值："+paymentType);
            orderUpdate.put("order_status", ORDER_STATUS_PENDING_DELIVERY.getId());
            orderUpdate.put("payment_type", paymentType);
            callDataCenter(ORDER_UPDATE_ORDER, orderUpdate);
            // 保存交易流水
            JSONObject detail = new JSONObject();
            detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
            detail.put("order_no", orderNo);
            String sellerId = orderUpdate.getString("seller_id");
            detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
            detail.put("payer", orderUpdate.getString("buyer_id"));
            detail.put("trade_type", TRADE_TYPE_PAY.getId());
            detail.put("trade_status", TRADE_STATUS_PAY_SUCCESS.getId());
            String tradeNo = messageObject.getString("trade_no");
            String transactionId = messageObject.getString("transaction_id");
            detail.put("payment_type", paymentType);
            detail.put("payment_no", StringUtil.isNotEmpty(tradeNo) ? tradeNo : transactionId);
            detail.put("money", StringUtil.isNotEmpty(tradeNo) ? messageObject.getString("total_amount") : messageObject.getString("total_fee"));
            detail.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            //微信小程序发送支付成功的模版消息
            String openId = messageObject.getString("openid");
            if(StringUtil.isEmpty(openId)){
                String buyerId = orderUpdate.getString("buyer_id");
                JSONObject userJson = new JSONObject(1);
                userJson.put("id",buyerId);
                String userStr = callDataCenter(USER_GET_USER_BY_ID, userJson);
                LOGGER.info("查询到的用户信息---------------------userStr："+userStr);
                userJson = JSONObject.parseObject(userStr);
                openId = userJson.getString("wx_open_id");
            }
            if(StringUtil.isNotEmpty(openId)){
                JSONObject templateMap = new JSONObject();
                JSONObject jsonObject  = new JSONObject();
                jsonObject.put("id",id);
                String orderItemListStr = callDataCenter(TRADE_ORDER_ITEMS_LIST, jsonObject);
                List<TradeOrderItem> tradeOrderItems = JSON.parseObject(orderItemListStr, new TypeReference<List<TradeOrderItem>>() {
                });
                templateMap.put("openId", openId);
                templateMap.put("templateId", TEMPLATE_SEND_TYPE_PAY_RESULT.getId());
                JSONObject dataJson =new JSONObject(6);
                JSONObject keyword1 = new JSONObject();
                keyword1.put("value","支付成功");
                JSONObject keyword2 = new JSONObject();
                keyword2.put("value",detail.getBigDecimal("money").divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP));
                JSONObject keyword3 = new JSONObject();
                keyword3.put("value",tradeOrderItems.get(0).getSpuName());
                JSONObject keyword4 = new JSONObject();
                keyword4.put("value",DateUtil.getNowTimeStr());
                JSONObject keyword5 = new JSONObject();
                keyword5.put("value","MOTI官方商城");
                JSONObject keyword6 = new JSONObject();
                keyword6.put("value",orderNo);
                dataJson.put("keyword1",keyword1);
                dataJson.put("keyword2",keyword2);
                dataJson.put("keyword3",keyword3);
                dataJson.put("keyword4",keyword4);
                dataJson.put("keyword5",keyword5);
                dataJson.put("keyword6",keyword6);
                templateMap.put("data", dataJson);
                callThirdCenter(WX_TEMPLATE_MESSAGE_SEND, templateMap);
            }
        } catch (Exception e) {
            LOGGER.error("支付回调MQ处理出错:", e);
        }
    }

    @Override
    public void calculateProfit(String message) {
        try {
            LOGGER.info("计算收益message:{}", message);
            JSONObject msg = JSONObject.parseObject(message);
            String orderNo = msg.getString("orderNo");
            JSONObject condition = new JSONObject(1);
            condition.put("order_no", orderNo);
            if (StringUtils.isEmpty(orderNo)) {
                LOGGER.error("订单为空");
            }
            JSONObject orderJson = JSONObject.parseObject(callDataCenter(DataCenterMethod.ORDER_LIST_ORDER, condition));
            String stringOrderSource = orderJson.getString("order_source");
            Integer orderSource = Integer.valueOf(stringOrderSource);
            Long orderId = Long.valueOf(orderJson.getString("id"));
            Long buyerId = Long.valueOf(orderJson.getString("buyer_id"));
            BigDecimal orderMoney = BigDecimal.valueOf(Double.valueOf(orderJson.getString("order_money")));
            if (orderSource < 4) {
                JSONObject dto = new JSONObject(3);
                dto.put("orderId", orderId);
                dto.put("userId", buyerId);
                dto.put("totalPrice", orderMoney);
                callDataCenter(DataCenterMethod.LODE_RUNNER_USER_LOG_INSERT, dto);
            }
        } catch (Exception e) {
            LOGGER.error("赚钱人分成入库异常={}", e);
        }
    }
}