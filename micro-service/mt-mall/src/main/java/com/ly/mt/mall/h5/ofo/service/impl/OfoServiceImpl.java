package com.ly.mt.mall.h5.ofo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.*;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.login.vo.LoginByMobileVo;
import com.ly.mt.mall.h5.ofo.service.OfoService;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SPU_DETAIL;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_OFO;
import static com.ly.mt.core.base.dict.UserStatus.USER_STATUS_USING;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_PAY_WITHDRAWAL;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * @Description ofo 对接
 * @Author
 */
@Service
public class OfoServiceImpl extends BaseServiceImpl implements OfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OfoServiceImpl.class);

    /**
     * @Description 查询商品spu列表
     * @Author
     */
    @Override
    public ResponseJson goodsOfoSpuList() {
        try {
            List returnList = new ArrayList();
            JSONObject json = new JSONObject();
            json.put("app_type", AppType.APP_TYPE_OFO.getId());
            //查询ofo推销spu列表数据
            String spuIdResult = callDataCenter(GOODS_FRONT_SPUID_QUERY, json);
            if (StringUtil.isNotEmpty(spuIdResult)) {
                JSONArray spuIdArray = JSONObject.parseArray(spuIdResult);
                if (spuIdArray.size() > 0) {
                    String goodsOfoResult = callDataCenter(GOODS_FRONT_QUERY, json);
                    JSONArray goodsOfoArray = JSONObject.parseArray(goodsOfoResult);
                    json = new JSONObject();
                    String spuInfoResult = callDataCenter(GOODS_SPU_QUERY_ALL, json);
                    JSONArray spuInfoArray = JSONObject.parseArray(spuInfoResult);
                    for (int i = 0; i < spuIdArray.size(); i++) {
                        String spuId = String.valueOf(spuIdArray.get(i));
                        for (int j = 0; j < spuInfoArray.size(); j++) {
                            JSONObject node = spuInfoArray.getJSONObject(j);
                            if (node.getString("id").equals(spuId)) {
                                Map map = new HashMap();
                                map.put("spuId", spuId);
                                map.put("name", node.getString("name"));
                                map.put("cid", node.getString("cid"));
                                map.put("marketPrice", node.getString("market_price"));
                                map.put("wholesalePrice", node.getString("wholesale_price"));
                                map.put("pictureUrl", node.getString("picture_url"));
                                map.put("pictureUrlC", node.getString("picture_url_c"));
                                map.put("describeUrl", node.getString("describe_url"));

                                for (int k = 0; k < goodsOfoArray.size(); k++) {
                                    JSONObject goodsOfo = goodsOfoArray.getJSONObject(k);
                                    if (goodsOfo.getString("spu_id").equals(spuId)) {
                                        map.put("price", goodsOfo.getString("price"));
                                        break;
                                    }
                                }
                                returnList.add(map);
                            }
                        }
                    }
                }
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, returnList);
        } catch (Exception e) {
            LOGGER.error("ofo对接,goodsOfoSpuList查询spu列表数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryGoodsOfoSpuById(String spuId) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", spuId);
            String result = callDataCenter(GOODS_SPU_QUERY, json);
            JSONObject resultJson = null;

            if (StringUtil.isNotEmpty(result)) {
                resultJson = JSONObject.parseObject(result);
                //查询商品spu图片数据
                json = new JSONObject();
                json.put("spu_id", spuId);
                String pictTemp = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
                List<String> pictureList = null;
                if ("[]".equals(pictTemp)) {
                    pictureList = new ArrayList<>(1);
                    pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                    resultJson.put("pictureList", pictureList);
                } else {
                    resultJson.put("pictureList", JSONObject.parseArray(pictTemp));
                }

                //根据spuId查询属性
                String resultAttr = callDataCenter(GOODS_SPU_ATTR_BY_SPUID, json);
                JSONArray arrayAttr = JSONObject.parseArray(resultAttr);
                //根据spuId查询属性值
                String resultAttrValue = callDataCenter(GOODS_SPU_ATTR_VALUE_BY_SPUID, json);
                JSONArray arrayAttrValue = JSONObject.parseArray(resultAttrValue);
                json.put("app_type", AppType.APP_TYPE_OFO.getId());
                String resultSku = callDataCenter(GOODS_FRONT_SKU_BY_SPUID, json);
                JSONArray arraySku = JSONObject.parseArray(resultSku);

                for (int j = 0; j < arrayAttr.size(); j++) {
                    JSONObject nodeAttr = arrayAttr.getJSONObject(j);
                    String attrId = nodeAttr.getString("attrId");
                    List valueList = null;
                    for (int k = 0; k < arrayAttrValue.size(); k++) {
                        if (valueList == null) {
                            valueList = new ArrayList();
                        }
                        JSONObject nodeAttrValue = arrayAttrValue.getJSONObject(k);
                        if (attrId.equals(nodeAttrValue.getString("attrId"))) {
                            for (int m = 0; m < arraySku.size(); m++) {
                                JSONObject nodeSku = arraySku.getJSONObject(m);
                                if (nodeSku.getString("attributes").equals(nodeAttrValue.getString("attrValueId"))) {
                                    nodeAttrValue.put("skuId", nodeSku.getString("skuId"));
                                    nodeAttrValue.put("skuName", nodeSku.getString("skuName"));
                                    nodeAttrValue.put("skuPrice", nodeSku.getString("skuPrice"));
                                    valueList.add(nodeAttrValue);
                                }
                            }
                        }
                    }
                    nodeAttr.put("attrValueList", valueList);
                }
                resultJson.put("attr", arrayAttr);
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
        } catch (Exception e) {
            LOGGER.error("ofo对接,queryGoodsOfoSpuById查询spu详情数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson querySkupicturcBySkuId(String skuId) {
        try {
            JSONObject json = new JSONObject();
            json.put("sku_id", skuId);
            String result = callDataCenter(GOODS_SKU_PICTURE_QUERY, json);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
        } catch (Exception e) {
            LOGGER.error("ofo对接,querySkupicturcBySkuId查询sku图片数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson mobileLogin(String userName, String mobile, String dynamicCode, HttpServletRequest request) {
        try {
            // 校验是否已注册
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            String userJson = callDataCenter(USER_GET_LOGIN_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isNotEmpty(userJson)) {
                // 校验验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_LOGIN, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效,请重新获取!");
                }
                if (!redisCode.equals(dynamicCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }

            } else {
                // 校验动态验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_REGIST, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
                }
                if (!dynamicCode.equals(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }
                // 未注册直接注册
                //registStatus = "0";
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
                jsonObject.put("id", id);
                jsonObject.put("user_name", userName);
                jsonObject.put("login_name", "MT" + id.substring(0, 8));
                jsonObject.put("password", MD5Util.md5(mobile));
                jsonObject.put("user_status", USER_STATUS_USING.getId());
                jsonObject.put("create_time", DateUtil.getNowTimeStr());
                jsonObject.put("quick_type", QUICK_TYPE_OFO.getId());
                userJson = callDataCenter(USER_INSERT, jsonObject);
            }
            LoginByMobileVo loginVo = JSONObject.toJavaObject(JSONObject.parseObject(userJson), LoginByMobileVo.class);
            LOGGER.info("----------------------loginVo:" + JSONObject.toJSONString(loginVo));
            saveSession(request, loginVo.getId(), loginVo.getLoginName(), userName, mobile);
            LOGGER.info("loginVo.getId():" + loginVo.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginVo.getId());
        } catch (Exception e) {
            LOGGER.error("手机号验证码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @TxcTransaction(appName = "moti")
    @Override
    public ResponseJson createOrder(String skuId, String skuNum, String provinceCode, String provinceName, String cityCode, String cityName, String districtCode, String districtName, String userAddress, String mobile, String appType,String actiClass, HttpServletRequest request) {
        try {
            String userId = getLoginUserId();
           /* if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
            }

            String clickToken = redisService.get(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId);
            if (StringUtil.isEmpty(clickToken)) {
                redisService.set(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId, 8L, TimeUnit.SECONDS);
            } else {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_OPERATOR_TOO_FASTER);
            }*/
            String userName = getLoginUserName();
            String nowTime = DateUtil.getNowTimeStr();
            String xid = TxcContext.getCurrentXid();
            //保存收货地址
            JSONObject json = new JSONObject();
            String addressId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_ADDRESS);
            json.put("xid", xid);
            json.put("id", addressId);
            json.put("user_id", userId);
            json.put("user_name", userName);
            json.put("receive_name", userName);
            json.put("receive_phone", mobile);
            json.put("create_time", nowTime);
            json.put("user_address", userAddress);
            json.put("province_code", provinceCode);
            json.put("province_name", provinceName);
            json.put("city_code", cityCode);
            json.put("city_name", cityName);
            json.put("district_name", districtName);
            json.put("district_code", districtCode);
            callDataCenter(DataCenterMethod.USERADDRESS_INSERT, json);
            //根据skuId查询sku数据
            json = new JSONObject();
            json.put("app_type", appType);
            json.put("sku_id", skuId);
            json.put("acti_class",actiClass);
            String skuInfoResult = callDataCenter(GOODS_FRONT_SKU_BY_SKUID, json);
            if (StringUtil.isNotEmpty(skuInfoResult)) {
                JSONObject skuInfo = JSONObject.parseObject(skuInfoResult);
                BigDecimal price = new BigDecimal(skuInfo.getString("skuPrice"));
                String orderPrice = price.multiply(new BigDecimal(skuNum)).toString();
                //新增订单数据
                JSONObject jsonOrder = new JSONObject();
                jsonOrder.put("xid", xid);
                String orderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDERS);
                String orderNo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ORDER_NO);
                jsonOrder.put("id", orderId);
                jsonOrder.put("order_no", orderNo);
                jsonOrder.put("buyer_id", userId);
                jsonOrder.put("address_id", addressId);
                jsonOrder.put("order_type", "3");
                //jsonOrder.put("order_source", "999999");
                if (AppType.APP_TYPE_OFO.getId().equals(appType)) {
                    jsonOrder.put("order_source", OrderSource.ORDER_SOURCE_OFO.getId());
                    HttpSession session = request.getSession();
                    jsonOrder.put("third_code", session.getAttribute("code"));
                } else if (AppType.APP_TYPE_POKE.getId().equals(appType)) {
                    jsonOrder.put("order_source", OrderSource.ORDER_SOURCE_POKE.getId());
                }
                jsonOrder.put("order_old_money", orderPrice);
                jsonOrder.put("order_money", orderPrice);
                jsonOrder.put("order_discount_money", "0");
                jsonOrder.put("distribution_id", DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId());
                jsonOrder.put("order_status", OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId());
                jsonOrder.put("create_time", DateUtil.getNowTimeStr());
                jsonOrder.put("freight", "0");
                jsonOrder.put("push_status", "1");
                jsonOrder.put("order_yn", "1");
                jsonOrder.put("is_refund", "1");
                jsonOrder.put("locked", "1");
                jsonOrder.put("auto_cancel_time",DateUtil.getAfterMinutesDateFromPointTime(30,DateUtil.getNowTimeStr()));
                callDataCenter(DataCenterMethod.TRADE_ORDERS_INSERT, jsonOrder);
                //新增订单明细数据
                json = new JSONObject();
                json.put("xid", xid);
                String itemId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_ITEMS);
                json.put("id", itemId);
                json.put("order_id", orderId);
                json.put("sku_id", skuId);
                json.put("sku_num", skuNum);
                json.put("sku_name", skuInfo.getString("skuName"));
                json.put("create_time", nowTime);
                json.put("sku_price", price.toString());
                json.put("spu_id", skuInfo.getString("spuId"));
                json.put("spu_name", skuInfo.getString("spuName"));
                callDataCenter(DataCenterMethod.TRADE_ORDER_ITEMS_INSERT, json);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonOrder);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询sku数据为空");
            }
        } catch (Exception e) {
            LOGGER.error("提交订单出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson saveTypeAndCode(String type, String code, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("type", type);
            if (StringUtil.isNotEmpty(code)) {
                session.setAttribute("code", code);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("保存参数和编码出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson savePaymentType(String orderId, String paymentType, String freight) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", orderId);
            String result = callDataCenter(TRADE_ORDERS_GET, json);
            if (StringUtil.isNotEmpty(result)) {
                JSONObject order = JSONObject.parseObject(result);
                json.put("freight", freight);
                json.put("payment_type", paymentType);
                //BigDecimal orderMoney = new BigDecimal(order.getString("order_money"));
                //orderMoney = orderMoney.add(new BigDecimal(freight));
                //json.put("order_money",orderMoney);
               // json.put("order_money", "0.01");
                callDataCenter(TRADE_ORDERS_UPDATE_PAYMENTTYPE, json);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询订单数据为空");
            }
        } catch (Exception e) {
            LOGGER.error("保存参数和编码出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryGoodsFrontByActiClass(String appType, String actiClass) {
        try {
            JSONObject json = new JSONObject();
            json.put("app_type", appType);
            json.put("acti_class", actiClass);
            String frontResult = callDataCenter(GOODS_FRONT_BY_APPTYPE_ACTICLASS, json);
            JSONObject resultJson = null;
            if (StringUtil.isNotEmpty(frontResult)) {
                JSONObject frontJson = JSONObject.parseObject(frontResult);
                String spuId = frontJson.getString("spu_id");
                String skuId = frontJson.getString("sku_id");

                json.put("id", spuId);
                String spuResult = callDataCenter(GOODS_SPU_QUERY, json);
                if (StringUtil.isNotEmpty(spuResult)) {
                    resultJson = JSONObject.parseObject(spuResult);

                    resultJson.put("pictureUrl",frontJson.getString("picture_url"));
                    //查询商品spu图片数据
                    json = new JSONObject();
                    json.put("spu_id", spuId);
                    String pictTemp = callDataCenter(GOODS_SPU_PICTURE_BY_SPUID, json);
                    List<String> pictureList = null;
                    if ("[]".equals(pictTemp)) {
                        pictureList = new ArrayList<>(1);
                        pictureList.add(PICTURE_PLACEHOLDER_SPU_DETAIL.getId());
                        resultJson.put("pictureList", pictureList);
                    } else {
                        resultJson.put("pictureList", JSONObject.parseArray(pictTemp));
                    }
                    resultJson.put("skuId", skuId);
                    resultJson.put("price", frontJson.getString("price"));

                    //根据spuId查询属性
                    String resultAttr = callDataCenter(GOODS_SPU_ATTR_BY_SPUID, json);
                    JSONArray arrayAttr = JSONObject.parseArray(resultAttr);
                    //根据spuId查询所有的属性值
                    String resultAttrValue = callDataCenter(GOODS_SPU_ALL_ATTR_VALUE_BY_SPUID, json);
                    JSONArray arrayAttrValue = JSONObject.parseArray(resultAttrValue);
                    json = new JSONObject();
                    json.put("id", skuId);
                    String resultSku = callDataCenter(GOODS_SKU_QUERY, json);
                    JSONObject skuJson = JSONObject.parseObject(resultSku);

                    for (int j = 0; j < arrayAttr.size(); j++) {
                        JSONObject nodeAttr = arrayAttr.getJSONObject(j);
                        String attrId = nodeAttr.getString("attrId");
                        List valueList = null;
                        for (int k = 0; k < arrayAttrValue.size(); k++) {
                            if (valueList == null) {
                                valueList = new ArrayList();
                            }
                            JSONObject nodeAttrValue = arrayAttrValue.getJSONObject(k);
                            if (attrId.equals(nodeAttrValue.getString("attrId"))) {
                                if (skuJson.getString("attributes").equals(nodeAttrValue.getString("attrValueId"))) {
                                    nodeAttrValue.put("skuId", skuJson.getString("sku_id"));
                                    nodeAttrValue.put("skuName", skuJson.getString("sku_name"));
                                    nodeAttrValue.put("skuPrice", skuJson.getString("sku_price"));
                                    valueList.add(nodeAttrValue);
                                }
                            }
                        }
                        nodeAttr.put("attrValueList", valueList);
                    }
                    resultJson.put("attr", arrayAttr);
                }
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
        } catch (Exception e) {
            LOGGER.error("扑克牌对接,queryGoodsFrontByActiClass查询spu详情数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson queryOrderList(String orderSource){
        try {
            String userId = getLoginUserId();
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
            }
            JSONObject json = new JSONObject();
            json.put("buyer_id", userId);
            json.put("order_source", orderSource);
            //根据buyer_id和order_source查询订单数据
            String orderResult = callDataCenter(TRADE_ORDERS_BY_ORDERSOURCE, json);
            if(StringUtil.isNotEmpty(orderResult)){
                JSONArray resultJson = JSONObject.parseArray(orderResult);
                for (int i =0;i<resultJson.size();i++){
                    JSONObject node = resultJson.getJSONObject(i);
                    json = new JSONObject();
                    json.put("id",node.getString("id"));
                    //查询订单商品明细
                    String itemResult = callDataCenter(TRADE_ORDER_ITEMS_LIST,json);
                    if(StringUtil.isNotEmpty(itemResult)){
                        JSONArray itemJson = JSONObject.parseArray(itemResult);
                        for (int j=0;j<itemJson.size();j++){
                            JSONObject item = itemJson.getJSONObject(j);
                            json = new JSONObject();
                            json.put("id",item.getString("sku_id"));
                            //查询sku详情
                            String skuResult = callDataCenter(GOODS_SKU_INFO_GET_BY_ID,json);
                            if(StringUtil.isNotEmpty(skuResult)){
                                JSONObject skuJson = JSONObject.parseObject(skuResult);
                                item.put("skuInfo",skuJson);
                                json = new JSONObject();
                                json.put("sku_id",item.getString("sku_id"));
                                //查询sku图片
                                String skuPictureResult = callDataCenter(GOODS_SKU_PICTURE_BY_SKUID,json);
                                JSONObject skuPictureJson = JSONObject.parseObject(skuPictureResult);
                                item.put("skuPicture",skuPictureJson);

                                json = new JSONObject();
                                json.put("id",skuJson.getString("attributes"));
                                //查询sku的属性值和属性数据
                                String attrResult = callDataCenter(GOODS_ATTR_BY_VALUEID,json);
                                JSONObject attrJson = JSONObject.parseObject(attrResult);
                                item.put("skuAttr",attrJson);
                            }
                        }
                        node.put("item",itemJson);
                    }
                }
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultJson);
            }else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, null);
            }
        } catch (Exception e) {
            LOGGER.error("扑克牌对接,queryGoodsFrontByActiClass查询spu详情数据出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 保存session
     * @Author
     */
    private void saveSession(HttpServletRequest request, String userId, String loginName, String userName, String mobile) {
        // 存储常用信息
        String token = UUID.randomUUID().toString();
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", userId);
        session.setAttribute("loginName", loginName);
        session.setAttribute("userName", userName);
        session.setAttribute("mobile", mobile);
        session.setAttribute("ipAddress", RequestUtil.getIpAddress(request));
        // 刷新session有效时间一周
        session.setMaxInactiveInterval(604800);
        // 刷新redis有效时间一周
        redisService.set(REDIS_TOKEN_LOGIN_MALL_H5, loginName, token, 10080L, TimeUnit.SECONDS);
    }

    @Override
    public ResponseJson applyWithdrawal(String userId) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", userId);
            String returnJson = callDataCenter(USER_GET_USER_BY_ID, json);
            JSONObject userJson = JSONObject.parseObject(returnJson);
            String openId = userJson.getString("wx_open_id");
            //json.put("amount",withdrawalAmount);
            json = new JSONObject();
            json.put("appid", "wx80a7401a02e0f8ec");//
            json.put("amount", "0.4");//提现金额
            json.put("tradeNo", StringUtil.getRandomIntByLength(20));//订单号
            json.put("openid", openId);
            json.put("desc", "提现申请");
            json.put("ip", "");
            LOGGER.info("applyWithdrawal 申请提现参数", json.toJSONString());
            callThirdCenter(WX_PAY_WITHDRAWAL, json);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.info("applyWithdrawal申请提现出现异常", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}