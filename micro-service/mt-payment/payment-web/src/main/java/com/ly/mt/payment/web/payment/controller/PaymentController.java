package com.ly.mt.payment.web.payment.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.payment.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_AL;
import static com.ly.mt.core.base.dict.PaymentType.PAYMENT_TYPE_WX;
import static com.ly.mt.core.base.method.PaymentMethodEnum.ALI_PAY_STATUS;
import static com.ly.mt.core.base.method.PaymentMethodEnum.WX_PAY_STATUS;

@Api(description = "支付查询接口")
@RestController
@RequestMapping("/payment/")
public class PaymentController extends BaseController {
    @ApiOperation(value = "支付状态查询", notes = "操作成功后返回result：30—支付成功，0—支付失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "paymentType", value = "支付类型：1—阿里，2—微信", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/pay/status")
    public JSONObject status(@RequestParam(value = "orderNo") String orderNo,
                             @RequestParam(value = "paymentType") String paymentType) {
        // 参数校验
        if (StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(paymentType)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 根据支付类型调用接口返回支付状态
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", orderNo);
        if (PAYMENT_TYPE_AL.getId().equals(paymentType)) {
            return callPaymentServer(ALI_PAY_STATUS, jsonObject);
        } else if (PAYMENT_TYPE_WX.getId().equals(paymentType)) {
            return callPaymentServer(WX_PAY_STATUS, jsonObject);
        } else {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
    }
}