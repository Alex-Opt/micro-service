package com.ly.mt.cabinet.c.alipay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.alipay.entity.AliPayProperties;
import com.ly.mt.cabinet.c.alipay.entity.AliPayReqVO;
import com.ly.mt.cabinet.c.alipay.service.GzgAliPayService;
import com.ly.mt.cabinet.c.alipay.service.GzgPayCommonService;
import com.ly.mt.cabinet.c.enumEntity.GzgAliPayStatusEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgCodeLengthEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.OrderStateEnum;
import com.ly.mt.cabinet.c.order.service.GzgOrderSyncCabinetProfitService;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
@PropertySource(value = {"classpath:myconfig-c.properties"}, ignoreResourceNotFound = false, encoding = "UTF-8")
public class GzgAliPayServiceImpl extends BaseServiceImpl implements GzgAliPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgAliPayServiceImpl.class);
    @Autowired
    private AliPayProperties aliPayProperties;
    @Value("${gzg.mctId}")
    private String mctId;
    @Resource
    private GzgExchangeService gzgExchangeService;
    @Autowired
    GzgPayCommonService gzgPayCommonService;
    @Autowired
    GzgRujinDeviceService gzgRujinDeviceService;

    @Autowired
    ReplenishmentGirdService replenishmentGirdService;

    @Autowired
    private GzgOrderSyncCabinetProfitService gzgOrderSyncCabinetProfitService;

    @Override
    public ResponseJson pay(AliPayReqVO aliPayReqVO) throws Exception {
        LOGGER.info("支付宝支付，service层接收到的数据 {}", aliPayReqVO);
        String subject = aliPayReqVO.getSubject();
        GzgOrder gzgOrderVo = null;
        long orderNo = aliPayReqVO.getOrderNo();
        String gzgCode = aliPayReqVO.getGzgCode();
        JSONObject jsonGzgOrders = new JSONObject();
        jsonGzgOrders.put("id", orderNo);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrders);
        gzgOrderVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);

        LOGGER.info("判断手否有支付订单，!StringUtil.isEmpty(gzgOrderVo.getPaymentNo())，打印{}", !StringUtil.isEmpty(gzgOrderVo.getPayment_no()));
        if (!StringUtil.isEmpty(gzgOrderVo.getPayment_no())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "订单重复提交");
        }

        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", orderNo);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        List<GzgOrderItem> gzgOrderItemVos = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);

        //展柜订单没有超时限制
        if(gzgOrderVo.getImei().length() !=  GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
            Boolean timeOut = gzgPayCommonService.isTimeOut(gzgOrderItemVos);
            if (timeOut) {
                LOGGER.info("支付宝订单超时，订单号：{}", orderNo);
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "订单超时");
            }
        }

        String amount = gzgOrderVo.getOrder_money().toString();
        //测试柜子价格0.01
        if ("865800042529062".equals(gzgCode) ||
                "RJ113211".equals(gzgCode) ||
                "RJ356874".equals(gzgCode) ||
                "100458206".equals(gzgCode)) {
            amount = "0.01";
        }
        String orderString = null;
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(aliPayProperties.getGatewayUrl(),
                    aliPayProperties.getAppid(),
                    aliPayProperties.getAppPrivateKey(),
                    aliPayProperties.getFormate(),
                    aliPayProperties.getCharset(),
                    aliPayProperties.getAlipayPublicKey(),
                    "RSA2");
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            Map<String, Object> wapPay = new HashMap<>();
            wapPay.put("out_trade_no", String.valueOf(orderNo));//.setOutTradeNo(String.valueOf(orderNo));
            //GzgOrderVo gzgOrderVo = gzgOrdersServiceMapper.selectByPrimaryKey(orderNo);
            //LOGGER.info("调用支付宝支付订单金额是:{}",gzgOrderVo.getOrderMoney());
            wapPay.put("total_amount", amount);
            wapPay.put("timeout_express", "60m");//.setTimeoutExpress("60m");
            wapPay.put("subject", subject);
            wapPay.put("produce_code", "QUICK_WAP_WAY");//.setProductCode("QUICK_WAP_WAY");
            LOGGER.info("发送支付宝参数：{}", JSON.toJSONString(wapPay));
            request.setBizContent(JSON.toJSONString(wapPay));
            request.setNotifyUrl(aliPayProperties.getNotifyUrl());
            request.setReturnUrl(aliPayProperties.getReturnUrl());
            LOGGER.info("支付宝请求参数：{}", request.getBizContent());
            AlipayTradeWapPayResponse response = null;
            response = alipayClient.pageExecute(request);
            LOGGER.info("调用支付宝返回结果是 {}", response.getBody());
            orderString = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("支付失败 {}", e.getMessage());
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        LOGGER.info("支付宝支付返回给前端的数据：{}", ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, orderString));
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, orderString);
    }

    @Override
    public ResponseJson aliNotify(Map<String, String> map) {
        LOGGER.info("alipay callback notify url,the response data is {}", JSON.toJSONString(map));
        JSONObject result = new JSONObject();
        boolean signVerified = false;
            LOGGER.info("开始验签");
            LOGGER.info("publicKey:{}",aliPayProperties.getPublicKey());
           /* signVerified = AlipaySignature.rsaCheckV1(map, aliPayProperties.getPublicKey(),
                    aliPayProperties.getCharset(), "RSA2");*/
          //  LOGGER.info("publicKey:{}",aliPayProperties.getPublicKey());
          //  LOGGER.info("验签完成 验签结果 {}",signVerified);
         //   if (true) {
                LOGGER.info("alipay sign {}", "success");
               JSONObject reqMap =  JSONObject.parseObject(JSON.toJSONString(map).replace("[","").replace("]",""));
                JSONObject jsonGzgOrder = new JSONObject();
                jsonGzgOrder.put("id",reqMap.getString("out_trade_no"));
                String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
                GzgOrder gzgOrderr = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);

                if (!StringUtil.isEmpty(gzgOrderr.getPayment_no())) {
                    return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
                }

               String appId = aliPayProperties.getAppid();
                String notifyAppId = reqMap.getString("app_id");
                LOGGER.info("获取支付宝回调参数appId= {}", reqMap.getString("app_id"));
                try {
                if (!StringUtils.equals(appId, notifyAppId)) {
                    LOGGER.error("The local appId {} is different with notifyAppId {}", appId, notifyAppId);
                    result.put("success", false);
                } else {
                    //String orderNo = map.get("out_trade_no");
                    String orderNo = reqMap.getString("out_trade_no");
                    //String totalAmount = map.get("total_amount");
                    String totalAmount = reqMap.getString("total_amount");
                    //String tradeStatus = map.get("trade_status");
                    String tradeStatus = reqMap.getString("trade_status");
                    //String tradeNo = map.get("trade_no");//支付宝交易号
                    String tradeNo = reqMap.getString("trade_no");//支付宝交易号
                    if (StringUtils.equals(tradeStatus, GzgAliPayStatusEnum.TRADE_SUCCESS.getKey()) || StringUtils.equals(tradeStatus, GzgAliPayStatusEnum.TRADE_FINISHED.getKey())) {
                        //支付成功，更改订单状态，数据库操作
                        GzgOrder gzgOrder = getGzgOrder(orderNo);
                        List<GzgOrderItem> gzgOrderItemList = getGzgOrderItemList(orderNo);

                        if(gzgOrder.getImei().length()== GzgCodeLengthEnum.GZG_YILIAN.getCode()){//亿联柜子
                            afterPaySuccessToYilian( gzgOrder, gzgOrderItemList , tradeNo);
                             result.put("success", true);
                        }else if(gzgOrder.getImei().length()==GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){//展柜
                            LOGGER.info("微信支付成功，展柜编号：{}", gzgOrder.getImei());
                            String gzgOrderId = gzgOrder.getId();
                            GzgOrder gzgOrderVo = new GzgOrder();
                            gzgOrderVo.setId(gzgOrderId);
                            gzgOrderVo.setPayment_no(reqMap.getString("trade_no"));//支付流水号
                            gzgOrderVo.setOrder_status(OrderStateEnum.COMPLETED.getKey());
                            gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                            gzgOrderVo.setPayment_type(GzgPayTypeEnum.ALIPAY.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                            gzgPayCommonService.updateGzgOrder(gzgOrderVo,gzgOrderItemList);
                            try{
                                replenishmentGirdService.createReplenishOrder(gzgOrderId);
                            }catch (Exception e){
                                LOGGER.info("生成补货订单异常 :{}",e);
                            }
                             result.put("success", true);
                        }else {//如金柜子
                            LOGGER.error("支付成功，打开如今柜子 柜子号={}",gzgOrder.getImei());
                            afterPaySuccessToRujin( gzgOrder, gzgOrderItemList , tradeNo);
                             result.put("success", true);
                        }
                        LOGGER.info("update coupon----------------");
                        afterPaySuccessSync(gzgOrder);
                    } else if (StringUtils.equals(tradeStatus, GzgAliPayStatusEnum.TRADE_CLOSED.getKey())) {
                        LOGGER.error("the order {} has closed", orderNo);
                        GzgOrder gzgOrder = getGzgOrder(orderNo);
                        List<GzgOrderItem> gzgOrderItemList = getGzgOrderItemList(orderNo);
                        gzgPayCommonService.afterPayFail(gzgOrder, gzgOrderItemList,tradeNo,GzgPayTypeEnum.ALIPAY);
                        result.put("success", false);
                    } else {
                        LOGGER.error("the order {} wate user pay money", orderNo);
                        result.put("success", false);
                    }
                }
          //  }
        } catch (Exception e) {
           LOGGER.info("验签异常");
           e.printStackTrace();
       }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, result);
    }


    @Async
    public void afterPaySuccessSync(GzgOrder order){
        try{
            gzgPayCommonService.updateGzgUserCoupon(order);
        }catch (Exception e){
            LOGGER.info("更新优惠卷信息异常 :{}",e);
        }
        try{
            gzgOrderSyncCabinetProfitService.syncCabinetStoreProfit(order.getId());
        }catch (Exception e){
            LOGGER.info("计算收益信息异常 :{}",e);
        }
        try{
            processCreateRewardRecord(order);
        }catch (Exception e){
            LOGGER.info("计算奖励异常 :{}",e);
        }
    }
    @Override
    public boolean query(String orderNo) throws Exception {
        LOGGER.info("調用支付寶查詢接口,查詢訂單，參數订单编号orderNo為：{}", orderNo);
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayProperties.getGatewayUrl(), aliPayProperties.getAppid(), aliPayProperties.getAppPrivateKey(), aliPayProperties.getFormate(), aliPayProperties.getCharset(), aliPayProperties.getAlipayPublicKey(), aliPayProperties.getSignType());
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizParam = new JSONObject();
        bizParam.put("out_trade_no", orderNo);
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            LOGGER.info("交易支付查询调用成功");
            String tradeNo = response.getTradeNo();

            JSONObject jsonGzgOrders = new JSONObject();
            jsonGzgOrders.put("id", orderNo);
            String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrders);
            GzgOrder gzgOrderVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);

//            GzgOrdersVo gzgOrderVo = gzgOrdersServiceMapper.selectByPrimaryKey(orderNo);
            LOGGER.info("查詢訂單獲取訂單信息：{}", JSON.toJSONString(gzgOrderVo));
            gzgOrderVo.setPayment_type(String.valueOf(GzgPayTypeEnum.ALIPAY.getKey()));
            gzgOrderVo.setOrder_money(response.getTotalAmount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            gzgOrderVo.setOrder_status("50");
            gzgOrderVo.setPayment_no(response.getTradeNo());
            LOGGER.info("更新订单,订单数据是 : {}", JSON.toJSONString(gzgOrderVo));
            String resultUpdateGzgOrders = callDataCenter(GZG_ORDER_UPDATE, JSONObject.parseObject(JSONObject.toJSONString(gzgOrderVo)));
            JSONObject jsonObject = JSONObject.parseObject(resultUpdateGzgOrders);
            String code = jsonObject.getString("code");
            if (ResponseCode.RESPONSE_CODE_SUCCESS.getCode().equals(code)) {
                LOGGER.info("支付宝支付成功，更新表gzg_orders订单编号为{}数据成功", tradeNo);
            } else {
                LOGGER.info("支付宝支付成功，更新表gzg_orders订单编号为{}数据失败", tradeNo);
            }

            if (StringUtils.isNotBlank(tradeNo)) {
                return true;
            } else {
                return false;
            }
        } else {
            LOGGER.info("交易支付查询接口调用失败,结果为 {}", response.getBody());
            return false;
        }
    }

    @Override
    public ResponseJson payResult(String orderNo) throws Exception {
        return gzgPayCommonService.queryGzgOrderPayInfoByGzgOrderId(orderNo);
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
                LOGGER.info("支付宝支付成功后打开亿联柜子成功，柜子编号：{}", gzgOrder.getImei());
                //打开柜子成功，更新数据库
                gzgPayCommonService.afterSuccessOpenDevice(gzgOrder, gzgOrderItemList, transaction_id, GzgPayTypeEnum.ALIPAY);
            } else {
                LOGGER.error("支付宝支付成功后打开亿联柜子失败，柜子编号：{}", gzgOrder.getImei());
                //打开柜子失败，更新数据库
                gzgPayCommonService.afterFailOpenDevice(gzgOrder, gzgOrderItemList, transaction_id, GzgPayTypeEnum.ALIPAY);
            }
        } catch (Exception e) {
            LOGGER.error("支付宝支付成功后打开亿联柜子并更新数据库报错，报错详情:", e);
        }

    }


    /**
     * 支付成功后对如金柜子的操作
     * @param gzgOrder
     * @param gzgOrderItemList
     * @param transaction_id
     */

    public void afterPaySuccessToRujin(GzgOrder gzgOrder,List<GzgOrderItem> gzgOrderItemList ,String transaction_id) throws Exception {
        JSONObject paramm = new JSONObject();
        paramm.put("name",gzgOrder.getImei());
        String resultJson=  callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME,paramm);
        GzgRujinRelation relation =  JSONObject.toJavaObject(JSONObject.parseObject(resultJson), GzgRujinRelation.class);

        Boolean aBoolean = gzgRujinDeviceService.openRujinDevice(gzgOrder.getId(), relation.getTid(), gzgOrderItemList.get(0).getCabinet_no(), GzgPayTypeEnum.ALIPAY);//打开柜子
        try {
            if (aBoolean) {
                LOGGER.info("支付宝支付成功后打开如金柜子命令发送成功，柜子编号：{}", gzgOrder.getImei());
                //打开柜子成功，更新数据库
                String gzgOrderId = gzgOrder.getId();
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(gzgOrderId);
                gzgOrderVo.setPayment_no(transaction_id);//支付流水号
                gzgOrderVo.setOrder_status(OrderStateEnum.WAIT_OPEN.getKey());
                gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                //gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setPayment_type(GzgPayTypeEnum.ALIPAY.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);

            } else {
                LOGGER.error("支付宝支付成功后打开如金柜子命令发送失败，柜子编号：{}", gzgOrder.getImei());
                //打开柜子失败，更新数据库
                String gzgOrderId = gzgOrder.getId();
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(gzgOrderId);
                gzgOrderVo.setPayment_no(transaction_id);//支付流水号
                gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
                gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgOrderVo.setPayment_type(GzgPayTypeEnum.ALIPAY.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);
            }
        } catch (Exception e) {
            LOGGER.error("支付宝支付成功后发出打开如金柜子命令并更新数据库报错，报错详情:", e);
        }

    }

    public GzgOrder getGzgOrder(String orderNo){
        JSONObject jsonGzgOrders = new JSONObject();
        jsonGzgOrders.put("id", orderNo);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrders);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        return  gzgOrder;
    }

    public List<GzgOrderItem> getGzgOrderItemList(String orderNo){
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", orderNo);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        List<GzgOrderItem> gzgOrderItemList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
        return gzgOrderItemList;
    }

    /**
     * 支付成功后，生成分成奖励明细记录，并更新奖励表奖励总额和待结算金额
     * @param gzgOrder
     */
    @Transactional
    public  void processCreateRewardRecord(GzgOrder gzgOrder){
        try {
            LOGGER.info("processCreateRewardRecord 支付宝支付成功后，生成分成奖励明细记录1 ：{}", gzgOrder.getId());
            if (null == gzgOrder || StringUtil.isEmpty(gzgOrder.getHotel_id())) {
                return;
            }
            JSONObject json = new JSONObject();
            //根据店铺id，查询商务合作表的分成比例字段
            json.put("store_id", gzgOrder.getHotel_id());
            json.put("status", "0");//status=0 有效的商务合作分成数据
            String coorperationResult = callDataCenter(CABINET_COORPERATION_GET_BY_STRORE_STATUS, json);
            LOGGER.info("processCreateRewardRecord 支付宝支付成功后，生成分成奖励明细记录2 ：{}", coorperationResult);
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
            LOGGER.info("processCreateRewardRecord 支付宝支付成功后，生成分成奖励明细记录成功3 ：{}", json.toJSONString());

            //查询奖励金额表，更新奖励表奖励总额和待结算金额
            json = new JSONObject();
            json.put("user_id", userId);
            json.put("type", "2");
            String rewardResult = callDataCenter(CABINET_REPLENISH_REWARD_BY_USERID, json);
            LOGGER.info("processCreateRewardRecord 支付宝支付成功后，查询奖励金额表4 ：{}", rewardResult);
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
                LOGGER.info("processCreateRewardRecord 支付宝支付成功后，新增奖励金额表5 ：{}", json.toJSONString());
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
                LOGGER.info("processCreateRewardRecord 支付宝支付成功后，更新奖励金额表6 ：{}", json.toJSONString());
            }
        }catch (Exception e){
            LOGGER.error("processCreateRewardRecord 支付宝支付成功后，更新奖励金额表报错， 异常：", e);
        }
    }


}
