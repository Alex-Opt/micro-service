package com.ly.mt.task.gy.timing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.LatLonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.goods.dao.GoodsSkuInfoDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.trade.dao.TradeOrdersDao;
import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.data.trade.entity.TradeOrders;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.entity.UserAddress;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.gy.entity.GYResult;
import com.ly.mt.task.gy.method.MethodEnum;
import com.ly.mt.task.gy.service.impl.GyServiceImpl;
import com.ly.mt.task.gy.timing.service.OrderTimingService;
import com.ly.mt.task.order.service.TradeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_CITY_EXPRESS;
import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ORDINARY_EXPRESS;
import static com.ly.mt.core.base.dict.IsRefund.IS_REFUND_NO;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_DELIVERY;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_RECEIPT;
import static com.ly.mt.core.base.dict.PushStatus.*;
import static com.ly.mt.core.base.dict.ShopStatus.SHOP_STATUS_YES;
import static com.ly.mt.core.base.dict.ShopType.SHOP_TYPE_BUSINESS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.logistics.dict.Kd100Com.KD_COM_SF;

@Service
public class OrderTimingServiceImpl extends GyServiceImpl implements OrderTimingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    @Resource
    private TradeOrdersDao tradeOrdersDao;
    @Resource
    private TradeOrderItemsDao tradeOrderItemsDao;
    @Resource
    private TradeOrderService tradeOrderService;
    @Resource
    private UserAddressDao userAddressDao;
    @Resource
    private GoodsSkuInfoDao goodsSkuInfoDao;
    @Resource
    private ShopInfoDao shopInfoDao;

    @Override
    public JSONObject sendTrade(String json) throws Exception {
        // 订单id
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = jsonObject.getString("id");
        LOGGER.info("------------------------------收到推送管易的订单入参----------------------------------------" + jsonObject);
        if (StringUtil.isEmpty(id)) {
            LOGGER.error("推送订单到管易入参订单id为空");
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
        //查询订单数据
        TradeOrders tradeOrders = tradeOrdersDao.getTradeOrdersByIdFromRedis(id);
        if (null == tradeOrders) {
            LOGGER.error("推送订单到管易查询id={}的订单数据为空", id);
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
        LOGGER.info("-----------tradeOrder.getDistributionId():" + tradeOrders.getDistributionId());
        tradeOrders = sendMsg(tradeOrders);
        LOGGER.info("---------------------------发送短信给店铺人员结束-------------------------------------");
        //只有次日达和普通快递这两种快递方式走管易
        if (tradeOrders.getDistributionId().equals(DISTRIBUTE_TYPE_CITY_EXPRESS.getId()) || tradeOrders.getDistributionId().equals(DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId())) {
            //查询订单明细数据
            List<TradeOrderItems> tradeOrderItemList = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(id);
            //查询用户地址数据
            UserAddress userAddress = userAddressDao.getUserAddressByIdFromRedis(tradeOrders.getAddressId());
            //封装推送订单的参数//getReqJSONObject();
            JSONObject paramJson = processParam(getReqJSONObject(), tradeOrders, tradeOrderItemList, userAddress, tradeOrders.getDistributionId());
            //调用管易接口，推送订单
            paramJson.put("sign", getSign(paramJson.toString()));
            String returnJson = postGY(paramJson);
            GYResult result = JSONObject.parseObject(returnJson, GYResult.class);
            if (result.getSuccess()) {
                TradeOrders orders = new TradeOrders();
                orders.setId(id);
                orders.setGyShopCode(paramJson.getString("shop_code"));
                orders.setGyWarehouseCode(paramJson.getString("warehouse_code"));
                orders.setPushStatus(PUSH_STATUS_PUSH_SUCCESS.getId());
                orders.setOrderStatus(ORDER_STATUS_PENDING_RECEIPT.getId());
                tradeOrdersDao.updateTradeOrders(orders);
                LOGGER.info("推送订单数据到管易，更新订单表状态成功！");
            }
            return JSONObject.parseObject(returnJson);
        }
        return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
    }


    /**
     * 组装调用管易接口参数
     *
     * @param json
     * @param order
     * @param itemList
     * @param address
     * @return
     */
    public JSONObject processParam(JSONObject json, TradeOrders order, List<TradeOrderItems> itemList, UserAddress address, String deliveryType) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        json.put("method", "gy.erp.trade.add");
        //店铺代码
        if (DISTRIBUTE_TYPE_CITY_EXPRESS.getId().equals(deliveryType)) {
            json.put("shop_code", order.getGyShopCode() == null ? yml.getShopIdNextDay() : order.getGyShopCode());
            //物流公司
            json.put("express_code", KD_COM_SF.getGyCom());
        } else {
            json.put("shop_code", order.getGyShopCode() == null ? yml.getShopIdCommon() : order.getGyShopCode());
            //物流公司
            json.put("express_code", KD_COM_SF.getGyCom());
        }
        json.put("post_fee", Double.valueOf(order.getFreight()));
        json.put("cod", false);
        //会员代码
        json.put("vip_code", address.getUserId());
        //平台单号
        json.put("platform_code", order.getOrderNo());
        String wareHouseId = null;
        if (yml.getWarehouseId().contains("1")) {
            wareHouseId = "001";
        } else {
            wareHouseId = yml.getWarehouseId();
        }
        //仓库代码
        json.put("warehouse_code", order.getGyWarehouseCode() == null ? wareHouseId : order.getGyWarehouseCode());
        //拍单时间
        json.put("deal_datetime", format.format(format.parse(order.getCreateTime())));
        //订单类型
        json.put("order_type_code", "Sales");
        //买家留言
        json.put("buyer_memo", order.getBuyerMemo());
        //收货人
        json.put("receiver_name", address.getReceiveName());
        //电话
        json.put("receiver_phone", address.getReceivePhone());
        //手机号
        json.put("receiver_mobile", address.getReceivePhone());
        //收货地址
        json.put("receiver_address", address.getUserAddress());
        json.put("receiver_province", address.getProvinceName());
        json.put("receiver_city", address.getCityName());
        json.put("receiver_district", address.getDistrictName());
        //商品明细
        List<Map<String, Object>> details = new ArrayList<>(itemList.size());
        for (TradeOrderItems item : itemList) {
            Map<String, Object> detail = new LinkedHashMap<>();
            detail.put("item_code", item.getSkuId());
            GoodsSkuInfo goodsSkuInfo = goodsSkuInfoDao.getGoodsSkuInfoByIdFromRedis(item.getSkuId());
            // 不能传空值
            detail.put("sku_code", goodsSkuInfo.getCode());
            detail.put("price", item.getSkuPrice());
            detail.put("qty", item.getSkuNum());
            detail.put("oid", item.getId());
            details.add(detail);
        }
        json.put("details", details);
        //支付明细
        List<Map<String, Object>> payments = new ArrayList<>();
        Map<String, Object> payment = new LinkedHashMap<>();
        payment.put("pay_type_code", order.getPaymentType());
        payment.put("payment", Double.valueOf(order.getOrderMoney()));
        payments.add(payment);
        json.put("payments", payments);
        return json;
    }

    /**
     * m-server调用gy服务查询订单的物流信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getDeliveryInfoByOrderId(String json) throws Exception {
        JSONObject jsonObject1 = JSONObject.parseObject(json);
        String orderNo = (String) jsonObject1.get("orderNo");
        JSONObject jsonObject = getReqJSONObject();
        jsonObject.put("page_no", "1");
        jsonObject.put("page_size", "5");
        jsonObject.put("delivery", "1");
        //按订单号查询
        jsonObject.put("outer_code", orderNo);
        jsonObject.put("method", MethodEnum.GY_ERP_TRADE_DELIVERYS_GET.getMethodName());
        jsonObject.put("sign", getSign(jsonObject.toJSONString()));
        String responseJson = postGY(jsonObject);
        LOGGER.info("查询gy对应订单号：" + orderNo + "对应的物流信息返回值：" + responseJson);
        return JSONObject.parseObject(responseJson);
    }

    /**
     * 批量推送订单到管易系统
     *
     * @param orderList
     * @return
     * @throws Exception
     */
    @Override
    public void sendTradeOrdersToGY(List<TradeOrders> orderList) throws Exception {
        for (TradeOrders tradeOrder : orderList) {
            //只有次日达和普通快递这两种快递方式走管易
            if (tradeOrder == null) {
                continue;
            }
            tradeOrder = sendMsg(tradeOrder);
            if (tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_CITY_EXPRESS.getId()) || tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId())) {
                //查询订单明细数据
                List<TradeOrderItems> tradeOrderItemList = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(tradeOrder.getId());
                //查询用户地址数据
                LOGGER.info("tradeOrder.getAddressId():" + tradeOrder.getAddressId());
                UserAddress userAddress = userAddressDao.getUserAddressByIdFromRedis(tradeOrder.getAddressId());
                //查询区域数据(根据userAddress的DistrictCode递归查询省市区数据)
                LOGGER.info("userAddress.getDistrictCode()：" + JSONObject.toJSONString(userAddress));
                if (StringUtil.isEmpty(userAddress.getDistrictCode())) {
                    LOGGER.info("用户地址数据缺失，不能推送到管易！，订单id为：" + tradeOrder.getId());
                    TradeOrders orders = new TradeOrders();
                    orders.setId(tradeOrder.getId());
                    orders.setPushStatus(PUSH_STATUS_PUSH_FAIL.getId());
                    tradeOrdersDao.updateTradeOrders(orders);
                    continue;
                }
                //封装推送订单的参数//getReqJSONObject();
                JSONObject paramJson = processParam(getReqJSONObject(), tradeOrder, tradeOrderItemList, userAddress, tradeOrder.getDistributionId());
                //调用管易接口，推送订单
                paramJson.put("sign", getSign(paramJson.toString()));
                String returnJson = postGY(paramJson);
                //更新订单推送状态
                TradeOrders orders = new TradeOrders();
                orders.setId(tradeOrder.getId());
                if(StringUtil.isEmpty(returnJson)){
                    orders.setPushStatus(PUSH_STATUS_PUSH_FAIL.getId());
                    orders.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
                    tradeOrdersDao.updateTradeOrders(orders);
                    LOGGER.info("================订单id："+tradeOrder.getId()+"推送管易失败=================");
                    continue;
                }
                GYResult result = JSONObject.parseObject(returnJson, GYResult.class);
                if (result.getSuccess()) {
                    orders.setGyShopCode(paramJson.getString("shop_code"));
                    orders.setGyWarehouseCode(paramJson.getString("warehouse_code"));
                    orders.setPushStatus(PUSH_STATUS_PUSH_SUCCESS.getId());
                    orders.setOrderStatus(ORDER_STATUS_PENDING_RECEIPT.getId());
                    tradeOrdersDao.updateTradeOrders(orders);
                    LOGGER.info("推送订单数据到管易sucess，更新订单表状态推送成功，待收货状态！");
                }else{
                    orders.setPushStatus(PUSH_STATUS_PUSH_FAIL.getId());
                    orders.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
                    tradeOrdersDao.updateTradeOrders(orders);
                    LOGGER.info("推送订单数据到管易false，更新订单表状态为推送失败，代发货状态！");
                }
            }
        }
    }

    @Override
    public List<TradeOrders> getOrderList() {
        TradeOrders orders = new TradeOrders();
        orders.setOrderStatus(ORDER_STATUS_PENDING_DELIVERY.getId());
        orders.setOrderYn(VALID_STATUS_YES.getId());
        orders.setIsRefund(IS_REFUND_NO.getId());
        String pushStatus = PUSH_STATUS_NOT_PUSH.getId() + "," + PUSH_STATUS_PUSH_FAIL.getId();
        orders.setPushStatus(pushStatus);
        String distributionId = DISTRIBUTE_TYPE_CITY_EXPRESS.getId() + "," + DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId();
        orders.setDistributionId(distributionId);
        return tradeOrdersDao.listTradeOrdersFromMysql(orders);
    }

    /**
     * 针对同城达或者次日达的订单，要发短信提醒商家
     *
     * @param tradeOrders
     */
    private TradeOrders sendMsg(TradeOrders tradeOrders) throws Exception {
        String orderId = tradeOrders.getId();
        if (tradeOrders.getDistributionId().equals(DISTRIBUTE_TYPE_CITY_EXPRESS.getId())) {
            //查询收货地址对应的经纬度数据
            UserAddress userAddress = userAddressDao.getUserAddressByIdFromRedis(tradeOrders.getAddressId());
            String lat = userAddress.getLat();
            String lon = userAddress.getLon();
            //查询买家的收货地址所在城市的商家
            ShopInfo shop = new ShopInfo();
            shop.setShopProvinceCode(userAddress.getProvinceCode());
            shop.setShopCityCode(userAddress.getCityCode());
            shop.setShopType(SHOP_TYPE_BUSINESS.getId());
            shop.setStatus(SHOP_STATUS_YES.getId());
            List<ShopInfo> shopDefaultList = shopInfoDao.listShopInfoFromMysql(shop);
            //利用冒泡排序计算比较出最近的兜底商家
            double distanceMin = 1000000000.00;
            Map<Double, ShopInfo> map = new HashMap();
            if (shopDefaultList != null && shopDefaultList.size() > 0) {
                for (ShopInfo shopInfo : shopDefaultList) {
                    if (shopInfo == null) {
                        LOGGER.info("orderId=" + orderId + "的订单对应的收货地址尚未配置城市对应的兜底商家。");
                        continue;
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
            LOGGER.info("订单:" + orderId + "找到同城的兜底商家" + JSONObject.toJSONString(shopInfo));

            String shopId = shopInfo.getId();
            String userId = shopInfo.getUserId();
            String gyShopCode = shopInfo.getGyShopCode();
            String gyWarehouseCode = shopInfo.getGyWarehouseCode();
            tradeOrders.setGyWarehouseCode(gyWarehouseCode);
            tradeOrders.setGyShopCode(gyShopCode);
            tradeOrders.setShopId(shopId);
            tradeOrders.setSellerId(userId);
            //更新订单的兜底商家信息。
            tradeOrdersDao.updateTradeOrders(tradeOrders);
            //给兜底商家发短信
            String mobile = shopInfo.getMobile();
            List<String> mobileList = new ArrayList<>(3);
            mobileList.add(mobile);
            //每单必须通知北京兜底店铺的强逻辑
            mobileList.add("18001215068");
            //北京要通知这两个人
            if (userAddress.getProvinceCode().equals("11")) {
                mobileList.add("18833886686");
                mobileList.add("18511929685");
            }
            List<TradeOrderItems> items = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(orderId);
            int num =0;
            for (TradeOrderItems item : items) {
                num += Integer.valueOf(item.getSkuNum());
            }
            String itemNum = String.valueOf(num);
            String orderNo = tradeOrders.getOrderNo();
            Set set = new HashSet(mobileList);
            mobileList.clear();
            mobileList.addAll(set);
            for (String mob : mobileList) {
                if (StringUtil.isNotEmpty(mobile)) {
                    LOGGER.info("短信通知兜底商家----------------------------------start,兜底商家手机号：" + mob);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("phone", mob);
                    jsonObject.put("signName", "MOTI");
                    jsonObject.put("templateCode", "SMS_171745946");
                    Map templateMap = new HashMap(6);
                    templateMap.put("distType", "同城");
                    templateMap.put("productId", orderNo);
                    templateMap.put("itemNum", itemNum);
                    templateMap.put("name", userAddress.getReceiveName());
                    templateMap.put("phone", userAddress.getReceivePhone());
                    templateMap.put("city", userAddress.getCityName());
                    String templateParam = JSONObject.toJSONString(templateMap);
                    jsonObject.put("templateParam", templateParam);
                    tradeOrderService.sendMsg(jsonObject);
                }
            }
            LOGGER.info("短信通知兜底商家------------------------------------end");
        }
        return tradeOrders;
    }
}
