package com.ly.mt.cabinet.c.order.controller;

import com.ly.mt.cabinet.c.order.entity.WebGZG;
import com.ly.mt.cabinet.c.order.entity.WebOrderVo;
import com.ly.mt.cabinet.c.order.service.GzgOrderService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 格子柜订单控制层
 */
@RestController
@RequestMapping("/cabinet/c/gzg/")
@Api(value = "格子柜订单控制层", tags = "order")
public class GzgOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GzgOrderController.class);
    @Autowired
    GzgOrderService gzgOrderService;

    @ApiOperation(value = "格子柜订单生成接口")
    @PostMapping("gzgOrderGenerate")
    public ResponseJson GzgOrderGenerate(@ApiParam(name = "webGZG", value = "前端传入格子编号和货物skuid和格子数", required = true) @RequestBody WebOrderVo webGZG) {
        String code = webGZG.getImei();
        List<WebGZG> skuIds = webGZG.getSkuIds();
        if (null == code || "null".equals(code) || StringUtil.isEmpty(code) || skuIds.size() == 0) {
            LOGGER.info("购买生成订单GZGOqianduanrderGenerateController.GzgOrderGenerate---------参数异常");
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try {
            ResponseJson responseJson = gzgOrderService.gzgOrderGenerate(webGZG);
            return responseJson;

        } catch (Exception e) {
            LOGGER.info("购买生成订单{}格子柜生成订单失败，错误信息：{}", code, e.getMessage());
            e.printStackTrace();
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }

    }


    @ApiOperation(value = "格子柜订单列表")
    @PostMapping("gzgOrderList")
    public ResponseJson getGzgOrderListByUserId(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {


        if (null == userId || "null".equals(userId) || StringUtil.isEmpty(userId)) {
            LOGGER.info("个人中心订单列表 GZGOrderGenerateController.GzgOrderGenerate---------参数异常");
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try {
            ResponseJson responseJson = gzgOrderService.getGzgOrderListByUserId(userId);
            return responseJson;

        } catch (Exception e) {
            LOGGER.info("个人中心订单列表 {}用户生成订单列表失败，错误信息：{}", userId,e.getMessage());
            e.printStackTrace();
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "订单是否超时或支付完成")
    @PostMapping("isOrderTimeOutOrComplete")
    public ResponseJson isOrderTimeOut(@RequestParam("orderId") String orderId){
        if(StringUtil.isEmpty(orderId)||  "undefined".equals(orderId)){
            LOGGER.info("订单是否超时或支付完成GZGOrderGenerateController.isOrderTimeOut---------参数异常");
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try{
            return  gzgOrderService.isOrderTimeOutOrComplete(orderId);
        }catch (Exception e){
            LOGGER.info("订单是否超时或支付完成GZGOrderGenerateController.isOrderTimeOut---------程序执行异常");
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "重新下单")
    @PostMapping("retryOrder")
    public ResponseJson retryOrder(@RequestParam(value = "orderId",required = true) String orderId,
                                   @RequestParam(value = "userId",required = false, defaultValue = "")String userId){
        if(StringUtil.isEmpty(orderId)){
            LOGGER.info("GZGOrderGenerateController.retryOrder---------参数异常");
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try{
            return  gzgOrderService.retryOrder(orderId,userId);
        }catch (Exception e){
            LOGGER.info("重新下单GZGOrderGenerateController.retryOrder---------程序执行异常");
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "个人中心-订单详情")
    @PostMapping("orderDetail")
    public ResponseJson orderDetatil(@RequestParam(value = "orderId",required = true) String orderId){
        if(StringUtil.isEmpty(orderId)){
            LOGGER.info("个人中心订单详情GZGOrderGenerateController.orderDetail---------参数异常");
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数错误");
        }
        try{
            return  gzgOrderService.orderDetail(orderId);
        }catch (Exception e){
            LOGGER.info("个人中心订单详情GZGOrderGenerateController.orderDetatil---------程序执行异常");
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

}
