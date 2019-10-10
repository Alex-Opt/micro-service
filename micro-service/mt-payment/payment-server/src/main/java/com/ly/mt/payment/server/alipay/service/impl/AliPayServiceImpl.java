package com.ly.mt.payment.server.alipay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ly.mt.core.base.dict.PaymentType;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.payment.server.alipay.service.AlipayService;
import com.ly.mt.payment.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.payment.server.payment.async.PaymentServiceAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_NOT_LOGIN_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_CALCULATE_PROFIT;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_HOME_B_PROVIDER;
import static com.ly.mt.core.redis.RedisKey.ENTITY_ORDER_PAYMENT_ALIPAY;
import static com.ly.mt.payment.server.base.dict.AlipayResultCodeEnum.ALIPAY_RESULT_CODE_BUSINESS_FALL;
import static com.ly.mt.payment.server.base.dict.AlipayResultCodeEnum.ALIPAY_RESULT_CODE_SUCCESS;
import static com.ly.mt.payment.server.base.dict.AlipayTradeStatusEnum.ALIPAY_TRADE_STATUS_TRADE_SUCCESS;

/**
 * @Description 阿里支付接口
 * @Author taoye
 */
@Service
public class AliPayServiceImpl extends BaseServiceImpl implements AlipayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AliPayServiceImpl.class);
    @Autowired
    public PaymentServiceAsync async;

    /**
     * @Description 支付状态查询
     * @Author taoye
     */
    @Override
    public JSONObject status(String json) throws Exception {
        PaymentOrderVo orderVo = JSON.parseObject(json, PaymentOrderVo.class);
        int orderStatus = mapper.getOrderStatusByOrderNo(orderVo);
        if (orderStatus >= Integer.valueOf(ORDER_STATUS_PENDING_DELIVERY.getId())) {
            return JsonUtil.getSuccessJson(TRADE_STATUS_PAY_SUCCESS.getId());
        }
        // 查询阿里支付状态接口
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        // 参数封装
        Map<String, Object> queryMap = new HashMap<>(4);
        // 商户网站唯一订单号
        queryMap.put("out_trade_no", orderVo.getOrderNo());
        // 调用阿里接口解析返回参数
        alipayRequest.setBizContent(JSON.toJSONString(queryMap));
        String resultJson = getAlipayClient().execute(alipayRequest).getBody();
        JSONObject jsonObject = JSONObject.parseObject(resultJson);
        resultJson = jsonObject.getString("alipay_trade_query_response");
        jsonObject = JSONObject.parseObject(resultJson);
        String code = jsonObject.getString("code");
        if (ALIPAY_RESULT_CODE_BUSINESS_FALL.getCode().equals(code)) {
            LOGGER.error("调用阿里支付状态查询接口业务处理失败,失败信息={}", jsonObject.getString("msg"));
            return JsonUtil.getSuccessJson(0);
        } else if (!ALIPAY_RESULT_CODE_SUCCESS.getCode().equals(code)) {
            LOGGER.error("调用阿里支付状态查询接口失败,错误信息={}", jsonObject.getString("msg"));
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 支付成功
        String tradeStatus = jsonObject.getString("trade_status");
        if (ALIPAY_TRADE_STATUS_TRADE_SUCCESS.getCode().equals(tradeStatus)) {
            orderVo.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
            mapper.updateOrderStatusPaymentSuccess(orderVo);
            async.saveAlipayPaymentDetail(jsonObject, yml.getAlipaySellerId(), null);
            return JsonUtil.getSuccessJson(TRADE_STATUS_PAY_SUCCESS.getId());
        }
        return JsonUtil.getSuccessJson(0);
    }

    /**
     * @Description 支付回调
     * @Author taoye
     */
    @Override
    public JSONObject notify(String json) throws Exception {
        LOGGER.info("=======================进入支付宝的支付回调接口==============================");
        // 回调通知参数
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String outTradeNo = map.get("out_trade_no");
        String totalAmount = map.get("total_amount");
        String tradeStatus = map.get("trade_status");
        // 验签
        if (!checkSign(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 验证卖家
        if (!checkSeller(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 验证appId
        if (!checkAppId(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 获取系统订单
        PaymentOrderVo orderVo = getH5PaymentOrder(ENTITY_ORDER_PAYMENT_ALIPAY, outTradeNo);
        if (null == orderVo) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        String buyerId = orderVo.getBuyerId();
        // 验证订单号
        if (!checkOrderNo(outTradeNo, orderVo.getOrderNo())) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 验证订单金额
        if (!checkOrderMoney(orderVo.getOrderMoney(), totalAmount)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 通知触发
        if (ALIPAY_TRADE_STATUS_TRADE_SUCCESS.getCode().equals(tradeStatus)) {
            // 支付成功
            orderVo.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
            orderVo.setPaymentType(PAYMENT_TYPE_AL.getId());
            orderVo.setPayTime(DateUtil.getNowTimeStr());
            //更新订单状态
            mapper.updateOrderStatusPaymentSuccess(orderVo);
            //是一小时达的订单发送mq
            JSONObject mqJson = new JSONObject();
            LOGGER.info("支付成功，判断订单是否为一小时达订单。distributionId：" + orderVo.getDistributionId());
            if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(orderVo.getDistributionId())) {
                LOGGER.info("订单为一小时达订单，发送mq到一小时达消息队列。");
                mqJson.put("out_trade_no", orderVo.getOrderNo());
                mqService.sendMessage(RABBIT_MQ_HOME_B_PROVIDER, mqJson);
            }
            //异步通知计算收益
            mqService.sendMessage(RABBIT_MQ_CALCULATE_PROFIT, mqJson);
        }
        async.saveAlipayPaymentDetail(JSONObject.parseObject(json), yml.getAlipaySellerId(), buyerId);
        return JsonUtil.getSuccessJson();
    }

    /**
     * @Description WAP支付
     * @Author taoye
     */
    @Override
    public JSONObject wapPay(String json) throws Exception {
        LOGGER.info("=====================进入支付宝支付接口=========================");
        // 入参
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        // 获取订单
        String orderNo = map.get("orderNo");
        PaymentOrderVo orderVo = null;
        //商城支付
        orderVo = getH5PaymentOrder(ENTITY_ORDER_PAYMENT_ALIPAY, orderNo);
        if (null == orderVo) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 校验订单金额
        String orderMoney = orderVo.getOrderMoney();
        double totalMoney = Double.valueOf(orderMoney);
        double mixMoney = 0.01;
        double maxMoney = 100000000.00;
        if (totalMoney < mixMoney || totalMoney > maxMoney) {
            LOGGER.error("调用阿里支付接口订单金额出错：totalMoney={}", totalMoney);
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 缓存数据,有效时间30分钟
        redisServer.setEntity(ENTITY_ORDER_PAYMENT_ALIPAY, orderNo, orderVo, 30L, TimeUnit.MINUTES);
        // 创建API对应的request
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        // 回跳地址
        String returnUrl = map.get("returnUrl");
        alipayRequest.setReturnUrl(returnUrl);
        // 通知地址
        alipayRequest.setNotifyUrl(yml.getAlipayApiNotify());
        // 参数封装
        Map<String, Object> wayPayMap = new HashMap<>(4);
        // 商品的标题/交易标题/订单标题/订单关键字等
        String spuName = orderVo.getSpuName();
        LOGGER.info("===========================商品，订单的标题spuName:" + spuName);
        wayPayMap.put("subject", StringUtil.isEmpty(spuName) ? "魔笛雾化器" : spuName);
        // 商户网站唯一订单号
        wayPayMap.put("out_trade_no", orderNo);
        // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        wayPayMap.put("total_amount", totalMoney);
        // 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
        wayPayMap.put("product_code", "QUICK_WAP_WAY");
        // 业务参数
        alipayRequest.setBizContent(JSON.toJSONString(wayPayMap));
        // 调用SDK生成表单
        try {
           /* String form = getAlipayClient().execute(alipayRequest).getBody();*/
            String form = getAlipayClient().pageExecute(alipayRequest).getBody();
            async.savePaymentDetail(orderVo, yml.getAlipaySellerId(), orderVo.getBuyerId());
            return JsonUtil.getSuccessJson(form);
        } catch (Exception e) {
            LOGGER.error("调用阿里接口发起支付出错:", e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    /**
     * @Description 回调验签
     * @Author taoye
     */
    private boolean checkSign(Map<String, String> map) {
        try {
            map.remove("serviceName");
            map.remove("functionName");
            boolean sign = AlipaySignature.rsaCheckV1(map, yml.getAlipayKeyPublic(), yml.getAlipayCharset(), yml.getAlipaySignType());
            if (!sign) {
                LOGGER.error("阿里支付回调验签失败");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("阿里支付回调验签出错:", e);
            return false;
        }
    }

    /**
     * @Description 校验卖家
     * @Author taoye
     */
    private boolean checkSeller(Map<String, String> map) {
        String sellerId = map.get("seller_id");
        String sellerEmail = map.get("seller_email");
        if (!yml.getAlipaySellerId().equals(sellerId) && !yml.getAlipaySellerEmail().equals(sellerEmail)) {
            LOGGER.error("阿里支付回调验证卖家失败:ymlSellerId={},sellerId={},ymlSellerEmail={},sellerEmaill={}", yml.getAlipaySellerId(), sellerId, yml.getAlipaySellerEmail(), sellerEmail);
            return false;
        }
        return true;
    }

    /**
     * @Description 校验appId
     * @Author taoye
     */
    private boolean checkAppId(Map<String, String> map) {
        String appId = map.get("app_id");
        if (!yml.getAlipayAppId().equals(appId)) {
            LOGGER.error("阿里支付回调验证appId失败:ymlAppId={},appId={}", yml.getAlipayAppId(), appId);
            return false;
        }
        return true;
    }

    /**
     * @Description 校验orderNo
     * @Author taoye
     */
    private boolean checkOrderNo(String outTradeNo, String orderNo) {
        if (!orderNo.equals(outTradeNo)) {
            LOGGER.error("阿里支付回调验证订单编号失败:outTradeNo={},orderNo={}", outTradeNo, orderNo);
            return false;
        }
        return true;
    }

    /**
     * @Description 校验订单金额
     * @Author taoye
     */
    private boolean checkOrderMoney(String orderMoney, String totalAmount) {
        if (new BigDecimal(orderMoney).compareTo(new BigDecimal(totalAmount))!=0) {
            LOGGER.error("阿里支付回调验证订单金额失败:totalAmount={},orderMoney={}", totalAmount, orderMoney);
            return false;
        }
        return true;
    }
}