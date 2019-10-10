package com.ly.mt.cabinet.c.order.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.coupon.entity.CouponCode;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.entity.GzgInfoVo;
import com.ly.mt.cabinet.c.enumEntity.GzgCodeLengthEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgOrderSourceEnum;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.entity.GzgOrderItemVo;
import com.ly.mt.cabinet.c.good.entity.GoodsSkuInfo;
import com.ly.mt.cabinet.c.good.entity.GzgSkuPicture;
import com.ly.mt.cabinet.c.order.entity.*;
import com.ly.mt.cabinet.c.order.service.GzgOrderScheduledService;
import com.ly.mt.cabinet.c.order.service.GzgOrderService;
import com.ly.mt.cabinet.c.response.GoodsInfoResp;
import com.ly.mt.cabinet.c.response.PayInfoResp;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.redis.RedisKey;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;


@Service
public class GzgOrderServiceImpl extends BaseServiceImpl implements GzgOrderService {
    private final static Logger log = LoggerFactory.getLogger(FeignServiceImpl.class);
    @Autowired
    GzgExchangeService gzgExchangeService;
    @Autowired
    GzgCouponCodeService gzgCouponCodeService;
    @Autowired
    GzgRujinDeviceService   gzgRujinDeviceService;
    @Autowired
    GzgOrderScheduledService gzgOrderScheduledService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseJson gzgOrderGenerate(WebOrderVo webOrderVo) throws Exception {
        log.info("下单开始：{}" ,webOrderVo.getImei());
        String imei = webOrderVo.getImei();
        List<WebGZG> skuIds = webOrderVo.getSkuIds();
        if(imei.length()== GzgCodeLengthEnum.GZG_YILIAN.getCode()){//亿联柜子
            Boolean isAvailableBycode = gzgExchangeService.getIsAvailableBycode(imei);//判断是否在线
            if (!isAvailableBycode) {
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "设备不可用，请联系运维人员");
            }
            Boolean locking = isLocking(skuIds, imei);
            if(locking){//判断商品是否锁定
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "商品被锁定，三十秒后再购买");
            }
        }else if(imei.length()==GzgCodeLengthEnum.GZG_RUJIN.getCode()){//如金的柜子
            log.info("如金下单" );
            if(skuIds.size()>1){//如金的柜子一次只能买一个
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "一次只能买单个商品");
            }

            Boolean locking = isLocking(skuIds, imei);
            if(locking){//判断商品是否锁定
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "商品被锁定，三十秒后再购买");
            }
            log.info("查看如金设备是否在线");

            JSONObject paramm = new JSONObject();
            paramm.put("name",imei);
            String resultJson=  callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME,paramm);
            GzgRujinRelation relation =  JSONObject.toJavaObject(JSONObject.parseObject(resultJson), GzgRujinRelation.class);
            Boolean online = gzgRujinDeviceService.isOnline(relation.getTid());
            log.info("查看如金设备货道是否故障");
            Boolean aBoolean = gzgRujinDeviceService.queryHgState(relation.getTid(),skuIds.get(0).getCabinetNo(),"0");//参数0代表查询命令
            if (!online) {//判断柜子是否可用
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "设备不可用，请联系运维人员");
            }
            if(!aBoolean){
                aBoolean = gzgRujinDeviceService.queryHgState(relation.getTid(),skuIds.get(0).getCabinetNo(),"1");
                if(!aBoolean){
                    return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "该货道有故障，请联系运维人员");
                }
            }
        }
        log.info("开始下单");
        ResponseJson responseJson = orderCreatePublic(webOrderVo);
        return responseJson;
    }

    @Override
    public ResponseJson getGzgOrderListByUserId(String userId) throws Exception {
        List<GzgOrder> gzgOrderList = getGzgOrderList(userId);
        if(gzgOrderList==null || gzgOrderList.size()==0){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }
        List<GzgOrderVo> gzgOrderVoList = new ArrayList<GzgOrderVo>();
        GzgOrderVo gzgOrderVo = null;
        for(int i=0;i<gzgOrderList.size();i++){
            GzgOrder gzgOrder = gzgOrderList.get(i);
            String order_status = gzgOrder.getOrder_status();
            log.info("订单状态:结果{}",gzgOrder.getOrder_status());
            if(OrderStateEnum.TO_BE_PAY.getKey().equals(order_status) && StringUtil.isEmpty(gzgOrder.getPayment_no())){//判段代付款状态是否处于超时状态
                log.info("超时判断:结果{}",compareDate(gzgOrder.getCreate_time()));
                if(compareDate(gzgOrder.getCreate_time())>180){//判断订单是否超时，超时即更新订单状态
                updateGzgOrderState(gzgOrder.getId());
                    gzgOrder.setOrder_status(OrderStateEnum.OVERTIME.getKey());
                }
            }
            gzgOrderVo = new GzgOrderVo();
            gzgOrderVo.setId(gzgOrder.getId());
            gzgOrderVo.setCreate_time(gzgOrder.getCreate_time());
            gzgOrderVo.setOrder_status(gzgOrder.getOrder_status());
            gzgOrderVo.setOrder_money(gzgOrder.getOrder_money());
            gzgOrderVo.setOrder_discount_money(gzgOrder.getOrder_discount_money());
            List<GzgOrderItem> gzgOrderItemList = getGzgOrderItemList(gzgOrder.getId());
            List<GzgOrderItemVo> gzgOrderItemVoList = new ArrayList<GzgOrderItemVo>();
            for (int j = 0; j <gzgOrderItemList.size() ; j++) {
                GzgOrderItemVo gzgOrderItemVo = new GzgOrderItemVo();
                gzgOrderItemVo.setOrder_id(gzgOrderItemList.get(j).getId());
                gzgOrderItemVo.setSku_price(gzgOrderItemList.get(j).getSku_price());
                gzgOrderItemVo.setPicture_url(getGzgSkuPictureVo(gzgOrderItemList.get(j).getSku_id()).getSmall_pic_url());
                gzgOrderItemVo.setSku_num("1");
                gzgOrderItemVo.setSku_name(gzgOrderItemList.get(j).getSku_name());
                gzgOrderItemVoList.add(gzgOrderItemVo);
            }
            gzgOrderVo.setGzgOrderItemVoList(gzgOrderItemVoList);
            gzgOrderVoList.add(gzgOrderVo);
        }
        log.info("订单列表返回的数据:{}",gzgOrderVoList);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,gzgOrderVoList);
    }

    @Override
    public ResponseJson isOrderTimeOutOrComplete(String orderId) throws Exception {
        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", orderId);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        log.info("查看订单是否超时或支付完成，查询订单主体信息{}", resultGzgOrders);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        if(OrderStateEnum.OVERTIME.getKey().equals(gzgOrder.getOrder_status())){
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_GZG_ORDER_TIMEOUT);
        }
        if(OrderStateEnum.COMPLETED.getKey().equals(gzgOrder.getOrder_status())){
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_GZG_ORDER_COMPLETE);
        }
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson retryOrder(String orderId,String userId) throws Exception {
        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", orderId);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        log.info("重新发起支付，查询订单主体信息{}", resultGzgOrders);
        List<GzgOrderItemVo> gzgOrderItemVoList = new ArrayList<GzgOrderItemVo>();
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgOrders), GzgOrder.class);
        BigDecimal amount = new BigDecimal(gzgOrder.getOrder_old_money());
        if(OrderStateEnum.TO_BE_PAY.getKey().equals(gzgOrder.getOrder_status())){
            String usedCouponByUserIdAndCouponId = "";
            if (StringUtil.isNotEmpty(userId)) {
                //查看新人八折券是否使用
                usedCouponByUserIdAndCouponId = gzgCouponCodeService.isUsedCouponByUserIdAndCouponId(userId, GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT.getId());
            }
            JSONObject jsonGzgOrderItem = new JSONObject();
            jsonGzgOrderItem.put("order_id", orderId);
            String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
            log.info("调用数据中心，查询订单item{}", resultGzgOrderItem);
            List<GzgOrderItem> GzgOrderItemList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
            //总的优惠
            BigDecimal discountMoney = BigDecimal.ZERO;
            //商品明细中除单价最大商品之外的其他商品分摊优惠总额
            BigDecimal lastPromotionPrice =BigDecimal.ZERO;
            log.info("是否使用优惠券:usedCouponByUserIdAndCouponId:{}", usedCouponByUserIdAndCouponId);
            if (StringUtil.isEmpty(usedCouponByUserIdAndCouponId) || "[]".equals(usedCouponByUserIdAndCouponId)){
                log.info("优惠卷信息为空");
                gzgOrder.setOrder_discount_money("0");
            }else {
                log.info("优惠卷信息不为空");
                if(amount.multiply(new BigDecimal("0.2")).compareTo(new BigDecimal("20"))==1){
                    if(GzgOrderItemList.size() == 1){
                        //单个商品
                        GzgOrderItemList.get(0).setPomotion_price("20");
                        GzgOrderItemList.get(0).setPayment_price(amount.subtract(new BigDecimal("20"))
                                .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }else{
                        //多个商品 按商品价格升序排序
                        GzgOrderItemList = GzgOrderItemList
                                .stream()
                                .sorted(Comparator.comparing(GzgOrderItem::getSku_price).reversed())
                                .collect(Collectors.toList());
                        int k = 0;
                        for(;k < GzgOrderItemList.size()-1;k++){
                            GzgOrderItem orderItem = GzgOrderItemList.get(k);
                            BigDecimal skuPrice = new BigDecimal(orderItem.getSku_price());
                            BigDecimal averyPricePercent = skuPrice.divide(amount,2,BigDecimal.ROUND_HALF_UP);
                            BigDecimal fatalPromotionPrice = new BigDecimal(20).multiply(averyPricePercent).setScale(2,BigDecimal.ROUND_HALF_UP);
                            orderItem.setPomotion_price(fatalPromotionPrice+"");
                            orderItem.setPayment_price(skuPrice.subtract(fatalPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                            lastPromotionPrice = lastPromotionPrice.add(fatalPromotionPrice);
                        }
                        GzgOrderItemList.get(k).setPomotion_price(new BigDecimal(20).subtract(lastPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                        GzgOrderItemList.get(k).setPayment_price(new BigDecimal(GzgOrderItemList.get(k).getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP)
                                .subtract(new BigDecimal(GzgOrderItemList.get(k).getPomotion_price())).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }
                    gzgOrder.setOrder_discount_money("20");
                    gzgOrder.setOrder_money(amount.subtract(new BigDecimal("20")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                }else {
                    if(GzgOrderItemList.size() == 1){
                        //单个商品
                        GzgOrderItemList.get(0).setPomotion_price("20");
                        GzgOrderItemList.get(0).setPayment_price(amount.subtract(new BigDecimal("20"))
                                .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }else{
                        //多个商品 按商品价格升序排序
                        BigDecimal totalDiscountMoney = amount.multiply(new BigDecimal("0.2")).setScale(2,BigDecimal.ROUND_HALF_UP);
                        GzgOrderItemList = GzgOrderItemList
                                .stream()
                                .sorted(Comparator.comparing(GzgOrderItem::getSku_price).reversed())
                                .collect(Collectors.toList());
                        int i = 0;
                        //计算分摊优惠
                        for(;i < GzgOrderItemList.size()-1;i++){
                            GzgOrderItem orderItem = GzgOrderItemList.get(i);
                            BigDecimal skuPrice = new BigDecimal(orderItem.getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP);
                            BigDecimal averyPricePercent = skuPrice.divide(amount,2,BigDecimal.ROUND_HALF_UP);
                            BigDecimal fatalPromotionPrice = totalDiscountMoney.multiply(averyPricePercent).setScale(2,BigDecimal.ROUND_HALF_UP);
                            orderItem.setPomotion_price(fatalPromotionPrice+"");
                            orderItem.setPayment_price(skuPrice.subtract(fatalPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                            lastPromotionPrice = lastPromotionPrice.add(fatalPromotionPrice);
                        }
                        GzgOrderItemList.get(i).setPomotion_price(totalDiscountMoney.subtract(lastPromotionPrice)
                                .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                        GzgOrderItemList.get(i).setPayment_price(new BigDecimal(GzgOrderItemList.get(i).getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP)
                                .subtract(new BigDecimal(GzgOrderItemList.get(i).getPomotion_price())).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }
                    discountMoney = (amount.multiply(new BigDecimal("0.2"))).setScale(2,BigDecimal.ROUND_HALF_UP);
                    gzgOrder.setOrder_discount_money(discountMoney+"");
                    // gzgOrder.setOrder_money((amount.multiply(new BigDecimal("0.8"))).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    gzgOrder.setOrder_money((amount.subtract(discountMoney).setScale(2,BigDecimal.ROUND_HALF_UP)+""));
                }
            }
            for (GzgOrderItem item:GzgOrderItemList) {
                String skuId = item.getSku_id();
                String cabinetNo = item.getCabinet_no();

                GoodsSkuInfo goodsSkuInfo = getGoodsSkuInfoById(skuId);

                if (StringUtils.isEmpty(goodsSkuInfo)) {
                    log.error("查询表goods_sku_info数据为空,异常skuId = {}",skuId);
                    return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
                }
                GzgSkuPicture gzgSkuPictureVo = getGzgSkuPictureVo(skuId);
                if (StringUtils.isEmpty(gzgSkuPictureVo)) {
                    log.error("查询表gzg_sku_pic数据为空,异常skuId = {}",skuId);
                    return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
                }
                JSONObject json = new JSONObject();
                json.put("order_id",gzgOrder.getId());
                json.put("sku_id",item.getSku_id());
                json.put("pomotion_price",item.getPomotion_price());
                json.put("payment_price",item.getPayment_price());
                callDataCenter(GZG_ORDER_ITEMS_UPDATE_BY_ORDER_ID,json);
                //生成返回给前段的实体类
                GzgOrderItemVo gzgOrderItemVo= new GzgOrderItemVo();
                gzgOrderItemVo.setPicture_url(gzgSkuPictureVo.getSmall_pic_url());
                gzgOrderItemVo.setSku_id(skuId);
                gzgOrderItemVo.setSku_name(goodsSkuInfo.getName());
                gzgOrderItemVo.setSku_num("1");
                gzgOrderItemVo.setSku_price(goodsSkuInfo.getMarket_price());
                gzgOrderItemVoList.add(gzgOrderItemVo);
                amount = amount.add(new BigDecimal(item.getSku_price()));//价格相加
                //生成返回给前段订单信息
            }
            GzgOrderVo gzgOrderVo = new GzgOrderVo();
            gzgOrderVo.setId(gzgOrder.getId());
            gzgOrderVo.setBuyer_id(gzgOrder.getBuyer_id());
            gzgOrderVo.setOrder_old_money(gzgOrder.getOrder_old_money());
            gzgOrderVo.setOrder_discount_money(gzgOrder.getOrder_discount_money());
            gzgOrderVo.setOrder_money(gzgOrder.getOrder_money());
            gzgOrderVo.setGzgOrderItemVoList(gzgOrderItemVoList);
            log.info("重新支付展示订单信息，返回给前端的信息：{}", gzgOrderVo);
            String xid = TxcContext.getCurrentXid();
            JSONObject updGzgOrder = new JSONObject();
            updGzgOrder.put("id", orderId);
            if(StringUtil.isNotEmpty(userId)){
                updGzgOrder.put("buyer_id", userId);
            }
            updGzgOrder.put("order_money",gzgOrder.getOrder_money() );
            updGzgOrder.put("order_old_money",gzgOrder.getOrder_old_money());
            updGzgOrder.put("order_discount_money",gzgOrder.getOrder_discount_money());
            callDataCenter(GZG_ORDER_UPDATE, updGzgOrder);
            if(gzgOrder.getImei().length() != GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
                gzgOrderScheduledService.gzgOrderScheduled(gzgOrder.getId());
            }
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, gzgOrderVo);
        }else{
            return  ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_SUCCESS,"此订单不可支付");
        }
    }

    /**
     * 事物处理写表操作：订单主表，订单明细表，以及未来更新的库存信息。
     *
     * @param gzgOrderVo
     * @param gzgOrderItems
     * @return
     * @throws Exception
     */
    @TxcTransaction(appName = "gzg")
    public void savaGzgOrderAndGzgOrderItem(GzgOrder gzgOrderVo, List<GzgOrderItem> gzgOrderItems) throws Exception {
        String xid = TxcContext.getCurrentXid();
        log.info("格子柜下单生成订单，订单明细表 订单实体={}",gzgOrderVo);
            if ("865800042529062".equals(gzgOrderVo.getImei()) ||
                    "RJ113211".equals(gzgOrderVo.getImei()) ||
                    "RJ356874".equals(gzgOrderVo.getImei()) ||
                    "100458206".equals(gzgOrderVo.getImei())) {

            gzgOrderVo.setOrder_money("0.01");
            }
        JSONObject jsonOrder = JSONObject.parseObject(JSONObject.toJSONString(gzgOrderVo));
        jsonOrder.put("xid", xid);

        for (int i = 0; i < gzgOrderItems.size(); i++) {
            GzgOrderItem gzgOrderItem = gzgOrderItems.get(i);
            JSONObject jsonOrderItem = JSONObject.parseObject(JSONObject.toJSONString(gzgOrderItem));
            jsonOrderItem.put("xid", xid);
            callDataCenter(GZG_ORDER_ITEMS_INSERT, jsonOrderItem);
        }
        callDataCenter(GZG_ORDER_INSERT, jsonOrder);
    }


    private GoodsSkuInfo getGoodsSkuInfoById(String skuId) {
        JSONObject jsonGoodSku = new JSONObject();
        jsonGoodSku.put("id", skuId);
        String result = callDataCenter(GOODS_SKU_QUERY, jsonGoodSku);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(JSONObject.parseObject(result), GoodsSkuInfo.class);
        return goodsSkuInfo;
    }


    private GzgSkuPicture getGzgSkuPictureVo(String skuId) {
        JSONObject jsonGzgSkuPic = new JSONObject();
        jsonGzgSkuPic.put("sku_id", skuId);
        String resultGzgSkuPic = callDataCenter(GZG_SKU_PICTURE_GET, jsonGzgSkuPic);
        GzgSkuPicture gzgSkuPictureVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgSkuPic), GzgSkuPicture.class);
        return gzgSkuPictureVo;
    }


    private List<GzgOrder> getGzgOrderList(String userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyer_id", userId);
        String result = callDataCenter(GZG_ORDER_GET_LIST, jsonObject);
        List<GzgOrder> gzgOrderList = JSONArray.parseArray(result, GzgOrder.class);
        return gzgOrderList;
    }

    private List<GzgOrderItem> getGzgOrderItemList(String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order_id", orderId);
        String result = callDataCenter(GZG_ORDER_ITEMS_GET_LIST, jsonObject);
        List<GzgOrderItem> gzgOrderItemList = JSONArray.parseArray(result, GzgOrderItem.class);
        return gzgOrderItemList;
    }

    /**
     * 更改用户订单信息至超时状态
     * @param orderId
     */
    private void updateGzgOrderState(String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", orderId);
        jsonObject.put("order_status", OrderStateEnum.OVERTIME.getKey());
        callDataCenter(GZG_ORDER_UPDATE, jsonObject);
    }

    /**
     * 比较传入的时间跟当前时间差多少秒
     * @param create_time
     * @return
     * @throws Exception
     */
    private int compareDate(String create_time) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = fmt.parse(create_time);
        long a = System.currentTimeMillis();
        long b = parse.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }


    /**
     *判断商品是否被锁定
     */
    public Boolean isLocking(List<WebGZG> skuIds,String imei){
        Boolean isLocking = false;
        for (int i = 0; i < skuIds.size(); i++) {
            String cabinetNo = skuIds.get(i).getCabinetNo();
            String s = redisService.get(RedisKey.REDIS_GZG_CODE_CABINET, imei + ":" + cabinetNo);
            if (StringUtil.isNotEmpty(s)) {
                log.info("产品已锁定，生成订单失败，锁定的key值：{}", "gzg:" + imei + ":" + cabinetNo);
                isLocking = true;
                break;
            }
        }
        if(!isLocking){
            for (int i = 0; i < skuIds.size(); i++) {
                String cabinetNo = skuIds.get(i).getCabinetNo();
                String s = redisService.get(RedisKey.REDIS_GZG_CODE_CABINET, imei + ":" + cabinetNo);
                if (s == null || "".equals(s)) {
                redisService.set(RedisKey.REDIS_GZG_CODE_CABINET, imei + ":" + cabinetNo, "1", OrderOverTimeEnum.GOODS_LOCK.getTime(), TimeUnit.SECONDS);
                log.info("生成订单放入redis，key值：{}", "gzg:" + imei + ":" + cabinetNo);
                }
            }
        }
        return isLocking;
    }

    /**
     * 订单生成的公用方法
     * @param webOrderVo
     * @return
     * @throws Exception
     */
    public ResponseJson orderCreatePublic(WebOrderVo webOrderVo) throws Exception {
        String imei = webOrderVo.getImei();
        List<WebGZG> skuIds = webOrderVo.getSkuIds();

        String userId = webOrderVo.getUserId();
        log.info("生成订单:userId={}",webOrderVo.getUserId());
        String usedCouponByUserIdAndCouponId = "";
        if (StringUtil.isNotEmpty(userId)) {
            //查看新人八折券是否使用
            usedCouponByUserIdAndCouponId = gzgCouponCodeService.isUsedCouponByUserIdAndCouponId(userId, GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT.getId());
        }


        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_GZG_ORDER);
        GzgOrder gzgOrder = new GzgOrder();
        gzgOrder.setId(id);
        gzgOrder.setBuyer_id(userId);
        gzgOrder.setOrder_status(OrderStateEnum.TO_BE_PAY.getKey());
        String nowTimeStr = DateUtil.getNowTimeStr();
        gzgOrder.setCreate_time(nowTimeStr);
        gzgOrder.setImei(imei);

        List<GzgOrderItem> gzgOrderItemList = new ArrayList<GzgOrderItem>();
        List<GzgOrderItemVo> gzgOrderItemVoList = new ArrayList<GzgOrderItemVo>();
        BigDecimal amount = new BigDecimal("0.00");

        for (int i = 0; i < skuIds.size(); i++) {
            String skuId = skuIds.get(i).getSkuId();
            String cabinetNo = skuIds.get(i).getCabinetNo();

            GoodsSkuInfo goodsSkuInfo = getGoodsSkuInfoById(skuId);

            if (StringUtils.isEmpty(goodsSkuInfo)) {
                log.error("查询表goods_sku_info数据为空,异常skuId = {}",skuId);
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
            }

            GzgSkuPicture gzgSkuPictureVo = getGzgSkuPictureVo(skuId);
          /*  if (StringUtils.isEmpty(gzgSkuPictureVo)) {
                log.error("查询表gzg_sku_pic数据为空,异常skuId = {}",skuId);
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
            }*/
            GzgOrderItem gzgOrderItem = new GzgOrderItem();
            gzgOrderItem.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_GZG_ORDER_ITEMS));
            gzgOrderItem.setOrder_id(id);
            gzgOrderItem.setSku_id(skuId);
            gzgOrderItem.setSku_name(goodsSkuInfo.getName());
            gzgOrderItem.setSku_price(goodsSkuInfo.getMarket_price());
            gzgOrderItem.setSku_num("1");//数量，目前格子柜的存货都是1
//            gzgOrderItemVo.setPaymentPrice(Double.parseDouble(goodsSkuInfo.getMarketPrice()));//实付金额
            gzgOrderItem.setCreate_time(nowTimeStr);
            gzgOrderItem.setImei(imei);
            gzgOrderItem.setCabinet_no(cabinetNo);
            gzgOrderItemList.add(gzgOrderItem);

            //生成返回给前段的实体类
            GzgOrderItemVo gzgOrderItemVo= new GzgOrderItemVo();
            gzgOrderItemVo.setPicture_url(StringUtils.isEmpty(gzgSkuPictureVo)?"":gzgSkuPictureVo.getSmall_pic_url());
            gzgOrderItemVo.setSku_id(skuId);
            gzgOrderItemVo.setSku_name(goodsSkuInfo.getName());
            gzgOrderItemVo.setSku_num("1");
            gzgOrderItemVo.setSku_price(goodsSkuInfo.getMarket_price());
            gzgOrderItemVoList.add(gzgOrderItemVo);
            amount = amount.add(new BigDecimal(gzgOrderItem.getSku_price()));//价格相加
        }
        //总的优惠
        BigDecimal discountMoney = BigDecimal.ZERO;
        //商品明细中除单价最大商品之外的其他商品分摊优惠总额
        BigDecimal lastPromotionPrice =BigDecimal.ZERO;
        log.info("是否使用优惠券:usedCouponByUserIdAndCouponId:{}", usedCouponByUserIdAndCouponId);
        if (StringUtil.isEmpty(usedCouponByUserIdAndCouponId) || "[]".equals(usedCouponByUserIdAndCouponId)){
            log.info("优惠卷信息为空");
            gzgOrder.setOrder_discount_money("0");
            gzgOrder.setOrder_money((amount)+"");
        }else {
            log.info("优惠卷信息不为空");
            if(amount.multiply(new BigDecimal("0.2")).compareTo(new BigDecimal("20"))==1){
                if(gzgOrderItemList.size() == 1){
                    //单个商品
                    gzgOrderItemList.get(0).setPomotion_price("20");
                    gzgOrderItemList.get(0).setPayment_price(amount.subtract(new BigDecimal("20"))
                            .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                }else{
                    //多个商品 按商品价格升序排序
                    gzgOrderItemList = gzgOrderItemList
                            .stream()
                            .sorted(Comparator.comparing(GzgOrderItem::getSku_price).reversed())
                            .collect(Collectors.toList());
                    int k = 0;
                    for(;k < gzgOrderItemList.size()-1;k++){
                        GzgOrderItem orderItem = gzgOrderItemList.get(k);
                        BigDecimal skuPrice = new BigDecimal(orderItem.getSku_price());
                        BigDecimal averyPricePercent = skuPrice.divide(amount,2,BigDecimal.ROUND_HALF_UP);
                        BigDecimal fatalPromotionPrice = new BigDecimal(20).multiply(averyPricePercent).setScale(2,BigDecimal.ROUND_HALF_UP);
                        orderItem.setPomotion_price(fatalPromotionPrice+"");
                        orderItem.setPayment_price(skuPrice.subtract(fatalPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                        lastPromotionPrice = lastPromotionPrice.add(fatalPromotionPrice);
                    }
                    gzgOrderItemList.get(k).setPomotion_price(new BigDecimal(20).subtract(lastPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    gzgOrderItemList.get(k).setPayment_price(new BigDecimal(gzgOrderItemList.get(k).getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP)
                            .subtract(new BigDecimal(gzgOrderItemList.get(k).getPomotion_price())).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                }


                gzgOrder.setOrder_discount_money("20");
                gzgOrder.setOrder_money(amount.subtract(new BigDecimal("20")).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
            }else {
                if(gzgOrderItemList.size() == 1){
                    //单个商品
                    gzgOrderItemList.get(0).setPomotion_price("20");
                    gzgOrderItemList.get(0).setPayment_price(amount.subtract(new BigDecimal("20"))
                            .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                }else{
                    //多个商品 按商品价格升序排序
                    BigDecimal totalDiscountMoney = amount.multiply(new BigDecimal("0.2")).setScale(2,BigDecimal.ROUND_HALF_UP);
                    gzgOrderItemList = gzgOrderItemList
                            .stream()
                            .sorted(Comparator.comparing(GzgOrderItem::getSku_price).reversed())
                            .collect(Collectors.toList());
                    int i = 0;
                    //计算分摊优惠
                    for(;i < gzgOrderItemList.size()-1;i++){
                        GzgOrderItem orderItem = gzgOrderItemList.get(i);
                        BigDecimal skuPrice = new BigDecimal(orderItem.getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        BigDecimal averyPricePercent = skuPrice.divide(amount,2,BigDecimal.ROUND_HALF_UP);
                        BigDecimal fatalPromotionPrice = totalDiscountMoney.multiply(averyPricePercent).setScale(2,BigDecimal.ROUND_HALF_UP);
                        orderItem.setPomotion_price(fatalPromotionPrice+"");
                        orderItem.setPayment_price(skuPrice.subtract(fatalPromotionPrice).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                        lastPromotionPrice = lastPromotionPrice.add(fatalPromotionPrice);
                    }
                    gzgOrderItemList.get(i).setPomotion_price(totalDiscountMoney.subtract(lastPromotionPrice)
                            .setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    gzgOrderItemList.get(i).setPayment_price(new BigDecimal(gzgOrderItemList.get(i).getSku_price()).setScale(2,BigDecimal.ROUND_HALF_UP)
                            .subtract(new BigDecimal(gzgOrderItemList.get(i).getPomotion_price())).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                }

                discountMoney = (amount.multiply(new BigDecimal("0.2"))).setScale(2,BigDecimal.ROUND_HALF_UP);
                gzgOrder.setOrder_discount_money(discountMoney+"");
                // gzgOrder.setOrder_money((amount.multiply(new BigDecimal("0.8"))).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                gzgOrder.setOrder_money((amount.subtract(discountMoney).setScale(2,BigDecimal.ROUND_HALF_UP)+""));
            }
        }

        gzgOrder.setOrder_old_money((amount)+"0");
        JSONObject jsonGzgInfo = new JSONObject();
        jsonGzgInfo.put("imei", imei);
        String resultGzgInfo = callDataCenter(CABINET_INFO_GET_BY_IMEI, jsonGzgInfo);
        if(imei.length()== GzgCodeLengthEnum.GZG_YILIAN.getCode()){
            gzgOrder.setOrder_source(GzgOrderSourceEnum.YILIAN.getKey());
        }else if(imei.length() == GzgCodeLengthEnum.GZG_RUJIN.getCode()){
            gzgOrder.setOrder_source(GzgOrderSourceEnum.RUJIN.getKey());
        } else{
            gzgOrder.setOrder_source(GzgOrderSourceEnum.SHOWCASE.getKey());
        }
        log.info("imei ：***************************************{}",imei);
        log.info("订单来源:{}",gzgOrder.getOrder_source());

        if (StringUtil.isNotEmpty(resultGzgInfo)) {
            CabinetInfo gzgInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgInfo), CabinetInfo.class);
            gzgOrder.setHotel_id(gzgInfoVo.getStore_id());
        }else{
            gzgOrder.setHotel_id("0");
            log.info("查询表gzg_info格子柜{}的配货方案id时，查询结果为空", imei);
        }
        GzgOrderVo gzgOrderVo = new GzgOrderVo();
        gzgOrder.setOrder_old_money(amount+"");
        savaGzgOrderAndGzgOrderItem(gzgOrder, gzgOrderItemList);
        //生成返回给前段订单信息

        gzgOrderVo.setId(gzgOrder.getId());
        gzgOrderVo.setBuyer_id(gzgOrder.getBuyer_id());
        gzgOrderVo.setOrder_old_money(gzgOrder.getOrder_old_money());
        gzgOrderVo.setOrder_discount_money(gzgOrder.getOrder_discount_money());
        gzgOrderVo.setOrder_money(gzgOrder.getOrder_money());
        gzgOrderVo.setGzgOrderItemVoList(gzgOrderItemVoList);

        log.info("生成订单，返回给前端的信息：{}", gzgOrderVo);
        if(gzgOrder.getImei().length() !=  GzgCodeLengthEnum.GZG_SHOWCASE.getCode()){
            gzgOrderScheduledService.gzgOrderScheduled(gzgOrder.getId());
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, gzgOrderVo);
    }


    @Override
    public ResponseJson orderDetail(String orderId) throws  Exception{
        log.info("GzgOrderServiceImpl.orderDetail，订单编号:{}", orderId);
        JSONObject result = new JSONObject();

        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", orderId);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        log.info("调用数据中心，查询订单item{}", resultGzgOrderItem);
        List<GzgOrderItem> GzgOrderItemList = JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);

        JSONObject jsonGzgOrder = new JSONObject();
        jsonGzgOrder.put("id", orderId);
        String resultGzgOrders = callDataCenter(GZG_ORDER_GET, jsonGzgOrder);
        log.info("调用数据中心，查询订单主体信息{}", resultGzgOrders);
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
        log.info("商品信息{}", goodsInfosResp);
        JSONObject goodsInfo = new JSONObject();
        goodsInfo.put("code", GzgOrderItemList.get(0).getImei());
        goodsInfo.put("goodsInfos", goodsInfosResp);
        result.put("goodsInfo", goodsInfo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PayInfoResp payInfoResp = null;
        log.info("判断订单状态{}", gzgOrder.getOrder_status());
        if (OrderStateEnum.TO_BE_PAY.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setCloseTime(gzgOrder.getCreate_time());
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setDisCountMoney(Double.parseDouble(gzgOrder.getOrder_discount_money()));
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESULT_CODE_GZG_ORDER_NOTPAY, "未支付", result);
        } else if (OrderStateEnum.COMPLETED.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            //封装优惠金额
            payInfoResp.setDisCountMoney(Double.parseDouble(gzgOrder.getOrder_discount_money()));
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESULT_CODE_GZG_ORDER_COMPLETE, "支付成功", result);
        } else if (OrderStateEnum.OVERTIME.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            payInfoResp.setCloseTime(DateUtil.getNowTimeStr());
            result.put("payInfo", payInfoResp);
            log.info("支付成功柜子打开成功，返回的数据{}", result);
            return ResponseUtil.getResponseJson(ResponseCode.RESULT_CODE_GZG_ORDER_TIMEOUT, "支付超时 订单关闭", result);
        }/*else if (OrderStateEnum.OPEN_FAIL.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESULT_CODE_GZG_ORDER_OPEN_FAIL, "舱门s失败", result);
        }*/else if (OrderStateEnum.PAY_FAIL.getKey().equals(gzgOrder.getOrder_status())) {
            payInfoResp = new PayInfoResp();
            payInfoResp.setAmount(Double.parseDouble(amount));
            payInfoResp.setOrderNo(Long.parseLong(orderId));
            payInfoResp.setPayTime(payTime);
            payInfoResp.setCloseTime(gzgOrder.getCreate_time());
            result.put("payInfo", payInfoResp);
            return ResponseUtil.getResponseJson(ResponseCode.RESULT_CODE_GZG_ORDER_PAY_FAIL, "支付失败", result);
        }else {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "无效订单");
        }
    }

}
