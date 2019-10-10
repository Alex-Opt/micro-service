package com.ly.mt.center.third.wx.controller;

import com.ly.mt.center.third.wx.entity.WxPayOrder;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "微信——支付服务")
@RestController
@RequestMapping("/center/third/")
public class WxPayController {
    @ApiOperation(
            value = "发起支付",
            notes = "1、trade_type  \n" +
                    "```  \n" +
                    "MWEB——H5支付  \n" +
                    "JSAPI——JSAPI/小程序支付  \n" +
                    "APP——APP支付  \n" +
                    "```  \n" +
                    "2、appId  \n" +
                    "```  \n" +
                    "当trade_type=MWEB时，appid为公众账号ID  \n" +
                    "当trade_type=APP时，appid为应用APPID  \n" +
                    "当trade_type=JSAPI时，如果时小程序时则appid为小程序ID，其他情况appid为公众账号ID  \n" +
                    "```  \n" +
                    "3、body  \n" +
                    "```  \n" +
                    "微信浏览器公众号支付——商家名称-销售商品类目，例：腾讯-游戏  \n" +
                    "第三方手机浏览器H5支付——浏览器打开的移动网页的主页title名-商品概述，例：腾讯充值中心-QQ会员充值  \n" +
                    "第三方APPAPP支付——应用市场上的APP名字-商品概述，例：天天爱消除-游戏充值  \n" +
                    "```  \n" +
                    "4、scene_info  \n" +
                    "```  \n" +
                    "当交易类型为MWEB时必填，例：{\\\"h5_info\\\":{\\\"type\\\":\\\"Wap\\\",\\\"wap_url\\\":\\\"http://mall.motivape.cn\\\",\\\"wap_name\\\":\\\"MOTI官方商城\\\"}}  \n" +
                    "```  \n" +
                    "5、result  \n" +
                    "```  \n" +
                    "当trade_type=MWEB时，返回mweb_url  \n" +
                    "当trade_type!=MWEB时，返回prepay_id  \n" +
                    "```  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx/pay/pay")
    public ResponseJson pay(@RequestBody WxPayOrder wxPayOrder) {
        return null;
    }


    @ApiOperation(
            value = "wap支付状态查询",
            notes = "1、result  \n" +
                    "```  \n" +
                    "20——支付失败  \n" +
                    "30——支付成功  \n" +
                    "```  \n" +
                    "2、appId  \n" +
                    "```  \n" +
                    "当trade_type=MWEB时，appid为公众账号ID  \n" +
                    "当trade_type=APP时，appid为应用APPID  \n" +
                    "当trade_type=JSAPI时，如果时小程序时则appid为小程序ID，其他情况appid为公众账号ID  \n" +
                    "```  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "out_trade_no", value = "订单编号", paramType = "query", required = true),
            @ApiImplicitParam(name = "appid", value = "appid", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx/pay/status")
    public ResponseJson status() {
        return null;
    }



    @ApiOperation(value = "微信内浏览器支付授权登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "填写第一步获取的code参数", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx/confirm/authorization")
    public ResponseJson confirmAuthorization() {
        return null;
    }



    @ApiOperation(value = "微信内浏览器支付或者小程序支付在统一下单接口后，组合数据再次签名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "微信分配的小程序ID", paramType = "query", required = true),
            @ApiImplicitParam(name = "prepayId ", value = "统一下单接口返回的 prepay_id ", paramType = "query", required = true),
            @ApiImplicitParam(name = "merchantKey ", value = "签名需要的入参key值 ", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wx/pay/signAgain")
    public ResponseJson paySignAgain() {
        return null;
    }
}