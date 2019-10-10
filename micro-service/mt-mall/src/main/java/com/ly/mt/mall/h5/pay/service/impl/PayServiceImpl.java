package com.ly.mt.mall.h5.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.config.YmlConfig;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.pay.service.PayService;
import com.ly.mt.mall.h5.pay.vo.PayOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.dict.TradeType.TRADE_TYPE_PAY;
import static com.ly.mt.core.base.dict.WxAppletTemplateSendType.*;
import static com.ly.mt.core.base.dict.WxPayTradeType.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_USER_WX_OPENID;

/**
 * @Description 支付接口
 * @Author taoye
 */
@Service
public class PayServiceImpl extends BaseServiceImpl implements PayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
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
            param.put("passback_params", "mt-mall");
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
            jsonObject.put("order_no", orderNo);
            String orderJson = callDataCenter(ORDER_GET_BY_ORDERNO, jsonObject);
            JSONObject order = JSONObject.parseObject(orderJson);
            int orderStatus = order.getIntValue("order_status");
            if (orderStatus >= Integer.valueOf(ORDER_STATUS_PENDING_DELIVERY.getId())) {
                jsonObject.put("tradeStatus", TRADE_STATUS_PAY_SUCCESS.getId());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
            }
            // 查询阿里订单支付状态
            jsonObject.put("out_trade_no", orderNo);
            String resultJson = callThirdCenter(AL_PAY_STATUS, jsonObject);
            JSONObject resultObject = JSONObject.parseObject(resultJson);
            String tradeStatus = resultObject.getString("trade_status");
            if (StringUtil.isNotEmpty(tradeStatus)) {
                // 更新系统订单支付状态
                order.put("order_status", ORDER_STATUS_PENDING_DELIVERY);
                callDataCenter(ORDER_UPDATE_ORDER, order);
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", resultObject.getString("total_amount"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_AL.getId());
                detail.put("payment_no", resultObject.getString("trade_no"));
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }
            jsonObject.put("tradeStatus", tradeStatus);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
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
    public ResponseJson wxPay(String orderNo, String spuName, String trade_type) {
        String openId = null;
        String tradeType = "JSAPI";
        JSONObject templateMap = new JSONObject();
        Map map = new HashMap(5);
        map.put("keyword3", spuName);
        map.put("keyword5", "MOTI官方商城");
        map.put("keyword4", DateUtil.getNowTimeStr());
        try {
            // 查询订单金额
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_no", orderNo);
            map.put("keyword6", orderNo);
            String orderListJson = callDataCenter(ORDER_LIST_ORDER, jsonObject);
            LOGGER.info("============================入参：" + jsonObject + "==================================查询订单的返回值信息：" + orderListJson + "======================");
            if (StringUtil.isEmpty(orderListJson)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            List<PayOrderVo> list = JSON.parseObject(orderListJson, new TypeReference<List<PayOrderVo>>() {
            });
            // 发起支付
            String orderMoney = getOrderMoney(list);
            map.put("keyword2", orderMoney);
            JSONObject param = new JSONObject();
            if (trade_type.equals(WX_PAY_TRADE_TYPE_APPLET.getType())) {
                //微信小程序是单独的
                param.put("appid", yml.getWxAppletAccountId());
            } else if (trade_type.equals(WX_PAY_TRADE_TYPE_APP.getType())) {
                //APP的支付appId也是单独的。
                param.put("appid", yml.getWxAppAccountId());
            } else {
                param.put("appid", yml.getWxPublicAccountId());
            }
            param.put("body", "MOTI-" + spuName);
            param.put("out_trade_no", orderNo);
            param.put("attach", "mt-mall");
            param.put("scene_info", yml.getWxWapPaySceneInfo());
            param.put("total_fee", orderMoney);
            param.put("ip", getLoginUserIp());
            if (trade_type.equals(WX_PAY_TRADE_TYPE_MWEB.getType())) {
                param.put("trade_type", WX_PAY_TRADE_TYPE_MWEB.getType());
                tradeType = "MWEB";
            } else if (trade_type.equals(WX_PAY_TRADE_TYPE_APP.getType())) {
                param.put("trade_type", WX_PAY_TRADE_TYPE_APP.getType());
                tradeType = "APP";
            } else {
                param.put("trade_type", WX_PAY_TRADE_TYPE_JSAPI.getType());
                openId = redisService.get(REDIS_USER_WX_OPENID, getLoginUserId());
                if (StringUtil.isEmpty(openId)) {
                    JSONObject json = new JSONObject();
                    json.put("id", getLoginUserId());
                    String returnJson = callDataCenter(USER_GET_USER_BY_ID, json);
                    JSONObject userJson = JSONObject.parseObject(returnJson);
                    LOGGER.info("--------------------------------查询返回的用户信息：{}", returnJson);
                    openId = userJson.getString("wx_open_id") == null ? userJson.getString("wxOpenId") : userJson.getString("wx_open_id");
                    if (StringUtil.isEmpty(openId)) {
                        LOGGER.error("--------------------------发起微信支付出错，获取用户openid为空！");
                        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
                    }
                }
                param.put("openId", openId);
            }
            //调用微信支付接口
            String mWebUrl = callThirdCenter(WX_PAY_PAY, param);
            map.put("keyword1", "支付成功");
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
            map.put("keyword1", "支付失败");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        } finally {
            //支付成功的微信小程序模版通知,这里的统一支付接口，在微信小程序中还没有支付。故不能执行下面的逻辑。对H5支付放开。
            if (StringUtil.isNotEmpty(openId) && !tradeType.equals(WX_PAY_TRADE_TYPE_JSAPI.getType())) {
                //微信小程序发送模版用的入参
                templateMap.put("openId", openId);
                templateMap.put("templateId", TEMPLATE_SEND_TYPE_PAY_RESULT.getId());
                templateMap.put("data", map);
                try {
                    callThirdCenter(WX_TEMPLATE_MESSAGE_SEND, templateMap);
                } catch (Exception e) {
                    LOGGER.error("微信小程序发送模版---------------支付场景-------出现异常：{}", e);
                }
            }
        }
    }

    /**
     * 微信小程序的再次签名
     *
     * @param prePayId
     * @return
     */
    @Override
    public ResponseJson paySignAgain(String prePayId, String payType) {
        //如果是小程序支付或者微信内浏览器支付，则需要调用统一下单接口后，商户server调用再次签名
        try {
            JSONObject signAgainJson = new JSONObject();
            signAgainJson.put("prepayId", prePayId);
            signAgainJson.put("merchantKey", yml.getWxMerchantKey());
            if (WX_PAY_TRADE_TYPE_APPLET.getType().equals(payType)) {
                //这里的appid用的是小程序的appId
                signAgainJson.put("appId", yml.getWxAppletAccountId());
            } else if (WX_PAY_TRADE_TYPE_JSAPI.getType().equals(payType) || WX_PAY_TRADE_TYPE_MWEB.getType().equals(payType)) {
                signAgainJson.put("appId", yml.getWxPublicAccountId());
            } else {
                signAgainJson.put("appId", yml.getWxAppAccountId());
            }
            LOGGER.info("再次签名的入参signAgainJson：" + signAgainJson);
            String payParam = callThirdCenter(WX_PAY_SIGN_AGAIN, signAgainJson);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, payParam);
        } catch (Exception e) {
            LOGGER.error("微信小程序的再次签名接口异常", e);
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
            jsonObject.put("order_no", orderNo);
            String orderJson = callDataCenter(ORDER_GET_BY_ORDERNO, jsonObject);
            JSONObject order = JSONObject.parseObject(orderJson);
            int orderStatus = order.getIntValue("order_status");
            if (orderStatus >= Integer.valueOf(ORDER_STATUS_PENDING_DELIVERY.getId())) {
                jsonObject.put("tradeStatus", TRADE_STATUS_PAY_SUCCESS.getId());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
                //return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, TRADE_STATUS_PAY_SUCCESS.getId());
            }
            // 查询微信订单支付状态
            jsonObject.put("out_trade_no", orderNo);
            jsonObject.put("appid", yml.getWxPublicAccountId());
            String resultJson = callThirdCenter(WX_PAY_STATUS, jsonObject);
            JSONObject resultObject = JSONObject.parseObject(resultJson);
            String tradeStatus = resultObject.getString("trade_status");
            if (StringUtil.isNotEmpty(tradeStatus)) {
                // 更新系统订单支付状态
                jsonObject.put("order_status", ORDER_STATUS_PENDING_DELIVERY);
                callDataCenter(ORDER_UPDATE_ORDER, jsonObject);
                // 保存交易流水
                JSONObject detail = new JSONObject();
                detail.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_PAYMENT_DETAIL));
                detail.put("order_no", order.getString("order_no"));
                String sellerId = order.getString("seller_id");
                detail.put("payee", StringUtil.isNotEmpty(sellerId) ? sellerId : null);
                detail.put("payer", order.getString("buyer_id"));
                detail.put("money", resultObject.getString("total_fee"));
                detail.put("trade_type", TRADE_TYPE_PAY.getId());
                detail.put("trade_status", tradeStatus);
                detail.put("payment_type", PAYMENT_TYPE_WX.getId());
                detail.put("payment_no", resultObject.getString("transaction_id"));
                detail.put("create_time", DateUtil.getNowTimeStr());
                callDataCenter(PAYMENT_DETAIL_INSERT, detail);
            }
            jsonObject.put("tradeStatus", tradeStatus);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
            //return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tradeStatus);
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