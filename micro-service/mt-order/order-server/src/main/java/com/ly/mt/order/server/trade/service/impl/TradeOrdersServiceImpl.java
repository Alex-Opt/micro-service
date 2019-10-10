package com.ly.mt.order.server.trade.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.dict.OrderType;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.activity.ActivityInfo;
import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.border.OrdersBattleInfo;
import com.ly.mt.core.base.entity.buycar.BuyCar;
import com.ly.mt.core.base.entity.buycar.CarSku;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import com.ly.mt.core.base.entity.coupon.CouponModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuAttr;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.*;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import com.ly.mt.core.base.entity.user.UserAddressVo;
import com.ly.mt.core.base.entity.user.UserMH5RegistVo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.trade.component.BaseOrderComponent;
import com.ly.mt.order.server.trade.component.PriceComponent;
import com.ly.mt.order.server.trade.component.TradeOrderServiceAsync;
import com.ly.mt.order.server.trade.decorator.CouponDecorator;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;
import com.ly.mt.order.server.trade.decorator.FullReductionDecorator;
import com.ly.mt.order.server.trade.mapper.TradeOrdersServiceMapper;
import com.ly.mt.order.server.trade.service.TradeOrdersService;
import com.ly.mt.order.server.user.mapper.UserAddressMapper;
import com.ly.mt.order.server.user.service.UserProfitsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.ly.mt.core.base.dict.ActivityUserScope.ACTIVITY_ALL_CATEGORIES;
import static com.ly.mt.core.base.dict.CouponActivityType.*;
import static com.ly.mt.core.base.dict.CouponType.COUPON_TYPE_ACTIVITY;
import static com.ly.mt.core.base.dict.CouponType.COUPON_TYPE_COUPON;
import static com.ly.mt.core.base.dict.CouponUseScope.COUPON_FOR_ALL_GOODS;
import static com.ly.mt.core.base.dict.DistributeType.*;
import static com.ly.mt.core.base.dict.OrderBattleStatus.ORDER_BATTLE_STATUS_ALREADY_SEND;
import static com.ly.mt.core.base.dict.OrderCancelStatus.ORDER_CANCEL_STATUS_YES;
import static com.ly.mt.core.base.dict.PushStatus.PUSH_STATUS_NOT_PUSH;
import static com.ly.mt.core.base.dict.OrderRefundStatus.ORDER_STATUS_REFUND_YES;
import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_PAYMENT;
import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SKU;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.base.method.OrderMethodEnum.*;
import static com.ly.mt.core.base.method.TaskMethodEnum.GY_DELIVERY_INFO;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.FN_ORDER_QUERY;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.KD100_QUERY_DELIVERY;
import static com.ly.mt.core.redis.RedisKey.ENTITY_STORE_ID_REDIS;
import static com.ly.mt.core.redis.RedisKey.REDIS_GOODS_SELLER_COUNT;

/**
 * 订单主表服务层
 *
 * @author zhanglifeng
 * @date 2019-05-21
 */
@Service
public class TradeOrdersServiceImpl extends BaseServiceImpl implements TradeOrdersService {
    /**
     * 日志声明
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrdersServiceImpl.class);

    @Resource
    private TradeOrdersServiceMapper tradeOrdersServiceMapper;

    @Resource
    private DecoratorCommonServiceComponent decoratorCommonServiceComponent;

    @Resource
    private TradeOrderServiceAsync tradeOrderServiceAsync;

    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 按订单id查询出对应的一条订单信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject selectByPrimaryKey(String json) throws Exception {
        LOGGER.info("订单管理----按订单id查询出对应的一条订单信息---入参:{}", json);
        Map<String, Object> result = new HashMap<>(16);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        Long id = Long.valueOf(map.get("id"));
        TradeOrder tradeOrder = tradeOrdersServiceMapper.selectByPrimaryKey(id);
        return JSONObject.parseObject(JSONObject.toJSONString(tradeOrder));
    }

    /**
     * 按订单id更新对应的一条订单信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject updateByPrimaryKey(String json) throws Exception {
        LOGGER.info("订单管理----订单id更新对应的一条订单信息---入参:{}", json);
        Map<String, Object> result = new HashMap<>(16);
        TradeOrder tradeOrder = JSON.parseObject(json, TradeOrder.class);
        try {
            tradeOrdersServiceMapper.updateByPrimaryKey(tradeOrder);
            LOGGER.info("==========TradeOrdersServiceImpl.updateByPrimaryKey=======订单更新成功！=======================");
            result.put("flag", true);
            return JsonUtil.getSuccessJson(result);
        } catch (Exception e) {
            LOGGER.error("=========TradeOrdersServiceImpl.updateByPrimaryKey====执行更新sql异常，{}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    /**
     * 查询订单列表（分页）
     * 1.根据用户id查询出订单表数据。【获取到订单编号，订单状态，商品总件数要统计明细中的个数。订单应付总额】
     * 2.根据订单主表id查询订单明细表查询商品信息。可以得到【商品名，商品原始单价，商品购买数量，缺少商品图片地址。】
     * 3.根据商品的sku_id查询商品表，得到商品的url.补全上一步信息。
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject queryOrderListByUserId(String json) throws Exception {
        LOGGER.info("订单管理----查询订单列表---入参:{}", json);
        TradeOrder trade = JSON.parseObject(json, TradeOrder.class);
        List<TradeOrderVo> tradeOrderVos = new ArrayList<>(1);
        Map<String, Object> result = new HashMap<>(16);
        //1.根据用户id查询出订单表数据。
        PageHelper.startPage(trade.getPage(), trade.getRows());
        String loginUserId = getLoginUserId(json);
        LOGGER.info("获取当前登陆人的信息。loginUserId：" + loginUserId);
        if (StringUtil.isEmpty(loginUserId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        List<TradeOrder> tradeOrders = tradeOrdersServiceMapper.queryListByUserId(Long.parseLong(getLoginUserId(json)), null);
        if (null == tradeOrders) {
            result.put("total", 0);
            result.put("rows", null);
            return JsonUtil.getSuccessJson(result);
        }
        PageInfo<TradeOrder> pageInfo = new PageInfo<>(tradeOrders);
        TradeOrderVo tradeOrderVo;
        for (TradeOrder tradeOrder : tradeOrders) {
            String id = tradeOrder.getId();
            //区分订单是否为一小时达，一小时达的订单状态为抢单表的状态。替换订单表的状态
            if (tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_ONE_HOUR.getId())) {
                OrdersBattleInfo orderBattle = tradeOrdersServiceMapper.getOrderBattleByOrderId(Long.parseLong(id));
                if (orderBattle != null) {
                    tradeOrder.setOrderStatus(orderBattle.getStatus());
                }
            }
            tradeOrderVo = new TradeOrderVo();
            BeanUtils.copyProperties(tradeOrder, tradeOrderVo);
            List<TradeOrderItemVo> tradeOrderItemVos = getOrderSkuDetailByOrderId(id);
            tradeOrderVo.setTradeOrderItemVos(tradeOrderItemVos);
            tradeOrderVos.add(tradeOrderVo);
        }
        result.put("total", pageInfo.getTotal());
        result.put("rows", tradeOrderVos);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public Integer queryOrderByUserId(String userId) throws Exception {
        List<TradeOrder> tradeOrders = tradeOrdersServiceMapper.queryListByUserId(Long.parseLong(userId), 1);
        if (tradeOrders == null || tradeOrders.size() < 1) {
            return 0;
        }
        return tradeOrders.size();
    }

    /**
     * 根据订单id查询订单下商品明细
     *
     * @param id 订单id
     * @return
     */
    private List<TradeOrderItemVo> getOrderSkuDetailByOrderId(String id) {
        //2.根据订单主表id查询订单明细表查询商品信息。
        List<TradeOrderItem> tradeOrderItems = tradeOrdersServiceMapper.getTradeOrderItemByOrderId(Long.parseLong(id));
        List<TradeOrderItemVo> tradeOrderItemVos = new ArrayList<>(tradeOrderItems.size());
        //3.根据商品的sku_id查询商品表 ，至于为啥不用联表查询来做，有一下两个原因
        // 3.1订单明细表是个大表，如果通过联表商品表，效率还不如再来一次循环快。所以还是分步骤条件查询效率更高
        //3.2 未来商品表会存在缓存中。走库的可能变小。
        TradeOrderItemVo tradeOrderItemVo = null;
        for (TradeOrderItem tradeOrderItem : tradeOrderItems) {
            tradeOrderItemVo = new TradeOrderItemVo();
            BeanUtils.copyProperties(tradeOrderItem, tradeOrderItemVo);
            String skuId = tradeOrderItem.getSkuId();
            //统计商品的月销量
            String sellerNumber = tradeOrdersServiceMapper.getSkuMonthSellerNumber(Long.parseLong(skuId), DateUtil.earlyMonthStr(), DateUtil.getNowTimeStr());
            tradeOrderItemVo.setSkuMonthSellerNumber(sellerNumber);
            //商品图片连接
            String pictureUrl = tradeOrdersServiceMapper.getPictureUrlBySkuId(Long.parseLong(skuId));
            if (StringUtil.isEmpty(pictureUrl)) {
                pictureUrl = PICTURE_PLACEHOLDER_SKU.getId();
            }
            //根据skuId查询商品属性
            GoodsSkuAttr goodsSkuAttr = tradeOrdersServiceMapper.selectSkuAttr(Long.parseLong(skuId));
            if (goodsSkuAttr != null && goodsSkuAttr.getAttrId()!=null) {
                String[] strings = goodsSkuAttr.getAttrId().split(":");
                List<Long> attrIdList = new ArrayList<>();
                if (strings != null && strings.length > 0) {
                    for (String s : strings) {
                        attrIdList.add(Long.parseLong(s));
                    }
                    List<Map> maps = tradeOrdersServiceMapper.selectGoodsAttrValueListByAttrId(attrIdList);
                    tradeOrderItemVo.setGoodsAttrValues(maps);
                }
            }else{
                tradeOrderItemVo.setGoodsAttrValues(null);
            }
            tradeOrderItemVo.setPictureUrl(pictureUrl);
            tradeOrderItemVos.add(tradeOrderItemVo);
        }
        return tradeOrderItemVos;
    }

    /**
     * 查询单个订单详情
     * 订单详情包含几个部分：
     * 1.快递信息：这个页面单独调用查询订单收货信息
     * 2.收货地址信息：根据id查询收货信息，已有。
     * 3.购买商品列表信息：根据订单id获取订单的商品列表信息。已有封装好的一个私有方法：getOrderSkuDetailByOrderId
     * 4.优惠价格（可能同时享受多个优惠，这个需要第五步），邮费（freight），订单总价-商品总价（order_old_money），实付款（order_money）：查询订单主表，已有
     * 5.根据订单号查询订单享受优惠活动列表。如果优惠类型为2.则不用查表知道为优惠券。如果是1.则需差优惠活动表的优惠活动类型。
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject queryOrderDetailByOrderId(String json) throws Exception {
        LOGGER.info("订单管理----按订单id查询出对应的订单详情信息---入参:{}", json);
        Map<String, Object> result = new HashMap<>(16);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        String orderId = map.get("id");
        String userId = map.get("userId");
        LOGGER.info("查询订单明细获取的用户id:" + userId);
        //4.优惠价格-查询订单主表
        TradeOrder tradeOrder = tradeOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(orderId));
        //区分订单是否为一小时达，一小时达的订单状态为抢单表的状态。替换订单表的状态
        if (tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_ONE_HOUR.getId())) {
            OrdersBattleInfo orderBattle = tradeOrdersServiceMapper.getOrderBattleByOrderId(Long.parseLong(orderId));
            if (orderBattle != null) {
                tradeOrder.setOrderStatus(orderBattle.getStatus());
            }
        }
        String addressId = tradeOrder.getAddressId();
        //2.收货地址信息：根据id查询收货信息
        LOGGER.info("地址id:" + addressId);
        UserAddress userAddress = tradeOrdersServiceMapper.selectByAddressId(Long.parseLong(addressId));
        //3.购买商品列表信息：根据订单id获取订单的商品列表信息
        List<TradeOrderItemVo> tradeOrderItemVos = getOrderSkuDetailByOrderId(orderId);
        //5.根据订单号查询订单享受优惠活动列表
        List<TradeOrderCoupon> tradeOrderCoupons = tradeOrdersServiceMapper.selectCouponInfoByOrderId(Long.parseLong(orderId));
        List<TradeOrderCouponVo> tradeOrderCouponVos = null;
        if (tradeOrderCoupons != null && tradeOrderCoupons.size() > 0) {
            tradeOrderCouponVos = new ArrayList<>(tradeOrderCoupons.size());
            TradeOrderCouponVo tradeOrderCouponVo = null;
            for (TradeOrderCoupon tradeOrderCoupon : tradeOrderCoupons) {
                tradeOrderCouponVo = new TradeOrderCouponVo();
                BeanUtils.copyProperties(tradeOrderCoupon, tradeOrderCouponVo);
                if (COUPON_TYPE_ACTIVITY.getId().equals(tradeOrderCoupon.getCouponType())) {
                    ActivityInfo activity = tradeOrdersServiceMapper.getActivityInfoById(Long.parseLong(tradeOrderCoupon.getCouponActivityId()));
                    String couponType = activity.getCouponActivityType();
                    //通过优惠类型code，得到优惠类型名称
                    String activityName = null;
                    if (COUPON_ACTIVITY_TYPE_FALL.getId().equals(couponType)) {
                        activityName = COUPON_ACTIVITY_TYPE_FALL.getName();
                    } else if (COUPON_ACTIVITY_TYPE_FULL_REDUCTION.getId().equals(couponType)) {
                        activityName = COUPON_ACTIVITY_TYPE_FULL_REDUCTION.getName();
                    } else if (COUPON_ACTIVITY_TYPE_SECOND_GRAB.getId().equals(couponType)) {
                        activityName = COUPON_ACTIVITY_TYPE_SECOND_GRAB.getName();
                    } else if (COUPON_ACTIVITY_TYPE_DISCOUNT.getId().equals(couponType)) {
                        activityName = COUPON_ACTIVITY_TYPE_DISCOUNT.getName();
                    } else {
                        activityName = COUPON_ACTIVITY_TYPE_GROUP_BUY.getName();
                    }
                    tradeOrderCouponVo.setCouponName(activityName);
                } else {
                    tradeOrderCouponVo.setCouponName(COUPON_ACTIVITY_TYPE_COUPON.getName());
                }
                tradeOrderCouponVos.add(tradeOrderCouponVo);
            }
        }
        TradeOrderVo orderVo = new TradeOrderVo();
        BeanUtils.copyProperties(tradeOrder, orderVo);
        //拼装地址信息
        orderVo.setAddress(userAddress);
        //拼装首单标志
        int size = queryOrderByUserId(userId);
        if (size > 1) {
            orderVo.setFirstFlag("false");
        } else {
            orderVo.setFirstFlag("true");
        }
        //拼装商品信息
        orderVo.setTradeOrderItemVos(tradeOrderItemVos);
        //拼装优惠信息
        orderVo.setTradeOrderCouponVos(tradeOrderCouponVos);
        return JsonUtil.getSuccessJson(orderVo);
    }


    /**
     * 用户下单前展示的预订单信息查询。
     * 主要是订单原始金额的查询，和促销优惠活动，优惠券的查询。
     * 其中促销优惠活动的
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject queryPreOrderInfo(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String itemListTemp = jsonObject.getString("itemList");
        String userId = jsonObject.getString("userId");
        List<GoodsSkuModel> itemList = JSONObject.parseArray(itemListTemp, GoodsSkuModel.class);
        for (GoodsSkuModel goodsSkuModel : itemList) {
            if (StringUtil.isEmpty(goodsSkuModel.getSkuId()) || StringUtil.isEmpty(goodsSkuModel.getSkuNum()) || StringUtil.isEmpty(goodsSkuModel.getSpuId())) {
                return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
            }
        }
        PriceComponent baseOrderComponent = new BaseOrderComponent();
        baseOrderComponent = new FullReductionDecorator(baseOrderComponent);
        baseOrderComponent = new CouponDecorator(baseOrderComponent);
        OrderModel orderModel = baseOrderComponent.calcOrderPrice(itemList, decoratorCommonServiceComponent, userId);
        //加上是不是首单的判断
        int size = queryOrderByUserId(userId);
        if (size == 0) {
            orderModel.setFirstOrder("true");
        } else {
            orderModel.setFirstOrder("false");
        }

        return JsonUtil.getSuccessJson(orderModel);
    }

    /**
     * 根据快递的算法得到快递的费用
     * 一小时达等配送费现在都是下面的规则：
     * 根据【订单金额 - 满减优惠 - 优惠券（红包）】 的价格计算配送费
     * 购买金额在0-34元：16元
     * 购买金额在35-99元时：9元
     * 购买金额在100-150元时：5元
     * 购买金额在151元以上时：0元
     * 首先免运费
     *
     * @param distributeId
     * @return
     */
    @Override
    public String getFeignByDistributeId(String distributeId, String money, String userId) throws Exception {
        int size = queryOrderByUserId(userId);
        if (0 == size) {
            return "0";
        }
        if (new BigDecimal(money).compareTo(new BigDecimal(34)) < 0) {
            return "16";
        } else if (new BigDecimal(money).compareTo(new BigDecimal(34)) >= 0 && new BigDecimal(money).compareTo(new BigDecimal(99)) < 0) {
            return "9";
        } else if (new BigDecimal(money).compareTo(new BigDecimal(99)) >= 0 && new BigDecimal(money).compareTo(new BigDecimal(150)) < 0) {
            return "5";
        } else {
            return "0";
        }

    }

    /**
     * 订单生成的方法
     * 入参：
     * 1.选中商品列表List<skuId,spu_id,sku_num>这三个数据的集合，
     * 2.优惠券id的集合List<coupon_id>,
     * 3.促销活动id集合List<activity_id>,
     * 4.邮费id,
     * 5.选中的地址id，
     * 6.订单备注
     * 订单生成逻辑处理：
     * 如果存在优惠活动。则先得到优惠活动的实体。【先使用促销活动，在使用优惠券。目前的促销活动仅有满减。所以先不考虑促销活动多个时的优先级问题。】
     * 涉及到的操作有：
     * a1.新增信息到订单主表（trade_orders）
     * a2.新增信息到订单明细表（trade_order_items）
     * a3.更新用户的优惠券的使用状态。coupon_code（优惠券码表）
     * a4.trade_order_coupon
     * a5.activity_user_grade_detail(进异步方法)
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject orderGenerate(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String userId = jsonObject.getString("userId");
        String itemListTemp = jsonObject.get("itemList").toString();
        List<GoodsSkuModel> itemList = JSONObject.parseArray(itemListTemp, GoodsSkuModel.class);
        PriceComponent baseOrderComponent = new BaseOrderComponent();
        //得到订单原始总价
        OrderModel orderModel = baseOrderComponent.calcOrderPrice(itemList, decoratorCommonServiceComponent, userId);
        orderModel = putParamIntoOrderModel(orderModel, jsonObject);
        //优先考虑促销活动-满减。未来如果添加别的促销活动，就要改动这部分了。所以我把有变动的地方单独提出去。未来在这个方法里更改。
        orderModel = getActivityDiscountMoney(orderModel);
        //促销活动结束后是优惠券的价钱计算和封装
        orderModel = getCouponPromotionMoney(orderModel);
        //注意：订单的原始总价只是商品价格的和。不包含运费。运费的累加算到实付金额里。
        String orderMoney = orderModel.getOrderVo().getOrderMoney();
        //根据快递方式id查询对应的快递费用，封装到orderModel数据模型中
        String distributionId = orderModel.getOrderVo().getDistributionId();
        String freight = getFeignByDistributeId(distributionId, orderMoney, userId);
        orderModel.getOrderVo().setFreight(freight);
        BigDecimal om = new BigDecimal(orderMoney).add(new BigDecimal(freight));
        orderModel.getOrderVo().setOrderMoney(om.toString());
        //首先是订单主表-trade_orders.因为时付款前先生成订单。所以状态为待付款。
        TradeOrder tradeOrder = getTradeOrderBean(orderModel);
        //订单明细表，为订单中的商品信息和分摊价格信息。
        List<TradeOrderItem> tradeOrderItemList = getTradeOrderItemBean(orderModel, tradeOrder.getId());
        //订单使用优惠券，促销活动的信息记录
        List<TradeOrderCoupon> tradeOrderCouponList = getTradeOrderCouponBean(orderModel, tradeOrder.getId());
        //订单的来源，渠道等信息记录。为保持统一，需要记录到订单媒体表。
        TradeOrderMedia tradeOrderMedia = getTradeOrderMedia(tradeOrder);
        //写表操作要在一个事务内。
        boolean flag = saveTradeOrderInfo(tradeOrder, tradeOrderItemList, tradeOrderCouponList,tradeOrderMedia);
        if (flag) {
            tradeOrderServiceAsync.recordCouponAndActivityUsedHistory(orderModel);
        }
        String yearMonthStr = DateUtil.thisYearMonthStr();
        //每次订单生成对应各个商品月销量都会增加。
        String spuId;
        String skuNum;
        for (GoodsSkuModel goodsSkuModel : itemList) {
            spuId = goodsSkuModel.getSpuId();
            skuNum = goodsSkuModel.getSkuNum();
            // TODO 线程池是多线程安全的，测试时注意
            redisServer.set(REDIS_GOODS_SELLER_COUNT, yearMonthStr + "_" + spuId, skuNum);
        }
        Map map = new ConcurrentHashMap(3);
        map.put("id", tradeOrder.getId());
        map.put("orderNo", tradeOrder.getOrderNo());
        return JsonUtil.getSuccessJson(map);
    }


    /**
     * 通过活动页面下单,配送方式为货到付款
     * 入参：
     * 1.用户名
     * 2.用户收获地址
     * 3.用户手机号码
     * 4,商品skuId
     * 5购买商品的数量(skunum)
     * 6订单来源 >1000
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject generatePagePromotionOrder(String json) throws Exception {
        JSONObject obj = JSONObject.parseObject(json);
        String pagePromotionOrderStr = obj.get("pageOrder").toString();
        PageOrderVo vo = JSONObject.parseObject(pagePromotionOrderStr, PageOrderVo.class);
        String address = vo.getAddress();
        //下单
        //将地址信息存入地址表
        UserAddress userAddress = new UserAddress();
        String addressId;
        String currId = getLoginUserId(json);
        if ("null".equals(currId)) {
            UserMH5RegistVo search = new UserMH5RegistVo();
            search.setMobile(vo.getMobile());
            currId = tradeOrdersServiceMapper.getUser(search).getId();
        }
        userAddress.setUserId(currId);
        userAddress.setUserName(vo.getUserName());
        userAddress.setReceiveName(vo.getUserName());
        userAddress.setReceivePhone(vo.getMobile());
        userAddress.setCreateTime(DateUtil.getNowTimeStr());
        userAddress.setUserAddress(address);
        //查询
        List<UserAddressVo> address1 = tradeOrdersServiceMapper.getAddress(Long.parseLong(currId));
        if (address1 == null || address1.size() == 0) {
            addressId = SnowflakeUtil.getPrimaryKey(USER_ADDRESS);
            userAddress.setId(addressId);
            tradeOrdersServiceMapper.insertAddress(userAddress);

        } else {
            addressId = address1.get(0).getId();
            userAddress.setId(address1.get(0).getId());
            userAddress.setModifyTime(DateUtil.getNowTimeStr());
            tradeOrdersServiceMapper.updateAddress(userAddress);
        }

        //封装下单商品集合信息
        List<GoodsSkuModel> goodsSkuModels = new ArrayList<>();
        GoodsSkuModel goodsSkuModel = new GoodsSkuModel();
        GoodsSkuInfo skuInfo = decoratorCommonServiceComponent.getGoodsSkuService().getGoodsSkuInfoBySkuId(Long.valueOf(vo.getSkuId()));
        goodsSkuModel.setSpuId(skuInfo.getSpuId());
        goodsSkuModel.setSkuId(vo.getSkuId());
        goodsSkuModel.setSkuNum(vo.getSkuNum());
        goodsSkuModels.add(goodsSkuModel);
        //得到订单原始总价
        BigDecimal originPrice = (new BigDecimal(skuInfo.getMarketPrice()).multiply(new BigDecimal(vo.getSkuNum())));
        String orderPrice = originPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        //生成订单
        try {
            LOGGER.info("====================活动订单下单开始========================");
            TradeOrder tradeOrder = new TradeOrder();
            String orderId = SnowflakeUtil.getPrimaryKey(TRADE_ORDERS);
            String orderNo = SnowflakeUtil.getPrimaryKey(ORDER_NO);
            tradeOrder.setId(orderId);
            tradeOrder.setOrderNo(orderNo);
            tradeOrder.setBuyerId(currId);
            tradeOrder.setAddressId(addressId);
            tradeOrder.setOrderMoney(orderPrice);
            tradeOrder.setOrderOldMoney(orderPrice);
            tradeOrder.setCreateTime(DateUtil.getNowTimeStr());
            tradeOrder.setDistributionId("4");
            tradeOrder.setOrderType(OrderType.ORDER_TYPE_ACTIVITY_ORDER.getId());
            int insertRet = tradeOrdersServiceMapper.insertActivityOrder(tradeOrder);
            Map map = new ConcurrentHashMap(3);
            if (insertRet > 0) {
                //插入明细表
                TradeOrderItem item = new TradeOrderItem();
                String itemId = SnowflakeUtil.getPrimaryKey(TRADE_ORDER_ITEMS);
                item.setId(itemId);
                item.setSkuId(vo.getSkuId());
                item.setSkuNum(vo.getSkuNum());
                item.setCreateTime(DateUtil.getNowTimeStr());
                item.setSkuPrice(skuInfo.getMarketPrice());
                item.setSpuId(skuInfo.getSpuId());
                TradeOrderItem tradeOrderItemDo = tradeOrdersServiceMapper.
                        selectSpuNameBySpuId(Long.parseLong(skuInfo.getSpuId()));
                item.setSkuName(skuInfo.getName());
                item.setSpuName(tradeOrderItemDo.getSpuName());
                item.setOrderId(orderId);
                tradeOrdersServiceMapper.insertOrderItem(item);
                map.put("id", orderId);
                map.put("orderNo", orderNo);
                LOGGER.info("========================活动订单下单完成============================");
            }
            return JsonUtil.getSuccessJson(map);
        } catch (Exception e) {
            LOGGER.error("===============================活动下单生成异常===========================");
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }

    }

    /**
     * 这里把快递，地址，优惠券，促销活动信息封装到参数模型里。
     *
     * @param orderModel
     * @param jsonObject
     * @return
     */
    private OrderModel putParamIntoOrderModel(OrderModel orderModel, JSONObject jsonObject) {
        //从入参中得到快递，地址,订单来源等信息
        String orderVoTemp = jsonObject.get("orderVo").toString();
        OrderVo orderVo = JSONObject.parseObject(orderVoTemp, OrderVo.class);
        orderModel.getOrderVo().setDistributionId(orderVo.getDistributionId());
        orderModel.getOrderVo().setAddressId(orderVo.getAddressId());
        orderModel.getOrderVo().setOrderSource(orderVo.getOrderSource());
        if(StringUtil.isNotEmpty(orderVo.getMaterial())){
            orderModel.getOrderVo().setMaterial(orderVo.getMaterial());
        }
        if(StringUtil.isNotEmpty(orderVo.getChannel())){
            orderModel.getOrderVo().setChannel(orderVo.getChannel());
        }
        //从入参中得到优惠券信息
        Object couponListTemp = jsonObject.get("couponList");
        if (couponListTemp != null && StringUtils.isNotEmpty(couponListTemp.toString())) {
            List<CouponModel> couponModels = JSONObject.parseArray(couponListTemp.toString(), CouponModel.class);
            orderModel.setCouponList(couponModels);
        }
        //从入参中得到促销活动信息
        Object activityListTemp = jsonObject.get("activityList");
        if (activityListTemp != null && StringUtils.isNotEmpty(activityListTemp.toString())) {
            List<ActivityModel> activityModels = JSONObject.parseArray(activityListTemp.toString(), ActivityModel.class);
            orderModel.setActivityList(activityModels);
        }
        return orderModel;
    }


    /**
     * 事物处理写表操作：订单主表，订单明细表，订单使用优惠信息表，以及未来更新的库存信息。
     *
     * @param tradeOrder
     * @param tradeOrderItemList
     * @param tradeOrderCouponList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean saveTradeOrderInfo(TradeOrder tradeOrder, List<TradeOrderItem> tradeOrderItemList, List<TradeOrderCoupon> tradeOrderCouponList, TradeOrderMedia tradeOrderMedia) throws Exception {
        try {
            tradeOrdersServiceMapper.insert(tradeOrder);
            tradeOrdersServiceMapper.batchInsertOrderItems(tradeOrderItemList);
            if (tradeOrderCouponList != null && tradeOrderCouponList.size() > 0) {
                tradeOrdersServiceMapper.batchInsertTradeOrderCoupon(tradeOrderCouponList);
            }
            if(tradeOrderMedia != null){
                tradeOrdersServiceMapper.saveTradeMediaInfo(tradeOrderMedia);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("订单生成写表出现异常！", e.getMessage(), e);
            throw new Exception();
        }
    }

    /**
     * 根据已经封装好的订单信息中抽取订单媒体信息。
     * @param tradeOrder
     * @return
     */
    private TradeOrderMedia getTradeOrderMedia(TradeOrder tradeOrder){
        String orderSource = tradeOrder.getOrderSource();
        String material = tradeOrder.getMaterial();
        String channel = tradeOrder.getChannel();
        //只有订单信息存在媒体信息时，才会保存一条进订单媒体中。
        if(StringUtil.isNotEmpty(orderSource) && StringUtil.isNotEmpty(material)&& StringUtil.isNotEmpty(channel)){
            TradeOrderMedia tradeOrderMedia = new TradeOrderMedia();
            tradeOrderMedia.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_TRADE_ORDER_MEDIA));
            tradeOrderMedia.setChannel(channel);
            tradeOrderMedia.setMaterial(material);
            tradeOrderMedia.setOrderId(tradeOrder.getId());
            tradeOrderMedia.setCreateTime(DateUtil.getNowTimeStr());
            tradeOrderMedia.setType(tradeOrder.getOrderSource());
            return tradeOrderMedia;
        }
        return null;
    }


    /**
     * 封装订单使用优惠实体
     *
     * @param orderModel
     * @return
     */
    private List<TradeOrderCoupon> getTradeOrderCouponBean(OrderModel orderModel, String orderId) {
        List<TradeOrderCoupon> traderOrderCouponList = orderModel.getTraderOrderCouponList();
        if (traderOrderCouponList != null && traderOrderCouponList.size() > 0) {
            for (TradeOrderCoupon tradeOrderCoupon : traderOrderCouponList) {
                String id = SnowflakeUtil.getPrimaryKey(TRADE_ORDER_COUPON);
                tradeOrderCoupon.setId(id);
                tradeOrderCoupon.setOrderId(orderId);
                tradeOrderCoupon.setUseTime(DateUtil.getNowTimeStr());
            }
        }
        return traderOrderCouponList;
    }


    /**
     * 封装订单主表实体
     *
     * @param orderModel
     * @return
     */
    private TradeOrder getTradeOrderBean(OrderModel orderModel) throws Exception {
        TradeOrder tradeOrder = new TradeOrder();
        BeanUtils.copyProperties(orderModel.getOrderVo(), tradeOrder);
        //生成订单号
        String id = SnowflakeUtil.getPrimaryKey(TRADE_ORDERS);
        String orderNo = SnowflakeUtil.getPrimaryKey(ORDER_NO);
        tradeOrder.setId(id);
        tradeOrder.setOrderNo(orderNo);
        tradeOrder.setCreateTime(DateUtil.getNowTimeStr());
        tradeOrder.setOrderStatus(ORDER_STATUS_PENDING_PAYMENT.getId());
        tradeOrder.setPushStatus(PUSH_STATUS_NOT_PUSH.getId());
        tradeOrder.setOrderType(OrderType.ORDER_TYPE_ORDINARY_ORDER.getId());
        //订单 自动收货时间 应和选择的快递类型有关。一小时达的自动收货设置为一天。次日达的设置为四天，普通快递的设置为一星期。
        String distributionId = tradeOrder.getDistributionId();
        String autoReceiveTime = null;
        String deliveryTime = null;
        if (DISTRIBUTE_TYPE_ONE_HOUR.equals(distributionId)) {
            autoReceiveTime = DateUtil.getAfterTimeDate(1 * 24);
            deliveryTime = DateUtil.getAfterMinutesDateFromPointTime(10, DateUtil.getNowTimeStr());
        } else if (DISTRIBUTE_TYPE_CITY_EXPRESS.equals(distributionId)) {
            autoReceiveTime = DateUtil.getAfterTimeDate(7 * 24);
            deliveryTime = DateUtil.getAfterTimeDate(2 * 24);
        } else {
            autoReceiveTime = DateUtil.getAfterTimeDate(14 * 24);
            deliveryTime = DateUtil.getAfterTimeDate(3 * 24);
        }
        tradeOrder.setOrderYn("1");
        tradeOrder.setIsRefund("1");
        tradeOrder.setLocked("1");
        tradeOrder.setDeliveryTime(deliveryTime);
        tradeOrder.setAutoReceiveTime(autoReceiveTime);
        //自动取消时间，设置为从下订单后的半个小时。如果不支付就自动取消。
        String autoCancelTime = DateUtil.getAfterMinutesDateFromPointTime(30, DateUtil.getNowTimeStr());
        tradeOrder.setAutoCancelTime(autoCancelTime);
        return tradeOrder;
    }

    /**
     * 获取订单明细实体
     *
     * @param orderModel
     * @return
     */
    private List<TradeOrderItem> getTradeOrderItemBean(OrderModel orderModel, String orderId) {
        List<GoodsSkuModel> itemList = orderModel.getItemList();
        List<TradeOrderItem> orderItems = new ArrayList<>(itemList.size());
        OrderVo orderVo = orderModel.getOrderVo();
        //订单共享优惠金额
        BigDecimal sharingMoney = new BigDecimal(orderVo.getSharingDiscountMoney() == null ? "0" : orderVo.getSharingDiscountMoney());
        //订单总优惠
        BigDecimal discountMoney = new BigDecimal(orderVo.getOrderDiscountMoney() == null ? "0" : orderVo.getOrderDiscountMoney());
        //订单原始金额，也即是商品总价
        BigDecimal orderOldMoney = new BigDecimal(orderVo.getOrderOldMoney());
        //订单实付金额
        BigDecimal orderMoney = new BigDecimal(orderVo.getOrderMoney());
        //总运费
        BigDecimal freight = new BigDecimal(orderVo.getFreight() == null ? "0" : orderVo.getFreight());
        TradeOrderItem tradeOrderItem = null;
        BigDecimal singleFreightTemp = BigDecimal.ZERO;
        BigDecimal singleDiscountMoneyTemp = BigDecimal.ZERO;
        BigDecimal paymentPriceTemp = BigDecimal.ZERO;
        for (int i = 0; i < itemList.size(); i++) {
            GoodsSkuModel goodsSkuModel = itemList.get(i);
            tradeOrderItem = new TradeOrderItem();
            BeanUtils.copyProperties(goodsSkuModel, tradeOrderItem);
            String id = SnowflakeUtil.getPrimaryKey(TRADE_ORDER_ITEMS);
            tradeOrderItem.setId(id);
            tradeOrderItem.setOrderId(orderId);
            String skuNum = goodsSkuModel.getSkuNum();
            String skuPrice = goodsSkuModel.getSkuPrice();
            if (i < itemList.size() - 1) {
                //得到单个商品的总价
                BigDecimal singleGoodsTotalMoney = new BigDecimal(skuNum).multiply(new BigDecimal(skuPrice));
                //得到该商品在订单中的份额占比,精确到小数点3位。
                BigDecimal singleGoodsRatio = singleGoodsTotalMoney.divide(orderOldMoney, 3, BigDecimal.ROUND_HALF_UP);
                //该商品分担的运费
                BigDecimal singleGoodsFreight = freight.multiply(singleGoodsRatio).setScale(2, BigDecimal.ROUND_HALF_UP);
                singleFreightTemp = singleFreightTemp.add(singleGoodsFreight);
                tradeOrderItem.setFreightPrice(singleGoodsFreight.toString());
                //该商品分担的共享优惠
                BigDecimal singleDiscountMoney = sharingMoney.multiply(singleGoodsRatio).setScale(2, BigDecimal.ROUND_HALF_UP);
                //总优惠=分摊的共享优惠+商品专属优惠
                BigDecimal singleTotalDiscountMoney = singleDiscountMoney.add(new BigDecimal(goodsSkuModel.getDiscountApportion() == null ? "0" : goodsSkuModel.getDiscountApportion()));
                tradeOrderItem.setPomotionPrice(singleTotalDiscountMoney.toString());
                singleDiscountMoneyTemp = singleDiscountMoneyTemp.add(singleTotalDiscountMoney);
                //该商品实付金额=该商品总价+分摊运费-总优惠
                BigDecimal paymentPrice = singleGoodsTotalMoney.subtract(singleTotalDiscountMoney).add(singleGoodsFreight);
                paymentPriceTemp = paymentPriceTemp.add(paymentPrice);
                tradeOrderItem.setPaymentPrice(paymentPrice.toString());
            }
            //最后一个商品特殊处理。用总得减去累加的。
            if (i == itemList.size() - 1) {
                BigDecimal singleTotalDiscountMoney = discountMoney.subtract(singleDiscountMoneyTemp);
                tradeOrderItem.setPomotionPrice(singleTotalDiscountMoney.toString());
                tradeOrderItem.setFreightPrice(freight.subtract(singleFreightTemp).toString());
                tradeOrderItem.setPaymentPrice(orderMoney.subtract(paymentPriceTemp).toString());
            }
            tradeOrderItem.setCreateTime(DateUtil.getNowTimeStr());
            orderItems.add(tradeOrderItem);
        }

        return orderItems;
    }


    /**
     * 把优惠券的信息封装到订单数据模型中，以供后续写表和计算
     *
     * @param orderModel
     * @return
     * @date 2019-06-09 增加逻辑:促销活动非全品类的先处理分摊到各个商品sku上。
     */
    private OrderModel getCouponPromotionMoney(OrderModel orderModel) {
        List<CouponModel> couponList = orderModel.getCouponList();
        List<TradeOrderCoupon> traderOrderCouponList = orderModel.getTraderOrderCouponList();
        String sharingDiscountMoney = orderModel.getOrderVo().getSharingDiscountMoney() == null ? "0" : orderModel.getOrderVo().getSharingDiscountMoney();
        BigDecimal sharingDiscountTemp = new BigDecimal(sharingDiscountMoney);
        if (couponList != null && couponList.size() > 0) {
            int i = 0;
            if (traderOrderCouponList == null) {
                traderOrderCouponList = new ArrayList<>(couponList.size());
            }
            TradeOrderCoupon tradeOrderCoupon = null;
            //得到订单减去促销活动后的实付金额。
            String orderMoney = orderModel.getOrderVo().getOrderMoney();
            for (CouponModel couponModel : couponList) {
                String couponId = couponModel.getCouponId();
                CouponInfo couponInfo = tradeOrdersServiceMapper.selectCouponInfoByPrimaryKey(Long.valueOf(couponId));
                BeanUtils.copyProperties(couponInfo, couponModel);
                couponModel.setCouponId(couponId);
                couponList.set(i, couponModel);
                i++;
                String denomination = couponInfo.getDenomination();
                String discountRate = couponInfo.getDiscountRate();
                LOGGER.info("订单使用的优惠券信息---------couponId="+couponId+",denomination="+denomination+",discountRate="+discountRate);
                tradeOrderCoupon = new TradeOrderCoupon();
                tradeOrderCoupon.setDiscountRate(discountRate);
                tradeOrderCoupon.setCouponActivityId(couponId);
                tradeOrderCoupon.setCouponType(COUPON_TYPE_COUPON.getId());
                tradeOrderCoupon.setDiscountPrice(denomination);
                traderOrderCouponList.add(tradeOrderCoupon);
                //每张优惠券对应的优惠总金额
                BigDecimal couponDiscount = denomination == null ? new BigDecimal(orderMoney).multiply(new BigDecimal(discountRate)).setScale(2, BigDecimal.ROUND_HALF_UP) : new BigDecimal(denomination);
                LOGGER.info("denomination == null的值为："+(denomination == null)+"经过计算后的优惠券折扣金额为：couponDiscount="+couponDiscount);
                if (COUPON_FOR_ALL_GOODS.getId().equals(couponInfo.getLimitType())) {
                    //优惠券针对全品类，则应该将优惠的钱算到共同分摊的金额里
                    sharingDiscountTemp = new BigDecimal(sharingDiscountMoney).add(couponDiscount);
                } else {
                    HashSet hSet = getSpuIdList(orderModel);
                    List<String> spuIdList = tradeOrdersServiceMapper.getSpuIdsByCouponIdAndSpuIds(Long.valueOf(couponId), new ArrayList(hSet));
                    calculatingSkuDiscount(orderModel, spuIdList, couponDiscount);
                }
                calculatingMoney(orderModel, denomination, discountRate);
            }
        }
        orderModel.setTraderOrderCouponList(traderOrderCouponList);
        orderModel.getOrderVo().setSharingDiscountMoney(sharingDiscountTemp.toString());
        return orderModel;
    }

    public void calculatingSkuDiscount(OrderModel orderModel, List<String> spuIdList, BigDecimal discount) {
        List<GoodsSkuModel> itemList = orderModel.getItemList();
        if (spuIdList != null && spuIdList.size() > 0) {
            //将享受到优惠券的商品上记录优惠的价钱,分为两步。先统计订单下有几类skuId的类数量：sharingSkuNum.再将spuId对应的优惠根据各自的总价钱占比分摊到每类skuId上。
            int sharingSkuNum = 0;
            //符合非全品类商品的累计总金额
            BigDecimal skuTotalMoney=BigDecimal.ZERO;
            for (GoodsSkuModel goodsSkuModel : itemList) {
                if (spuIdList.contains(goodsSkuModel.getSpuId())) {
                    sharingSkuNum++;
                    BigDecimal skuMoney = new BigDecimal(goodsSkuModel.getSkuNum()).multiply(new BigDecimal(goodsSkuModel.getSkuPrice()));
                    skuTotalMoney =skuTotalMoney.add(skuMoney);
                }
            }
            int sharingSkuNumTemp = sharingSkuNum;
            BigDecimal goodsDiscountApportionTemp = BigDecimal.ZERO;
            for (GoodsSkuModel goodsSkuModel : itemList) {
                if (spuIdList.contains(goodsSkuModel.getSpuId())) {
                    //该商品原有的分摊优惠金额
                    String discountApportion = goodsSkuModel.getDiscountApportion() == null ? "0" : goodsSkuModel.getDiscountApportion();
                    if (sharingSkuNumTemp > 1) {
                        //当前商品应该分摊的优惠券金额=本商品在符合条件商品的金额占比这个比例 乘以 本优惠券总金额discount
                        BigDecimal skuMoney = new BigDecimal(goodsSkuModel.getSkuNum()).multiply(new BigDecimal(goodsSkuModel.getSkuPrice()));
                        BigDecimal points = skuMoney.divide(skuTotalMoney, 2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal divide = discount.multiply(points).setScale(2, BigDecimal.ROUND_HALF_UP);
                        goodsSkuModel.setDiscountApportion(divide.add(new BigDecimal(discountApportion)).toString());
                        goodsDiscountApportionTemp = goodsDiscountApportionTemp.add(divide);
                    } else {
                        //最后一个分摊的优惠用减法
                        BigDecimal subtract = discount.subtract(goodsDiscountApportionTemp);
                        goodsSkuModel.setDiscountApportion(subtract.add(new BigDecimal(discountApportion)).toString());
                    }
                    sharingSkuNumTemp--;
                } else {
                    goodsSkuModel.setDiscountApportion("0");
                }
            }
        }
    }

    private void calculatingMoney(OrderModel orderModel, Object denomination, Object discountRate) {
        boolean calculatFlag = true;
        BigDecimal discountMoneyTemp = BigDecimal.ZERO;
        String orderDiscountMoney = orderModel.getOrderVo().getOrderDiscountMoney() == null ? "0" : orderModel.getOrderVo().getOrderDiscountMoney();
        String orderMoney = orderModel.getOrderVo().getOrderMoney();
        //无论是 float 还是 double 类型的变量，都有精度限制。所以一定要避免将浮点变量用“ ==”或“！=”与数字比较，应该设法转化成“ >=”或“ <=”形式。
        if (denomination != null && denomination.toString().compareTo("0") > 0) {
            discountMoneyTemp = new BigDecimal(denomination.toString());
            orderDiscountMoney = new BigDecimal(orderDiscountMoney).add(discountMoneyTemp).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else if (discountRate != null && discountRate.toString().compareTo("0") > 0) {
            //得到优惠金额
            BigDecimal couponDiscountMoney = new BigDecimal(orderMoney).multiply(new BigDecimal(discountRate.toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
            discountMoneyTemp = couponDiscountMoney;
            orderDiscountMoney = new BigDecimal(orderDiscountMoney).add(couponDiscountMoney).toString();
        } else {
            //说明没有享受折扣或者优惠价。就不用计算，也不用拆包后自动装包
            calculatFlag = false;
        }
        if (calculatFlag) {
            orderMoney = new BigDecimal(orderMoney).subtract(discountMoneyTemp).toString();
            orderModel.getOrderVo().setOrderDiscountMoney(orderDiscountMoney);
            orderModel.getOrderVo().setOrderMoney(orderMoney);
        }
    }

    private HashSet getSpuIdList(OrderModel orderModel) {
        List<GoodsSkuModel> itemList = orderModel.getItemList();
        HashSet hSet = new HashSet();
        for (GoodsSkuModel goodsSkuModel : itemList) {
            String spuId = goodsSkuModel.getSpuId();
            hSet.add(spuId);
        }
        return hSet;
    }

    /**
     * 把订单促销活动的信息封装到数据模型OrderModel的traderOrderCouponList中，以供后续的计算，写表用。
     *
     * @param orderModel
     * @return
     * @date 2019-06-09 增加逻辑:促销活动非全品类的先处理分摊到各个商品sku上。
     */
    private OrderModel getActivityDiscountMoney(OrderModel orderModel) {
        List<ActivityModel> activityList = orderModel.getActivityList();
        //得到全品类的共享的优惠总金额
        String sharingMoney = orderModel.getOrderVo().getSharingDiscountMoney() == null ? "0" : orderModel.getOrderVo().getSharingDiscountMoney();
        BigDecimal sharingMoneyDecimal = new BigDecimal(sharingMoney);
        if (null != activityList && activityList.size() > 0) {
            List<TradeOrderCoupon> traderOrderCouponList = orderModel.getTraderOrderCouponList();
            if (traderOrderCouponList == null) {
                traderOrderCouponList = new ArrayList<>(activityList.size());
            }
            List<Long> ids = new ArrayList<>(activityList.size());
            for (ActivityModel activityModel : activityList) {
                ids.add(Long.valueOf(activityModel.getActivityId()));
            }
            List<ActivityInfo> activityInfoList = tradeOrdersServiceMapper.listActivityByIds(ids);
            ActivityModel activityModel = null;
            TradeOrderCoupon tradeOrderCoupon = null;
            int i = 0;
            String orderMoney = orderModel.getOrderVo().getOrderMoney();
            for (ActivityInfo activityInfo : activityInfoList) {
                activityModel = new ActivityModel();
                BeanUtils.copyProperties(activityInfo, activityModel);
                activityModel.setActivityId(activityInfo.getId());
                activityModel.setActivityName(activityInfo.getName());
                //将orderModel中旧的ActivityList元素只包含id替换成信息更全的activityModel.这个后面测试时注意根据ids查出的活动集合和放入orderModel一致性，不一致也应该行。长度一致就可以！
                orderModel.getActivityList().set(i, activityModel);
                i++;
                tradeOrderCoupon = new TradeOrderCoupon();
                tradeOrderCoupon.setCouponType(COUPON_TYPE_ACTIVITY.getId());
                tradeOrderCoupon.setCouponActivityId(activityInfo.getId());
                String denomination = activityInfo.getDenomination();
                String discountRate = activityInfo.getDiscountRate();
                tradeOrderCoupon.setDiscountPrice(denomination);
                tradeOrderCoupon.setDiscountRate(discountRate);
                traderOrderCouponList.add(tradeOrderCoupon);
                String goodsLimitCategory = activityInfo.getGoodsLimitCategory();
                BigDecimal activityDiscount = denomination == null ? new BigDecimal(orderMoney).multiply(new BigDecimal(discountRate.toString())).setScale(2, BigDecimal.ROUND_HALF_UP) : new BigDecimal(denomination);
                if (ACTIVITY_ALL_CATEGORIES.getId().equals(goodsLimitCategory)) {
                    //活动指定全品类,后走共同分摊优惠的逻辑。
                    sharingMoneyDecimal = sharingMoneyDecimal.add(activityDiscount);
                } else {
                    HashSet hSet = getSpuIdList(orderModel);
                    //活动指定非全品类，则要将当前这个优惠活动的金额分摊到享受到活动的商品skuId上。则记录到各自商品的里。
                    // 1.查询当前商品是否有满足当前活动的spuId。
                    List<String> spuIdList = tradeOrdersServiceMapper.getSpuIdBySpuIdsAndActivityId(Long.parseLong(activityInfo.getId()), new ArrayList(hSet));
                    calculatingSkuDiscount(orderModel, spuIdList, activityDiscount);
                }
                calculatingMoney(orderModel, denomination, discountRate);
            }
            orderModel.setTraderOrderCouponList(traderOrderCouponList);
            orderModel.getOrderVo().setSharingDiscountMoney(sharingMoneyDecimal.toString());
        }
        return orderModel;
    }


    @Override
    public JSONObject orderCheck(String json) throws Exception {
        List<String> idList = new ArrayList<>(3);
        JSONObject orderModelJson = JSONObject.parseObject(json);
        //优惠券
        Object couponListStr = orderModelJson.get("couponList");
        if (null != couponListStr && StringUtils.isNotEmpty(couponListStr.toString())) {
            List<CouponModel> couponModels = JSONObject.parseArray(couponListStr.toString(), CouponModel.class);

            if (null != couponModels && couponModels.size() > 0) {
                for (CouponModel couponModel : couponModels) {
                    idList.add(couponModel.getCouponId());
                }
                List<CouponInfo> couponInfoList = tradeOrdersServiceMapper.batchQueryByCouponId(idList, DateUtil.getNowTimeStr());
                if (couponInfoList != null && couponInfoList.size() == idList.size()) {
                } else {
                    return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_COUPON_INVALID);
                }
            }
        }
        idList.clear();
        //促销活动
        Object activityListStr = orderModelJson.get("activityList");
        if (null != activityListStr && StringUtils.isNotEmpty(activityListStr.toString())) {
            List<ActivityModel> activityModels = JSONObject.parseArray(activityListStr.toString(), ActivityModel.class);
            if (null != activityModels && activityModels.size() > 0) {
                for (ActivityModel activityModel : activityModels) {
                    idList.add(activityModel.getActivityId());
                }
                List<ActivityInfo> activityInfos = tradeOrdersServiceMapper.batchQueryByActivityId(idList, DateUtil.getNowTimeStr());
                if (activityInfos != null && activityInfos.size() == idList.size()) {
                } else {
                    return JsonUtil.getErrorJson(ResponseCode.RESULT_ACTIVITY_INVALID);
                }
            }
        }
        return JsonUtil.getSuccessJson();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject cancelOrder(String json) throws Exception {
        JSONObject orderJson = JSONObject.parseObject(json);
        String orderId = orderJson.getString("orderId");
        Long userId = orderJson.getLong("userId");
        int i = tradeOrdersServiceMapper.cancleOrder(orderId, DateUtil.getNowTimeStr());
        if (i > 0) {
            //将使用的优惠券的状态改成未使用。分为两步，1.查询处该用户使用的优惠券，2.更新这些优惠券的使用状态
            List<String> couponIdList = tradeOrdersServiceMapper.getCouponListByOrderId(Long.parseLong(orderId));
            if (couponIdList != null && couponIdList.size() > 0) {
                tradeOrdersServiceMapper.updateCouponCodeUserStatusByCouponIdAndUserId(userId, couponIdList);
            }
            return JsonUtil.getSuccessJson();
        }
        return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_MYSQL_UPDATE_FAIL);
    }


    /**
     * 如果订单表的订单状态为待发货，则不用调用快递接口。只有已发货状态的订单，拿着订单表的发货单号，才去调用快递接口。
     * 查询发货状态，如果存在配送信息，则更新订单的状态为30：待收货
     * 同时将快递单号更新到订单中
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getOrderDeliveryInfo(String json) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = JSON.parseObject(json, Map.class);
        String orderId = map.get("id");
        TradeOrder tradeOrder = tradeOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(orderId));
        if (tradeOrder != null) {
            if (tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_CITY_EXPRESS.getId()) || tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_ORDINARY_EXPRESS.getId())) {
                //走管易，快递100
                jsonObject = getDeliveryInfoFromGYAndKD100(tradeOrder);
            } else if (tradeOrder.getDistributionId().equals(DISTRIBUTE_TYPE_ONE_HOUR.getId())) {
                //走蜂鸟  查询快递信息，要满足订单的状态为：订单已推送到蜂鸟系统
                OrdersBattleInfo battleInfo = tradeOrdersServiceMapper.getBattleInfoByOrderId(orderId);
                if (Integer.parseInt(battleInfo.getStatus()) > Integer.parseInt(ORDER_BATTLE_STATUS_ALREADY_SEND.getId())) {
                    jsonObject = getDeliveryInfoFromHummingLogistics(tradeOrder.getOrderNo());
                } else {
                    jsonObject.put("msg", "订单等待发货，暂无物流信息！");
                }
            } else {
                LOGGER.info("===============剩下这种货到付款的订单没有物流信息。====================");
                jsonObject.put("msg", "订单为货到付款，没有物流信息哦！");
                jsonObject.put("success", "false");
            }
        } else {
            jsonObject.put("msg", "订单不存在！");
        }
        jsonObject.put("distributionId", tradeOrder.getDistributionId());
        jsonObject.remove("functionName");
        jsonObject.remove("serviceName");
        return JsonUtil.getSuccessJson(jsonObject);
    }

    /**
     * 次日达，普通快递是通过管易系统发货。通过快递一百系统查询物流信息。
     *
     * @param tradeOrder
     * @return
     */
    private JSONObject getDeliveryInfoFromGYAndKD100(TradeOrder tradeOrder) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String expressNo = tradeOrder.getGyLogisticsCode();
        String expressCompanyCode = tradeOrder.getLogisticsCode();
        String orderNo = tradeOrder.getOrderNo();
        //先去获取快递单号，如果存在，直接查询物流信息。不存在再调用管易接口得到快递单号
        if (StringUtil.isEmpty(expressNo)) {
            //1.物流信息-应满足订单状态为未取消，未退货，才有物流信息
            if (ORDER_CANCEL_STATUS_YES.equals(tradeOrder.getOrderYn())) {
                jsonObject.put("msg", "订单已经取消，无物流信息。");
                return jsonObject;
            } else if (ORDER_STATUS_REFUND_YES.equals(tradeOrder.getIsRefund())) {
                jsonObject.put("msg", "订单已退货，无物流信息。");
                return jsonObject;
            } else {
                jsonObject.put("orderNo", orderNo);
                JSONObject deliverJson = callMtTask(GY_DELIVERY_INFO, jsonObject);
                LOGGER.info("查询mt-task的管易接口，订单号：" + orderNo + "对应的物流信息返回值：" + deliverJson);
                boolean success = (boolean) deliverJson.get("success");
                if (success) {
                    JSONArray deliverys = deliverJson.getJSONArray("deliverys");
                    if (deliverys != null) {
                        if (deliverys != null && deliverys.size() > 0) {
                            JSONObject jsonDelivery = deliverys.getJSONObject(0);
                            expressNo = jsonDelivery.getString("express_no");
                            LOGGER.info("获取订单编号：" + orderNo + "的快递单号：" + expressNo);
                            //将快递信息更新进订单表中
                            expressCompanyCode = jsonDelivery.getString("express_code");
                            LOGGER.info("----------------------------------------------------获取管易系统返回的快递公司code:" + expressCompanyCode);
                            Map expressCodeMap = tradeOrdersServiceMapper.getKD100ExpressCodeByGyExpressCode(expressCompanyCode);
                            if (expressCodeMap == null) {
                                LOGGER.info("根据管易的快递公司code查询快递一百系统的快递公司code为空！");
                            } else {
                                expressCompanyCode = expressCodeMap.get("kd100_express_company_code").toString();
                            }
                            String planDeliveryDate = jsonDelivery.getString("modify_date");
                            TradeOrder order = new TradeOrder();
                            order.setId(tradeOrder.getId());
                            //目前不考虑部分发货的状态。只有待收货状态
                            order.setOrderStatus("30");
                            if (planDeliveryDate != null) {
                                order.setDeliveryTime(planDeliveryDate);
                            }
                            order.setLogisticsCode(expressCompanyCode);
                            order.setGyLogisticsCode(expressNo);
                            tradeOrdersServiceMapper.updateOrderDeliveryInfoByOrderId(order);
                        } else {
                            jsonObject.put("msg", "暂无物流信息。");
                            return jsonObject;
                        }
                    } else {
                        jsonObject.put("msg", "暂无物流信息。");
                        return jsonObject;
                    }
                }
            }
        }
        //这里面会根据订单号查询从管易发货单查询接口同步过来的发货信息查询出【物流单号-expressNo】，然后调用快递100的接口查询物流信息。
        JSONObject kd100Json = new JSONObject();
        kd100Json.put("com", expressCompanyCode);
        if ("shunfeng".equals(expressCompanyCode)) {
            String buyerId = tradeOrder.getBuyerId();
            User userInfo = tradeOrdersServiceMapper.getUserInfoById(Long.parseLong(buyerId));
            kd100Json.put("phone", userInfo.getMobile());
        }
        kd100Json.put("num", expressNo);
        JSONObject deliveryInfoFromKD100 = callFNService(KD100_QUERY_DELIVERY, kd100Json);
        deliveryInfoFromKD100.put("expressNo", expressNo);
        deliveryInfoFromKD100.put("expressCompanyCode", expressCompanyCode);
        //--------------------微信小程序发送普通物流信息提醒通知------------------start-------
        JSONObject params =new JSONObject();
        //快递单号，物流单号
        params.put("expressNo",expressNo);
        //快递公司码
        params.put("code",expressCompanyCode);
        //订单编号
        params.put("orderNo",tradeOrder.getOrderNo());
        params.put("orderId",tradeOrder.getId());
        //买家用户id
        params.put("userId",tradeOrder.getBuyerId());
        params.put("time",tradeOrder.getCreateTime());
        params.put("addressId",tradeOrder.getAddressId());
        tradeOrderServiceAsync.asyncNotifyUserCommonDeliveryInfo(params);
        //--------------------微信小程序发送普通物流信息提醒通知------------------end-------
        return deliveryInfoFromKD100;
    }


    /**
     * 一小时达，系统走的是蜂鸟系统。快递也是查询蜂鸟的物流信息。
     *
     * @param orderNo
     * @return
     */
    private JSONObject getDeliveryInfoFromHummingLogistics(String orderNo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("partner_order_code", orderNo);
        JSONObject deliverJson = callFNService(FN_ORDER_QUERY, jsonObject);
        LOGGER.info("根据订单编号orderNo:" + orderNo + "查询出的订单详情及最新物流状态：" + deliverJson);
        return deliverJson;
    }


    @Override
    public JSONObject preOrderByStoryCar(String json) throws Exception {
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String userId = map.get("userId");
        //从redis中获取收藏车
        BuyCar storeCar = getCarFromRedis(userId);
        if (storeCar == null || storeCar.getItems().isEmpty()) {
            return JsonUtil.getErrorJson(RESULT_CODE_BUYCAR_NULL_ERROR);
        }
        //实现预订单
        Map<String, CarSku> items = storeCar.getItems();
        if (null != items && items.size() > 0) {
            List<GoodsSkuModel> itemList = new ArrayList<>();
            for (String key : items.keySet()) {
                CarSku carSku = items.get(key);
                GoodsSkuModel model = new GoodsSkuModel();
                model.setSpuId(carSku.getSpuId());
                model.setSkuId(carSku.getSkuId());
                model.setSkuNum(carSku.getNum());
                model.setSkuPrice(carSku.getPrice());
                itemList.add(model);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("itemList", itemList);
            return this.queryPreOrderInfo(jsonObject.toJSONString());
        }
        return JsonUtil.getErrorJson(RESULT_CODE_BUYCAR_NULL_ERROR);
    }

    /**
     * 从redis里获得收藏车信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    private BuyCar getCarFromRedis(String userId) throws Exception {
        BuyCar car = null;
        if (StringUtil.isEmpty(userId)) {
            return null;
        } else {
            String jsonObject = redisServer.get(ENTITY_STORE_ID_REDIS, userId);
            car = JSONObject.parseObject(jsonObject, BuyCar.class);
        }
        return car;
    }

    @Override
    public JSONObject orderCheckAddressHour(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long addressId = jsonObject.getLong("addressId");
        UserAddress userAddress = tradeOrdersServiceMapper.selectByAddressId(addressId);
        String cityName = userAddress.getCityName();
        String hourCitByCityName = tradeOrdersServiceMapper.getHourCitByCityName(cityName);
        if (StringUtil.isEmpty(hourCitByCityName)) {
            return JsonUtil.getSuccessJson("false");
        }
        return JsonUtil.getSuccessJson("true");
    }

    /**
     * 次日达逻辑目前和一小时的操作一样，未来有可能执行不同的业务逻辑，所以先拆开。
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject orderCheckAddressDay(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long addressId = jsonObject.getLong("addressId");
        UserAddress userAddress = tradeOrdersServiceMapper.selectByAddressId(addressId);
        String cityName = userAddress.getCityName();
        String hourCitByCityName = tradeOrdersServiceMapper.getHourCitByCityName(cityName);
        if (StringUtil.isEmpty(hourCitByCityName)) {
            return JsonUtil.getSuccessJson("false");
        }
        return JsonUtil.getSuccessJson("true");
    }

    @Override
    public JSONObject orderCheckBlueTooth(String json) throws Exception {
        try {
            JSONObject inParam = JSONObject.parseObject(json);
            int num = tradeOrdersServiceMapper.getBlueToothCount(Long.parseLong(inParam.getString("user")));
            return JsonUtil.getSuccessJson(num);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public JSONObject getOrderDeliveryInfoSms(String json) throws  Exception{
        JSONObject param = JSONObject.parseObject(json);
        String orderId = param.getString("orderId");
        TradeOrder tradeOrder = tradeOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(orderId));
        JSONObject paramObj = new JSONObject();
        paramObj.put("id",orderId);
        JSONObject orderDeliveryInfo = getOrderDeliveryInfo("{\"id\":\"" + orderId + "\"}");
     //   UserProfitsService userProfitsService = decoratorCommonServiceComponent.getUserProfitsService();
        //根据dizhiId查询地址信息
        UserAddress userAddress = userAddressMapper.getUserAddress(Long.parseLong(tradeOrder.getAddressId()));
        //JSONObject userByMobile = userProfitsService.getUserById(Long.parseLong(userAddress.getUserId()));
        JSONObject result=new JSONObject();
        result.put("logisticsCode",StringUtil.isEmpty(tradeOrder.getLogisticsCode())?"":tradeOrder.getLogisticsCode());
       // LOGGER.info("查询用户:{}",JSON.toJSONString(userByMobile));
        result.put("userName",userAddress.getReceiveName());
        LOGGER.info("订单:{}",JSON.toJSONString(tradeOrder));
        result.put("orderTime",tradeOrder.getCreateTime());
        result.put("privinceName",userAddress.getProvinceName());
        result.put("cityName",userAddress.getCityName());
        result.put("logisticsInfo",orderDeliveryInfo);
        return result;
    }


}
