package com.ly.mt.order.web.border.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.order.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static com.ly.mt.core.base.method.OrderMethodEnum.*;

@Api(description = "到家B端---抢单模块接口")
@RestController
@RequestMapping("/order/battleOrder/b")
public class BOrderBattleController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BOrderBattleController.class);

    @ApiOperation(value = "小B查询自己抢单列表接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/getOrderBattleList")
    public JSONObject tradeOrderDetail() {
        return callOrderServer(ORDER_BATTLE_QUERY_LIST, new JSONObject());
    }


    @ApiOperation(value = "抢单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBattleId", value = "抢的发货单id", required = true),
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/grabOrder")
    public JSONObject grabOrder(@RequestParam(value = "orderBattleId") String orderBattleId, @RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderBattleId", orderBattleId);
        jsonObject.put("orderId", orderId);
        LOGGER.info("抢单接口入参：" + jsonObject.toString());
        return callOrderServer(ORDER_BATTLE_GRAB_ORDER, jsonObject);
    }

    @ApiOperation(value = "小B释放抢单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBattleId", value = "抢的发货单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/releaseGrabOrder")
    public JSONObject releaseGrabOrder(@RequestParam(value = "orderBattleId") String orderBattleId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderBattleId", orderBattleId);
        LOGGER.info("释放抢单接口入参：" + jsonObject.toString());
        return callOrderServer(ORDER_BATTLE_RELEASE_GRAB_ORDER, jsonObject);
    }

    @ApiOperation(value = "取消抢单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBattleId", value = "抢的发货单id", required = true),
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/cancelGrabOrder")
    public JSONObject cancelGrabOrder(@RequestParam(value = "orderBattleId") String orderBattleId, @RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderBattleId", orderBattleId);
        jsonObject.put("orderId", orderId);
        LOGGER.info("取消抢单接口入参：" + jsonObject.toString());
        return callOrderServer(ORDER_BATTLE_CANCEL_GRAB_ORDER, jsonObject);
    }


    @ApiOperation(value = "校验商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "商品唯一码,目前传69码，暂时不用唯一码", required = true),
            @ApiImplicitParam(name = "battleId", value = "抢单Id", required = true),
            @ApiImplicitParam(name = "orderId", value = "订单Id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/checkGoodsEffectiveness")
    public JSONObject checkGoodsEffectiveness(@RequestParam(value = "code") String code, @RequestParam(value = "battleId") String battleId, @RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("battleId", battleId);
        jsonObject.put("orderId", orderId);
        LOGGER.info("校验商品接口入参：" + jsonObject.toString());
        return callOrderServer(ORDER_BATTLE_CHECK_GOODS, jsonObject);
    }


    @ApiOperation(value = "蜂鸟快递推送订单回调接口,此接口不对咱们系统。是对蜂鸟的回调接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "app_id", value = "调用蜂鸟接口时传的appId", required = true),
            @ApiImplicitParam(name = "data", value = "快递信息的json串", required = true),
            @ApiImplicitParam(name = "salt", value = "抢单Id", required = true),
            @ApiImplicitParam(name = "signature", value = "参数签名", required = true),
    })
    @PostMapping("/hummingBirdDeliveryCallBackMethod")
    public JSONObject hummingBirdDeliveryCallBackMethod(@RequestParam(value = "app_id") String appId,
                                                        @RequestParam(value = "data") String data,
                                                        @RequestParam(value = "salt") String salt,
                                                        @RequestParam(value = "signature") String signature) {
        JSONObject jsonObject;
        try {
            String decode = URLDecoder.decode(data, "UTF-8");
            jsonObject = JSONObject.parseObject(decode);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("转码解密出现异常：" + e.getMessage(), e);
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_SYSTEM_ERROR);
        }
        LOGGER.info("蜂鸟回调接口入参：" + jsonObject.toString());
        return callOrderServer(ORDER_BATTLE_CALL_BACK_METHOD, jsonObject);
    }

    @ApiOperation(value = "确认收货接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @PostMapping("/confirmFinishOrder")
    public JSONObject confirmFinishOrder(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(ORDER_BATTLE_CONFIRM_FINISH_ORDER, jsonObject);
    }

    @ApiOperation(value = "查询抢单信息，入商品校验截至时间，订单状态的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @PostMapping("/getOrderBattleInfo")
    public JSONObject getGoodsCheckDeadLine(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(ORDER_BATTLE_INFO, jsonObject);
    }

    @ApiOperation(value = "查询订单商品详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @PostMapping("/queryOrderDetail")
    public JSONObject queryOrderDetail(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(ORDER_BATTLE_QUERY_ORDER_DETAIL, jsonObject);
    }

    @ApiOperation(value = "商品校验完成，推送订单到蜂鸟接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @PostMapping("/sendOrderToHummingBird")
    public JSONObject sendOrderToHummingBird(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(ORDER_BATTLE_SEND_ORDER, jsonObject);
    }

    @ApiOperation(value = "查询查询骑手位置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true)
    })
    @PostMapping("/queryCarrierPosition")
    public JSONObject queryCarrierPosition(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        return callOrderServer(ORDER_BATTLE_QUERY_CARRIER_POSITION, jsonObject);
    }

}
