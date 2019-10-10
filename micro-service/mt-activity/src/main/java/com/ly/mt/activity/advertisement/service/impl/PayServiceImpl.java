package com.ly.mt.activity.advertisement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.activity.advertisement.service.PayService;
import com.ly.mt.activity.advertisement.vo.PayOrderVo;
import com.ly.mt.activity.base.config.YmlConfig;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_FALL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.TradeType.TRADE_TYPE_PAY;
import static com.ly.mt.core.base.dict.WxPayTradeType.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.DataCenterMethod.PAYMENT_DETAIL_INSERT;
import static com.ly.mt.core.feign.ThirdCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_PAY_STATUS;
import static com.ly.mt.core.redis.RedisKey.REDIS_USER_WX_OPENID;

@Service
public class PayServiceImpl extends BaseServiceImpl implements PayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);
    @Resource
    YmlConfig yml;


    /**
     * @Description 支付宝WAP支付
     * @Author taoye
     */
    @Override
    public ResponseJson alPay(String orderNo, String returnUrl, String spuName) {
        try {
            // 查询订单金额
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_no", orderNo);
            String orderListJson = callDataCenter(ORDER_LIST_ORDER, jsonObject);
            if (StringUtil.isEmpty(orderListJson)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            List<PayOrderVo> list = JSON.parseObject(orderListJson, new TypeReference<List<PayOrderVo>>() {
            });
            // 发起支付
            String orderMoney = getOrderMoney(list);
            JSONObject param = new JSONObject();
            param.put("out_trade_no", orderNo);
            param.put("return_url", returnUrl);
            param.put("spu_name", spuName);
            param.put("total_amount", orderMoney);
            String form = callThirdCenter(AL_PAY_WAP_PAY, param);
            // 保存交易流水
            PayOrderVo orderVo = list.get(0);
            JSONObject detail = new JSONObject();
            detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
            detail.put("order_no", orderNo);
            String sellerId = orderVo.getSellerId();
            detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
            detail.put("payer", orderVo.getBuyerId());
            detail.put("money", orderMoney);
            detail.put("trade_type", TRADE_TYPE_PAY.getId());
            detail.put("trade_status", TRADE_STATUS_PAY_SUCCESS.getId());
            detail.put("payment_type", PAYMENT_TYPE_AL.getId());
            detail.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, form);
        } catch (Exception e) {
            LOGGER.error("发起支付宝支付出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 支付宝WAP支付状态
     * @Author taoye
     */
    @Override
    public ResponseJson alPayStatus(String orderNo) {
        try {
            // 查询系统订单支付状态
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", orderNo);
            String orderJson = callDataCenter(ORDER_GET_ORDER, jsonObject);
            JSONObject order = JSONObject.parseObject(orderJson);
            int orderStatus = order.getIntValue("result");
            if (orderStatus >= Integer.valueOf(ORDER_STATUS_PENDING_DELIVERY.getId())) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, TRADE_STATUS_PAY_SUCCESS.getId());
            }
            // 查询阿里订单支付状态
            jsonObject.put("out_trade_no",order.getString("order_no"));
            String resultJson = callThirdCenter(AL_PAY_STATUS, jsonObject);
            JSONObject resultObject = JSONObject.parseObject(resultJson);

            String tradeStatus = resultObject.getString("trade_status");
            if ("30".equals(tradeStatus)) {
                // 更新系统订单支付状态
                LOGGER.info("支付宝支付订单 修改活动订单状态为待支付状态 状态：{}",tradeStatus);
                order.put("order_status", ORDER_STATUS_PENDING_DELIVERY.getId());
                callDataCenter(ORDER_UPDATE_ORDER, order);
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", order.getString("order_money"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_AL.getId());
                /*detail.put("payment_no", resultObject.getString("trade_no"));*/
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }else if("20".equals(tradeStatus)){
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", order.getString("order_money"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_AL.getId());
                /*detail.put("payment_no", resultObject.getString("trade_no"));*/
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeStatus);
        } catch (Exception e) {
            LOGGER.error("查询支付宝支付状态出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 微信WAP支付
     * @Author taoye
     */
    @Override
    public ResponseJson wxPay(String orderNo, String spuName,String ip, String trade_type) {
        try {
            // 查询订单金额
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_no", orderNo);
            String orderListJson = callDataCenter(ORDER_LIST_ORDER, jsonObject);
            if (StringUtil.isEmpty(orderListJson)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            List<PayOrderVo> list = JSON.parseObject(orderListJson, new TypeReference<List<PayOrderVo>>() {
            });
            // 发起支付
            String orderMoney = getOrderMoney(list);
            JSONObject param = new JSONObject();
            param.put("appid", yml.getWxPublicAccountId());
            param.put("body", "MOTI-" + spuName);
            param.put("out_trade_no", orderNo);
            param.put("scene_info", yml.getWxWapPaySceneInfo());
            param.put("total_fee", orderMoney);
            if (trade_type.equals(WX_PAY_TRADE_TYPE_MWEB.getType())) {
                param.put("trade_type", WX_PAY_TRADE_TYPE_MWEB.getType());
            } else if (trade_type.equals(WX_PAY_TRADE_TYPE_JSAPI.getType())) {
                param.put("trade_type", WX_PAY_TRADE_TYPE_JSAPI.getType());
                String openId = redisService.get(REDIS_USER_WX_OPENID, getLoginUserId());
                if (StringUtil.isEmpty(openId)) {
                    JSONObject json = new JSONObject();
                    json.put("id", getLoginUserId());
                    String returnJson = callDataCenter(USER_GET_USER_BY_ID, json);
                    JSONObject userJson = JSONObject.parseObject(returnJson);
                    openId = userJson.getString("wx_open_id");
                    if (StringUtil.isEmpty(openId)) {
                        LOGGER.error("发起微信支付出错:", "获取用户openid为空！");
                        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
                    }
                }
                param.put("openId", openId);
            } else {
                param.put("trade_type", WX_PAY_TRADE_TYPE_APP.getType());
            }
            param.put("ip",ip);
            //调用微信支付接口
            String mWebUrl = callThirdCenter(WX_PAY_PAY, param);
            // 保存交易流水
            PayOrderVo orderVo = list.get(0);
            JSONObject detail = new JSONObject();
            detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
            detail.put("order_no", orderNo);
            String sellerId = orderVo.getSellerId();
            detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
            detail.put("payer", orderVo.getBuyerId());
            detail.put("money", orderMoney);
            detail.put("trade_type", TRADE_TYPE_PAY.getId());
            detail.put("trade_status", TRADE_STATUS_PAY_SUCCESS.getId());
            detail.put("payment_type", PAYMENT_TYPE_WX.getId());
            detail.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mWebUrl);
        } catch (Exception e) {
            LOGGER.error("发起微信支付出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 微信WAP支付状态
     * @Author taoye
     */
    @Override
    public ResponseJson wxPayStatus(String orderNo) {
        try {
            // 查询系统订单支付状态
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", orderNo);
            String orderJson = callDataCenter(ORDER_GET_ORDER, jsonObject);
            JSONObject order = JSONObject.parseObject(orderJson);
            int orderStatus = order.getIntValue("order_status");
            if (orderStatus >= Integer.valueOf(ORDER_STATUS_PENDING_DELIVERY.getId())) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, TRADE_STATUS_PAY_SUCCESS.getId());
            }
            // 查询微信订单支付状态
            jsonObject.put("out_trade_no", order.getString("order_no"));
            jsonObject.put("appid", yml.getWxPublicAccountId());
            String resultJson = callThirdCenter(WX_PAY_STATUS, jsonObject);
            JSONObject resultObject = JSONObject.parseObject(resultJson);
            String tradeStatus = resultObject.getString("trade_status");
            if (TRADE_STATUS_PAY_SUCCESS.getId().equals(tradeStatus)) {
                // 更新系统订单状态
                LOGGER.info("微信支付订单 修改活动订单状态为待支付状态 状态：{}",tradeStatus);
                JSONObject updateOrderJson =  new JSONObject();
                updateOrderJson.put("id", orderNo);
                updateOrderJson.put("order_status", ORDER_STATUS_PENDING_DELIVERY.getId());
                callDataCenter(ORDER_UPDATE_ORDER, updateOrderJson);
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", order.getString("order_money"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_WX.getId());
                /*detail.put("payment_no", resultObject.getString("transaction_id"));*/
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }else if(TRADE_STATUS_PAY_FALL.getId().equals(tradeStatus)){
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", order.getString("order_money"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_WX.getId());
                /*detail.put("payment_no", resultObject.getString("transaction_id"));*/
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeStatus);
        } catch (Exception e) {
            LOGGER.error("查询微信支付状态出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson confirmAuthorization(String code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        LOGGER.info("=============获取用户001userId:=============" + getLoginUserId());
        jsonObject.put("userId", getLoginUserId());
        try {
            callThirdCenter(WX_PAY_CONFIRM_AUTHORIZATION, jsonObject);
            LOGGER.info("mall调用三方授权登陆接口的返回值数据信息success");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.info("mall调用三方授权登陆接口异常", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getJsApiParam(String prePayId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("prepayId",prePayId);
        jsonObject.put("appId",yml.getWxPublicAccountId());
        jsonObject.put("merchantKey",yml.getWxMerchantKey());

        try {
            String param = callThirdCenter(WX_PAY_SIGN_AGAIN, jsonObject);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,param);
        } catch (Exception e) {
            LOGGER.error("获取微信预支付参数接口异常",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 获取订单总金额
     * @Author taoye
     */
    private String getOrderMoney(List<PayOrderVo> list) {
        Double money = 0.00;
        if (null == list || list.size() <= 0) {
            return String.valueOf(money);
        }
        for (PayOrderVo vo : list) {
            money += Double.valueOf(vo.getOrderMoney());
        }
        return String.valueOf(money);
    }

}
