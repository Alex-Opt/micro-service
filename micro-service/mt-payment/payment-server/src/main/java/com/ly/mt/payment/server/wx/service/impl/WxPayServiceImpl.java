package com.ly.mt.payment.server.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundInfo;
import com.ly.mt.core.base.util.*;
import com.ly.mt.payment.server.alipay.service.impl.AliPayServiceImpl;
import com.ly.mt.payment.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.payment.server.payment.async.PaymentServiceAsync;
import com.ly.mt.payment.server.wx.service.WxpayService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_CALCULATE_PROFIT;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_HOME_B_PROVIDER;
import static com.ly.mt.core.redis.RedisKey.ENTITY_ORDER_PAYMENT_ALIPAY;
import static com.ly.mt.core.redis.RedisKey.ENTITY_ORDER_PAYMENT_WXPAY;
import static com.ly.mt.payment.server.base.dict.WxpayErrCodeEnum.WXPAY_ERR_CODE_ORDER_NOT_EXIST;
import static com.ly.mt.payment.server.base.dict.WxpayResultCodeEnum.WXPAY_RESULT_CODE_SUCCESS;

/**
 * @Description 微信支付接口
 * @Author taoye
 */
@Service
public class WxPayServiceImpl extends BaseServiceImpl implements WxpayService {
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
        // 参数封装
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("appid", yml.getWxpayAppId());
        treeMap.put("mch_id", yml.getWxpayMerchantId());
        treeMap.put("out_trade_no", orderVo.getOrderNo());
        treeMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
        String sign = getWeChatSign(treeMap, yml.getWxpayMerchantKey());
        treeMap.put("sign", sign);
        String xml = XmlUtil.transMap2Xml(treeMap);
        // 调用微信接口
        String resultXml = postExternalServer(yml.getWxpayApiOrderQuery(), xml);
        // 解析返回数据
        Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
        String returnCode = String.valueOf(resultMap.get("return_code"));
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
            LOGGER.error("wapPay调用微信支付接口失败,错误信息:{}", resultMap.get("return_msg"));
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        String resultCode = String.valueOf(resultMap.get("result_code"));
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
            String errCode = String.valueOf(resultMap.get("err_code"));
            LOGGER.error("wapPay调用微信支付接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
            if (WXPAY_ERR_CODE_ORDER_NOT_EXIST.getCode().equals(errCode)) {
                return JsonUtil.getSuccessJson(0);
            }
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        String tradeState = String.valueOf(resultMap.get("trade_state"));
        if (WXPAY_RESULT_CODE_SUCCESS.getCode().equals(tradeState)) {
            orderVo.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
            mapper.updateOrderStatusPaymentSuccess(orderVo);
            // 金额单位转换 分—>元
            double totalFee = Double.valueOf(String.valueOf(resultMap.get("total_fee")));
            String totalAmount = String.valueOf(totalFee / 100);
            async.saveWxpayPaymentDetail(JSONObject.parseObject(JSONObject.toJSONString(resultMap)), yml.getWxpayMerchantId(), null, totalAmount);
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
        LOGGER.info("=========================进入微信支付回调接口============================");
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        // 解析返回数据
        String returnCode = map.get("return_code");
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
            LOGGER.error("支付回调返回支付失败,错误信息:{}", map.get("return_msg"));
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        String resultCode = map.get("result_code");
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("微信支付回调返回支付失败,错误代码={},错误信息={},", map.get("err_code"), map.get("err_code_des"));
            return JsonUtil.getSuccessJson();
        }
        // 验签
        if (!checkSign(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 获取系统订单
        String outTradeNo = map.get("out_trade_no");
        PaymentOrderVo orderVo = getH5PaymentOrder(ENTITY_ORDER_PAYMENT_ALIPAY, outTradeNo);
        if (null == orderVo) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 金额单位转换 分—>元
        double totalFee = Double.valueOf(map.get("total_fee"));
        String totalAmount = String.valueOf(totalFee / 100);
        String orderMoney = orderVo.getOrderMoney();
        // 校验金额
        if (!checkMoney(orderMoney, totalAmount)) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 支付成功
        orderVo.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
        orderVo.setPaymentType(PAYMENT_TYPE_WX.getId());
        orderVo.setPayTime(DateUtil.getNowTimeStr());
        mapper.updateOrderStatusPaymentSuccess(orderVo);
        //是一小时达的订单发送mq
        LOGGER.info("支付成功，判断订单是否为一小时达订单。distributionId：" + orderVo.getDistributionId());
        JSONObject mqJson = new JSONObject();
        if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(orderVo.getDistributionId())) {
            LOGGER.info("订单为一小时达订单，发送mq到一小时达消息队列。");
            mqJson.put("out_trade_no", orderVo.getOrderNo());
            mqService.sendMessage(RABBIT_MQ_HOME_B_PROVIDER, mqJson);
        }
        //异步通知计算收益
        mqService.sendMessage(RABBIT_MQ_CALCULATE_PROFIT, mqJson);
        async.saveWxpayPaymentDetail(JSONObject.parseObject(json), yml.getWxpayMerchantId(), orderVo.getBuyerId(), orderVo.getOrderMoney());
        return JsonUtil.getSuccessJson();
    }

    /**
     * @Description WAP支付
     * @Author taoye
     */
    @Override
    public JSONObject wapPay(String json) throws Exception {
        // 入参
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String orderNo = map.get("orderNo");
        PaymentOrderVo orderVo = null;
        //商城支付
        orderVo = getH5PaymentOrder(ENTITY_ORDER_PAYMENT_WXPAY, orderNo);
        if (null == orderVo) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 缓存数据,有效时间30分钟
        redisServer.setEntity(ENTITY_ORDER_PAYMENT_WXPAY, orderNo, orderVo, 30L, TimeUnit.MINUTES);
        // 单位转换 元—>分
        String orderMoney = orderVo.getOrderMoney();
        int totalMoney = (int) ((double) (Double.valueOf(orderMoney) * 100));
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("appid", yml.getWxpayAppId());
        treeMap.put("mch_id", yml.getWxpayMerchantId());
        treeMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
        String spuName = orderVo.getSpuName();
        treeMap.put("body", StringUtil.isEmpty(spuName) ? "魔笛雾化器" : spuName);
        treeMap.put("out_trade_no", orderNo);
        treeMap.put("total_fee", totalMoney);
        treeMap.put("spbill_create_ip", map.get("loginIpAddress"));
        treeMap.put("notify_url", yml.getWxpayApiNotify());
        treeMap.put("trade_type", yml.getWxpayH5TradeType());
        treeMap.put("scene_info", yml.getWxpayH5SceneInfo());
        String sign = getWeChatSign(treeMap, yml.getWxpayMerchantKey());
        treeMap.put("sign", sign);
        String xml = XmlUtil.transMap2Xml(treeMap);
        // 调用微信接口
        String resultXml = postExternalServer(yml.getWxpayApiUnifiedOrder(), xml);
        // 解析返回数据
        Map<String, Object> resultMap = XmlUtil.transXml2Map(resultXml);
        String returnCode = String.valueOf(resultMap.get("return_code"));
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
            LOGGER.error("wapPay调用微信支付接口失败,错误信息:{}", resultMap.get("return_msg"));
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        String resultCode = String.valueOf(resultMap.get("result_code"));
        if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("wapPay调用微信支付接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        // 返回拉起微信客户端地址
        String mwebUrl = String.valueOf(resultMap.get("mweb_url"));
        async.savePaymentDetail(orderVo, yml.getWxpayMerchantId(), orderVo.getBuyerId());
        return JsonUtil.getSuccessJson(mwebUrl);
    }

    @Override
    public JSONObject refund(String json) throws Exception {
        // 入参
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String orderNo = map.get("orderNo");
        String refundNo = map.get("refundNo");
        PaymentOrderVo orderVo = null;
        //商城支付
        orderVo = getH5PaymentOrder(ENTITY_ORDER_PAYMENT_WXPAY, orderNo);
        if (null == orderVo) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        //查询退款单
        TradeOrderRefundInfo tradeOrderRefundInfo = mapper.getTradeOrderRefundInfoById(Long.parseLong(refundNo));
        if (tradeOrderRefundInfo == null) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 单位转换 元—>分
        String orderMoney = orderVo.getOrderMoney();
        int totalMoney = (int) ((double) (Double.valueOf(orderMoney) * 100));
        int refundFee = (int) ((double) (Double.valueOf(tradeOrderRefundInfo.getRefundPrice()) * 100));

        TreeMap<String, Object> paraMap = new TreeMap<>();
        paraMap.put("appid", yml.getWxpayAppId());
        paraMap.put("mch_id", yml.getWxpayMerchantId());
        paraMap.put("nonce_str", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM));
        paraMap.put("out_trade_no", orderNo);
        paraMap.put("out_refund_no", refundNo);
        paraMap.put("total_fee", totalMoney);
        paraMap.put("refund_fee", refundFee);
        String sign = getWeChatSign(paraMap, yml.getWxpayMerchantKey());
        paraMap.put("sign", sign);
        String xml = XmlUtil.transMap2Xml(paraMap);
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //指向你的证书的绝对路径，带着证书去访问
        FileInputStream instream = new FileInputStream(
                new File("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/wechat/apiclient_cert.p12"));//P12文件目录
        try {
            //下载证书时的密码、默认密码是你的MCHID mch_id
            keyStore.load(instream, yml.getWxpayMerchantId().toCharArray());//这里写密码
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, yml.getWxpayMerchantId().toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();
        HttpPost httppost = new HttpPost(yml.getWxpayApiRefund());
        try {
            StringEntity se = new StringEntity(xml);
            httppost.setEntity(se);
            LOGGER.info("executing request" + httppost.getRequestLine());
            CloseableHttpResponse responseEntry = httpclient.execute(httppost);
            try {
                HttpEntity entity = responseEntry.getEntity();
                if (entity != null) {
                    // 解析返回数据
                    Map<String, Object> resultMap = XmlUtil.transXml2Map(entity.getContent().toString());
                    String returnCode = String.valueOf(resultMap.get("return_code"));
                    if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(returnCode)) {
                        LOGGER.error("wapPay调用微信退款接口失败,错误信息:{}", resultMap.get("return_msg"));
                        return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR, resultMap.get("return_msg"));
                    }
                    String resultCode = String.valueOf(resultMap.get("result_code"));
                    if (!WXPAY_RESULT_CODE_SUCCESS.getCode().equals(resultCode)) {
                        LOGGER.error("wapPay调用微信退款接口失败,错误代码:{},错误描述:{}", resultMap.get("err_code"), resultMap.get("err_code_des"));
                        return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR, resultMap.get("err_code_des"));
                    }
                    //退款成功 更改退款单状态
                    LOGGER.info("wapPay调用微信退款接口成功");
                    mapper.updateTradeOrderRefundStatusById(Long.parseLong(refundNo));
                    return JsonUtil.getSuccessJson();
                } else {
                    return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
                }
            } finally {
                responseEntry.close();
            }
        } finally {
            httpclient.close();
        }
    }


    /**
     * @Description 验签
     * @Author taoye
     */
    private boolean checkSign(Map<String, String> map) {
        try {
            String sign = map.get("sign");
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.putAll(map);
            treeMap.remove("serviceName");
            treeMap.remove("functionName");
            treeMap.remove("sign");
            treeMap.put("appid", yml.getWxpayAppId());
            treeMap.put("mch_id", yml.getWxpayMerchantId());
            String weChatSign = getWeChatSign(treeMap, yml.getWxpayMerchantKey());
            if (!sign.equals(weChatSign)) {
                LOGGER.error("微信支付回调验签失败");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("微信支付回调验签出错:", e);
            return false;
        }
    }

    /**
     * @Description 校验订单金额
     * @Author taoye
     */
    private boolean checkMoney(String orderMoney, String totalAmount) {
        if (new BigDecimal(orderMoney).compareTo(new BigDecimal(totalAmount)) != 0) {
            LOGGER.error("微信支付回调验证订单金额失败:totalAmount={},orderMoney={}", totalAmount, orderMoney);
            return false;
        }
        return true;
    }
}