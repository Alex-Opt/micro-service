package com.ly.mt.mall.h5.pay.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.pay.service.PayService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.WxPayTradeType.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "支付接口")
@RestController
@RequestMapping("/mall/h5/pay")
public class PayController {
    @Resource
    PayService service;

    @ApiOperation(value = "支付宝WAP支付", notes = "操作成功后返回result：form页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "returnUrl", value = "支付成功后跳转地址", paramType = "query", required = true),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "spuName", value = "订单中商品spu名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/al")
    public ResponseJson alPay(@RequestParam(value = "orderNo") String orderNo,
                              @RequestParam(value = "returnUrl") String returnUrl,
                              @RequestParam(value = "spuName") String spuName) {
        // 参数校验
        if (StringUtil.isEmpty(returnUrl) || StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(spuName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.alPay(orderNo, returnUrl, spuName);
    }


    @ApiOperation(value = "支付宝WAP支付状态", notes = "result=20：支付失败；result=30：支付成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/alPayStatus")
    public ResponseJson alPayStatus(@RequestParam(value = "orderNo") String orderNo) {
        // 参数校验
        if (StringUtil.isEmpty(orderNo)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.alPayStatus(orderNo);
    }


    @ApiOperation(value = "微信h5-WAP支付", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "spuName", value = "订单中商品spu名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx")
    public ResponseJson wxPay(@RequestParam(value = "orderNo") String orderNo,
                              @RequestParam(value = "spuName") String spuName) {
        if (StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(spuName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.wxPay(orderNo, spuName, WX_PAY_TRADE_TYPE_MWEB.getType());
    }


    @ApiOperation(value = "微信H5-WAP支付状态", notes = "result=20：支付失败；result=30：支付成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wxPayStatus")
    public ResponseJson wxPayStatus(@RequestParam(value = "orderNo") String orderNo) {
        // 参数校验
        if (StringUtil.isEmpty(orderNo)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.wxPayStatus(orderNo);
    }

    @ApiOperation(value = "微信内浏览器支付第一步:授权登陆接口，因为第二步统一下单接口需要的入参openid，需要这一步获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "填写第一步获取的code参数", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/confirmAuthorization")
    public ResponseJson confirmAuthorization(@RequestParam(value = "code") String code) {
        if (StringUtil.isEmpty(code)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "code值不能为空!");
        }
        return service.confirmAuthorization(code);
    }

    @ApiOperation(value = "微信内浏览器支付第二步：调用统一下单api,返回预订单信息：prepay_id。", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "spuName", value = "订单中商品spu名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wxJsPay")
    public ResponseJson wxJsPay(@RequestParam(value = "orderNo") String orderNo, @RequestParam(value = "spuName") String spuName) {
        if (StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(spuName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.wxPay(orderNo, spuName, WX_PAY_TRADE_TYPE_JSAPI.getType());
    }

    @ApiOperation(value = "微信内浏览器支付第三步:调起H5支付页面需要的参数封装接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prepayId", value = "预付单Id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/getJsApiParam")
    public ResponseJson getJsApiParam(@RequestParam(value = "prepayId") String prepayId) {
        if (StringUtil.isEmpty(prepayId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "预付单Id值不能为空!");
        }
        return service.paySignAgain(prepayId, WX_PAY_TRADE_TYPE_JSAPI.getType());
    }


    @ApiOperation(value = "微信小程序支付第一步:统一下单接口", notes = "操作成功后返回result：拉起微信客户端的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "spuName", value = "订单中商品spu名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wxAppletPay")
    public ResponseJson wxAppletPay(@RequestParam(value = "orderNo") String orderNo, @RequestParam(value = "spuName") String spuName) {
        if (StringUtil.isEmpty(orderNo) || StringUtil.isEmpty(spuName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.wxPay(orderNo, spuName, WX_PAY_TRADE_TYPE_APPLET.getType());
    }

    @ApiOperation(value = "微信小程序支付第二步:再次签名，封装入参给前台吊起微信支付页面 接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prepayId", value = "预付单Id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/paySignAgain")
    public ResponseJson paySignAgain(@RequestParam(value = "prepayId") String prepayId) {
        if (StringUtil.isEmpty(prepayId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "预付单Id值不能为空!");
        }
        return service.paySignAgain(prepayId, WX_PAY_TRADE_TYPE_APPLET.getType());
    }

}