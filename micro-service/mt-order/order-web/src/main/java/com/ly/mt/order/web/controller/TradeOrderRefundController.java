package com.ly.mt.order.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.dto.AddTradeOrderRefundDto;
import com.ly.mt.core.base.entity.trade.dto.GetBuyerRefundsPageDto;
import com.ly.mt.core.base.entity.trade.dto.UpdateRefundInfoDto;
import com.ly.mt.core.base.entity.trade.dto.UpdateRefundStatusDto;
import com.ly.mt.order.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.ly.mt.core.base.method.OrderMethodEnum.*;

/**
 * @author 484876123@qq.com
 */
@Api(description = "退款---订单退款功能")
@RestController
@RequestMapping("/order/refund")
public class TradeOrderRefundController extends BaseController {
    /**
     * 日志声明
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderRefundController.class);
    @ApiOperation(value = "申请售后/退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单号", required = true),
            @ApiImplicitParam(name = "buyerId", value = "买家id", required = true),
            @ApiImplicitParam(name = "refundReason", value = "退款/退货描述", required = true),
            @ApiImplicitParam(name = "refundDesc", value = "退款/退货原因", required = true),
            @ApiImplicitParam(name = "skuId", value = "退货单品sku码,如果是退款，无此参数", required = false),
            @ApiImplicitParam(name = "refundsCount", value = "退货数量,如果是退款，无此参数", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 150, message = "订单状态禁止售后/退款"),
            @ApiResponse(code = 151, message = "退货数量大于订单数量")
    })
    @PostMapping("/addRefund")
   // public JSONObject getRefund(@RequestBody AddTradeOrderRefundDto param) {
    public JSONObject getRefund(@RequestBody String  refund) {
        LOGGER.info("请求退款参数:",refund);
        JSONObject refundJsonObj = JSONObject.parseObject(JSONObject.parseObject(refund).get("refund").toString());
        JSONObject json = callOrderServer(TRADE_ORDER_ADD_REFUND, JSONObject.parseObject(JSON.toJSONString(refundJsonObj)));
        return json;
    }


    @ApiOperation(value = "通过买家Id返回退货单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buyerId", value = "买家Id", required = true),
            @ApiImplicitParam(name = "page", value = "页号", required = true),
            @ApiImplicitParam(name = "rows", value = "每页条数", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/getBuyerRefunds")
    public JSONObject getBuyerRefunds(@ModelAttribute GetBuyerRefundsPageDto param) {
        JSONObject json = callOrderServer(TRADE_ORDER_GET_BUYER_REFUND_LIST, JSONObject.parseObject(JSON.toJSONString(param)));
        return json;
    }


    @ApiOperation(value = "根据Id更新退货单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单/退款单id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 2, message = "参数错误"),
            @ApiResponse(code = 153, message = "当前退货单已取消"),
            @ApiResponse(code = 155, message = "退货单为空")
    })
    @PostMapping("/updateRefundStatus")
    public JSONObject updateRefundStatus(@ModelAttribute UpdateRefundStatusDto param) {
        JSONObject json = callOrderServer(TRADE_ORDER_UPDATE_REFUND_STATUS, JSONObject.parseObject(JSON.toJSONString(param)));
        return json;
    }


    @ApiOperation(value = "根据Id更新退货单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单/退款单id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 153, message = "当前退货单已取消"),
            @ApiResponse(code = 155, message = "退货单为空")

    })
    @PostMapping("/updateRefundInfo")
    public JSONObject updateRefundInfo(@RequestBody UpdateRefundInfoDto param) {
        JSONObject json = callOrderServer(TRADE_ORDER_UPDATE_REFUND_INFO, JSONObject.parseObject(JSON.toJSONString(param)));
        return json;
    }

    @ApiOperation(value = "根据id取消退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 153, message = "当前退货单已取消"),
            @ApiResponse(code = 154, message = "当前退货单状态拒绝取消申请")
    })
    @PostMapping("/updateRefundCancel")
    public JSONObject updateRefundCancel(@ApiParam(value = "退货单id", required = true) @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        JSONObject json = callOrderServer(TRADE_ORDER_UPDATE_REFUND_CANCEL, jsonObject);
        return json;
    }


    @ApiOperation(value = "通过买家Id返回退货单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 155, message = "系统异常")
    })
    @PostMapping("/getRefundItem")
    public JSONObject getRefundItem(@ApiParam(value = "退货单id", required = true) @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        JSONObject json = callOrderServer(TRADE_ORDER_GET_REFUND_ITEM, jsonObject);
        return json;
    }



    @ApiOperation(value = "微信退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refundId", value = "退货单id", required = true),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常"),
            @ApiResponse(code = 155, message = "系统异常")
    })
    @PostMapping("/wxRefund")
    public JSONObject wxRefund(@RequestParam(value = "refundId",required = true) String refundId,
                               @RequestParam(value = "orderNo",required = true) String orderNo){
        JSONObject json = new JSONObject();
        json.put("refundId",refundId);
        json.put("orderNo",orderNo);
        return callOrderServer(TRADE_ORDER_WX_REFUND,json);
    }
}
