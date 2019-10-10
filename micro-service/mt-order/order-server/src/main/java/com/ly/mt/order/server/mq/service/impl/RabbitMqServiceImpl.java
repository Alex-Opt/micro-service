package com.ly.mt.order.server.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.template.style.Style0;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.border.CallBackParamInfo;
import com.ly.mt.core.base.entity.border.OrderBattleExpresses;
import com.ly.mt.core.base.entity.border.OrderBattleShop;
import com.ly.mt.core.base.entity.border.OrdersBattleInfo;
import com.ly.mt.core.base.entity.shop.ShopAddress;
import com.ly.mt.core.base.entity.shop.ShopInfo;
import com.ly.mt.core.base.entity.trade.TradeOrder;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import com.ly.mt.core.base.util.*;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.mq.mapper.RabbitMqServiceMapper;
import com.ly.mt.order.server.mq.service.RabbitMqService;
import com.ly.mt.order.server.trade.component.TradeOrderServiceAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

import static com.ly.mt.core.base.dict.BattleOrderStatus.BATTLE_NOT_GRAB;
import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.HummingBirdDeliveryStatus.*;
import static com.ly.mt.core.base.dict.OpenType.OPEN_APP;
import static com.ly.mt.core.base.dict.OrderBattleStatus.*;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_RECEIPT;
import static com.ly.mt.core.base.dict.OrderType.ORDER_TYPE_ORDINARY_ORDER;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.method.TaskMethodEnum.GY_SEND_ORDER;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.AL_SMS_SEND;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.FN_ORDER_CREATE;
import static com.ly.mt.core.redis.RedisKey.ORDER_BATTLE_REDID;

/**
 * mq消费业务处理层
 *
 * @author zhanglifeng
 * @date 2019-06-25
 */
@Service
public class RabbitMqServiceImpl extends BaseServiceImpl implements RabbitMqService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMqServiceImpl.class);
    @Resource
    private RabbitMqServiceMapper rabbitMqServiceMapper;
    @Resource
    private TradeOrderServiceAsync tradeOrderServiceAsync;
    /**
     * 1.生成发货单信息表 orders_battle_info，并将这条发货单添加到redis的抢单列表中。｛"order_battle_id_发货单id",order_battle_info｝
     * 2.生成符合发货范围的小B列表 order_battle_shop
     * 3.根据发货单中生成的id捞取符合条件的小B，推送信息给小B。这个要用到mq
     * 4.推送成功后要更新一小时达的订单状态为推送成功。也即是新生成的订单只推送一次。
     * 自此，抢单需要的基本信息都已具备。等待小B查看抢单信息，进行抢单。
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> handlerHomeBMq(String message) {
        try {
            LOGGER.info("---------------------------------------------一小时达message:{}", message);
            JSONObject jsonObject = JSONObject.parseObject(message);
            Long orderNo = jsonObject.getLong("out_trade_no");
            List<String> orderIds = new ArrayList<>(1);
            //根据订单编号查询一小时达订单。
            List<TradeOrder> tradeOrders = rabbitMqServiceMapper.getTradeOrderByOrderNo(orderNo, Long.parseLong(DISTRIBUTE_TYPE_ONE_HOUR.getId()));
            if (tradeOrders == null || tradeOrders.size() < 1) {
                return null;
            }
            List<OrdersBattleInfo> douDiList = new ArrayList<>();
            for (TradeOrder tradeOrder : tradeOrders) {
                String orderId = tradeOrder.getId();
                //根据订单生成发货单
                OrdersBattleInfo ordersBattleInfo = getOrderBattleInfoFromTradeOrder(tradeOrder);
                rabbitMqServiceMapper.insertOrderBattle(ordersBattleInfo);
                //将发货单添加到redis的抢单列表中，主要用id,发货单别的信息用不到。
                redisServer.setEntity(ORDER_BATTLE_REDID, ordersBattleInfo.getId(), ordersBattleInfo.getId());
                //针对每一个订单，捞取符合订单发货范围的小B列表
                UserAddress address = rabbitMqServiceMapper.getBuyerAddressById(Long.parseLong(tradeOrder.getAddressId()));
                String lat = address.getLat();
                String lon = address.getLon();
                if (StringUtil.isEmpty(lat) || StringUtil.isEmpty(lon)) {
                    LOGGER.info("订单-orderId:" + orderId + "对应的地址信息的经纬度信息不全！");
                    throw new Exception("订单-orderId:" + orderId + "对应的地址信息的经纬度信息不全！");
                }
                //目前业务定捞取五公里内的小B查询的范围
                double[] doubles = LatLonUtil.getAround(Double.parseDouble(lat), Double.parseDouble(lon), 5000);
                List<ShopAddress> shopAddresses = rabbitMqServiceMapper.getShopAddressByLonAndLatRange(String.valueOf(doubles[0]), String.valueOf(doubles[1]), String.valueOf(doubles[2]), String.valueOf(doubles[3]));
                if (shopAddresses != null && shopAddresses.size() > 0) {
                    //捞取到附近的小B，插入到 订单抢单关联表（符合资格的商家列表） order_battle_shop
                    List<OrderBattleShop> orderBattleShopList = getOderBattleShopBeanList(shopAddresses, tradeOrder.getId(), ordersBattleInfo.getId());
                    if (orderBattleShopList != null && orderBattleShopList.size() > 0) {
                        //要先剔除掉黑名单上的小B-shopId为依据
                        List<String> blackList = rabbitMqServiceMapper.getBlackList();
                        if (blackList != null && blackList.size() > 0) {
                            Iterator<OrderBattleShop> iterator = orderBattleShopList.iterator();
                            while (iterator.hasNext()) {
                                OrderBattleShop next = iterator.next();
                                if (blackList.contains(next.getShopId())) {
                                    iterator.remove();
                                }
                            }
                        }
                        if(orderBattleShopList != null && orderBattleShopList.size()>0){
                            rabbitMqServiceMapper.batchInsertOrderBattleShop(orderBattleShopList);
                            // 推送信息给小B，这块调用信息推送接口即可
                            LOGGER.info("推送信息给小B-------start");
                            String orderMoney = tradeOrder.getOrderMoney();
                            BigDecimal bonus = new BigDecimal(orderMoney).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP);
                            List<String> clientIdList = rabbitMqServiceMapper.getClientIdByUserId(orderBattleShopList);
                            String url = "https://mall.motivape.cn";
                            String content = "系统又有一笔新订单，赶紧去抢单吧~";
                            String title = "MOTI到家";
                            String text = "有新的MOTI到家订单，奖励" + bonus + "元，快来抢吧";
                            String logo = "https://mall.motivape.cn";
                            String logoUrl = "https://moti-dev.oss-cn-beijing.aliyuncs.com/image/user/goods/2/single/112492576846/112492581718.jpg";
                            Style0 style0 = pushMessageServer.getStyle0(title, text, logo, logoUrl);
                            pushMessageServer.pushList(clientIdList, OPEN_APP.getId(), url, content, style0);
                            LOGGER.info("推送信息给小B-------end");
                        }else{
                            //说明附近范围内没有符合条件的小B，进入兜底的集合。
                            douDiList.add(ordersBattleInfo);
                        }
                    }
                } else {
                    LOGGER.info("-------------------------------------订单-orderId:" + orderId + "未找到小B，会被放到兜底流程中！");
                    //说明附近范围内没有小B，进入兜底的集合。
                    douDiList.add(ordersBattleInfo);
                }
                orderIds.add(orderId);
            }
            List<Map<String, String>> maps = sendHourOrdersToGY(douDiList);
            return maps;
        } catch (Exception e) {
            LOGGER.error("一小时达message处理出错:", e);
            return null;
        }
    }

    /**
     * 将一小时达的订单变成普通订单发送到管易。
     *
     * @param douDiList
     * @throws Exception
     */
    private List<Map<String, String>> sendHourOrdersToGY(List<OrdersBattleInfo> douDiList) throws Exception {
        LOGGER.info("----------------------------------------------------五公里内没有小B 抢单");
        List<Map<String, String>> shopListMap = null;
        //  针对一个订单捞取同城最近的兜底商家，如果最近商家范围在五公里内，则推送到蜂鸟。五公里外则走管易的顺丰。
        if (douDiList != null && douDiList.size() > 0) {
            shopListMap = new ArrayList<>();
            //1.针对每一个订单都要捞取对应的五公里以内的兜底商家
            LOGGER.info("针对每一个订单都要捞取对应的五公里以内的兜底商家--------------------------------------start");
            for (OrdersBattleInfo battleOrder : douDiList) {
                long orderId = Long.parseLong(battleOrder.getOrderId());
                //用户收货地址
                UserAddress userAddress = rabbitMqServiceMapper.getAddressByOrderId(orderId);
                String lat = userAddress.getLat();
                String lon = userAddress.getLon();
                //根据收货地址寻找附近的兜底商家 思路：可以限定个最大的半径。为5公里，通过一公里循环递增半径的方式搜寻兜底商家。如果循环过程中找到，则停止循环。如果最大半径内都没有。则进入推送管易的流程。
                ShopInfo shop = null;
                for (int i = 1; i < 6; i++) {
                    //根据半径计算经纬度范围
                    double[] range = LatLonUtil.getAround(Double.parseDouble(lat), Double.parseDouble(lon), i * 1000);
                    List<ShopInfo> shopInfoList = rabbitMqServiceMapper.getShopInfoByLonAndLatRange(String.valueOf(range[0]), String.valueOf(range[1]), String.valueOf(range[2]), String.valueOf(range[3]));
                    if (shopInfoList != null && shopInfoList.size() > 0) {
                        shop = shopInfoList.get(0);
                        break;
                    }
                }
                LOGGER.info("针对每一个订单都要捞取对应的五公里以内的兜底商家----end");
                String userId = null;
                String shopId = null;
                if (shop != null) {
                    Map shopMap = new HashMap();
                    shopMap.put("orderId", battleOrder.getOrderId());
                    String mobile = shop.getMobile();
                    shopMap.put("mobile", mobile);
                    shopListMap.add(shopMap);
                    if (StringUtil.isEmpty(mobile)) {
                        LOGGER.info("--------------------------未找到兜底商家的手机号，无法发送短信！------------------------------");
                    }
                    LOGGER.info("五公里内找到兜底商家，单子等待推送蜂鸟");
                    //说明找到兜底商家了，更新抢单状态，同时将兜底商家信息更新进抢单表和订单表。
                    userId = shop.getUserId();
                    shopId = shop.getId();
                    rabbitMqServiceMapper.updateShopIdIntoTradeOrders(Long.parseLong(shopId), Long.parseLong(userId), orderId);
                    battleOrder.setModifyTime(DateUtil.getNowTimeStr());
                    battleOrder.setStatus(ORDER_BATTLE_STATUS_ALREADY_SEND.getId());
                    rabbitMqServiceMapper.updateBattleOrderDeliveryStatus(battleOrder);
                } else {
                    LOGGER.info("---------------------------------------------------------五公里内未找到兜底商家,更新订单为暂未同意转城次日达订单");
                    //说明五公里范围内没有兜底商家,五公里内未找到兜底商家，则找同城中最近的兜底商家，更新shopId到订单表,抢单表,推送管易。
                    //更新订单的买家/商家信息,订单同意状态为待确认。
                    TradeOrder tradeOrder = new TradeOrder();
                    tradeOrder.setId(orderId + "");
                    int i = rabbitMqServiceMapper.updateTradeOrdersDefaultShopInfoById(tradeOrder);
                    LOGGER.info("tradeOrder:" + JSONObject.toJSONString(tradeOrder) + "-,---------------更新订单同意状态为待确认:" + i);
                    //目前先做强转为次日达
                    JSONObject orderJson = new JSONObject();
                    orderJson.put("id", orderId);
                    //直接调用同意转为次日达，推送管易，给客户发短信的操作
                    agreeNextDayOrder(JSONObject.toJSONString(orderJson));
                }
                LOGGER.info("----------------------------------------------------更新抢单信息为已被抢，同时移除redis中的单子：" + battleOrder.getId());
                battleOrder.setUserId(userId);
                battleOrder.setShopId(shopId);
                battleOrder.setGrabedAt(DateUtil.getNowTimeStr());
                battleOrder.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
                battleOrder.setModifyTime(DateUtil.getNowTimeStr());
                rabbitMqServiceMapper.updateBattleInfoById(battleOrder);
                //移除redis中的对应单子
                redisServer.del(ORDER_BATTLE_REDID, battleOrder.getId());
            }
        }
        return shopListMap;
    }

    /**
     * 短信通知兜底人员来了一小时达订单并推送订单到蜂鸟
     *
     * @param list
     */
    @Override
    public void sendMsgAndFn(List<Map<String, String>> list) {
        if (list != null && list.size() > 0) {
            for (Map<String, String> map : list) {
                String mobile = map.get("mobile");
                String orderId = map.get("orderId");
                long id = Long.parseLong(orderId);
                UserAddress address = rabbitMqServiceMapper.getAddressByOrderId(id);
                List<String> mobileList = new ArrayList<>(3);
                mobileList.add(mobile);
                //通知北京兜底店铺的强逻辑
                mobileList.add("18001215068");
                //北京要通知这两个人
                if (address.getProvinceCode().equals("11")) {
                    mobileList.add("18833886686");
                    mobileList.add("18511929685");
                }
                String itemNum = rabbitMqServiceMapper.getTradeOrderItemsByOrderId(id);
                String orderNo = rabbitMqServiceMapper.getOrderNoByOrderId(id);
                Set set =new HashSet(mobileList);
                mobileList.clear();
                mobileList.addAll(set);
                for (String mob : mobileList) {
                    if (StringUtil.isNotEmpty(mobile)) {
                        LOGGER.info("短信通知兜底商家--start,兜底商家手机号：" + mob);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("phone", mob);
                        jsonObject.put("signName", "MOTI");
                        jsonObject.put("templateCode", "SMS_171745946");
                        Map templateMap = new HashMap(3);
                        templateMap.put("distType", "一小时达");
                        templateMap.put("productId", orderNo);
                        templateMap.put("itemNum", itemNum);
                        templateMap.put("name", address.getReceiveName());
                        templateMap.put("phone", address.getReceivePhone());
                        templateMap.put("city", address.getCityName());
                        String templateParam = JSONObject.toJSONString(templateMap);
                        jsonObject.put("templateParam", templateParam);
                        JSONObject smsResponseJson = callFNService(AL_SMS_SEND, jsonObject);
                        LOGGER.info("----------给兜底人员发短信的结果--------------------------smsResponseJson：" + smsResponseJson);
                    }
                }
                LOGGER.info("短信通知兜底商家--end");
                //由于没有小B,所以不用更新小B的对应此单的抢单状态。直接将订单推送给蜂鸟系统
                //调用蜂鸟的接口推送订单
                JSONObject fnJsonObject = new JSONObject();
                fnJsonObject.put("order_id", orderId);
                JSONObject orderCreateJson = callFNService(FN_ORDER_CREATE, fnJsonObject);
                LOGGER.info("调用蜂鸟的接口推送订单返回信息：" + orderCreateJson);
            }
        }
    }

    /**
     * 获取 订单抢单关联表（符合资格的商家）
     *
     * @param shopAddresses
     * @param orderId
     * @param battleId
     * @return
     */
    private List<OrderBattleShop> getOderBattleShopBeanList(List<ShopAddress> shopAddresses, String orderId, String battleId) {
        List<OrderBattleShop> list = new ArrayList<>(shopAddresses.size());
        OrderBattleShop orderBattleShop;
        for (ShopAddress shopAddress : shopAddresses) {
            orderBattleShop = new OrderBattleShop();
            orderBattleShop.setId(SnowflakeUtil.getPrimaryKey(ORDERS_BATTLE_SHOP));
            String userId = shopAddress.getUserId();
            orderBattleShop.setUserId(userId);
            String shopId = shopAddress.getShopId();
            orderBattleShop.setShopId(shopId);
            orderBattleShop.setStatus(BATTLE_NOT_GRAB.getId());
            orderBattleShop.setCreateTime(DateUtil.getNowTimeStr());
            orderBattleShop.setOrderId(orderId);
            orderBattleShop.setOrdersBattleId(battleId);
            list.add(orderBattleShop);
        }
        return list;
    }

    /**
     * 将订单表的信息放到发货单中
     *
     * @param tradeOrder
     * @return
     */
    private OrdersBattleInfo getOrderBattleInfoFromTradeOrder(TradeOrder tradeOrder) throws Exception {
        OrdersBattleInfo ordersBattleInfo = new OrdersBattleInfo();
        String id = SnowflakeUtil.getPrimaryKey(ORDERS_BATTLE_INFO);
        ordersBattleInfo.setId(id);
        ordersBattleInfo.setOrderId(tradeOrder.getId());
        ordersBattleInfo.setBuyerId(tradeOrder.getBuyerId());
        ordersBattleInfo.setType(ORDER_TYPE_ORDINARY_ORDER.getId());
        ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_NOT_BATTLE.getId());
        String afterMinutesDateFromPointTime = DateUtil.getAfterMinutesDateFromPointTime(60, tradeOrder.getCreateTime());
        ordersBattleInfo.setEstimatedSuccessedAt(afterMinutesDateFromPointTime);
        ordersBattleInfo.setCreateTime(DateUtil.getNowTimeStr());
        return ordersBattleInfo;
    }

    private OrderBattleExpresses getOrderBattleExpress(OrdersBattleInfo ordersBattleInfo) {
        OrderBattleExpresses orderBattleExpresses = new OrderBattleExpresses();
        orderBattleExpresses.setId(SnowflakeUtil.getPrimaryKey(ORDER_BATTLE_EXPRESSES));
        orderBattleExpresses.setOrderId(ordersBattleInfo.getOrderId());
        orderBattleExpresses.setOrdersBattleId(ordersBattleInfo.getId());
        orderBattleExpresses.setShopId(ordersBattleInfo.getShopId());
        orderBattleExpresses.setUserId(ordersBattleInfo.getUserId());
        return orderBattleExpresses;
    }

    /**
     * 蜂鸟推送订单回调接口。用于知道订单的物流更新，物流信息变化。
     * 咱们自己系统的单子状态值：9-系统拒单/配送异常 11-蜂鸟系统已接单 13-已分配骑手 15-骑手已到店 17-配送中 19-已送达 21-商品签收完成 23-抢单已取消 25-商品被拒签 27-商品申请退货中 29-商品退货退款完成
     * 蜂鸟系统的单子状态值：1-系统已接单 ，20-已分配骑手，80-骑手已到店，2-配送中，3-已送达，5-系统拒单/配送异常
     * 1.更新发货单-抢单状态
     * 2.记录快递信息到order_battle_expresses
     * 2.如果订单已经送达。则更新订单trade_orders表的订单状态
     *
     * @throws Exception
     */
    @Override
    public void handlerFnCallBack(String message) {
        try {
            if (StringUtil.isEmpty(message)) {
                return;
            }
            JSONObject jsonObject = JSONObject.parseObject(message);
            String data = URLDecoder.decode(jsonObject.getString("data"), "UTF-8");
            LOGGER.info("蜂鸟回调接口，接受发送的mq，更新订单数据message:" + data);
            CallBackParamInfo callBackParamInfo = JSONObject.parseObject(data, CallBackParamInfo.class);
            String orderNo = callBackParamInfo.getOrderNo();
            String orderId = rabbitMqServiceMapper.getOrderIdByOrderNo(Long.parseLong(orderNo));
            callBackParamInfo.setOrderId(orderId);
            OrdersBattleInfo ordersBattleInfo = rabbitMqServiceMapper.getBattleInfoByOrderId(orderId);
            String clientId = rabbitMqServiceMapper.getClientIdById(Long.parseLong(ordersBattleInfo.getUserId()));
            String url = "https://mall.motivape.cn";
            String content = "您的出货单有物流信息更新~";
            String title = "MOTI到家";
            String text = "moti";
            String logo = "https://mall.motivape.cn";
            String logoUrl = "https://moti-dev.oss-cn-beijing.aliyuncs.com/image/user/goods/2/single/112492576846/112492581718.jpg";
            OrderBattleExpresses orderBattleExpresses = getOrderBattleExpress(ordersBattleInfo);
            orderBattleExpresses.setData(JSONObject.toJSONString(callBackParamInfo));
            Integer orderStatus = callBackParamInfo.getOrderStatus();
            String orderStatusName = "";
            if (Integer.parseInt(DELIVERY_STATUS_SYSTEM_RECEIVED_ORDER.getId()) == orderStatus) {
                //系统已接单
                orderStatusName="系统已接单";
                text = "蜂鸟系统已经接单了，订单号：" + callBackParamInfo.getOrderId();
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_ACCEPT_ORDER.getId());
                ordersBattleInfo.setTakedAt(DateUtil.getNowTimeStr());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_ACCEPT_ORDER.getId());
            } else if (Integer.parseInt(DELIVERY_STATUS_ALLOCATED_RIDER.getId()) == orderStatus) {
                //已分配到骑手，则要把骑手信息保存/更新进发货单中
                orderStatusName="订单已经分配到骑手";
                text = "订单号：" + callBackParamInfo.getOrderId() + "的订单已经分配到骑手。";
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_RIDER_FETCH.getId());
                ordersBattleInfo.setDeliverymanName(callBackParamInfo.getDeliverymanName());
                ordersBattleInfo.setDeliverymanPhone(callBackParamInfo.getDeliverymanPhone());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_RIDER_FETCH.getId());
            } else if (Integer.parseInt(DELIVERY_STATUS_RIDER_ARRIVED_STORE.getId()) == orderStatus) {
                //骑手已到店
                orderStatusName="骑手已到店";
                text = "骑手" + callBackParamInfo.getDeliverymanName() + "已到店。";
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_RIDER_ARRIVAL.getId());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_RIDER_ARRIVAL.getId());
            } else if (Integer.parseInt(DELIVERY_STATUS_IN_DISTRIBUTION.getId()) == orderStatus) {
                //配送中
                orderStatusName="配送员已成功取到商品";
                text = "您的订单号为" + callBackParamInfo.getOrderId() + "的订单，配送员已成功取到商品";
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_TRANSPORTING.getId());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_TRANSPORTING.getId());
                //订单一旦配送，表示订单从待配送变成待收货
                rabbitMqServiceMapper.updateOrderStatusById(callBackParamInfo.getOrderId(), null, Integer.parseInt(ORDER_STATUS_PENDING_RECEIPT.getId()));
            } else if (Integer.parseInt(DELIVERY_STATUS_ARRIVED.getId()) == orderStatus) {
                //已送达，送达不代表订单结束。要客户确认收货才更新订单状态为完成。
                orderStatusName="订单已送达";
                text = "您的订单号为" + callBackParamInfo.getOrderId() + "的订单已送达";
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_SERVICED.getId());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_SERVICED.getId());
            } else if (Integer.parseInt(DELIVERY_STATUS_ABNORMAL_DELIVERY.getId()) == orderStatus) {
                //系统拒单/配送异常
                orderStatusName="订单配送异常";
                text = "您的订单号为" + callBackParamInfo.getOrderId() + "的订单被系统拒单了。";
                //系统拒单后的处理逻辑是更新状态为已被抢单。并将单子推送到管易，快递方式为次日达。
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
                orderBattleExpresses.setState(ORDER_BATTLE_STATUS_REFUSED.getId());
                LOGGER.info("订单编号为：" + callBackParamInfo.getOrderId() + "的订单系统拒单/配送异常,errorCode:" + callBackParamInfo.getErrorCode() + "description:" + callBackParamInfo.getDescription());
                JSONObject orderJson = new JSONObject();
                orderJson.put("id", callBackParamInfo.getOrderId());
                agreeNextDayOrder(JSONObject.toJSONString(orderJson));
            } else {
                LOGGER.info("===========蜂鸟返回的订单状态未定义=================参数信息：" + callBackParamInfo.toString());
            }
            ordersBattleInfo.setModifyTime(DateUtil.getNowTimeStr());
            rabbitMqServiceMapper.updateBattleOrderDeliveryStatus(ordersBattleInfo);
            //---------------------------增加一小时达物流信息更新的微信小程序通知---------start
            tradeOrderServiceAsync.asyncNotifyUserOneHourDeliveryInfo(ordersBattleInfo,orderStatusName);
            if (Integer.parseInt(DELIVERY_STATUS_ARRIVED.getId()) == orderStatus) {
                //如果送达，系统自动确认收货。并发送微信服务通知信息给用户
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_FINISHED.getId());
                rabbitMqServiceMapper.updateBattleOrderDeliveryStatus(ordersBattleInfo);
                TradeOrder tradeOrder = rabbitMqServiceMapper.getTradeOrderById(Long.parseLong(orderId));
                List<TradeOrderItem> tradeOrderItems = rabbitMqServiceMapper.getTradeOrderItemByOrderId(Long.parseLong(orderId));
                String spuName = tradeOrderItems.get(0).getSpuName();
                //--------------------------增加确认收货信息更新的微信学校程序通知-------------start
                tradeOrderServiceAsync.asyncNotifyUserConfirmReceipt(spuName,tradeOrder);
                //--------------------------增加确认收货信息更新的微信学校程序通知-------------end
            }
            //--------------------------增加一小时达物流信息更新的微信小程序通知---------end
            orderBattleExpresses.setCreateTime(DateUtil.getNowTimeStr());
            rabbitMqServiceMapper.insertOrderBattleExpress(orderBattleExpresses);
            Style0 style0 = pushMessageServer.getStyle0(title, text, logo, logoUrl);
            pushMessageServer.pushToSingle(clientId, OPEN_APP.getId(), url, content, style0);
        } catch (Exception e) {
            LOGGER.error("蜂鸟回调接口，接受发送的mq，更新订单数据出错:", e);
        }
    }

    @Override
    public JSONObject agreeNextDayOrder(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long id = jsonObject.getLong("id");
        LOGGER.info("同意更改订单id为" + id + "的配送方式为次日达");
        rabbitMqServiceMapper.updateTradeOrdersDeliveryTypeById(id);
        User user = rabbitMqServiceMapper.getBuyerInfoByOrderId(id);
        if (user == null) {
            LOGGER.info("获取用户信息为空，无法发送短信！");
            return JsonUtil.getErrorJson(ResponseCode.RESPONSE_CODE_ERROR);
        }
        JSONObject smsJson = new JSONObject();
        smsJson.put("phone", user.getMobile());
        smsJson.put("signName", "雷炎科技");
        smsJson.put("templateCode", "SMS_171185443");
        Map templateMap = new HashMap(1);
        templateMap.put("orderId", jsonObject.getString("id"));
        String templateParam = JSONObject.toJSONString(templateMap);
        smsJson.put("templateParam", templateParam);
        LOGGER.info("-----------------------------达转为次日达发短信的入参：" + smsJson);
        JSONObject smsResponseJson = callFNService(AL_SMS_SEND, smsJson);
        LOGGER.info("------------------------------发短信的返回结果：" + smsResponseJson);
        JSONObject deliverJson = callMtTask(GY_SEND_ORDER, jsonObject);
        LOGGER.info("-------------------------------推送到管易结束，返回结果：{}",deliverJson);
        return JsonUtil.getSuccessJson();
    }

    @Override
    public void updateTradeOrderStatus(String message) {
        LOGGER.info("支付成功的更新订单的回调入参：" + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        Long orderNo = Long.valueOf(jsonObject.getString("out_trade_no"));
        String paymentType = jsonObject.getString("paymentType");
        rabbitMqServiceMapper.updateTradeOrderByOrderNo(orderNo, Integer.parseInt(ORDER_STATUS_PENDING_DELIVERY.getId()), paymentType);
        LOGGER.info("支付更新回调处理结束。");
    }

}
