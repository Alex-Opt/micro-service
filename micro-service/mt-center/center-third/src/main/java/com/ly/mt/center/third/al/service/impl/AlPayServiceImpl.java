package com.ly.mt.center.third.al.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ly.mt.center.third.al.entity.AlPayOrder;
import com.ly.mt.center.third.al.service.AlPayService;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.center.third.al.dict.AlPayResultCode.AL_PAY_RESULT_CODE_BUSINESS_FALL;
import static com.ly.mt.center.third.al.dict.AlPayResultCode.AL_PAY_RESULT_CODE_SUCCESS;
import static com.ly.mt.center.third.al.dict.AlPayTradeStatus.AL_PAY_TRADE_STATUS_TRADE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.REDIS_ENTITY_PAY_ORDER_AL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_FALL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @Description 阿里支付接口
 * @Author taoye
 */
@Service
public class AlPayServiceImpl extends BaseServiceImpl implements AlPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AlPayServiceImpl.class);
    private static AlipayClient alipayClient;
    @Resource
    YmlConfig yml;

    /**
     * @Description 支付状态查询
     * @Author taoye
     */
    @Override
    public ResponseJson status(JSONObject jsonObject) {
        try {
            // 查询阿里支付状态接口
            AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
            // 参数封装
            Map<String, Object> queryMap = new HashMap<>(4);
            // 商户网站唯一订单号
            queryMap.put("out_trade_no", jsonObject.getString("out_trade_no"));
            // 调用阿里接口解析返回参数
            alipayRequest.setBizContent(JSON.toJSONString(queryMap));
            String responseJson = getAlipayClient().execute(alipayRequest).getBody();
            JSONObject responseObject = JSONObject.parseObject(responseJson);
            String resultJson = responseObject.getString("alipay_trade_query_response");
            JSONObject resultObject = JSONObject.parseObject(resultJson);
            String code = resultObject.getString("code");
            if (AL_PAY_RESULT_CODE_BUSINESS_FALL.getCode().equals(code)) {
                LOGGER.error("调用阿里支付状态查询接口业务处理失败,失败信息={}", resultObject.getString("msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            } else if (!AL_PAY_RESULT_CODE_SUCCESS.getCode().equals(code)) {
                LOGGER.error("调用阿里支付状态查询接口失败,错误信息={}", resultObject.getString("msg"));
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 返回结果
            JSONObject result = new JSONObject();
            result.put("trade_no", resultObject.getString("trade_no"));
            result.put("total_amount", resultObject.getString("total_amount"));
            String tradeStatus = resultObject.getString("trade_status");
            if (AL_PAY_TRADE_STATUS_TRADE_SUCCESS.getCode().equals(tradeStatus)) {
                // 支付成功
                result.put("trade_status", TRADE_STATUS_PAY_SUCCESS.getId());
            } else {
                // 支付失败
                result.put("trade_status", TRADE_STATUS_PAY_FALL.getId());
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
        } catch (Exception e) {
            LOGGER.error("查询阿里支付状态接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 支付回调
     * @Author taoye
     */
    @Override
    public ResponseJson notify(JSONObject jsonObject) {
        try {
            // 回调通知参数
            Map<String, String> map = JSONObject.toJavaObject(jsonObject, Map.class);
            // 验签
            if (!checkSign(map)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 验证卖家
            if (!checkSeller(map)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 验证appId
            if (!checkAppId(map)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 获取系统订单
            String out_trade_no = map.get("out_trade_no");
            String orderJson = redisService.get(REDIS_ENTITY_PAY_ORDER_AL, out_trade_no);
            if (StringUtil.isEmpty(orderJson)) {
                LOGGER.error("阿里支付回调出错:未从缓存中查到订单信息");
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 验证订单金额
            AlPayOrder alPayOrder = JSONObject.toJavaObject(JSONObject.parseObject(orderJson), AlPayOrder.class);
            String total_amount = map.get("total_amount");
            if (!total_amount.equals(alPayOrder.getTotal_amount())) {
                LOGGER.error("阿里支付回调出错:验证订单金额失败");
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 返回支付状态
            String trade_status = map.get("trade_status");
            if (AL_PAY_TRADE_STATUS_TRADE_SUCCESS.getCode().equals(trade_status)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, TRADE_STATUS_PAY_SUCCESS.getId());
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("阿里支付回调出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description WAP支付
     * @Author taoye
     */
    @Override
    public ResponseJson wapPay(JSONObject jsonObject) {
        try {
            AlPayOrder alPayOrder = JSONObject.toJavaObject(jsonObject, AlPayOrder.class);
            // 校验订单金额
            double total_money = Double.valueOf(alPayOrder.getTotal_amount());
            double mix_money = 0.01;
            double max_money = 100000000.00;
            if (total_money < mix_money || total_money > max_money) {
                LOGGER.error("调用阿里支付接口订单金额出错：total_money={}", total_money);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 缓存数据
            redisService.setEntity(REDIS_ENTITY_PAY_ORDER_AL, alPayOrder.getOut_trade_no(), alPayOrder, 24L, TimeUnit.HOURS);
            // 创建API对应的request
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            // 回跳地址
            String return_url = alPayOrder.getReturn_url();
            alipayRequest.setReturnUrl(return_url);
            // 通知地址
            alipayRequest.setNotifyUrl(yml.getAlPayApiNotify());
            // 参数封装
            Map<String, Object> wayPayMap = new HashMap<>(4);
            // 商品的标题/交易标题/订单标题/订单关键字等
            String spuName = alPayOrder.getSpu_name();
            wayPayMap.put("subject", StringUtil.isEmpty(spuName) ? "魔笛雾化器" : spuName);
            // 商户网站唯一订单号
            wayPayMap.put("out_trade_no", alPayOrder.getOut_trade_no());
            // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
            wayPayMap.put("total_amount", alPayOrder.getTotal_amount());
            // 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
            wayPayMap.put("product_code", "QUICK_WAP_WAY");
            if(StringUtil.isNotEmpty(alPayOrder.getPassback_params())){
                //回调使用的系统参数
                wayPayMap.put("passback_params", URLEncoder.encode(alPayOrder.getPassback_params(),"utf-8"));
            }
            // 业务参数
            alipayRequest.setBizContent(JSON.toJSONString(wayPayMap));

            // 调用SDK生成表单
            String form = getAlipayClient().pageExecute(alipayRequest).getBody();

            LOGGER.info("调用支付宝SDK生成表单",form);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, form);
        } catch (Exception e) {
            LOGGER.error("发起阿里支付出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    // TODO: 2019/9/20 linan optimize
    @Override
    public ResponseJson appPay(JSONObject jsonObject) {
        try {
            AlPayOrder alPayOrder = JSONObject.toJavaObject(jsonObject, AlPayOrder.class);
            // 校验订单金额
            double total_money = Double.valueOf(alPayOrder.getTotal_amount());
            double mix_money = 0.01;
            double max_money = 100000000.00;
            if (total_money < mix_money || total_money > max_money) {
                LOGGER.error("调用阿里支付接口订单金额出错：total_money={}", total_money);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            // 缓存数据
            redisService.setEntity(REDIS_ENTITY_PAY_ORDER_AL, alPayOrder.getOut_trade_no(), alPayOrder, 24L, TimeUnit.HOURS);

            // request model
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setSubject(StringUtil.isEmpty(alPayOrder.getSpu_name()) ? "魔笛雾化器" : alPayOrder.getSpu_name());
            model.setOutTradeNo(alPayOrder.getOut_trade_no());
            model.setTotalAmount(alPayOrder.getTotal_amount());
            model.setProductCode("QUICK_MSECURITY_PAY");
            if(StringUtil.isNotEmpty(alPayOrder.getPassback_params())){
                model.setPassbackParams(URLEncoder.encode(alPayOrder.getPassback_params(),"utf-8"));
            }

            // pay request
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setNotifyUrl(yml.getAlPayApiNotify());
            request.setBizModel(model);

            AlipayTradeAppPayResponse response = getAlipayClient().sdkExecute(request);

            String body = response.getBody();
            LOGGER.info("aliPay app pay orderString: {}", body);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, body);
        } catch (Exception e) {
            LOGGER.error("aliPay app pay error:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson refund(JSONObject parameter) {
        AlipayClient alipayClient = getAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        Map<String,Object> bizContent = new HashMap<>();
        bizContent.put("out_trade_no",parameter.getString("out_trade_no"));
        bizContent.put("refund_amount",parameter.getString("refund_amount"));
        bizContent.put("out_request_no", UUID.randomUUID().toString());
        request.setBizContent(JSON.toJSONString(bizContent));
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()){
                String body = response.getBody();
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,body);
            }else {
                LOGGER.error("调用阿里退款接口失败");
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }

        } catch (AlipayApiException e) {
            LOGGER.error("发起阿里退款出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }







    /**
     * @Description 回调验签
     * @Author taoye
     */
    private boolean checkSign(Map<String, String> map) {
        try {
            LOGGER.info("AlPayServiceImpl.checkSign() map:{},publicKey:{},charset:{},signType:{}",
                    map, yml.getAlPayKeyPublic(),yml.getAlPayCharset(),yml.getAlPaySignType());
            map.remove("serverName");
            map.remove("functionName");
            boolean sign = AlipaySignature.rsaCheckV1(map, yml.getAlPayKeyPublic(), yml.getAlPayCharset(), yml.getAlPaySignType());
            if (!sign) {
                LOGGER.error("阿里支付回调出错:验签失败");
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
        if (!yml.getAlPaySellerId().equals(sellerId) && !yml.getAlPaySellerEmail().equals(sellerEmail)) {
            LOGGER.error("阿里支付回调出错:验证卖家失败:ymlSellerId={},sellerId={},ymlSellerEmail={},sellerEmaill={}", yml.getAlPaySellerId(), sellerId, yml.getAlPaySellerEmail(), sellerEmail);
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
        if (!yml.getAlPayAppId().equals(appId)) {
            LOGGER.error("阿里支付回调出错:验证appId失败:ymlAppId={},appId={}", yml.getAlPayAppId(), appId);
            return false;
        }
        return true;
    }


    /**
     * @Description 阿里支付client获取
     * @Author taoye
     */
    private AlipayClient getAlipayClient() {
        if (null == alipayClient) {
            alipayClient = new DefaultAlipayClient(
                    yml.getAlPayApiServer(), yml.getAlPayAppId(), yml.getAlPayKeyPrivate(),
                    yml.getAlPayFormat(), yml.getAlPayCharset(), yml.getAlPayKeyPublic(), yml.getAlPaySignType()
            );
        }
        return alipayClient;
    }
}