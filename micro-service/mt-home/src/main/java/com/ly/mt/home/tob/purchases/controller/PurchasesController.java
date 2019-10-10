package com.ly.mt.home.tob.purchases.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.TradeStatus;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.pay.service.PayService;
import com.ly.mt.home.tob.purchases.service.PurchasesService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 商家进货单
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Api(tags = "商家进货单")
@RestController
@RequestMapping("/home/purchases")
public class PurchasesController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    PurchasesService purchasesService;

    @Resource
    PayService payService;

    @ApiOperation(value = "我的进货单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "行数", required = true),
            @ApiImplicitParam(name = "page", value = "页码", required = true)
    })
    @PostMapping("/list")
    public ResponseJson getPurchasesList(@RequestBody JSONObject jsonObject) {
        String rows = jsonObject.getString("rows");
        String page = jsonObject.getString("page");
        Assert.hasText(rows, "参数异常");
        Assert.hasText(page, "参数异常");
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, purchasesService.queryPurchasesList(rows, page));
    }


    @ApiOperation(value = "进货单详情接口")
    @PostMapping("/detail")
    public ResponseJson tradeOrderDetail(@ApiParam(value = "订单id", required = true) @RequestParam(value = "id") String id) {
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, purchasesService.getPurchasesAndItem(id));
    }


    @ApiOperation(value = "商家下单")
    @PostMapping("/addOrder")
    public ResponseJson generateOrder(@ApiParam(value = "订单信息参数集合--shopPurchasesVo{itemList:商品集合,address:收货地址},itemList参数信息--shopPurchasesItemVo{shopPurchasesItemVo:skuId,spuId,skuName,skuPrice,skuNum}", required = true)
                                      @RequestBody ShopPurchasesVo shopPurchasesVo) {
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, purchasesService.addPurchases(shopPurchasesVo));
    }


    @ApiOperation(value = "取消订单接口")
    @PostMapping("/cancelOrder")
    public ResponseJson cancelOrder(@ApiParam(value = "订单id", required = true) @RequestParam(value = "id") String id) {
        purchasesService.cancelPurchases(id);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation(value = "确认收货接口")
    @PostMapping("/completeOrder")
    public ResponseJson completeOrder(@ApiParam(value = "订单id", required = true) @RequestParam(value = "id") String id) {
        purchasesService.completePurchases(id);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation(value = "获取运费")
    @PostMapping("/getPurchasesFreight")
    public ResponseJson getPurchasesFreight() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, purchasesService.getPurchasesFreight());
    }


    @ApiOperation(value = "根据订单号查询物流信息")
    @PostMapping("/getOrderDeliveryInfo")
    public ResponseJson getOrderDeliveryInfo(@ApiParam(value = "订单id", required = true) @RequestParam(value = "id") String id) {
        return purchasesService.getPurchasesDeliveryInfo(id);
    }

    @ApiOperation(value = "检查订单状态")
    @PostMapping("/checkPurchases")
    public ResponseJson checkPurchases(@ApiParam(value = "订单id", required = true) @RequestParam(value = "id") String id) {
        boolean payFlag = purchasesService.checkPurchasesStatus(id);
        boolean finishFlag = false;
        try {
            finishFlag = payService.status(id).getString("code").equals(TradeStatus.TRADE_STATUS_PAY_SUCCESS.getId());
        } catch (Exception e) {
            logger.error("PurchasesController.checkPurchases{}", e.getMessage());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payFlag", payFlag);
        jsonObject.put("finishFlag", finishFlag);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, jsonObject);
    }

}
