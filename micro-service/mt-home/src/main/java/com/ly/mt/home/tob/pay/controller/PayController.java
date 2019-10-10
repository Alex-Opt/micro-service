package com.ly.mt.home.tob.pay.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.pay.service.PayService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @author linan
 * @description : 支付接口
 * @date 2019/7/17
 */
@Api(value = "支付接口", tags = {"支付接口"})
@RestController
@RequestMapping("/home/pay")
public class PayController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    PayService service;

    @ApiOperation(value = "支付宝WAP支付", notes = "操作成功后返回result：form页面")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/al")
    public ResponseJson alPay(@ApiParam(value = "订单编号", required = true) @RequestParam(value = "orderNo") String orderNo,
                              @ApiParam(value = "支付成功后跳转地址", required = true) @RequestParam(value = "returnUrl") String returnUrl,
                              @ApiParam(value = "支付类型") @RequestParam(value = "tradeType", required = false) String tradeType) {

        Assert.hasText(returnUrl, "参数错误");
        Assert.hasText(orderNo, "参数错误");

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, service.alPay(orderNo, returnUrl, tradeType));
    }


    @ApiOperation(value = "微信WAP支付", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx")
    public ResponseJson wxPay(@ApiParam(value = "订单编号", required = true) @RequestParam(value = "orderNo") String orderNo,
                              @ApiParam(value = "支付类型", required = true) @RequestParam(value = "tradeType") String tradeType) {
        Assert.hasText(orderNo, "参数错误");
        Assert.hasText(tradeType, "参数错误");

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, service.wxPay(orderNo, tradeType));
    }

    @ApiOperation(value = "jsapi支付")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/jsapi")
    public ResponseJson jsapiPay(@ApiParam(value = "code", required = true) @RequestParam(value = "code") String code,
                                 @ApiParam(value = "orderNo", required = true) @RequestParam(value = "orderNo") String orderNo) {
        Assert.hasText(code, "参数错误");
        Assert.hasText(orderNo, "参数错误");

        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, service.jsapiPay(code, orderNo));
    }

    @ApiOperation(value = "支付状态")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/status")
    public ResponseJson status(@ApiParam(value = "订单编号", required = true) @RequestParam(value = "orderNo") String orderNo) {
        Assert.hasText(orderNo, "参数错误");

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, service.status(orderNo));
    }

}