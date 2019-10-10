package com.ly.mt.center.data.payment.controller;

import com.ly.mt.center.data.payment.entity.PaymentDetail;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "支付流水接口")
@RestController
@RequestMapping("/data/center/paymentDetail")
public class PaymentDetailController {
    @ApiOperation(value = "保存PaymentDetail")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/insertPaymentDetail")
    public ResponseJson insertPaymentDetail(@RequestBody PaymentDetail paymentDetail) {
        return null;
    }
}