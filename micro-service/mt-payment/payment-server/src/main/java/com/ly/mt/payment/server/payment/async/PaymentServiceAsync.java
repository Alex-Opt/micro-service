package com.ly.mt.payment.server.payment.async;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.payment.PaymentDetailVo;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.payment.server.payment.mapper.PaymentServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PAYMENT_DETAIL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_FALL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.TradeType.TRADE_TYPE_PAY;
import static com.ly.mt.payment.server.base.dict.AlipayTradeStatusEnum.ALIPAY_TRADE_STATUS_WAIT_BUYER_PAY;

/**
 * @Description 支付交易流水处理异步任务
 * @Author taoye
 */
@Component
public class PaymentServiceAsync {
    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentServiceAsync.class);
    @Resource
    PaymentServiceMapper mapper;

    /**
     * @Description 阿里支付交易流水处理
     * @Author taoye
     */
    @Async
    public void saveAlipayPaymentDetail(JSONObject jsonObject, String sellerId, String buyerId) {
        try {
            PaymentDetailVo vo = new PaymentDetailVo();
            vo.setId(SnowflakeUtil.getPrimaryKey(PAYMENT_DETAIL));
            vo.setOrderNo(jsonObject.getString("out_trade_no"));
            vo.setPayee(sellerId);
            vo.setPayer(buyerId);
            vo.setMoney(jsonObject.getString("total_amount"));
            vo.setTradeType(TRADE_TYPE_PAY.getId());
            String tradeStatus = jsonObject.getString("trade_status");
            if (!ALIPAY_TRADE_STATUS_WAIT_BUYER_PAY.getCode().equals(tradeStatus)) {
                vo.setTradeStatus(TRADE_STATUS_PAY_SUCCESS.getId());
            } else {
                vo.setTradeStatus(TRADE_STATUS_PAY_FALL.getId());
            }
            vo.setPaymentType(PAYMENT_TYPE_AL.getId());
            vo.setPaymentNo(jsonObject.getString("trade_no"));
            vo.setCreateTime(DateUtil.getNowTimeStr());
            mapper.insertPaymentDetail(vo);
        } catch (Exception e) {
            LOGGER.error("阿里支付交易流水处理出错:", e);
        }
    }

    /**
     * @Description 微信支付交易流水处理
     * @Author taoye
     */
    @Async
    public void saveWxpayPaymentDetail(JSONObject jsonObject, String sellerId, String buyerId, String orderMoney) {
        try {
            PaymentDetailVo vo = new PaymentDetailVo();
            vo.setId(SnowflakeUtil.getPrimaryKey(PAYMENT_DETAIL));
            vo.setOrderNo(jsonObject.getString("out_trade_no"));
            vo.setPayee(sellerId);
            vo.setPayer(buyerId);
            vo.setMoney(orderMoney);
            vo.setTradeType(TRADE_TYPE_PAY.getId());
            vo.setTradeStatus(TRADE_STATUS_PAY_SUCCESS.getId());
            vo.setPaymentType(PAYMENT_TYPE_WX.getId());
            vo.setPaymentNo(jsonObject.getString("transaction_id"));
            vo.setCreateTime(DateUtil.getNowTimeStr());
            mapper.insertPaymentDetail(vo);
        } catch (Exception e) {
            LOGGER.error("微信支付交易流水处理出错:", e);
        }
    }

    /**
     * @Description 阿里/微信支付交易流水处理
     * @Author taoye
     */
    @Async
    public void savePaymentDetail(PaymentOrderVo order, String sellerId, String buyerId) {
        try {
            PaymentDetailVo vo = new PaymentDetailVo();
            vo.setId(SnowflakeUtil.getPrimaryKey(PAYMENT_DETAIL));
            vo.setOrderNo(order.getOrderNo());
            vo.setPayee(sellerId);
            vo.setPayer(buyerId);
            vo.setMoney(order.getOrderMoney());
            vo.setTradeType(TRADE_TYPE_PAY.getId());
            vo.setTradeStatus(null);
            vo.setPaymentType(PAYMENT_TYPE_AL.getId());
            vo.setPaymentNo(null);
            vo.setCreateTime(DateUtil.getNowTimeStr());
            mapper.insertPaymentDetail(vo);
        } catch (Exception e) {
            LOGGER.error("阿里/微信支付交易流水处理出错:", e);
        }
    }
}