package com.ly.mt.mall.h5.ofo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.ofo.service.OfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "ofo分销接口")
@RestController
@RequestMapping("/mall/h5/ofo")
public class OfoController {
    @Resource
    OfoService service;

    @ApiOperation("查询对接ofo商品spu列表接口")
    @PostMapping("/goodsSpuList")
    public ResponseJson goodsOfoSpuList() {
        return service.goodsOfoSpuList();
    }

    @ApiOperation(value = "根据spuId 查询详情接口", notes = "查询对接ofo商品spu详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "spuId", paramType = "query", required = true)
    })
    @PostMapping("/queryGoodsOfoSpuById")
    public ResponseJson queryGoodsOfoSpuById(@RequestParam(value = "spuId") String spuId) {
        // 校验参数
        if (StringUtil.isEmpty(spuId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.queryGoodsOfoSpuById(spuId);
    }

    @ApiOperation(value = "根据skuId 查询图片接口", notes = "查询对接ofo商品sku图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "skuId", paramType = "query", required = true)
    })
    @PostMapping("/querySkupicturcBySkuId")
    public ResponseJson querySkupicturcBySkuId(@RequestParam(value = "skuId") String skuId) {
        // 校验参数
        if (StringUtil.isEmpty(skuId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.querySkupicturcBySkuId(skuId);
    }

    @ApiOperation(value = "手机号验证码登录", notes = "登录成功后返回result,当registStatus=0时为新用户需要补全数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/mobileLogin")
    public ResponseJson mobileLogin(@RequestParam(value = "userName") String userName,
                                    @RequestParam(value = "mobile") String mobile,
                                    @RequestParam(value = "dynamicCode") String dynamicCode,
                                    HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(userName) || !StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.mobileLogin(userName, mobile, dynamicCode, request);
    }


    @ApiOperation("下订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "skuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "skuNum", value = "sku数量", paramType = "query", required = true),
            @ApiImplicitParam(name = "provinceCode", value = "省份", paramType = "query", required = true),
            @ApiImplicitParam(name = "provinceName", value = "省份名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "cityCode", value = "城市", paramType = "query", required = true),
            @ApiImplicitParam(name = "cityName", value = "城市名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "districtCode", value = "区域", paramType = "query", required = true),
            @ApiImplicitParam(name = "districtName", value = "区域名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "userAddress", value = "详细地址", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "appType", value = "活动类型：ofo：6，扑克牌：7", paramType = "query", required = true),
            @ApiImplicitParam(name = "actiClass", value = "actiClass：分类值，扑克牌：A、2、3、4、5、6、7、8、9、10、J、Q、K", paramType = "query", required = true)
    })
    @PostMapping("/createOrder")
    public ResponseJson createOrder(String skuId, String skuNum, String provinceCode, String provinceName, String cityCode,
                                    String cityName, String districtCode, String districtName, String userAddress, String mobile, String appType,String actiClass, HttpServletRequest request) {
        if (StringUtil.isEmpty(skuId) || StringUtil.isEmpty(skuNum) || StringUtil.isEmpty(provinceCode) || StringUtil.isEmpty(provinceName) || StringUtil.isEmpty(cityCode)
                || StringUtil.isEmpty(cityName) || StringUtil.isEmpty(districtCode) || StringUtil.isEmpty(districtName) || StringUtil.isEmpty(userAddress)
                || StringUtil.isEmpty(mobile) || StringUtil.isEmpty(appType)||StringUtil.isEmpty(actiClass)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        return service.createOrder(skuId, skuNum, provinceCode, provinceName, cityCode, cityName, districtCode, districtName, userAddress, mobile, appType,actiClass, request);
    }

    @ApiOperation(value = "保存类型和编码", notes = "保存类型和编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型，type值为：ofo、poke", paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "编号", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/saveTypeAndCode")
    public ResponseJson saveTypeAndCode(@RequestParam(value = "type") String type,
                                        String code,
                                        HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(type)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.saveTypeAndCode(type, code, request);
    }

    @ApiOperation(value = "保存支付方式", notes = "保存支付方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", paramType = "query", required = true),
            @ApiImplicitParam(name = "paymentType", value = "支付类型", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/savePaymentType")
    public ResponseJson savePaymentType(@RequestParam(value = "orderId") String orderId,
                                        @RequestParam(value = "paymentType") String paymentType,
                                        HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(orderId) || StringUtil.isEmpty(paymentType)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        String freight = "0";
       /* if (paymentType.equals("1") || paymentType.equals("2")) {
            freight = "6";
        } else if (paymentType.equals("6")) {
            freight = "9";
        } else {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }*/
        return service.savePaymentType(orderId, paymentType, freight);
    }

    @ApiOperation(value = "根据appType和actiClass 查询数据", notes = "查询对接ofo商品sku图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appType", value = "appType:  ofo：6，扑克牌：7", paramType = "query", required = true),
            @ApiImplicitParam(name = "actiClass", value = "actiClass：分类值，扑克牌：A、2、3、4、5、6、7、8、9、10、J、Q、K", paramType = "query", required = true)
    })
    @PostMapping("/queryGoodsFrontByActiClass")
    public ResponseJson queryGoodsFrontByActiClass(@RequestParam(value = "appType") String appType,
                                                   @RequestParam(value = "actiClass") String actiClass) {
        // 校验参数
        if (StringUtil.isEmpty(appType) || StringUtil.isEmpty(actiClass)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.queryGoodsFrontByActiClass(appType, actiClass);
    }

    @ApiOperation(value = "根据orderSource查询订单数据", notes = "查询订单数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderSource", value = "订单来源: 扑克牌：1000000", paramType = "query", required = true)
    })
    @PostMapping("/queryOrderList")
    public ResponseJson queryOrderList(@RequestParam(value = "orderSource") String orderSource) {
        // 校验参数
        if (StringUtil.isEmpty(orderSource)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.queryOrderList(orderSource);
    }

    /*@ApiOperation("申请提现")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/applyWithdraw")
    public ResponseJson applyWithdraw(String userId, HttpServletRequest request) {

        return service.applyWithdrawal(userId);
    }*/
}