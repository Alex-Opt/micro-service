package com.ly.mt.order.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.trade.PageOrderVo;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.order.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.ly.mt.core.base.method.OrderMethodEnum.*;


@Api(description = "活动页---订单管理中心接口")
@RestController
@RequestMapping("/order/activityPage")
public class ActivityPageOrderController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityPageOrderController.class);

    @ApiOperation(value = "从活动页下单(到付)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true),
            @ApiImplicitParam(name = "mobile", value = "电话", required = true),
            @ApiImplicitParam(name = "skuId", value = "商品skuid", required = true),
            @ApiImplicitParam(name = "skuNum", value = "商品数量", required = true),
            @ApiImplicitParam(name = "address", value = "收货地址", required = true),
            @ApiImplicitParam(name = "orderSource", value = "订单来源", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/bookingActivityOrder")
    public JSONObject geneOrderFromPagePromotion(@RequestBody String  pageOrderVo) {
        JSONObject pageOrderVoJson = JSONObject.parseObject(pageOrderVo);
        String pageOrderStr = pageOrderVoJson.get("pageOrder").toString();
        PageOrderVo orderVo = JSONObject.parseObject(pageOrderStr,PageOrderVo.class);
        if (null != orderVo &&
                null!=orderVo.getAddress() &&
                null != orderVo.getUserName()&&
                null != orderVo.getMobile()) {
            return callOrderServer(TRADE_ORDER_PAGE_PROMOTION, JSONObject.parseObject(pageOrderVo));
        }
        return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_PARAM_ERROR);
    }

}
