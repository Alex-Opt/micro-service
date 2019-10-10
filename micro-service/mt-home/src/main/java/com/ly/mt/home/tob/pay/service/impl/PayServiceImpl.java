package com.ly.mt.home.tob.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.TradeStatus;
import com.ly.mt.core.base.dict.TradeType;
import com.ly.mt.core.base.dict.WxPayTradeType;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.ThirdCenterMethod;
import com.ly.mt.home.base.config.YmlConfig;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.pay.service.PayService;
import com.ly.mt.home.tob.pay.vo.PaymentDetailVo;
import com.ly.mt.home.tob.purchases.service.PurchasesService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.base.dict.WxPayTradeType.WX_PAY_TRADE_TYPE_APP;
import static com.ly.mt.core.base.dict.WxPayTradeType.WX_PAY_TRADE_TYPE_JSAPI;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_PAY_CONFIRM_AUTHORIZATION;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_PAY_PAY;
import static com.ly.mt.core.redis.RedisKey.REDIS_USER_WX_OPENID;
import static com.ly.mt.home.base.constant.PaymentModel.PAYMENT_MODEL_AL;
import static com.ly.mt.home.base.constant.PaymentModel.PAYMENT_MODEL_WX;

/**
 * @author linan
 * @description : 支付接口
 * @date 2019/7/17
 */
@Service
public class PayServiceImpl extends BaseServiceImpl implements PayService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    PurchasesService purchasesService;

    @Resource
    YmlConfig ymlConfig;

    /**
     * @description: 支付宝WAP支付
     * @author: linan
     * @date: 2019/7/17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String alPay(String orderNo, String returnUrl, String tradeType) {
        ShopPurchasesVo purchasesVo = purchasesService.getPurchases(orderNo, getLoginShopId());
        try {
            purchasesService.checkPurchases(purchasesVo.getId());

            JSONObject param = new JSONObject();
            param.put("out_trade_no", orderNo);
            param.put("return_url", returnUrl);
            param.put("spu_name", "MOTI电子烟");
            param.put("total_amount", 0.01);
            //param.put("total_amount", purchasesVo.getActualAmount());
            param.put("trade_type", tradeType);

            String form;
            // TODO: 2019/9/20 optimize constant
            if(WX_PAY_TRADE_TYPE_APP.getType().equals(tradeType)) {
                form = callThirdCenter(ThirdCenterMethod.AL_PAY_APP_PAY, param);
            } else {
                form = callThirdCenter(ThirdCenterMethod.AL_PAY_WAP_PAY, param);
            }

            // add paymentDetail
            PaymentDetailVo paymentVo = this.createPaymentDetailVo(purchasesVo);
            paymentVo.setPaymentType(PAYMENT_TYPE_AL.getId());
            JSONObject paymentJson = (JSONObject) JSON.toJSON(paymentVo);
            callDataCenter(DataCenterMethod.PAYMENT_DETAIL_INSERT, paymentJson);

            // update purchases pay_model
            updateShopPurchases(orderNo, PAYMENT_MODEL_AL.getId());

            logger.info("alpay form: {}", form);
            return form;
        } catch (Exception e) {
            logger.error("PayServiceImpl.alPay():{}", e);
            throw new MTException("订单异常，请重新提交订单");
        }
    }

    /**
     * @description: 微信WAP支付
     * @author: linan
     * @date: 2019/7/17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String wxPay(String orderNo, String tradeType) {
        ShopPurchasesVo purchasesVo = purchasesService.getPurchases(orderNo, getLoginShopId());

        if(!WxPayTradeType.checkTradeType(tradeType)){
            throw new MTException("支付类型异常");
        }
        try {
            purchasesService.checkPurchases(purchasesVo.getId());

            JSONObject param = new JSONObject();
            param.put("out_trade_no", orderNo);
            param.put("total_fee", 0.01);
            //param.put("total_fee", purchasesVo.getActualAmount());
            param.put("ip", getLoginUserIp());
            param.put("app_id", ymlConfig.getWxPublicAccountId());
            param.put("scene_info", ymlConfig.getWxWapPaySceneInfo());
            param.put("body", "MOTI电子烟");
            param.put("trade_type", tradeType);
            if (tradeType.equals(WX_PAY_TRADE_TYPE_JSAPI.getType())) {
                String openId = redisService.get(REDIS_USER_WX_OPENID, getLoginUserId());
                param.put("openId", openId);
            }

            String mWebUrl = callThirdCenter(WX_PAY_PAY, param);
            // add paymentDetail
            PaymentDetailVo paymentVo = this.createPaymentDetailVo(purchasesVo);
            paymentVo.setPaymentType(PAYMENT_TYPE_WX.getId());
            JSONObject jsonObject = (JSONObject) JSON.toJSON(paymentVo);
            callDataCenter(DataCenterMethod.PAYMENT_DETAIL_INSERT, jsonObject);

            // update purchases pay_model
            updateShopPurchases(orderNo, PAYMENT_MODEL_WX.getId());

            logger.info("wxpay mweburl: {}", mWebUrl);
            return mWebUrl;
        } catch (Exception e) {
            logger.error("PayServiceImpl.wxPay():{}", e);
            throw new MTException("订单异常，请重新提交订单");
        }
    }

    @Override
    public Map jsapiPay(String code, String orderNo) {
        try {
            // query shop_purchases
            ShopPurchasesVo purchasesVo = purchasesService.getPurchases(orderNo, getLoginShopId());
            logger.info("purchasesVo:{}", purchasesVo.toString());
            if (null == purchasesVo) {
                throw new MTException("订单异常");
            }

            String openId = redisService.get(REDIS_USER_WX_OPENID, getLoginUserId());
            if(StringUtils.isEmpty(openId)){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", code);
                jsonObject.put("userId", getLoginUserId());
                // wx auth
                callThirdCenter(WX_PAY_CONFIRM_AUTHORIZATION, jsonObject);
                logger.info("home 调用三方授权登陆接口的返回值数据信息success");
            }

            // get package(prepay_id)
            String prepayId = this.wxPay(orderNo, WX_PAY_TRADE_TYPE_JSAPI.getType());

            // jsapi param
            String nonceStr = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM);
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.put("appid", ymlConfig.getWxPublicAccountId());
            treeMap.put("timeStamp", time);
            treeMap.put("nonce_str", nonceStr);
            treeMap.put("package", "prepay_id=".concat(prepayId));
            treeMap.put("paySign", generateSignMD52(time, nonceStr, prepayId));
            logger.info("jsapi pay param:{}", treeMap);
            return treeMap;
        } catch (Exception e) {
            logger.error("home jsapi支付异常", e);
            throw new MTException("订单异常，请重新提交订单");
        }
    }

    /**
     * 订单支付状态
     *
     * @param orderNo
     * @return
     */
    @Override
    public JSONObject status(String orderNo) {
        ShopPurchasesVo purchasesVo = purchasesService.getPurchases(orderNo, getLoginShopId());

        Assert.notNull(purchasesVo, "订单不存在");
        String type = purchasesVo.getPayedModel();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", orderNo);
        String result = null;
        try {
            if (type.equals(PAYMENT_MODEL_AL.getId())) {
                result = callThirdCenter(ThirdCenterMethod.AL_PAY_STATUS, jsonObject);
            } else if (type.equals(PAYMENT_MODEL_WX.getId())) {
                jsonObject.put("appid", ymlConfig.getWxPublicAccountId());
                result = callThirdCenter(ThirdCenterMethod.WX_PAY_STATUS, jsonObject);
            } else {
                throw new IllegalArgumentException("订单尚未发起支付");
            }

            logger.info("pay status: {}", result);
            Assert.hasText(result, "订单查询异常");
            JSONObject resultJson = JSONObject.parseObject(result);

            JSONObject returnJson = new JSONObject();
            returnJson.put("code", resultJson.getString("trade_status"));
            returnJson.put("msg", TradeStatus.getTradeStatusById(resultJson.getString("trade_status")).getName());
            returnJson.put("amount", purchasesVo.getActualAmount());
            returnJson.put("type", type);
            return returnJson;
        } catch (Exception e) {
            throw new MTException("获取订单状态异常");
        }
    }

    /**
     * @description:
     * @author: linan
     * @date: 2019/7/17
     */
    private PaymentDetailVo createPaymentDetailVo(ShopPurchasesVo purchasesVo) {
        return new PaymentDetailVo.Builder()
                .id(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_PAYMENT_DETAIL))
                .orderNo(purchasesVo.getId())
                .payee(StringUtils.isEmpty(purchasesVo.getSellerId()) ? null : purchasesVo.getSellerId())
                .payer(purchasesVo.getUserId())
                .money(purchasesVo.getActualAmount())
                .tradeType(TradeType.TRADE_TYPE_PAY.getId())
                .tradeStatus(TradeStatus.TRADE_STATUS_PAY_CREATE.getId())
                .createTime(DateUtil.getNowTimeStr()).build();
    }

    /**
     * @description:
     * @author: linan
     * @date: 2019/7/17
     */
    private void updateShopPurchases(String orderNo, String code) {
        JSONObject purchasesJson = new JSONObject();
        purchasesJson.put("id", orderNo);
        purchasesJson.put("payed_model", code);
        //purchasesJson.put("status", ShopPurchasesEnum.SHOP_PURCHASES_TO_SEND.getCode());
        purchasesJson.put("motify_time", DateUtil.getNowTimeStr());
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_UPDATE, purchasesJson);
    }

    private String generateSign(SortedMap<String, String> maps, String key) throws Exception {
        StringBuffer sbkey = new StringBuffer();
        Set es = maps.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sbkey.append(k + "=" + v + "&");
            }
        }
        sbkey = sbkey.append("key=" + key);
        logger.info("MD5加密的字符串{}", sbkey.toString());
        //MD5加密,结果转换为大写字符
        String sign = MD5Util.md5(sbkey.toString()).toUpperCase();
        System.out.println("MD5加密值后sign =" + sign);
        return sign;


    }

    private String generateSignMD52(String timeStamp, String nonce_str, String prepay_id) throws Exception {
        SortedMap<String, String> maps = new TreeMap<String, String>();
        maps.put("appId", ymlConfig.getWxPublicAccountId());
        maps.put("timeStamp", timeStamp);
        maps.put("nonceStr", nonce_str);
        maps.put("package", "prepay_id=" + prepay_id);
        maps.put("signType", "MD5");
        logger.info("二次签名的值：{}", maps);
        return generateSign(maps, ymlConfig.getWxMerchantKey());
    }
}