package com.ly.mt.purchase.web.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.purchase.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.method.PurchaseMethodEnum.PURCHASE_SHOP_PURCHASES_DISCOUNT_BY_SHOPID;

/**
 * 商家进货优惠
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 23:12:12
 */
@Api(description = "B端商家进货优惠信息",value = "ShopPurchasesDiscountController")
@RequestMapping("/b/shop/purchases")
@RestController
public class ShopPurchasesDiscountController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesDiscountController.class);

    @ApiOperation(value = "根据店铺shopId统计商家的累计优惠信息")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/getPurchasesInfo")
    public JSONObject getPurchasesDiscountInfoByShopId() {
        JSONObject jsonObject = new JSONObject();
        return callPurchaseServer(PURCHASE_SHOP_PURCHASES_DISCOUNT_BY_SHOPID, jsonObject);
    }
}
