package com.ly.mt.activity.advertisement.controller;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.service.AdvertisementOrderService;
import com.ly.mt.activity.advertisement.vo.PageOrderVo;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Base64;

@Api("广告活动订单接口")
@RestController
@RequestMapping("/activity1/ad/order/")
public class AdvertisementOrderController {
    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(AdvertisementOrderController.class);
    @Resource
    private AdvertisementOrderService service;

    @ApiOperation("活动页面H5下单")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tobaccoSku", value = "烟杆sku", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tobaccoSkuNum", value = "烟杆数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cartridgesSku", value = "烟弹sku", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cartridgesSkuNum", value = "烟弹数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userAddress", value = "详细地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "source", value = "来源", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "媒体类型 ", required = true, paramType = "query"),
            @ApiImplicitParam(name = "channel", value = "渠道商", required = true, paramType = "query"),
            @ApiImplicitParam(name = "material", value = "素材", required = true, paramType = "query"),
            @ApiImplicitParam(name = "paymentType", value = "支付方式", required = true, paramType = "query"),
    })*/
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回orderId,orderNo"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/bookingGghdOrder")
    public ResponseJson bookingOrder(@RequestBody String pageOrder){
        JSONObject pageOrderJsonObj = JSONObject.parseObject(JSONObject.parseObject(pageOrder).get("pageOrder").toString());
        //烟杆sku 烟杆数量 烟弹sku 烟弹数量 详细地址 电话 姓名 订单来源 订单媒体类型 渠道商Id 渠道商Id 支付方式 3 :支付宝   6 其他
       LOGGER.info("活动下单请求开始 入参 ：",pageOrder);
        if (checkParamter(pageOrderJsonObj)) return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);

        try{
            LOGGER.info("下单请求开始",pageOrderJsonObj);
            return service.bookingActivityOrder(pageOrderJsonObj);
        }catch (Exception e){
            LOGGER.info("下单请求异常",pageOrderJsonObj);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回orderId,orderNo"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/bookingGghdOrder2c")
    public ResponseJson bookingGghdOrder2c(@RequestBody String pageOrder){
        String paramStr = getEncodeParamString(pageOrder);
        JSONObject pageOrderJsonObj = JSONObject.parseObject(paramStr);
        LOGGER.info("活动下单请求开始 入参 ：",pageOrder);
        if (checkParamter(pageOrderJsonObj)) return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        try{
            LOGGER.info("2c活动下单请求开始",pageOrderJsonObj);
            return service.bookingActivityOrder2c(pageOrderJsonObj);
        }catch (Exception e){
            LOGGER.info("2c活动下单请求异常",pageOrderJsonObj);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ '*'>>0x1);
        }
        String s = new String(a);
        return s;
    }
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回orderId,orderNo"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/bookingGghdOrderLittleSmoke")
    public ResponseJson bookingGghdOrderLittleSmoke(@RequestBody String pageOrder){
        String paramStr = getEncodeParamString(pageOrder);
        JSONObject pageOrderJsonObj = JSONObject.parseObject(paramStr);
        if(null == pageOrderJsonObj.getString("tobaccoSku") ||
            null ==pageOrderJsonObj.getString("tobaccoSkuPrice") ||
            null == pageOrderJsonObj.getString("tobaccoSkuNum") ||
                null == pageOrderJsonObj.getString("address") ||
                !StringUtil.isPhoneNumber(pageOrderJsonObj.getString("mobile")) ||
                null == pageOrderJsonObj.getString("userName") ||
                null == pageOrderJsonObj.getString("source") ||
                null == pageOrderJsonObj.getString("type") ||
                null == pageOrderJsonObj.getString("provinceCode") ||
                null == pageOrderJsonObj.getString("provinceName") ||
                null == pageOrderJsonObj.getString("cityCode") ||
                null == pageOrderJsonObj.getString("cityName") ||
                null == pageOrderJsonObj.getString("districtName") ||
                null == pageOrderJsonObj.getString("districtCode") ||
                /*null ==pageOrderJsonObj.getString("channel")&&
                null ==pageOrderJsonObj.getString("material")&&*/
                null == pageOrderJsonObj.getString("paymentType")
        ) {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        return service.bookingGghdOrderLittleSmoke(pageOrderJsonObj);
    }

    private String getEncodeParamString(@RequestBody String pageOrder) {
        LOGGER.info("小烟下单请求参数(加密前)={}",pageOrder);
        String paramStr = convertMD5(JSONObject.parseObject(pageOrder).get("pageOrder").toString());
        LOGGER.info("小烟下单请求参数(加密后)={}",paramStr);
        return paramStr;
    }


    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回订单详情"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/getOrderInfo2c")
    public  ResponseJson getOrderInfo2c(@RequestParam(value = "orderId",required = true)String orderId){
        JSONObject param = new JSONObject();
        param.put("id",orderId);
        return service.getOrderInfo2c(param);
    }


    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回订单详情"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    @PostMapping("/sendSms")
    public  ResponseJson sendSms(@RequestParam(value = "orderNo",required = true)String orderNo,
                                 @RequestParam(value = "mobile",required = true)String mobile){
        JSONObject param = new JSONObject();
        param.put("orderNo",orderNo);
        param.put("mobile",mobile);
        try{
            service.sendSms(orderNo,mobile);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }


    }

    private boolean checkParamter(JSONObject pageOrderJsonObj) {
        if (
                null == pageOrderJsonObj.getString("tobaccoSku") &&
                        null == pageOrderJsonObj.getString("tobaccoSkuNum") &&
                        null == pageOrderJsonObj.getString("cartridgesSku") &&
                        null == pageOrderJsonObj.getString("cartridgesSkuNum") &&
                        null == pageOrderJsonObj.getString("address") &&
                        !StringUtil.isPhoneNumber(pageOrderJsonObj.getString("mobile")) &&
                        null == pageOrderJsonObj.getString("userName") &&
                        null == pageOrderJsonObj.getString("source") &&
                        null == pageOrderJsonObj.getString("type") &&
                        null == pageOrderJsonObj.getString("provinceCode") &&
                        null == pageOrderJsonObj.getString("provinceName") &&
                        null == pageOrderJsonObj.getString("cityCode") &&
                        null == pageOrderJsonObj.getString("cityName") &&
                        null == pageOrderJsonObj.getString("districtName") &&
                        null == pageOrderJsonObj.getString("districtCode") &&
                /*null ==pageOrderJsonObj.getString("channel")&&
                null ==pageOrderJsonObj.getString("material")&&*/
                        null == pageOrderJsonObj.getString("paymentType")
        ) {
            return true;
        }
        return false;
    }


    @PostMapping(value = "/generateOrderForMafengwo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回订单详情"),
            @ApiResponse(code = 1, message = "获取失败!")
    })
    public ResponseJson generateOrderForMafengwo(@RequestBody String pageOrder){
        String paramStr = getEncodeParamString(pageOrder);
        PageOrderVo pageOrderVo = JSONObject.toJavaObject(JSONObject.parseObject(paramStr), PageOrderVo.class);
        LOGGER.info("马蜂窝下单接口请求参数:{}",pageOrderVo);
        return service.generateOrderForMafengwo(pageOrderVo);
    }


}
