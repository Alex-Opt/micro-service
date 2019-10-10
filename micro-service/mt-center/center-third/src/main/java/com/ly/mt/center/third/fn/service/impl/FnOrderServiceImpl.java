package com.ly.mt.center.third.fn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.fn.entity.*;
import com.ly.mt.center.third.fn.service.FnOrderService;
import com.ly.mt.center.third.fn.service.FnTokenService;
import com.ly.mt.center.third.fn.dict.FnApi;
import com.ly.mt.center.third.fn.util.FnRandomUtil;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_FN_STRING_TOKEN;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @Description 蜂鸟配送接口服务
 * @Author taoye
 */
@Service
public class FnOrderServiceImpl extends BaseServiceImpl implements FnOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FnOrderServiceImpl.class);
    @Resource
    FnTokenService tokenService;
    @Resource
    YmlConfig yml;

    /**
     * @Description 推送订单
     * @Author taoye
     */
    @Override
    public ResponseJson orderCreate(JSONObject jsonObject) {
        try {
            FnOrderCreate fnOrderCreate = JSONObject.toJavaObject(jsonObject, FnOrderCreate.class);
            String order_id = fnOrderCreate.getOrder_id();
            if (StringUtil.isEmpty(order_id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单id为空");
            }
            // 订单信息查询
            JSONObject paramObject = new JSONObject();
            paramObject.put("id", order_id);
            String orderJson = callDataCenter(ORDER_GET_ORDER, paramObject);
            if (StringUtil.isEmpty(orderJson)) {
                LOGGER.error("数据中心返回订单数据为空,order_id={}", order_id);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            JSONObject orderObject = JSONObject.parseObject(orderJson, JSONObject.class);
            // 商品信息查询
            String orderItemsJson = callDataCenter(TRADE_ORDER_ITEMS_LIST, paramObject);
            if (StringUtil.isEmpty(orderItemsJson)) {
                LOGGER.error("数据中心返回订单商品数据为空,order_id={}", order_id);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            List<JSONObject> orderItemsList = JSONObject.parseArray(orderItemsJson, JSONObject.class);
            // 店铺信息查询
            String shopId = orderObject.getString("shop_id");
            paramObject.put("id", shopId);
            String shopJson = callDataCenter(SHOP_GET_SHOP_BY_ID, paramObject);
            if (StringUtil.isEmpty(shopJson)) {
                LOGGER.error("数据中心返回店铺信息为空,shop_id={}", shopId);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            JSONObject shopObject = JSONObject.parseObject(shopJson, JSONObject.class);
            // 店铺地址信息查询
            String shopAddressJson = callDataCenter(SHOP_GET_SHOP_ADDRESS_BY_SHOP_ID, paramObject);
            if (StringUtil.isEmpty(shopAddressJson)) {
                LOGGER.error("数据中心返回店铺地址信息为空,shop_id={}", shopId);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            JSONObject shopAddressObject = JSONObject.parseObject(shopAddressJson, JSONObject.class);
            String address_id = orderObject.getString("address_id");
            paramObject.put("address_id",address_id);
            String addressJson = callDataCenter(USERADDRESS_BY_ID, paramObject);
            if (StringUtil.isEmpty(addressJson)) {
                LOGGER.error("数据中心返回买家收货地址信息为空,address_id={}", address_id);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            JSONObject userAddressObject = JSONObject.parseObject(addressJson, JSONObject.class);
            // 买家信息封装
            FnOrderCreateReceiverInfo receiverInfo = new FnOrderCreateReceiverInfo();
            receiverInfo.setReceiver_name(userAddressObject.getString("receive_name"));
            receiverInfo.setReceiver_primary_phone(userAddressObject.getString("receive_phone"));
            receiverInfo.setReceiver_address(userAddressObject.getString("user_address"));
            receiverInfo.setReceiver_longitude(userAddressObject.getString("lon"));
            receiverInfo.setReceiver_latitude(userAddressObject.getString("lat"));
            receiverInfo.setPosition_source("3");
            // 卖家信息封装
            FnOrderCreateTransportInfo transportInfo = new FnOrderCreateTransportInfo();
            /*transportInfo.setTransport_id(shopObject.getString("id"));*/
            transportInfo.setTransport_name(shopObject.getString("shop_name"));
            transportInfo.setTransport_address(shopAddressObject.getString("user_address"));
            transportInfo.setTransport_longitude(shopAddressObject.getString("send_lon"));
            transportInfo.setTransport_latitude(shopAddressObject.getString("send_lat"));
            transportInfo.setTransport_tel(shopAddressObject.getString("receive_phone"));
            transportInfo.setPosition_source("3");
            // 商品信息封装
            List<FnOrderCreateItem> items = new ArrayList<>();
            int goodsCount = 0;
            for (JSONObject itemObject : orderItemsList) {
                FnOrderCreateItem item = new FnOrderCreateItem();
                item.setItem_id(itemObject.getString("sku_id"));
                item.setItem_name(itemObject.getString("sku_name"));
                String itemQuantity = itemObject.getString("sku_num");
                item.setItem_quantity(itemQuantity);
                item.setItem_price(itemObject.getString("sku_price"));
                item.setItem_actual_price(itemObject.getString("payment_price"));
                item.setAgent_purchase_price(null);
                item.setIs_need_package("0");
                item.setIs_agent_purchase("0");
                items.add(item);
                goodsCount += Integer.valueOf(itemQuantity);
            }
            // 订单信息封装
            FnOrderCreate orderCreate = new FnOrderCreate();
            orderCreate.setPartner_order_code(orderObject.getString("order_no"));
            orderCreate.setNotify_url(yml.getFnNotifyUrl());
            orderCreate.setOrder_type("1");
            orderCreate.setChain_store_code(yml.getFnStoreCode());
            orderCreate.setOrder_total_amount(orderObject.getString("order_old_money"));
            orderCreate.setOrder_actual_amount(orderObject.getString("order_money"));
            orderCreate.setIs_invoiced("0");
            orderCreate.setOrder_payment_status("1");
            orderCreate.setOrder_payment_method("1");
            orderCreate.setIs_agent_payment("0");
            orderCreate.setGoods_count(String.valueOf(goodsCount));
            orderCreate.setReceiver_info(receiverInfo);
            orderCreate.setTransport_info(transportInfo);
            orderCreate.setItems_json(items);
            // 获取签名
            int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
            String orderCreateJson = JSONObject.toJSONString(orderCreate);
            String sign = tokenService.getBusinessSign(orderCreateJson, salt);
            // 请求参数封装
            FnRequest FnRequest = new FnRequest();
            FnRequest.setApp_id(yml.getFnAppId());
            FnRequest.setSalt(String.valueOf(salt));
            FnRequest.setData(orderCreateJson);
            FnRequest.setSignature(sign);
            String requestJson = JSONObject.toJSONString(FnRequest);
            String url = tokenService.getRequestUtl(FnApi.FN_API_ORDER_CREATE);
            String resultJson = restTemplatePost(url, requestJson);
            LOGGER.info("=====================================================调用蜂鸟推送返回的结果："+resultJson);
            FnResult fnResult = JSONObject.parseObject(resultJson, FnResult.class);
            String successCode = "200";
            if (null == fnResult || !successCode.equals(fnResult.getCode())) {
                if(fnResult.getCode().equals("40004")){
                    //说明token过期
                    redisService.del(REDIS_FN_STRING_TOKEN);
                }
                LOGGER.error("订单id={}推送蜂鸟配送失败,蜂鸟接口返回失败信息msg:"+fnResult.getMsg(), order_id);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("蜂鸟推送订单出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 取消订单
     * @Author taoye
     */
    @Override
    public ResponseJson orderCancel(JSONObject jsonObject) {
        try {
            FnOrderCancel orderCancel = JSONObject.toJavaObject(jsonObject, FnOrderCancel.class);
            if (StringUtil.isEmpty(orderCancel.getPartner_order_code())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单号为空");
            }
            if (StringUtil.isEmpty(orderCancel.getOrder_cancel_reason_code())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单取消原因代码为空");
            }
            if (StringUtil.isEmpty(orderCancel.getOrder_cancel_code())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单取消编码为空");
            }
            String order_cancel_time = DateUtil.getNowTimeStamp();
            // 取消时间
            orderCancel.setOrder_cancel_time(order_cancel_time);
            // 获取签名
            int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
            String orderJson = JSONObject.toJSONString(orderCancel);
            String sign = tokenService.getBusinessSign(orderJson, salt);
            // 请求参数封装
            FnRequest FnRequest = new FnRequest();
            FnRequest.setApp_id(yml.getFnAppId());
            FnRequest.setSalt(String.valueOf(salt));
            FnRequest.setData(orderJson);
            FnRequest.setSignature(sign);
            String requestJson = JSONObject.toJSONString(FnRequest);
            String url = tokenService.getRequestUtl(FnApi.FN_API_ORDER_CANCEL);
            String resultJson = restTemplatePost(url, requestJson);
            LOGGER.info("=====================================================调用蜂鸟取消返回的结果："+resultJson);
            FnResult FnResult = JSONObject.parseObject(resultJson, FnResult.class);
            String successCode = "200";
            if (null == FnResult || !successCode.equals(FnResult.getCode())) {
                LOGGER.error("订单编号={}取消失败", orderCancel.getPartner_order_code());
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("蜂鸟取消订单出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询订单
     * @Author taoye
     */
    @Override
    public ResponseJson orderQuery(JSONObject jsonObject) {
        try {
            FnOrderQuery fnOrderQuery = JSONObject.toJavaObject(jsonObject, FnOrderQuery.class);
            if (StringUtil.isEmpty(fnOrderQuery.getPartner_order_code())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单号为空");
            }
            // 获取签名
            int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
            String orderQueryJson = JSONObject.toJSONString(fnOrderQuery);
            String sign = tokenService.getBusinessSign(orderQueryJson, salt);
            // 请求参数封装
            FnRequest FnRequest = new FnRequest();
            FnRequest.setApp_id(yml.getFnAppId());
            FnRequest.setSalt(String.valueOf(salt));
            FnRequest.setData(orderQueryJson);
            FnRequest.setSignature(sign);
            String requestJson = JSONObject.toJSONString(FnRequest);
            String url = tokenService.getRequestUtl(FnApi.FN_API_ORDER_QUERY);
            String resultJson = restTemplatePost(url, requestJson);
            LOGGER.info("=====================================================调用蜂鸟查询返回的结果："+resultJson);
            FnResult FnResult = JSONObject.parseObject(resultJson, FnResult.class);
            String successCode = "200";
            if (null == FnResult || !successCode.equals(FnResult.getCode())) {
                LOGGER.error("订单编号={}查询失败", fnOrderQuery.getPartner_order_code());
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
           return  ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,FnResult);
        } catch (Exception e) {
            LOGGER.error("蜂鸟查询订单出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}