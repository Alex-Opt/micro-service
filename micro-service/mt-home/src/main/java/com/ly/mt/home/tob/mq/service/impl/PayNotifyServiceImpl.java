package com.ly.mt.home.tob.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.TradeStatus;
import com.ly.mt.core.base.dict.TradeType;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.constant.ShopPurchasesEnum;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.discount.service.DiscountService;
import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;
import com.ly.mt.home.tob.mq.service.PayNotifyService;
import com.ly.mt.home.tob.pay.vo.PaymentDetailVo;
import com.ly.mt.home.tob.purchases.service.PurchasesService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;

@Service
public class PayNotifyServiceImpl extends BaseServiceImpl implements PayNotifyService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    PurchasesService purchasesService;

    @Resource
    DiscountService discountService;

    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payNotify(String json) {
        try {
            logger.info("pay notify param: {}", json);
            JSONObject messageObject = JSONObject.parseObject(json);
            String orderNo = messageObject.getString("out_trade_no");
            ShopPurchasesVo purchasesVo = purchasesService.getPurchases(orderNo, null);

            if (purchasesVo == null) {
                logger.info("支付回调MQ处理失败:order_no={}", orderNo);
                return;
            }

            if (!ShopPurchasesEnum.SHOP_PURCHASES_TO_PAY.getCode().equals(purchasesVo.getStatus())) {
                logger.info("支付回调MQ处理失败:订单已经不是等待付款状态");
                return;
            }

            String sellerId = purchasesVo.getSellerId();
            String tradeNo = messageObject.getString("trade_no");
            String transactionId = messageObject.getString("transaction_id");

            // update puchases status
            purchasesVo.setStatus(ShopPurchasesEnum.SHOP_PURCHASES_TO_SEND.getCode());
            purchasesVo.setPayedTime(DateUtil.getNowTimeStr());
            purchasesVo.setTransactionId(StringUtil.isNotEmpty(tradeNo) ? tradeNo : transactionId);
            purchasesVo.setModifyTime(DateUtil.getNowTimeStr());
            purchasesService.updatePurchase(purchasesVo);
            logger.info("update purchases sucsess");

            // 保存交易流水
            PaymentDetailVo detailVo = new PaymentDetailVo.Builder()
                    .id(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL))
                    .orderNo(purchasesVo.getId())
                    .payee(StringUtil.isNotEmpty(sellerId) ? sellerId : null)
                    .payer(purchasesVo.getUserId())
                    .money(purchasesVo.getActualAmount())
                    .tradeType(TradeType.TRADE_TYPE_PAY.getId())
                    .tradeStatus(TradeStatus.TRADE_STATUS_PAY_SUCCESS.getId())
                    .paymentType(StringUtil.isNotEmpty(tradeNo) ? PAYMENT_TYPE_AL.getId() : PAYMENT_TYPE_WX.getId())
                    .paymentNo(StringUtil.isNotEmpty(tradeNo) ? tradeNo : transactionId)
                    .money(StringUtil.isNotEmpty(tradeNo) ? messageObject.getString("total_amount") : messageObject.getString("total_fee"))
                    .createTime(DateUtil.getNowTimeStr()).build();
            JSONObject jsonObject = (JSONObject) JSON.toJSON(detailVo);
            callDataCenter(DataCenterMethod.PAYMENT_DETAIL_INSERT, jsonObject);
            logger.info("save payment detail sucsess");

            // update shop_purchases_discount
            List<ShopPurchasesDiscountVo> discountList = discountService.getDiscountByPurchasesId(orderNo);
            if (discountList.size() > 0) {
                for (ShopPurchasesDiscountVo discountVo : discountList) {
                    discountService.updateDiscountStatus(discountVo.getId(), ShopConstant.DiscountStatus.NORMAL.getValue());
                }
                logger.info("update shop purchases discount sucsess");
            }

            // TODO: 2019/7/24 添加库存
            //basicACK(message, channel);
        } catch (Exception e) {
            logger.error("支付回调MQ处理出错:", e);
            //basicNACK(message, channel);
        }
    }

    @Override
    public void calculateProfit(Channel channel, Message message) {

        /*try {
            String messageJson  = new String(message.getBody(), "UTF-8");
            JSONObject msg = JSONObject.parseObject(messageJson);
            String orderNo = msg.getString("orderNo");
            JSONObject condition = new JSONObject(1);
            condition.put("order_no",orderNo);
            if (StringUtils.isEmpty(orderNo)){
                logger.error("订单为空");
            }
            JSONObject orderJson = JSONObject.parseObject(callDataCenter(DataCenterMethod.ORDER_LIST_ORDER, condition));
            String stringOrderSource = orderJson.getString("order_source");
            Integer orderSource = Integer.valueOf(stringOrderSource);
            Long orderId = Long.valueOf(orderJson.getString("id"));
            Long buyerId = Long.valueOf(orderJson.getString("buyer_id"));
            BigDecimal orderMoney = BigDecimal.valueOf(Double.valueOf(orderJson.getString("order_money")));
            if (orderSource < 4){
                JSONObject dto = new JSONObject(3);
                dto.put("orderId",orderId);
                dto.put("userId",buyerId);
                dto.put("totalPrice",orderMoney);
                callDataCenter(DataCenterMethod.LODE_RUNNER_USER_LOG_INSERT, dto);
            }
        } catch (Exception e) {
            logger.error("赚钱人分成入库异常={}", e);
        }*/

    }
}