package com.ly.mt.activity.advertisement.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.mq.service.PayNotifyService;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_PAYMENT;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.TradeType.TRADE_TYPE_PAY;
import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
public class PayNotifyServiceImpl extends BaseServiceImpl implements PayNotifyService {

    //日志
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
            String orderJson = callDataCenter(ORDER_GET_ORDER, orderQuery);
            if (StringUtil.isEmpty(orderJson)) {
                LOGGER.info("支付回调MQ处理失败:order_no={}", orderNo);
                return;
            }
            JSONObject orderUpdate = JSONObject.parseObject(orderJson);
            String orderStatus = orderUpdate.getString("order_status");
            if (!ORDER_STATUS_PENDING_PAYMENT.getId().equals(orderStatus)) {
                LOGGER.info("支付回调MQ处理失败:订单已经不是等待付款状态");
                return;
            }
            // 移除id，根据order_no更新数据
            orderUpdate.remove("id");
            orderUpdate.put("order_status", ORDER_STATUS_PENDING_DELIVERY.getId());
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
            detail.put("payment_type", StringUtil.isNotEmpty(tradeNo) ? PAYMENT_TYPE_AL.getId() : PAYMENT_TYPE_WX.getId());
            detail.put("payment_no", StringUtil.isNotEmpty(tradeNo) ? tradeNo : transactionId);
            detail.put("money", StringUtil.isNotEmpty(tradeNo) ? messageObject.getString("total_amount") : messageObject.getString("total_fee"));
            detail.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(PAYMENT_DETAIL_INSERT, detail);
        } catch (Exception e) {
            LOGGER.error("支付回调MQ处理出错:", e);
        }
    }
}