package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopPurchasesDiscount;
import com.ly.mt.center.data.shop.service.ShopPurchasesDiscountService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家进货优惠接口")
@RestController
@RequestMapping("/data/center/shopPurchasesDiscount")
public class ShopPurchasesDiscountController {
    @Resource
    ShopPurchasesDiscountService shopPurchasesDiscountService;
    @ApiOperation(value = "保存ShopPurchasesDiscount")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopPurchasesDiscount")
    public ResponseJson insertShopPurchasesDiscount(@RequestBody JSONObject jsonObject) {
        return shopPurchasesDiscountService.insertShopPurchasesDiscount(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopPurchasesDiscount",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopPurchasesDiscount")
    public ResponseJson deleteShopPurchasesDiscount() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopPurchasesDiscount",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopPurchasesDiscount")
    public ResponseJson updateShopPurchasesDiscount(@RequestBody JSONObject jsonObject) {
        return shopPurchasesDiscountService.updateShopPurchasesDiscount(jsonObject);
    }


    @ApiOperation(value = "查询ShopPurchasesDiscount", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurchasesDiscount")
    public ResponseJson getShopPurchasesDiscount(@RequestBody JSONObject jsonObject) {
        return shopPurchasesDiscountService.getShopPurchasesDiscount(jsonObject);
    }

    @ApiOperation(value = "查询商家累计优惠", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopTotalDiscount")
    public ResponseJson getShopTotalDiscount(@RequestBody JSONObject jsonObject) {
        return shopPurchasesDiscountService.getShopTotalDiscount(jsonObject);
    }
}