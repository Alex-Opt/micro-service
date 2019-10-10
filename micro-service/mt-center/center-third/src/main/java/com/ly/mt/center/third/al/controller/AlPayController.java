package com.ly.mt.center.third.al.controller;

import com.ly.mt.center.third.al.entity.AlPayOrder;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "阿里——支付服务")
@RestController
@RequestMapping("/center/third/")
public class AlPayController {
    @ApiOperation(value = "wap支付", notes = "支付发起成功后返回result：唤起支付宝支付的form表单")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/al/pay/wapPay")
    public ResponseJson wapPay(@RequestBody AlPayOrder alPayOrder) {
        return null;
    }


    @ApiOperation(value = "wap支付状态查询", notes = "result=20：支付失败；result=30：支付成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "out_trade_no", value = "订单编号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/al/pay/status")
    public ResponseJson status() {
        return null;
    }
}