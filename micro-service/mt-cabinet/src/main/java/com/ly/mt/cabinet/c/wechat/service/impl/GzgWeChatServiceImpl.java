package com.ly.mt.cabinet.c.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.google.gson.JsonObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.DeviceCallStartService;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.alipay.service.GzgPayCommonService;
import com.ly.mt.cabinet.c.enumEntity.GzgCodeLengthEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.OrderStateEnum;
import com.ly.mt.cabinet.c.order.service.GzgOrderSyncCabinetProfitService;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.cabinet.c.tools.HttpClient;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import com.ly.mt.cabinet.c.wechat.entity.*;
import com.ly.mt.cabinet.c.wechat.service.GzgWeChatService;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.ly.mt.core.feign.DataCenterMethod.*;


@Service
public class GzgWeChatServiceImpl extends BaseServiceImpl implements GzgWeChatService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgWeChatServiceImpl.class);
    @Autowired
    private WeChatPropertiesConfig weChatPropertiesConfig;

    @Value("${gzg.mctId}")
    private String mctId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    private XMLUtils xmlUtils;
    @Autowired
    private WeChatUtil weChatUtil;
    @Autowired
    private HttpClient httpClient;
    @Resource
    private GzgExchangeService gzgExchangeService;
    @Autowired
    private DeviceCallStartService deviceCallStartService;
    @Autowired
    GzgPayCommonService gzgPayCommonService;
    @Autowired
    GzgRujinDeviceService gzgRujinDeviceService;

    @Autowired
    ReplenishmentGirdService replenishmentGirdService;
    @Autowired
    private GzgOrderSyncCabinetProfitService gzgOrderSyncCabinetProfitService;

    @Override
    public ResponseJson weChatPayByH5(String billCreateIp, String gzgOrderId) throws Exception {

        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", gzgOrderId);
        String resultGzgOrder = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);

        GzgOrder gzgOrdersVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrder), GzgOrder.class);

//        GzgOrderVo gzgOrderVo = gzgOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(gzgOrderId));

        if (!StringUtil.isEmpty(gzgOrdersVo.getPayment_no())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "订单重复提交");
        }
        String amount = gzgOrdersVo.getOrder_money();
        String code = gzgOrdersVo.getImei();
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", gzgOrderId);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);

        List<GzgOrderItem> gzgOrderItemVos = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
        //展柜订单没有超时限制
        if(gzgOrdersVo.getImei().length() != GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
            Boolean timeOut = gzgPayCommonService.isTimeOut(gzgOrderItemVos);
            if (timeOut) {
                LOGGER.info("微信订单超时，订单号：{}", gzgOrderId);
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "订单超时");
            }
        }
        //测试柜子价格0.01
        if ("865800042529062".equals(code) ||
                "RJ113211".equals(code) ||
                "RJ356874".equals(code) ||
                "100458206".equals(code)) {
            amount = "0.01";
        }
        String preXmlParam = weChatUtil.packageParam(billCreateIp, amount, gzgOrderId, "");
        LOGGER.info("传送给给微信的xml文件：{}", preXmlParam);
        CloseableHttpResponse response = httpClient.doPost(weChatPropertiesConfig.getUnifiedorder(), preXmlParam);
        LOGGER.info("微信返回信息：{}", response);
        int statusCode = response.getStatusLine().getStatusCode();
        String mweb_url = null;
        JSONObject jsonObject = new JSONObject();
        try {
            if (statusCode == HttpClient.SUCCESS) {
                HttpEntity entity = response.getEntity();
                String respResult = EntityUtils.toString(entity, "UTF-8");
                respResult = respResult.replaceAll("<!\\[CDATA\\[|]]>", "");
                respResult = respResult.replaceAll("&", "&amp;");
                LOGGER.info("微信H5支付获取返回信息字符串：{}", respResult);
                Map<String, Object> map = xmlUtils.xml2Map(respResult);
                String return_code = String.valueOf(map.get("return_code"));
                if ("SUCCESS".equals(return_code)) {
                    //获取mweb_url
                    mweb_url = String.valueOf(map.get("mweb_url"));
                    mweb_url = mweb_url.replaceAll("&amp;", "&");

                    String redirect_url = URLEncoder.encode(weChatPropertiesConfig.getReturnUrl());
                    jsonObject.put("mweb_url", mweb_url+"&redirect_url="+redirect_url);
                } else {
                    LOGGER.error("call webChatPay get mweb_url error,the error message is {}", map.get("return_msg"));
                    return null;
                }
            }

        } catch (Exception e) {
            LOGGER.error("call weChatPay of WeChatServiceImpl has error,the error message is {}", e.getMessage());
            return null;
        }
        LOGGER.info("微信支付service层返回给controller层的数据{}", ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_SUCCESS, jsonObject.toJSONString()));
        return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_SUCCESS, jsonObject.toJSONString());
    }


    @Override
    public ResponseJson weChatPayByJsApi(WeChatPayReqVO weChatPayReqVO, String billCreateIp) throws Exception {
        String gzgOrderId = weChatPayReqVO.getGzgOrderId();
        String code = weChatPayReqVO.getCode();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + weChatPropertiesConfig.getAppid()
                + "&secret=" + appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject1 = HttpClientUtil.httpGet(url);
        LOGGER.info("通过code值获取openid，http请求获取的数据{}", jsonObject1);
        String openId = redisService.get(RedisKey.REDIS_USER_WX_OPENID,code);
        if(StringUtil.isEmpty(openId)){
            openId = jsonObject1.getString("openid");
            LOGGER.info("redis为空，获取的openid值：{}",jsonObject1.getString("openid"));
            redisService.set(RedisKey.REDIS_USER_WX_OPENID,code,openId);
        }
        LOGGER.info("缓存的openid值：{}",openId);
        JSONObject jsonGzgOrders = new JSONObject();
        jsonGzgOrders.put("id", gzgOrderId);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrders);
        GzgOrder gzgOrdersVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        System.out.println(!StringUtil.isEmpty(gzgOrdersVo.getPayment_no()));
        if (!StringUtil.isEmpty(gzgOrdersVo.getPayment_no())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "订单已支付，请勿重复提交");
        }
        String amount = gzgOrdersVo.getOrder_money();
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", gzgOrderId);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        List<GzgOrderItem> gzgOrderItemsVoList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
        if(gzgOrdersVo.getImei().length() != GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
            Boolean timeOut = gzgPayCommonService.isTimeOut(gzgOrderItemsVoList);
            if (timeOut) {
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "支付超时");
            }
        }
        if ("865800042529062".equals(gzgOrdersVo.getImei()) ||
                "RJ113211".equals(gzgOrdersVo.getImei()) ||
                "RJ356874".equals(gzgOrdersVo.getImei()) ||
                "100458206".equals(gzgOrdersVo.getImei())) {
            amount = "0.01";
        }
        String preXmlParam = weChatUtil.packageParam(billCreateIp, amount, gzgOrderId, openId);
        LOGGER.info("传送给给微信的xml文件：{}", preXmlParam);

        CloseableHttpResponse response = httpClient.doPost(weChatPropertiesConfig.getUnifiedorder(), preXmlParam);

        LOGGER.info("微信返回信息：{}", response);
        int statusCode = response.getStatusLine().getStatusCode();
        JSONObject jsonObject = new JSONObject();
        try {
            if (statusCode == HttpClient.SUCCESS) {
                HttpEntity entity = response.getEntity();
                String respResult = EntityUtils.toString(entity, "UTF-8");
                respResult = respResult.replaceAll("<!\\[CDATA\\[|]]>", "");
                respResult = respResult.replaceAll("&", "&amp;");
                LOGGER.info("微信支付获取prepay_id返回信息字符串：{}", respResult);
                Map<String, Object> map = xmlUtils.xml2Map(respResult);
                String return_code = String.valueOf(map.get("return_code"));
                if ("SUCCESS".equals(return_code)) {
                    String s = JSON.toJSONString(map);
                    jsonObject = JSONObject.parseObject(s);
                    String prepay_id = jsonObject.getString("prepay_id");
                    String uuid = UUIDUtils.createUUID();
                    String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
                    String signNo2 = weChatUtil.generateSignMD52(timeStamp, uuid, prepay_id);//二次签名验证，传给前端
                    jsonObject.put("sign", signNo2);
                    jsonObject.put("timeStamp", timeStamp);
                    jsonObject.put("nonce_str", uuid);
                    LOGGER.info("微信支付获取prepay_id返回前段字符串{}", jsonObject);
                } else {
                    LOGGER.error("call webChatPay get prepay_id error,the error message is {}", map.get("return_msg"));
                    return null;
                }
            }
        } catch (Exception e) {
            LOGGER.error("call weChatPay of WeChatServiceImpl has error,the error message is {}", e.getMessage());
            return null;
        }
        ResponseJson responseObj = ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, jsonObject);
        LOGGER.info("微信浏览器支付service层返回给controller层的数据{}", responseObj);
        return responseObj;
    }


    /**
     * 查询订单
     *
     * @param orderNo
     * @return
     */
    @Override
    public boolean orderQuery(long orderNo) {
        return true;
    }

    /**
     * 微信回调
     *
     * @param
     * @return
     */
    @Override
    public ResponseJson weChatNotify(Map map) throws Exception {
        LOGGER.info("微信支付完成，回调notify，service层收到的数据 {}", map);
        // 解析返回数据
        String return_code = (String) map.get("return_code");
        String out_trade_no = (String) map.get("out_trade_no");
        String transaction_id = (String) map.get("transaction_id");
        if (!"SUCCESS".equals(return_code)) {
            LOGGER.error("支付回调返回支付失败,错误信息:{}", map.get("return_code"));
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        String result_code = (String) map.get("result_code");
        if (!"SUCCESS".equals(result_code)) {
            LOGGER.error("微信支付回调返回支付失败,错误代码={},错误信息={},", map.get("err_code"), map.get("err_code_des"));
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", out_trade_no);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        LOGGER.error("回调查询订单信息{},", resultGzgOrders);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        LOGGER.error("回调查询解析订单信息{},", gzgOrder);
        LOGGER.info("订单编号{}:",gzgOrder.getId());
        gzgOrder.setId(JSONObject.parseObject(resultGzgOrders).getString("id"));
        LOGGER.info("订单编号{}:",gzgOrder.getId());
        if (!StringUtil.isEmpty(gzgOrder.getPayment_no())) {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }
        LOGGER.info("订单编号:",gzgOrder.getId());
        // 验签
        if (!checkSign(map)) {
            LOGGER.info("订单编号:",gzgOrder.getId());
            GzgOrder gzgOrderVo = new GzgOrder();
            gzgOrderVo.setId(out_trade_no);
            gzgOrderVo.setPayment_no(transaction_id);//支付流水号
            gzgOrderVo.setOrder_status(OrderStateEnum.PAY_FAIL.getKey());
            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            gzgOrderVo.setPayment_type(GzgPayTypeEnum.WECHAT.getKey());//1 网银，2 微信，3 支付宝，4 其他，5 信用，
            JSONObject jsonGzgOrderVo = JSONObject.parseObject(JSONObject.toJSONString(gzgOrderVo));
            callDataCenter(GZG_ORDER_UPDATE, jsonGzgOrderVo);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        LOGGER.info("订单编号:{}",gzgOrder.getId());
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", out_trade_no);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        List<GzgOrderItem> gzgOrderItemList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
        LOGGER.info("订单编号:",gzgOrder.getId());
        LOGGER.error("回调验签成功,格子柜号{},",gzgOrder.getImei() );
        if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {
            if(gzgOrder.getImei().length()==15){//亿联柜子
                LOGGER.info("订单编号:",gzgOrder.getId());
                afterPaySuccessToYilian( gzgOrder, gzgOrderItemList , transaction_id);
            }else if(gzgOrder.getImei().length()==9){//展柜
                LOGGER.info("微信支付成功，展柜编号：{}", gzgOrder.getImei());
                String gzgOrderId = gzgOrder.getId();
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(gzgOrderId);
                gzgOrderVo.setPayment_no(transaction_id);//支付流水号
                gzgOrderVo.setOrder_status(OrderStateEnum.COMPLETED.getKey());
                gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setPayment_type(GzgPayTypeEnum.WECHAT.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,gzgOrderItemList);
                try{
                    replenishmentGirdService.createReplenishOrder(gzgOrderId);
                }catch (Exception e){
                    LOGGER.info("生成补货订单异常 :{}",e);
                }
            }else {//如金
                LOGGER.info("订单编号:",gzgOrder.getId());
                afterPaySuccessToRujin( gzgOrder, gzgOrderItemList , transaction_id);
            }
            afterPaySuccessSync(gzgOrder);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        } else {
            gzgPayCommonService.afterPayFail(gzgOrder, gzgOrderItemList,transaction_id,GzgPayTypeEnum.WECHAT);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Async
    public void afterPaySuccessSync(GzgOrder order){
        try{
            gzgPayCommonService.updateGzgUserCoupon(order);
        }catch (Exception e){
            LOGGER.info("更新优惠卷信息异常 : {}",e);
        }
        try{
            gzgOrderSyncCabinetProfitService.syncCabinetStoreProfit(order.getId());
        }catch (Exception e){
            LOGGER.info("计算收益信息异常 {}",e);
        }
        /*try{
            replenishmentGirdService.createReplenishOrder(order.getId());
        }catch (Exception e){
            LOGGER.info("生成补货订单异常 : {}",e);
        }*/
        try{
            //支付成功，生成分成奖励明细记录
            processCreateRewardRecord(order);
        }catch (Exception e){
            LOGGER.info("生成分成奖励明细记录异常 : {}",e);
        }
    }
    @Override
    public ResponseJson weChatPayDetail(String orderId) throws Exception {
        return gzgPayCommonService.queryGzgOrderPayInfoByGzgOrderId(orderId);
    }


    /**
     * @Description 验签
     * @Author taoye
     */
    private boolean checkSign(Map<String, String> map) {
        try {
            String sign = map.get("sign");
//            Map<String, String> treeMap = new HashMap<String, String>();
            SortedMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.putAll(map);
            treeMap.remove("serviceName");
            treeMap.remove("functionName");
            treeMap.remove("sign");
            treeMap.put("appid", weChatPropertiesConfig.getAppid());
            treeMap.put("mch_id", String.valueOf(weChatPropertiesConfig.getMerchantId()));
            String weChatSign = WeChatUtil.generateSign(treeMap, weChatPropertiesConfig.getKey());
            LOGGER.info("=================微信返回的sign : {},本地生成的比对的sign  : {} ", sign, weChatSign);
            if (!sign.equals(weChatSign)) {
                LOGGER.error("格子柜微信支付回调验签失败");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("微信支付回调验签出错:", e);
            return false;
        }
    }

    /**
     * 支付成功后对亿联柜子的操作
     * @param gzgOrder
     * @param gzgOrderItemList
     * @param transaction_id
     */

       public void afterPaySuccessToYilian(GzgOrder gzgOrder,List<GzgOrderItem> gzgOrderItemList ,String transaction_id){
           Boolean aBoolean = gzgExchangeService.openDevice(gzgOrder, gzgOrderItemList, GzgPayTypeEnum.WECHAT);//打开柜子
           try {
                   if (aBoolean) {
                       LOGGER.info("微信支付成功后打开亿联柜子成功，柜子编号：{}", gzgOrder.getImei());
                       //打开柜子成功，更新数据库
                       gzgPayCommonService.afterSuccessOpenDevice(gzgOrder, gzgOrderItemList, transaction_id, GzgPayTypeEnum.WECHAT);
                   } else {
                       LOGGER.error("微信支付成功后打开亿联柜子失败，柜子编号：{}", gzgOrder.getImei());
                       //打开柜子失败，更新数据库
                       gzgPayCommonService.afterFailOpenDevice(gzgOrder, gzgOrderItemList, transaction_id, GzgPayTypeEnum.WECHAT);
                   }
           } catch (Exception e) {
               LOGGER.error("微信支付成功后打开亿联柜子并更新数据库报错，报错详情:", e);
           }
       }


    /**
     * 支付成功后对如金柜子的操作
     * @param gzgOrder
     * @param gzgOrderItemList
     * @param transaction_id
     */
    @TxcTransaction(appName = "gzg")
    public void afterPaySuccessToRujin(GzgOrder gzgOrder,List<GzgOrderItem> gzgOrderItemList ,String transaction_id) throws Exception {
        LOGGER.info("afterPaySuccessToRujin，柜子编号：{},订单号:", gzgOrder.getImei(),gzgOrder.getId());
        JSONObject paramm = new JSONObject();
        paramm.put("name",gzgOrder.getImei());
        String resultJson=  callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME,paramm);
        GzgRujinRelation relation =  JSONObject.toJavaObject(JSONObject.parseObject(resultJson), GzgRujinRelation.class);
        Boolean aBoolean = gzgRujinDeviceService.openRujinDevice(gzgOrder.getId(), relation.getTid(), gzgOrderItemList.get(0).getCabinet_no(), GzgPayTypeEnum.WECHAT);//打开柜子
        String xid = TxcContext.getCurrentXid();
        try {
            if (aBoolean) {
                LOGGER.info("微信支付成功后打开如金柜子成功，柜子编号：{}", gzgOrder.getImei());
                //打开柜子命令发送成功，更新数据库
                String gzgOrderId = gzgOrder.getId();
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(gzgOrderId);
                gzgOrderVo.setPayment_no(transaction_id);//支付流水号
                gzgOrderVo.setOrder_status(OrderStateEnum.WAIT_OPEN.getKey());
                gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                //测试挡板
              //  gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setPayment_type(GzgPayTypeEnum.WECHAT.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);
            } else {
                LOGGER.error("微信支付成功后发送打开如金柜子命令失败，柜子编号：{}", gzgOrder.getImei());
                //打开柜子命令发送失败，更新数据库
                String gzgOrderId = gzgOrder.getId();
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(gzgOrderId);
                gzgOrderVo.setPayment_no(transaction_id);//支付流水号
                gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
                gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setPayment_type(GzgPayTypeEnum.WECHAT.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);
            }
        } catch (Exception e) {
            LOGGER.error("微信支付成功后发送打开如金柜子命令并更新数据库报错，报错详情:", e);
        }

    }

    /*@Override
    public void test(String id){
        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", id);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        processCreateRewardRecord(gzgOrder);
    }*/
    /**
     * 支付成功后，生成分成奖励明细记录，并更新奖励表奖励总额和待结算金额
     * @param gzgOrder
     */
    @Transactional
    public  void processCreateRewardRecord(GzgOrder gzgOrder){
        try {
            LOGGER.info("processCreateRewardRecord 微信支付成功后，生成分成奖励明细记录1 ：{}", gzgOrder.getId());
            if (null == gzgOrder || StringUtil.isEmpty(gzgOrder.getHotel_id())) {
                return;
            }
            JSONObject json = new JSONObject();
            //根据店铺id，查询商务合作表的分成比例字段
            json.put("store_id", gzgOrder.getHotel_id());
            json.put("status", "0");//status=0 有效的商务合作分成数据
            String coorperationResult = callDataCenter(CABINET_COORPERATION_GET_BY_STRORE_STATUS, json);
            LOGGER.info("processCreateRewardRecord 微信支付成功后，生成分成奖励明细记录2 ：{}", coorperationResult);
            if (StringUtil.isEmpty(coorperationResult)) {
                return;
            }
            String cuuentTime = DateUtil.getNowTimeStr();
            JSONObject coorperationJson = JSONObject.parseObject(coorperationResult);
            String divide_scale = coorperationJson.getString("divide_scale");
            String orderOldMoney = gzgOrder.getOrder_old_money();
            String userId = coorperationJson.getString("owner_user_id");
            if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(divide_scale)) {
                return;
            }
            //json.put("status","0");//status=0 未结算
            //添加奖励明细记录(暂时status=1 都是已结算状态)
            json.put("owner_user_id", userId);
            json.put("divide_scale", divide_scale);
            json.put("order_id", gzgOrder.getId());
            json.put("buyer_id", gzgOrder.getBuyer_id());
            BigDecimal amount = new BigDecimal(Double.valueOf(orderOldMoney) * 100);
            String reward = String.valueOf(amount.multiply(new BigDecimal(divide_scale)).setScale(0, BigDecimal.ROUND_DOWN));
            json.put("order_old_money", amount);
            json.put("reward", reward);
            json.put("type", "2");
            json.put("status", "1");//status=1 已结算状态
            json.put("create_time", cuuentTime);
            String rewardRecordId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_REWARD_RECORD);
            json.put("id", rewardRecordId);
            callDataCenter(GZG_REWARD_RECORD_INSERT, json);
            LOGGER.info("processCreateRewardRecord 微信支付成功后，生成分成奖励明细记录成功3 ：{}", json.toJSONString());

            //查询奖励金额表，更新奖励表奖励总额和待结算金额
            json = new JSONObject();
            json.put("user_id", userId);
            json.put("type", "2");
            String rewardResult = callDataCenter(CABINET_REPLENISH_REWARD_BY_USERID, json);
            LOGGER.info("processCreateRewardRecord 微信支付成功后，查询奖励金额表4 ：{}", rewardResult);
            JSONArray rewardArray = JSONArray.parseArray(rewardResult);
            if (rewardArray.size() == 0) {
                String replenishRewardId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_REPLENISH_REWARD);
                json.put("id", replenishRewardId);
                json.put("cumulative_reward_amount", reward);
                json.put("may_withdrawal_amount", reward);
                json.put("pending_settlement_amount", "0");
                json.put("cumulative_withdrawal_amount", "0");
                json.put("version", "0");
                json.put("create_time", cuuentTime);
                callDataCenter(CABINET_REPLENISH_REWARD_INSERT, json);
                LOGGER.info("processCreateRewardRecord 微信支付成功后，新增奖励金额表5 ：{}", json.toJSONString());
            } else {
                json = new JSONObject();
                JSONObject rewardJson = rewardArray.getJSONObject(0);
                json.put("id", rewardJson.getString("id"));
                BigDecimal cumulative_reward_amount = new BigDecimal(rewardJson.getString("cumulative_reward_amount")).add(new BigDecimal(reward));
                BigDecimal may_withdrawal_amount = new BigDecimal(rewardJson.getString("may_withdrawal_amount")).add(new BigDecimal(reward));
                json.put("cumulative_reward_amount", String.valueOf(cumulative_reward_amount));
                json.put("may_withdrawal_amount", String.valueOf(may_withdrawal_amount));
                json.put("update_time", cuuentTime);
                json.put("version", rewardJson.getString("version"));
                callDataCenter(UPDATE_REPLENISH_REWARD_BYID, json);
                LOGGER.info("processCreateRewardRecord 微信支付成功后，更新奖励金额表6 ：{}", json.toJSONString());
            }
        }catch (Exception e){
            LOGGER.info("processCreateRewardRecord 微信支付成功后，更新奖励金额表报错， 异常 ：", e);
        }
    }



}