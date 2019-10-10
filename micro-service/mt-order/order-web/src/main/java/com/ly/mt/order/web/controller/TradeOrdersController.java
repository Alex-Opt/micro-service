package com.ly.mt.order.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.coupon.CouponModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderVo;
import com.ly.mt.core.base.method.OrderMethodEnum;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.order.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_CITY_EXPRESS;
import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.base.method.OrderMethodEnum.*;
import static com.ly.mt.core.redis.RedisKey.*;


@Api(description = "M端H5商城---订单管理中心接口")
@RestController
@RequestMapping("/order/m")
public class TradeOrdersController extends BaseController {
    @Resource
    public RedisService redisServer;

    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrdersController.class);

    @ApiOperation(value = "用户分页查询订单列表接口")
    @PostMapping("/listTradeOrder")
    public JSONObject listTradeOrder(@ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                     @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        return callOrderServer(TRADE_ORDER_LIST, jsonObject);
    }


    @ApiOperation(value = "根据订单id查询单个订单详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单号", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/tradeOrderDetail")
    public JSONObject tradeOrderDetail(@RequestParam(value = "id") String id, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        String userId = getLoginUserId(request);
        jsonObject.put("userId", userId);
        return callOrderServer(TRADE_ORDER_DETAIL, jsonObject);
    }


    @ApiOperation(value = "预订单查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "购买的商品信息实体集合实体的一个参数：skuId", required = true),
            @ApiImplicitParam(name = "spuId", value = "购买的商品信息实体集合实体的一个参数：spuId", required = true),
            @ApiImplicitParam(name = "skuNum", value = "购买的商品信息实体集合实体的一个参数：购买商品的数量skuNum", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/queryPreOrderInfo")
    public JSONObject queryPreOrderInfo(@ApiParam(value = "购买的商品集合信息", required = true) @RequestBody List<GoodsSkuModel> goodsSkuModels, HttpServletRequest request) {
        String userId = getLoginUserId(request);
        LOGGER.info("===================userId:" + userId);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        String clickToken = redisServer.get(REDIS_REPEAT_CLICK_ACTION_QUERY_PRE_ORDER_INFO, userId);
        if (StringUtil.isEmpty(clickToken)) {
            redisServer.set(REDIS_REPEAT_CLICK_ACTION_QUERY_PRE_ORDER_INFO, userId, userId, 5L, TimeUnit.SECONDS);
        } else {
            return JsonUtil.getErrorJson(RESPONSE_CODE_OPERATOR_TOO_FASTER);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("itemList", goodsSkuModels);
        jsonObject.put("userId", userId);

        JSONObject jsonObject1 = callOrderServer(TRADE_ORDER_HTML_SHOW, jsonObject);
        redisServer.del(REDIS_REPEAT_CLICK_ACTION_QUERY_PRE_ORDER_INFO, userId);
        return jsonObject1;
    }


    @ApiOperation(value = "下订单前，调用支付接口前对订单的校验接口,完成对订单中使用给的优惠活动是否过期的校验，过期则提示用户重新下单.完成一小时达订单时间限制校验。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "List<CouponModel> couponList", value = "可使用的优惠券参数集合--CouponModel{couponId：优惠券id}", required = true),
            @ApiImplicitParam(name = "List<ActivityModel> activityList", value = "可用促销活动参数集合--ActivityModel{activityId:优惠活动id}", required = true),
            @ApiImplicitParam(name = "distributionId", value = "配送方式id", required = true),
            @ApiImplicitParam(name = "addressId", value = "订单的地址id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 41, message = "校验未通过,优惠券已过期"),
            @ApiResponse(code = 42, message = "校验未通过,促销活动已过期"),
            @ApiResponse(code = 7, message = "校验未通过,不在配送时间范围内"),
            @ApiResponse(code = 8, message = "校验未通过,收货地址暂不支持一小时达"),
            @ApiResponse(code = 0, message = "校验通过")
    })
    @PostMapping("/checkOrder")
    public JSONObject checkOrder(@RequestBody String orderModel, HttpServletRequest request) {
        JSONObject orderModelJson = JSONObject.parseObject(orderModel);
        Object distributionId = orderModelJson.get("distributionId");
        if (distributionId == null) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_NEED);
        }
        //首先校验当前时间是不是一小时达订单的时间。如果配送为一小时达则进行时间校验。
        if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(distributionId.toString())) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String format = sdf.format(new Date());
            if (format.compareTo("09:30") < 0 || format.compareTo("17:30") > 0) {
                return JsonUtil.getErrorJson(RESULT_CODE_OUT_TIME);
            }
            //增加逻辑判断当前下单地址是否支持一小时达订单。
            JSONObject jsonObject = callOrderServer(TRADE_ORDER_CHECK_ADDRESS_HOUR, orderModelJson);
            String hourFlag = jsonObject.getString("result");
            if ("false".equals(hourFlag)) {
                return JsonUtil.getErrorJson(RESULT_CODE_OUT_DISTRICT);
            }
        }
        //次日达的订单也要校验下地址所在城市是否开通了次日达。
        if (DISTRIBUTE_TYPE_CITY_EXPRESS.getId().equals(distributionId.toString())) {
            JSONObject jsonObject = callOrderServer(TRADE_ORDER_CHECK_ADDRESS_DAY, orderModelJson);
            String dayFlag = jsonObject.getString("result");
            if ("false".equals(dayFlag)) {
                return JsonUtil.getErrorJson(RESULT_CODE_OUT_DISTRICT);
            }
        }
        //需要校验的标志。一开始默认不用校验。
        boolean necessaryCheck = false;
        //优惠券
        Object couponListStr = orderModelJson.get("couponList");
        if (null != couponListStr && StringUtil.isNotEmpty(couponListStr.toString())) {
            List<CouponModel> couponModels = JSONObject.parseArray(couponListStr.toString(), CouponModel.class);
            if (null != couponModels && couponModels.size() > 0) {
                necessaryCheck = true;
            }
        }
        //促销活动
        Object activityListStr = orderModelJson.get("activityList");
        if (null != activityListStr && StringUtil.isNotEmpty(activityListStr.toString())) {
            List<ActivityModel> activityModels = JSONObject.parseArray(couponListStr.toString(), ActivityModel.class);
            if (null != activityModels && activityModels.size() > 0) {
                necessaryCheck = true;
            }
        }
        //如果存在以上任何一种活动，则有必要check
        if (necessaryCheck) {
            return callOrderServer(TRADE_ORDER_CHECK, orderModelJson);
        }
        //如果两种类型的活动都没有。则校验通过。
        return JsonUtil.getSuccessJson();
    }


    @ApiOperation(value = "下订单或者说订单生成接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "List<GoodsSkuModel> itemList", value = "购买的商品信息参数集合--GoodsSkuModel{spuId,skuId,skuNum:购买的商品数量}", required = true),
            @ApiImplicitParam(name = "List<CouponModel> couponList", value = "可使用的优惠券参数集合--CouponModel{couponId：优惠券id}", required = true),
            @ApiImplicitParam(name = "List<ActivityModel> activityList", value = "可用促销活动参数集合--ActivityModel{activityId:优惠活动id}", required = true),
            @ApiImplicitParam(name = "OrderVo orderVo", value = "订单信息参数集合--OrderVo{buyerMemo:买家留言,distributionId:配送方式Id,addressId:收货地址id,orderSource：订单来源(1：H5，2：小程序，3：APP  40~50:活动页下单来源)}", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/generateOrder")
    public JSONObject generateOrder(@ApiParam(value = "订单模型的入参", required = true) @RequestBody String orderModel, HttpServletRequest request) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        String clickToken = redisServer.get(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId);
        if (StringUtil.isEmpty(clickToken)) {
            redisServer.set(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId, userId, 8L, TimeUnit.SECONDS);
        } else {
            return JsonUtil.getErrorJson(RESPONSE_CODE_OPERATOR_TOO_FASTER);
        }
        JSONObject orderModelJson = JSONObject.parseObject(orderModel);
        orderModelJson.put("userId", userId);
        //校验必须有的入参
        String orderVo = orderModelJson.getString("orderVo");
        OrderVo order = JSONObject.parseObject(orderVo, OrderVo.class);
        String distributionId = order.getDistributionId();
        String addressId = order.getAddressId();
        String orderSource = order.getOrderSource();
        if (StringUtil.isEmpty(distributionId) || StringUtil.isEmpty(addressId) || StringUtil.isEmpty(orderSource)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_NEED);
        }
        String itemListStr = orderModelJson.getString("itemList");
        List<GoodsSkuModel> goodsSkuModels = JSONObject.parseArray(itemListStr, GoodsSkuModel.class);
        if (null != goodsSkuModels && goodsSkuModels.size() > 0) {
            JSONObject jsonObject = callOrderServer(TRADE_ORDER_GENERATE, orderModelJson);
            redisServer.del(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId);
            return jsonObject;
        } else {
            redisServer.del(REDIS_REPEAT_CLICK_ACTION_GENERATE_ORDER, userId);
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_GOODS_NULL_ERROR);
        }
    }


    @ApiOperation(value = "取消订单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/cancelOrder")
    public JSONObject cancelOrder(@RequestParam(value = "orderId") String orderId, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = getLoginUserId(request);
        jsonObject.put("userId", userId);
        jsonObject.put("orderId", orderId);
        return callOrderServer(TRADE_ORDER_CANCEL_ORDER, jsonObject);
    }


    @ApiOperation(value = "根据订单号查询物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/getOrderDeliveryInfo")
    public JSONObject getOrderDeliveryInfo(@RequestParam(value = "orderId") String orderId, HttpServletRequest request) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        String clickToken = redisServer.get(REDIS_REPEAT_CLICK_ACTION_ORDER_DELIVERY_INFO, userId);
        if (StringUtil.isEmpty(clickToken)) {
            redisServer.set(REDIS_REPEAT_CLICK_ACTION_ORDER_DELIVERY_INFO, userId, userId, 8L, TimeUnit.SECONDS);
        } else {
            return JsonUtil.getErrorJson(RESPONSE_CODE_OPERATOR_TOO_FASTER);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", orderId);
        JSONObject jsonObject1 = callOrderServer(TRADE_ORDER_DELIVERY_INFO, jsonObject);
        redisServer.del(REDIS_REPEAT_CLICK_ACTION_ORDER_DELIVERY_INFO, userId);
        return jsonObject1;
    }


    @ApiOperation(value = "根据订单号查询物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true),
            @ApiImplicitParam(name = "address", value = "地址Id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @GetMapping("/getOrderDeliveryInfoForSms")
    public JSONObject getOrderDeliveryInfoForSms(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        if (StringUtil.isEmpty(orderId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_NEED);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(TRADE_ORDER_DELIVERY_INFO_SMS, jsonObject);
    }


    @ApiOperation(value = "根据收藏车信息，实现预订单功能", notes = "返回预订单数据")
    @PostMapping(value = "/preOrderByStoryCar")
    @ResponseBody
    public JSONObject preOrderByStoryCar(HttpServletRequest request) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);

        try {
            return callOrderServer(TRADE_PRE_ORDER_STORY, jsonObject);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @ApiOperation(value = "根据快递方式id,支付金额得到相应的快递费用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "distributeId", value = "快递方式id", paramType = "query", required = true),
            @ApiImplicitParam(name = "money", value = "订单实付金额", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/getFreight")
    public JSONObject getFreight(@RequestParam(value = "distributeId") String distributeId,
                                 @RequestParam(value = "money") String money,
                                 HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String freight;
        //新需求是配送费的规则都和一小时达的一样。
        if (new BigDecimal(money).compareTo(new BigDecimal(34)) < 0) {
            freight = "16";
        } else if (new BigDecimal(money).compareTo(new BigDecimal(34)) >= 0 && new BigDecimal(money).compareTo(new BigDecimal(99)) < 0) {
            freight = "9";
        } else if (new BigDecimal(money).compareTo(new BigDecimal(99)) >= 0 && new BigDecimal(money).compareTo(new BigDecimal(150)) < 0) {
            freight = "5";
        } else {
            freight = "0";
        }
        json.put("freight", freight);
        return JsonUtil.getSuccessJson(json);
    }

    @ApiOperation(value = "用户同意将一小时达订单转为次日达订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping(value = "/agreeNextDayOrder")
    @ResponseBody
    public JSONObject agreeNextDayOrder(HttpServletRequest request, @RequestParam(value = "orderId") String orderId) {
        String userId = getLoginUserId(request);
        if (StringUtil.isEmpty(userId)) {
            return JsonUtil.getErrorJson(RESULT_CODE_NOT_LOGIN_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", orderId);
        try {
            return callOrderServer(TRADE_ORDER_AGREE_NEXT_DAY_REACH, jsonObject);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @ApiOperation(value = "下单前校验用户购买的蓝牙商品的数量，如果购买的数量超过3个，不能购买")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回数量"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping(value = "/checkBlueToothPreBookingNum")
    public JSONObject checkBlueToothPreBookingNum(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", getLoginUserId(request));
        return callOrderServer(OrderMethodEnum.TRADE_ORDER_CHECK_BLUETOOTH, jsonObject);
    }
}
