package com.ly.mt.cabinet.c.alipay.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.alipay.service.GzgPayCommonService;
import com.ly.mt.cabinet.c.coupon.entity.CouponCode;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.enumEntity.GzgCodeLengthEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.OrderStateEnum;
import com.ly.mt.cabinet.c.response.GoodsInfoResp;
import com.ly.mt.cabinet.c.response.PayInfoResp;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_CODE_CABINET;

@Service
@PropertySource(value = {"classpath:myconfig-c.properties"}, ignoreResourceNotFound = false, encoding = "UTF-8")
public class GzgPayCommonServiceImpl extends BaseServiceImpl implements GzgPayCommonService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgPayCommonServiceImpl.class);
    @Value("${gzg.mctId}")
    private String mctId;

    @Autowired
    private ReplenishmentGirdService replenishmentGirdService;

    public String getMctId() {
        return mctId;
    }
    @Autowired
    GzgCouponCodeService gzgCouponCodeService;

    @Override
    public Boolean isTimeOut(List<GzgOrderItem> gzgOrderItemVos) throws Exception {

        String gzgOrderId = gzgOrderItemVos.get(0).getOrder_id();
        Boolean isTimeOut = false;
        for (int i = 0; i < gzgOrderItemVos.size(); i++) {
            String cabinetNo = gzgOrderItemVos.get(i).getCabinet_no();
            String code = gzgOrderItemVos.get(i).getImei();
            String s = redisService.get(REDIS_GZG_CODE_CABINET, code + ":" + cabinetNo);
            if (StringUtil.isEmpty(s)) {
                isTimeOut = true;
                break;
            }
        }
        if (isTimeOut) {
            GzgOrder gzgOrdersVo = new GzgOrder();
            gzgOrdersVo.setId(gzgOrderId);
            gzgOrdersVo.setOrder_status(OrderStateEnum.OVERTIME.getKey());
            //gzgOrderVo.setPaymentNo(map.get("transaction_id"));//支付流水号/超时，没有订单信息
            gzgOrdersVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            updateGzgOrder(gzgOrdersVo,null);
        }
        return isTimeOut;
    }

    @Override
    public void afterFailOpenDevice(GzgOrder gzgOrder, List<GzgOrderItem> gzgOrderItemVos, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum) throws Exception {

        LOGGER.info("打開格子柜失败，更新订单信息");
        String gzgOrderId = gzgOrder.getId();
        String gzgCode = gzgOrder.getImei();
        String gzgOrderItemId = gzgOrderItemVos.get(0).getId();
        String cabineNo = gzgOrderItemVos.get(0).getCabinet_no();

        GzgOrder gzgOrderVo = new GzgOrder();
        gzgOrderVo.setId(gzgOrderId);
        gzgOrderVo.setPayment_no(transaction_id);//支付流水号
        gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
        gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
        gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
        gzgOrderVo.setPayment_type(gzgPayTypeEnum.getKey());//1 网银，2 微信，3 支付宝，4 其他，5 信用，
        updateGzgOrder(gzgOrderVo,null);//保存交易信息
    }



    @Override
    public void afterPayFail(GzgOrder gzgOrder, List<GzgOrderItem> gzgOrderItemVos, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum) throws Exception {

        LOGGER.info("支付失败，更新订单信息");
        String gzgOrderId = gzgOrder.getId();
        GzgOrder gzgOrderVo = new GzgOrder();
        gzgOrderVo.setId(gzgOrderId);
        gzgOrderVo.setPayment_no(transaction_id);//支付流水号
        gzgOrderVo.setOrder_status(OrderStateEnum.PAY_FAIL.getKey());
        gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
        gzgOrderVo.setPayment_type(gzgPayTypeEnum.getKey());//1 网银，2 微信，3 支付宝，4 其他，5 信用，
        updateGzgOrder(gzgOrderVo,null);//保存交易信息
    }




    @Override
    public void afterSuccessOpenDevice(GzgOrder gzgOrder, List<GzgOrderItem> gzgOrderItemVos, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum) throws Exception {

        LOGGER.info("打開格子柜成功，更新订单信息");
        String gzgOrderId = gzgOrder.getId();
        GzgOrder gzgOrderVo = new GzgOrder();
        gzgOrderVo.setId(gzgOrderId);
        gzgOrderVo.setPayment_no(transaction_id);//支付流水号
        gzgOrderVo.setOrder_status(OrderStateEnum.COMPLETED.getKey());
        gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
        gzgOrderVo.setPay_time(DateUtil.getNowTimeStr());
        gzgOrderVo.setPayment_type(gzgPayTypeEnum.getKey() + "");//1 网银，2 微信，3 支付宝，4 其他，5 信用，
        updateGzgOrder(gzgOrderVo,gzgOrderItemVos);
        try{
            replenishmentGirdService.createReplenishOrder(gzgOrder.getId());
        }catch (Exception e){
            LOGGER.info("生成补货订单异常 :{}",e);
        }
    }

    @Override
    public ResponseJson queryGzgOrderPayInfoByGzgOrderId(String orderId) throws Exception {
        LOGGER.info("GzgPayCommonServiceImpl。queryGzgOrderPayInfoByGzgOrderId，订单编号:{}", orderId);
        JSONObject result = new JSONObject();
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", orderId);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        LOGGER.info("调用数据中心，查询订单item{}", resultGzgOrderItem);
        List<GzgOrderItem> GzgOrderItemList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);

        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", orderId);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        LOGGER.info("调用数据中心，查询订单主体信息{}", resultGzgOrders);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);

        String amount = gzgOrder.getOrder_money();
        String payTime = StringUtil.isEmpty(gzgOrder.getOrder_finish_time())?"":gzgOrder.getOrder_finish_time();

        List<GoodsInfoResp> goodsInfosResp = new ArrayList<>();

        for (GzgOrderItem vo : GzgOrderItemList) {
            GoodsInfoResp resp = new GoodsInfoResp();
            resp.setGoodsName(vo.getSku_name());
            resp.setCabinetNo(Integer.valueOf(vo.getCabinet_no()));
            goodsInfosResp.add(resp);
        }
        LOGGER.info("商品信息{}", goodsInfosResp);
        JSONObject goodsInfo = new JSONObject();
        goodsInfo.put("code", GzgOrderItemList.get(0).getImei());
        goodsInfo.put("goodsInfos", goodsInfosResp);
        result.put("goodsInfo", goodsInfo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PayInfoResp payInfoResp = null;
        LOGGER.info("判断订单状态{}", gzgOrder.getOrder_status());
        if (OrderStateEnum.TO_BE_PAY.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setCloseTime(gzgOrder.getCreate_time());
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setDisCountMoney(Double.parseDouble(gzgOrder.getOrder_discount_money()));
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESPONSE_CODE_ERROR, "您还未完成支付，请去支付页面支付", result);
        } else if (OrderStateEnum.COMPLETED.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            //封装优惠金额
            payInfoResp.setDisCountMoney(Double.parseDouble(gzgOrder.getOrder_discount_money()));
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESPONSE_CODE_SUCCESS, "支付成功", result);
        } else if (OrderStateEnum.OVERTIME.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
           payInfoResp.setCloseTime(DateUtil.getNowTimeStr());
            result.put("payInfo", payInfoResp);
            LOGGER.info("支付成功柜子打开成功，返回的数据{}", result);
            return ResponseUtil.getResponseJson(ResponseCode.RESPONSE_CODE_ERROR, "支付超时 订单关闭", result);
       }else if (OrderStateEnum.OPEN_FAIL.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
           payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
           result.put("payInfo", payInfoResp);
         //  return ResponseUtil.getResponseJson(, "舱门打开失败", result);
           ResponseJson res = new ResponseJson();
           res.setCode("2");res.setMsg("舱门打开失败");res.setResult(result);
            return res;
        }else if (OrderStateEnum.PAY_FAIL.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            payInfoResp.setCloseTime(gzgOrder.getCreate_time());
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESPONSE_CODE_ERROR, "支付失败", result);
        }else {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "无效订单");
        }
    }


    @TxcTransaction(appName = "gzg")
    @Override
    public void updateGzgOrder(GzgOrder gzgOrdersVo,List<GzgOrderItem> gzgOrderItemList) throws Exception {
        String xid = TxcContext.getCurrentXid();
        JSONObject jsonGzgOrders = JSONObject.parseObject(JSONObject.toJSONString(gzgOrdersVo));
        jsonGzgOrders.put("xid", xid);
        callDataCenter(GZG_ORDER_UPDATE, jsonGzgOrders);
        if(Objects.nonNull(gzgOrderItemList)){
            for (GzgOrderItem item:gzgOrderItemList) {
                JSONObject updStockJson = new JSONObject();
                updStockJson.put("imei",item.getImei());
                updStockJson.put("xid",xid);
                if(item.getImei().length() == GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
                   //展柜扣减库存 按照sku和imei
                    updStockJson.put("sku_id",item.getSku_id());
                    LOGGER.info("调用数据中心 扣减展柜库存 {}号柜，sku:{}", item.getImei(),item.getSku_id());
                    callDataCenter(GZG_SHOWCASE_GOODS_PLAN_UPDATE, updStockJson);
                }else{
                    updStockJson.put("cabinet_no",item.getCabinet_no());
                    LOGGER.info("调用数据中心 扣减库存 {}号柜，{}号柜门", item.getImei(),item.getCabinet_no());
                    callDataCenter(GZG_RUJIN_YILIAN_GOODS_PLAN_UPDATE, updStockJson);
                }
            }
        }
    }

    @Override
    public void updateGzgUserCoupon(GzgOrder gzgOrder) {
        String notUserdCoupon = "";
        LOGGER.info("updateGzgUserCoupon");
        if (StringUtil.isNotEmpty(gzgOrder.getBuyer_id())) {
            //查看新人八折券是否使用
            try {
                notUserdCoupon = gzgCouponCodeService.isUsedCouponByUserIdAndCouponId(gzgOrder.getBuyer_id(), GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT.getId());
                LOGGER.info("updateGzgUserCoupon {}",notUserdCoupon);
                if(StringUtil.isNotEmpty(notUserdCoupon)){
                    //更新优惠券使用状态为已使用
                    List<CouponCode> couponCodes = JSONObject.parseArray(notUserdCoupon, CouponCode.class);
                    JSONObject CouponStatus = new JSONObject();
                    CouponStatus.put("user_id",gzgOrder.getBuyer_id());
                    CouponStatus.put("coupon_id",couponCodes.get(0).getCoupon_id());
                    callDataCenter(DataCenterMethod.GZG_COUPON_CODE_UPDATE_USE_STATUS,CouponStatus);
                    return;
                }
            }catch (Exception e){
                return;
            }
        }
    }

}
