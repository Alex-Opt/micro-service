package com.ly.mt.activity.advertisement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.service.AdvertisementOrderService;
import com.ly.mt.activity.advertisement.vo.Gift;
import com.ly.mt.activity.advertisement.vo.PageOrderVo;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.DistributeType;
import com.ly.mt.core.base.dict.OrderStatus;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.redis.dict.AlSmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.AL_SMS_SEND;


@Service
public class AdvertisementOrderServiceImpl extends BaseServiceImpl implements AdvertisementOrderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdvertisementOrderServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseJson bookingActivityOrder(JSONObject paramObject) throws Exception{
        PageOrderVo pageOrder = JSONObject.toJavaObject(paramObject, PageOrderVo.class);
        //下单
        String currId = getCurrentUserId(pageOrder);
        //插入
        String addressId = insertAddress(pageOrder, currId);
        //计算价格
        JSONObject goodsSkuJson = new JSONObject();
        goodsSkuJson.put("id",pageOrder.getTobaccoSku());
        JSONObject skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
        String tobaccoSkuPrice =  skuResult.getString("market_price");
        String tobaccoSpu = skuResult.getString("spu_id");
        String tobaccoSkuName = skuResult.getString("name");
        BigDecimal cartridgesOriginPrice = BigDecimal.ZERO;
        String cartridgesSkuPrice = "";String cartridgesSpu = "";String cartridgesSkuName = "";
        if(!"".equals(pageOrder.getCartridgesSku())){
            goodsSkuJson.put("id",pageOrder.getCartridgesSku());
            skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
             cartridgesSkuPrice = skuResult.getString("market_price");
             cartridgesSpu = skuResult.getString("spu_id");
            cartridgesSkuName = skuResult.getString("name");
            //得到烟弹原始总价
            cartridgesOriginPrice = (new BigDecimal(cartridgesSkuPrice).multiply(new BigDecimal(pageOrder.getCartridgesSkuNum())));
        }
        //得到订单原始总价
        BigDecimal tobaccoOriginPrice = (new BigDecimal(tobaccoSkuPrice).multiply(new BigDecimal(pageOrder.getTobaccoSkuNum())));
        BigDecimal originPrice = tobaccoOriginPrice.add(cartridgesOriginPrice);
        String orderPrice = originPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        Map<String,String> map = new ConcurrentHashMap<>(3);
        //生成订单
        try {
            LOGGER.info("====================活动订单下单开始========================");
            JSONObject tradeOrderJsonObj = new JSONObject();
            String orderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDERS);
            String orderNo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ORDER_NO);
            tradeOrderJsonObj.put("id",orderId);
            tradeOrderJsonObj.put("order_no",orderNo);
            tradeOrderJsonObj.put("buyer_id",currId);
            tradeOrderJsonObj.put("address_id",addressId);
            tradeOrderJsonObj.put("order_type","3");
            tradeOrderJsonObj.put("order_source",pageOrder.getSource());
            tradeOrderJsonObj.put("order_old_money",orderPrice);
            tradeOrderJsonObj.put("order_money",orderPrice);
            tradeOrderJsonObj.put("order_discount_money","0");
            tradeOrderJsonObj.put("distribution_id",("3".equals(pageOrder.getPaymentType()) || "2".equals(pageOrder.getPaymentType()) )?DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId():"4");
            tradeOrderJsonObj.put("order_status",(pageOrder.getPaymentType().equals("6"))?
                    OrderStatus.ORDER_STATUS_PENDING_DELIVERY.getId()
                    :OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId());
            tradeOrderJsonObj.put("create_time",DateUtil.getNowTimeStr());
            tradeOrderJsonObj.put("payment_type",pageOrder.getPaymentType());
            tradeOrderJsonObj.put("freight","0");
            tradeOrderJsonObj.put("push_status","1");
            tradeOrderJsonObj.put("order_yn","1");
            tradeOrderJsonObj.put("is_refund","1");
            tradeOrderJsonObj.put("locked","1");
            callDataCenter(DataCenterMethod.TRADE_ORDERS_INSERT, tradeOrderJsonObj);
            //插入订单媒体表
            JSONObject mediaJson = new JSONObject();
            mediaJson.put("id",SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_MEDIA));
            mediaJson.put("order_id",orderId);
            mediaJson.put("type",pageOrder.getType());
            mediaJson.put("material",pageOrder.getMaterial());
            mediaJson.put("channel",pageOrder.getChannel());
            mediaJson.put("create_time",DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_MEDIA_INSERT,mediaJson);
            //if("0".equals(insertCode)){
                //插入明细表
            String tobaccoSpuName = insertTradeOrderItem(pageOrder.getTobaccoSku(), pageOrder.getTobaccoSkuNum(), tobaccoSkuPrice, tobaccoSpu, tobaccoSkuName, orderId);
            if(!"".equals(pageOrder.getCartridgesSku())){
                    insertTradeOrderItem(pageOrder.getCartridgesSku(), pageOrder.getCartridgesSkuNum(), cartridgesSkuPrice, cartridgesSpu, cartridgesSkuName, orderId);
                }
                map.put("id", orderId);
                map.put("orderNo", orderNo);
                map.put("spuName", tobaccoSpuName);
                LOGGER.info("========================活动订单下单完成============================");
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,map);
           // }
        } catch (Exception e) {
            LOGGER.error("===============================活动下单生成异常===========================");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseJson bookingActivityOrder2c(JSONObject paramObject) throws Exception {
        PageOrderVo pageOrder = JSONObject.toJavaObject(paramObject, PageOrderVo.class);
        //下单
        //将地址信息存入地址表
        String currId = getCurrentUserId(pageOrder);
        //插入
        String addressId = insertAddress(pageOrder, currId);
        //计算价格
        JSONObject goodsSkuJson = new JSONObject();
        goodsSkuJson.put("id",pageOrder.getTobaccoSku());
        JSONObject skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
        String tobaccoSkuPrice =  "";
        //判断是否有烟杆价格
        if(StringUtil.isEmpty(pageOrder.getTobaccoSkuPrice())){
            tobaccoSkuPrice =  skuResult.getString("market_price");
        }else{
            tobaccoSkuPrice = pageOrder.getTobaccoSkuPrice();
        }
        String tobaccoSpu = skuResult.getString("spu_id");
        String tobaccoSkuName = skuResult.getString("name");
        BigDecimal cartridgesOriginPrice = BigDecimal.ZERO;
        String cartridgesSkuPrice = "";String cartridgesSpu = "";String cartridgesSkuName = "";
        if(!"".equals(pageOrder.getCartridgesSku())){
            goodsSkuJson.put("id",pageOrder.getCartridgesSku());
            skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
            if(StringUtil.isEmpty(pageOrder.getCartridgesSkuPrice())){
                cartridgesSkuPrice =  skuResult.getString("market_price");
            }else{
                cartridgesSkuPrice = pageOrder.getCartridgesSkuPrice();
            }
            //cartridgesSkuPrice = skuResult.getString("market_price");
            cartridgesSpu = skuResult.getString("spu_id");
            cartridgesSkuName = skuResult.getString("name");
            //得到烟弹原始总价
            cartridgesOriginPrice = (new BigDecimal(cartridgesSkuPrice).multiply(new BigDecimal(pageOrder.getCartridgesSkuNum())));
        }
        //得到订单原始总价
        BigDecimal tobaccoOriginPrice = (new BigDecimal(tobaccoSkuPrice).multiply(new BigDecimal(pageOrder.getTobaccoSkuNum())));
        BigDecimal originPrice = tobaccoOriginPrice.add(cartridgesOriginPrice);
        String orderPrice = originPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        Map<String,String> map = new ConcurrentHashMap<>(3);
        //生成订单
        try {
            LOGGER.info("====================活动订单下单开始========================");
            JSONObject tradeOrderJsonObj = new JSONObject();
            String orderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDERS);
            String orderNo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ORDER_NO);
            tradeOrderJsonObj.put("id",orderId);
            tradeOrderJsonObj.put("order_no",orderNo);
            tradeOrderJsonObj.put("buyer_id",currId);
            tradeOrderJsonObj.put("address_id",addressId);
            tradeOrderJsonObj.put("order_type","3");
            tradeOrderJsonObj.put("order_source",pageOrder.getSource());
            tradeOrderJsonObj.put("order_old_money",orderPrice);
            tradeOrderJsonObj.put("order_money",orderPrice);
            tradeOrderJsonObj.put("order_discount_money","0");
            tradeOrderJsonObj.put("distribution_id",("3".equals(pageOrder.getPaymentType()) || "2".equals(pageOrder.getPaymentType()) )?DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId():"4");
            tradeOrderJsonObj.put("order_status",(pageOrder.getPaymentType().equals("6"))?
                    OrderStatus.ORDER_STATUS_PENDING_DELIVERY.getId()
                    :OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId());
            tradeOrderJsonObj.put("create_time",DateUtil.getNowTimeStr());
            tradeOrderJsonObj.put("payment_type",pageOrder.getPaymentType());
            tradeOrderJsonObj.put("freight","0");
            tradeOrderJsonObj.put("push_status","1");
            tradeOrderJsonObj.put("order_yn","1");
            tradeOrderJsonObj.put("is_refund","1");
            tradeOrderJsonObj.put("locked","1");
            tradeOrderJsonObj.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_INSERT, tradeOrderJsonObj);
            //插入订单媒体表
            JSONObject mediaJson = new JSONObject();
            mediaJson.put("id",SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_MEDIA));
            mediaJson.put("order_id",orderId);
            mediaJson.put("type",pageOrder.getType());
            mediaJson.put("material",pageOrder.getMaterial());
            mediaJson.put("channel",pageOrder.getChannel());
            mediaJson.put("create_time",DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_MEDIA_INSERT,mediaJson);
            //插入明细表
            String tobaccoSpuName = insertTradeOrderItem(pageOrder.getTobaccoSku(),
                                                                pageOrder.getTobaccoSkuNum(), tobaccoSkuPrice, tobaccoSpu, tobaccoSkuName, orderId);
            if(!"".equals(pageOrder.getCartridgesSku())){
                insertTradeOrderItem(pageOrder.getCartridgesSku(), pageOrder.getCartridgesSkuNum(), cartridgesSkuPrice, cartridgesSpu, cartridgesSkuName, orderId);
            }
            map.put("id", orderId);
            map.put("address", addressId);
            map.put("orderNo", orderNo);
            map.put("spuName", tobaccoSpuName);
            map.put("orderTime",tradeOrderJsonObj.getString("create_time"));
            map.put("paymentType",("6".equals(pageOrder.getPaymentType())?"货到付款":"3".equals(pageOrder.getPaymentType())?"支付宝支付":"微信支付"));
            map.put("deliveryInfo","有货，免费包邮");
            map.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            LOGGER.info("========================活动订单下单完成============================");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,map);
            // }
        } catch (Exception e) {
            LOGGER.error("===============================活动下单生成异常===========================");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    private String insertAddress(PageOrderVo pageOrder,String userId) {
        JSONObject addressJson = new JSONObject();
        addressJson.put("user_id", userId);
        addressJson.put("user_name", pageOrder.getUserName());
        addressJson.put("receive_name", pageOrder.getUserName());
        addressJson.put("receive_phone", pageOrder.getMobile());
        addressJson.put("create_time", DateUtil.getNowTimeStr());
        addressJson.put("user_address", pageOrder.getAddress());
        addressJson.put("province_code", pageOrder.getProvinceCode());
        addressJson.put("province_name", pageOrder.getProvinceName());
        addressJson.put("city_code", pageOrder.getCityCode());
        addressJson.put("city_name", pageOrder.getCityName());
        addressJson.put("district_name", pageOrder.getDistrictName());
        addressJson.put("district_code", pageOrder.getDistrictCode());
        String addressId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_ADDRESS);
        addressJson.put("id", addressId);
        callDataCenter(DataCenterMethod.USERADDRESS_INSERT, addressJson);
        return addressId;
    }

    @Override
    public ResponseJson getOrderInfo2c(JSONObject paramObject) {
        JSONObject param = new JSONObject();
        param.put("id",Long.parseLong(paramObject.getString("id")));
         return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_LIST,param));
}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseJson bookingGghdOrderLittleSmoke(JSONObject paramObject) {
        PageOrderVo pageOrder = JSONObject.toJavaObject(paramObject, PageOrderVo.class);
        String currId = getCurrentUserId(pageOrder);
        //每人限购一个
        if ("0".equals(pageOrder.getTobaccoSkuPrice())||
                "1".equals(pageOrder.getTobaccoSkuPrice())||
                "3".equals(pageOrder.getTobaccoSkuPrice())
        ||"9".equals(pageOrder.getTobaccoSkuPrice())){
            JSONObject param = new JSONObject();
            param.put("userId",currId);
            param.put("skuId",pageOrder.getTobaccoSku());
          //MTGO限购条件
            if("112492575751".equals(pageOrder.getTobaccoSku())||
                "112492575752".equals(pageOrder.getTobaccoSku()) ||
                    "112492575754".equals(pageOrder.getTobaccoSku())){
                //查询购买数量
                String resul = callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_MTGO_ZEROPRICE_COUNT, param);
                if(Integer.parseInt(resul) >= 1){
                    return  ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,"MTGO您已购买");
                }
                //小烟 1元 3元 9元限购条件
            }else if("112492576930".equals(pageOrder.getTobaccoSku()) ||
                    "112492582466".equals(pageOrder.getTobaccoSku())||
                    "112492578023".equals(pageOrder.getTobaccoSku())||
                    "112492576122".equals(pageOrder.getTobaccoSku())||
                    "112492576116".equals(pageOrder.getTobaccoSku())||
                    "112492578055".equals(pageOrder.getTobaccoSku())) {
                String result = callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_LITTLE_SMOKE_COUNT, param);
                if(Integer.parseInt(result) >= 1){
                    return  ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,"您已购买");
                }
            }else{
                //套装限购条件
                String result = callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_TAOZHUANG_ZEROPRICE_COUNT, param);
                if(Integer.parseInt(result) >= 1){
                    return  ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,"您已购买此套装");
                }
            }
        }
        //插入地址
        String addressId = insertAddress(pageOrder,currId);
        //计算价格
        JSONObject goodsSkuJson = new JSONObject();
        goodsSkuJson.put("id",pageOrder.getTobaccoSku());
        JSONObject skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
       // String tobaccoSkuPrice =  skuResult.getString("market_price");
        String tobaccoSpu = skuResult.getString("spu_id");
        String tobaccoSkuName = skuResult.getString("name");
        BigDecimal cartridgesOriginPrice = BigDecimal.ZERO;
        //如果烟弹不为空
        String cartridgesSpu = "";
        String cartridgesSkuName = "";
        if(StringUtil.isNotEmpty(pageOrder.getCartridgesSku())
                && StringUtil.isNotEmpty(pageOrder.getCartridgesSkuPrice())
                && StringUtil.isNotEmpty(pageOrder.getCartridgesSkuNum())){
            goodsSkuJson.put("id",pageOrder.getCartridgesSku());
            skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
            cartridgesSpu = skuResult.getString("spu_id");
            cartridgesSkuName = skuResult.getString("name");
            cartridgesOriginPrice = (new BigDecimal(pageOrder.getCartridgesSkuPrice()).multiply(new BigDecimal(pageOrder.getCartridgesSkuNum())));
        }

        BigDecimal tobaccoOriginPrice = (new BigDecimal(pageOrder.getTobaccoSkuPrice()).multiply(new BigDecimal(pageOrder.getTobaccoSkuNum())));
        BigDecimal orderMoney = tobaccoOriginPrice.add(cartridgesOriginPrice);
        String orderPrice = orderMoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        Map<String,String> map = new ConcurrentHashMap<>(3);
        //生成订单
        try {
            LOGGER.info("====================活动订单小烟下单开始========================");
            JSONObject tradeOrderJsonObj = new JSONObject();
            String orderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDERS);
            String orderNo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ORDER_NO);
            tradeOrderJsonObj.put("id",orderId);
            tradeOrderJsonObj.put("order_no",orderNo);
            tradeOrderJsonObj.put("buyer_id",currId);
            tradeOrderJsonObj.put("address_id",addressId);
            tradeOrderJsonObj.put("order_type","3");
            tradeOrderJsonObj.put("order_source",pageOrder.getSource());
            tradeOrderJsonObj.put("order_old_money",orderPrice);
            tradeOrderJsonObj.put("order_money",orderPrice);
            tradeOrderJsonObj.put("order_discount_money","0");
            tradeOrderJsonObj.put("distribution_id",("3".equals(pageOrder.getPaymentType()) || "2".equals(pageOrder.getPaymentType()) )?DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId():"4");
            tradeOrderJsonObj.put("order_status",(pageOrder.getPaymentType().equals("6"))?
                    OrderStatus.ORDER_STATUS_PENDING_DELIVERY.getId()
                    :OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId());
            tradeOrderJsonObj.put("create_time",DateUtil.getNowTimeStr());
            tradeOrderJsonObj.put("payment_type",pageOrder.getPaymentType());
            tradeOrderJsonObj.put("freight","0");
            tradeOrderJsonObj.put("push_status","1");
            tradeOrderJsonObj.put("order_yn","1");
            tradeOrderJsonObj.put("is_refund","1");
            tradeOrderJsonObj.put("locked","1");
            tradeOrderJsonObj.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_INSERT, tradeOrderJsonObj);
            //插入订单媒体表
            JSONObject mediaJson = new JSONObject();
            mediaJson.put("id",SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_MEDIA));
            mediaJson.put("order_id",orderId);
            mediaJson.put("type",pageOrder.getType());
            mediaJson.put("material",pageOrder.getMaterial());
            mediaJson.put("channel",pageOrder.getChannel());
            mediaJson.put("create_time",DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_MEDIA_INSERT,mediaJson);
            //插入明细表
            String tobaccoSpuName = insertTradeOrderItem(pageOrder.getTobaccoSku(), pageOrder.getTobaccoSkuNum(),
                    pageOrder.getTobaccoSkuPrice(), tobaccoSpu, tobaccoSkuName, orderId);
            if(StringUtil.isNotEmpty(pageOrder.getCartridgesSku())){
                insertTradeOrderItem(pageOrder.getCartridgesSku(),pageOrder.getCartridgesSkuNum(),pageOrder.getCartridgesSkuPrice(),cartridgesSpu,cartridgesSkuName,orderId);
            }
            map.put("id", orderId);
            map.put("orderNo", orderNo);
            map.put("spuName", tobaccoSpuName);
            map.put("orderTime",tradeOrderJsonObj.getString("create_time"));
            map.put("paymentType",("6".equals(pageOrder.getPaymentType())?"货到付款":"3".equals(pageOrder.getPaymentType())?"支付宝支付":"微信支付"));
            map.put("deliveryInfo","有货，免费包邮");
            map.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            LOGGER.info("========================活动订单小烟下单结束============================");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,map);
            // }
        } catch (Exception e) {
            LOGGER.error("===============================活动订单小烟下单异常===========================");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public void sendSms(String orderNo,String mobile) throws Exception{
        JSONObject smsJson = new JSONObject();
        smsJson.put("phone", mobile);
        smsJson.put("signName", "MOTI");
        smsJson.put("templateCode", "SMS_173405630");
        Map templateMap = new HashMap(1);
        templateMap.put("order", orderNo);
        String templateParam = JSONObject.toJSONString(templateMap);
        smsJson.put("templateParam", templateParam);
        LOGGER.info("达转为次日达发短信的入参：" + smsJson);
        String smsResponseJson = callThirdCenter(AL_SMS_SEND, smsJson);
        LOGGER.info("发短信的返回结果：" + smsResponseJson);

    }

    @Override
    public ResponseJson generateOrderForMafengwo(PageOrderVo pageOrder) {
        String currId = getCurrentUserId(pageOrder);
        //插入地址
        String addressId = insertAddress(pageOrder,currId);
        //计算价格
        JSONObject goodsSkuJson = new JSONObject();
        goodsSkuJson.put("id",pageOrder.getTobaccoSku());
        JSONObject skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
        String tobaccoSkuPrice =  "";
        //判断是否有烟杆价格
        if(StringUtil.isEmpty(pageOrder.getTobaccoSkuPrice())){
            tobaccoSkuPrice =  skuResult.getString("market_price");
        }else{
            tobaccoSkuPrice = pageOrder.getTobaccoSkuPrice();
        }
        String tobaccoSpu = skuResult.getString("spu_id");
        String tobaccoSkuName = skuResult.getString("name");
        BigDecimal cartridgesOriginPrice = BigDecimal.ZERO;
        //得到订单原始总价
        BigDecimal tobaccoOriginPrice = (new BigDecimal(tobaccoSkuPrice).multiply(new BigDecimal(pageOrder.getTobaccoSkuNum())));
        BigDecimal originPrice = tobaccoOriginPrice.add(cartridgesOriginPrice);
        String orderPrice = originPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        Map<String,String> map = new ConcurrentHashMap<>(3);
        //生成订单
        try {
            LOGGER.info("====================活动订单下单开始========================");
            JSONObject tradeOrderJsonObj = new JSONObject();
            String orderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDERS);
            String orderNo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ORDER_NO);
            tradeOrderJsonObj.put("id",orderId);
            tradeOrderJsonObj.put("order_no",orderNo);
            tradeOrderJsonObj.put("buyer_id",currId);
            tradeOrderJsonObj.put("address_id",addressId);
            tradeOrderJsonObj.put("order_type","3");
            tradeOrderJsonObj.put("order_source",pageOrder.getSource());
            tradeOrderJsonObj.put("order_old_money",orderPrice);
            tradeOrderJsonObj.put("order_money",orderPrice);
            tradeOrderJsonObj.put("order_discount_money","0");
            tradeOrderJsonObj.put("distribution_id",("3".equals(pageOrder.getPaymentType()) || "2".equals(pageOrder.getPaymentType()) )?DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId():"4");
            tradeOrderJsonObj.put("order_status",(pageOrder.getPaymentType().equals("6"))?
                    OrderStatus.ORDER_STATUS_PENDING_DELIVERY.getId()
                    :OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId());
            tradeOrderJsonObj.put("create_time",DateUtil.getNowTimeStr());
            tradeOrderJsonObj.put("payment_type",pageOrder.getPaymentType());
            tradeOrderJsonObj.put("freight","0");
            tradeOrderJsonObj.put("push_status","1");
            tradeOrderJsonObj.put("order_yn","1");
            tradeOrderJsonObj.put("is_refund","1");
            tradeOrderJsonObj.put("locked","1");
            tradeOrderJsonObj.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_INSERT, tradeOrderJsonObj);
            //插入订单媒体表
            JSONObject mediaJson = new JSONObject();
            mediaJson.put("id",SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_MEDIA));
            mediaJson.put("order_id",orderId);
            mediaJson.put("type",pageOrder.getType());
            mediaJson.put("material",pageOrder.getMaterial());
            mediaJson.put("channel",pageOrder.getChannel());
            mediaJson.put("create_time",DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.TRADE_ORDERS_MEDIA_INSERT,mediaJson);
            //插入明细表
            insertTradeOrderItem(pageOrder.getTobaccoSku(),pageOrder.getTobaccoSkuNum(), pageOrder.getTobaccoSkuPrice(),  tobaccoSpu,  tobaccoSkuName, orderId);
             if(pageOrder.getGiftList() != null  && pageOrder.getGiftList().size() >0){
            for (Gift gift:pageOrder.getGiftList()) {
                goodsSkuJson.put("id",pageOrder.getCartridgesSku());
                skuResult = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SKU_QUERY,goodsSkuJson));
                insertTradeOrderItem(gift.getSkuId(),gift.getCount(), gift.getPrice(),  skuResult.getString("spu_id"),  skuResult.getString("name"), orderId);
            }
        }
            map.put("id", orderId);
            map.put("address", addressId);
            map.put("orderNo", orderNo);
            map.put("spuName", pageOrder.getTobaccoSku());
            map.put("orderTime",tradeOrderJsonObj.getString("create_time"));
            map.put("paymentType",("6".equals(pageOrder.getPaymentType())?"货到付款":"3".equals(pageOrder.getPaymentType())?"支付宝支付":"微信支付"));
            map.put("deliveryInfo","有货，免费包邮");
            map.put("remark",(null == pageOrder.getRemark()|| "".equals(pageOrder.getRemark()))?"":pageOrder.getRemark());
            LOGGER.info("========================活动订单下单完成============================");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,map);
            // }
        } catch (Exception e) {
            LOGGER.error("===============================活动下单生成异常===========================");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    private String getCurrentUserId(PageOrderVo pageOrder) {
        String currId = getLoginUserId();
        if ("null".equals(currId)) {
            JSONObject userJson = new JSONObject();
            userJson.put("mobile", pageOrder.getMobile());
            JSONObject retJson = JSONObject.parseObject(callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE, userJson));
            currId = retJson.getString("id");
        }
        return currId;
    }


    private String  insertTradeOrderItem(String sku, String skuNum, String SkuPrice, String spu, String SkuName, String orderId) throws Exception {
        String itemId =  SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_ITEMS);
        JSONObject itemObj1 =new JSONObject();
        itemObj1.put("id",itemId);
        itemObj1.put("order_id",orderId);
        itemObj1.put("sku_id",sku);
        itemObj1.put("sku_num",skuNum);
        itemObj1.put("sku_name",SkuName);
        itemObj1.put("create_time", DateUtil.getNowTimeStr());
        itemObj1.put("sku_price",SkuPrice);
        itemObj1.put("payment_price",SkuPrice);
        itemObj1.put("spu_id",spu);
        JSONObject spuJson =  new JSONObject();
        spuJson.put("id",spu);
        String spuName = JSONObject.parseObject(callDataCenter(DataCenterMethod.GOODS_SPU_QUERY,spuJson)).getString("name");
        itemObj1.put("spu_name",spuName);
        callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_INSERT,itemObj1);
        return spuName;
    }
}
