package com.ly.mt.order.server.border.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.border.*;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.shop.ShopAddress;
import com.ly.mt.core.base.entity.shop.ShopProfitLogs;
import com.ly.mt.core.base.entity.shop.ShopProfitsForBattleOrder;
import com.ly.mt.core.base.entity.shop.ShopStocks;
import com.ly.mt.core.base.entity.trade.TradeOrder;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.entity.trade.TradeOrderItemVo;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.border.mapper.BOrderBattleServiceMapper;
import com.ly.mt.order.server.border.service.BOrderBattleService;
import com.ly.mt.order.server.trade.component.TradeOrderServiceAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.BillingStatus.BILLING_STATUS_WAITING_BILL;
import static com.ly.mt.core.base.dict.GoodsCheck.CHECK_GOODS_SECCESS;
import static com.ly.mt.core.base.dict.GrabStatus.GRAB_STATUS_FAILURE;
import static com.ly.mt.core.base.dict.GrabStatus.GRAB_STATUS_SUCCESS;
import static com.ly.mt.core.base.dict.OrderBattleStatus.*;
import static com.ly.mt.core.base.dict.OrderCancelCode.ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK;
import static com.ly.mt.core.base.dict.OrderCancelReasonCode.SHOP_CANCEL;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_COMPLETE;
import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SKU;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.dict.ProfitType.PROFIT_TYPE_GRAB_AMOUNT;
import static com.ly.mt.core.base.dict.UserType.USER_TYPE_ORDINARY_USER;
import static com.ly.mt.core.base.entity.ResponseCode.CHECK_GOODS_NO_GOODS_CODE_DATA;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.*;
import static com.ly.mt.core.redis.RedisKey.ORDER_BATTLE_CHECK_GOODS;
import static com.ly.mt.core.redis.RedisKey.ORDER_BATTLE_REDID;

/**
 * 抢单模块-服务层
 *
 * @author zhanglifeng
 * @date 2019-06-13
 */
@Service("bOrderBattleServiceImpl")
public class BOrderBattleServiceImpl extends BaseServiceImpl implements BOrderBattleService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BOrderBattleServiceImpl.class);

    @Resource
    private BOrderBattleServiceMapper bOrderBattleServiceMapper;

    @Resource
    private TradeOrderServiceAsync tradeOrderServiceAsync;

    @Override
    public JSONObject queryOrderBattleList(String json) throws Exception {
        List<OrderBattleInfoModel> list = new ArrayList();
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        //首先查询出小B可以看到的发货单有那些。
        List<OrderBattleShop> orderBattleShopList = bOrderBattleServiceMapper.getOrdeBattleShopByUserIdAndShopId(Long.parseLong(userId), Long.parseLong(shopId));
        if (orderBattleShopList != null && orderBattleShopList.size() > 0) {
            OrderBattleInfoModel orderBattleInfoModel = null;
            List<TradeOrderItemVo> itemList;
            for (OrderBattleShop orderBattleShop : orderBattleShopList) {
                itemList = new ArrayList<>();
                String orderId = orderBattleShop.getOrderId();
                String ordersBattleId = orderBattleShop.getOrdersBattleId();
                orderBattleInfoModel = new OrderBattleInfoModel();
                //设置抢单的状态
                orderBattleInfoModel.setStatus(orderBattleShop.getStatus());
                orderBattleInfoModel.setOrderId(orderId);
                orderBattleInfoModel.setOrderBattleId(ordersBattleId);
                //查询订单信息
                TradeOrder tradeOrders = bOrderBattleServiceMapper.getTradeOrdersById(Long.parseLong(orderId));
                orderBattleInfoModel.setAddressId(tradeOrders.getAddressId());
                orderBattleInfoModel.setOrderMoney(tradeOrders.getOrderMoney());
                //考虑到订单有可能客户订单生成后过了几分钟或者几个小时才付款，这时如果还以订单创建时间，则捞取的就是过期单子，所以应该以生成发货单的时间加上15分钟为deadLine
                String createTime = orderBattleShop.getCreateTime();
                //供页面倒计时用的时间，考虑job任务的时间，多加一分钟
                String deadLine = DateUtil.getAfterMinutesDateFromPointTime(16, createTime);
                orderBattleInfoModel.setDeadLine(deadLine);
                orderBattleInfoModel.setBonus(new BigDecimal(tradeOrders.getOrderMoney()).multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                //查询订单明细表
                List<TradeOrderItem> tradeOrderItemList = bOrderBattleServiceMapper.getTradeOrderItemByOrderId(Long.parseLong(orderId));
                int num = 0;
                TradeOrderItemVo tradeOrderItemVo = null;
                for (TradeOrderItem tradeOrderItem : tradeOrderItemList) {
                    tradeOrderItemVo = new TradeOrderItemVo();
                    BeanUtils.copyProperties(tradeOrderItem, tradeOrderItemVo);
                    String skuId = tradeOrderItem.getSkuId();
                    //商品图片连接
                    String pictureUrl = bOrderBattleServiceMapper.getPictureUrlBySkuId(Long.parseLong(skuId));
                    if (StringUtil.isEmpty(pictureUrl)) {
                        pictureUrl = PICTURE_PLACEHOLDER_SKU.getId();
                    }
                    tradeOrderItemVo.setPictureUrl(pictureUrl);
                    itemList.add(tradeOrderItemVo);
                    num = num + Integer.parseInt(tradeOrderItem.getSkuNum());
                }
                orderBattleInfoModel.setItemList(itemList);
                orderBattleInfoModel.setSkuTotalNum(String.valueOf(num));
                LOGGER.info("========================经纬度查询===============start===============================");
                //计算距离，需要数据小B的经纬度，发货地址的经纬度
                UserAddress address = bOrderBattleServiceMapper.getBuyerAddressById(Long.parseLong(tradeOrders.getAddressId()));
                String lat1 = address.getLat();
                String lon1 = address.getLon();
                ShopAddress shopInfo = bOrderBattleServiceMapper.getShopInfoByUserInfo(Long.parseLong(userId), Long.parseLong(shopId));
                String lat2 = shopInfo.getSendLat();
                String lon2 = shopInfo.getSendLon();
                //得到他们的距离为米单位
                double distance = LatLonUtil.getDistance(Double.valueOf(lon1), Double.valueOf(lat1), Double.valueOf(lon2), Double.valueOf(lat2));
                LOGGER.info("========================经纬度查询===============end===============================");
                orderBattleInfoModel.setDistance(String.valueOf(distance * 0.001));
                list.add(orderBattleInfoModel);
            }
        }
        return JsonUtil.getSuccessJson(list);
    }

    /**
     * 小B抢单功能
     * 要写：抢单日志表：order_battle_logs，抢没抢成功都记录到这个表（异步）
     * 订单抢单关联表：order_battle_shop，抢单成功后，在成功抢单的逻辑里更新该单对应的所有小B的单子状态。（同步）
     * 商家抢单信息表：orders_battle_info，抢单成功后，更新成功抢到单的小B信息到发货单里面（异步）
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject grabOrder(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject returnJson = new JSONObject();
        //1.校验账户是否绑定微信。
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        User user = bOrderBattleServiceMapper.getUserInfoById(Long.parseLong(userId));
        if (user == null || user.getWxOpenId() == null || StringUtil.isEmpty(user.getWxOpenId())) {
            returnJson.put("grabFlag", "false");
            returnJson.put("msg", "亲，您的抢单收益会定期结算到微信账号中，请您先绑定微信~");
            return JsonUtil.getSuccessJson(returnJson);
        }
        String orderBattleId = jsonObject.get("orderBattleId").toString();
        String orderId = jsonObject.get("orderId").toString();
        String status;
        boolean del = redisServer.del(ORDER_BATTLE_REDID, orderBattleId);
        if (del) {
            //证明该小B抢单成功
            returnJson.put("grabFlag", "true");
            status = GRAB_STATUS_SUCCESS.getId();
            OrderBattleShop orderBattleShop = new OrderBattleShop();
            orderBattleShop.setOrdersBattleId(orderBattleId);
            orderBattleShop.setStatus(ORDER_BATTLE_ALREADY_OTHER_GRAB.getId());
            //首先将该单对应的状态都更新为已被抢
            try {
                bOrderBattleServiceMapper.updateOrderBattleStatus(orderBattleShop);
                //在次更新抢成功的小B的状态为已经抢到手
                orderBattleShop.setUserId(userId);
                orderBattleShop.setShopId(shopId);
                orderBattleShop.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
                bOrderBattleServiceMapper.updateOrderBattleStatus(orderBattleShop);
                OrdersBattleInfo orderBattleInfoBean = getOrderBattleInfoBean(orderBattleId, userId, shopId);
                bOrderBattleServiceMapper.updateBattleInfoById(orderBattleInfoBean);
                bOrderBattleServiceMapper.updateShopIdIntoTradeOrders(Long.parseLong(shopId), Long.parseLong(userId), Long.parseLong(orderId));
                //存放待校验商品信息到redis
                List<TradeOrderItem> orderItems = bOrderBattleServiceMapper.getOrderItemsByOrderId(Long.valueOf(orderId));
                Integer passNum = 0;
                Integer countNum = 0;
                JSONObject cacheJson = new JSONObject();
                cacheJson.put("countNum", countNum);
                List<String> skuList = new ArrayList<>(orderItems.size());
                for (TradeOrderItem tradeOrderItem : orderItems) {
                    String skuId = tradeOrderItem.getSkuId();
                    skuList.add(skuId);
                    String skuNum = tradeOrderItem.getSkuNum();
                    passNum = passNum + Integer.valueOf(skuNum);
                    cacheJson.put(skuId, new ArrayList<>());
                }
                cacheJson.put("passNum", passNum);
                cacheJson.put("skuIdList", skuList);
                cacheJson.put("finishFlag", "false");
                redisServer.set(ORDER_BATTLE_CHECK_GOODS, userId + "_" + orderBattleId, JSONObject.toJSONString(cacheJson), 50L, TimeUnit.MINUTES);
            } catch (Exception e) {
                returnJson.put("grabFlag", "false");
                returnJson.put("msg", "系统异常");
                LOGGER.error("执行更新异常，" + e.getMessage(), e);
                redisServer.set(ORDER_BATTLE_REDID, orderBattleId, orderBattleId);
            }
        } else {
            //证明该小B抢单失败，发货单已被抢走
            returnJson.put("grabFlag", "false");
            returnJson.put("msg", "手慢了，该订单已被抢~");
            status = GRAB_STATUS_FAILURE.getId();
        }
        OrderBattleLogs orderBattleLogsBean = getOrderBattleLogsBean(orderId, orderBattleId, status, userId, shopId);
        syncInsertAndUpdateBattleOrder(orderBattleLogsBean);
        return JsonUtil.getSuccessJson(returnJson);
    }

    /**
     * 异步执行记录抢单日志
     *
     * @param orderBattleLogsBean
     */
    @Async
    void syncInsertAndUpdateBattleOrder(OrderBattleLogs orderBattleLogsBean) {
        try {
            bOrderBattleServiceMapper.insertOrderBattleLogs(orderBattleLogsBean);
        } catch (Exception e) {
            LOGGER.error("syncInsertAndUpdateBattleOrder 方法执行数据库异常" + e.getMessage(), e);
        }
    }

    /**
     * 抢单日志表实体类封装
     *
     * @param orderId
     * @param orderBattleId
     * @param status
     * @return
     */
    private OrderBattleLogs getOrderBattleLogsBean(String orderId, String orderBattleId, String status, String userId, String shopId) {
        OrderBattleLogs orderBattleLogs = new OrderBattleLogs();
        orderBattleLogs.setId(SnowflakeUtil.getPrimaryKey(ORDERS_BATTLE_LOGS));
        orderBattleLogs.setOrderId(orderId);
        orderBattleLogs.setUserId(userId);
        orderBattleLogs.setShopId(shopId);
        orderBattleLogs.setOrdersBattleId(orderBattleId);
        orderBattleLogs.setUserType(USER_TYPE_ORDINARY_USER.getId());
        orderBattleLogs.setStatus(status);
        orderBattleLogs.setCreateTime(DateUtil.getNowTimeStr());
        return orderBattleLogs;
    }


    private OrdersBattleInfo getOrderBattleInfoBean(String orderBattleId, String userId, String shopId) throws Exception {
        OrdersBattleInfo ordersBattleInfo = bOrderBattleServiceMapper.getGrabNumById(Long.valueOf(orderBattleId));
        ordersBattleInfo.setUserId(userId);
        ordersBattleInfo.setShopId(shopId);
        String grabNum = ordersBattleInfo.getGrabedNum() == null ? "0" : ordersBattleInfo.getGrabedNum();
        ordersBattleInfo.setGrabedNum(String.valueOf(Integer.valueOf(grabNum) + 1));
        ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_ALREADY_BATTLE.getId());
        String nowTimeStr = DateUtil.getNowTimeStr();
        ordersBattleInfo.setGrabedAt(nowTimeStr);
        //抢单后，商品校验的时间为五分钟。
        String checkDeadLine = DateUtil.getAfterMinutesDateFromPointTime(5, nowTimeStr);
        ordersBattleInfo.setCheckedAt(checkDeadLine);
        ordersBattleInfo.setModifyTime(nowTimeStr);
        return ordersBattleInfo;
    }

    /**
     * 小B释放抢单接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject releaseGrabOrder(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = jsonObject.get("orderBattleId").toString();
        //首先校验当前的单子是否可以释放。如果单子到了状态：配送中，则无法释放订单。
        OrdersBattleInfo orderBattle = bOrderBattleServiceMapper.getGrabNumById(Long.valueOf(id));
        String status = orderBattle.getStatus();
        if (Integer.parseInt(ORDER_BATTLE_STATUS_RIDER_ARRIVAL.getId()) < Integer.parseInt(status)) {
            jsonObject.put("releaseFlag", "false");
            jsonObject.put("msg", "订单配送中，不允许释放订单！");
            return jsonObject;
        }
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        //2.更新order_battle_shop表中的中小B对应这个单子状态为可抢，同时更新该小B的抢单状态为4-取消、释放状态
        OrderBattleShop orderBattleShop = new OrderBattleShop();
        orderBattleShop.setOrdersBattleId(id);
        orderBattleShop.setStatus(ORDER_BATTLE_STATUS_NOT_BATTLE.getId());
        bOrderBattleServiceMapper.updateBattleOrderRelease(orderBattleShop);
        orderBattleShop.setStatus(ORDER_BATTLE_GIVE_UP_GRAB.getId());
        orderBattleShop.setUserId(userId);
        orderBattleShop.setShopId(shopId);
        bOrderBattleServiceMapper.updateBattleOrderRelease(orderBattleShop);
        //小B在点击商品校验完成按钮前，即没有发送订单到蜂鸟，只考虑代发货的数量即可，此时尚未扣除库存数量和增加销售量
        if (Integer.parseInt(status) < Integer.parseInt(ORDER_BATTLE_STATUS_ALREADY_SEND.getId())) {
            //3.查询该小B已经校验过的商品及数量 order_battle_check_logs，扫描过得肯定在小B的库存中有代发货的数量delivery_nums。要给他加上 shop_stocks
            recoverDeliveryNum(id, shopId);
        } else {
            //3.说明已经发送订单到蜂鸟了，这时待发货数量恢复。库存减少，销售量则增加。要恢复这两个字段的数据
            recoverStocks(orderBattle.getOrderId(), shopId);
            // 调用蜂鸟的同步取消订单接口。
            String orderNo = bOrderBattleServiceMapper.getOrderNoByOrderId(Long.parseLong(orderBattle.getOrderId()));
            sendCancelOrderToFN(orderNo, SHOP_CANCEL.getId());
        }
        //4.更新该发货单的状态为可抢orders_battle_info,并删除掉小B的信息，并把它放到redis中。
        OrdersBattleInfo ordersBattleInfo = new OrdersBattleInfo();
        ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_NOT_BATTLE.getId());
        ordersBattleInfo.setUserId(null);
        ordersBattleInfo.setId(id);
        ordersBattleInfo.setShopId(null);
        ordersBattleInfo.setModifyTime(DateUtil.getNowTimeStr());
        ordersBattleInfo.setCheckedAt(null);
        ordersBattleInfo.setGrabedAt(null);
        bOrderBattleServiceMapper.updateBattleInfoById(ordersBattleInfo);
        //将发货单添加到redis的抢单列表中，主要用id,发货单别的信息用不到。
        redisServer.setEntity(ORDER_BATTLE_REDID, id, id);
        jsonObject.put("releaseFlag", "true");
        redisServer.del(ORDER_BATTLE_CHECK_GOODS, getLoginUserId(json) + "_" + id);
        return JsonUtil.getSuccessJson(jsonObject);
    }

    private void sendCancelOrderToFN(String orderNo, String orderCancelReasonCode) throws Exception {
        JSONObject fnJsonObject = new JSONObject();
        fnJsonObject.put("partner_order_code", orderNo);
        fnJsonObject.put("order_cancel_code", ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK.getId());
        fnJsonObject.put("order_cancel_description", ORDER_CANCEL_LOGISTICS_REASONS_OUT_OF_STOCK.getName());
        fnJsonObject.put("order_cancel_reason_code", orderCancelReasonCode);
        fnJsonObject.put("order_cancel_time", DateUtil.getNowTimeStr());
        JSONObject orderCancelJson = callFNService(FN_ORDER_CANCEL, fnJsonObject);
        LOGGER.info("调用蜂鸟的同步取消订单接口返回信息：" + orderCancelJson);
        //取消失败，则手动回滚
        if (orderCancelJson.get("code").equals("1")) {
            throw new Exception();
        }
    }

    /**
     * 恢复增加的代发货数量
     */
    private void recoverDeliveryNum(String id, String shopId) {
        List<OrderBattleCheckLogs> shopCheckLogs = bOrderBattleServiceMapper.getShopCheckLogs(Long.parseLong(id), Long.parseLong(shopId));
        if (shopCheckLogs != null && shopCheckLogs.size() > 0) {
            //由于校验商品用的是唯一码code，所以会出现即使一个skuId，也会出现多条的情况。所以这里要先进行统计，去重复。在更新到库存表的待发货数值中。
            Map<String, Integer> map = new HashMap(2);
            Integer skuNum = 0;
            for (OrderBattleCheckLogs orderBattleCheckLogs : shopCheckLogs) {
                skuNum = skuNum + (map.get(orderBattleCheckLogs.getSkuId()) == null ? 0 : map.get(orderBattleCheckLogs.getSkuId()));
                map.put(orderBattleCheckLogs.getSkuId(), skuNum);
            }
            Set<Map.Entry<String, Integer>> entries = map.entrySet();
            ShopStocks shopStocks;
            for (Map.Entry entry : entries) {
                shopStocks = new ShopStocks();
                shopStocks.setSkuId(entry.getKey().toString());
                shopStocks.setDeliveryNums(entry.getValue().toString());
                shopStocks.setShopId(shopId);
                shopStocks.setModifyTime(DateUtil.getNowTimeStr());
                bOrderBattleServiceMapper.updateStockDeliveryNums(shopStocks);
            }
        }
    }

    /**
     * 恢复 被扣减的库存,增加的销量
     *
     * @param orderId
     */
    private void recoverStocks(String orderId, String shopId) {
        List<TradeOrderItem> orderItems = bOrderBattleServiceMapper.getOrderItemsByOrderId(Long.valueOf(orderId));
        for (TradeOrderItem tradeOrderItem : orderItems) {
            String skuNum = tradeOrderItem.getSkuNum();
            String skuId = tradeOrderItem.getSkuId();
            bOrderBattleServiceMapper.recoverShopGoodsStocks(Integer.valueOf(skuNum), Long.parseLong(shopId), Long.parseLong(skuId), DateUtil.getNowTimeStr());
        }
    }


    /**
     * 取消订单是订单作废，走退款流程。和释放订单不一样。
     * 1.更新订单表trade_orders为取消。订单状态为完成
     * 2.更新orders_battle_info 状态为已取消。
     * 3.更新库存的待发货数值
     * 4.调用退款接口，进行退款。
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject cancelGrabOrder(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderBattleId = jsonObject.get("orderBattleId").toString();
        String orderId = jsonObject.get("orderId").toString();
        //首先校验当前的单子是否可以释放
        OrdersBattleInfo orderBattle = bOrderBattleServiceMapper.getGrabNumById(Long.valueOf(orderBattleId));
        String status = orderBattle.getStatus();
        if (Integer.parseInt(ORDER_BATTLE_STATUS_RIDER_ARRIVAL.getId()) < Integer.parseInt(status)) {
            jsonObject.put("cancelFlag", "false");
            jsonObject.put("msg", "订单配送中，不允许取消订单！");
        }
        bOrderBattleServiceMapper.cancleOrderByOrderId(Long.valueOf(orderId), DateUtil.getNowTimeStr());
        bOrderBattleServiceMapper.updateBattleOrderStatusById(Long.valueOf(orderBattleId), DateUtil.getNowTimeStr(), ORDER_BATTLE_STATUS_CANCELED.getId());
        String shopId = getLoginShopId(json);
        //如果单子没有发送到蜂鸟，只考虑代发货的数量即可
        if (Integer.parseInt(status) < Integer.parseInt(ORDER_BATTLE_STATUS_ALREADY_SEND.getId())) {
            //3.查询该小B已经校验过的商品及数量 order_battle_check_logs，扫描过得肯定在小B的库存中有代发货的数量delivery_nums。要给他加上 shop_stocks
            recoverDeliveryNum(orderBattleId, shopId);
        } else {
            //3.说明已经校验通过，这时待发货数量恢复。库存减少，销售量则增加。要恢复这两个字段的数据
            recoverStocks(orderBattle.getOrderId(), shopId);
            //调用蜂鸟的同步取消订单接口。
            String orderNo = bOrderBattleServiceMapper.getOrderNoByOrderId(Long.parseLong(orderBattle.getOrderId()));
            sendCancelOrderToFN(orderNo, SHOP_CANCEL.getId());
        }
        //TODO  调用退款接口
        LOGGER.info("============================退款接口尚未开发====================================================");
        /*if(true){
            throw new Exception("退款接口尚未开发");
        }*/
        jsonObject.put("flag", "true");
        redisServer.del(ORDER_BATTLE_CHECK_GOODS, orderBattle.getUserId() + "_" + orderBattleId);
        return JsonUtil.getSuccessJson(jsonObject);
    }

    /**
     * 通过code校验商品的有效性的判断点有几个
     * 1.goods_sku_code 的存在且验证次数不能大于1（不加上本次校验）.
     * 2.校验的商品是否是订单内的商品skuId
     * redis需要存放一个过期时间为6分钟的缓存，用来判断校验是否通过。只有预设的校验次数和最后扫描完成判断相等，ok，校验通过
     * passNum:通过校验需要的累积通过数
     * countNum:上次校验后的通过数
     * {userId:{passNum:5,skuIdList:[],countNum:4,code1:skuId1,code2:skuId2,...}}
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject checkGoodsEffectiveness(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject returnJson = new JSONObject();
        String code = jsonObject.get("code").toString();
        String orderId = jsonObject.get("orderId").toString();
        String battleId = jsonObject.get("battleId").toString();
        String userId = getLoginUserId(json);
        //首先根据用户id去redis取商品的扫描记录。
        String cache = redisServer.get(RedisKey.ORDER_BATTLE_CHECK_GOODS, userId + "_" + battleId);
        if (cache == null || StringUtil.isEmpty(cache)) {
            returnJson.put("msg", "商品校验已经超时！");
            returnJson.put("checkFlag", "false");
            return JsonUtil.getSuccessJson(returnJson);
        }
        JSONObject jsonCache = JSONObject.parseObject(cache);
        String finishFlag = jsonCache.get("finishFlag").toString();
        if (CHECK_GOODS_SECCESS.getId().equals(finishFlag)) {
            returnJson.put("msg", "商品已校验成功！");
            returnJson.put("checkFlag", "true");
            returnJson.putAll(jsonCache);
            returnJson.remove("countNum");
            returnJson.remove("skuIdList");
            returnJson.remove("passNum");
            return JsonUtil.getSuccessJson(returnJson);
        }
        Object skuIdsTemp = jsonCache.get("skuIdList");
        //获取到订单中的skuId
        List<String> skuList = JSONObject.parseArray(JSONObject.toJSONString(skuIdsTemp), String.class);
        //根据code查询对应goods_sku_code中的skuId
        GoodsSkuInfo goodsSkuInfo = bOrderBattleServiceMapper.getGoodsSkuInfoByBarCode(code);
        if (goodsSkuInfo == null) {
            return JsonUtil.getErrorJson(CHECK_GOODS_NO_GOODS_CODE_DATA);
        }
        String skuId = goodsSkuInfo.getId();
        if (skuList.contains(skuId)) {
            Object codesTemp = jsonCache.get(skuId);
            if (codesTemp == null || StringUtil.isEmpty(codesTemp.toString())) {
                List<String> codeList = new ArrayList<>();
                codeList.add(code);
                jsonCache.put(skuId, codeList);
            } else {
                List<String> codeList = JSONObject.parseArray(codesTemp.toString(), String.class);
                int passNum = Integer.valueOf(jsonCache.get("passNum").toString());
                int countNum = Integer.valueOf(jsonCache.get("countNum").toString());
                if (passNum == countNum + 1) {
                    //TODO 二期待确认：校验完成时，要先确认小B的库存是否满足，满足在返回校验成功，否则校验成功，但提示库存不足。
                    jsonCache.put("finishFlag", "true");
                    //更新抢单的状态为3-商家已商品校验
                    bOrderBattleServiceMapper.updateBattleOrderStatusById(Long.parseLong(battleId), DateUtil.getNowTimeStr(), ORDER_BATTLE_STATUS_ALREADY_CHECK.getId());
                }
                codeList.add(code);
                jsonCache.put(skuId, codeList);
                jsonCache.put("countNum", countNum + 1);
            }

        } else {
            returnJson.put("msg", "用户没有订购该商品，请更换正确商品！");
            returnJson.put("checkFlag", "false");
            return JsonUtil.getSuccessJson(returnJson);
        }
        returnJson.put("checkFlag", "true");
        redisServer.set(RedisKey.ORDER_BATTLE_CHECK_GOODS, userId + "_" + battleId, JSONObject.toJSONString(jsonCache));
        //增加商家订单检货日志
        OrderBattleCheckLogs orderBattleCheckLogs = getOrderBattleCheckLogsBean(battleId, orderId, skuId, code, getLoginShopId(json));
        bOrderBattleServiceMapper.insertOrderBattleCheckLogs(orderBattleCheckLogs);
        //更新商家库存待发货数量
        bOrderBattleServiceMapper.updateGoodsSocksByShopIdAndSkuId(Long.parseLong(getLoginShopId(json)), Long.parseLong(skuId), DateUtil.getNowTimeStr());
        returnJson.putAll(jsonCache);
        returnJson.remove("countNum");
        returnJson.remove("skuIdList");
        returnJson.remove("passNum");
        return JsonUtil.getSuccessJson(returnJson);

        //下面注释掉的不能动。后面要放开用的。目前暂时用上面的逻辑
        //下面注释掉的不能动。后面要放开用的。目前暂时用上面的逻辑
        //下面注释掉的不能动。后面要放开用的。目前暂时用上面的逻辑
       /*  JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject returnJson = new JSONObject();
        String code = jsonObject.get("code").toString();
        String orderId = jsonObject.get("orderId").toString();
        String battleId = jsonObject.get("battleId").toString();
        String userId = getLoginUserId();
        //首先根据用户id去redis取商品的扫描记录。
        String cache = redisServer.get(ORDER_BATTLE_CHECK_GOODS, userId+"_"+battleId);
        if (cache == null || StringUtil.isEmpty(cache)) {
            returnJson.put("msg", "商品校验已经超时！");
            returnJson.put("checkFlag", "false");
            return JsonUtil.getSuccessJson(returnJson);
        }

        JSONObject jsonCache = JSONObject.parseObject(cache);
        String finishFlag = jsonCache.get("finishFlag").toString();
        if (CHECK_GOODS_SECCESS.getId().equals(finishFlag)) {
            returnJson.put("msg", "商品已校验成功！");
            returnJson.put("checkFlag", "true");
            returnJson.putAll(jsonCache);
            returnJson.remove("countNum");
            returnJson.remove("skuIdList");
            returnJson.remove("passNum");
            return JsonUtil.getSuccessJson(returnJson);
        }
        Object skuIdsTemp = jsonCache.get("skuIdList");
        //获取到订单中的skuId
        List<String> skuList = JSONObject.parseArray(JSONObject.toJSONString(skuIdsTemp), String.class);
        //根据code查询对应goods_sku_code中的skuId
        GoodsSkuCode goodsSkuCode = bOrderBattleServiceMapper.getGoodsSkuCodeInfoByCode(code);
        if(goodsSkuCode == null){
            return JsonUtil.getErrorJson(CHECK_GOODS_NO_GOODS_CODE_DATA);
        }
        String skuId = goodsSkuCode.getSkuId();
        if (skuList.contains(skuId)) {
            String checkNum = goodsSkuCode.getCheckNum();
            Object codesTemp = jsonCache.get(skuId);
            if (codesTemp == null || StringUtil.isEmpty(codesTemp.toString())) {
                if (Integer.parseInt(checkNum) > 1) {
                    returnJson.put("msg", "该商品已经在合伙人平台销售过，不能二次销售！");
                    returnJson.put("checkFlag", "false");
                    return JsonUtil.getSuccessJson(returnJson);
                }
                List<String> codeList = new ArrayList<>();
                codeList.add(code);
                jsonCache.put(skuId, codeList);
            } else {
                List<String> codeList = JSONObject.parseArray(codesTemp.toString(), String.class);
                if (codeList.contains(code)) {
                    returnJson.put("msg", "该商品已经扫描过了！");
                    returnJson.put("checkFlag", "false");
                    return JsonUtil.getSuccessJson(returnJson);
                }
                if (Integer.parseInt(checkNum) > 1) {
                    returnJson.put("msg", "该商品已经在合伙人平台销售过，不能二次销售！");
                    returnJson.put("checkFlag", "false");
                    return JsonUtil.getSuccessJson(returnJson);
                }
                int passNum = Integer.valueOf(jsonCache.get("passNum").toString());
                int countNum = Integer.valueOf(jsonCache.get("countNum").toString());
                if (passNum == countNum + 1) {
                    jsonCache.put("finishFlag", "true");
                    //更新抢单的状态为3-商家已商品校验
                    bOrderBattleServiceMapper.updateBattleOrderStatusById(Long.parseLong(battleId), DateUtil.getNowTimeStr(), ORDER_BATTLE_STATUS_ALREADY_CHECK.getId());
                }
                codeList.add(code);
                jsonCache.put(skuId, codeList);
                jsonCache.put("countNum", countNum + 1);
            }

        } else {
            returnJson.put("msg", "用户没有订购该商品，请更换正确商品！");
            returnJson.put("checkFlag", "false");
            return JsonUtil.getSuccessJson(returnJson);
        }
        returnJson.put("checkFlag", "true");
        redisServer.set(ORDER_BATTLE_CHECK_GOODS, userId+"_"+battleId, JSONObject.toJSONString(jsonCache));
        //校验通过则更新校验次数+1
        bOrderBattleServiceMapper.updateGoodsSkuCodeCheckTimes(code, DateUtil.getNowTimeStr());
        //增加商家订单检货日志
        OrderBattleCheckLogs orderBattleCheckLogs = getOrderBattleCheckLogsBean(battleId, orderId, skuId, goodsSkuCode.getBarCode());
        bOrderBattleServiceMapper.insertOrderBattleCheckLogs(orderBattleCheckLogs);
        //更新商家库存待发货数量
        bOrderBattleServiceMapper.updateGoodsSocksByShopIdAndSkuId(Long.parseLong(getLoginShopId()), Long.parseLong(skuId), DateUtil.getNowTimeStr());
        returnJson.putAll(jsonCache);
        returnJson.remove("countNum");
        returnJson.remove("skuIdList");
        returnJson.remove("passNum");
        return JsonUtil.getSuccessJson(returnJson);*/
    }

    private OrderBattleCheckLogs getOrderBattleCheckLogsBean(String battleId, String orderId, String skuId, String barCode, String shopId) {
        OrderBattleCheckLogs orderBattleCheckLogs = new OrderBattleCheckLogs();
        orderBattleCheckLogs.setId(SnowflakeUtil.getPrimaryKey(ORDER_BATTLE_CHECK_LOGS));
        orderBattleCheckLogs.setShopId(shopId);
        orderBattleCheckLogs.setOrdersBattleId(battleId);
        orderBattleCheckLogs.setOrderId(orderId);
        orderBattleCheckLogs.setSkuId(skuId);
        orderBattleCheckLogs.setBarcode(barCode);
        orderBattleCheckLogs.setCreateTime(DateUtil.getNowTimeStr());
        return orderBattleCheckLogs;
    }

    @Override
    public JSONObject sendOrderToHummingBird(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderId = jsonObject.get("orderId").toString();
        //扣减库存，待发货数量,增加销量
        deductionStocks(orderId, getLoginShopId(json));
        //调用蜂鸟的接口推送订单
        JSONObject fnJsonObject = new JSONObject();
        fnJsonObject.put("order_id", orderId);
        OrdersBattleInfo ordersBattleInfo = new OrdersBattleInfo();
        ordersBattleInfo.setModifyTime(DateUtil.getNowTimeStr());
        ordersBattleInfo.setOrderId(orderId);
        ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_ALREADY_SEND.getId());
        bOrderBattleServiceMapper.updateBattleOrderDeliveryStatus(ordersBattleInfo);
        JSONObject orderCreateJson = callFNService(FN_ORDER_CREATE, fnJsonObject);
        LOGGER.info("调用蜂鸟的接口推送订单返回信息：" + orderCreateJson);
        return JsonUtil.getSuccessJson(orderCreateJson);
    }


    /**
     * 扣减库存,增加销量
     *
     * @param orderId
     */
    private void deductionStocks(String orderId, String shopId) {
        List<TradeOrderItem> orderItems = bOrderBattleServiceMapper.getOrderItemsByOrderId(Long.valueOf(orderId));
        for (TradeOrderItem tradeOrderItem : orderItems) {
            String skuNum = tradeOrderItem.getSkuNum();
            String skuId = tradeOrderItem.getSkuId();
            bOrderBattleServiceMapper.deductionShopGoodsStocks(Integer.valueOf(skuNum), Long.parseLong(shopId), Long.parseLong(skuId), DateUtil.getNowTimeStr());
        }
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
     * 确认收货接口
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject confirmFinishOrder(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderId = jsonObject.get("orderId").toString();
        OrdersBattleInfo ordersBattleInfo = new OrdersBattleInfo();
        ordersBattleInfo.setOrderId(orderId);
        ordersBattleInfo.setModifyTime(DateUtil.getNowTimeStr());
        ordersBattleInfo.setStatus(ORDER_BATTLE_STATUS_FINISHED.getId());
        bOrderBattleServiceMapper.updateBattleOrderDeliveryStatus(ordersBattleInfo);
        bOrderBattleServiceMapper.updateOrderStatusById(orderId, DateUtil.getNowTimeStr(), Integer.parseInt(ORDER_STATUS_COMPLETE.getId()));
        OrderBattleExpresses orderBattleExpresses = getOrderBattleExpress(ordersBattleInfo);
        orderBattleExpresses.setState(ORDER_BATTLE_STATUS_FINISHED.getId());
        orderBattleExpresses.setCreateTime(DateUtil.getNowTimeStr());
        bOrderBattleServiceMapper.insertOrderBattleExpress(orderBattleExpresses);
        TradeOrder tradeOrders = bOrderBattleServiceMapper.getTradeOrdersById(Long.parseLong(orderId));
        List<TradeOrderItem> tradeOrderItemList = bOrderBattleServiceMapper.getTradeOrderItemByOrderId(Long.parseLong(orderId));
        Integer num = 0;
        for (TradeOrderItem tradeOrderItem : tradeOrderItemList) {
            String skuNum = tradeOrderItem.getSkuNum();
            num = num + Integer.parseInt(skuNum);
        }
        ShopProfitLogs shopProfitLogsBean = getShopProfitLogsBean(tradeOrders, getLoginUserId(json), getLoginShopId(json));
        bOrderBattleServiceMapper.insertShopProfitLogs(shopProfitLogsBean);
        ShopProfitsForBattleOrder shopProfitsForBattleOrderBean = getShopProfitsBean(tradeOrders, num, getLoginShopId(json), getLoginUserId(json));
        ShopProfitsForBattleOrder shopProfitsForBattleOrder = bOrderBattleServiceMapper.getShopProfitsByUserIdAndShopId(getLoginUserId(json), getLoginShopId(json));
        if (shopProfitsForBattleOrder == null) {
            //执行新增
            bOrderBattleServiceMapper.insertShopProfits(shopProfitsForBattleOrderBean);
        } else {
            //执行更新
            shopProfitsForBattleOrderBean.setId(shopProfitsForBattleOrder.getId());
            bOrderBattleServiceMapper.updateShopProfitsByPrimaryKey(shopProfitsForBattleOrderBean);
        }
        String spuName = tradeOrderItemList.get(0).getSpuName();
        //----------------------------------------------异步通知确认收货----微信小程序模版消息提醒---start
        tradeOrderServiceAsync.asyncNotifyUserConfirmReceipt(spuName,tradeOrders);
        //----------------------------------------------异步通知确认收货----微信小程序模版消息提醒---end
        return JsonUtil.getSuccessJson();
    }

    /**
     * 获取商家收益汇总实体类
     *
     * @return
     */
    private ShopProfitsForBattleOrder getShopProfitsBean(TradeOrder tradeOrders, int grab, String shopId, String userId) {
        ShopProfitsForBattleOrder shopProfitsForBattleOrder = new ShopProfitsForBattleOrder();
        shopProfitsForBattleOrder.setId(SnowflakeUtil.getPrimaryKey(SHOP_PROFITS));
        shopProfitsForBattleOrder.setShopId(shopId);
        //收益为订单的百分之一
        BigDecimal bigDecimal = new BigDecimal(tradeOrders.getOrderMoney()).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP);
        shopProfitsForBattleOrder.setTotalProfit(bigDecimal.toString());
        shopProfitsForBattleOrder.setUserId(userId);
        shopProfitsForBattleOrder.setGrab(grab + "");
        shopProfitsForBattleOrder.setCreateTime(DateUtil.getNowTimeStr());
        return shopProfitsForBattleOrder;
    }

    /**
     * 获取商家收益日志实体类
     *
     * @return
     */
    private ShopProfitLogs getShopProfitLogsBean(TradeOrder tradeOrders, String userId, String shopId) {
        ShopProfitLogs shopProfitLogs = new ShopProfitLogs();
        shopProfitLogs.setId(SnowflakeUtil.getPrimaryKey(SHOP_PROFIT_LOGS));
        shopProfitLogs.setOrderId(tradeOrders.getId());
        shopProfitLogs.setUserId(userId);
        shopProfitLogs.setShopId(shopId);
        BigDecimal bigDecimal = new BigDecimal(tradeOrders.getOrderMoney()).multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
        shopProfitLogs.setProfit(bigDecimal.toString());
        shopProfitLogs.setProfitType(PROFIT_TYPE_GRAB_AMOUNT.getId());
        shopProfitLogs.setCreateTime(DateUtil.getNowTimeStr());
        shopProfitLogs.setStatus(BILLING_STATUS_WAITING_BILL.getId());
        return shopProfitLogs;
    }

    @Override
    public JSONObject getOrderBattleInfo(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderId = jsonObject.get("orderId").toString();
        List<OrdersBattleInfo> battleInfo = bOrderBattleServiceMapper.getBattleInfoByOrderId(orderId);
        return JsonUtil.getSuccessJson((battleInfo != null && battleInfo.size() > 0) ? battleInfo.get(0) : null);
    }

    @Override
    public JSONObject queryOrderDetail(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderId = jsonObject.get("orderId").toString();
        List<TradeOrderItem> tradeOrderItemList = bOrderBattleServiceMapper.getTradeOrderItemByOrderId(Long.parseLong(orderId));
        List<OrdersBattleInfo> battleInfo = bOrderBattleServiceMapper.getBattleInfoByOrderId(orderId);
        Map map = new HashMap(2);
        map.put("itemList", tradeOrderItemList);
        map.put("status", battleInfo.get(0).getStatus());
        return JsonUtil.getSuccessJson(map);
    }

    @Override
    public JSONObject queryCarrierPosition(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderId = jsonObject.get("orderId").toString();
        String orderNo = bOrderBattleServiceMapper.getOrderNoByOrderId(Long.parseLong(orderId));
        jsonObject.put("orderNo", orderNo);
        JSONObject carrierQueryJson = callFNService(FN_CARRIER_QUERY, jsonObject);
        LOGGER.info("调用蜂鸟的接口推送订单返回信息：" + carrierQueryJson);
        return JsonUtil.getSuccessJson(carrierQueryJson);
    }
}

