package com.ly.mt.task.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.LatLonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.coupon.dao.CouponCodeDao;
import com.ly.mt.core.data.order.dao.OrderBattleCheckLogsDao;
import com.ly.mt.core.data.order.dao.OrderBattleShopDao;
import com.ly.mt.core.data.order.dao.OrdersBattleInfoDao;
import com.ly.mt.core.data.order.entity.OrderBattleCheckLogs;
import com.ly.mt.core.data.order.entity.OrderBattleShop;
import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.dao.ShopStocksDao;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.shop.entity.ShopStocks;
import com.ly.mt.core.data.trade.dao.TradeOrderCouponDao;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.trade.dao.TradeOrdersDao;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.data.trade.entity.TradeOrders;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.core.data.user.entity.UserAddress;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.gy.timing.service.OrderTimingService;
import com.ly.mt.task.order.service.TradeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.ly.mt.core.base.dict.CouponType.COUPON_TYPE_COUPON;
import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_CITY_EXPRESS;
import static com.ly.mt.core.base.dict.IsAgree.IS_AGREE_WAIT;
import static com.ly.mt.core.base.dict.IsAgree.IS_AGREE_YES;
import static com.ly.mt.core.base.dict.IsRefund.IS_REFUND_NO;
import static com.ly.mt.core.base.dict.OrderBattleStatus.*;
import static com.ly.mt.core.base.dict.OrderCancelCode.ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK;
import static com.ly.mt.core.base.dict.OrderCancelReasonCode.SHOP_CANCEL;
import static com.ly.mt.core.base.dict.OrderStatus.*;
import static com.ly.mt.core.base.dict.PushStatus.PUSH_STATUS_NOT_PUSH;
import static com.ly.mt.core.base.dict.PushStatus.PUSH_STATUS_PUSH_FAIL;
import static com.ly.mt.core.base.dict.ShopStatus.SHOP_STATUS_YES;
import static com.ly.mt.core.base.dict.ShopType.SHOP_TYPE_BUSINESS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.dict.WxAppletTemplateSendType.TEMPLATE_SEND_TYPE_WAITING_PAY_RESULT;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.*;
import static com.ly.mt.core.redis.RedisKey.ORDER_BATTLE_REDID;

@Service
public class TradeOrderServiceImpl extends BaseServiceImpl implements TradeOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderServiceImpl.class);
    @Resource
    private TradeOrdersDao tradeOrdersDao;
    @Resource
    private TradeOrderItemsDao tradeOrderItemsDao;
    @Resource
    private TradeOrderCouponDao tradeOrderCouponDao;
    @Resource
    private CouponCodeDao couponCodeDao;
    @Resource
    private OrdersBattleInfoDao ordersBattleInfoDao;
    @Resource
    private OrderBattleShopDao orderBattleShopDao;
    @Resource
    private OrderBattleCheckLogsDao orderBattleCheckLogsDao;
    @Resource
    private UserAddressDao userAddressDao;
    @Resource
    private ShopInfoDao shopInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ShopStocksDao shopStocksDao;

    @Resource
    private OrderTimingService orderTimingService;

    @Override
    public void updCompleteStatus() {
        tradeOrdersDao.updateFinishStatus();
    }

    @Override
    public void updCancelStatus() {
        TradeOrders orders = new TradeOrders();
        orders.setOrderStatus(ORDER_STATUS_PENDING_PAYMENT.getId());
        orders.setOrderYn(VALID_STATUS_YES.getId());
        String autoCancelTime = " <= '" + DateUtil.getNowTimeStr() + "'";
        orders.setAutoCancelTime(autoCancelTime);
        List<TradeOrders> list = tradeOrdersDao.listTradeOrdersFromMysql(orders);
        if (list != null && list.size() > 0) {
            LOGGER.info("本次执行已经到自动取消时间的订单数量：" + list.size());
            //第一步执行更新订单的状态为取消
            tradeOrdersDao.updateCancelStatus(list);
            LOGGER.info("==============执行更新订单的状态为取消=========end========");
            //第二步查询用户使用的优惠券信息
            List<Map<String, String>> userCouponIdList = new ArrayList<>();
            for (TradeOrders order : list) {
                List<TradeOrderCoupon> coupons = tradeOrderCouponDao.listTradeOrderCouponByOrderIdAndCouponTypeFromRedis(order.getId(), COUPON_TYPE_COUPON.getId());
                if (coupons.size() <= 0) {
                    continue;
                }
                Map<String, String> map = new HashMap<>(1);
                //目前的业务为一个订单最多可以使用一张优惠券。所以根据订单id查询的优惠券集合最多size=1;
                TradeOrderCoupon tradeOrderCoupon = coupons.get(0);
                map.put("couponId", tradeOrderCoupon.getCouponActivityId());
                map.put("userId", order.getBuyerId());
                userCouponIdList.add(map);
            }
            //第三步 将使用的优惠券回退给用户。
            if (userCouponIdList.size() > 0) {
                LOGGER.info("=================将要退还给用户的优惠券信息：" + JSONObject.toJSONString(userCouponIdList));
                couponCodeDao.batchUpdateCouponCodeUnused(userCouponIdList);
            }
            LOGGER.info("==============将使用的优惠券回退给用户========end");
        } else {
            LOGGER.info("本次执行没有捞取到已经到自动取消时间的订单！");
        }
    }


    /**
     * 一小时达订单进入抢单表。作为抢单的单源，如果过了15分钟状态任然是1-未抢单,或者单子被蜂鸟系统拒单。则要进入兜底流程。
     * 1.获取订单地址最近区域的兜底商家，进行推送。
     * 2.更新进入兜底商家的抢单状态为2-已被抢单
     *
     * @throws Exception
     */
    @Override
    public void processingTimeOutOrders() throws Exception {
        TradeOrders tradeOrders = new TradeOrders();
        tradeOrders.setOrderYn(VALID_STATUS_YES.getId());
        tradeOrders.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
        tradeOrders.setIsRefund(IS_REFUND_NO.getId());
        String pushStatus = PUSH_STATUS_NOT_PUSH.getId() + "," + PUSH_STATUS_PUSH_FAIL.getId();
        tradeOrders.setPushStatus(pushStatus);
        List<TradeOrders> list = tradeOrdersDao.listTradeOrdersFromMysql(tradeOrders);
        if (null == list || list.size() <= 0) {
            return;
        }
        List<OrdersBattleInfo> ordersBattleInfos = new ArrayList<>();
        String nowTimeStr = DateUtil.getNowTimeStr();
        String time = DateUtil.getBeforeMinutesDateFromPointTime(15, nowTimeStr);
        for (TradeOrders order : list) {
            OrdersBattleInfo battle = ordersBattleInfoDao.getOrdersBattleInfoByOrderIdFromRedis(order.getId());
            String status = battle.getStatus();
            if (!ORDER_BATTLE_STATUS_NOT_BATTLE.getId().equals(status) && !ORDER_BATTLE_STATUS_REFUSED.getId().equals(status)) {
                continue;
            }
            String createTime = battle.getCreateTime();
            if (1 == DateUtil.compareDateTime(createTime, time)) {
                continue;
            }
            ordersBattleInfos.add(battle);
        }
        if (ordersBattleInfos.size() <= 0) {
            return;
        }
        LOGGER.info("============超时的一小时达订单捞取=========================orders.size()" + JSONObject.toJSONString(ordersBattleInfos));
        for (OrdersBattleInfo battleOrder : ordersBattleInfos) {
            String orderId = battleOrder.getOrderId();
            //用户收货地址
            TradeOrders order = tradeOrdersDao.getTradeOrdersByIdFromRedis(orderId);
            UserAddress address = userAddressDao.getUserAddressByIdFromRedis(order.getAddressId());
            LOGGER.info("--------------------------------获取用户地址：" + JSONObject.toJSONString(address));
            String lat = address.getLat();
            String lon = address.getLon();
            //思路：可以限定个最大的半径。为5公里，通过一公里循环递增半径的方式搜寻兜底商家。如果循环过程中找到，则停止循环。如果最大半径内都没有。则进入推送管易的流程。
            ShopInfo shop = null;
            for (int i = 1; i < 6; i++) {
                //根据半径计算经纬度范围
                double[] range = LatLonUtil.getAround(Double.parseDouble(lat), Double.parseDouble(lon), i * 1000);
                ShopInfo shopInfo = new ShopInfo();
                shopInfo.setStatus(SHOP_STATUS_YES.getId());
                shopInfo.setShopType(SHOP_TYPE_BUSINESS.getId());
                shopInfo.setShopLon(getBetweenSql(String.valueOf(range[0]), String.valueOf(range[1])));
                shopInfo.setShopLat(getBetweenSql(String.valueOf(range[2]), String.valueOf(range[3])));
                List<ShopInfo> shopInfoList = shopInfoDao.listShopInfoFromMysql(shopInfo);
                if (shopInfoList != null && shopInfoList.size() > 0) {
                    shop = shopInfoList.get(0);
                    break;
                }
            }
            String userId = null;
            String shopId = null;
            if (shop != null) {
                LOGGER.info("五公里内找到兜底商家，单子推送蜂鸟");
                //说明找到兜底商家了，更新抢单状态，同时将兜底商家信息更新进抢单表和订单表。
                userId = shop.getUserId();
                shopId = shop.getId();
                TradeOrders orders = new TradeOrders();
                orders.setId(orderId);
                orders.setSellerId(userId);
                orders.setShopId(shopId);
                orders.setOrderStatus(ORDER_STATUS_PENDING_RECEIPT.getId());
                tradeOrdersDao.updateTradeOrders(orders);
                //由于没有小B,所以不用更新小B的对应此单的抢单状态。直接将订单推送给蜂鸟系统
                //调用蜂鸟的接口推送订单
                JSONObject fnJsonObject = new JSONObject();
                fnJsonObject.put("order_id", orderId);
                battleOrder.setModifyTime(DateUtil.getNowTimeStr());
                battleOrder.setStatus(ORDER_BATTLE_STATUS_ALREADY_SEND.getId());
                ordersBattleInfoDao.updateOrdersBattleInfo(battleOrder);
                JSONObject orderCreateJson = callFNService(FN_ORDER_CREATE, fnJsonObject);
                LOGGER.info("调用蜂鸟的接口推送订单返回信息：" + orderCreateJson);
            } else {
                LOGGER.info("五公里内未找到兜底商家，则找同城中最近的兜底商家，更新shopId到订单表,抢单表,推送管易");
                //说明五公里范围内没有兜底商家,五公里内未找到兜底商家，则找同城中最近的兜底商家，更新shopId到订单表,抢单表,推送管易。
                ShopInfo si = new ShopInfo();
                si.setShopType(SHOP_TYPE_BUSINESS.getId());
                si.setShopProvinceCode(address.getProvinceCode());
                si.setShopCityCode(address.getCityCode());
                List<ShopInfo> shopDefaultList = shopInfoDao.listShopInfoFromMysql(si);
                //利用冒泡排序计算比较出最近的兜底商家
                double distanceMin = 1000000000.00;
                Map<Double, ShopInfo> map = new HashMap();
                if (shopDefaultList != null && shopDefaultList.size() > 0) {
                    for (ShopInfo shopInfo : shopDefaultList) {
                        if (shopInfo == null) {
                            LOGGER.info("orderId=" + orderId + "的订单对应的收货地址尚未配置城市对应的兜底商家。");
                            throw new Exception("未配置城市对应的兜底商家");
                        }
                        String shopLat = shopInfo.getShopLat();
                        String shopLon = shopInfo.getShopLon();
                        double distanceMax = LatLonUtil.getDistance(Double.valueOf(lon), Double.valueOf(lat), Double.valueOf(shopLon), Double.valueOf(shopLat));
                        map.put(distanceMax, shopInfo);
                        if (distanceMax < distanceMin) {
                            distanceMin = distanceMax;
                        }
                    }
                } else {
                    LOGGER.info("orderId=" + orderId + "的订单对应的收货地址尚未配置城市对应的兜底商家。");
                    throw new Exception("未配置城市对应的兜底商家");
                }
                //经过这次循环后可以得到最小距离和对应的兜底商家。
                ShopInfo shopInfo = map.get(distanceMin);
                shopId = shopInfo.getId();
                userId = shopInfo.getUserId();
                String gyShopCode = shopInfo.getGyShopCode();
                String gyWarehouseCode = shopInfo.getGyWarehouseCode();
                TradeOrders tradeOrder = new TradeOrders();
                tradeOrder.setId(orderId);
                tradeOrder.setGyWarehouseCode(gyWarehouseCode);
                tradeOrder.setGyShopCode(gyShopCode);
                tradeOrder.setShopId(shopId);
                tradeOrder.setSellerId(userId);
                tradeOrder.setIsAgree(IS_AGREE_WAIT.getId());
                //更新订单的买家/商家信息
                int j = tradeOrdersDao.updateTradeOrders(tradeOrders);
                LOGGER.info("tradeOrder:" + JSONObject.toJSONString(tradeOrder) + "-,---------------更新订单的买家/商家信息,订单同意状态为待确认:" + j);
                agreeNextDayOrder(orderId, battleOrder.getId(), shopId, battleOrder.getBuyerId());
            }
            LOGGER.info("更新抢单信息为已被抢，同时移除redis中的单子：" + battleOrder.getId());
            battleOrder.setUserId(userId);
            battleOrder.setShopId(shopId);
            battleOrder.setGrabedAt(DateUtil.getNowTimeStr());
            battleOrder.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
            battleOrder.setModifyTime(DateUtil.getNowTimeStr());
            ordersBattleInfoDao.updateOrdersBattleInfo(battleOrder);
            //移除redis中的对应单子
            redisService.del(ORDER_BATTLE_REDID, battleOrder.getId());

        }
    }


    private void agreeNextDayOrder(String id, String battleId, String shopId, String buyerId) throws Exception {
        LOGGER.info("同意更改订单id为" + id + "的配送方式为次日达");
        TradeOrders tradeOrders = new TradeOrders();
        tradeOrders.setIsAgree(IS_AGREE_YES.getId());
        tradeOrders.setDistributionId(DISTRIBUTE_TYPE_CITY_EXPRESS.getId());
        tradeOrders.setAgreeTime(DateUtil.getNowTimeStr());
        tradeOrders.setId(id);
        tradeOrdersDao.updateTradeOrders(tradeOrders);
        User user = userDao.getUserByIdFromRedis(battleId);
        if (user == null) {
            LOGGER.info("订单id:" + id + " 对应的买家用户信息不存在，请确认数据问题");
            OrdersBattleInfo battleOrder = new OrdersBattleInfo();
            battleOrder.setId(battleId);
            battleOrder.setUserId(null);
            battleOrder.setShopId(shopId);
            battleOrder.setGrabedAt(DateUtil.getNowTimeStr());
            battleOrder.setStatus(ORDER_BATTLE_STATUS_CANCELED.getId());
            battleOrder.setModifyTime(DateUtil.getNowTimeStr());
            ordersBattleInfoDao.updateOrdersBattleInfo(battleOrder);
            //移除redis中的对应单子
            redisService.del(ORDER_BATTLE_REDID, battleOrder.getId());
            LOGGER.info("订单id:" + id + " 对应的买家用户信息不存在，问题数据已更改状态为已取消！");
            return;
        }
        JSONObject smsJson = new JSONObject();
        smsJson.put("phone", user.getMobile());
        smsJson.put("signName", "雷炎科技");
        smsJson.put("templateCode", " SMS_171185443");
        Map templateMap = new HashMap(1);
        templateMap.put("orderId", id);
        String templateParam = JSONObject.toJSONString(templateMap);
        smsJson.put("templateParam", templateParam);
        sendMsg(smsJson);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id + "");
        JSONObject deliverJson = orderTimingService.sendTrade(JSONObject.toJSONString(jsonObject));
        LOGGER.info("deliverJson:" + deliverJson);
    }


    /**
     * 小B抢单后，要对商品进行校验。如果超过五分钟单子状态没有变成校验成功。则系统自动取消小B的抢单。处理逻辑和释放一样。
     * 1.更新order_battle_shop表中的中小B对应这个单子状态为可抢，同时更新该小B的抢单状态为4-取消、释放状态
     * 2.查询该小B已经校验过的商品及数量 order_battle_check_logs，扫描过得肯定在小B的库存中有代发货的数量delivery_nums。要给他加上 shop_stocks
     * 3.更新该发货单的状态为可抢orders_battle_info，并把它放到redis中。
     *
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void productVerificationExpired() throws Exception {
        //1.搂取商品校验超时且状态为未配送的单子
        OrdersBattleInfo battleInfo = new OrdersBattleInfo();
        battleInfo.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
        battleInfo.setCheckedAt(" < '" + DateUtil.getNowTimeStr() + "'");
        List<OrdersBattleInfo> battleOrders = ordersBattleInfoDao.listOrdersBattleInfoFromMysql(battleInfo);
        if (battleOrders != null && battleOrders.size() > 0) {
            for (OrdersBattleInfo ordersBattleInfo : battleOrders) {
                String userId = ordersBattleInfo.getUserId();
                String shopId = ordersBattleInfo.getShopId();
                String id = ordersBattleInfo.getId();
                String status = ordersBattleInfo.getStatus();
                //2.更新order_battle_shop表中的中小B对应这个单子状态为可抢，同时更新该小B的抢单状态为4-取消、释放状态
                OrderBattleShop orderBattleShop = new OrderBattleShop();
                orderBattleShop.setOrdersBattleId(id);
                orderBattleShop.setStatus(ORDER_BATTLE_STATUS_NOT_BATTLE.getId());
                orderBattleShopDao.updateOrderBattleShop(orderBattleShop);
                orderBattleShop.setStatus(ORDER_BATTLE_GIVE_UP_GRAB.getId());
                orderBattleShop.setUserId(userId);
                orderBattleShop.setShopId(shopId);
                orderBattleShopDao.updateOrderBattleShop(orderBattleShop);
                //小B在点击商品校验完成按钮前，即没有发送订单到蜂鸟，只考虑代发货的数量即可，此时尚未扣除库存数量和增加销售量
                if (Integer.parseInt(status) < Integer.parseInt(ORDER_BATTLE_STATUS_ALREADY_SEND.getId())) {
                    //3.查询该小B已经校验过的商品及数量 order_battle_check_logs，扫描过得肯定在小B的库存中有代发货的数量delivery_nums。要给他加上 shop_stocks
                    recoverDeliveryNum(id, shopId);
                } else {
                    //3.说明已经发送订单到蜂鸟了，这时待发货数量恢复。库存减少，销售量则增加。要恢复这两个字段的数据
                    recoverStocks(ordersBattleInfo.getOrderId());
                    //根据订单id查询订单编号
                    TradeOrders tradeOrders = tradeOrdersDao.getTradeOrdersByIdFromRedis(ordersBattleInfo.getOrderId());
                    String orderNo = tradeOrders.getOrderNo();
                    JSONObject fnJsonObject = new JSONObject();
                    fnJsonObject.put("partner_order_code", orderNo);
                    fnJsonObject.put("order_cancel_code", ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK.getId());
                    fnJsonObject.put("order_cancel_description", ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK.getName());
                    fnJsonObject.put("order_cancel_reason_code", SHOP_CANCEL.getId());
                    fnJsonObject.put("order_cancel_time", DateUtil.getNowTimeStr());
                    JSONObject orderCancelJson = callFNService(FN_ORDER_CANCEL, fnJsonObject);
                    LOGGER.info("调用蜂鸟的同步取消订单接口返回信息：" + orderCancelJson);
                }
                //4.更新该发货单的状态为可抢orders_battle_info,并删除掉小B的信息，并把它放到redis中。
                ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_NOT_BATTLE.getId());
                ordersBattleInfo.setUserId("0");
                ordersBattleInfo.setId(id);
                ordersBattleInfo.setShopId("0");
                ordersBattleInfo.setModifyTime(DateUtil.getNowTimeStr());
                ordersBattleInfo.setCheckedAt("");
                ordersBattleInfo.setGrabedAt("");
                ordersBattleInfoDao.updateOrdersBattleInfo(ordersBattleInfo);
                //将发货单添加到redis的抢单列表中，主要用id,发货单别的信息用不到。
                redisService.setEntity(ORDER_BATTLE_REDID, ordersBattleInfo.getId(), ordersBattleInfo.getId());
            }
        }

    }


    /**
     * 恢复增加的代发货数量
     */
    private void recoverDeliveryNum(String id, String shopId) {
        OrderBattleCheckLogs logs = new OrderBattleCheckLogs();
        logs.setShopId(shopId);
        logs.setOrdersBattleId(id);
        List<OrderBattleCheckLogs> shopCheckLogs = orderBattleCheckLogsDao.listOrderBattleCheckLogsFromMysql(logs);
        if (shopCheckLogs != null && shopCheckLogs.size() > 0) {
            //由于校验商品用的是唯一码code，所以会出现即使一个skuId，也会出现多条的情况。所以这里要先进行统计，去重复。在更新到库存表的待发货数值中。
            Map<String, Integer> map = new HashMap(2);
            Integer skuNum = 0;
            for (OrderBattleCheckLogs orderBattleCheckLogs : shopCheckLogs) {
                skuNum = skuNum + (map.get(orderBattleCheckLogs.getSkuId()) == null ? 0 : map.get(orderBattleCheckLogs.getSkuId()));
                map.put(orderBattleCheckLogs.getSkuId(), skuNum);
            }
            Set<Map.Entry<String, Integer>> entries = map.entrySet();
            List<ShopStocks> list = new ArrayList<>();
            for (Map.Entry entry : entries) {
                ShopStocks shopStocks = new ShopStocks();
                shopStocks.setSkuId(entry.getKey().toString());
                shopStocks.setShopId(shopId);
                shopStocks.setDeliveryNums(entry.getValue().toString());
                shopStocks.setModifyTime(DateUtil.getNowTimeStr());
                list.add(shopStocks);
            }
            if (list.size() > 0) {
                shopStocksDao.updateDeliveryNums(list);
            }
        }
    }

    /**
     * 恢复 被扣减的库存,增加的销量
     *
     * @param orderId
     */
    private void recoverStocks(String orderId) {
        TradeOrders tradeOrders = tradeOrdersDao.getTradeOrdersByIdFromRedis(orderId);
        String shopId = tradeOrders.getShopId();
        if (StringUtil.isEmpty(shopId)) {
            LOGGER.error("TradeOrderServiceImpl.recoverStocks shopId must not be null");
            return;
        }
        List<ShopStocks> list = new ArrayList<>();
        List<TradeOrderItems> tradeOrderItems = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(orderId);
        for (TradeOrderItems tradeOrderItem : tradeOrderItems) {
            String skuNum = tradeOrderItem.getSkuNum();
            if (StringUtil.isEmpty(skuNum)) {
                LOGGER.error("TradeOrderServiceImpl.recoverStocks skuNum must not be null");
                continue;
            }
            String skuId = tradeOrderItem.getSkuId();
            if (StringUtil.isEmpty(skuId)) {
                LOGGER.error("TradeOrderServiceImpl.recoverStocks skuId must not be null");
                continue;
            }
            ShopStocks shopStocks = new ShopStocks();
            shopStocks.setShopId(shopId);
            shopStocks.setNums(skuNum);
            shopStocks.setSkuId(skuId);
            shopStocks.setModifyTime(DateUtil.getNowTimeStr());
            list.add(shopStocks);
        }
        if (list.size() > 0) {
            shopStocksDao.updateNums(list);
        }
    }

    @Override
    public void sendMsg(JSONObject msgJson) throws Exception {
        LOGGER.info("达转为次日达发短信的入参：" + msgJson);
        JSONObject smsResponseJson = callFNService(AL_SMS_SEND, msgJson);
        LOGGER.info("发短信的返回结果：" + smsResponseJson);
    }

    @Override
    public void notPayUserSendWxMsg() throws Exception {
        //第一步。捞取距离自动结束时间剩余14-16分钟的订单。可以由当前时间计算出14-16分钟后的时间，订单自动取消时间在这个时间区间的都是符合的
        String nowTimeStr = DateUtil.getNowTimeStr();
        String smallTime = DateUtil.getAfterMinutesDateFromPointTime(14, nowTimeStr);
        String bigTime = DateUtil.getAfterMinutesDateFromPointTime(15, nowTimeStr);
        List<TradeOrders> willCancelOrder = tradeOrdersDao.getWillCancelOrderByTime(smallTime, bigTime);
        LOGGER.info("到家C-----------本次任务捞取到的符合条件的订单数量----------------:{}", willCancelOrder==null?"0":willCancelOrder.size());
        if (willCancelOrder == null || willCancelOrder.size() <= 0) {
            return;
        }
        for (TradeOrders tradeOrders : willCancelOrder) {
            String buyerId = tradeOrders.getBuyerId();
            User user = new User();
            user.setId(buyerId);
            User userInfo = userDao.getUserFromMysql(user);
            String wxOpenId = userInfo.getWxOpenId();
            if (StringUtil.isNotEmpty(wxOpenId)) {
                String orderMoney = tradeOrders.getOrderMoney();
                String orderNo = tradeOrders.getOrderNo();
                String status = "待支付";
                List<TradeOrderItems> tradeOrderItems = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(tradeOrders.getId());
                String spuName = tradeOrderItems.get(0).getSpuName();
                JSONObject dataJson = new JSONObject(4);
                JSONObject keyword1 = new JSONObject();
                keyword1.put("value", spuName);
                JSONObject keyword2 = new JSONObject();
                keyword2.put("value", orderMoney);
                JSONObject keyword3 = new JSONObject();
                keyword3.put("value", orderNo);
                JSONObject keyword4 = new JSONObject();
                keyword4.put("value", status);
                dataJson.put("keyword1", keyword1);
                dataJson.put("keyword2", keyword2);
                dataJson.put("keyword3", keyword3);
                dataJson.put("keyword4", keyword4);
                sendWxMsg(wxOpenId, TEMPLATE_SEND_TYPE_WAITING_PAY_RESULT.getId(), dataJson);
            }
        }
    }

    /**
     * 调用三方发送信息到微信小程序的公共方法
     *
     * @param wxOpenId
     * @param templateId
     * @param dataJson
     */
    private void sendWxMsg(String wxOpenId, String templateId, JSONObject dataJson) {
        JSONObject templateMap = new JSONObject();
        templateMap.put("openId", wxOpenId);
        templateMap.put("templateId", templateId);
        templateMap.put("data", dataJson);
        try {
            LOGGER.info("--------------------------------------调用三方发送微信小程序信息提醒------start------------入参：{}", JSONObject.toJSONString(templateMap));
            callFNService(WX_TEMPLATE_MESSAGE_SEND, templateMap);
            LOGGER.info("--------------------------------------调用三方发送微信小程序信息提醒-----success-------发送完毕！");
        } catch (Exception e) {
            LOGGER.info("-------------------------------------发送信息到用户的微信小程序异常：{}", e);
        }
    }

}